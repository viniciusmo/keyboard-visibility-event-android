package com.viniciusmo.keyboardvisibility

import android.support.v4.app.Fragment
import android.view.View

internal class FragmentGetRootViewStrategy(val fragment:Fragment) : GetRootViewStrategy {

    override fun getRootView(): View {
        val rootView = fragment.view
        return rootView!!
    }

}