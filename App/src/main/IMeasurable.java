package main;

/**
 * Generic measurable interface.
 * Implemented by all unit enums.
 */
public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
}