package com.example.ucp2pam.data.repository

import com.example.ucp2pam.data.dao.SuplierDao
import com.example.ucp2pam.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl (
    private val suplierDao: SuplierDao
): RepositorySpl{
    override suspend fun insertSpl(suplier: Suplier){
        suplierDao.insertSuplier(suplier)
    }

    override fun getAllSpl(): Flow<List<Suplier>> =
        suplierDao.getAllSuplier()

    override fun getSpl(id: String): Flow<Suplier> =
        suplierDao.getSupplier(id)
}