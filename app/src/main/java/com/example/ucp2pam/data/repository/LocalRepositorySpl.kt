package com.example.ucp2pam.data.repository

import com.example.ucp2pam.data.dao.SuplierDao
import com.example.ucp2pam.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl (
    private val suplierDao: SuplierDao
) : RepositorySpl {
    override suspend fun insertSuplier(suplier: Suplier) {
        suplierDao.insertSuplier(suplier)
    }
    override fun getAllSuplier(): Flow<List<Suplier>> =
        suplierDao.getAllSuplier()

    override fun getSuplier(id: String): Flow<Suplier> =
        suplierDao.getSupplier(id)
}