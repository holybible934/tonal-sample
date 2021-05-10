package com.tonal.interview

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tonal.interview.adapter.MainRecyclerViewAdapter
import com.tonal.interview.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var mainAdapter: MainRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainViewModel = MainActivityViewModel(DependencyInjection.movementRepository)
        mainViewModel.loadMovementList()
        mainViewModel.movementList.observe(this, {
//            Log.d(TAG, "onCreate: List Size is " + it.size)
            mainAdapter.submitList(it)
        })
        mainAdapter = MainRecyclerViewAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.movements)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mainAdapter

        val searchView = findViewById<SearchView>(R.id.searchvw)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                // Filter with searched keyword
                if (p0.isNullOrEmpty()) {
                    mainAdapter.submitList(mainViewModel.movementList.value)
                } else {
                    mainAdapter.submitList(mainViewModel.movementList.value!!
                        .filter { it.name.contains("$p0", true) }.toList())
                }
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                // Do nothing
                return false
            }
        })
        searchView.setOnCloseListener {
            mainAdapter.submitList(mainViewModel.movementList.value)
            false
        }
    }
}