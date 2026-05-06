package test;

/**
 * UC6: Addition of Two Length Units
 *
 * This test class validates the addition operations performed on different
 * length measurements in the Quantity Measurement Application.
 * It ensures that lengths can be added together and produce correct results
 * regardless of their units of measurement.
 *
 * Test Coverage:
 * - Addition of lengths in the same unit (e.g., Feet + Feet)
 * - Addition of lengths in different units (e.g., Feet + Inches)
 * - Addition of lengths with unit conversion and result validation
 * - Edge cases and boundary conditions for length addition
 *
 * @author Developer
 * @version 6.0
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.QuantityMeasurementApp;
import main.QuantityMeasurementApp.Length;
import main.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    // Tolerance for floating-point comparisons
    private static final double EPSILON = 0.01;

    // ==================== BACKWARD COMPATIBILITY TESTS ====================

    /**
     * Test 1: Feet equality (UC1 backward compatibility)
     */
    @Test
    public void testFeetEquality() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(feet1.equals(feet2),
                "Two Length objects with 1.0 feet should be equal");
    }

    /**
     * Test 2: Inches equality (UC2 backward compatibility)
     */
    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(inches1.equals(inches2),
                "Two Length objects with 1.0 inches should be equal");
    }

    /**
     * Test 3: Feet and Inches comparison (UC3 backward compatibility)
     */
    @Test
    public void testFeetInchesComparison() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "1.0 feet should equal 12.0 inches");
    }

    /**
     * Test 4: Feet inequality
     */
    @Test
    public void testFeetInequality() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(feet1.equals(feet2),
                "1.0 feet should not equal 2.0 feet");
    }

    /**
     * Test 5: Inches inequality
     */
    @Test
    public void testInchesInequality() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(2.0, LengthUnit.INCHES);

        assertFalse(inches1.equals(inches2),
                "1.0 inches should not equal 2.0 inches");
    }

    /**
     * Test 6: Cross unit inequality
     */
    @Test
    public void testCrossUnitInequality() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertFalse(feet.equals(inches),
                "1.0 feet should not equal 1.0 inches");
    }

    /**
     * Test 7: Multiple feet comparison
     */
    @Test
    public void testMultipleFeetComparison() {
        Length feet = new Length(2.0, LengthUnit.FEET);
        Length inches = new Length(24.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "2.0 feet should equal 24.0 inches");
    }

    /**
     * Test 8: 1 cm equals 0.393701 inches
     */
    @Test
    public void centimeterEquals39Point3701Inches() {
        Length cm = new Length(100.0, LengthUnit.CENTIMETERS);
        Length inches = new Length(39.37, LengthUnit.INCHES);

        assertTrue(cm.equals(inches),
                "100 cm should equal approximately 39.37 inches");
    }

    /**
     * Test 9: Three feet equals one yard
     */
    @Test
    public void threeFeetEqualsOneYard() {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(feet.equals(yard),
                "3 feet should equal 1 yard");
    }

    /**
     * Test 10: 30.48 cm equals 1 foot
     */
    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length cm = new Length(30.48, LengthUnit.CENTIMETERS);
        Length foot = new Length(1.0, LengthUnit.FEET);

        assertTrue(cm.equals(foot),
                "30.48 cm should equal 1 foot");
    }

    /**
     * Test 11: 1 yard does not equal 1 inch
     */
    @Test
    public void yardNotEqualToInches() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertFalse(yard.equals(inches),
                "1 yard should not equal 1 inch");
    }

    /**
     * Test 12: Reference equality (same object)
     */
    @Test
    public void referenceEqualitySameObject() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(yard.equals(yard),
                "A Length object should equal itself (reflexive property)");
    }

    /**
     * Test 13: Equals returns false for null
     */
    @Test
    public void equalsReturnsFalseForNull() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertFalse(yard.equals(null),
                "Length object should not equal null");
    }

    /**
     * Test 14: Reflexive, Symmetric and Transitive property
     */
    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        // Reflexive
        assertTrue(yard.equals(yard), "Reflexive property failed");

        // Symmetric
        assertTrue(yard.equals(feet) && feet.equals(yard),
                "Symmetric property failed");

        // Transitive
        assertTrue(yard.equals(feet) && feet.equals(inches) && yard.equals(inches),
                "Transitive property failed");
    }

    /**
     * Test 15: Different values same unit not equal
     */
    @Test
    public void differentValuesSameUnitNotEqual() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(2.0, LengthUnit.YARDS);

        assertFalse(yard1.equals(yard2),
                "1 yard should not equal 2 yards");
    }

    /**
     * Test 16: Cross unit equality demonstrate method
     */
    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(yard, feet),
                "Demonstrate method should return true for equal lengths");
    }

    /**
     * Test 17: Convert feet to inches (UC5 backward compatibility)
     */
    @Test
    public void convertFeetToInches() {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length result = feet.convertTo(LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON,
                "3 feet should convert to 36 inches");
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    /**
     * Test 18: Convert yards to inches using overloaded method (UC5 backward compatibility)
     */
    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        Length lengthInYards = new Length(2.0, LengthUnit.YARDS);
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);
        Length expected = new Length(72.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp
                        .demonstrateLengthEquality(result, expected),
                "2 yards should convert to 72 inches");
    }

    // ==================== UC6 ADDITION TESTS ====================

    /**
     * Test 19: Same unit addition - Feet + Feet
     * add(1.0 FEET, 2.0 FEET) should return 3.0 FEET
     */
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2.0, LengthUnit.FEET);
        Length result = length1.add(length2);

        assertEquals(3.0, result.getValue(), EPSILON,
                "1 foot + 2 feet should equal 3 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET");
    }

    /**
     * Test 20: Same unit addition - Inches + Inches
     * add(6.0 INCHES, 6.0 INCHES) should return 12.0 INCHES
     */
    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length length1 = new Length(6.0, LengthUnit.INCHES);
        Length length2 = new Length(6.0, LengthUnit.INCHES);
        Length result = length1.add(length2);

        assertEquals(12.0, result.getValue(), EPSILON,
                "6 inches + 6 inches should equal 12 inches");
        assertEquals(LengthUnit.INCHES, result.getUnit(),
                "Result unit should be INCHES");
    }

    /**
     * Test 21: Cross unit addition - Feet + Inches (result in FEET)
     * add(1.0 FEET, 12.0 INCHES) should return 2.0 FEET
     */
    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length result = length1.add(length2);

        assertEquals(2.0, result.getValue(), EPSILON,
                "1 foot + 12 inches should equal 2 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET (unit of first operand)");
    }

    /**
     * Test 22: Cross unit addition - Inches + Feet (result in INCHES)
     * add(12.0 INCHES, 1.0 FEET) should return 24.0 INCHES
     */
    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.FEET);
        Length result = length1.add(length2);

        assertEquals(24.0, result.getValue(), EPSILON,
                "12 inches + 1 foot should equal 24 inches");
        assertEquals(LengthUnit.INCHES, result.getUnit(),
                "Result unit should be INCHES (unit of first operand)");
    }

    /**
     * Test 23: Cross unit addition - Yards + Feet (result in YARDS)
     * add(1.0 YARDS, 3.0 FEET) should return 2.0 YARDS
     */
    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        Length result = length1.add(length2);

        assertEquals(2.0, result.getValue(), EPSILON,
                "1 yard + 3 feet should equal 2 yards");
        assertEquals(LengthUnit.YARDS, result.getUnit(),
                "Result unit should be YARDS (unit of first operand)");
    }

    /**
     * Test 24: Cross unit addition - Centimeters + Inches (result in CENTIMETERS)
     * add(2.54 CENTIMETERS, 1.0 INCHES) should return approximately 5.08 CENTIMETERS
     */
    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Length length1 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, LengthUnit.INCHES);
        Length result = length1.add(length2);

        assertEquals(5.08, result.getValue(), EPSILON,
                "2.54 cm + 1 inch should equal approximately 5.08 cm");
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit(),
                "Result unit should be CENTIMETERS (unit of first operand)");
    }

    /**
     * Test 25: Addition with feet and inches using demonstrateLengthAddition
     * add(1.0 FEET, 12.0 INCHES) should return 2.0 FEET
     */
    @Test
    public void addFeetAndInches() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp
                .demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(2.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp
                        .demonstrateLengthEquality(sumLength, expectedLength),
                "1 foot + 12 inches should equal 2 feet");
    }

    /**
     * Test 26: Commutativity
     * add(1.0 FEET, 12.0 INCHES) should equal add(12.0 INCHES, 1.0 FEET)
     * when compared in a common base unit
     */
    @Test
    public void testAddition_Commutativity() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        Length result1 = feet.add(inches);     // 2.0 FEET
        Length result2 = inches.add(feet);     // 24.0 INCHES

        // Both should represent the same physical length
        assertTrue(result1.equals(result2),
                "Addition should be commutative: 1ft+12in == 12in+1ft");
    }

    /**
     * Test 27: Identity element - adding zero
     * add(5.0 FEET, 0.0 INCHES) should return 5.0 FEET
     */
    @Test
    public void testAddition_WithZero() {
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length zeroInches = new Length(0.0, LengthUnit.INCHES);
        Length result = fiveFeet.add(zeroInches);

        assertEquals(5.0, result.getValue(), EPSILON,
                "5 feet + 0 inches should equal 5 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET");
    }

    /**
     * Test 28: Negative value addition
     * add(5.0 FEET, -2.0 FEET) should return 3.0 FEET
     */
    @Test
    public void testAddition_NegativeValues() {
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length negTwoFeet = new Length(-2.0, LengthUnit.FEET);
        Length result = fiveFeet.add(negTwoFeet);

        assertEquals(3.0, result.getValue(), EPSILON,
                "5 feet + (-2 feet) should equal 3 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET");
    }

    /**
     * Test 29: Null second operand throws exception
     * add(1.0 FEET, null) should throw IllegalArgumentException
     */
    @Test
    public void testAddition_NullSecondOperand() {
        Length length = new Length(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            length.add(null);
        }, "Adding null should throw IllegalArgumentException");
    }

    /**
     * Test 30: Large value addition
     * add(1e6 FEET, 1e6 FEET) should return 2e6 FEET
     */
    @Test
    public void testAddition_LargeValues() {
        Length large1 = new Length(1e6, LengthUnit.FEET);
        Length large2 = new Length(1e6, LengthUnit.FEET);
        Length result = large1.add(large2);

        assertEquals(2e6, result.getValue(), 1.0,
                "1,000,000 feet + 1,000,000 feet should equal 2,000,000 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET");
    }

    /**
     * Test 31: Small value addition
     * add(0.001 FEET, 0.002 FEET) should return approximately 0.003 FEET
     */
    @Test
    public void testAddition_SmallValues() {
        Length small1 = new Length(0.001, LengthUnit.FEET);
        Length small2 = new Length(0.002, LengthUnit.FEET);
        Length result = small1.add(small2);

        assertEquals(0.003, result.getValue(), EPSILON,
                "0.001 feet + 0.002 feet should equal approximately 0.003 feet");
    }

    /**
     * Test 32: Yards + Inches addition
     * add(36.0 INCHES, 1.0 YARDS) should return 72.0 INCHES
     */
    @Test
    public void testAddition_InchPlusYard() {
        Length inches = new Length(36.0, LengthUnit.INCHES);
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length result = inches.add(yard);

        assertEquals(72.0, result.getValue(), EPSILON,
                "36 inches + 1 yard should equal 72 inches");
        assertEquals(LengthUnit.INCHES, result.getUnit(),
                "Result unit should be INCHES");
    }

    /**
     * Test 33: Immutability check
     * Original lengths should remain unchanged after addition
     */
    @Test
    public void testAddition_Immutability() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        // Perform addition
        length1.add(length2);

        // Originals should be unchanged
        assertEquals(1.0, length1.getValue(), EPSILON,
                "Original length1 should remain unchanged");
        assertEquals(LengthUnit.FEET, length1.getUnit(),
                "Original length1 unit should remain FEET");
        assertEquals(12.0, length2.getValue(), EPSILON,
                "Original length2 should remain unchanged");
    }

    /**
     * Test 34: Result unit is always the unit of the first operand
     */
    @Test
    public void testAddition_ResultUnitIsFirstOperandUnit() {
        Length cm = new Length(10.0, LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, LengthUnit.INCHES);

        Length result = cm.add(inch);

        assertEquals(LengthUnit.CENTIMETERS, result.getUnit(),
                "Result unit should always be the unit of the first operand");
    }

    /**
     * Test 35: Chain addition
     * 1 foot + 12 inches + 1 foot = 3 feet
     */
    @Test
    public void testAddition_ChainAddition() {
        Length foot1 = new Length(1.0, LengthUnit.FEET);
        Length inches12 = new Length(12.0, LengthUnit.INCHES);
        Length foot2 = new Length(1.0, LengthUnit.FEET);

        // Chain: (foot1 + inches12) + foot2
        Length result = foot1.add(inches12).add(foot2);

        assertEquals(3.0, result.getValue(), EPSILON,
                "1 foot + 12 inches + 1 foot should equal 3 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET");
    }
}