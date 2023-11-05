package com.yaundeCode.examenAdopcionApp

class DogProvider {
    companion object{
        val dogList = listOf<Dog>(
            Dog(
                guardado = true,
                imagen = "imagen_fondo_perro",
                nombre = "Firulais",
                raza = "Caniche",
                subRaza = "Toy",
                edad = 3,
                sexo = "hembra"
            ),
            Dog(
                guardado = false,
                imagen = "imagen_fondo_perro2",
                nombre = "firu segundo",
                raza = "Buldog",
                subRaza = "Frances",
                edad = 5,
                sexo = "macho"
            ),
            Dog(
                guardado = false,
                imagen = "imagen_fondo_perro",
                nombre = "firu tercero",
                raza = "Labrador Retriever:",
                subRaza = "Americano",
                edad = 1,
                sexo = "macho"
            ),
            Dog(
                guardado = false,
                imagen = "imagen_fondo_perro2",
                nombre = "firu cuarto",
                raza = "Labrador Retriever:",
                subRaza = "Ingles",
                edad = 7,
                sexo = "macho"
            )
        )
    }
}