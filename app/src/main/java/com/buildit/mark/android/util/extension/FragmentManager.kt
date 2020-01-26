package com.buildit.mark.android.util.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.buildit.mark.android.R

/**
 * Created by harshit.laddha on 25/01/2020
 */
internal fun FragmentManager.removeFragment(tag: String, slideIn: Int = R.anim.slide_left,
                                            slideOut: Int = R.anim.slide_right) {
    this.findFragmentByTag(tag)?.let {
        this.beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(slideIn, slideOut)
                .remove(it)
                .commitNow()
    }
}

internal fun FragmentManager.addFragment(containerViewId: Int, fragment: Fragment, tag: String,
                                         slideIn: Int = R.anim.slide_left,
                                         slideOut: Int = R.anim.slide_right) {
    this.beginTransaction().disallowAddToBackStack()
            .setCustomAnimations(slideIn, slideOut)
            .add(containerViewId, fragment, tag)
            .commit()
}

