package com.example.remidi_ucp2.data.repositori

import com.example.remidi_ucp2.data.room.Kategori
import com.example.remidi_ucp2.data.room.KategoriDao
import kotlinx.coroutines.flow.Flow

class RepositoriKategori(private val kategoriDao: KategoriDao) {

    val allKategori: Flow<List<Kategori>> = kategoriDao.getAllKategori()

    suspend fun insertKategori(kategori: Kategori) {
        if (kategori.parentId != null) {
            val isLoop = kategoriDao.isCyclic(kategori.parentId, kategori.id)
            if (isLoop > 0) {
                throw Exception("Gagal: Struktur kategori menyebabkan Cyclic Reference!")
            }
        }
        kategoriDao.insertKategori(kategori)
    }

    suspend fun updateKategori(kategori: Kategori) {
        kategoriDao.updateKategori(kategori)
    }
}