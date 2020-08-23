package com.med.utilization.bnv.home

import android.view.ViewGroup
import com.med.coreui.recycleview.ItemDelegate
import com.med.coreui.recycleview.viewbinding.ViewBindingHolder
import com.med.coreui.recycleview.viewbinding.createViewBindingHolder
import com.med.utilization.R
import com.med.utilization.databinding.ItemArticleBinding

data class ArticleItem(val article: Article) : ItemDelegate<ViewBindingHolder<ItemArticleBinding>> {
	override fun onCreateViewHolder(parent: ViewGroup): ViewBindingHolder<ItemArticleBinding> {
		return parent.createViewBindingHolder(ItemArticleBinding::inflate)
	}

	override fun areItemTheSame(other: Any): Boolean {
		return other is ArticleItem && other.article.id == article.id
	}

	override fun getViewType(): Int {
		return R.layout.item_article
	}

	override fun onBindViewHolder(holder: ViewBindingHolder<ItemArticleBinding>) {
		with(holder.binding) {
			title.text = article.name
			subtitle.text = article.lastName
			//TODO load image with fresco
		}
	}
}