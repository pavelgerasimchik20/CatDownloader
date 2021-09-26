package com.geras.cats.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cats")
data class Cat(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "icon")
    val icon: Int

) : Serializable
