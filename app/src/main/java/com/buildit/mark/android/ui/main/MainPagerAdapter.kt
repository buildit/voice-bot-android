package com.buildit.mark.android.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.buildit.mark.android.R
import com.buildit.mark.android.ui.main.bills.view.BillsFragment
import com.buildit.mark.android.util.ScreenUtils.dpToPx

/**
 * Created by harshit.laddha on 25/01/2020
 */
class MainPagerAdapter(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val PAGE_COUNT = 5
    var context: Context? = null

    private val mTabsTitle = arrayOf("Transfer", "Deposit", "Ask Mark", "Pay Bill", "Send")

    @SuppressLint("InflateParams")
    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.menu_item_standard, null)
        val title = view.findViewById(R.id.itemTitle) as TextView
        title.text = mTabsTitle[position]
        val icon = view.findViewById(R.id.itemIcon) as ImageView
        if (position != 2) icon.setImageResource(R.drawable.ic_star)
        else {
            val container = view.findViewById(R.id.itemContainer) as LinearLayout
            container.setPadding(0, 0, 0, 20)
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(56F))
            layoutParams.setMargins(0, 0, 0, 0)
//            icon.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//            icon.layoutParams.height = dpToPx(42F)
            icon.layoutParams = layoutParams
            icon.scaleType = ImageView.ScaleType.FIT_START
            icon.setImageResource(R.drawable.mark_md)

        }
        return view
    }

    override fun getItem(pos: Int): Fragment {
        return BillsFragment.newInstance()
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTabsTitle[position]
    }
}