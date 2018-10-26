package com.viniciusmo.keyboardvisibility

import android.app.Activity
import android.view.View
import android.view.ViewGroup

internal class ActivityGetRootViewStrategy(private val activity: Activity) : GetRootViewStrategy {

    override fun getRootView(): View {
        return (activity.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
    }

}