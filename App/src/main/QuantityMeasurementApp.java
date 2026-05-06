package main;

/**
 * QuantityMeasurementApp - UC10
 *
 * Generic demonstration class.
 *
 * Supports:
 * - Equality
 * - Conversion
 * - Addition
 *
 * for ANY IMeasurable category.
 */
public class QuantityMeasurementApp {

    /**
     * Generic equality demonstration.
     */
    public static <U extends IMeasurable>
    boolean demonstrateEquality(
            Quantity<U> quantity1,
            Quantity<U> quantity2) {

        return quantity1.equals(quantity2);
    }

    /**
     * Generic conversion demonstration.
     */
    public static <U extends IMeasurable>
    Quantity<U> demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        return quantity.convertTo(targetUnit);
    }

    /**
     * Generic addition demonstration.
     */
    public static <U extends IMeasurable>
    Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2) {

        return quantity1.add(quantity2);
    }

    /**
     * Generic addition with target unit.
     */
    public static <U extends IMeasurable>
    Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2,
            U targetUnit) {

        return quantity1.add(quantity2, targetUnit);
    }

    public static void main(String[] args) {

        // Length demo
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println(feet.equals(inches));

        System.out.println(
                feet.convertTo(LengthUnit.INCHES));

        System.out.println(
                feet.add(inches, LengthUnit.FEET));

        // Weight demo
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println(kg.equals(grams));

        System.out.println(
                kg.convertTo(WeightUnit.GRAM));

        System.out.println(
                kg.add(grams, WeightUnit.KILOGRAM));
    }
}