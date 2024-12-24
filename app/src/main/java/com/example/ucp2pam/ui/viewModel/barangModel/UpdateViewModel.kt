package com.example.ucp2pam.ui.viewModel.barangModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Barang
import com.example.ucp2pam.data.repository.RepositoryBrg
import com.example.ucp2pam.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel() {

    var UpdateBrgUiState by mutableStateOf(BrgUiState())
        private set

    private val barangId: String = checkNotNull(savedStateHandle[DestinasiUpdate.idBarang])

    init {
        viewModelScope.launch {
            UpdateBrgUiState = repositoryBrg.getBarang(barangId)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun updateState(barangEvent: BarangEvent) {
        UpdateBrgUiState = UpdateBrgUiState.copy(barangEvent = barangEvent)
    }

    // Validate input fields
    fun validateFields(): Boolean {
        val event = UpdateBrgUiState.barangEvent
        val errorState = FormBrgErrorState(
            idbarang = if (event.idbarang.isEmpty()) "Id barang tidak boleh kosong" else null,
            namabarang = if (event.namabarang.isEmpty()) "Nama barang tidak boleh kosong" else null,
            deskripsi = if (event.deskripsi.isEmpty()) "Deskripsi barang tidak boleh kosong" else null,
            harga = if (event.harga <= 0) "Harga barang tidak boleh kosong atau negatif" else null,
            stok = if (event.stok <= 0) "Stok barang tidak boleh kosong atau negatif" else null,
            namasuplier = if (event.namasuplier.isEmpty()) "Nama suplier tidak boleh kosong" else null
        )

        UpdateBrgUiState = UpdateBrgUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Update the barang data
    fun updateData() {
        val currentEvent = UpdateBrgUiState.barangEvent

        // Validate the fields before saving
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    // Update barang data in the repository
                    repositoryBrg.updateBarang(currentEvent.toBarangEntity())

                    // Success: Update the state with a success message
                    UpdateBrgUiState = UpdateBrgUiState.copy(
                        snackbarMessage = "Data Berhasil Diupdate",
                        barangEvent = BarangEvent(), // Clear form after successful update
                        isEntryValid = FormBrgErrorState()
                    )
                } catch (e: Exception) {
                    // Failure: Update the state with an error message
                    UpdateBrgUiState = UpdateBrgUiState.copy(
                        snackbarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            // Validation failed: Show validation message
            UpdateBrgUiState = UpdateBrgUiState.copy(
                snackbarMessage = "Periksa kembali data Anda"
            )
        }
    }

    // Reset snackbar message
    fun resetSnackbarMessage() {
        UpdateBrgUiState = UpdateBrgUiState.copy(
            snackbarMessage = null
        )
    }
}

// Extension function to convert a Barang entity to UI state
fun Barang.toUIStateBrg(): BrgUiState = BrgUiState(
    barangEvent = this.toDetailUiEvent()
)