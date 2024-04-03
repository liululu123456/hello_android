package com.thoughtworks.androidtrain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class Image (@PrimaryKey val id: Long, val url: String)