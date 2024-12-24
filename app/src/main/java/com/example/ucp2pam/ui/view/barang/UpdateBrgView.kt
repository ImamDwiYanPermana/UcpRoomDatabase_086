package com.example.ucp2pam.ui.view.barang
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.ui.costumWidget.TopAppBar
import com.example.ucp2pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2pam.ui.viewModel.barangModel.UpdateBrgViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateBrgViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val BrgUiState = viewModel.UpdateBrgUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(
        BrgUiState.snackbarMessage
    ) {
        println("LaunchedEffect triggered")
        BrgUiState.snackbarMessage?.let { message ->
            println("Launhing coroutine for snackbar")
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long,
            )
            viewModel.resetSnackbarMessage()
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)},
    ) { it
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 130.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Edit Barang",
            )
            InsertBodyBrg (
                uiState = BrgUiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()){
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
                }
            )
        }
    }
}