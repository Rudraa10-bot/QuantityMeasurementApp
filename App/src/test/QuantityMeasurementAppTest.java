package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UC11: Volume Measurement Equality, Conversion, and Addition")
class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    // ═══════════════════════════════════════════════════════════════
    // BACKWARD COMPATIBILITY: Length Tests (UC1 – UC5)
    // ═══════════════════════════════════════════════════════════════

    @Test
    @DisplayName("lengthFeetEqualsInches: 1 foot == 12 inches")
    void lengthFeetEqualsInches() {
        Quantity<LengthUnit> foot  = new Quantity<>(1.0,  LengthUnit.FOOT);
        Quantity<LengthUnit> inch  = new Quantity<>(12.0, LengthUnit.INCH);
        assertTrue(foot.equals(inch));
    }

    @Test
    @DisplayName("lengthYardsEqualsFeet: 1 yard == 3 feet")
    void lengthYardsEqualsFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FOOT);
        assertTrue(yard.equals(feet));
    }

    @Test
    @DisplayName("lengthFeetEqualsInches: 1 foot == 12 inches (symmetry)")
    void lengthFeetEqualsInchesSymmetry() {
        Quantity<LengthUnit> foot = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCH);
        assertTrue(inch.equals(foot));
    }

    @Test
    @DisplayName("addLengthYardsAndFeet: 1 yard + 1 foot = 4 feet")
    void addLengthYardsAndFeet() {
        Quantity<LengthUnit> yard   = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> foot   = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> result = yard.add(foot, LengthUnit.FOOT);
        assertEquals(4.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FOOT, result.getUnit());
    }

    @Test
    @DisplayName("addLengthFeetAndInches: 1 foot + 12 inches = 2 feet")
    void addLengthFeetAndInches() {
        Quantity<LengthUnit> foot   = new Quantity<>(1.0,  LengthUnit.FOOT);
        Quantity<LengthUnit> inch   = new Quantity<>(12.0, LengthUnit.INCH);
        Quantity<LengthUnit> result = foot.add(inch, LengthUnit.FOOT);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("convertLengthFeetToInches: 1 foot = 12 inches")
    void convertLengthFeetToInches() {
        Quantity<LengthUnit> foot   = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> result = foot.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("convertLengthYardsToFeet: 1 yard = 3 feet")
    void convertLengthYardsToFeet() {
        Quantity<LengthUnit> yard   = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> result = yard.convertTo(LengthUnit.FOOT);
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    // ═══════════════════════════════════════════════════════════════
    // BACKWARD COMPATIBILITY: Weight Tests (UC6 – UC10)
    // ═══════════════════════════════════════════════════════════════

    @Test
    @DisplayName("weightKilogramEqualsGrams: 1 kg == 1000 g")
    void weightKilogramEqualsGrams() {
        Quantity<WeightUnit> kg   = new Quantity<>(1.0,    WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(gram));
    }

    @Test
    @DisplayName("weightPoundEqualsGrams: 1 pound == ~453.592 g")
    void weightPoundEqualsGrams() {
        Quantity<WeightUnit> pound = new Quantity<>(1.0,     WeightUnit.POUND);
        Quantity<WeightUnit> gram  = new Quantity<>(453.592, WeightUnit.GRAM);
        assertTrue(pound.equals(gram));
    }

    @Test
    @DisplayName("convertWeightKilogramsToGrams: 1 kg = 1000 g")
    void convertWeightKilogramsToGrams() {
        Quantity<WeightUnit> kg     = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    @DisplayName("addWeightKilogramsAndGrams: 1 kg + 1000 g = 2 kg")
    void addWeightKilogramsAndGrams() {
        Quantity<WeightUnit> kg     = new Quantity<>(1.0,    WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gram   = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.add(gram, WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    @DisplayName("addWeightTonnesAndKilograms: 1 tonne + 1000 kg = 2 tonnes")
    void addWeightTonnesAndKilograms() {
        Quantity<WeightUnit> tonne  = new Quantity<>(1.0,    WeightUnit.TONNE);
        Quantity<WeightUnit> kg     = new Quantity<>(1000.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = tonne.add(kg, WeightUnit.TONNE);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("preventCrossTypeAdditionLengthVsWeight")
    void preventCrossTypeAdditionLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        // Compiler-level type safety: different generic types
        // Runtime: equals() returns false
        assertFalse(length.equals(weight));
    }

    // ═══════════════════════════════════════════════════════════════
    // UC11: VOLUME — EQUALITY TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testEquality_LitreToLitre_SameValue: 1L == 1L")
    void testEquality_LitreToLitre_SameValue() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("testEquality_LitreToLitre_DifferentValue: 1L != 2L")
    void testEquality_LitreToLitre_DifferentValue() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertFalse(a.equals(b));
    }

    @Test
    @DisplayName("testEquality_MillilitreToMillilitre: 500mL == 500mL")
    void testEquality_MillilitreToMillilitre() {
        Quantity<VolumeUnit> a = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> b = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("testEquality_GallonToGallon: 1gal == 1gal")
    void testEquality_GallonToGallon() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> b = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("testEquality_LitreToMillilitre_EquivalentValue: 1L == 1000mL")
    void testEquality_LitreToMillilitre_EquivalentValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml    = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(litre.equals(ml));
    }

    @Test
    @DisplayName("testEquality_MillilitreToLitre_EquivalentValue: 1000mL == 1L (symmetry)")
    void testEquality_MillilitreToLitre_EquivalentValue() {
        Quantity<VolumeUnit> ml    = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre = new Quantity<>(1.0,    VolumeUnit.LITRE);
        assertTrue(ml.equals(litre));
    }

    @Test
    @DisplayName("testEquality_LitreToGallon_EquivalentValue: 3.78541L == 1gal")
    void testEquality_LitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> litre  = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0,     VolumeUnit.GALLON);
        assertTrue(litre.equals(gallon));
    }

    @Test
    @DisplayName("testEquality_GallonToLitre_EquivalentValue: 1gal == 3.78541L (symmetry)")
    void testEquality_GallonToLitre_EquivalentValue() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0,     VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre  = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertTrue(gallon.equals(litre));
    }

    @Test
    @DisplayName("testEquality_500mLToHalfLitre: 500mL == 0.5L")
    void testEquality_500mLToHalfLitre() {
        Quantity<VolumeUnit> ml    = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre = new Quantity<>(0.5,   VolumeUnit.LITRE);
        assertTrue(ml.equals(litre));
    }

    @Test
    @DisplayName("testEquality_VolumeVsLength_Incompatible: 1L != 1ft")
    void testEquality_VolumeVsLength_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FOOT);
        assertFalse(volume.equals(length));
    }

    @Test
    @DisplayName("testEquality_VolumeVsWeight_Incompatible: 1L != 1kg")
    void testEquality_VolumeVsWeight_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(volume.equals(weight));
    }

    @Test
    @DisplayName("testEquality_NullComparison: 1L.equals(null) == false")
    void testEquality_NullComparison() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertFalse(volume.equals(null));
    }

    @Test
    @DisplayName("testEquality_SameReference: reflexive property")
    void testEquality_SameReference() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(volume.equals(volume));
    }

    @Test
    @DisplayName("testEquality_NullUnit: constructor throws IllegalArgumentException")
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, (VolumeUnit) null));
    }

    @Test
    @DisplayName("testEquality_TransitiveProperty: A==B and B==C implies A==C")
    void testEquality_TransitiveProperty() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> c = new Quantity<>(1.0,    VolumeUnit.LITRE);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    @DisplayName("testEquality_ZeroValue: 0L == 0mL")
    void testEquality_ZeroValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml    = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        assertTrue(litre.equals(ml));
    }

    @Test
    @DisplayName("testEquality_NegativeVolume: -1L == -1000mL")
    void testEquality_NegativeVolume() {
        Quantity<VolumeUnit> litre = new Quantity<>(-1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml    = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);
        assertTrue(litre.equals(ml));
    }

    @Test
    @DisplayName("testEquality_LargeVolumeValue: 1000000mL == 1000L")
    void testEquality_LargeVolumeValue() {
        Quantity<VolumeUnit> ml    = new Quantity<>(1_000_000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre = new Quantity<>(1000.0,      VolumeUnit.LITRE);
        assertTrue(ml.equals(litre));
    }

    @Test
    @DisplayName("testEquality_SmallVolumeValue: 0.001L == 1mL")
    void testEquality_SmallVolumeValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml    = new Quantity<>(1.0,   VolumeUnit.MILLILITRE);
        assertTrue(litre.equals(ml));
    }

    @Test
    @DisplayName("testEquality_DifferentVolumes: 1L != 2L")
    void testEquality_DifferentVolumes() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertFalse(a.equals(b));
    }

    // ═══════════════════════════════════════════════════════════════
    // UC11: VOLUME — CONVERSION TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testConversion_LitreToMillilitre: 1L -> 1000mL")
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> litre  = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    @DisplayName("testConversion_MillilitreToLitre: 1000mL -> 1L")
    void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> ml     = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = ml.convertTo(VolumeUnit.LITRE);
        assertEquals(1.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    @DisplayName("testConversion_GallonToLitre: 1gal -> 3.78541L")
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    @DisplayName("testConversion_LitreToGallon: 3.78541L -> ~1gal")
    void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> litre  = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    @DisplayName("testConversion_MillilitreToGallon: 1000mL -> ~0.264172gal")
    void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> ml     = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = ml.convertTo(VolumeUnit.GALLON);
        assertEquals(0.264172, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    @DisplayName("testConversion_GallonToMillilitre: 1gal -> 3785.41mL")
    void testConversion_GallonToMillilitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(3785.41, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    @DisplayName("testConversion_SameUnit: 5L -> 5L")
    void testConversion_SameUnit() {
        Quantity<VolumeUnit> litre  = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.LITRE);
        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    @DisplayName("testConversion_ZeroValue: 0L -> 0mL")
    void testConversion_ZeroValue() {
        Quantity<VolumeUnit> litre  = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testConversion_NegativeValue: -1L -> -1000mL")
    void testConversion_NegativeValue() {
        Quantity<VolumeUnit> litre  = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testConversion_RoundTrip: 1.5L -> mL -> L == 1.5L")
    void testConversion_RoundTrip() {
        Quantity<VolumeUnit> original = new Quantity<>(1.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result   = original
                .convertTo(VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(1.5, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testConversion_2GallonToLitre: 2gal -> ~7.57082L")
    void testConversion_2GallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(2.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);
        assertEquals(7.57082, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testConversion_500mLToGallon: 500mL -> ~0.132086gal")
    void testConversion_500mLToGallon() {
        Quantity<VolumeUnit> ml     = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = ml.convertTo(VolumeUnit.GALLON);
        assertEquals(0.132086, result.getValue(), EPSILON);
    }

    // ═══════════════════════════════════════════════════════════════
    // UC11: VOLUME — ADDITION TESTS (IMPLICIT TARGET UNIT)
    // ═══════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testAddition_SameUnit_LitrePlusLitre: 1L + 2L = 3L")
    void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> a      = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b      = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.add(b);
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    @DisplayName("testAddition_SameUnit_MillilitrePlusMillilitre: 500mL + 500mL = 1000mL")
    void testAddition_SameUnit_MillilitrePlusMillilitre() {
        Quantity<VolumeUnit> a      = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> b      = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = a.add(b);
        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    @DisplayName("testAddition_CrossUnit_LitrePlusMillilitre: 1L + 1000mL = 2L")
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> litre  = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml     = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(ml);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    @DisplayName("testAddition_CrossUnit_MillilitrePlusLitre: 1000mL + 1L = 2000mL")
    void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> ml     = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre  = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = ml.add(litre);
        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    @DisplayName("testAddition_CrossUnit_GallonPlusLitre: 1gal + 3.78541L = ~2gal")
    void testAddition_CrossUnit_GallonPlusLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0,     VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre  = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = gallon.add(litre);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    @DisplayName("testAddition_500mLPlusHalfLitre: 500mL + 0.5L = 1000mL")
    void testAddition_500mLPlusHalfLitre() {
        Quantity<VolumeUnit> ml     = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre  = new Quantity<>(0.5,   VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = ml.add(litre);
        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    // ═══════════════════════════════════════════════════════════════
    // UC11: VOLUME — ADDITION TESTS (EXPLICIT TARGET UNIT)
    // ═══════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testAddition_ExplicitTargetUnit_Litre: 1L + 1000mL = 2L")
    void testAddition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> litre  = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml     = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.LITRE);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    @DisplayName("testAddition_ExplicitTargetUnit_Millilitre: 1L + 1000mL = 2000mL")
    void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> litre  = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml     = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.MILLILITRE);
        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    @DisplayName("testAddition_ExplicitTargetUnit_Gallon: 3.78541L + 3.78541L = ~2gal")
    void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> a      = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b      = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.add(b, VolumeUnit.GALLON);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    @DisplayName("testAddition_1Gal_Plus_3785L_InGallon: ~2 gallon")
    void testAddition_1Gal_Plus_3785L_InGallon() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0,     VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre  = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = gallon.add(litre, VolumeUnit.GALLON);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testAddition_500mL_Plus_1L_InGallon: ~0.396258gal")
    void testAddition_500mL_Plus_1L_InGallon() {
        Quantity<VolumeUnit> ml     = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre  = new Quantity<>(1.0,   VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = ml.add(litre, VolumeUnit.GALLON);
        assertEquals(0.396258, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testAddition_2L_Plus_4Gal_InLitre: ~17.14164L")
    void testAddition_2L_Plus_4Gal_InLitre() {
        Quantity<VolumeUnit> litre  = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(4.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = litre.add(gallon, VolumeUnit.LITRE);
        assertEquals(17.14164, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testAddition_Commutativity: A+B == B+A (in same target unit)")
    void testAddition_Commutativity() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml    = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> ab = litre.add(ml, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ba = ml.add(litre, VolumeUnit.LITRE);

        assertEquals(ab.getValue(), ba.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testAddition_WithZero: 5L + 0mL = 5L")
    void testAddition_WithZero() {
        Quantity<VolumeUnit> litre  = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> zero   = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(zero);
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testAddition_NegativeValues: 5L + (-2000mL) = 3L")
    void testAddition_NegativeValues() {
        Quantity<VolumeUnit> litre  = new Quantity<>(5.0,     VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml     = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(ml);
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testAddition_LargeValues: 1e6L + 1e6L = 2e6L")
    void testAddition_LargeValues() {
        Quantity<VolumeUnit> a      = new Quantity<>(1e6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b      = new Quantity<>(1e6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.add(b);
        assertEquals(2e6, result.getValue(), 1.0); // larger tolerance for large values
    }

    @Test
    @DisplayName("testAddition_SmallValues: 0.001L + 0.002L = ~0.003L")
    void testAddition_SmallValues() {
        Quantity<VolumeUnit> a      = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b      = new Quantity<>(0.002, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.add(b);
        assertEquals(0.003, result.getValue(), EPSILON);
    }

    // ═══════════════════════════════════════════════════════════════
    // UC11: VOLUME — VolumeUnit ENUM METHOD TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testVolumeUnitEnum_LitreConstant: conversion factor == 1.0")
    void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
    }

    @Test
    @DisplayName("testVolumeUnitEnum_MillilitreConstant: conversion factor == 0.001")
    void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
    }

    @Test
    @DisplayName("testVolumeUnitEnum_GallonConstant: conversion factor == 3.78541")
    void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
    }

    @Test
    @DisplayName("testConvertToBaseUnit_LitreToLitre: 5.0 -> 5.0")
    void testConvertToBaseUnit_LitreToLitre() {
        assertEquals(5.0, VolumeUnit.LITRE.convertToBaseUnit(5.0), EPSILON);
    }

    @Test
    @DisplayName("testConvertToBaseUnit_MillilitreToLitre: 1000.0 -> 1.0")
    void testConvertToBaseUnit_MillilitreToLitre() {
        assertEquals(1.0, VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    @DisplayName("testConvertToBaseUnit_GallonToLitre: 1.0 -> 3.78541")
    void testConvertToBaseUnit_GallonToLitre() {
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    @DisplayName("testConvertFromBaseUnit_LitreToLitre: 2.0 -> 2.0")
    void testConvertFromBaseUnit_LitreToLitre() {
        assertEquals(2.0, VolumeUnit.LITRE.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    @DisplayName("testConvertFromBaseUnit_LitreToMillilitre: 1.0 -> 1000.0")
    void testConvertFromBaseUnit_LitreToMillilitre() {
        assertEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    @DisplayName("testConvertFromBaseUnit_LitreToGallon: 3.78541 -> ~1.0")
    void testConvertFromBaseUnit_LitreToGallon() {
        assertEquals(1.0, VolumeUnit.GALLON.convertFromBaseUnit(3.78541), EPSILON);
    }

    // ═══════════════════════════════════════════════════════════════
    // UC11: INTEGRATION, SCALABILITY & GENERIC CONSISTENCY TESTS
    // ═══════════════════════════════════════════════════════════════

    @Test
    @DisplayName("testGenericQuantity_VolumeOperations_Consistency")
    void testGenericQuantity_VolumeOperations_Consistency() {
        // Generic Quantity<U> works identically for volume
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        // equality
        assertTrue(v1.equals(v2));
        // conversion
        Quantity<VolumeUnit> converted = v1.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, converted.getValue(), EPSILON);
        // addition
        Quantity<VolumeUnit> sum = v1.add(v2);
        assertEquals(2.0, sum.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, sum.getUnit());
    }

    @Test
    @DisplayName("testScalability_VolumeIntegration: no special handling needed")
    void testScalability_VolumeIntegration() {
        // VolumeUnit integrates seamlessly – same code path as Length & Weight
        Quantity<VolumeUnit> v = new Quantity<>(2.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> r = v.convertTo(VolumeUnit.LITRE);
        assertEquals(7.57082, r.getValue(), EPSILON);
    }

    @Test
    @DisplayName("testBackwardCompatibility_AllUC1Through10Tests: length & weight unaffected")
    void testBackwardCompatibility_AllUC1Through10Tests() {
        // Length still works
        Quantity<LengthUnit> foot  = new Quantity<>(1.0,  LengthUnit.FOOT);
        Quantity<LengthUnit> inch  = new Quantity<>(12.0, LengthUnit.INCH);
        assertTrue(foot.equals(inch));

        // Weight still works
        Quantity<WeightUnit> kg   = new Quantity<>(1.0,    WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(gram));

        // Volume works too
        Quantity<VolumeUnit> litre = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml    = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(litre.equals(ml));

        // Cross-category prevention still holds
        assertFalse(foot.equals(kg));
        assertFalse(litre.equals(foot));
        assertFalse(litre.equals(kg));
    }

    @Test
    @DisplayName("testEquality_MillilitreToGallon: 1000mL == ~0.264172gal")
    void testEquality_MillilitreToGallon() {
        Quantity<VolumeUnit> ml     = new Quantity<>(1000.0,   VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(0.264172, VolumeUnit.GALLON);
        assertTrue(ml.equals(gallon));
    }

    @Test
    @DisplayName("testImmutability_ConversionDoesNotModifyOriginal")
    void testImmutability_ConversionDoesNotModifyOriginal() {
        Quantity<VolumeUnit> original = new Quantity<>(1.0, VolumeUnit.LITRE);
        original.convertTo(VolumeUnit.MILLILITRE);
        // Original must remain unchanged
        assertEquals(1.0,           original.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, original.getUnit());
    }

    @Test
    @DisplayName("testImmutability_AdditionDoesNotModifyOriginals")
    void testImmutability_AdditionDoesNotModifyOriginals() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        a.add(b);
        assertEquals(1.0,              a.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, a.getUnit());
        assertEquals(1000.0,                b.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, b.getUnit());
    }

    @Test
    @DisplayName("testTypeConsistency_HashCodeWithEquals")
    void testTypeConsistency_HashCodeWithEquals() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        // If equals() is true, hashCode() must be equal
        assertTrue(a.equals(b));
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("preventCrossTypeComparisonLengthVsWeight")
    void preventCrossTypeComparisonLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight));
    }

    @Test
    @DisplayName("addVolumeLitersAndMilliliters: integration demo")
    void addVolumeLitersAndMilliliters() {
        Quantity<VolumeUnit> litre  = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml     = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.LITRE);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("volumeLitreEqualsMilliliters: 1L == 1000mL")
    void volumeLitreEqualsMilliliters() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml    = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(litre.equals(ml));
    }

    @Test
    @DisplayName("convertVolumeLitersToMilliliters: 1L -> 1000mL")
    void convertVolumeLitersToMilliliters() {
        Quantity<VolumeUnit> litre  = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), EPSILON);
    }
}