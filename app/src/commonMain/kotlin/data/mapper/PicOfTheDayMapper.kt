package data.mapper

import domain.entity.PlanetarySummary

class PlanetaryMapper : Mapper<PlanetarySummary, PlanetaryResponse> {

    override operator fun invoke(response: PlanetaryResponse) = PlanetarySummary(
        date = response.date,
        explanation = response.explanation,
        mediaType = response.mediaType,
        serviceVersion = response.serviceVersion,
        title = response.title,
        url = response.url
    )
}