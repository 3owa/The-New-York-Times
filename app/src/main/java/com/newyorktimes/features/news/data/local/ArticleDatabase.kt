package com.newyorktimes.features.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.newyorktimes.core.contants.DbConstants
import com.newyorktimes.features.news.data.local.entity.ArticleEntity


@Database(
    entities = [ArticleEntity::class],
    version = DbConstants.DATABASE_VERSION
)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao
}