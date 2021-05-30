package com.example.citiessdk.cities.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citiessdk.R
import com.example.citiessdk.cities.data.Mall
import com.example.citiessdk.cities.data.Shop

class MallsFragment : Fragment(){
    lateinit var malls : ArrayList<Mall>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MallsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_malls, container, false)

        val bundle = this.arguments
        if (bundle != null) {
            malls = bundle.getParcelableArrayList<Mall>("Malls") as ArrayList<Mall>
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.malls_recyclerView)

        adapter = MallsAdapter(malls, object : MallsAdapter.ItemClickListener {
            override fun onItemClicked(model: List<Shop>) {
                showShops(model)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        activity?.title = "Malls"
    }

    private fun showShops (shops: List<Shop>) {
        val shopsFragment = ShopsFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("Shops", ArrayList(shops))
        shopsFragment.arguments = bundle

        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, shopsFragment)
            .commit()
    }
}