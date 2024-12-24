package com.example.ucp2pam.ui.viewModel.suplierModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Suplier
import com.example.ucp2pam.data.repository.RepositorySpl
import kotlinx.coroutines.launch

class SuplierViewModel(private val repositorySup: RepositorySpl) : ViewModel() {
    var SplUiState by mutableStateOf(SuplierUiState())

    fun updateUiState(suplierEvent: SuplierEvent) {
        SplUiState = SplUiState.copy(
            suplierEvent = suplierEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = SplUiState.suplierEvent
        val errorState = FormSplErrorState(
            idsuplier = when {
                event.idsuplier.isEmpty() -> "Id suplier tidak boleh kosong"
                event.idsuplier.length < 3 -> "Id suplier minimal 3 karakter"
                else -> null
            },
            namasuplier = when {
                event.namasuplier.isEmpty() -> "Nama suplier tidak boleh kosong"
                else -> null
            },
            kontaksuplier = when {
                event.kontaksuplier.isEmpty() -> "Kontak suplier tidak boleh kosong"
                else -> null
            },
            alamatsuplier = when {
                event.alamatsuplier.isEmpty() -> "Alamat suplier tidak boleh kosong"
                else -> null
            }
        )
        SplUiState = SplUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        if (SplUiState.isLoading) return  // Prevent multiple submits

        val currentEvent = SplUiState.suplierEvent
        println("Attempting to save data: $currentEvent") // Debug log

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    println("Fields validated, converting to entity") // Debug log
                    val entity = currentEvent.toSuplierEntity()
                    println("Converting to entity: $entity") // Debug log

                    SplUiState = SplUiState.copy(isLoading = true)
                    repositorySup.insertSuplier(entity)
                    println("Data saved successfully") // Debug log

                    SplUiState = SplUiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        suplierEvent = SuplierEvent(),
                        isEntryValid = FormSplErrorState(),
                        isLoading = false
                    )
                } catch (e: Exception) {
                    println("Error saving data: ${e.message}") // Debug log
                    SplUiState = SplUiState.copy(
                        snackbarMessage = "Data Gagal Disimpan: ${e.message}",
                        isLoading = false
                    )
                }
            }
        } else {
            println("Validation failed") // Debug log
            SplUiState = SplUiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

    fun resetSnackbarMessage() {
        SplUiState = SplUiState.copy(
            snackbarMessage = null
        )
    }
}

data class SuplierUiState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormSplErrorState = FormSplErrorState(),
    val snackbarMessage: String? = null,
    val isLoading: Boolean = false
)

data class FormSplErrorState(
    val idsuplier: String? = null,
    val namasuplier: String? = null,
    val kontaksuplier: String? = null,
    val alamatsuplier: String? = null
) {
    fun isValid(): Boolean {
        return idsuplier == null && namasuplier == null &&
                kontaksuplier == null && alamatsuplier == null
    }
}

data class SuplierEvent(
    val idsuplier: String = "",
    val namasuplier: String = "",
    val kontaksuplier: String = "",
    val alamatsuplier: String = ""
)

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    id = idsuplier,
    nama = namasuplier,
    kontak = kontaksuplier,
    alamat = alamatsuplier
)