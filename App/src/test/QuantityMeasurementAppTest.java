package test;

import main.Weight;
import main.WeightUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UC9 Test Class
 */
public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    /**
     * KG to KG equality
     */
    @Test
    public void testEquality_KilogramToKilogram_SameValue() {

        Weight kg1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight kg2 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(kg1.equals(kg2));
    }

    /**
     * KG inequality
     */
    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {

        Weight kg1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight kg2 =
                new Weight(2.0, WeightUnit.KILOGRAM);

        assertFalse(kg1.equals(kg2));
    }

    /**
     * KG equals Gram
     */
    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    /**
     * Gram equals KG
     */
    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {

        Weight gram =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(gram.equals(kg));
    }

    /**
     * Weight vs null
     */
    @Test
    public void testEquality_NullComparison() {

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        assertFalse(kg.equals(null));
    }

    /**
     * Same reference
     */
    @Test
    public void testEquality_SameReference() {

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(kg.equals(kg));
    }

    /**
     * Null unit validation
     */
    @Test
    public void testEquality_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Weight(1.0, null));
    }

    /**
     * Transitive property
     */
    @Test
    public void testEquality_TransitiveProperty() {

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight pound =
                new Weight(2.20462, WeightUnit.POUND);

        assertTrue(kg.equals(gram));
        assertTrue(gram.equals(pound));
        assertTrue(kg.equals(pound));
    }

    /**
     * Zero values
     */
    @Test
    public void testEquality_ZeroValue() {

        Weight kg =
                new Weight(0.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(0.0, WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    /**
     * Negative weights
     */
    @Test
    public void testEquality_NegativeWeight() {

        Weight kg =
                new Weight(-1.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(-1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    /**
     * Large values
     */
    @Test
    public void testEquality_LargeWeightValue() {

        Weight gram =
                new Weight(1000000.0, WeightUnit.GRAM);

        Weight kg =
                new Weight(1000.0, WeightUnit.KILOGRAM);

        assertTrue(gram.equals(kg));
    }

    /**
     * Small values
     */
    @Test
    public void testEquality_SmallWeightValue() {

        Weight kg =
                new Weight(0.001, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(1.0, WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    /**
     * Pound to KG conversion
     */
    @Test
    public void testConversion_PoundToKilogram() {

        Weight pound =
                new Weight(2.20462, WeightUnit.POUND);

        Weight kg =
                pound.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, kg.getValue(), EPSILON);
    }

    /**
     * KG to Pound conversion
     */
    @Test
    public void testConversion_KilogramToPound() {

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight pound =
                kg.convertTo(WeightUnit.POUND);

        assertEquals(2.20462, pound.getValue(), EPSILON);
    }

    /**
     * Same unit conversion
     */
    @Test
    public void testConversion_SameUnit() {

        Weight kg =
                new Weight(5.0, WeightUnit.KILOGRAM);

        Weight result =
                kg.convertTo(WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    /**
     * Round-trip conversion
     */
    @Test
    public void testConversion_RoundTrip() {

        Weight kg =
                new Weight(1.5, WeightUnit.KILOGRAM);

        Weight gram =
                kg.convertTo(WeightUnit.GRAM);

        Weight result =
                gram.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    /**
     * Addition same unit
     */
    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {

        Weight kg1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight kg2 =
                new Weight(2.0, WeightUnit.KILOGRAM);

        Weight result =
                kg1.add(kg2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    /**
     * Addition cross-unit
     */
    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight result =
                kg.add(gram);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    /**
     * Pound + KG
     */
    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {

        Weight pound =
                new Weight(2.20462, WeightUnit.POUND);

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight result =
                pound.add(kg);

        assertEquals(4.40924, result.getValue(), EPSILON);
    }

    /**
     * Explicit target unit
     */
    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight result =
                kg.add(gram, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);

        assertEquals(
                WeightUnit.GRAM,
                result.getUnit());
    }

    /**
     * Addition commutativity
     */
    @Test
    public void testAddition_Commutativity() {

        Weight kg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight result1 =
                kg.add(gram);

        Weight result2 =
                gram.add(kg);

        assertTrue(result1.equals(result2));
    }

    /**
     * Addition with zero
     */
    @Test
    public void testAddition_WithZero() {

        Weight kg =
                new Weight(5.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(0.0, WeightUnit.GRAM);

        Weight result =
                kg.add(gram);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    /**
     * Addition with negative values
     */
    @Test
    public void testAddition_NegativeValues() {

        Weight kg =
                new Weight(5.0, WeightUnit.KILOGRAM);

        Weight gram =
                new Weight(-2000.0, WeightUnit.GRAM);

        Weight result =
                kg.add(gram);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    /**
     * Large additions
     */
    @Test
    public void testAddition_LargeValues() {

        Weight kg1 =
                new Weight(1e6, WeightUnit.KILOGRAM);

        Weight kg2 =
                new Weight(1e6, WeightUnit.KILOGRAM);

        Weight result =
                kg1.add(kg2);

        assertEquals(2e6, result.getValue(), EPSILON);
    }
}