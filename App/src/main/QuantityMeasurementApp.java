package main;

/**
 * QuantityMeasurementApp - UC2: Inches measurement equality
 *
 * This class is responsible for checking the equality of two numerical values
 * measured in inches in the Quantity Measurement Application.
 * Extends UC1 to accommodate both Feet and Inches measurements separately.
 */
public class QuantityMeasurementApp {

    /**
     * Inner class to represent Feet measurement
     * Uses encapsulation and immutability principles
     */
    public static class Feet {
        private final double value;

        /**
         * Constructor to initialize the feet value
         * @param value the measurement value in feet
         */
        public Feet(double value) {
            this.value = value;
        }

        /**
         * Override equals() method to compare two Feet objects based on their value
         *
         * <p>Important Checks:</p>
         * 1. Reference Check: If both references point to the same object, return true
         * 2. Null Check: If the compared object is null, return false
         * 3. Type Check: If the compared object is not of type Feet, return false
         * 4. Value Comparison: Use Double.compare() to compare the double values for equality
         *
         * @param obj The object to compare with
         * @return true if both Feet objects have the same value, false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            // Reference check: if both point to same object
            if (this == obj) {
                return true;
            }

            // Null check: if compared object is null
            if (obj == null) {
                return false;
            }

            // Type check: if compared object is not of type Feet
            if (getClass() != obj.getClass()) {
                return false;
            }

            // Cast to Feet type safely
            Feet other = (Feet) obj;

            // Value comparison using Double.compare() for precise comparison
            return Double.compare(this.value, other.value) == 0;
        }

        /**
         * Override hashCode() to maintain the equals-hashCode contract
         */
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }

        /**
         * Override toString() for better representation
         */
        @Override
        public String toString() {
            return value + " ft";
        }
    }

    /**
     * Inner class to represent Inches measurement
     * Uses encapsulation and immutability principles
     * Similar structure to Feet class for consistency
     */
    public static class Inches {
        private final double value;

        /**
         * Constructor to initialize the inches value
         * @param value the measurement value in inches
         */
        public Inches(double value) {
            this.value = value;
        }

        /**
         * Override equals() method to compare two Inches objects based on their value
         *
         * <p>Important Checks:</p>
         * 1. Reference Check: If both references point to the same object, return true
         * 2. Null Check: If the compared object is null, return false
         * 3. Type Check: If the compared object is not of type Inches, return false
         * 4. Value Comparison: Use Double.compare() to compare the double values for equality
         *
         * @param obj The object to compare with
         * @return true if both Inches objects have the same value, false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            // Reference check: if both point to same object
            if (this == obj) {
                return true;
            }

            // Null check: if compared object is null
            if (obj == null) {
                return false;
            }

            // Type check: if compared object is not of type Inches
            if (getClass() != obj.getClass()) {
                return false;
            }

            // Cast to Inches type safely
            Inches other = (Inches) obj;

            // Value comparison using Double.compare() for precise comparison
            return Double.compare(this.value, other.value) == 0;
        }

        /**
         * Override hashCode() to maintain the equals-hashCode contract
         */
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }

        /**
         * Override toString() for better representation
         */
        @Override
        public String toString() {
            return value + " inch";
        }
    }

    /**
     * Define a static method to demonstrate Feet equality check
     * Reduces dependency on main method
     */
    public static void demonstrateFeetEquality() {
        System.out.println("=== Feet Equality Demonstration ===");

        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        Feet feet3 = new Feet(2.0);

        System.out.println("1.0 ft equals 1.0 ft: " + feet1.equals(feet2)); // true
        System.out.println("1.0 ft equals 2.0 ft: " + feet1.equals(feet3)); // false
        System.out.println("feet1 equals itself: " + feet1.equals(feet1)); // true
        System.out.println("feet1 equals null: " + feet1.equals(null)); // false
        System.out.println();
    }

    /**
     * Defining a static method to demonstrate Inches equality check
     * Reduces dependency on main method
     */
    public static void demonstrateInchesEquality() {
        System.out.println("=== Inches Equality Demonstration ===");

        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);
        Inches inches3 = new Inches(2.0);

        System.out.println("1.0 inch equals 1.0 inch: " + inches1.equals(inches2)); // true
        System.out.println("1.0 inch equals 2.0 inch: " + inches1.equals(inches3)); // false
        System.out.println("inches1 equals itself: " + inches1.equals(inches1)); // true
        System.out.println("inches1 equals null: " + inches1.equals(null)); // false
        System.out.println();
    }

    /**
     * Main method to demonstrate Inches equality check
     * Calls separate static methods for Feet and Inches equality checks
     */
    public static void main(String[] args) {
        // Demonstrate Feet equality
        demonstrateFeetEquality();

        // Demonstrate Inches equality
        demonstrateInchesEquality();
    }
}