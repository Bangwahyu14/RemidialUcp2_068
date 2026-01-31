package com.example.remidi_ucp2.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface KategoriDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKategori(kategori: Kategori)

    @Update
    suspend fun updateKategori(kategori: Kategori)

    @Query("""
        WITH RECURSIVE sub_kategori AS (
            SELECT id FROM tabel_kategori WHERE id = :indukId
            UNION ALL
            SELECT k.id FROM tabel_kategori k
            JOIN sub_kategori sk ON k.parentId = sk.id
        )
        SELECT * FROM tabel_buku 
        WHERE kategoriId IN sub_kategori AND isDeleted = 0
    """)
    fun getBukuBerdasarkanKategoriRekursif(indukId: Int): Flow<List<Buku>>

    @Query("SELECT * FROM tabel_kategori WHERE isDeleted = 0")
    fun getAllKategori(): Flow<List<Kategori>>

    @Query("""
        WITH RECURSIVE keturunan AS (
            SELECT id FROM tabel_kategori WHERE id = :calonAnakId
            UNION ALL
            SELECT k.id FROM tabel_kategori k
            JOIN keturunan kt ON k.parentId = kt.id
        )
        SELECT COUNT(*) FROM keturunan WHERE id = :calonParentId
    """)
    suspend fun isCyclic(calonParentId: Int, calonAnakId: Int): Int
}