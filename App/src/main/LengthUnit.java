package main;

/**
 * LengthUnit - Standalone Enum for UC8
 *
 * This enum is responsible for all unit conversion logic.
 * Base unit is FEET.
 *
 * Responsibilities:
 * 1. Convert any unit to base unit (feet)
 * 2. Convert base unit (feet) to any unit
 *
 * This follows Single Responsibility Principle (SRP).
 */
public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    /**
     * Constructor for LengthUnit enum
     *
     * @param conversionFactor conversion factor relative to feet
     */
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * Get conversion factor
     *
     * @return conversion factor relative to feet
     */
    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Convert value from this unit to base unit (feet)
     *
     * @param value value in this unit
     * @return value converted to feet
     */
    public double convertToBaseUnit(double value) {
        double result = value * conversionFactor;
        return Math.round(result * 100.0) / 100.0;
    }

    /**
     * Convert value from base unit (feet) to this unit
     *
     * @param baseValue value in feet
     * @return converted value in this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        double result = baseValue / conversionFactor;
        return Math.round(result * 100.0) / 100.0;
    }
}