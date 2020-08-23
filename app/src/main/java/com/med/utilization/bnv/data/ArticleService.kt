package com.med.utilization.bnv.data

import com.med.utilization.bnv.home.Article
import retrofit2.http.GET

interface ArticleService {

	@GET("articles")
	suspend fun getArticles(): List<Article>
}