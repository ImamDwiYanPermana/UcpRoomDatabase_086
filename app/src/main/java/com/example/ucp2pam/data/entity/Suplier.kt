package com.example.ucp2pam.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suplier")
data class Suplier(
    @PrimaryKey
    val idSup: String,
    val namaSup: String,
    val kontakSup: String,
    val alamatSup: String,
)
