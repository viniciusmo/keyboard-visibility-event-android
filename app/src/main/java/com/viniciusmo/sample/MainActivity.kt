package com.viniciusmo.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.viniciusmo.keyboardvisibility.keyboard
import org.jetbrains.anko.alert

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        keyboard {
            onClosed { alert("onKeyboardHide").show() }
            onOpened { alert("onKeyboardVisible").show() }
        }

    }
}
