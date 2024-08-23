package com.example.firstlineandroidcode.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firstlineandroidcode.R

class NewsContentFragment : Fragment() {

    private lateinit var mView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.news_content_frag, container, false)
        return mView
    }

    fun refresh(title: String, content: String) {
        val contentLayout : LinearLayout = mView.findViewById(R.id.contentLayout)
        contentLayout.visibility = View.VISIBLE

        val newsTitle: TextView = mView.findViewById(R.id.newsTitle)
        newsTitle.text = title // 刷新新闻的标题

        val newsContent: TextView = mView.findViewById(R.id.newsContent)
        newsContent.text = content // 刷新新闻的内容
    }

}