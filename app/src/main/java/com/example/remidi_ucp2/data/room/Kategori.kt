package com.example.remidi_ucp2.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabel_kategori")
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaKategori: String,
    val parentId: Int? = null,
    val isDeleted: Boolean = false
)