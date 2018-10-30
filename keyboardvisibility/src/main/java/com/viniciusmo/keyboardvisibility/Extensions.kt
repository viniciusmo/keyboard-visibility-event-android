package com.viniciusmo.keyboardvisibility

import android.app.Activity
import android.support.v4.app.Fragment

@DslMarker
annotation class KeyboardDsl

fun Activity.keyboard(listener: KeyboardVisibilityListenerBuilder.() -> Unit) {
    return KeyBoardVisibility.build(this).setListener(listener)
}

fun Fragment.keyboard(listener: KeyboardVisibilityListenerBuilder.() -> Unit) {
    return KeyBoardVisibility.build(this).setListener(listener)
}