package com.viniciusmo.keyboardvisibility

@KeyboardDsl
class KeyboardVisibilityListenerBuilder(var onKeyboardOpened: () -> Unit = {}, var onKeyboardClose: () -> Unit = {}) {

    fun onOpened(onKeyboardOpened: () -> Unit) {
        this.onKeyboardOpened = onKeyboardOpened
    }

    fun onClosed(onKeyboardClose: () -> Unit) {
        this.onKeyboardClose = onKeyboardClose
    }

    fun build() : KeyboardVisibilityListener =
            KeyboardVisibilityListener(onKeyboardOpened, onKeyboardClose)
}

class KeyboardVisibilityListener(val onKeyboardOpened: () -> Unit, val onKeyboardClose: () -> Unit)