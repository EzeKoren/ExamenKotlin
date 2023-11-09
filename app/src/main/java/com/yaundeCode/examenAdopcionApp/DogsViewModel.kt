package com.yaundeCode.examenAdopcionApp

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaundeAode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.database.DogDao
import com.yaundeCode.examenAdopcionApp.models.Dog
import com.yaundeCode.examenAdopcionApp.models.ListAllBreeds
import com.yaundeCode.examenAdopcionApp.models.RandomDogImage
import com.yaundeCode.examenAdopcionApp.service.ActivityServiceApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date
import java.util.Random

class DogsViewModel(application: Application) : AndroidViewModel(application) {
    val dogList: MutableLiveData<List<Dog>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    private val db: AppDatabase = AppDatabase.getDatabase(application)
    private val dogDao: DogDao = db.DogDao()

    fun loadDogs() {
        if(dogDao?.getDogCount() == 0) {
            loadDogsFromApi()
        } else {
            loadDogsFromRoomDB()
        }
    }

    fun addDog(dog: Dog) {
        dogDao.insertDog(dog)
        loadDogsFromRoomDB()
    }

    fun updateDog(dog: Dog) {
        dogDao.updateDog(dog)
        loadDogsFromRoomDB()
    }

    private fun loadDogsFromApi() {
        val newDogList = mutableListOf<Dog>()
        val dogService = ActivityServiceApiBuilder.create()
        dogService.getAllBreeds().enqueue(object : Callback<ListAllBreeds> {
            override fun onResponse(call: Call<ListAllBreeds>, response: Response<ListAllBreeds>) {
                if (response.isSuccessful) {
                    val breeds = response.body()?.breeds
                    if (breeds != null) {
                        for ((breed, subBreeds) in breeds.entries.shuffled().take(20)) {
                            for (subBreed in subBreeds) {
                                dogService.getRandomDogImage(breed).enqueue(object :
                                    Callback<RandomDogImage> {
                                    override fun onResponse(call: Call<RandomDogImage>, response: Response<RandomDogImage>) {
                                        if (response.isSuccessful) {
                                            val imageUrl = response.body()?.imageUrl
                                            if (imageUrl != null) {
                                                val random = Random()
                                                val age = random.nextInt(10) + 1
                                                val gender = if (random.nextBoolean()) "Male" else "Female"
                                                val weight = random.nextDouble() * (25 - 4) + 4
                                                val names = arrayOf("Woody", "Lito", "Pepa", "Mou", "Toto", "Rocio", "Alegria", "Firulais", "Tommy", "Roco", "Rosita", "Negro", "Gomez", "Churchill")
                                                val name = names.random()
                                                val publishedDate = Date().toString()
                                                val owners = arrayOf("Martin", "refugio patitas", "firuHome")
                                                val owner = owners.random()
                                                val status = random.nextBoolean()
                                                val favorite = random.nextBoolean()

                                                val descripcion = listOf<String>(
                                                    "Energético y juguetón: Este perrito siempre está listo para la acción. Le encanta jugar y corretear, ¡ideal para alguien activo!",
                                                    "Tierno compañero: Con ojos que derriten corazones, este peludo amigo busca amor incondicional. Es perfecto para alguien que busca cariño constante.",
                                                    "Sabio y tranquilo: Este perro es más relajado y disfruta de los momentos de tranquilidad. Sería ideal para alguien que busca un amigo calmado.",
                                                    "Adaptable y sociable: A este peludo le encanta conocer nuevas personas y se adapta fácilmente a diferentes situaciones. ¡Ideal para una familia social!",
                                                    "Guardián leal: Si buscas un compañero protector, este perro es leal hasta la médula. Siempre está atento para velar por ti.",
                                                    "Amante de las siestas: Si prefieres las tardes relajadas y las siestas, este perrito es tu compañero perfecto. Le encanta descansar y disfrutar de momentos tranquilos.",
                                                    "Aventurero y curioso: Este intrépido canino está listo para explorar el mundo contigo. Perfecto para alguien que ama la naturaleza y la actividad al aire libre.",
                                                    "Educado y obediente: Conoce los comandos básicos y tiene buenos modales. Este perro hará que el entrenamiento sea pan comido.",
                                                    "Amigo para todos: Este perrito se lleva bien con niños, adultos y otros animales. Si buscas un compañero que se integre fácilmente, él es el indicado.",
                                                    "Resiliente y valiente: A pesar de sus experiencias pasadas, este perro ha demostrado ser fuerte y valiente. Busca un hogar amoroso donde pueda empezar una nueva vida."
                                                ).random()

                                                var newDog = Dog(
                                                    image = imageUrl,
                                                    name = name,
                                                    age = age,
                                                    gender = gender,
                                                    publishedDate = publishedDate,
                                                    weight = weight,
                                                    description = descripcion,
                                                    breed = breed,
                                                    subBreed = subBreeds.shuffled()[0],
                                                    location = "Ciudad de buenos Aires",
                                                    owner = owner,
                                                    status = status,
                                                    favorite = favorite
                                                )

                                                newDogList.add(newDog)
                                                dogList.value = newDogList
                                                dogDao.insertDog(newDog)
                                            }
                                        } else {
                                            errorMessage.value = "Error al obtener todas las razas: ${response.errorBody()}"
                                        }
                                    }

                                    override fun onFailure(call: Call<RandomDogImage>, t: Throwable) {
                                        errorMessage.value = "Fallo al obtener todas las razas: ${t.message}"
                                    }
                                })
                            }
                        }
                    }
                } else {
                    errorMessage.value = "Error al obtener todas las razas: ${response.errorBody()}"
                }
            }

            override fun onFailure(call: Call<ListAllBreeds>, t: Throwable) {
                errorMessage.value = "Fallo al obtener todas las razas: ${t.message}"
            }
        })
    }

    private fun loadDogsFromRoomDB() {
        val dogListDB = dogDao.getAll()
        dogList.value = dogListDB
    }
}
