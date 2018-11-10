package artalejo.com.epg.ui.home

import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseActivity
import artalejo.com.epg.ui.base.BaseFragment
import artalejo.com.epg.ui.epg.EpgFragment
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity: BaseActivity(), HomeView, BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    private var epgFragment: EpgFragment? = null

    override var layout = R.layout.home_activity

    override fun onViewLoaded() {
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        bottom_navigation.selectedItemId = R.id.home_navigation
    }

    override fun onBackPressed() {
        epgFragment?.let {
            if (it.isAdded) super.onBackPressed()
        } ?: run {
            clickOnItem({
                epgFragment = epgFragment?.let { it } ?: EpgFragment.newInstance()
                epgFragment
            }, replaceFragment = true)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_navigation -> {
                clickOnItem({
                    epgFragment = epgFragment?.let { it } ?: EpgFragment.newInstance()
                    epgFragment
                }, replaceFragment = true)
                return true
            }
            R.id.live_navigation -> {
//                clickOnItem({
//                    epgFragment = epgFragment?.let { it } ?: EpgFragment.newInstance()
//                    epgFragment
//                }, replaceFragment = true)
                return true
            }
            R.id.tv_guide_navigation -> {
//                clickOnItem({
//                    epgFragment = epgFragment?.let { it } ?: EpgFragment.newInstance()
//                    epgFragment
//                }, replaceFragment = true)
                return true
            }
            R.id.catch_up_navigation -> {
//                clickOnItem({
//                    epgFragment = epgFragment?.let { it } ?: EpgFragment.newInstance()
//                    epgFragment
//                }, replaceFragment = true)
                return true
            }
            R.id.library_navigation -> {
//                clickOnItem({
//                    epgFragment = epgFragment?.let { it } ?: EpgFragment.newInstance()
//                    epgFragment
//                }, replaceFragment = true)
                return true
            }
            else -> return false
        }
    }

    private fun clickOnItem(fragmentGenerator: () -> BaseFragment?, replaceFragment: Boolean) {
        val homeBaseFragment = fragmentGenerator()
        if (homeBaseFragment?.isAdded == true) return
        if (false == homeBaseFragment?.isVisible) {
            if (replaceFragment) replaceFragment(R.id.fragment_container, homeBaseFragment)
            else addFragment(R.id.fragment_container, homeBaseFragment)
        }
    }
}