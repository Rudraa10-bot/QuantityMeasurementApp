package main;

/**
 * QuantityMeasurementApp - UC4: Extended Unit Support
 *
 * This class extends UC3 by introducing Yards and Centimeters as additional length units.
 * Demonstrates how the generic Quantity class design scales effortlessly to accommodate
 * new units without code duplication.
 *
 * Supported Units:
 * - Feet (ft)
 * - Inches (in)
 * - Yards (yd)
 * - Centimeters (cm)
 *
 * @author Development Team
 * @version 4.0
 */
public class QuantityMeasurementApp {

    /**
     * Enum to represent different length units and their conversion factors
     * with the base unit being inches. This means all the conversion factors
     * are defined in terms of inches.
     */
    public enum LengthUnit {
        FEET(12.0),           // 1 foot = 12 inches
        INCHES(1.0),          // 1 inch = 1 inch (base unit)
        YARDS(36.0),          // 1 yard = 36 inches (1 yard = 3 feet = 36 inches)
        CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

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
     * This class eliminates code duplication from UC1, UC2, and UC3
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
         * Convert the length value to the base unit (inches) and round off to two decimal places
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
     * Create a static method to taxiin method parameters and demonstrate
     * equality check
     */
    public static boolean demonstrateLengthComparison(Length length1, Length length2) {
        return length1.compare(length2);
    }

    /**
     * Create a static method to demonstrate Feet and Inches comparison
     */
    public static void demonstrateFeetInchesComparison() {
        System.out.println("=== Feet and Inches Comparison ===");
        // Demonstrate Feet and Inches comparison
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2)); // Should print true
        System.out.println();
    }

    /**
     * Create a static method to demonstrate Yards and Inches comparison
     */
    public static void demonstrateYardsInchesComparison() {
        System.out.println("=== Yards and Inches Comparison ===");
        Length length3 = new Length(1.0, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length3.equals(length4)); // Should print true
        System.out.println();
    }

    /**
     * Create a static method to demonstrate Centimeters and Inches comparison
     */
    public static void demonstrateCentimetersInchesComparison() {
        System.out.println("=== Centimeters and Inches Comparison ===");
        Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length5.equals(length6)); // Should print true
        System.out.println();
    }

    /**
     * Create a static method to demonstrate Feet and Yards comparison
     */
    public static void demonstrateFeetYardsComparison() {
        System.out.println("=== Feet and Yards Comparison ===");
        Length length1 = new Length(3.0, LengthUnit.FEET);
        Length length2 = new Length(1.0, LengthUnit.YARDS);
        System.out.println("Are lengths equal? " + length1.equals(length2)); // Should print true
        System.out.println();
    }

    /**
     * Create a static method to demonstrate Centimeters and Feet comparison
     */
    public static void demonstrateCentimetersFeetComparison() {
        System.out.println("=== Centimeters and Feet Comparison ===");
        Length length1 = new Length(30.48, LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, LengthUnit.FEET);
        System.out.println("Are lengths equal? " + length1.equals(length2)); // Should print true
        System.out.println();
    }

    /**
     * Main method for standalone testing
     * Run | Debug
     */
    public static void main(String[] args) {
        demonstrateFeetInchesComparison();
        demonstrateYardsInchesComparison();
        demonstrateCentimetersInchesComparison();
        demonstrateFeetYardsComparison();
        demonstrateCentimetersFeetComparison();
    }
}