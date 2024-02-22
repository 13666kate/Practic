package sence.kate.practica3.RandomI

import sence.kate.practica3.R
import kotlin.random.Random

class RandomImage {
    companion object {
        private val imageRandom = intArrayOf(
            R.drawable.cat1,
            R.drawable.cat2,
            R.drawable.cat3,
            R.drawable.cat4,
            R.drawable.cat5,
            R.drawable.cat6,
            R.drawable.cat7,
            R.drawable.cat9,
            R.drawable.cat10,

            // Добавьте сюда ссылки на другие изображения
        )
    }
    fun randomImageResId(): Int {
        val randomIndex = Random.nextInt(imageRandom.size)
        return imageRandom[randomIndex]
    }
}

