package main;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // ---------------- LENGTH ----------------
        Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> sixInch = new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println("Subtraction (Implicit Target): 10 FEET - 6 INCH = "
                + tenFeet.subtract(sixInch)); // ~9.50 FEET after rounding in subtract()

        System.out.println("Subtraction (Explicit Target): 10 FEET - 6 INCH in INCH = "
                + tenFeet.subtract(sixInch, LengthUnit.INCH)); // 114 INCH

        System.out.println("Division: 24 INCH / 2 FEET = "
                + new Quantity<>(24.0, LengthUnit.INCH).divide(new Quantity<>(2.0, LengthUnit.FOOT)));

        // ---------------- WEIGHT ----------------
        Quantity<WeightUnit> tenKg = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> fiveThousandGram = new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("Subtraction: 10 KG - 5000 G = " + tenKg.subtract(fiveThousandGram));
        System.out.println("Division: 10 KG / 5 KG = "
                + tenKg.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        // ---------------- VOLUME ----------------
        Quantity<VolumeUnit> fiveL = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> fiveHundredMl = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("Subtraction: 5 L - 500 mL = " + fiveL.subtract(fiveHundredMl));
        System.out.println("Division: 5 L / 10 L = "
                + fiveL.divide(new Quantity<>(10.0, VolumeUnit.LITRE)));
    }

    // Existing demonstrateEquality / demonstrateConversion / demonstrateAddition remain (UC10-UC11)

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2) {
        return q1.subtract(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        return q1.subtract(q2, targetUnit);
    }

    public static <U extends IMeasurable> double demonstrateDivision(
            Quantity<U> q1, Quantity<U> q2) {
        return q1.divide(q2);
    }
}