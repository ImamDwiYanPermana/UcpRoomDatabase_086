package com.example.ucp2pam.data.repository

import com.example.ucp2pam.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {
    suspend fun insertSuplier(suplier: Suplier)
    fun getAllSuplier(): Flow<List<Suplier>>
    fun getSuplier(id: String): Flow<Suplier>
}