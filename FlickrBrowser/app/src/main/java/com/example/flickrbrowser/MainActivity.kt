package com.example.flickrbrowser

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete, GetFlickrJsonData.OnDataAvailable {


    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate called")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android, oreo", "en_us", true)

        val getRawData = GetRawData(this)
        getRawData.execute(url)

        Log.d(TAG, "onCreate ends")
    }

    private fun createUri(baseURL: String, searchCriteria: String, lang: String, matchAll: Boolean): String {
        Log.d(TAG, ".createUri starts")

        var uri = Uri.parse(baseURL)
        var builder = uri.buildUpon()
        builder = builder.appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
        builder = builder.appendQueryParameter("lang", lang)
        builder = builder.appendQueryParameter("format", "json")
        builder = builder.appendQueryParameter("nojsoncallback", "1")
        uri = builder.build()

        return uri.toString()
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, ".onDataAvailable called, data is $data")

        

        Log.d(TAG, ".onDataAvailable ends")
    }

    override fun onError(exception: Exception) {
        Log.d(TAG, ".onError called, error is ${exception.message}")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.

        Log.d(TAG, "onCreateOptionsMenu called")

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Log.d(TAG, "onCreateItemSelected called")

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete called")

            val getFlickrJsonData = GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)

        } else {
            Log.d(TAG, "onDownloadComplete failed with status $status. Error message is: $data")
        }
    }



}