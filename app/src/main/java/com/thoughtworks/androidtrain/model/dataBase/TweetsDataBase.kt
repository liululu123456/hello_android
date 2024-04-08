package com.thoughtworks.androidtrain.model.dataBase

import com.thoughtworks.androidtrain.model.entity.CommentListConverter
import com.thoughtworks.androidtrain.model.entity.ImageListConverter
import com.thoughtworks.androidtrain.model.entity.SenderConverter
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thoughtworks.androidtrain.model.dao.CommentDao
import com.thoughtworks.androidtrain.model.dao.ImageDao
import com.thoughtworks.androidtrain.model.dao.SenderDao
import com.thoughtworks.androidtrain.model.dao.TweetDao
import com.thoughtworks.androidtrain.model.entity.Comment
import com.thoughtworks.androidtrain.model.entity.Image
import com.thoughtworks.androidtrain.model.entity.Sender
import com.thoughtworks.androidtrain.model.entity.Tweet

@Database(entities = [Tweet::class, Image::class, Comment::class, Sender::class], version = 1)
@TypeConverters(ImageListConverter::class, SenderConverter::class, CommentListConverter::class )
abstract class AppDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao
    abstract fun imageDao(): ImageDao
    abstract fun commentDao(): CommentDao
    abstract fun senderDao(): SenderDao

    companion object {
        private const val DATABASE_NAME = "my_app_database"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}