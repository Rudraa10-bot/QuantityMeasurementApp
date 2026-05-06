package test;

import main.LengthUnit;
import main.QuantityMeasurementApp;
import main.QuantityMeasurementApp.Length;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * QuantityMeasurementAppTest - UC8
 *
 * Tests for standalone LengthUnit enum
 * and refactored QuantityLength design.
 *
 * @author Developer
 * @version 8.0
 */
public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    /**
     * Test 1:
     * LengthUnit.FEET constant
     */
    @Test
    public void testLengthUnitEnum_FeetConstant() {

        assertEquals(
                1.0,
                LengthUnit.FEET.getConversionFactor(),
                EPSILON);
    }

    /**
     * Test 2:
     * LengthUnit.INCHES constant
     */
    @Test
    public void testLengthUnitEnum_InchesConstant() {

        assertEquals(
                1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON);
    }

    /**
     * Test 3:
     * LengthUnit.YARDS constant
     */
    @Test
    public void testLengthUnitEnum_YardsConstant() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.getConversionFactor(),
                EPSILON);
    }

    /**
     * Test 4:
     * LengthUnit.CENTIMETERS constant
     */
    @Test
    public void testLengthUnitEnum_CentimetersConstant() {

        assertEquals(
                1.0 / 30.48,
                LengthUnit.CENTIMETERS.getConversionFactor(),
                EPSILON);
    }

    /**
     * Test 5:
     * FEET -> FEET conversion
     */
    @Test
    public void testConvertToBaseUnit_FeetToFeet() {

        assertEquals(
                5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                EPSILON);
    }

    /**
     * Test 6:
     * INCHES -> FEET conversion
     */
    @Test
    public void testConvertToBaseUnit_InchesToFeet() {

        assertEquals(
                1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON);
    }

    /**
     * Test 7:
     * YARDS -> FEET conversion
     */
    @Test
    public void testConvertToBaseUnit_YardsToFeet() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON);
    }

    /**
     * Test 8:
     * CENTIMETERS -> FEET conversion
     */
    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {

        assertEquals(
                1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPSILON);
    }

    /**
     * Test 9:
     * FEET <- FEET conversion
     */
    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {

        assertEquals(
                2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                EPSILON);
    }

    /**
     * Test 10:
     * FEET -> INCHES conversion
     */
    @Test
    public void testConvertFromBaseUnit_FeetToInches() {

        assertEquals(
                12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON);
    }

    /**
     * Test 11:
     * FEET -> YARDS conversion
     */
    @Test
    public void testConvertFromBaseUnit_FeetToYards() {

        assertEquals(
                1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPSILON);
    }

    /**
     * Test 12:
     * FEET -> CENTIMETERS conversion
     */
    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {

        assertEquals(
                30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                EPSILON);
    }

    /**
     * Test 13:
     * Equality after refactor
     */
    @Test
    public void testQuantityLengthRefactored_Equality() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    /**
     * Test 14:
     * convertTo after refactor
     */
    @Test
    public void testQuantityLengthRefactored_ConvertTo() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);

        assertEquals(
                LengthUnit.INCHES,
                result.getUnit());
    }

    /**
     * Test 15:
     * add() after refactor
     */
    @Test
    public void testQuantityLengthRefactored_Add() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);

        assertEquals(
                LengthUnit.FEET,
                result.getUnit());
    }

    /**
     * Test 16:
     * add() with target unit after refactor
     */
    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(inches, LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), EPSILON);

        assertEquals(
                LengthUnit.YARDS,
                result.getUnit());
    }

    /**
     * Test 17:
     * Null unit validation
     */
    @Test
    public void testQuantityLengthRefactored_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    /**
     * Test 18:
     * Invalid value validation
     */
    @Test
    public void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET));
    }

    /**
     * Test 19:
     * Round-trip conversion
     */
    @Test
    public void testRoundTripConversion_RefactoredDesign() {

        Length feet =
                new Length(5.0, LengthUnit.FEET);

        Length inches =
                feet.convertTo(LengthUnit.INCHES);

        Length backToFeet =
                inches.convertTo(LengthUnit.FEET);

        assertEquals(
                5.0,
                backToFeet.getValue(),
                EPSILON);
    }

    /**
     * Test 20:
     * Enum immutability
     */
    @Test
    public void testUnitImmutability() {

        LengthUnit unit = LengthUnit.FEET;

        assertEquals(
                1.0,
                unit.getConversionFactor(),
                EPSILON);
    }

    /**
     * Test 21:
     * Backward compatibility UC6 addition
     */
    @Test
    public void testBackwardCompatibility_UC6AdditionTests() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(inches);

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON);

        assertEquals(
                LengthUnit.FEET,
                result.getUnit());
    }

    /**
     * Test 22:
     * Backward compatibility UC7 target unit
     */
    @Test
    public void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(inches, LengthUnit.INCHES);

        assertEquals(
                24.0,
                result.getValue(),
                EPSILON);

        assertEquals(
                LengthUnit.INCHES,
                result.getUnit());
    }
}