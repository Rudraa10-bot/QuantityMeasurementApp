package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UC13: Centralized Arithmetic Logic (DRY) - Validation + Behavior")
class UC13QuantityRefactorTest {

    private static final double EPS = 1e-4;

    @Test
    @DisplayName("Null operand: add/subtract/divide all throw same exception message")
    void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FOOT);

        String addMsg = assertThrows(IllegalArgumentException.class, () -> q.add(null)).getMessage();
        String subMsg = assertThrows(IllegalArgumentException.class, () -> q.subtract(null)).getMessage();
        String divMsg = assertThrows(IllegalArgumentException.class, () -> q.divide(null)).getMessage();

        assertEquals(addMsg, subMsg);
        assertEquals(subMsg, divMsg);
        assertEquals("Other quantity cannot be null.", addMsg);
    }

    @Test
    @DisplayName("Null target unit for add/subtract throws consistent message")
    void testValidation_NullTargetUnit_AddSubtractReject() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FOOT);

        assertEquals("Target unit cannot be null.",
                assertThrows(IllegalArgumentException.class, () -> a.add(b, null)).getMessage());

        assertEquals("Target unit cannot be null.",
                assertThrows(IllegalArgumentException.class, () -> a.subtract(b, null)).getMessage());
    }

    @Test
    @DisplayName("Cross-category protection is consistent across operations (using raw types)")
    @SuppressWarnings({"rawtypes", "unchecked"})
    void testValidation_CrossCategory_ConsistentAcrossOperations_RawTypes() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        String addMsg = assertThrows(IllegalArgumentException.class, () -> length.add(weight)).getMessage();
        String subMsg = assertThrows(IllegalArgumentException.class, () -> length.subtract(weight)).getMessage();
        String divMsg = assertThrows(IllegalArgumentException.class, () -> length.divide(weight)).getMessage();

        assertEquals(addMsg, subMsg);
        assertEquals(subMsg, divMsg);
        assertEquals("Incompatible quantity categories.", addMsg);
    }

    @Test
    @DisplayName("Rounding: addition rounds to 2 decimals (1ft + 1in = 1.08ft)")
    void testRounding_Add_TwoDecimalPlaces() {
        Quantity<LengthUnit> ft1 = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> in1 = new Quantity<>(1.0, LengthUnit.INCH);

        Quantity<LengthUnit> result = ft1.add(in1); // implicit target = FOOT
        assertEquals(1.08, result.getValue(), EPS);
        assertEquals(LengthUnit.FOOT, result.getUnit());
    }

    @Test
    @DisplayName("Rounding: subtraction rounds to 2 decimals (1ft - 1in = 0.92ft)")
    void testRounding_Subtract_TwoDecimalPlaces() {
        Quantity<LengthUnit> ft1 = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> in1 = new Quantity<>(1.0, LengthUnit.INCH);

        Quantity<LengthUnit> result = ft1.subtract(in1);
        assertEquals(0.92, result.getValue(), EPS);
    }

    @Test
    @DisplayName("Division: returns raw double (no rounding enforced by Quantity)")
    void testDivision_NoRounding() {
        Quantity<LengthUnit> ft1 = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<LengthUnit> in7 = new Quantity<>(7.0, LengthUnit.INCH);

        // Expected ratio ~ 12/7 = 1.714285...
        double ratio = ft1.divide(in7);
        assertEquals(12.0 / 7.0, ratio, 1e-3);
        // If it were rounded to 2 decimals, it would be 1.71 and this would fail
        assertNotEquals(1.71, ratio, 1e-6);
    }

    @Test
    @DisplayName("Division by zero throws ArithmeticException with consistent message")
    void testDivision_ByZero_Throws() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> zero = new Quantity<>(0.0, LengthUnit.FOOT);

        ArithmeticException ex = assertThrows(ArithmeticException.class, () -> a.divide(zero));
        assertEquals("Division by zero quantity is not allowed.", ex.getMessage());
    }

    @Test
    @DisplayName("Immutability: operands unchanged after add/subtract/divide")
    void testImmutability_AllOperations() {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> add = a.add(b);
        Quantity<VolumeUnit> sub = a.subtract(b);
        double div = a.divide(new Quantity<>(10.0, VolumeUnit.LITRE));

        // originals unchanged
        assertEquals(5.0, a.getValue(), EPS);
        assertEquals(VolumeUnit.LITRE, a.getUnit());
        assertEquals(500.0, b.getValue(), EPS);
        assertEquals(VolumeUnit.MILLILITRE, b.getUnit());

        // sanity checks
        assertNotNull(add);
        assertNotNull(sub);
        assertEquals(0.5, div, EPS);
    }
}