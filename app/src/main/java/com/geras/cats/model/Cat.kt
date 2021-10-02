package com.geras.cats.model

data class Cat(
    var breeds: List<Int>? = null,
    var id: String? = null,
    var url: String? = null,
    var width: Int? = null,
    var height: Int? = null,
)
