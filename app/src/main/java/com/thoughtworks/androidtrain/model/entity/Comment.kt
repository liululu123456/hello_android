package com.thoughtworks.androidtrain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class Comment(@PrimaryKey val id: Long,
                   var content: String, val sender: Sender
)