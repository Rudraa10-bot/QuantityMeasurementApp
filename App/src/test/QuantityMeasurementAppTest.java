package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.QuantityMeasurementApp.Feet;

/**
 * Test class for QuantityMeasurementApp - UC1: Feet measurement equality
 * Tests various equality scenarios for Feet objects
 */
public class QuantityMeasurementAppTest {

    /**
     * Test Case 1: testFeetEquality_SameValue
     * Verifies that two Feet objects with the same value (1.0 ft) are considered equal
     * Tests the value-based equality implementation
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
     * Verifies that two Feet objects with different values (1.0 ft and 2.0 ft) are not equal
     * Tests that different values result in inequality
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
     * Tests null safety and prevents NullPointerException
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
     * Tests type safety to prevent ClassCastException
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
     * Tests the reflexive property of equals() contract
     */
    @Test
    public void testFeetEquality_SameReference() {
        Feet feet1 = new Feet(1.0);

        assertTrue(feet1.equals(feet1),
                "Feet object should be equal to itself (reflexive property)");
    }

    /**
     * Test Case 6: testFeetEquality_Symmetric
     * Verifies the symmetric property: if a.equals(b) then b.equals(a)
     * Tests symmetric property of equals() contract
     */
    @Test
    public void testFeetEquality_Symmetric() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        assertTrue(feet1.equals(feet2) && feet2.equals(feet1),
                "Equality should be symmetric: if feet1 equals feet2, then feet2 equals feet1");
    }

    /**
     * Test Case 7: testFeetEquality_Transitive
     * Verifies the transitive property: if a.equals(b) and b.equals(c) then a.equals(c)
     * Tests transitive property of equals() contract
     */
    @Test
    public void testFeetEquality_Transitive() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        Feet feet3 = new Feet(1.0);

        assertTrue(feet1.equals(feet2) && feet2.equals(feet3) && feet1.equals(feet3),
                "Equality should be transitive: if feet1 equals feet2 and feet2 equals feet3, then feet1 equals feet3");
    }

    /**
     * Test Case 8: testFeetEquality_Consistent
     * Verifies the consistent property: multiple calls to equals() return the same result
     * Tests consistent property of equals() contract
     */
    @Test
    public void testFeetEquality_Consistent() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        boolean firstCall = feet1.equals(feet2);
        boolean secondCall = feet1.equals(feet2);
        boolean thirdCall = feet1.equals(feet2);

        assertTrue(firstCall && secondCall && thirdCall,
                "Multiple calls to equals() should return consistent results");
    }

    /**
     * Test Case 9: testFeetEquality_ZeroValues
     * Verifies that two Feet objects with zero values are equal
     * Tests edge case with zero values
     */
    @Test
    public void testFeetEquality_ZeroValues() {
        Feet feet1 = new Feet(0.0);
        Feet feet2 = new Feet(0.0);

        assertTrue(feet1.equals(feet2),
                "Two Feet objects with value 0.0 should be equal");
    }

    /**
     * Test Case 10: testFeetEquality_NegativeValues
     * Verifies that two Feet objects with the same negative values are equal
     * Tests edge case with negative values
     */
    @Test
    public void testFeetEquality_NegativeValues() {
        Feet feet1 = new Feet(-1.0);
        Feet feet2 = new Feet(-1.0);

        assertTrue(feet1.equals(feet2),
                "Two Feet objects with value -1.0 should be equal");
    }

    /**
     * Test Case 11: testFeetEquality_DecimalPrecision
     * Verifies correct comparison of decimal values
     * Tests floating-point precision handling
     */
    @Test
    public void testFeetEquality_DecimalPrecision() {
        Feet feet1 = new Feet(1.5);
        Feet feet2 = new Feet(1.5);

        assertTrue(feet1.equals(feet2),
                "Two Feet objects with value 1.5 should be equal");
    }
}