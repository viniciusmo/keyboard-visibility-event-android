package com.viniciusmo.keyboardvisibility

class KeyBoardVisibilityListener(var onKeyboardOpened: () -> Unit = {}, var onKeyboardClose: () -> Unit = {}) {

    fun onOpened(onKeyboardOpened: () -> Unit) {
        this.onKeyboardOpened = onKeyboardOpened
    }

    fun onClosed(onKeyboardClose: () -> Unit) {
        this.onKeyboardClose = onKeyboardClose
    }

}