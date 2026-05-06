package main;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-9;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite (not NaN/Infinity).");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ------------------------------------------------------------
    // Conversion (unchanged public API)
    // ------------------------------------------------------------
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        if (!this.unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit category conversion.");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    // ------------------------------------------------------------
    // UC13: Public arithmetic API (signatures unchanged)
    // Internals now delegate to centralized helpers
    // ------------------------------------------------------------

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double resultValue = targetUnit.convertFromBaseUnit(resultBase);

        // UC13: Round add/subtract results to 2 decimals
        resultValue = roundToTwoDecimals(resultValue);

        return new Quantity<>(resultValue, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double resultValue = targetUnit.convertFromBaseUnit(resultBase);

        // UC13: Round add/subtract results to 2 decimals
        resultValue = roundToTwoDecimals(resultValue);

        return new Quantity<>(resultValue, targetUnit);
    }

    public double divide(Quantity<U> other) {
        // targetUnit not required for division
        validateArithmeticOperands(other, null, false);
        // UC13: division returns scalar (no unit conversion back)
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ------------------------------------------------------------
    // UC13: Centralized internal DRY logic
    // ------------------------------------------------------------

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (Math.abs(b) < EPSILON) {
                throw new ArithmeticException("Division by zero quantity is not allowed.");
            }
            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.op = op;
        }

        public double compute(double leftBase, double rightBase) {
            return op.applyAsDouble(leftBase, rightBase);
        }
    }

    /**
     * Validates operands for arithmetic operations (add, subtract, divide).
     * Ensures:
     * - other is not null
     * - category compatibility (same enum/unit class)
     * - finiteness of both numeric values
     * - targetUnit is validated only when required (add/subtract)
     */
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null.");
        }
        if (other.unit == null) {
            throw new IllegalArgumentException("Other quantity unit cannot be null.");
        }
        if (!Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Other quantity value must be finite (not NaN/Infinity).");
        }

        // Cross-category protection (important if raw types are used)
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Incompatible quantity categories.");
        }

        if (targetUnitRequired) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null.");
            }
            if (!this.unit.getClass().equals(targetUnit.getClass())) {
                throw new IllegalArgumentException("Target unit is not in the same category.");
            }
        }
    }

    /**
     * Converts both operands to base unit and performs the specified arithmetic operation in base units.
     * Returns the base-unit result (for DIVIDE: scalar ratio).
     */
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return operation.compute(thisBase, otherBase);
    }

    private double roundToTwoDecimals(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ------------------------------------------------------------
    // equals / hashCode / toString (unchanged behavior)
    // ------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Quantity)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        long rounded = Math.round(baseValue / EPSILON);
        return Objects.hash(unit.getClass(), rounded);
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.6f, %s)", value, unit.getUnitName());
    }
}