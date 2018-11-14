package artalejo.com.epg.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseActivity
import artalejo.com.epg.ui.base.BaseFragment
import artalejo.com.epg.ui.emptyFragment.EmptyFragment
import artalejo.com.epg.ui.epg.EpgFragment
import artalejo.com.epg.ui.epgDetail.EpgDetailFragment
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity: BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        private const val EXTRA_FRAGMENT_EPG = "EXTRA_FRAGMENT_EPG"
        private const val EXTRA_FRAGMENT_LIVE = "EXTRA_FRAGMENT_LIVE"
        private const val EXTRA_FRAGMENT_GUIDE = "EXTRA_FRAGMENT_GUIDE"
        private const val EXTRA_FRAGMENT_CATCH_UP = "EXTRA_FRAGMENT_CATCH_UP"
        private const val EXTRA_FRAGMENT_LIBRARY = "EXTRA_FRAGMENT_LIBRARY"
        private const val CURRENT_TAB_SELECTED = "CURRENT_TAB_SELECTED"

        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    private var epgFragment: EpgFragment? = null
    private var epgLiveFragment: EpgDetailFragment? = null
    private var epgGuideFragment: EmptyFragment? = null
    private var epgCatchUpFragment: EpgDetailFragment? = null
    private var epgLibraryFragment: EmptyFragment? = null
    private var defaultTabMenuId = R.id.home_navigation

    override var layout = R.layout.home_activity

    override fun onViewLoaded(savedInstanceState: Bundle?) {
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        checkRestoringState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            outState.putInt(CURRENT_TAB_SELECTED, bottom_navigation.selectedItemId)
            supportFragmentManager.putFragmentSafe(outState, EXTRA_FRAGMENT_EPG, epgFragment)
            supportFragmentManager.putFragmentSafe(outState, EXTRA_FRAGMENT_LIVE, epgLiveFragment)
            supportFragmentManager.putFragmentSafe(outState, EXTRA_FRAGMENT_GUIDE, epgGuideFragment)
            supportFragmentManager.putFragmentSafe(outState, EXTRA_FRAGMENT_CATCH_UP, epgCatchUpFragment)
            supportFragmentManager.putFragmentSafe(outState, EXTRA_FRAGMENT_LIBRARY, epgLibraryFragment)
        }
    }

    private fun checkRestoringState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = defaultTabMenuId
        } else {
            bottom_navigation.selectedItemId = savedInstanceState.getInt(CURRENT_TAB_SELECTED, defaultTabMenuId)
            epgFragment = supportFragmentManager.getFragment(savedInstanceState, EXTRA_FRAGMENT_EPG) as? EpgFragment
            epgLiveFragment = supportFragmentManager.getFragment(savedInstanceState, EXTRA_FRAGMENT_LIVE) as? EpgDetailFragment
            epgGuideFragment = supportFragmentManager.getFragment(savedInstanceState, EXTRA_FRAGMENT_GUIDE) as? EmptyFragment
            epgCatchUpFragment = supportFragmentManager.getFragment(savedInstanceState, EXTRA_FRAGMENT_CATCH_UP) as? EpgDetailFragment
            epgLibraryFragment = supportFragmentManager.getFragment(savedInstanceState, EXTRA_FRAGMENT_LIBRARY) as? EmptyFragment
        }
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
                clickOnItem({
                    epgLiveFragment = epgLiveFragment?.let { it } ?: EpgDetailFragment.newInstance(isLiveShow = true)
                    epgLiveFragment
                }, replaceFragment = true)
                return true
            }
            R.id.tv_guide_navigation -> {
                clickOnItem({
                    epgGuideFragment = epgGuideFragment?.let { it } ?: EmptyFragment.newInstance(getString(R.string.guide))
                    epgGuideFragment
                }, replaceFragment = true)
                return true
            }
            R.id.catch_up_navigation -> {
                clickOnItem({
                    epgCatchUpFragment = epgCatchUpFragment?.let { it } ?: EpgDetailFragment.newInstance(isLiveShow = false)
                    epgCatchUpFragment
                }, replaceFragment = true)
                return true
            }
            R.id.library_navigation -> {
                clickOnItem({
                    epgLibraryFragment = epgLibraryFragment?.let { it } ?: EmptyFragment.newInstance(getString(R.string.library))
                    epgLibraryFragment
                }, replaceFragment = true)
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