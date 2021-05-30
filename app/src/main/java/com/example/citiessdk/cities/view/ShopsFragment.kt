package com.example.citiessdk.cities.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citiessdk.R
import com.example.citiessdk.cities.data.Shop

class ShopsFragment : Fragment() {
    lateinit var shops: ArrayList<Shop>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShopsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_malls, container, false)

        val bundle = this.arguments
        if (bundle != null) {
            shops = bundle.getParcelableArrayList<Shop>("Shops") as ArrayList<Shop>
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.title = "Shops"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.malls_recyclerView)

        adapter = ShopsAdapter(shops)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }
}