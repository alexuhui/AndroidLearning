package com.example.firstlineandroidcode.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstlineandroidcode.R

class NewsTitleFragment : Fragment() {
    private val TAG = "NewsTitleFragment"
    private var isTwoPane = false

    inner class NewsAdapter(val newsList: List<News>) :
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                if (isTwoPane) {
                    val newsContentFrag : Fragment? = activity?.supportFragmentManager?.findFragmentById(R.id.newsContentFrag)
                    // 如果是双页模式，则刷新NewsContentFragment中的内容
                    val fragment = newsContentFrag as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {
                    // 如果是单页模式，则直接启动NewsContentActivity
                    NewsContentActivity.actionStart(parent.context, news.title,
                        news.content)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount() = newsList.size

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null
//        Log.d(TAG, "onActivityCreated: isTwoPane = $isTwoPane")
//        val layoutManager = LinearLayoutManager(activity)
//        val newsTitleRecyclerView : RecyclerView? = activity?.findViewById(R.id.newsTitleRecyclerView)
//        newsTitleRecyclerView?.layoutManager = layoutManager
//        val adapter = NewsAdapter(getNews())
//        newsTitleRecyclerView?.adapter = adapter
//    }

//    override fun  onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null
//        Log.d(TAG, "onViewCreated: isTwoPane = $isTwoPane")
//        val layoutManager = LinearLayoutManager(activity)
//        val newsTitleRecyclerView : RecyclerView? = activity?.findViewById(R.id.newsTitleRecyclerView)
//        newsTitleRecyclerView?.layoutManager = layoutManager
//        val adapter = NewsAdapter(getNews())
//        newsTitleRecyclerView?.adapter = adapter
//    }

    /**
     * 这里是个坑
     * 书上用的是 onActivityCreated ，但这个方法已过期
     * 网上看到替代方案都是 onViewCreated，但这个方法是获取不到 newsContentLayout 的
     * *** 根据api注释（before any saved state has been restored in to the view.）
     * *** 这个方法没有保存任何状态，可能是这个问题，顺势看到一个 onViewStateRestored
     * 使用 onViewStateRestored 发现终于一切正常
     * */
    override fun onViewStateRestored(savedInstanceState: Bundle?)
    {
        super.onViewStateRestored(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null
        Log.d(TAG, "onViewStateRestored: isTwoPane = $isTwoPane")
        val layoutManager = LinearLayoutManager(activity)
        val newsTitleRecyclerView : RecyclerView? = activity?.findViewById(R.id.newsTitleRecyclerView)
        newsTitleRecyclerView?.layoutManager = layoutManager
        val adapter = NewsAdapter(getNews())
        newsTitleRecyclerView?.adapter = adapter
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            val news = News("This is news title $i", getRandomLengthString("This is news content $i. "))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val flag = inflater.inflate(R.layout.news_title_frag, container, false)
        Log.d(TAG, "onCreateView: flag = $flag")
        return flag
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        Log.d(TAG, "onViewCreated: mView = $mView")
////        val fragmentLayout : FrameLayout = mView.findViewById(R.id.newsContentLayout)
//        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null//fragmentLayout != null
//        val layoutManager = LinearLayoutManager(activity)
//        val newsTitleRecyclerView : RecyclerView = mView.findViewById(R.id.newsTitleRecyclerView)
//        newsTitleRecyclerView.layoutManager = layoutManager
//        val adapter = NewsAdapter(getNews())
//        newsTitleRecyclerView.adapter = adapter
//    }

}
