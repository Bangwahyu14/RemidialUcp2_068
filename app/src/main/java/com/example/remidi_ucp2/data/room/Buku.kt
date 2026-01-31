package com.example.remidi_ucp2.data.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tabel_buku",
    foreignKeys = [
        ForeignKey(
            entity = Kategori::class,
            parentColumns = ["id"],
            childColumns = ["kategoriId"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val kategoriId: Int,
    val statusPinjam: Boolean = false,
    val isDeleted: Boolean = false
)