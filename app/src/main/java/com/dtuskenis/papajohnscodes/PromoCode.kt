package com.dtuskenis.papajohnscodes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PromoCode(
    @SerialName("name")
    private val name: String,
    @SerialName("code")
    val rawValue: String
) {

    val description: String
    get() = name.replaceFirst(Regex("[0-9]* - [^-]* - "), "")
}
