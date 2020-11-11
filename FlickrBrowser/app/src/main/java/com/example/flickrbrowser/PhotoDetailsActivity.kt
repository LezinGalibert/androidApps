package com.example.flickrbrowser

import academy.learnprogramming.flickrbrowser.R
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_photo_details.*

class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        activateToolBar(true)

        val photo = intent.getParcelableExtra<Photo>(PHOTO_TRANSFER)

        if (photo != null) {
            photo_title.text = resources.getString(R.string.photo_title_text,photo.title)
            photo_tags.text = resources.getString(R.string.photo_tags_text,photo.tags)
            photo_author.text = resources.getString(R.string.photo_author_text, "my", "red", "car")

            Picasso.with(this).load(photo.link).error(R.drawable.image_logo).into(photo_image)
        }

    }
}