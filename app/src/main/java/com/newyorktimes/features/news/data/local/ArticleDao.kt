package com.newyorktimes.features.news.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.newyorktimes.features.news.data.local.entity.ArticleEntity
import com.newyorktimes.features.news.domain.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Query("SELECT * FROM articleentity")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(article: List<ArticleEntity>)

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)

}