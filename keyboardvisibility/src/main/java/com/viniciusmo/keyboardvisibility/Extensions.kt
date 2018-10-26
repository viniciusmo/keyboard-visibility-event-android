package com.viniciusmo.keyboardvisibility

import android.app.Activity
import android.support.v4.app.Fragment

fun Activity.keyboard(listener: KeyBoardVisibilityListener.() -> Unit) {
    return KeyBoardVisibility.build(this).setListener(listener)
}

fun Fragment.keyboard(listener: KeyBoardVisibilityListener.() -> Unit) {
    return KeyBoardVisibility.build(this).setListener(listener)
}