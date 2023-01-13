package com.ankit.jumpingmindsassignment.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Beer(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "first_brewed") val first_brewed: String?,
    @ColumnInfo(name = "abv") val abv: Float?
)
