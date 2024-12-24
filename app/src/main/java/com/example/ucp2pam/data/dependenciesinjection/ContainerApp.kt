package com.example.ucp2pam.data.dependenciesinjection

import android.content.Context
import com.example.ucp2pam.data.database.BarangDatabase
import com.example.ucp2pam.data.repository.LocalRepositoryBrg
import com.example.ucp2pam.data.repository.LocalRepositorySpl
import com.example.ucp2pam.data.repository.RepositoryBrg
import com.example.ucp2pam.data.repository.RepositorySpl

interface InterdaceContainerApp {
    val repositoryBrg: RepositoryBrg
    val repositorySpl: RepositorySpl
}

class ContainerApp(private val context: Context) : InterdaceContainerApp{
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(BarangDatabase.getDatabase(context).barangDao())
    }
    override val repositorySpl: RepositorySpl by lazy {
        LocalRepositorySpl(BarangDatabase.getDatabase(context).suplierDao())
    }
}