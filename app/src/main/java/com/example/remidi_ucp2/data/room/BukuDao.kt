package com.example.remidi_ucp2.data.room

import androidx.room.*

@Dao
interface BukuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuku(buku: Buku)


    @Query("SELECT COUNT(*) FROM tabel_buku WHERE kategoriId = :katId AND statusPinjam = 1 AND isDeleted = 0")
    suspend fun countBukuDipinjam(katId: Int): Int


    @Query("UPDATE tabel_buku SET isDeleted = 1 WHERE id = :bukuId")
    suspend fun softDeleteBuku(bukuId: Int)


    @Query("UPDATE tabel_buku SET kategoriId = NULL WHERE kategoriId = :katId")
    suspend fun alihkanKategoriBuku(katId: Int)

    @Transaction
    suspend fun hapusKategoriAman(katId: Int, hapusBuku: Boolean) {
        val jumlahDipinjam = countBukuDipinjam(katId)
        if (jumlahDipinjam > 0) {

            throw Exception("Gagal: Ada buku yang sedang dipinjam!")
        }

        if (hapusBuku) {

            updateBukuSoftDeleteByKategori(katId)
        } else {

            alihkanKategoriBuku(katId)
        }
    }

    @Query("UPDATE tabel_buku SET isDeleted = 1 WHERE kategoriId = :katId")
    suspend fun updateBukuSoftDeleteByKategori(katId: Int)
}