# Quantity Measurement Application (UC1–UC13)

A Java application demonstrating a scalable, type-safe **quantity measurement system** supporting multiple measurement categories—**Length**, **Weight**, and **Volume**—with:

- Equality comparison across units within a category
- Unit conversion within a category
- Arithmetic operations: **addition, subtraction, division**
- Strong separation between categories (length ≠ weight ≠ volume)
- DRY refactor (UC13) with centralized arithmetic logic

---

## Features

### Supported Measurement Categories & Units

#### Length (Base unit: **FOOT**)
- `INCH`
- `FOOT`
- `YARD`
- `CENTIMETRE`
- `MILLIMETRE`

#### Weight (Base unit: **KILOGRAM**)
- `KILOGRAM`
- `GRAM`
- `TONNE`
- `POUND`

#### Volume (Base unit: **LITRE**)
- `LITRE`
- `MILLILITRE`
- `GALLON` (1 gal ≈ 3.78541 L)

---

## Operations

### Equality (within category)
Compares by converting both quantities to the category base unit and checking within epsilon tolerance.

Examples:
- `1 FOOT == 12 INCH`
- `1 KILOGRAM == 1000 GRAM`
- `1 LITRE == 1000 MILLILITRE`

Cross-category equality is always false:
- `1 LITRE != 1 FOOT`
- `1 FOOT != 1 KILOGRAM`

---

### Conversion (within category)
Convert a quantity to another unit in the same category.

Examples:
- `1 FOOT -> 12 INCH`
- `1 GALLON -> 3.78541 LITRE`
- `500 MILLILITRE -> ~0.132086 GALLON`

---

### Addition
Adds two quantities of the same category:
- Default result unit: first operand’s unit
- Optional explicit target unit

Examples:
- `1 LITRE + 1000 MILLILITRE = 2 LITRE`
- `1 FOOT + 12 INCH = 2 FOOT`

---

### Subtraction (UC12)
Subtracts within the same category:
- Default result unit: first operand’s unit
- Optional explicit target unit
- Negative and zero results supported
- Results are rounded to **2 decimals** (as per UC12/UC13 design)

Examples:
- `10 FOOT - 6 INCH = 9.50 FOOT`
- `5 LITRE - 2 LITRE = 3.00 LITRE`

---

### Division (UC12)
Divides two quantities of the same category and returns a **dimensionless double ratio**:
- `10 KG / 5 KG = 2.0`
- `24 INCH / 2 FOOT = 1.0`

Division by zero throws `ArithmeticException`.

---

## Architecture Overview

### Core Types

#### `IMeasurable`
Contract implemented by each unit enum:
- conversion factor relative to category base unit
- convert to/from base unit
- readable unit name

#### `Quantity<U extends IMeasurable>`
Generic immutable quantity class:
- holds a numeric value and a unit `U`
- supports operations using base-unit normalization
- enforces cross-category isolation using runtime unit-class checks in `equals()` and validation helpers

### UC13 Refactor (DRY)
Arithmetic logic is centralized inside `Quantity` using:
- `validateArithmeticOperands(...)` (shared validation)
- `performBaseArithmetic(...)` (shared base-unit normalization + operation dispatch)
- `ArithmeticOperation` enum (ADD/SUBTRACT/DIVIDE) using lambda computation

Public method signatures remain unchanged.

---

## Project Structure
