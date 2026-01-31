package com.example.remidi_ucp2.data.repositori

import com.example.remidi_ucp2.data.room.Buku
import com.example.remidi_ucp2.data.room.BukuDao
import kotlinx.coroutines.flow.Flow

class RepositoriBuku(private val bukuDao: BukuDao) {
    fun getBukuByKategori(katId: Int): Flow<List<Buku>> {
        return bukuDao.getBukuBerdasarkanKategoriRekursif(katId)
    }

    suspend fun insertBuku(buku: Buku) {
        bukuDao.insertBuku(buku)
    }

    suspend fun hapusKategoriDenganOpsi(katId: Int, hapusSemuaBuku: Boolean) {
        try {

            bukuDao.hapusKategoriAman(katId, hapusSemuaBuku)
        } catch (e: Exception) {
            throw e
        }
    }
}