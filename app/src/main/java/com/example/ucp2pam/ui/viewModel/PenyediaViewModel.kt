package com.example.ucp2pam.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2pam.KrsApp
import com.example.ucp2pam.ui.viewModel.barangModel.BarangViewModel
import com.example.ucp2pam.ui.viewModel.barangModel.DetailBrgViewModel
import com.example.ucp2pam.ui.viewModel.barangModel.HomeBrgViewModel
import com.example.ucp2pam.ui.viewModel.barangModel.UpdateBrgViewModel
import com.example.ucp2pam.ui.viewModel.suplierModel.HomeSplViewModel
import com.example.ucp2pam.ui.viewModel.suplierModel.SuplierViewModel

object PenyediaViewModel{

    val Factory = viewModelFactory {
        initializer {
            HomePageViewModel(
                KrsApp().containerApp.repositoryBrg,
                KrsApp().containerApp.repositorySpl
            )
        }
        initializer {
            HomeBrgViewModel(
                KrsApp().containerApp.repositoryBrg
            )
        }
        initializer {
            HomeSplViewModel(
                KrsApp().containerApp.repositorySpl
            )
        }
        initializer {
            BarangViewModel(
                KrsApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            SuplierViewModel(
                KrsApp().containerApp.repositorySpl
            )
        }
    }
}

fun CreationExtras.KrsApp() : KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)