package com.dtuskenis.papajohnscodes

data class PapaJohnsCode(private val name: String,
                         val code: String) {

    val description: String
    get() = name.replaceFirst(Regex("[0-9]* - [^-]* - "), "")
}