package com.example.evaintkt.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "consume_table")
data class Entity (
        @PrimaryKey(autoGenerate = true)
        @NotNull
        val id: Int = 0,
        val item: String,
        val unitPrice: Int,
        val cantidad: Int)