package main;

/**
 * QuantityMeasurementApp - UC5: Unit-to-Unit Conversion
 *
 * This class encapsulates a length value along with its unit of measurement.
 * All conversions and comparisons use inches as the base unit.
 * Values converted to the base unit are rounded to two decimal places
 * for deterministic equality checks.
 *
 * Equality semantics:
 * Two Length instances are considered equal if their values,
 * when converted to the base unit (inches) and rounded to two decimal places,
 * are numerically identical.
 *
 * Supported units are declared in the nested LengthUnit enum with
 * conversion factors relative to inches (FEET, INCHES, YARDS, CENTIMETERS).
 *
 * Instance conversion method (added):
 * public Length convertTo(LengthUnit targetUnit)
 *
 * Conversion method behavior:
 * - Converts this instance to the base unit (inches).
 * - Converts from inches into targetUnit.
 * - Returns a new Length with the converted numeric value
 *   rounded to two decimal places.
 *
 * Thread-safety and mutability:
 * Instances are used as value objects. The conversion method returns new
 * instances rather than mutating the receiver, so callers can treat objects
 * as effectively immutable.
 *
 * @author Developer
 * @version 1.0
 */
public class QuantityMeasurementApp {

    /**
     * Nested enumeration representing different length units
     * and their conversion factors.
     *
     * The base unit for conversion is inches.
     * Thus, each unit's conversion factor is defined relative to inches.
     *
     * Each unit stores a conversion factor relative to inches (the base unit).
     * This design simplifies unit conversions by always converting
     * through a common base unit.
     *
     * Example:
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
         * @param conversionFactor conversion factor relative to inches (the base unit).
         */
        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        /**
         * Get the conversion factor for this unit.
         *
         * @return conversion factor to inches
         */
        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    /**
     * Generic Length class to represent any length measurement.
     *
     * This class encapsulates a length value along with its unit of measurement.
     * All conversions and comparisons use inches as the base unit.
     *
     * Supported units: FEET, INCHES, YARDS, CENTIMETERS
     *
     * @author Developer
     * @version 1.0
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
         * Private Utility Method:
         * This method is used internally for all conversions and comparisons.
         * It ensures consistent rounding to two decimal places across all operations.
         *
         * @return the length value in inches, rounded to two decimal places
         */
        private double convertToBaseUnit() {
            double baseValue = this.value * this.unit.getConversionFactor();
            return Math.round(baseValue * 100.0) / 100.0;
        }

        /**
         * Compare two Length objects for equality based on their values in the base unit.
         *
         * @param thatLength the Length object to compare with
         * @return true if both Length objects represent the same measurement, false otherwise
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
         * Overridden Method: Implements the Object equals(Object) contract.
         * Performs reference equality check first, then type validation,
         * and finally delegates to the compare(Length) method for value-based comparison.
         *
         * Algorithm:
         * 1. Check if both references point to the same object (early optimization)
         * 2. Validate that the other object is not null and is of type Length
         * 3. Cast to Length and invoke compare(Length) for value-based comparison
         *
         * @param obj the object to compare with this Length
         * @return true if both represent the same length in the base unit (inches),
         *         false otherwise
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

            // Cast and compare using the compare method
            Length other = (Length) obj;
            return this.compare(other);
        }

        /**
         * Convert this length to the specified target unit.
         *
         * Public API Method:
         * Provides the primary interface for unit conversion.
         * This method implements the conversion pipeline:
         * base unit conversion, target unit conversion,
         * and rounding to maintain precision consistency.
         *
         * Conversion Pipeline:
         * 1. Validate that targetUnit is not null
         *    (throws IllegalArgumentException if null)
         * 2. Convert this instance to the base unit (inches)
         *    using convertToBaseUnit()
         * 3. Convert from inches to targetUnit by dividing by
         *    the target unit's conversion factor
         * 4. Round the result to two decimal places
         * 5. Return a new Length instance with the converted value
         *
         * Immutability Guarantee:
         * This method never modifies the receiver; it always returns
         * a new Length instance, ensuring that the original object remains unchanged.
         *
         * @param targetUnit the unit to convert this length into; must not be null
         * @return a new Length representing the same physical length in targetUnit,
         *         with the numeric value rounded to two decimal places
         * @throws IllegalArgumentException if targetUnit is null
         */
        public Length convertTo(LengthUnit targetUnit) {
            // Validate targetUnit
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            // Step 1: Convert to base unit (inches)
            double baseValue = this.convertToBaseUnit();

            // Step 2: Convert from base unit to target unit
            double convertedValue = baseValue / targetUnit.getConversionFactor();

            // Step 3: Round to two decimal places
            convertedValue = Math.round(convertedValue * 100.0) / 100.0;

            // Step 4: Return new Length instance (immutability)
            return new Length(convertedValue, targetUnit);
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
         * Overridden Method:
         * Provides a human-readable format for logging and debugging.
         * The format is "{value} {unit}" where the value is formatted
         * to two decimal places.
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
        System.out.println("Are lengths equal? " + result);
        return result;
    }

    /**
     * Demonstrate length equality between two QuantityLength instances.
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
     * Method Overload 1:
     * Takes a numeric value and two units (from and to).
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
     * Demonstrate length conversion from one Length instance to another unit.
     *
     * Method Overload 2:
     * Takes an existing Length object and target unit.
     * Used when you already have a Length instance.
     *
     * Method Overloading means having multiple methods with the same name
     * but different parameter lists within the same class.
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
     * Main method to demonstrate extended unit support.
     */
    public static void main(String[] args) {
        System.out.println("=== UC5: Unit-to-Unit Conversion Demo ===\n");

        // Demo 1: Feet to Inches (Overload 1)
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

        // Demo 2: Yards to Feet (Overload 1)
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);

        // Demo 3: Inches to Yards (Overload 1)
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);

        // Demo 4: Centimeters to Inches (Overload 1)
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);

        // Demo 5: Zero value conversion (Overload 1)
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

        // Demo 6: Using overloaded method (Overload 2)
        Length lengthInYards = new Length(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);

        // Demo 7: Feet and Inches comparison
        System.out.println("\n=== Equality Checks ===");
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.YARDS, 3.0, LengthUnit.FEET);
    }
}