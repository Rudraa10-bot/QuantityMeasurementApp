package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.QuantityMeasurementApp.Length;
import main.QuantityMeasurementApp.LengthUnit;

/**
 * Test class for QuantityMeasurementApp - UC4: Extended Unit Support
 * Tests various equality scenarios for Length objects with Yards and Centimeters
 */
public class QuantityMeasurementAppTest {

    // ==================== FEET EQUALITY TESTS ====================

    /**
     * Test Case 1: testFeetEquality
     * Verifies backward compatibility with UC3
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
     * Verifies backward compatibility with UC3
     */
    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(inches1.equals(inches2),
                "Two Length objects with value 1.0 inches should be equal");
    }

    // ==================== FEET-INCHES COMPARISON TESTS ====================

    /**
     * Test Case 3: testFeetInchesComparison
     * Verifies backward compatibility with UC3
     */
    @Test
    public void testFeetInchesComparison() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "1.0 feet should equal 12.0 inches");
    }

    // ==================== YARDS EQUALITY TESTS ====================

    /**
     * Test Case 4: testFeetInequality
     * Verifies inequality for different feet values
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
     * Verifies inequality for different inch values
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
     * Verifies cross-unit inequality
     */
    @Test
    public void testCrossUnitInequality() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertFalse(feet.equals(inches),
                "1.0 feet should not equal 1.0 inches");
    }

    /**
     * Test Case 7: testMultipleFeetComparison
     * Verifies 2 feet equals 24 inches
     */
    @Test
    public void testMultipleFeetComparison() {
        Length feet = new Length(2.0, LengthUnit.FEET);
        Length inches = new Length(24.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches),
                "2.0 feet should equal 24.0 inches");
    }

    // ==================== YARDS EQUALITY TESTS ====================

    /**
     * Test Case 8: yardEquals36Inches
     * Verifies that 1 yard equals 36 inches
     */
    @Test
    public void yardEquals36Inches() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(inches),
                "1 yard should equal 36 inches");
    }

    /**
     * Test Case 9: threeFeetEqualsOneYard
     * Verifies that 3 feet equals 1 yard
     */
    @Test
    public void threeFeetEqualsOneYard() {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(feet.equals(yard),
                "3 feet should equal 1 yard");
    }

    /**
     * Test Case 10: thirtyPoint48CmEqualsOneFoot
     * Verifies that 30.48 cm equals 1 foot
     */
    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length cm = new Length(30.48, LengthUnit.CENTIMETERS);
        Length foot = new Length(1.0, LengthUnit.FEET);

        assertTrue(cm.equals(foot),
                "30.48 cm should equal 1 foot");
    }

    /**
     * Test Case 11: yardNotEqualToInches
     * Verifies that 1 yard does not equal 1 inch
     */
    @Test
    public void yardNotEqualToInches() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(1.0, LengthUnit.INCHES);

        assertFalse(yard.equals(inches),
                "1 yard should not equal 1 inch");
    }

    /**
     * Test Case 12: referenceEqualitySameObject
     * Verifies reflexive property
     */
    @Test
    public void referenceEqualitySameObject() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(yard.equals(yard),
                "A yard object should equal itself (reflexive property)");
    }

    /**
     * Test Case 13: equalsReturnsFalseForNull
     * Verifies null safety
     */
    @Test
    public void equalsReturnsFalseForNull() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertFalse(yard.equals(null),
                "Yard object should not equal null");
    }

    /**
     * Test Case 14: reflexiveSymmetricAndTransitiveProperty
     * Verifies reflexive, symmetric, and transitive properties
     */
    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        // Reflexive
        assertTrue(yard.equals(yard), "Reflexive property failed");

        // Symmetric
        assertTrue(yard.equals(feet) && feet.equals(yard), "Symmetric property failed");

        // Transitive
        assertTrue(yard.equals(feet) && feet.equals(inches) && yard.equals(inches),
                "Transitive property failed");
    }

    /**
     * Test Case 15: differentValuesSameUnitNotEqual
     * Verifies inequality for different values of same unit
     */
    @Test
    public void differentValuesSameUnitNotEqual() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(2.0, LengthUnit.YARDS);

        assertFalse(yard1.equals(yard2),
                "1 yard should not equal 2 yards");
    }

    // ==================== CENTIMETERS EQUALITY TESTS ====================

    /**
     * Test Case 16: centimeterEquals39Point3701Inches
     * Verifies that 1 cm equals 0.393701 inches
     */
    @Test
    public void centimeterEquals39Point3701Point() {
        Length cm = new Length(100.0, LengthUnit.CENTIMETERS);
        Length inches = new Length(39.3701, LengthUnit.INCHES);

        assertTrue(cm.equals(inches),
                "100 cm should equal 39.3701 inches");
    }

    /**
     * Test Case 17: oneCentimeterNotEqualOneInch
     * Verifies that 1 cm does not equal 1 inch
     */
    @Test
    public void oneCentimeterNotEqualOneInch() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, LengthUnit.INCHES);

        assertFalse(cm.equals(inch),
                "1 cm should not equal 1 inch");
    }

    /**
     * Test Case 18: centimeterEqualitySameValue
     * Verifies that two cm measurements with same value are equal
     */
    @Test
    public void centimeterEqualitySameValue() {
        Length cm1 = new Length(10.0, LengthUnit.CENTIMETERS);
        Length cm2 = new Length(10.0, LengthUnit.CENTIMETERS);

        assertTrue(cm1.equals(cm2),
                "10 cm should equal 10 cm");
    }

    /**
     * Test Case 19: centimeterInequalityDifferentValue
     * Verifies inequality for different cm values
     */
    @Test
    public void centimeterInequalityDifferentValue() {
        Length cm1 = new Length(10.0, LengthUnit.CENTIMETERS);
        Length cm2 = new Length(20.0, LengthUnit.CENTIMETERS);

        assertFalse(cm1.equals(cm2),
                "10 cm should not equal 20 cm");
    }

    /**
     * Test Case 20: centimeterReflexiveProperty
     * Verifies reflexive property for centimeters
     */
    @Test
    public void centimeterReflexiveProperty() {
        Length cm = new Length(10.0, LengthUnit.CENTIMETERS);

        assertTrue(cm.equals(cm),
                "A cm object should equal itself (reflexive property)");
    }

    /**
     * Test Case 21: centimeterNullComparison
     * Verifies null safety for centimeters
     */
    @Test
    public void centimeterNullComparison() {
        Length cm = new Length(10.0, LengthUnit.CENTIMETERS);

        assertFalse(cm.equals(null),
                "Centimeter object should not equal null");
    }

    /**
     * Test Case 22: centimeterSymmetricProperty
     * Verifies symmetric property for centimeters
     */
    @Test
    public void centimeterSymmetricProperty() {
        Length cm1 = new Length(10.0, LengthUnit.CENTIMETERS);
        Length cm2 = new Length(10.0, LengthUnit.CENTIMETERS);

        assertTrue(cm1.equals(cm2) && cm2.equals(cm1),
                "Symmetric property should hold for centimeters");
    }

    /**
     * Test Case 23: centimeterToYardConversion
     * Verifies cm to yard conversion
     */
    @Test
    public void centimeterToYardConversion() {
        Length cm = new Length(91.44, LengthUnit.CENTIMETERS);
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(cm.equals(yard),
                "91.44 cm should equal 1 yard");
    }

    /**
     * Test Case 24: crossUnitEqualityDemonstrateMethod
     * Verifies the demonstrateLengthEquality method works correctly
     */
    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(main.QuantityMeasurementApp.demonstrateLengthEquality(yard, feet),
                "Demonstrate method should return true for equal lengths");
    }

    /**
     * Test Case 25: twoYardsEqual72Inches
     * Verifies complex multi-unit scenario
     */
    @Test
    public void twoYardsEqual72Inches() {
        Length yards = new Length(2.0, LengthUnit.YARDS);
        Length inches = new Length(72.0, LengthUnit.INCHES);

        assertTrue(yards.equals(inches),
                "2 yards should equal 72 inches");
    }

    /**
     * Test Case 26: twoYardsEqual6Feet
     * Verifies yard to feet conversion for multiple yards
     */
    @Test
    public void twoYardsEqual6Feet() {
        Length yards = new Length(2.0, LengthUnit.YARDS);
        Length feet = new Length(6.0, LengthUnit.FEET);

        assertTrue(yards.equals(feet),
                "2 yards should equal 6 feet");
    }

    /**
     * Test Case 27: zeroValuesAcrossAllUnits
     * Verifies that zero values are equal across all units
     */
    @Test
    public void zeroValuesAcrossAllUnits() {
        Length zeroFeet = new Length(0.0, LengthUnit.FEET);
        Length zeroInches = new Length(0.0, LengthUnit.INCHES);
        Length zeroYards = new Length(0.0, LengthUnit.YARDS);
        Length zeroCm = new Length(0.0, LengthUnit.CENTIMETERS);

        assertTrue(zeroFeet.equals(zeroInches) &&
                        zeroInches.equals(zeroYards) &&
                        zeroYards.equals(zeroCm),
                "All zero values should be equal across units");
    }

    /**
     * Test Case 28: differentClassComparison
     * Verifies type safety
     */
    @Test
    public void differentClassComparison() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        String notALength = "1.0";

        assertFalse(yard.equals(notALength),
                "Length object should not equal a String object");
    }
}