package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.QuantityMeasurementApp.Feet;
import main.QuantityMeasurementApp.Inches;

/**
 * Test class for QuantityMeasurementApp - UC2: Feet and Inches measurement equality
 * Tests various equality scenarios for both Feet and Inches objects
 */
public class QuantityMeasurementAppTest {

    // ==================== FEET EQUALITY TESTS ====================

    /**
     * Test Case 1: testFeetEquality_SameValue
     * Verifies that two Feet objects with the same value (1.0 ft) are considered equal
     */
    @Test
    public void testFeetEquality_SameValue() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        assertTrue(feet1.equals(feet2),
                "Two Feet objects with value 1.0 should be equal");
    }

    /**
     * Test Case 2: testFeetEquality_DifferentValue
     * Verifies that two Feet objects with different values are not equal
     */
    @Test
    public void testFeetEquality_DifferentValue() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);

        assertFalse(feet1.equals(feet2),
                "Feet objects with values 1.0 and 2.0 should not be equal");
    }

    /**
     * Test Case 3: testFeetEquality_NullComparison
     * Verifies that a Feet object is not equal to null
     */
    @Test
    public void testFeetEquality_NullComparison() {
        Feet feet1 = new Feet(1.0);

        assertFalse(feet1.equals(null),
                "Feet object should not be equal to null");
    }

    /**
     * Test Case 4: testFeetEquality_DifferentClass
     * Verifies that a Feet object is not equal to an object of a different type
     */
    @Test
    public void testFeetEquality_DifferentClass() {
        Feet feet1 = new Feet(1.0);
        String notAFeet = "1.0";

        assertFalse(feet1.equals(notAFeet),
                "Feet object should not be equal to a String object");
    }

    /**
     * Test Case 5: testFeetEquality_SameReference
     * Verifies that a Feet object is equal to itself (reflexive property)
     */
    @Test
    public void testFeetEquality_SameReference() {
        Feet feet1 = new Feet(1.0);

        assertTrue(feet1.equals(feet1),
                "Feet object should be equal to itself (reflexive property)");
    }

    /**
     * Test Case 6: testFeetEquality_Symmetric
     * Verifies the symmetric property of equals()
     */
    @Test
    public void testFeetEquality_Symmetric() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        assertTrue(feet1.equals(feet2) && feet2.equals(feet1),
                "Equality should be symmetric for Feet objects");
    }

    /**
     * Test Case 7: testFeetEquality_ZeroValues
     * Verifies equality for zero values
     */
    @Test
    public void testFeetEquality_ZeroValues() {
        Feet feet1 = new Feet(0.0);
        Feet feet2 = new Feet(0.0);

        assertTrue(feet1.equals(feet2),
                "Two Feet objects with value 0.0 should be equal");
    }

    // ==================== INCHES EQUALITY TESTS ====================

    /**
     * Test Case 8: testInchesEquality_SameValue
     * Verifies that two Inches objects with the same value (1.0 inch) are considered equal
     */
    @Test
    public void testInchesEquality_SameValue() {
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);

        assertTrue(inches1.equals(inches2),
                "Two Inches objects with value 1.0 should be equal");
    }

    /**
     * Test Case 9: testInchesEquality_DifferentValue
     * Verifies that two Inches objects with different values are not equal
     */
    @Test
    public void testInchesEquality_DifferentValue() {
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(2.0);

        assertFalse(inches1.equals(inches2),
                "Inches objects with values 1.0 and 2.0 should not be equal");
    }

    /**
     * Test Case 10: testInchesEquality_NullComparison
     * Verifies that an Inches object is not equal to null
     */
    @Test
    public void testInchesEquality_NullComparison() {
        Inches inches1 = new Inches(1.0);

        assertFalse(inches1.equals(null),
                "Inches object should not be equal to null");
    }

    /**
     * Test Case 11: testInchesEquality_DifferentClass
     * Verifies that an Inches object is not equal to an object of a different type
     */
    @Test
    public void testInchesEquality_DifferentClass() {
        Inches inches1 = new Inches(1.0);
        String notAnInches = "1.0";

        assertFalse(inches1.equals(notAnInches),
                "Inches object should not be equal to a String object");
    }

    /**
     * Test Case 12: testInchesEquality_SameReference
     * Verifies that an Inches object is equal to itself (reflexive property)
     */
    @Test
    public void testInchesEquality_SameReference() {
        Inches inches1 = new Inches(1.0);

        assertTrue(inches1.equals(inches1),
                "Inches object should be equal to itself (reflexive property)");
    }

    /**
     * Test Case 13: testInchesEquality_Symmetric
     * Verifies the symmetric property of equals()
     */
    @Test
    public void testInchesEquality_Symmetric() {
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);

        assertTrue(inches1.equals(inches2) && inches2.equals(inches1),
                "Equality should be symmetric for Inches objects");
    }

    /**
     * Test Case 14: testInchesEquality_Transitive
     * Verifies the transitive property of equals()
     */
    @Test
    public void testInchesEquality_Transitive() {
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);
        Inches inches3 = new Inches(1.0);

        assertTrue(inches1.equals(inches2) && inches2.equals(inches3) && inches1.equals(inches3),
                "Equality should be transitive for Inches objects");
    }

    /**
     * Test Case 15: testInchesEquality_ZeroValues
     * Verifies equality for zero values
     */
    @Test
    public void testInchesEquality_ZeroValues() {
        Inches inches1 = new Inches(0.0);
        Inches inches2 = new Inches(0.0);

        assertTrue(inches1.equals(inches2),
                "Two Inches objects with value 0.0 should be equal");
    }

    /**
     * Test Case 16: testInchesEquality_NegativeValues
     * Verifies equality for negative values
     */
    @Test
    public void testInchesEquality_NegativeValues() {
        Inches inches1 = new Inches(-1.0);
        Inches inches2 = new Inches(-1.0);

        assertTrue(inches1.equals(inches2),
                "Two Inches objects with value -1.0 should be equal");
    }

    /**
     * Test Case 17: testInchesEquality_DecimalPrecision
     * Verifies correct comparison of decimal values
     */
    @Test
    public void testInchesEquality_DecimalPrecision() {
        Inches inches1 = new Inches(1.5);
        Inches inches2 = new Inches(1.5);

        assertTrue(inches1.equals(inches2),
                "Two Inches objects with value 1.5 should be equal");
    }

    // ==================== CROSS-TYPE COMPARISON TESTS ====================

    /**
     * Test Case 18: testFeetNotEqualToInches
     * Verifies that Feet and Inches objects are not equal (different types)
     * This ensures type safety between different measurement units
     */
    @Test
    public void testFeetNotEqualToInches() {
        Feet feet1 = new Feet(1.0);
        Inches inches1 = new Inches(1.0);

        assertFalse(feet1.equals(inches1),
                "Feet object should not be equal to Inches object (different types)");
    }

    /**
     * Test Case 19: testInchesNotEqualToFeet
     * Verifies that Inches and Feet objects are not equal (different types)
     * Symmetric test of the above
     */
    @Test
    public void testInchesNotEqualToFeet() {
        Inches inches1 = new Inches(1.0);
        Feet feet1 = new Feet(1.0);

        assertFalse(inches1.equals(feet1),
                "Inches object should not be equal to Feet object (different types)");
    }

    /**
     * Test Case 20: testFeetEquality_LargeValues
     * Verifies equality for large values
     */
    @Test
    public void testFeetEquality_LargeValues() {
        Feet feet1 = new Feet(1000000.0);
        Feet feet2 = new Feet(1000000.0);

        assertTrue(feet1.equals(feet2),
                "Two Feet objects with large values should be equal");
    }
}