import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UC12: Subtraction and Division Operations")
class QuantityMeasurementAppTest {

    private static final double EPS = 1e-4;

    // -------------------- SUBTRACTION --------------------

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FOOT);

        Quantity<LengthUnit> result = a.subtract(b);
        assertEquals(5.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FOOT, result.getUnit());
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches_ImplicitTarget() {
        Quantity<LengthUnit> feet10 = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> inch6  = new Quantity<>(6.0, LengthUnit.INCH);

        // Expected: 10 - 0.5 = 9.5 feet (rounded to 2 decimals inside subtract)
        Quantity<LengthUnit> result = feet10.subtract(inch6);
        assertEquals(9.5, result.getValue(), EPS);
        assertEquals(LengthUnit.FOOT, result.getUnit());
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> feet10 = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> inch6  = new Quantity<>(6.0, LengthUnit.INCH);

        // 10 feet = 120 inches; 120 - 6 = 114 inches
        Quantity<LengthUnit> result = feet10.subtract(inch6, LengthUnit.INCH);
        assertEquals(114.0, result.getValue(), EPS);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> a = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(3.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = a.subtract(b);
        assertEquals(7.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = a.subtract(b, VolumeUnit.MILLILITRE);
        assertEquals(3000.0, result.getValue(), EPS);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<WeightUnit> a = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = a.subtract(b);
        assertEquals(-3.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_ResultingInZero_CrossUnit() {
        Quantity<LengthUnit> feet10  = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> inch120 = new Quantity<>(120.0, LengthUnit.INCH);

        Quantity<LengthUnit> result = feet10.subtract(inch120);
        assertEquals(0.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.INCH);

        Quantity<LengthUnit> result = a.subtract(b);
        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(-2.0, LengthUnit.FOOT);

        Quantity<LengthUnit> result = a.subtract(b);
        assertEquals(7.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FOOT);

        assertEquals(5.0, a.subtract(b).getValue(), EPS);
        assertEquals(-5.0, b.subtract(a).getValue(), EPS);
    }

    @Test
    void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> result = a
                .subtract(new Quantity<>(2.0, LengthUnit.FOOT))
                .subtract(new Quantity<>(1.0, LengthUnit.FOOT));

        assertEquals(7.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_NullOperand_Throws() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        assertThrows(IllegalArgumentException.class, () -> a.subtract(null));
    }

    @Test
    void testSubtraction_NullTargetUnit_Throws() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FOOT);
        assertThrows(IllegalArgumentException.class, () -> a.subtract(b, null));
    }

    // Raw type test to verify runtime cross-category protection (compile-time generics normally prevents)
    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    void testSubtraction_CrossCategory_RawTypes_Throws() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> length.subtract(weight));
    }

    // -------------------- DIVISION --------------------

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FOOT);

        assertEquals(5.0, a.divide(b), EPS);
    }

    @Test
    void testDivision_CrossUnit_InchesDividedByFeet() {
        Quantity<LengthUnit> inch24 = new Quantity<>(24.0, LengthUnit.INCH);
        Quantity<LengthUnit> feet2  = new Quantity<>(2.0, LengthUnit.FOOT);

        // 24 inches == 2 feet -> ratio ~1
        assertEquals(1.0, inch24.divide(feet2), 1e-3);
    }

    @Test
    void testDivision_RatioGreaterThanOne() {
        Quantity<WeightUnit> a = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertEquals(2.0, a.divide(b), EPS);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(10.0, VolumeUnit.LITRE);
        assertEquals(0.5, a.divide(b), EPS);
    }

    @Test
    void testDivision_RatioEqualToOne_CrossUnit() {
        Quantity<VolumeUnit> a = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(1.0, a.divide(b), EPS);
    }

    @Test
    void testDivision_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FOOT);

        assertEquals(2.0, a.divide(b), EPS);
        assertEquals(0.5, b.divide(a), EPS);
    }

    @Test
    void testDivision_ByZero_Throws() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> zero = new Quantity<>(0.0, LengthUnit.FOOT);

        assertThrows(ArithmeticException.class, () -> a.divide(zero));
    }

    @Test
    void testDivision_NullOperand_Throws() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        assertThrows(IllegalArgumentException.class, () -> a.divide(null));
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    void testDivision_CrossCategory_RawTypes_Throws() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> length.divide(weight));
    }

    // -------------------- IMMUTABILITY + INTEGRATION --------------------

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);

        Quantity<LengthUnit> result = a.subtract(b);

        assertEquals(10.0, a.getValue(), EPS);
        assertEquals(LengthUnit.FOOT, a.getUnit());
        assertEquals(6.0, b.getValue(), EPS);
        assertEquals(LengthUnit.INCH, b.getUnit());

        assertEquals(9.5, result.getValue(), EPS);
    }

    @Test
    void testDivision_Immutability() {
        Quantity<WeightUnit> a = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        double ratio = a.divide(b);

        assertEquals(10.0, a.getValue(), EPS);
        assertEquals(5.0, b.getValue(), EPS);
        assertEquals(2.0, ratio, EPS);
    }

    @Test
    void testSubtractionAddition_InverseRelationship() {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> back = a.add(b).subtract(b);
        // subtract() rounds to 2 decimals; still should be ~5.00 L
        assertEquals(5.0, back.getValue(), EPS);
        assertEquals(VolumeUnit.LITRE, back.getUnit());
    }
}