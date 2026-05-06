package main;

/**
 * QuantityMeasurementApp - UC9
 *
 * Demonstrates:
 * - Weight equality
 * - Weight conversion
 * - Weight addition
 *
 * Length functionality from UC8 remains supported.
 */
public class QuantityMeasurementApp {

    /**
     * Weight equality demo
     */
    public static boolean demonstrateWeightEquality(
            Weight weight1,
            Weight weight2) {

        boolean result = weight1.equals(weight2);

        System.out.println("The two weight measurements are "
                + (result ? "equal." : "not equal."));

        return result;
    }

    /**
     * Weight comparison demo using raw values.
     */
    public static boolean demonstrateWeightComparison(
            double value1,
            WeightUnit unit1,
            double value2,
            WeightUnit unit2) {

        Weight weight1 = new Weight(value1, unit1);
        Weight weight2 = new Weight(value2, unit2);

        return demonstrateWeightEquality(weight1, weight2);
    }

    /**
     * Weight conversion demo.
     */
    public static Weight demonstrateWeightConversion(
            double value,
            WeightUnit fromUnit,
            WeightUnit toUnit) {

        Weight weight = new Weight(value, fromUnit);

        return weight.convertTo(toUnit);
    }

    /**
     * Overloaded conversion demo.
     */
    public static Weight demonstrateWeightConversion(
            Weight weight,
            WeightUnit toUnit) {

        return weight.convertTo(toUnit);
    }

    /**
     * Weight addition demo (implicit target unit).
     */
    public static Weight demonstrateWeightAddition(
            Weight weight1,
            Weight weight2) {

        return weight1.add(weight2);
    }

    /**
     * Weight addition demo (explicit target unit).
     */
    public static Weight demonstrateWeightAddition(
            Weight weight1,
            Weight weight2,
            WeightUnit targetUnit) {

        return weight1.add(weight2, targetUnit);
    }

    public static void main(String[] args) {

        System.out.println("=== UC9 Weight Measurement Demo ===");

        Weight oneKg =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight thousandGram =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight pounds =
                new Weight(2.20462, WeightUnit.POUND);

        // Equality
        System.out.println(oneKg.equals(thousandGram));

        // Conversion
        System.out.println(
                oneKg.convertTo(WeightUnit.GRAM));

        // Addition
        System.out.println(
                oneKg.add(thousandGram));

        // Explicit target unit
        System.out.println(
                oneKg.add(thousandGram, WeightUnit.GRAM));

        // Pound conversion
        System.out.println(
                pounds.convertTo(WeightUnit.KILOGRAM));
    }
}