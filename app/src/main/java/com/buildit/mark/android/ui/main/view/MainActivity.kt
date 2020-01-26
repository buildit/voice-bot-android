package com.buildit.mark.android.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import com.buildit.mark.android.R
import com.buildit.mark.android.ui.base.view.BaseActivity
import com.buildit.mark.android.ui.login.view.LoginActivity
import com.buildit.mark.android.util.extension.removeFragment
import kotlinx.android.synthetic.main.app_bar_navigation.*
import javax.inject.Inject
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.buildit.mark.android.ui.main.MainPagerAdapter
import com.buildit.mark.android.ui.main.chat.view.ChatFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    internal lateinit var mainPagerAdapter: MainPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainPagerAdapter.context = this
        setupToolbar()
        setupBottomBar()
    }

    private fun setupBottomBar() {
        val pager = viewPager as ViewPager
        val bottomTab = tabLayout as TabLayout

        pager.adapter = mainPagerAdapter
        bottomTab.setupWithViewPager(pager)

        for (i in 0 until bottomTab.tabCount) {
            val tab = bottomTab.getTabAt(i)
            tab?.customView = mainPagerAdapter.getTabView(i)
        }

        bottomTab.getTabAt(0)?.customView?.isSelected = true

        bottomTab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                onTabSelectedListener(tab.position, bottomTab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                onTabSelectedListener(tab.position, bottomTab)
            }
        })
    }

    private fun onTabSelectedListener(position: Int, bottomTab: TabLayout) {
        if (position == 2) {
            openChatDialog()
            bottomTab.getTabAt(2)?.customView?.isSelected = false
            bottomTab.getTabAt(3)?.customView?.isSelected = true
            bottomTab.setScrollPosition(3, 0F, true)
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun openChatDialog() {
        val chatFragment = ChatFragment.newInstance() as DialogFragment
        chatFragment.isCancelable = false
        chatFragment.dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        chatFragment.dialog?.window?.setGravity(Gravity.BOTTOM or Gravity.LEFT)
        chatFragment.show(supportFragmentManager, "ChatFragment")
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(ContextCompat
                .getDrawable(this, R.drawable.ic_arrow_back_white_24dp))
        actionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            openLoginActivity()
        }
    }

    override fun onBackPressed() {
        openLoginActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager.removeFragment(tag = tag)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }

    override fun onFragmentAttached() { }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            openLoginActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
