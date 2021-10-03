package com.geras.cats.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Cat(

    @PrimaryKey
    var id: String = "",
    var url: String = "",
    var width: Int = 0,
    var height: Int = 0

)
