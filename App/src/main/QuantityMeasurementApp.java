package main;

/**
 * QuantityMeasurementApp - UC8
 *
 * Refactored design where LengthUnit is extracted
 * as a standalone enum with conversion responsibility.
 *
 * QuantityLength now focuses only on:
 * - Equality
 * - Arithmetic
 * - Delegating conversion responsibility
 *
 * @author Developer
 * @version 8.0
 */
public class QuantityMeasurementApp {

    /**
     * Length class representing any length measurement.
     */
    public static class Length {

        private final double value;
        private final LengthUnit unit;

        /**
         * Constructor
         *
         * @param value measurement value
         * @param unit measurement unit
         */
        public Length(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }

            this.value = value;
            this.unit = unit;
        }

        /**
         * Convert this length to base unit (feet)
         *
         * Delegates conversion responsibility to LengthUnit.
         *
         * @return value in feet
         */
        private double convertToBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        /**
         * Convert from base unit to target unit
         *
         * Delegates conversion responsibility to LengthUnit.
         *
         * @param baseValue value in feet
         * @param targetUnit target unit
         * @return converted value
         */
        private double convertFromBaseToTargetUnit(
                double baseValue,
                LengthUnit targetUnit) {

            return targetUnit.convertFromBaseUnit(baseValue);
        }

        /**
         * Compare two lengths.
         *
         * @param thatLength another length
         * @return true if equal
         */
        private boolean compare(Length thatLength) {

            double thisBase = this.convertToBaseUnit();
            double thatBase = thatLength.convertToBaseUnit();

            return Double.compare(thisBase, thatBase) == 0;
        }

        /**
         * Equality override.
         */
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

            Length other = (Length) obj;

            return compare(other);
        }

        /**
         * Convert this length to target unit.
         *
         * @param targetUnit target unit
         * @return converted length
         */
        public Length convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = convertToBaseUnit();

            double converted =
                    convertFromBaseToTargetUnit(baseValue, targetUnit);

            return new Length(converted, targetUnit);
        }

        /**
         * UC6 Addition:
         * Result in first operand unit.
         *
         * @param thatLength another length
         * @return result length
         */
        public Length add(Length thatLength) {

            if (thatLength == null) {
                throw new IllegalArgumentException("Cannot add null");
            }

            return addAndConvert(thatLength, this.unit);
        }

        /**
         * UC7 Addition:
         * Result in specified target unit.
         *
         * @param thatLength another length
         * @param targetUnit target unit
         * @return result length
         */
        public Length add(Length thatLength, LengthUnit targetUnit) {

            if (thatLength == null) {
                throw new IllegalArgumentException("Cannot add null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            return addAndConvert(thatLength, targetUnit);
        }

        /**
         * Private utility method for addition.
         *
         * @param length another length
         * @param targetUnit target unit
         * @return result
         */
        private Length addAndConvert(
                Length length,
                LengthUnit targetUnit) {

            double thisBase = this.convertToBaseUnit();
            double thatBase = length.convertToBaseUnit();

            double sumBase = thisBase + thatBase;

            double result =
                    convertFromBaseToTargetUnit(sumBase, targetUnit);

            return new Length(result, targetUnit);
        }

        /**
         * hashCode override.
         */
        @Override
        public int hashCode() {
            return Double.hashCode(convertToBaseUnit());
        }

        /**
         * String representation.
         */
        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }
    }

    /**
     * Equality demonstration
     */
    public static boolean demonstrateLengthEquality(
            Length length1,
            Length length2) {

        boolean result = length1.equals(length2);

        System.out.println("The two length measurements are "
                + (result ? "equal." : "not equal."));

        return result;
    }

    /**
     * Conversion demonstration
     */
    public static Length demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toUnit) {

        Length length = new Length(value, fromUnit);

        return length.convertTo(toUnit);
    }

    /**
     * Overloaded conversion demonstration
     */
    public static Length demonstrateLengthConversion(
            Length length,
            LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    /**
     * UC6 addition demonstration
     */
    public static Length demonstrateLengthAddition(
            Length length1,
            Length length2) {

        return length1.add(length2);
    }

    /**
     * UC7 addition demonstration
     */
    public static Length demonstrateLengthAddition(
            Length length1,
            Length length2,
            LengthUnit targetUnit) {

        return length1.add(length2, targetUnit);
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        Length oneFoot =
                new Length(1.0, LengthUnit.FEET);

        Length twelveInches =
                new Length(12.0, LengthUnit.INCHES);

        System.out.println(
                oneFoot.convertTo(LengthUnit.INCHES));

        System.out.println(
                oneFoot.add(twelveInches, LengthUnit.FEET));

        System.out.println(
                twelveInches.equals(
                        new Length(1.0, LengthUnit.YARDS)));
    }
}