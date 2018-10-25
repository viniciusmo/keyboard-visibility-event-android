package com.viniciusmo.keyboardvisibility

import android.app.Activity
import android.graphics.Rect
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

class KeyBoardVisibility private constructor(activity: Activity?, fragment: Fragment?) : ViewTreeObserver.OnGlobalLayoutListener {

    constructor(fragment: Fragment) : this(null, fragment)

    constructor(activity: Activity) : this(activity, null)

    companion object {

        private const val MAGIC_NUMBER = 200.0f

    }

    private var previousState:Boolean = false

    private var screenDensity: Float
    private var keyboarVisibilityListener: ((isVisible: Boolean) -> Unit)? = null
    private var rootView: View

    init {
        if (activity == null) {
            rootView = getRootView(fragment!!)
            screenDensity = getDensityScreen(fragment.activity!!)
        } else {
            rootView = getRootView(activity)
            screenDensity = getDensityScreen(activity)
        }
        rootView.viewTreeObserver?.addOnGlobalLayoutListener(this)
    }

    override fun onGlobalLayout() {
        val rootViewArea = Rect()
        rootView.getWindowVisibleDisplayFrame(rootViewArea)
        val heightDiff = rootView.rootView.height - (rootViewArea.bottom - rootViewArea.top)
        val dp = heightDiff / screenDensity
        val isVisible = dp > MAGIC_NUMBER
        if (previousState != isVisible){
            previousState = isVisible
            keyboarVisibilityListener?.invoke(isVisible)
        }
    }

    private fun getRootView(fragment: Fragment): View {
        val rootView = fragment.view
        return rootView!!
    }

    private fun getRootView(from: Activity): ViewGroup {
        return (from.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
    }

    private fun getDensityScreen(from: Activity): Float {
        return from.resources.displayMetrics.density
    }

    fun addKeyboardVisibilityListener(keyboarVisibilityListener: (isVisible: Boolean) -> Unit) {
        this.keyboarVisibilityListener = keyboarVisibilityListener
    }
}