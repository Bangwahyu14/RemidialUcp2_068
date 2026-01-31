package com.example.remidi_ucp2.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabel_penulis")
data class Penulis(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaPenulis: String
)