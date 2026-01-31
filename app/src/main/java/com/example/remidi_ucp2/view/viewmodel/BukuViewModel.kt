package com.example.remidi_ucp2.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remidi_ucp2.data.repositori.RepositoriBuku
import com.example.remidi_ucp2.data.repositori.RepositoriPenulis
import com.example.remidi_ucp2.data.room.Buku
import kotlinx.coroutines.launch

class BukuViewModel(
    private val repositoriBuku: RepositoriBuku,
    private val repositoriPenulis: RepositoriPenulis
) : ViewModel() {

    var uiState by mutableStateOf(BukuUIState())
        private set

    fun updateUiState(bukuEvent: BukuEvent) {
        uiState = BukuUIState(
            bukuEvent = bukuEvent,
            isEntryValid = validateInput(bukuEvent)
        )
    }

    private fun validateInput(event: BukuEvent): Boolean {
        return event.judul.isNotBlank() && event.kategoriId != 0
    }

    fun simpanBuku() {
        if (validateInput(uiState.bukuEvent)) {
            viewModelScope.launch {
                try {
                    repositoriBuku.insertBuku(uiState.bukuEvent.toBukuEntity())
                } catch (e: Exception) {
                    // Logika error handling
                }
            }
        }
    }
}

data class BukuUIState(
    val bukuEvent: BukuEvent = BukuEvent(),
    val isEntryValid: Boolean = false
)

data class BukuEvent(
    val id: Int = 0,
    val judul: String = "",
    val kategoriId: Int = 0,
    val statusPinjam: Boolean = false
)

fun BukuEvent.toBukuEntity(): Buku = Buku(
    id = id,
    judul = judul,
    kategoriId = kategoriId,
    statusPinjam = statusPinjam
)