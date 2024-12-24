package com.example.ucp2pam.ui.view.suplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.ui.costumWidget.TopAppBar
import com.example.ucp2pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2pam.ui.viewModel.suplierModel.FormSplErrorState
import com.example.ucp2pam.ui.viewModel.suplierModel.SuplierEvent
import com.example.ucp2pam.ui.viewModel.suplierModel.SuplierUiState
import com.example.ucp2pam.ui.viewModel.suplierModel.SuplierViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InsertSplView (
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuplierViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.SplUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackbarMessage()
            }
        }
    }

    Scaffold(
        Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier"
            )
            InsertBodySpl(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateUiState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                        delay(100) // Give time for save operation
                        if (uiState.snackbarMessage?.contains("Berhasil") == true) {
                            onNavigate()
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun InsertBodySpl(
    modifier: Modifier = Modifier,
    onValueChange: (SuplierEvent) -> Unit,
    uiState: SuplierUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSuplier(
            suplierEvent = uiState.suplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = !uiState.isLoading
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(text = "Simpan")
            }
        }
    }
}

@Composable
fun FormSuplier(
    suplierEvent: SuplierEvent,
    onValueChange: (SuplierEvent) -> Unit = {},
    errorState: FormSplErrorState = FormSplErrorState(),
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(15.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.idsuplier,
            onValueChange = { onValueChange(suplierEvent.copy(idsuplier = it)) },
            label = { Text(text = "Id Suplier") },
            isError = errorState.idsuplier != null,
            placeholder = { Text(text = "Masukkan Id Suplier") }
        )
        if (errorState.idsuplier != null) {
            Text(
                text = errorState.idsuplier,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.namasuplier,
            onValueChange = { onValueChange(suplierEvent.copy(namasuplier = it)) },
            label = { Text(text = "Nama Suplier") },
            isError = errorState.namasuplier != null,
            placeholder = { Text(text = "Masukkan Nama Suplier") }
        )
        if (errorState.namasuplier != null) {
            Text(
                text = errorState.namasuplier,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontaksuplier,
            onValueChange = { onValueChange(suplierEvent.copy(kontaksuplier = it)) },
            label = { Text(text = "Kontak") },
            isError = errorState.kontaksuplier != null
        )
        if (errorState.kontaksuplier != null) {
            Text(
                text = errorState.kontaksuplier,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamatsuplier,
            onValueChange = { onValueChange(suplierEvent.copy(alamatsuplier = it)) },
            label = { Text(text = "Alamat") },
            isError = errorState.alamatsuplier != null
        )
        if (errorState.alamatsuplier != null) {
            Text(
                text = errorState.alamatsuplier,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}