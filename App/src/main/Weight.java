package main;

/**
 * Weight.java
 *
 * Represents a weight quantity with support for:
 * - Equality
 * - Conversion
 * - Addition
 */
public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Weight unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Weight value must be finite");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    /**
     * Convert this weight to kilogram.
     */
    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    /**
     * Convert from kilogram to target unit.
     */
    private double convertFromBaseToTargetUnit(
            double weightInKg,
            WeightUnit targetUnit) {

        return targetUnit.convertFromBaseUnit(weightInKg);
    }

    /**
     * Equality logic.
     */
    private boolean compare(Weight thatWeight) {

        double thisBase = this.convertToBaseUnit();
        double thatBase = thatWeight.convertToBaseUnit();

        return Double.compare(thisBase, thatBase) == 0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Weight other = (Weight) obj;

        return compare(other);
    }

    /**
     * Convert this weight to target unit.
     */
    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();

        double converted =
                convertFromBaseToTargetUnit(baseValue, targetUnit);

        return new Weight(converted, targetUnit);
    }

    /**
     * UC6 style addition.
     * Result in first operand unit.
     */
    public Weight add(Weight thatWeight) {

        if (thatWeight == null) {
            throw new IllegalArgumentException("Cannot add null Weight");
        }

        return addAndConvert(thatWeight, this.unit);
    }

    /**
     * UC7 style addition.
     * Result in specified target unit.
     */
    public Weight add(Weight thatWeight, WeightUnit targetUnit) {

        if (thatWeight == null) {
            throw new IllegalArgumentException("Cannot add null Weight");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        return addAndConvert(thatWeight, targetUnit);
    }

    /**
     * Internal addition helper.
     */
    private Weight addAndConvert(
            Weight weight,
            WeightUnit targetUnit) {

        double thisBase = this.convertToBaseUnit();
        double thatBase = weight.convertToBaseUnit();

        double sumBase = thisBase + thatBase;

        double result =
                convertFromBaseToTargetUnit(sumBase, targetUnit);

        return new Weight(result, targetUnit);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    @Override
    public String toString() {
        return String.format("%.5f %s", value, unit);
    }
}