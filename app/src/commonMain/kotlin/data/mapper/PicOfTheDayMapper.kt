package data.mapper

import data.response.PicOfTheDayResponse
import domain.entity.PicOfTheDay

class PicOfTheDayMapper : Mapper<PicOfTheDay, PicOfTheDayResponse> {

    override operator fun invoke(response: PicOfTheDayResponse) = PicOfTheDay(
        explanation = response.explanation,
        mediaType = when(response.mediaType.toLowerCase()) {
            "video" -> MediaType.Video
            "image" -> MediaType.Image
            else -> MediaType.Other
        },
        hdurl = response.hdurl,
        title = response.title,
        url = response.url
    )
}

sealed class MediaType {
    object Video : MediaType()
    object Image : MediaType()
    object Other : MediaType()
}