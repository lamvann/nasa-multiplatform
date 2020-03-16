package data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PicOfTheDayResponse(
    val date: String,
    val title: String,
    val explanation: String,
    val copyright: String,
    val hdurl: String,
    @SerialName("service_version") val serviceVersion: String,
    @SerialName("media_type") val mediaType: String,
    val url: String
)