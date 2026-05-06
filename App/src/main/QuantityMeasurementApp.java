package main;

/**
 * QuantityMeasurementApp - UC6: Addition of Two Length Units
 *
 * This class extends UC5 by introducing addition operations between
 * length measurements. The Quantity Length API can add two lengths
 * of potentially different units and return the result in the unit
 * of the first operand.
 *
 * Example:
 * Adding 1 foot and 12 inches yields 2 feet
 * (based on the unit of the first operand)
 *
 * @author Developer
 * @version 1.0
 * @since UC6
 */
public class QuantityMeasurementApp {

    /**
     * Enum representing different length units and their conversion factors.
     * Base unit is inches. All conversion factors are relative to inches.
     *
     * Examples:
     * 1 FOOT = 12.0 inches
     * 1 YARD = 36.0 inches
     * 1 CENTIMETER = 0.393701 inches
     */
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        /**
         * Constructor for LengthUnit enum.
         *
         * @param conversionFactor conversion factor relative to inches
         */
        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        /**
         * Get the conversion factor for this unit.
         *
         * @return conversion factor relative to inches
         */
        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    /**
     * Length class representing any length measurement.
     *
     * This class encapsulates a length value along with its unit.
     * Supports equality, conversion, and addition operations.
     *
     * The result is returned in the unit of the first operand.
     *
     * Example:
     * Length length1 = new Length(3.0, LengthUnit.FEET);
     * Length length2 = new Length(12.0, LengthUnit.INCHES);
     * Length result = length1.add(length2); // Result: 4.0 FEET
     */
    public static class Length {

        // Instance variables
        private final double value;
        private final LengthUnit unit;

        /**
         * Constructor to initialize length value and unit.
         *
         * @param value the measurement value
         * @param unit  the unit of measurement
         */
        public Length(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        /**
         * Converts this length value to the base unit (inches) with rounding.
         *
         * Private Utility Method used internally for all conversions
         * and comparisons. Ensures consistent rounding to two decimal places.
         *
         * @return the length value in inches, rounded to two decimal places
         */
        private double convertToBaseUnit() {
            double baseValue = this.value * this.unit.getConversionFactor();
            return Math.round(baseValue * 100.0) / 100.0;
        }

        /**
         * Converts a length value from the base unit (inches) to the target unit.
         *
         * This private method mainly came into existence to avoid code duplication
         * in the conversion process as both the convertTo and add methods require
         * this functionality.
         *
         * @param lengthInInches the length value in inches to convert
         * @param targetUnit     the unit to convert the length into
         * @return the converted length value in the target unit, rounded to two decimal places
         */
        private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
            double converted = lengthInInches / targetUnit.getConversionFactor();
            return Math.round(converted * 100.0) / 100.0;
        }

        /**
         * Compare two Length objects for equality based on their values in the base unit.
         *
         * @param thatLength the Length object to compare with
         * @return true if both represent the same physical measurement, false otherwise
         */
        public boolean compare(Length thatLength) {
            if (thatLength == null) {
                return false;
            }

            double thisBaseValue = this.convertToBaseUnit();
            double thatBaseValue = thatLength.convertToBaseUnit();

            return Double.compare(thisBaseValue, thatBaseValue) == 0;
        }

        /**
         * Checks equality between this Length and another object.
         *
         * Overridden Method that implements the Object equals(Object) contract.
         * Performs reference equality check first, then type validation,
         * and finally delegates to the compare(Length) method.
         *
         * Algorithm:
         * 1. Check if both references point to the same object
         * 2. Validate that the other object is not null and is of type Length
         * 3. Cast to Length and invoke compare(Length) for value-based comparison
         *
         * @param obj the object to compare with this Length
         * @return true if both represent the same physical length, false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            // Reference check
            if (this == obj) {
                return true;
            }

            // Null check
            if (obj == null) {
                return false;
            }

            // Type check
            if (getClass() != obj.getClass()) {
                return false;
            }

            // Value-based comparison
            Length other = (Length) obj;
            return this.compare(other);
        }

        /**
         * Convert this length to the specified target unit.
         *
         * Public API Method for unit conversion.
         * Implements the conversion pipeline:
         * base unit conversion, target unit conversion,
         * and rounding to maintain precision consistency.
         *
         * Conversion Pipeline:
         * 1. Validate that targetUnit is not null
         * 2. Convert this instance to the base unit (inches)
         * 3. Convert from inches to targetUnit
         * 4. Round the result to two decimal places
         * 5. Return a new Length instance
         *
         * Immutability Guarantee:
         * This method never modifies the receiver. It always returns
         * a new Length instance, ensuring immutability.
         *
         * @param targetUnit the unit to convert this length into; must not be null
         * @return a new Length representing the same physical length in targetUnit
         * @throws IllegalArgumentException if targetUnit is null
         */
        public Length convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            // Step 1: Convert to base unit (inches)
            double baseValue = this.convertToBaseUnit();

            // Step 2: Convert from base unit to target unit
            double convertedValue = convertFromBaseToTargetUnit(baseValue, targetUnit);

            // Step 3: Return new Length instance (immutability)
            return new Length(convertedValue, targetUnit);
        }

        /**
         * Adds another Length to this Length.
         *
         * Instance method that adds the given Length to this Length.
         * The result is returned in the unit of this (the first) operand.
         *
         * Addition Pipeline:
         * 1. Validate that thatLength is not null
         * 2. Convert both lengths to the base unit (inches)
         * 3. Sum the base unit values
         * 4. Convert the sum back to the unit of this instance
         * 5. Round the result to two decimal places
         * 6. Return a new Length instance with the summed value
         *
         * Immutability Guarantee:
         * This method never modifies either operand. It always returns
         * a new Length instance, ensuring immutability.
         *
         * @param thatLength the Length to add
         * @return a new Length representing the sum in this instance's unit
         * @throws IllegalArgumentException if thatLength is null
         */
        public Length add(Length thatLength) {
            // Validate input
            if (thatLength == null) {
                throw new IllegalArgumentException("Cannot add null Length");
            }

            // Step 1: Convert both to base unit (inches)
            double thisInBase = this.convertToBaseUnit();
            double thatInBase = thatLength.convertToBaseUnit();

            // Step 2: Sum the base unit values
            double sumInBase = thisInBase + thatInBase;

            // Step 3: Convert sum back to the unit of this instance
            double sumInThisUnit = convertFromBaseToTargetUnit(sumInBase, this.unit);

            // Step 4: Return new Length in this unit
            return new Length(sumInThisUnit, this.unit);
        }

        /**
         * Override hashCode() to maintain the equals-hashCode contract.
         *
         * @return hash code based on the base unit value
         */
        @Override
        public int hashCode() {
            return Double.hashCode(convertToBaseUnit());
        }

        /**
         * Returns a string representation of this Length.
         *
         * Format: "%.2f %s" (e.g., "12.00 INCHES", "3.50 FEET")
         *
         * @return a formatted string representation of this length
         */
        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
        }

        /**
         * Get the value of this length.
         *
         * @return the numeric value
         */
        public double getValue() {
            return value;
        }

        /**
         * Get the unit of this length.
         *
         * @return the LengthUnit
         */
        public LengthUnit getUnit() {
            return unit;
        }
    }

    /**
     * Demonstrate length equality between two Length instances.
     *
     * @param length1 the first Length instance
     * @param length2 the second Length instance
     * @return true if the two lengths are equal, false otherwise
     */
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println("The two length measurements are "
                + (result ? "equal." : "not equal."));
        return result;
    }

    /**
     * Demonstrate length equality between two raw values with units.
     *
     * @param value1 the first length value
     * @param unit1  the unit of the first length value
     * @param value2 the second length value
     * @param unit2  the unit of the second length value
     * @return true if the two lengths are equal, false otherwise
     */
    public static boolean demonstrateLengthComparison(
            double value1, LengthUnit unit1,
            double value2, LengthUnit unit2) {
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);
        return demonstrateLengthEquality(l1, l2);
    }

    /**
     * Demonstrate length conversion from one unit to another.
     *
     * Method Overload 1: Takes a numeric value and two units.
     * Used when you have raw values to convert.
     *
     * @param value    the length value to convert
     * @param fromUnit the unit of the length value
     * @param toUnit   the target unit to convert to
     * @return a new Length instance representing the converted length
     */
    public static Length demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toUnit) {
        Length length = new Length(value, fromUnit);
        Length converted = length.convertTo(toUnit);
        System.out.println(value + " " + fromUnit + " = " + converted);
        return converted;
    }

    /**
     * Demonstrate length conversion from a Length instance to another unit.
     *
     * Method Overload 2: Takes an existing Length object and target unit.
     * Used when you already have a Length instance.
     *
     * @param length the Length instance to convert
     * @param toUnit the target unit to convert to
     * @return a new Length instance representing the converted length
     */
    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        Length converted = length.convertTo(toUnit);
        System.out.println(length + " = " + converted);
        return converted;
    }

    /**
     * Demonstrate addition of second QuantityLength to first QuantityLength.
     *
     * The result is returned in the unit of the first operand.
     *
     * @param length1 the first QuantityLength instance
     * @param length2 the second QuantityLength instance
     * @return a new QuantityLength instance representing the sum of the two lengths
     */
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        Length result = length1.add(length2);
        System.out.println(length1 + " + " + length2 + " = " + result);
        return result;
    }

    /**
     * Main method to demonstrate extended unit support.
     */
    public static void main(String[] args) {
        System.out.println("=== UC6: Addition of Two Length Units Demo ===\n");

        // Demo 1: Same unit addition - Feet + Feet
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(2.0, LengthUnit.FEET);
        demonstrateLengthAddition(feet1, feet2);
        // Expected: 3.0 FEET

        // Demo 2: Cross-unit addition - Feet + Inches (result in FEET)
        Length foot = new Length(1.0, LengthUnit.FEET);
        Length inches12 = new Length(12.0, LengthUnit.INCHES);
        demonstrateLengthAddition(foot, inches12);
        // Expected: 2.0 FEET

        // Demo 3: Cross-unit addition - Inches + Feet (result in INCHES)
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        demonstrateLengthAddition(inches, oneFoot);
        // Expected: 24.0 INCHES

        // Demo 4: Cross-unit addition - Yards + Feet (result in YARDS)
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length threeFeet = new Length(3.0, LengthUnit.FEET);
        demonstrateLengthAddition(yard, threeFeet);
        // Expected: 2.0 YARDS

        // Demo 5: Cross-unit addition - Inches + Yards (result in INCHES)
        Length inches36 = new Length(36.0, LengthUnit.INCHES);
        Length oneYard = new Length(1.0, LengthUnit.YARDS);
        demonstrateLengthAddition(inches36, oneYard);
        // Expected: 72.0 INCHES

        // Demo 6: Cross-unit addition - CM + Inches (result in CENTIMETERS)
        Length cm254 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length oneInch = new Length(1.0, LengthUnit.INCHES);
        demonstrateLengthAddition(cm254, oneInch);
        // Expected: ~5.08 CENTIMETERS

        // Demo 7: Identity element - Feet + 0 Inches
        Length fiveFoott = new Length(5.0, LengthUnit.FEET);
        Length zeroInches = new Length(0.0, LengthUnit.INCHES);
        demonstrateLengthAddition(fiveFoott, zeroInches);
        // Expected: 5.0 FEET

        // Demo 8: Negative value addition
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length negTwoFeet = new Length(-2.0, LengthUnit.FEET);
        demonstrateLengthAddition(fiveFeet, negTwoFeet);
        // Expected: 3.0 FEET
    }
}