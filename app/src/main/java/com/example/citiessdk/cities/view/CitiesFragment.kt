package com.example.citiessdk.cities.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citiessdk.R
import com.example.citiessdk.cities.data.City
import com.example.citiessdk.cities.data.Mall
import com.example.citiessdk.cities.viewmodel.CitiesViewModel
import org.koin.android.ext.android.inject

class CitiesFragment : Fragment(R.layout.fragment_cities) {
    private val viewModel by inject<CitiesViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CitiesAdapter
    private lateinit var cityList: ArrayList<City>
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        recyclerView = view.findViewById(R.id.cities_recyclerView)
        cityList = ArrayList()

        adapter = CitiesAdapter(cityList, object : CitiesAdapter.ItemClickListener {
            override fun onItemClicked(malls: List<Mall>) {
                showMalls(malls)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        showCities()
    }

    override fun onResume() {
        super.onResume()
        activity?.title = "Cities"
    }

    private fun showCities() {
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            it?.let { it1 ->
                cityList.addAll(it1)
            }
            adapter.notifyDataSetChanged()
        })
    }

    private fun showMalls(malls: List<Mall>) {
        val mallsFragment = MallsFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("Malls", ArrayList(malls))
        mallsFragment.arguments = bundle

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                mallsFragment
            )
            .addToBackStack(null)
            .commit()
    }
}