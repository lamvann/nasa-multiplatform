package domain.repository

import domain.entity.PlanetarySummary

interface PlanetaryRepository {
    suspend fun fetchPlanetaryData(): PlanetarySummary
}