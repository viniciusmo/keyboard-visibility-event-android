package com.viniciusmo.keyboardvisibility

import android.app.Activity
import android.graphics.Rect
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewTreeObserver

class KeyBoardVisibility private constructor(getRootViewStrategy: GetRootViewStrategy,
                                             private var screenDensity: Float) {

    companion object {

        private const val MAGIC_NUMBER = 200.0f

        private fun getDensityScreen(from: Activity): Float {
            return from.resources.displayMetrics.density
        }

        fun build(activity: Activity): KeyBoardVisibility {
            val keyBoardVisibility = KeyBoardVisibility(ActivityGetRootViewStrategy(activity), getDensityScreen(activity))
            return registerAutoDispose(activity, keyBoardVisibility)
        }

        fun build(fragment: Fragment): KeyBoardVisibility {
            val activity = fragment.activity!!
            val keyBoardVisibility = KeyBoardVisibility(FragmentGetRootViewStrategy(fragment), getDensityScreen(activity))
            return registerAutoDispose(activity, keyBoardVisibility)
        }

        private fun registerAutoDispose(activity: Activity, keyBoardVisibility: KeyBoardVisibility): KeyBoardVisibility {
            activity.application.registerActivityLifecycleCallbacks(object : WrapperActivityLifecycleCallback(activity) {
                override fun onTargetActivityDestroyed() {
                    keyBoardVisibility.dispose()
                }
            })
            return keyBoardVisibility
        }

    }

    private var previousState: Boolean = false
    private var keyboarVisibilityListener: KeyBoardVisibilityListener? = null
    private var rootView: View = getRootViewStrategy.getRootView()
    private var onGlobalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener? = null

    init {
        onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rootViewArea = Rect()
            rootView.getWindowVisibleDisplayFrame(rootViewArea)
            val heightDiff = rootView.rootView.height - (rootViewArea.bottom - rootViewArea.top)
            val dp = heightDiff / screenDensity
            val isVisible = dp > MAGIC_NUMBER
            if (previousState != isVisible) {
                previousState = isVisible
                if (isVisible) {
                    this.keyboarVisibilityListener?.onKeyboardOpened?.invoke()
                } else {
                    this.keyboarVisibilityListener?.onKeyboardClose?.invoke()
                }
            }
        }
        rootView.viewTreeObserver?.addOnGlobalLayoutListener(onGlobalLayoutListener)
    }

    private fun dispose() {
        rootView.viewTreeObserver?.removeOnGlobalLayoutListener { onGlobalLayoutListener }
    }

    fun setListener(listener: KeyBoardVisibilityListener.() -> Unit) {
        this.keyboarVisibilityListener = KeyBoardVisibilityListener().apply(listener)
    }
}