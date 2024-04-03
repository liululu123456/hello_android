package com.thoughtworks.androidtrain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sender")
data class Sender (@PrimaryKey val id: Long, val userName: String, val nick: String, val avatar: String)