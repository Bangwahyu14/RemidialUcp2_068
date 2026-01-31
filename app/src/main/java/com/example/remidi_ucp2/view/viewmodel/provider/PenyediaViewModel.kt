package com.example.remidi_ucp2.view.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.remidi_ucp2.ContainerApp
import com.example.remidi_ucp2.ui.viewmodel.BukuViewModel
import com.example.remidi_ucp2.ui.viewmodel.KategoriViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            BukuViewModel(
                aplikasiPerpustakaan().container.repositoriBuku,
                aplikasiPerpustakaan().container.repositoriPenulis
            )
        }
        initializer {
            KategoriViewModel(
                aplikasiPerpustakaan().container.repositoriKategori
            )
        }
    }
}

fun CreationExtras.aplikasiPerpustakaan(): PerpustakaanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpustakaanApp)