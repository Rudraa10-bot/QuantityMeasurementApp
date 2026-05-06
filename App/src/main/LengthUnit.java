public enum LengthUnit implements IMeasurable {
    INCH(0.0833333),
    FOOT(1.0),
    YARD(3.0),
    CENTIMETRE(0.0328084),
    MILLIMETRE(0.00328084);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}