package main;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("  Quantity Measurement Application");
        System.out.println("========================================\n");

        // ── LENGTH DEMONSTRATIONS ─────────────────────────────────
        System.out.println("--- LENGTH MEASUREMENTS ---");
        Quantity<LengthUnit> foot1   = new Quantity<>(1.0,  LengthUnit.FOOT);
        Quantity<LengthUnit> inch12  = new Quantity<>(12.0, LengthUnit.INCH);
        Quantity<LengthUnit> yard1   = new Quantity<>(1.0,  LengthUnit.YARD);
        Quantity<LengthUnit> foot3   = new Quantity<>(3.0,  LengthUnit.FOOT);

        demonstrateEquality(foot1, inch12);
        demonstrateEquality(yard1, foot3);
        demonstrateConversion(foot1, LengthUnit.INCH);
        demonstrateAddition(foot1, inch12, LengthUnit.FOOT);
        System.out.println();

        // ── WEIGHT DEMONSTRATIONS ─────────────────────────────────
        System.out.println("--- WEIGHT MEASUREMENTS ---");
        Quantity<WeightUnit> kg1     = new Quantity<>(1.0,    WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g1000   = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> tonne1  = new Quantity<>(1.0,    WeightUnit.TONNE);

        demonstrateEquality(kg1, g1000);
        demonstrateConversion(kg1, WeightUnit.GRAM);
        demonstrateAddition(kg1, g1000, WeightUnit.KILOGRAM);
        System.out.println();

        // ── VOLUME DEMONSTRATIONS ─────────────────────────────────
        System.out.println("--- VOLUME MEASUREMENTS ---");

        Quantity<VolumeUnit> volume1 = new Quantity<>(1.0,    VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume3 = new Quantity<>(1.0,    VolumeUnit.GALLON);

        // Equality
        System.out.println("== Equality Comparisons ==");
        demonstrateEquality(volume1, volume2);   // true: 1 L == 1000 mL
        demonstrateEquality(volume1, volume3);   // false: 1 L != 1 Gallon
        demonstrateEquality(volume3, volume1);   // false: 1 Gallon != 1 L

        Quantity<VolumeUnit> gallon_in_litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        demonstrateEquality(volume3, gallon_in_litre); // true: 1 Gallon == 3.78541 L

        Quantity<VolumeUnit> ml500  = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> l05    = new Quantity<>(0.5,   VolumeUnit.LITRE);
        demonstrateEquality(ml500, l05);  // true: 500 mL == 0.5 L
        System.out.println();

        // Conversion
        System.out.println("== Unit Conversions ==");
        demonstrateConversion(volume1, VolumeUnit.MILLILITRE); // 1 L  -> 1000 mL
        demonstrateConversion(volume3, VolumeUnit.LITRE);      // 1 gal -> 3.78541 L
        demonstrateConversion(volume2, VolumeUnit.GALLON);     // 1000 mL -> ~0.264172 gal
        demonstrateConversion(new Quantity<>(0.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);
        demonstrateConversion(volume1, VolumeUnit.LITRE);      // same unit
        System.out.println();

        // Addition (implicit target unit)
        System.out.println("== Addition (Implicit Target Unit) ==");
        demonstrateAddition(volume1, volume2, VolumeUnit.LITRE); // 1L + 1000mL = 2L (explicit here for demo)
        Quantity<VolumeUnit> litre2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        System.out.println("1L.add(2L) = " + volume1.add(litre2));                     // 3L
        System.out.println("1L.add(1000mL) = " + volume1.add(volume2));               // 2L
        System.out.println("500mL.add(0.5L) = " + ml500.add(l05));                    // 1000mL
        Quantity<VolumeUnit> gallon2     = new Quantity<>(2.0,     VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre378541 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        System.out.println("2gal.add(3.78541L) = " + gallon2.add(litre378541));       // ~3 gal
        System.out.println();

        // Addition (explicit target unit)
        System.out.println("== Addition (Explicit Target Unit) ==");
        System.out.println("1L.add(1000mL, ML) = "
                + volume1.add(volume2, VolumeUnit.MILLILITRE));                        // 2000 mL
        System.out.println("1gal.add(3.78541L, GAL) = "
                + volume3.add(litre378541, VolumeUnit.GALLON));                        // ~2 gal
        System.out.println("500mL.add(1L, GAL) = "
                + ml500.add(volume1, VolumeUnit.GALLON));                              // ~0.396258 gal
        Quantity<VolumeUnit> litre2_ = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon4 = new Quantity<>(4.0, VolumeUnit.GALLON);
        System.out.println("2L.add(4gal, L) = "
                + litre2_.add(gallon4, VolumeUnit.LITRE));                             // ~17.14164 L
        System.out.println();

        // Cross-category incompatibility
        System.out.println("== Cross-Category Incompatibility ==");
        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FOOT);
        Quantity<WeightUnit> oneKg   = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        System.out.println("1L equals 1ft? " + volume1.equals(oneFoot));   // false
        System.out.println("1L equals 1kg? " + volume1.equals(oneKg));     // false
    }

    // ── Generic demonstration helpers ─────────────────────────────

    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> a, Quantity<U> b) {
        System.out.printf("  %s equals %s? -> %b%n", a, b, a.equals(b));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> source, U targetUnit) {
        Quantity<U> result = source.convertTo(targetUnit);
        System.out.printf("  %s convertTo(%s) -> %s%n",
                source, targetUnit.getUnitName(), result);
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> a, Quantity<U> b, U targetUnit) {
        Quantity<U> result = a.add(b, targetUnit);
        System.out.printf("  %s add %s (target: %s) -> %s%n",
                a, b, targetUnit.getUnitName(), result);
    }
}