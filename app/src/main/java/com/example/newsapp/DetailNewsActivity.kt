package com.example.newsapp

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ActivityDetailNewsBinding
import com.example.newsapp.model.ArticlesItem

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        val dataArtikel = intent.getParcelableExtra<ArticlesItem>(EXTRA_DETAIL)
        Glide.with(this@DetailNewsActivity)
            .load(dataArtikel?.urlToImage)
            .error(R.drawable.ic_launcher_background)
            .into(binding.imageDetail)
        val tvDetail: TextView = findViewById(R.id.tv_detail)
        tvDetail.text = dataArtikel?.description
        binding.toolbarLayout.title = dataArtikel?.title
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

}