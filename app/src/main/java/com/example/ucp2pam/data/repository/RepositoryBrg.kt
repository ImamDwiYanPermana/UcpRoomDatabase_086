package com.example.ucp2pam.data.repository

import com.example.ucp2pam.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBrg {
    suspend fun  insertBrg(barang: Barang)
    fun getAllBrg(): Flow<List<Barang>>
    fun getBrg(nama: String): Flow<Barang>
    suspend fun deleteBrg (barang: Barang)
    suspend fun updateBrg (barang: Barang)
}