package main;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-9;

    // UC12 asks rounding subtraction results to 2 decimals
    private static final int SUBTRACTION_ROUNDING_SCALE = 2;

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

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        // Allow converting only within same category (runtime safety for raw type usage)
        if (!this.unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit category conversion.");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateOther(other);
        validateTargetUnit(targetUnit);

        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        double sumBase   = thisBase + otherBase;

        double resultValue = targetUnit.convertFromBaseUnit(sumBase);
        return new Quantity<>(resultValue, targetUnit);
    }

    // =========================
    // UC12: SUBTRACTION
    // =========================

    /** Subtracts other from this, result in this.unit (implicit target unit). */
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    /** Subtracts other from this, result expressed in targetUnit. */
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateOther(other);
        validateTargetUnit(targetUnit);

        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        double diffBase  = thisBase - otherBase;

        double resultValue = targetUnit.convertFromBaseUnit(diffBase);

        // UC12: round subtraction results to 2 decimals
        resultValue = round(resultValue, SUBTRACTION_ROUNDING_SCALE);

        return new Quantity<>(resultValue, targetUnit);
    }

    // =========================
    // UC12: DIVISION
    // =========================

    /**
     * Divides this quantity by other quantity of same category.
     * Returns a dimensionless scalar ratio (double).
     */
    public double divide(Quantity<U> other) {
        validateOther(other);

        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        if (Math.abs(otherBase) < EPSILON) {
            throw new ArithmeticException("Division by zero quantity is not allowed.");
        }
        return thisBase / otherBase;
    }

    // =========================
    // Validation helpers (UC12)
    // =========================

    private void validateOther(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null.");
        }
        if (other.unit == null) {
            throw new IllegalArgumentException("Other quantity unit cannot be null.");
        }
        if (!Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Other quantity value must be finite.");
        }

        // Runtime cross-category safety (important if raw types are used)
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Incompatible quantity categories.");
        }
    }

    private void validateTargetUnit(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        if (!this.unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Target unit is not in the same category.");
        }
    }

    private static double round(double value, int decimals) {
        double factor = Math.pow(10, decimals);
        return Math.round(value * factor) / factor;
    }

    // =========================
    // equals/hashCode/toString
    // =========================

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