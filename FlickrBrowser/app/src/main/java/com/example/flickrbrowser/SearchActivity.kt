package com.example.flickrbrowser

import academy.learnprogramming.flickrbrowser.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : BaseActivity() {
    private val TAG = "SearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, ".onCreate: starts")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolBar(true)
        Log.d(TAG, ".onCreate: ends")

    }
}