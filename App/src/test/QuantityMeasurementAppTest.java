package test;

/**
 * QuantityMeasurementAppUC7Test
 *
 * Test class for UC7 - Addition of Length Measurements with Target Unit Specification
 *
 * This test class validates the addition operations performed on different length measurements
 * in the Quantity Measurement Application. It ensures that lengths can be added together
 * and produce correct results regardless of their units of measurement.
 *
 * Test Coverage:
 * - Addition of lengths in the same unit (e.g., Feet + Feet)
 * - Addition of lengths in different units (e.g., Feet + Inches)
 * - Addition of lengths with target unit specification (e.g., Feet + Inches = Inches)
 * - Validation of results in specified target units
 * - Addition of lengths with unit conversion and result validation
 * - Edge cases and boundary conditions for length addition
 *
 * @author Developer
 * @version 7.0
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
     * Test 1: Feet equality
     */
    @Test
    public void testFeetEquality() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);
        assertTrue(feet1.equals(feet2),
                "Two Length objects with 1.0 feet should be equal");
    }

    /**
     * Test 2: Inches equality
     */
    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);
        assertTrue(inches1.equals(inches2),
                "Two Length objects with 1.0 inches should be equal");
    }

    /**
     * Test 3: Feet and Inches comparison
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
     * Test 8: Centimeter equals 39.37 inches (per 100)
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
                "A Length object should equal itself");
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

        assertTrue(yard.equals(yard), "Reflexive property failed");
        assertTrue(yard.equals(feet) && feet.equals(yard), "Symmetric property failed");
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
     * Test 18: Convert yards to inches using overloaded method
     */
    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        Length lengthInYards = new Length(2.0, LengthUnit.YARDS);
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);
        Length expected = new Length(72.0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(result, expected),
                "2 yards should convert to 72 inches");
    }

    /**
     * Test 19: UC6 add - Feet + Inches (result in first operand unit: FEET)
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

    // ==================== UC7 ADDITION WITH TARGET UNIT TESTS ====================

    /**
     * Test 20: Explicit target unit = FEET
     * add(1.0 FEET, 12.0 INCHES, FEET) should return 2.0 FEET
     */
    @Test
    public void addFeetAndInchesWithTargetUnitInches() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length result = length1.add(length2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON,
                "1 foot + 12 inches in INCHES should equal 24.0 inches");
        assertEquals(LengthUnit.INCHES, result.getUnit(),
                "Result unit should be INCHES");
    }

    /**
     * Test 21: Explicit target unit = FEET
     * add(1.0 FEET, 12.0 INCHES, FEET) should return 2.0 FEET
     */
    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length result = length1.add(length2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON,
                "1 foot + 12 inches in FEET should equal 2.0 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET");
    }

    /**
     * Test 22: Explicit target unit = INCHES
     * add(1.0 FEET, 12.0 INCHES, INCHES) should return 24.0 INCHES
     */
    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length result = length1.add(length2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON,
                "1 foot + 12 inches in INCHES should equal 24.0 inches");
        assertEquals(LengthUnit.INCHES, result.getUnit(),
                "Result unit should be INCHES");
    }

    /**
     * Test 23: Explicit target unit = YARDS (different from both operands)
     * add(1.0 FEET, 12.0 INCHES, YARDS) should return approximately 0.67 YARDS
     */
    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length result = length1.add(length2, LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), EPSILON,
                "1 foot + 12 inches in YARDS should equal approximately 0.67 yards");
        assertEquals(LengthUnit.YARDS, result.getUnit(),
                "Result unit should be YARDS");
    }

    /**
     * Test 24: Explicit target unit = CENTIMETERS
     * add(1.0 INCHES, 1.0 INCHES, CENTIMETERS) should return approximately 5.08 CENTIMETERS
     */
    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.INCHES);
        Length result = length1.add(length2, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON,
                "1 inch + 1 inch in CENTIMETERS should equal approximately 5.08 cm");
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit(),
                "Result unit should be CENTIMETERS");
    }

    /**
     * Test 25: Explicit target unit same as first operand
     * add(2.0 YARDS, 3.0 FEET, YARDS) should return 3.0 YARDS
     */
    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length length1 = new Length(2.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        Length result = length1.add(length2, LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), EPSILON,
                "2 yards + 3 feet in YARDS should equal 3.0 yards");
        assertEquals(LengthUnit.YARDS, result.getUnit(),
                "Result unit should be YARDS");
    }

    /**
     * Test 26: Explicit target unit same as second operand
     * add(2.0 YARDS, 3.0 FEET, FEET) should return 9.0 FEET
     */
    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length length1 = new Length(2.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        Length result = length1.add(length2, LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON,
                "2 yards + 3 feet in FEET should equal 9.0 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET");
    }

    /**
     * Test 27: Commutativity with explicit target unit
     * add(1.0 FEET, 12.0 INCHES, YARDS) == add(12.0 INCHES, 1.0 FEET, YARDS)
     */
    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        Length result1 = feet.add(inches, LengthUnit.YARDS);
        Length result2 = inches.add(feet, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON,
                "Addition with explicit target unit should be commutative");
        assertEquals(result1.getUnit(), result2.getUnit(),
                "Both results should have the same unit");
    }

    /**
     * Test 28: Zero operand with explicit target unit
     * add(5.0 FEET, 0.0 INCHES, YARDS) should return approximately 1.67 YARDS
     */
    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length zeroInches = new Length(0.0, LengthUnit.INCHES);
        Length result = fiveFeet.add(zeroInches, LengthUnit.YARDS);

        assertEquals(1.67, result.getValue(), EPSILON,
                "5 feet + 0 inches in YARDS should equal approximately 1.67 yards");
        assertEquals(LengthUnit.YARDS, result.getUnit(),
                "Result unit should be YARDS");
    }

    /**
     * Test 29: Negative values with explicit target unit
     * add(5.0 FEET, -2.0 FEET, INCHES) should return 36.0 INCHES
     */
    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length negTwoFeet = new Length(-2.0, LengthUnit.FEET);
        Length result = fiveFeet.add(negTwoFeet, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON,
                "5 feet + (-2 feet) in INCHES should equal 36.0 inches");
        assertEquals(LengthUnit.INCHES, result.getUnit(),
                "Result unit should be INCHES");
    }

    /**
     * Test 30: Null target unit throws IllegalArgumentException
     */
    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            length1.add(length2, null);
        }, "Null target unit should throw IllegalArgumentException");
    }

    /**
     * Test 31: Null second operand throws IllegalArgumentException
     */
    @Test
    public void testAddition_ExplicitTargetUnit_NullSecondOperand() {
        Length length1 = new Length(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            length1.add(null, LengthUnit.FEET);
        }, "Null second operand should throw IllegalArgumentException");
    }

    /**
     * Test 32: Large to small scale conversion
     * add(1000.0 FEET, 500.0 FEET, INCHES) should return 18000.0 INCHES
     */
    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length large1 = new Length(1000.0, LengthUnit.FEET);
        Length large2 = new Length(500.0, LengthUnit.FEET);
        Length result = large1.add(large2, LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), 1.0,
                "1000 feet + 500 feet in INCHES should equal 18000.0 inches");
        assertEquals(LengthUnit.INCHES, result.getUnit(),
                "Result unit should be INCHES");
    }

    /**
     * Test 33: Small to large scale conversion
     * add(12.0 INCHES, 12.0 INCHES, YARDS) should return approximately 0.67 YARDS
     */
    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length inches1 = new Length(12.0, LengthUnit.INCHES);
        Length inches2 = new Length(12.0, LengthUnit.INCHES);
        Length result = inches1.add(inches2, LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), EPSILON,
                "12 inches + 12 inches in YARDS should equal approximately 0.67 yards");
        assertEquals(LengthUnit.YARDS, result.getUnit(),
                "Result unit should be YARDS");
    }

    /**
     * Test 34: All unit combinations - Mathematical correctness
     * add(1 FEET, 12 INCHES, FEET) = 2 FEET and add(1 FEET, 12 INCHES, INCHES) = 24 INCHES
     * Both should represent the same physical length
     */
    @Test
    public void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        Length resultInFeet = feet.add(inches, LengthUnit.FEET);
        Length resultInInches = feet.add(inches, LengthUnit.INCHES);
        Length resultInYards = feet.add(inches, LengthUnit.YARDS);
        Length resultInCm = feet.add(inches, LengthUnit.CENTIMETERS);

        // Verify values
        assertEquals(2.0, resultInFeet.getValue(), EPSILON);
        assertEquals(24.0, resultInInches.getValue(), EPSILON);
        assertEquals(0.67, resultInYards.getValue(), EPSILON);

        // Verify units
        assertEquals(LengthUnit.FEET, resultInFeet.getUnit());
        assertEquals(LengthUnit.INCHES, resultInInches.getUnit());
        assertEquals(LengthUnit.YARDS, resultInYards.getUnit());
        assertEquals(LengthUnit.CENTIMETERS, resultInCm.getUnit());

        // Verify all represent the same physical length
        assertTrue(resultInFeet.equals(resultInInches),
                "2 FEET should equal 24 INCHES (same physical length)");
        assertTrue(resultInFeet.equals(resultInYards),
                "2 FEET should equal ~0.67 YARDS (same physical length)");
    }

    /**
     * Test 35: Precision tolerance with explicit target units
     * Multiple additions verified using epsilon-based comparison
     */
    @Test
    public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, LengthUnit.INCHES);
        Length result = cm.add(inch, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON,
                "2.54 cm + 1 inch in CENTIMETERS should equal approximately 5.08 cm");
    }

    /**
     * Test 36: UC7 demonstrateLengthAddition with target unit - static method
     * add(1.0 FEET, 12.0 INCHES, FEET) using static method
     */
    @Test
    public void testDemonstrateLengthAdditionWithTargetUnit() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(length1, length2, LengthUnit.FEET);
        Length expected = new Length(2.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(result, expected),
                "Static method should return 2.0 FEET for 1 foot + 12 inches target FEET");
    }

    /**
     * Test 37: UC7 demonstrateLengthAddition with target unit YARDS
     * add(36.0 INCHES, 1.0 YARDS, FEET) should return 6.0 FEET
     */
    @Test
    public void testDemonstrateLengthAdditionYardsAndInchesToFeet() {
        Length inches = new Length(36.0, LengthUnit.INCHES);
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(inches, yard, LengthUnit.FEET);

        assertEquals(6.0, result.getValue(), EPSILON,
                "36 inches + 1 yard in FEET should equal 6.0 feet");
        assertEquals(LengthUnit.FEET, result.getUnit(),
                "Result unit should be FEET");
    }

    /**
     * Test 38: Immutability preserved with explicit target unit
     */
    @Test
    public void testAddition_ExplicitTargetUnit_ImmutabilityPreserved() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        length1.add(length2, LengthUnit.YARDS);

        // Originals should remain unchanged
        assertEquals(1.0, length1.getValue(), EPSILON,
                "Original length1 value should be unchanged");
        assertEquals(LengthUnit.FEET, length1.getUnit(),
                "Original length1 unit should be unchanged");
        assertEquals(12.0, length2.getValue(), EPSILON,
                "Original length2 value should be unchanged");
        assertEquals(LengthUnit.INCHES, length2.getUnit(),
                "Original length2 unit should be unchanged");
    }
}