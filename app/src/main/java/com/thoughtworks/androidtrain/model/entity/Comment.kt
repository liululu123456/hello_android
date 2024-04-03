package com.thoughtworks.androidtrain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class Comment(@PrimaryKey val id: Long,
                   val content: String, val sender: Sender
)