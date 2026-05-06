package test;

import main.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC10 Generic Quantity Tests
 */
public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    /**
     * LengthUnit implements IMeasurable
     */
    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {

        IMeasurable unit = LengthUnit.FEET;

        assertEquals(
                1.0,
                unit.getConversionFactor(),
                EPSILON);
    }

    /**
     * WeightUnit implements IMeasurable
     */
    @Test
    public void testIMeasurableInterface_WeightUnitImplementation() {

        IMeasurable unit = WeightUnit.KILOGRAM;

        assertEquals(
                1.0,
                unit.getConversionFactor(),
                EPSILON);
    }

    /**
     * Length equality
     */
    @Test
    public void testGenericQuantity_LengthOperations_Equality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    /**
     * Weight equality
     */
    @Test
    public void testGenericQuantity_WeightOperations_Equality() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(grams));
    }

    /**
     * Length conversion
     */
    @Test
    public void testGenericQuantity_LengthOperations_Conversion() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(
                12.0,
                inches.getValue(),
                EPSILON);
    }

    /**
     * Weight conversion
     */
    @Test
    public void testGenericQuantity_WeightOperations_Conversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(
                1000.0,
                grams.getValue(),
                EPSILON);
    }

    /**
     * Length addition
     */
    @Test
    public void testGenericQuantity_LengthOperations_Addition() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON);
    }

    /**
     * Weight addition
     */
    @Test
    public void testGenericQuantity_WeightOperations_Addition() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(grams, WeightUnit.KILOGRAM);

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON);
    }

    /**
     * Cross-category prevention
     */
    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(feet.equals(kg));
    }

    /**
     * Null unit validation
     */
    @Test
    public void testGenericQuantity_ConstructorValidation_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    /**
     * Invalid value validation
     */
    @Test
    public void testGenericQuantity_ConstructorValidation_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    /**
     * Addition all combinations
     */
    @Test
    public void testGenericQuantity_Addition_AllUnitCombinations() {

        Quantity<LengthUnit> feet =
                new Quantity<>(2.0, LengthUnit.FEET);

        Quantity<LengthUnit> yard =
                new Quantity<>(1.0, LengthUnit.YARDS);

        Quantity<LengthUnit> result =
                feet.add(yard, LengthUnit.FEET);

        assertEquals(
                5.0,
                result.getValue(),
                EPSILON);
    }

    /**
     * Generic demonstration equality
     */
    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(
                QuantityMeasurementApp.demonstrateEquality(
                        kg,
                        grams));
    }

    /**
     * Generic demonstration conversion
     */
    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                QuantityMeasurementApp.demonstrateConversion(
                        feet,
                        LengthUnit.INCHES);

        assertEquals(
                12.0,
                inches.getValue(),
                EPSILON);
    }

    /**
     * Generic demonstration addition
     */
    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> pound =
                new Quantity<>(2.20462, WeightUnit.POUND);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateAddition(
                        kg,
                        pound,
                        WeightUnit.KILOGRAM);

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON);
    }

    /**
     * HashCode consistency
     */
    @Test
    public void testHashCode_GenericQuantity_Consistency() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(
                feet.hashCode(),
                inches.hashCode());
    }

    /**
     * Immutability
     */
    @Test
    public void testImmutability_GenericQuantity() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(
                1.0,
                feet.getValue(),
                EPSILON);

        assertEquals(
                12.0,
                inches.getValue(),
                EPSILON);
    }

    /**
     * Round-trip conversion
     */
    @Test
    public void testRoundTripConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                kg.convertTo(WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                grams.convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                5.0,
                result.getValue(),
                EPSILON);
    }
}