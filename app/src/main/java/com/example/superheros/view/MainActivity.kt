package com.example.superheros.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheros.R
import com.example.superheros.model.SuperHero
import com.example.superheros.viewmodel.MainActivityViewModel
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SuperHerosAdapter
    private lateinit var superHeros: ArrayList<SuperHero>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


        recyclerView = findViewById(R.id.recyclerView)
        superHeros = ArrayList()
        adapter = SuperHerosAdapter(this, superHeros, object : SuperHerosAdapter.ItemClickListener {
            override fun onItemClicked(model: SuperHero) {
                viewDetails(model)
            }
        })

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val mLayoutManager = GridLayoutManager(this, 3)
            recyclerView.layoutManager = mLayoutManager
            recyclerView.addItemDecoration(GridSpacingItemDecoration(3, dpToPx(10), true))
            recyclerView.itemAnimator = DefaultItemAnimator()
        } else {
            val mLayoutManager = GridLayoutManager(this, 2)
            recyclerView.layoutManager = mLayoutManager
            recyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
            recyclerView.itemAnimator = DefaultItemAnimator()
        }
        recyclerView.adapter = adapter

        displaySuperHero()
    }

    private fun viewDetails(superHero: SuperHero) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("details", superHero)
        startActivity(intent)
    }

    private fun displaySuperHero(){
//        viewModel.superHeros?.observe(this, Observer {
//            if (it.isNotEmpty()) {
//                superHeros.addAll(it)
//            }
//        })

//        viewModel.superHeros

        viewModel.superHeros?.observe(this, Observer {
            Log.e("MainActivity", "here")
            it?.let { it1 -> superHeros.addAll(it1) }
            adapter.notifyDataSetChanged()
        })

        progressBar.visibility = View.GONE
    }

    private fun dpToPx(dp: Int): Int {
        val r = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    }
}
