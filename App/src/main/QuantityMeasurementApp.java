package main;

/**
 * QuantityMeasurementApp - UC3: Generic Quantity Class for DRY Principle
 *
 * This class refactors UC1 and UC2 to eliminate code duplication by introducing
 * a generic Quantity class with a LengthUnit enum. This approach follows the DRY
 * (Don't Repeat Yourself) principle and makes the codebase more scalable.
 *
 * @author Development Team
 * @version 3.0
 */
public class QuantityMeasurementApp {

    /**
     * Enum to represent different length units and their conversion factors
     * with the base unit being inches. This means all the conversion factors
     * are defined in terms of inches.
     */
    public enum LengthUnit {
        FEET(12.0),     // 1 foot = 12 inches
        INCHES(1.0);    // 1 inch = 1 inch (base unit)

        private final double conversionFactor;

        /**
         * Constructor for LengthUnit enum
         * @param conversionFactor conversion factor to base unit (inches)
         */
        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        /**
         * Get the conversion factor for this unit
         * @return conversion factor to inches
         */
        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    /**
     * Generic Length class to represent any length measurement
     * This class eliminates code duplication from UC1 and UC2
     */
    public static class Length {
        private final double value;
        private final LengthUnit unit;

        /**
         * Constructor to initialize length value and unit
         * @param value the measurement value
         * @param unit the unit of measurement
         */
        public Length(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        /**
         * Convert the length value to the base unit (inches)
         * This method is used internally for comparison purposes
         * @return the value converted to inches
         */
        private double convertToBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        /**
         * Compare two Length objects for equality based on their values in the base unit
         * This method handles cross-unit comparisons (e.g., 1 Foot == 12 Inches)
         *
         * @param thatLength the Length object to compare with
         * @return true if both Length objects represent the same measurement, false otherwise
         */
        public boolean compare(Length thatLength) {
            if (thatLength == null) {
                return false;
            }

            // Convert both values to base unit and compare
            double thisBaseValue = this.convertToBaseUnit();
            double thatBaseValue = thatLength.convertToBaseUnit();

            return Double.compare(thisBaseValue, thatBaseValue) == 0;
        }

        /**
         * Equals method is overridden to firstly check if the two objects are the same reference.
         * If not, it checks if the other object is null or of a different class.
         * Finally, it calls the compare method to determine equality based on converted values.
         *
         * @Override
         */
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
         * Override hashCode() to maintain the equals-hashCode contract
         */
        @Override
        public int hashCode() {
            // Use the base unit value for consistent hashing
            return Double.hashCode(convertToBaseUnit());
        }

        /**
         * Override toString() for better representation
         */
        @Override
        public String toString() {
            return value + " " + unit.toString().toLowerCase();
        }
    }

    /**
     * Create a generic method to demonstrate Length equality check
     * @param length1 first Length object
     * @param length2 second Length object
     * @return true if equal, false otherwise
     */
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    /**
     * Create a static method to demonstrate Feet equality check
     */
    public static void demonstrateFeetEquality() {
        System.out.println("=== Feet Equality Demonstration ===");

        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);

        System.out.println("1.0 ft equals 1.0 ft: " + feet1.equals(feet2)); // true
        System.out.println();
    }

    /**
     * Create a static method to demonstrate Inches equality check
     */
    public static void demonstrateInchesEquality() {
        System.out.println("=== Inches Equality Demonstration ===");

        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);

        System.out.println("1.0 inch equals 1.0 inch: " + inches1.equals(inches2)); // true
        System.out.println();
    }

    /**
     * Create a static method to demonstrate Feet and Inches comparison
     */
    public static void demonstrateFeetInchesComparison() {
        System.out.println("=== Feet and Inches Comparison ===");

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("1.0 ft equals 12.0 inches: " + length1.equals(length2)); // true
        System.out.println("Are lengths equal? " + length1.equals(length2)); // Should print true
        System.out.println();
    }

    /**
     * Main method to demonstrate Feet and Inches equality checks
     * and comparison checks between Feet and Inches
     */
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}