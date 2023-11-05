package com.yaundeCode.examenAdopcionApp

class DogProvider {
    companion object{
        val dogList = listOf<Dog>(
            Dog(
                nombre = "Firulais",
                raza = "Caniche",
                subRaza = "Toy",
                edad = 3,
                sexo = "hembra"
            ),
            Dog(
                nombre = "firu segundo",
                raza = "Buldog",
                subRaza = "Frances",
                edad = 5,
                sexo = "macho"
            ),
            Dog(
                nombre = "firu tercero",
                raza = "Labrador Retriever:",
                subRaza = "Americano",
                edad = 1,
                sexo = "macho"
            ),
            Dog(
                nombre = "firu cuarto",
                raza = "Labrador Retriever:",
                subRaza = "Ingles",
                edad = 7,
                sexo = "macho"
            )
        )
    }
}