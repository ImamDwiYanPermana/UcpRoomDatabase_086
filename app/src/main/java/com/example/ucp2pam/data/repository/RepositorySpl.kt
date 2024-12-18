package com.example.ucp2pam.data.repository

import com.example.ucp2pam.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {
    suspend fun  insertSpl(suplier: Suplier)

    fun getAllSpl(): Flow<List<Suplier>>

    fun getSpl(id: String): Flow<Suplier>
}