package com.dtuskenis.papajohnscodes

import com.google.gson.annotations.SerializedName

data class PromoCode(
    @SerializedName("name")
    private val name: String,
    @SerializedName("code")
    val rawValue: String
) {

    val description: String
    get() = name.replaceFirst(Regex("[0-9]* - [^-]* - "), "")
}
