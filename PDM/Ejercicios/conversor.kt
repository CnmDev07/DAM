fun main() {
    // Conversión de Celsius a Fahrenheit
    printFinalTemperature(
        initialMeasurement = 27.0,
        initialUnit = "Celsius",
        finalUnit = "Fahrenheit"
    ) { celsius -> (9.0 / 5.0) * celsius + 32 }

    // Conversión de Kelvin a Celsius
    printFinalTemperature(
        initialMeasurement = 350.0,
        initialUnit = "Kelvin",
        finalUnit = "Celsius"
    ) { kelvin -> kelvin - 273.15 }

    // Conversión de Fahrenheit a Kelvin
    printFinalTemperature(
        initialMeasurement = 10.0,
        initialUnit = "Fahrenheit",
        finalUnit = "Kelvin"
    ) { fahrenheit -> (5.0 / 9.0) * (fahrenheit - 32) + 273.15 }
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // Formatear a 2 decimales
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}
