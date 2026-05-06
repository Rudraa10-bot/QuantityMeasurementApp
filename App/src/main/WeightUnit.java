package main;

/**
 * WeightUnit.java
 *
 * Standalone enum for weight conversion responsibility.
 * Base unit = KILOGRAM.
 */
public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Convert value from this unit to base unit (kilogram)
     */
    public double convertToBaseUnit(double value) {
        return Math.round(value * conversionFactor * 100000.0) / 100000.0;
    }

    /**
     * Convert value from kilogram to this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return Math.round((baseValue / conversionFactor) * 100000.0) / 100000.0;
    }
}