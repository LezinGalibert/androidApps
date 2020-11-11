package com.example.flickrbrowser

import academy.learnprogramming.flickrbrowser.R
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.NonCancellable.cancel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

private const val TAG = "MainActivity"

class MainActivity : BaseActivity(),
        RecyclerItemClickListener.onRecyclerClickListener {
    private val flickrRecyclerViewAdapter = FlickrRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate called")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        activateToolBar(false)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this, recycler_view, this))
        recycler_view.adapter = flickrRecyclerViewAdapter

        CoroutineScope(Dispatchers.Main).launch {
            val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android, oreo", "en_us", true)

            refreshImages(url)
        }

        Log.d(TAG, "onCreate ends")
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG, ".onItemClick starts")
        Toast.makeText(this, "Short tap at position $position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d(TAG, ".onItemLongClick starts")
        //Toast.makeText(this, "Long tap at position $position", Toast.LENGTH_SHORT).show()
        val photo = flickrRecyclerViewAdapter.getPhoto(position)
        if (photo != null) {
            val intent = Intent(this, PhotoDetailsActivity::class.java)
            intent.putExtra(PHOTO_TRANSFER, photo)
            startActivity(intent)
        }
    }

    private fun createUri(baseURL: String, searchCriteria: String, lang: String, matchAll: Boolean): String {
        Log.d(TAG, ".createUri starts")

        var uri = Uri.parse(baseURL)
        var builder = uri.buildUpon()
        builder = builder.appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
        builder = builder.appendQueryParameter("tags", searchCriteria)
        builder = builder.appendQueryParameter("lang", lang)
        builder = builder.appendQueryParameter("format", "json")
        builder = builder.appendQueryParameter("nojsoncallback", "1")
        uri = builder.build()

        return uri.toString()
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
            R.id.action_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        Log.d(TAG, ".onResume starts")
        super.onResume()

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val queryResult = sharedPref.getString(FLICKR_QUERY, "")

        if (queryResult != null) {
            if (queryResult.isNotEmpty()) {
                CoroutineScope(Dispatchers.Main).launch {
                    val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", queryResult, "en_us", true)
                    refreshImages(url)

                }
            }
        }
    }

    suspend fun getFlickrJsonData(url: String): ArrayList<Photo> {
        return withContext(IO) {
            val data = URL(url).readText()

            val photoList = ArrayList<Photo>()

            val jsonData = JSONObject(data)
            val itemsArray = jsonData.getJSONArray("items")

            for (i in 0 until itemsArray.length()) {
                val jsonPhoto = itemsArray.getJSONObject(i)
                val title = jsonPhoto.getString("title")
                val author = jsonPhoto.getString("author")
                val authorID = jsonPhoto.getString("author_id")
                val tags = jsonPhoto.getString("tags")

                val jsonMedia = jsonPhoto.getJSONObject("media")
                val photoURL = jsonMedia.getString("m")
                val link = photoURL.replaceFirst("_m.jpg", "_b.jpg")

                val photoObject = Photo(title, author, authorID, link, tags, photoURL)
                photoList.add(photoObject)
                Log.d(TAG, ".doInBackground $photoObject")
            }

            photoList
        }
    }

    suspend fun refreshImages(url: String) {

        try {
            val photoList = getFlickrJsonData(url)
            flickrRecyclerViewAdapter.loadNewPhoto(photoList)
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> {
                    "Invalid URL ${e.message}"
                }
                is IOException -> {
                    "IO Exception reading data: ${e.message}"
                }
                is SecurityException -> {
                    "Security Exception: Needs permission? ${e.message}"
                }
                is JSONException -> {
                    "Error processing Json data. ${e.message}"
                }
                else -> {
                    "Unknown error: ${e.message}"

                }
            }
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}