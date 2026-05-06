package main;

/**
 * Generic Quantity Class.
 *
 * Supports:
 * - Equality
 * - Conversion
 * - Addition
 *
 * Works with any unit implementing IMeasurable.
 */
public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
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

    /**
     * Convert quantity to target unit.
     */
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);

        double converted =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }

    /**
     * Add quantity in first operand unit.
     */
    public Quantity<U> add(Quantity<U> other) {

        if (other == null) {
            throw new IllegalArgumentException("Cannot add null quantity");
        }

        return add(other, this.unit);
    }

    /**
     * Add quantity in specified target unit.
     */
    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Cannot add null quantity");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        double sumBase =
                thisBase + otherBase;

        double converted =
                targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(converted, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Quantity<?>)) {
            return false;
        }

        Quantity<?> other = (Quantity<?>) obj;

        /*
         * Prevent cross-category comparison
         * Example:
         * LengthUnit vs WeightUnit
         */
        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode() {

        double base =
                unit.convertToBaseUnit(value);

        return Double.hashCode(base);
    }

    @Override
    public String toString() {
        return String.format(
                "Quantity(%.5f, %s)",
                value,
                unit.getUnitName());
    }
}