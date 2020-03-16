package domain.entity

import data.mapper.MediaType

data class PicOfTheDay(
    val explanation: String,
    val mediaType: MediaType,
    val hdurl: String,
    val title: String,
    val url: String
)