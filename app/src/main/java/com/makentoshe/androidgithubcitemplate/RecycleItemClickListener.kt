package com.makentoshe.androidgithubcitemplate

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class RecycleItemClickListener: RecyclerView.OnItemTouchListener {
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onLongItemClick(view: View?, position: Int)
    }

    private lateinit var mGestureDetector: GestureDetector

    constructor(
        context: Context?,
        recyclerView: RecyclerView,
        listener: OnItemClickListener
    ) {
        mListener = listener
        mGestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child: View? = recyclerView.findChildViewUnder(e.x, e.y)
                if (child != null && mListener != null) {
                    mListener!!.onLongItemClick(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView: View? = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector!!.onTouchEvent(e)) {
            mListener!!.onItemClick(childView, view.getChildAdapterPosition(childView))
            return true
        }
        return false
    }


    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }
}