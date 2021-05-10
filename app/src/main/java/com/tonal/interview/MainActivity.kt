package com.tonal.interview

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tonal.interview.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainViewModel = MainActivityViewModel(DependencyInjection.movementRepository)
        mainViewModel.loadMovementList()
        mainViewModel.movementList.observe(this, {
            Log.d(TAG, "onCreate: List Size is " + it.size)
        })
    }
}