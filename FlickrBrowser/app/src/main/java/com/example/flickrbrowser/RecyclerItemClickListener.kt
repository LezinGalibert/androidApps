package com.example.flickrbrowser

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(context : Context,
                                recyclerView: RecyclerView,
                                private val listener: onRecyclerClickListener) : RecyclerView.SimpleOnItemTouchListener() {
    private val TAG = "RecyclerICL"

    interface onRecyclerClickListener {
        fun onItemClick(view : View, position : Int)
        fun onItemLongClick(view : View, position: Int)
    }

    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG, ".onLongPress: starts")
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            Log.d(TAG, ".onLongPress calling listener .onItemClick")
            if (childView != null) {
                listener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            Log.d(TAG, ".onSingleTap: starts")
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            Log.d(TAG, ".onSingleTap: calling listener .onItemClick")
            if (childView != null) {
                listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
            return true
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, ".onInterceptTouchevent: starts $e")

        val result = gestureDetector.onTouchEvent(e)

        Log.d(TAG, ".onInterceptTouchEvent() resturning: $result")

        return result
    }
}