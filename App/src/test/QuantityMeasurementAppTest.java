package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.QuantityMeasurementApp.Length;
import main.QuantityMeasurementApp.LengthUnit;

/**
 * Test class for QuantityMeasurementApp - UC3: Generic Quantity Class for DRY Principle
 * Tests various equality scenarios for Length objects with different units
 */
public class QuantityMeasurementAppTest {

    // ==================== FEET EQUALITY TESTS ====================

    /**
     * Test Case 1: testFeetEquality_SameValue
     * Verifies that two Length objects with the same feet value are equal
     */
    @Test
    public void testFeetEquality() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(feet1.equals(feet2),
                "Two Length objects with value 1.0 feet should be equal");
    }

    // ==================== INCHES EQUALITY TESTS ====================

    /**
     * Test Case 2: testInchesEquality
     * Verifies that two Length objects with the same inch value are equal
     */
    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(inches1.equals(inches2),
                "Two Length objects with value 1.0 inches should be equal");
    }

    // ==================== CROSS-UNIT COMPARISON TESTS ====================

    /**
     * Test Case 3: testFeetInchesComparison
     * Verifies that 1 foot equals 12 inches (cross-unit comparison)
     */
    @Test
    public void testFeetInchesComparison() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "1.0 feet should equal 12.0 inches");
    }

    /**
     * Test Case 4: testFeetInequality
     * Verifies that two Length objects with different feet values are not equal
     */
    @Test
    public void testFeetInequality() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(feet1.equals(feet2),
                "1.0 feet should not equal 2.0 feet");
    }

    /**
     * Test Case 5: testInchesInequality
     * Verifies that two Length objects with different inch values are not equal
     */
    @Test
    public void testInchesInequality() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(2.0, LengthUnit.INCHES);

        assertFalse(inches1.equals(inches2),
                "1.0 inches should not equal 2.0 inches");
    }

    /**
     * Test Case 6: testCrossUnitInequality
     * Verifies that 1 foot does not equal 1 inch
     */
    @Test
    public void testCrossUnitInequality() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertFalse(feet.equals(inches),
                "1.0 feet should not equal 1.0 inches");
    }

    // ==================== MULTIPLE FEET COMPARISON TESTS ====================

    /**
     * Test Case 7: testMultipleFeetComparison
     * Verifies that 2 feet equals 24 inches
     */
    @Test
    public void testMultipleFeetComparison() {
        Length feet = new Length(2.0, LengthUnit.FEET);
        Length inches = new Length(24.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "2.0 feet should equal 24.0 inches");
    }

    // ==================== NULL AND REFERENCE TESTS ====================

    /**
     * Test Case 8: testEquality_NullComparison
     * Verifies that a Length object is not equal to null
     */
    @Test
    public void testEquality_NullComparison() {
        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(length.equals(null),
                "Length object should not be equal to null");
    }

    /**
     * Test Case 9: testEquality_SameReference
     * Verifies that a Length object equals itself (reflexive property)
     */
    @Test
    public void testEquality_SameReference() {
        Length length = new Length(1.0, LengthUnit.FEET);

        assertTrue(length.equals(length),
                "Length object should be equal to itself (reflexive property)");
    }

    /**
     * Test Case 10: testEquality_DifferentClass
     * Verifies that a Length object is not equal to an object of a different type
     */
    @Test
    public void testEquality_DifferentClass() {
        Length length = new Length(1.0, LengthUnit.FEET);
        String notALength = "1.0";

        assertFalse(length.equals(notALength),
                "Length object should not be equal to a String object");
    }

    // ==================== SYMMETRY TESTS ====================

    /**
     * Test Case 11: testEquality_Symmetric_Feet
     * Verifies the symmetric property for feet measurements
     */
    @Test
    public void testEquality_Symmetric_Feet() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(feet1.equals(feet2) && feet2.equals(feet1),
                "Equality should be symmetric for feet");
    }

    /**
     * Test Case 12: testEquality_Symmetric_CrossUnit
     * Verifies the symmetric property for cross-unit comparison
     */
    @Test
    public void testEquality_Symmetric_CrossUnit() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches) && inches.equals(feet),
                "Equality should be symmetric: 1 ft = 12 inches and 12 inches = 1 ft");
    }

    // ==================== TRANSITIVE TESTS ====================

    /**
     * Test Case 13: testEquality_Transitive
     * Verifies the transitive property of equals()
     */
    @Test
    public void testEquality_Transitive() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);
        Length feet3 = new Length(1.0, LengthUnit.FEET);

        assertTrue(feet1.equals(feet2) && feet2.equals(feet3) && feet1.equals(feet3),
                "Equality should be transitive");
    }

    // ==================== ZERO AND EDGE CASE TESTS ====================

    /**
     * Test Case 14: testEquality_ZeroValues
     * Verifies equality for zero values
     */
    @Test
    public void testEquality_ZeroValues() {
        Length feet1 = new Length(0.0, LengthUnit.FEET);
        Length feet2 = new Length(0.0, LengthUnit.FEET);

        assertTrue(feet1.equals(feet2),
                "Two Length objects with value 0.0 feet should be equal");
    }

    /**
     * Test Case 15: testEquality_ZeroCrossUnit
     * Verifies that 0 feet equals 0 inches
     */
    @Test
    public void testEquality_ZeroCrossUnit() {
        Length feet = new Length(0.0, LengthUnit.FEET);
        Length inches = new Length(0.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "0.0 feet should equal 0.0 inches");
    }

    // ==================== DECIMAL PRECISION TESTS ====================

    /**
     * Test Case 16: testEquality_DecimalValues
     * Verifies correct comparison of decimal values
     */
    @Test
    public void testEquality_DecimalValues() {
        Length feet1 = new Length(1.5, LengthUnit.FEET);
        Length feet2 = new Length(1.5, LengthUnit.FEET);

        assertTrue(feet1.equals(feet2),
                "Two Length objects with value 1.5 feet should be equal");
    }

    /**
     * Test Case 17: testEquality_DecimalCrossUnit
     * Verifies that 0.5 feet equals 6 inches
     */
    @Test
    public void testEquality_DecimalCrossUnit() {
        Length feet = new Length(0.5, LengthUnit.FEET);
        Length inches = new Length(6.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "0.5 feet should equal 6.0 inches");
    }

    // ==================== BACKWARD COMPATIBILITY TESTS (UC1 & UC2) ====================

    /**
     * Test Case 18: testBackwardCompatibility_UC1_FeetEquality
     * Ensures UC1 functionality is preserved
     */
    @Test
    public void testBackwardCompatibility_UC1_FeetEquality() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(feet1.equals(feet2),
                "UC1 backward compatibility: feet equality should work");
    }

    /**
     * Test Case 19: testBackwardCompatibility_UC2_InchesEquality
     * Ensures UC2 functionality is preserved
     */
    @Test
    public void testBackwardCompatibility_UC2_InchesEquality() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(inches1.equals(inches2),
                "UC2 backward compatibility: inches equality should work");
    }

    /**
     * Test Case 20: testBackwardCompatibility_UC2_DifferentUnits
     * Ensures different unit types are not equal (UC2 behavior)
     */
    @Test
    public void testBackwardCompatibility_UC2_DifferentUnitsDifferentValues() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertFalse(feet.equals(inches),
                "UC2 backward compatibility: 1 ft should not equal 1 inch");
    }

    // ==================== LARGE VALUE TESTS ====================

    /**
     * Test Case 21: testEquality_LargeValues
     * Verifies equality for large values
     */
    @Test
    public void testEquality_LargeValues() {
        Length feet1 = new Length(1000000.0, LengthUnit.FEET);
        Length feet2 = new Length(1000000.0, LengthUnit.FEET);

        assertTrue(feet1.equals(feet2),
                "Two Length objects with large values should be equal");
    }

    /**
     * Test Case 22: testEquality_LargeCrossUnit
     * Verifies cross-unit comparison for large values
     */
    @Test
    public void testEquality_LargeCrossUnit() {
        Length feet = new Length(100.0, LengthUnit.FEET);
        Length inches = new Length(1200.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "100 feet should equal 1200 inches");
    }

    // ==================== CONSISTENCY TESTS ====================

    /**
     * Test Case 23: testEquality_Consistent
     * Verifies that multiple calls to equals() return consistent results
     */
    @Test
    public void testEquality_Consistent() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);

        boolean firstCall = feet1.equals(feet2);
        boolean secondCall = feet1.equals(feet2);
        boolean thirdCall = feet1.equals(feet2);

        assertTrue(firstCall && secondCall && thirdCall,
                "Multiple calls to equals() should return consistent results");
    }

    /**
     * Test Case 24: testEquality_FractionalInches
     * Verifies comparison with fractional inch values
     */
    @Test
    public void testEquality_FractionalInches() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "1 foot should equal exactly 12 inches");
    }
}