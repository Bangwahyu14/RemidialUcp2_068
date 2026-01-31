package com.example.remidi_ucp2.data.repositori

import android.content.Context
import com.example.remidi_ucp2.data.room.AppDatabase

interface AppContainer {
    val repositoriKategori: RepositoriKategori
    val repositoriBuku: RepositoriBuku
    val repositoriPenulis: RepositoriPenulis
}

class ContainerApp(private val context: Context) : AppContainer {
    private val database: AppDatabase by lazy { AppDatabase.getDatabase(context) }

    override val repositoriKategori: RepositoriKategori by lazy {
        RepositoriKategori(database.kategoriDao())
    }

    override val repositoriBuku: RepositoriBuku by lazy {
        RepositoriBuku(database.bukuDao())
    }

    override val repositoriPenulis: RepositoriPenulis by lazy {
        RepositoriPenulis(database.penulisDao())
    }
}