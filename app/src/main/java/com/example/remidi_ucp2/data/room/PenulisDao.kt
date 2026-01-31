package com.example.remidi_ucp2.data.room


import androidx.room.*

@Dao
interface PenulisDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPenulis(penulis: Penulis)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun hubungkanBukuPenulis(penghubung: PenghubungBukuPenulis)

    @Query("""
        SELECT p.* FROM tabel_penulis p
        JOIN penghubung_buku_penulis pbp ON p.id = pbp.penulisId
        WHERE pbp.bukuId = :bukuId
    """)
    suspend fun getPenulisBuku(bukuId: Int): List<Penulis>
}