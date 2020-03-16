package domain.repository

import domain.entity.PicOfTheDay

interface PicOfTheDayRepository {
    suspend fun fetchPlanetaryData(date: String = ""): PicOfTheDay
}