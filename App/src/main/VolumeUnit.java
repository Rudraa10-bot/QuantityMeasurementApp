package main;

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts the given value (in this unit) to the base unit (Litre).
     *
     * @param value the value in this unit
     * @return the equivalent value in litres
     */
    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    /**
     * Converts the given value (in litres) to this unit.
     *
     * @param baseValue the value in litres
     * @return the equivalent value in this unit
     */
    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}