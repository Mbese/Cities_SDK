package com.example.superheros.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.superheros.R
import com.example.superheros.databinding.ActivityDetailsBinding
import com.example.superheros.model.SuperHero

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.title = "Details"

        val superHero : SuperHero = intent.getSerializableExtra("details") as SuperHero
        superHero.let {
            binding.model = superHero
            binding.imageUrl = superHero.image.url
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
