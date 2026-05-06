package main;

/**
 * QuantityMeasurementApp - UC7: Addition with Target Unit Specification
 *
 * This class extends UC6 by providing flexibility in specifying the unit
 * for the addition result. Instead of defaulting to the unit of the first
 * operand, the caller can explicitly specify any supported unit as the
 * target unit for the result.
 *
 * Adding 1 foot and 12 inches yields 2 feet (or 24 inches, depending on target unit)
 * Measurements must belong to the same category (length) but can have different units
 * Result is returned in the specified target unit
 *
 * @author Developer
 * @version 1.0
 * @since UC7
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
     * UC6: add(Length) returns result in unit of first operand
     * UC7: add(Length, LengthUnit) returns result in specified target unit
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
         * Private helper method to avoid code duplication in the conversion
         * process as both the convertTo and add methods require this functionality.
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
         * Overridden Method implementing the Object equals(Object) contract.
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
         * Conversion Pipeline:
         * 1. Validate that targetUnit is not null
         * 2. Convert this instance to the base unit (inches)
         * 3. Convert from inches to targetUnit
         * 4. Return a new Length instance
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

            double baseValue = this.convertToBaseUnit();
            double convertedValue = convertFromBaseToTargetUnit(baseValue, targetUnit);
            return new Length(convertedValue, targetUnit);
        }

        /**
         * UC6 Method: Adds another Length to this Length.
         *
         * The result is returned in the unit of this (the first) operand.
         * Delegates to the addAndConvert private method.
         *
         * @param thatLength the Length to add; must not be null
         * @return a new Length representing the sum in this instance's unit
         * @throws IllegalArgumentException if thatLength is null
         */
        public Length add(Length thatLength) {
            if (thatLength == null) {
                throw new IllegalArgumentException("Cannot add null Length");
            }
            // UC6: result in unit of first operand (this.unit)
            return addAndConvert(thatLength, this.unit);
        }

        /**
         * UC7 Method: Adds another Length to this Length with explicit target unit.
         *
         * Adding length to this length with target unit specification.
         *
         * Public API Method: This method allows adding two lengths specified
         * by their numeric values and units. The result is returned as per the unit of
         * specified target unit.
         *
         * This overloaded method maintains backward compatibility with UC6's add()
         * while adding explicit target unit support. The caller has explicit control
         * over the result unit rather than relying on implicit defaults.
         *
         * @param thatLength the Length to add; must not be null
         * @param targetUnit the unit to return the sum in; must not be null
         * @return a new Length representing the sum in the specified target unit
         * @throws IllegalArgumentException if thatLength or targetUnit is null
         */
        public Length add(Length thatLength, LengthUnit targetUnit) {
            if (thatLength == null) {
                throw new IllegalArgumentException("Cannot add null Length");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            // UC7: result in explicitly specified targetUnit
            return addAndConvert(thatLength, targetUnit);
        }

        /**
         * Private utility method to perform addition conversion on base unit value.
         *
         * Private Utility Method: This method is used internally by the
         * add(Length) and add(Length, LengthUnit) methods to convert this length
         * and the length to add into the base unit, sum them up, and convert the
         * result into the specified target unit.
         *
         * This method avoids code duplication in the addition process as both add
         * methods require this functionality and ensures consistent rounding to
         * two decimal places across all operations.
         *
         * Also maintains immutability by returning a new Length instance without
         * modifying the original objects.
         *
         * @param length     the Length to add
         * @param targetUnit the unit to return the sum in
         * @return a new Length representing the sum in the specified target unit
         */
        private Length addAndConvert(Length length, LengthUnit targetUnit) {
            // Step 1: Convert both lengths to base unit (inches)
            double thisInBase = this.convertToBaseUnit();
            double thatInBase = length.convertToBaseUnit();

            // Step 2: Sum the base unit values
            double sumInBase = thisInBase + thatInBase;

            // Step 3: Convert sum to the specified target unit
            double sumInTargetUnit = convertFromBaseToTargetUnit(sumInBase, targetUnit);

            // Step 4: Return new immutable Length instance
            return new Length(sumInTargetUnit, targetUnit);
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
     * Demonstrate length equality between two raw values.
     *
     * @param value1 first value
     * @param unit1  first unit
     * @param value2 second value
     * @param unit2  second unit
     * @return true if equal, false otherwise
     */
    public static boolean demonstrateLengthComparison(
            double value1, LengthUnit unit1,
            double value2, LengthUnit unit2) {
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);
        return demonstrateLengthEquality(l1, l2);
    }

    /**
     * Demonstrate length conversion. Method Overload 1: raw value with two units.
     *
     * @param value    value to convert
     * @param fromUnit source unit
     * @param toUnit   target unit
     * @return converted Length
     */
    public static Length demonstrateLengthConversion(
            double value, LengthUnit fromUnit, LengthUnit toUnit) {
        Length length = new Length(value, fromUnit);
        Length converted = length.convertTo(toUnit);
        System.out.println(value + " " + fromUnit + " = " + converted);
        return converted;
    }

    /**
     * Demonstrate length conversion. Method Overload 2: existing Length to another unit.
     *
     * @param length existing Length
     * @param toUnit target unit
     * @return converted Length
     */
    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        Length converted = length.convertTo(toUnit);
        System.out.println(length + " = " + converted);
        return converted;
    }

    /**
     * UC6: Demonstrate addition of two lengths. Result in unit of first operand.
     *
     * @param length1 the first Length instance
     * @param length2 the second Length instance
     * @return sum in unit of first operand
     */
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        Length result = length1.add(length2);
        System.out.println(length1 + " + " + length2 + " = " + result);
        return result;
    }

    /**
     * UC7: Demonstrate addition of two lengths with explicit target unit.
     *
     * Demonstrate addition of second QuantityLength to first QuantityLength
     * with target unit.
     *
     * @param length1    the first QuantityLength instance
     * @param length2    the second QuantityLength instance
     * @param targetUnit the target unit for the result
     * @return a new Length instance representing the sum in the target unit
     */
    public static Length demonstrateLengthAddition(
            Length length1, Length length2, LengthUnit targetUnit) {
        Length result = length1.add(length2, targetUnit);
        System.out.println(length1 + " + " + length2
                + " in " + targetUnit + " = " + result);
        return result;
    }

    /**
     * Main method to demonstrate extended unit support.
     */
    public static void main(String[] args) {
        System.out.println("=== UC7: Addition with Target Unit Specification Demo ===\n");

        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        Length twelveInches = new Length(12.0, LengthUnit.INCHES);
        Length oneYard = new Length(1.0, LengthUnit.YARDS);
        Length threeFeet = new Length(3.0, LengthUnit.FEET);
        Length inches36 = new Length(36.0, LengthUnit.INCHES);
        Length cm254 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length oneInch = new Length(1.0, LengthUnit.INCHES);
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length negTwoFeet = new Length(-2.0, LengthUnit.FEET);

        // Demo 1: target = FEET
        demonstrateLengthAddition(oneFoot, twelveInches, LengthUnit.FEET);
        // Output: 2.0 FEET

        // Demo 2: target = INCHES
        demonstrateLengthAddition(oneFoot, twelveInches, LengthUnit.INCHES);
        // Output: 24.0 INCHES

        // Demo 3: target = YARDS (different from both operands)
        demonstrateLengthAddition(oneFoot, twelveInches, LengthUnit.YARDS);
        // Output: ~0.67 YARDS

        // Demo 4: 1 YARD + 3 FEET in YARDS
        demonstrateLengthAddition(oneYard, threeFeet, LengthUnit.YARDS);
        // Output: 2.0 YARDS

        // Demo 5: 36 INCHES + 1 YARD in FEET
        demonstrateLengthAddition(inches36, oneYard, LengthUnit.FEET);
        // Output: 6.0 FEET

        // Demo 6: 2.54 CM + 1 INCH in CENTIMETERS
        demonstrateLengthAddition(cm254, oneInch, LengthUnit.CENTIMETERS);
        // Output: ~5.08 CENTIMETERS

        // Demo 7: 5 FEET + 0 INCHES in YARDS
        demonstrateLengthAddition(fiveFeet,
                new Length(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        // Output: ~1.67 YARDS

        // Demo 8: 5 FEET + (-2 FEET) in INCHES
        demonstrateLengthAddition(fiveFeet, negTwoFeet, LengthUnit.INCHES);
        // Output: 36.0 INCHES
    }
}