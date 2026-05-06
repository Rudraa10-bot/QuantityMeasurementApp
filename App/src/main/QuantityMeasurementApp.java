package main;

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
            // Returns 0 if values are equal, avoiding issues with == operator for doubles
            return Double.compare(this.value, other.value) == 0;
        }

        /**
         * Override hashCode() to maintain the equals-hashCode contract
         * Objects that are equal must have the same hash code
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
     * Main method to demonstrate Feet equality check
     */
    public static void main(String[] args) {
        // Test case 1: Equal values
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println("1.0 ft equals 1.0 ft: " + feet1.equals(feet2)); // Expected: true

        // Test case 2: Different values
        Feet feet3 = new Feet(2.0);
        System.out.println("1.0 ft equals 2.0 ft: " + feet1.equals(feet3)); // Expected: false

        // Test case 3: Same reference
        System.out.println("feet1 equals itself: " + feet1.equals(feet1)); // Expected: true

        // Test case 4: Null comparison
        System.out.println("feet1 equals null: " + feet1.equals(null)); // Expected: false

        // Test case 5: Different type comparison
        String notAFeet = "1.0";
        System.out.println("feet1 equals String: " + feet1.equals(notAFeet)); // Expected: false
    }
}