package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.api.ApiConfig
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.ResponseNews
import org.w3c.dom.Attr
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNews()
    }

    private fun getNews() {
        ApiConfig.service().getArticles().enqueue(object : Callback<ResponseNews> {
            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                if (response.isSuccessful) {
                    val responseNews: ResponseNews = response.body()!!
                    val listArticle: List<ArticlesItem> = responseNews.articles as List<ArticlesItem>
                    // Log.d("RESPONSE ARTIKEL", "onResponse: $listArticle")
                    val newsAdapter = NewsAdapter(listArticle)
                    binding.rvNews.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = newsAdapter
                        setHasFixedSize(true)
                    }
                } else {
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<ResponseNews>, p1: Throwable) {
                Toast.makeText(this@MainActivity, p1.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}