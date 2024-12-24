package com.example.ucp2pam.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2pam.ui.costumWidget.HomeBar

@Composable
fun Home(
    onBarangClick: () -> Unit,
    onTambahBarangClick: () -> Unit,
    onSuplierClick: () -> Unit,
    onTambahSuplierClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            HomeBar(
                showBackButton = false,
                onBack = { },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                // Header teks di atas kartu
                HeaderText()

                // Kolom dengan tampilan kartu
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Tambah Barang
                    CardItem(
                        title = "Tambah Barang",
                        onClick = onTambahBarangClick
                    )
                    // Barang List
                    CardItem(
                        title = "Daftar Barang",
                        onClick = onBarangClick
                    )
                    // Tambah Suplier
                    CardItem(
                        title = "Tambah Suplier",
                        onClick = onTambahSuplierClick
                    )
                    // Suplier List
                    CardItem(
                        title = "Daftar Suplier",
                        onClick = onSuplierClick
                    )
                }
            }
        }
    )
}

}