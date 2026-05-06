package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.QuantityMeasurementApp;
import main.QuantityMeasurementApp.Length;
import main.QuantityMeasurementApp.LengthUnit;

/**
 * Use Case 5: Conversion from one unit to another
 *
 * This test class validates the conversion functionality between
 * different units of measurement.
 *
 * The Quantity Measurement Application now supports conversions between
 * various units within the same measurement category.
 *
 * Please ensure the original functionalities of comparison are intact
 * while adding conversion features.
 */
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
     * Test 8: 1 yard equals 36 inches
     */
    @Test
    public void yardEquals36Inches() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(inches),
                "1 yard should equal 36 inches");
    }

    /**
     * Test 9: 30.48 cm equals 1 foot
     */
    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length cm = new Length(30.48, LengthUnit.CENTIMETERS);
        Length foot = new Length(1.0, LengthUnit.FEET);

        assertTrue(cm.equals(foot),
                "30.48 cm should equal 1 foot");
    }

    /**
     * Test 10: 1 yard does not equal 1 inch
     */
    @Test
    public void yardNotEqualToInches() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertFalse(yard.equals(inches),
                "1 yard should not equal 1 inch");
    }

    /**
     * Test 11: Reference equality (same object)
     */
    @Test
    public void referenceEqualitySameObject() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(yard.equals(yard),
                "A Length object should equal itself (reflexive property)");
    }

    /**
     * Test 12: Equals returns false for null
     */
    @Test
    public void equalsReturnsFalseForNull() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertFalse(yard.equals(null),
                "Length object should not equal null");
    }

    /**
     * Test 13: Reflexive, symmetric and transitive property
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
     * Test 14: Different values same unit not equal
     */
    @Test
    public void differentValuesSameUnitNotEqual() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(2.0, LengthUnit.YARDS);

        assertFalse(yard1.equals(yard2),
                "1 yard should not equal 2 yards");
    }

    /**
     * Test 15: Cross unit equality demonstrate method
     */
    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(yard, feet),
                "Demonstrate method should return true for equal lengths");
    }

    // ==================== CONVERSION TESTS (UC5 NEW TESTS) ====================

    /**
     * Test 16: Convert feet to inches
     * convert(1.0, FEET, INCHES) should return 12.0
     */
    @Test
    public void convertFeetToInches() {
        Length lengthInFeet = new Length(3.0, LengthUnit.FEET);
        Length lengthInInches = lengthInFeet.convertTo(LengthUnit.INCHES);

        assertEquals(36.0, lengthInInches.getValue(), EPSILON,
                "3 feet should convert to 36 inches");
        assertEquals(LengthUnit.INCHES, lengthInInches.getUnit(),
                "Unit should be INCHES after conversion");
    }

    /**
     * Test 17: Convert yards to inches using overloaded method
     * convert(2.0, YARDS, INCHES) should return 72.0
     */
    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        Length lengthInYards = new Length(2.0, LengthUnit.YARDS);
        Length lengthInInches = QuantityMeasurementApp
                .demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);
        Length expectedLength = new Length(72.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp
                        .demonstrateLengthEquality(lengthInInches, expectedLength),
                "2 yards should convert to 72 inches");
    }

    /**
     * Test 18: Convert inches to feet
     * convert(24.0, INCHES, FEET) should return 2.0
     */
    @Test
    public void convertInchesToFeet() {
        Length lengthInInches = new Length(24.0, LengthUnit.INCHES);
        Length lengthInFeet = lengthInInches.convertTo(LengthUnit.FEET);

        assertEquals(2.0, lengthInFeet.getValue(), EPSILON,
                "24 inches should convert to 2 feet");
        assertEquals(LengthUnit.FEET, lengthInFeet.getUnit(),
                "Unit should be FEET after conversion");
    }

    /**
     * Test 19: Convert yards to feet
     * convert(3.0, YARDS, FEET) should return 9.0
     */
    @Test
    public void convertYardsToFeet() {
        Length lengthInYards = new Length(3.0, LengthUnit.YARDS);
        Length lengthInFeet = lengthInYards.convertTo(LengthUnit.FEET);

        assertEquals(9.0, lengthInFeet.getValue(), EPSILON,
                "3 yards should convert to 9 feet");
    }

    /**
     * Test 20: Convert centimeters to inches
     * convert(2.54, CENTIMETERS, INCHES) should return approximately 1.0
     */
    @Test
    public void convertCentimetersToInches() {
        Length lengthInCm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length lengthInInches = lengthInCm.convertTo(LengthUnit.INCHES);

        assertEquals(1.0, lengthInInches.getValue(), EPSILON,
                "2.54 cm should convert to approximately 1.0 inch");
    }

    /**
     * Test 21: Convert feet to yards
     * convert(6.0, FEET, YARDS) should return 2.0
     */
    @Test
    public void convertFeetToYards() {
        Length lengthInFeet = new Length(6.0, LengthUnit.FEET);
        Length lengthInYards = lengthInFeet.convertTo(LengthUnit.YARDS);

        assertEquals(2.0, lengthInYards.getValue(), EPSILON,
                "6 feet should convert to 2 yards");
    }

    /**
     * Test 22: Convert inches to yards
     * convert(72.0, INCHES, YARDS) should return 2.0
     */
    @Test
    public void convertInchesToYards() {
        Length lengthInInches = new Length(72.0, LengthUnit.INCHES);
        Length lengthInYards = lengthInInches.convertTo(LengthUnit.YARDS);

        assertEquals(2.0, lengthInYards.getValue(), EPSILON,
                "72 inches should convert to 2 yards");
    }

    /**
     * Test 23: Round-trip conversion preserves value
     * convert(convert(v, A, B), B, A) should return approximately v
     */
    @Test
    public void convertRoundTripPreservesValue() {
        Length original = new Length(5.0, LengthUnit.FEET);
        Length toInches = original.convertTo(LengthUnit.INCHES);
        Length backToFeet = toInches.convertTo(LengthUnit.FEET);

        assertEquals(5.0, backToFeet.getValue(), EPSILON,
                "Round-trip conversion should preserve original value");
    }

    /**
     * Test 24: Zero value conversion
     * convert(0.0, FEET, INCHES) should return 0.0
     */
    @Test
    public void convertZeroValue() {
        Length zeroFeet = new Length(0.0, LengthUnit.FEET);
        Length zeroInches = zeroFeet.convertTo(LengthUnit.INCHES);

        assertEquals(0.0, zeroInches.getValue(), EPSILON,
                "Zero feet should convert to zero inches");
    }

    /**
     * Test 25: Negative value conversion
     * convert(-1.0, FEET, INCHES) should return -12.0
     */
    @Test
    public void convertNegativeValue() {
        Length negativeFeet = new Length(-1.0, LengthUnit.FEET);
        Length negativeInches = negativeFeet.convertTo(LengthUnit.INCHES);

        assertEquals(-12.0, negativeInches.getValue(), EPSILON,
                "-1 foot should convert to -12 inches");
    }

    /**
     * Test 26: Same unit conversion returns same value
     * convert(5.0, FEET, FEET) should return 5.0
     */
    @Test
    public void convertSameUnit() {
        Length length = new Length(5.0, LengthUnit.FEET);
        Length converted = length.convertTo(LengthUnit.FEET);

        assertEquals(5.0, converted.getValue(), EPSILON,
                "Converting to the same unit should return the same value");
        assertEquals(LengthUnit.FEET, converted.getUnit(),
                "Unit should remain FEET");
    }

    /**
     * Test 27: Invalid unit throws IllegalArgumentException
     */
    @Test
    public void convertInvalidUnitThrows() {
        Length length = new Length(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            length.convertTo(null);
        }, "Null target unit should throw IllegalArgumentException");
    }

    /**
     * Test 28: NaN value conversion
     */
    @Test
    public void convertNaNOrInfiniteThrows() {
        Length nanLength = new Length(Double.NaN, LengthUnit.FEET);
        Length result = nanLength.convertTo(LengthUnit.INCHES);

        assertTrue(Double.isNaN(result.getValue()),
                "NaN value conversion should remain NaN");
    }

    /**
     * Test 29: Precision tolerance for conversion
     */
    @Test
    public void convertPrecisionTolerance() {
        Length lengthInCm = new Length(100.0, LengthUnit.CENTIMETERS);
        Length lengthInInches = lengthInCm.convertTo(LengthUnit.INCHES);

        assertEquals(39.37, lengthInInches.getValue(), EPSILON,
                "100 cm should convert to approximately 39.37 inches within epsilon tolerance");
    }

    /**
     * Test 30: Large value conversion maintains precision
     */
    @Test
    public void convertLargeValue() {
        Length largeFeet = new Length(1000.0, LengthUnit.FEET);
        Length largeInches = largeFeet.convertTo(LengthUnit.INCHES);

        assertEquals(12000.0, largeInches.getValue(), EPSILON,
                "1000 feet should convert to 12000 inches");
    }

    /**
     * Test 31: Demonstrate length conversion with 3 params
     */
    @Test
    public void demonstrateLengthConversionThreeParams() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(3.0, LengthUnit.FEET, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON,
                "demonstrateLengthConversion(3.0, FEET, INCHES) should return 36.0");
        assertEquals(LengthUnit.INCHES, result.getUnit(),
                "Result unit should be INCHES");
    }

    /**
     * Test 32: Centimeters to feet conversion
     */
    @Test
    public void convertCentimetersToFeet() {
        Length cm = new Length(30.48, LengthUnit.CENTIMETERS);
        Length feet = cm.convertTo(LengthUnit.FEET);

        assertEquals(1.0, feet.getValue(), EPSILON,
                "30.48 cm should convert to 1 foot");
    }

    /**
     * Test 33: Three feet equals one yard
     */
    @Test
    public void threeFeetEqualsOneYard() {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(feet.equals(yard),
                "3 feet should equal 1 yard");
    }

    /**
     * Test 34: Centimeter equals 0.393701 inches
     */
    @Test
    public void centimeterEquals39Point3701Inches() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length convertedToInches = cm.convertTo(LengthUnit.INCHES);

        assertEquals(0.39, convertedToInches.getValue(), EPSILON,
                "1 cm should convert to approximately 0.39 inches");
    }
}