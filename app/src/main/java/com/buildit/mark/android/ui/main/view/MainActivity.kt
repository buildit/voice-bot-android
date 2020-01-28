package com.buildit.mark.android.ui.main.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobileconnectors.lex.interactionkit.config.InteractionConfig
import com.buildit.mark.android.R
import com.buildit.mark.android.ui.base.view.BaseActivity
import com.buildit.mark.android.ui.login.view.LoginActivity
import com.buildit.mark.android.ui.main.MainPagerAdapter
import com.buildit.mark.android.ui.main.chat.view.ChatFragment
import com.buildit.mark.android.ui.main.chat.view.ChatFragmentDataCallback
import com.buildit.mark.android.util.extension.removeFragment
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


class MainActivity : BaseActivity(), HasSupportFragmentInjector, ChatFragmentDataCallback {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var mainPagerAdapter: MainPagerAdapter
    internal lateinit var awsMobileClient: AWSMobileClient
    internal lateinit var lexInteractionConfig: InteractionConfig
    private lateinit var awsCredentials: AWSCredentials
    private var botRegion: String = "eu-west-1"
    private var hasVoicePermissions: Boolean = false
    private val REQUEST_RECORDING_PERMISSIONS_RESULT = 75

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainPagerAdapter.context = this
        setupToolbar()
        setupLexConfig()
    }

    private fun setupLexConfig() {
        awsMobileClient = AWSMobileClient.getInstance()
        awsMobileClient.initialize(this, object: Callback<UserStateDetails> {
            override fun onResult(result: UserStateDetails?) {
                val identityId = awsMobileClient.identityId
                awsCredentials = awsMobileClient.credentials
                var botName: String? = null
                var botAlias: String? = null
                var lexConfig: JSONObject
                try {
                    lexConfig = awsMobileClient.configuration.optJsonObject("Lex")
                    lexConfig = lexConfig.getJSONObject(lexConfig.keys().next())

                    botName = lexConfig.getString("Name")
                    botAlias = lexConfig.getString("Alias")
                    botRegion = lexConfig.getString("Region")
                } catch (e: JSONException) {
                    Log.e("AWSMobileClient", "onResult: Failed to read configuration", e)
                }


                lexInteractionConfig = InteractionConfig(
                        botName,
                        botAlias,
                        identityId)
                runOnUiThread {
                    setupBottomBar()
                }
            }

            @SuppressLint("ShowToast")
            override fun onError(e: Exception?) {
                Log.d("LexError", e.toString())
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error initializing lex. " +
                            "Please close the app and try again", Toast.LENGTH_LONG).show()
                }
            }
        })
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
            openChatDialog("Welcome")
            bottomTab.getTabAt(2)?.customView?.isSelected = false
            bottomTab.getTabAt(3)?.customView?.isSelected = true
            bottomTab.setScrollPosition(3, 0F, true)
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun openChatDialog(initIntent: String) {
        if (!hasVoicePermissions) {
            requestVoicePermissions()
        }
        val chatFragment = ChatFragment.newInstance(initIntent) as DialogFragment
        chatFragment.isCancelable = false
        chatFragment.dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        chatFragment.dialog?.window?.setGravity(Gravity.BOTTOM or Gravity.LEFT)
        chatFragment.show(supportFragmentManager, "chatFragment")
    }

    private fun requestVoicePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORDING_PERMISSIONS_RESULT)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_RECORDING_PERMISSIONS_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext,
                        "Please grant permissions to record audio for accessing " +
                                "mark's voice interface", Toast.LENGTH_SHORT).show()
            }
        }
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

    override fun getInteractionConfig(): InteractionConfig {
        return lexInteractionConfig
    }

    override fun getAWSMobileClient(): AWSMobileClient {
        return awsMobileClient
    }

    override fun getAWSRegion(): String {
        return botRegion
    }
}
