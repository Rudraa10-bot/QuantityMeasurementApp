import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-9;

    private final double value;
    private final U unit;

    /**
     * Constructs a new Quantity with the given value and unit.
     *
     * @param value the numerical measurement value
     * @param unit  the measurement unit (must not be null)
     * @throws IllegalArgumentException if unit is null
     */
    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
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
     * Converts this quantity to the specified target unit.
     *
     * @param targetUnit the unit to convert to
     * @return a new Quantity in the target unit
     */
    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    /**
     * Adds another quantity to this quantity.
     * Result is expressed in this quantity's unit (implicit target unit).
     *
     * @param other the other quantity to add
     * @return a new Quantity representing the sum
     */
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    /**
     * Adds another quantity to this quantity with an explicit target unit.
     *
     * @param other      the other quantity to add
     * @param targetUnit the unit for the result
     * @return a new Quantity representing the sum in the target unit
     */
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        double sumBase   = thisBase + otherBase;
        double resultValue = targetUnit.convertFromBaseUnit(sumBase);
        return new Quantity<>(resultValue, targetUnit);
    }

    /**
     * Checks equality between this and another object.
     * Two quantities are equal if and only if:
     *  - They are the same object (reflexive), OR
     *  - The other object is a Quantity of the SAME unit type class
     *    AND their base-unit values are within epsilon tolerance.
     *
     * @param obj the object to compare
     * @return true if logically equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Quantity)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Cross-category type safety: unit classes must match
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
        // Round to avoid floating-point inconsistencies in hash
        long rounded = Math.round(baseValue / EPSILON);
        return Objects.hash(unit.getClass(), rounded);
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.6f, %s)", value, unit.getUnitName());
    }
}