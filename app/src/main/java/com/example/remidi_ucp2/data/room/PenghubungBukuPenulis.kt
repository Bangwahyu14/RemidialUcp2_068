package com.example.remidi_ucp2.data.room

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "penghubung_buku_penulis",
    primaryKeys = ["bukuId", "penulisId"],
    foreignKeys = [
        ForeignKey(entity = Buku::class, parentColumns = ["id"], childColumns = ["bukuId"]),
        ForeignKey(entity = Penulis::class, parentColumns = ["id"], childColumns = ["penulisId"])
    ]
)
data class PenghubungBukuPenulis(
    val bukuId: Int,
    val penulisId: Int
)