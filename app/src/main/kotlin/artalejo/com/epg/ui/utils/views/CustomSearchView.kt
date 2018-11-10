package artalejo.com.epg.ui.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.ViewAnimationUtils
import android.widget.AutoCompleteTextView
import android.widget.TextView
import artalejo.com.epg.R
import artalejo.com.epg.ui.utils.CustomSearchView.Companion.ANIMATION_DURATION
import artalejo.com.epg.ui.utils.extensions.setGone
import artalejo.com.epg.ui.utils.extensions.setOnQueryTextChange
import artalejo.com.epg.ui.utils.extensions.setVisible

/**
 * This class binds a search view or a searchable toolbar with a reveal animation
 *
 * @property ANIMATION_DURATION time in millis that the animation will last
 */
class CustomSearchView {

    companion object {

        const val ANIMATION_DURATION = 300L

        /**
         * Inflates a search menu on a toolbar and adds a reveal animation when it expands
         *
         * @param toolbar that will inflate a search menu
         * @param onActionCollapse method that will be invoked when [SearchView] collapses
         * @param onActionExpand method that will be invoked when [SearchView] expands
         * @param search method that will be called invoked when [SearchView.OnQueryTextListener]
         * gets called
         */
        fun setCustomSearchToolbar(toolbar: Toolbar,
                                   onActionCollapse: () -> Unit, onActionExpand: () -> Unit,
                                   search: (String) -> Unit) {

            toolbar.inflateMenu(R.menu.search_menu)
            val searchMenu = toolbar.menu
            val itemSearch: MenuItem = searchMenu.findItem(R.id.search)

            itemSearch.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(toolbar,false, false)
                        onActionCollapse.invoke()
                    }
                    else toolbar.setGone()
                    return true
                }

                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    onActionExpand.invoke()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(toolbar, false, true)
                    else toolbar.setVisible()
                    return true
                }
            })

            val searchView = itemSearch.actionView as SearchView
            initSearchView(searchView, search)
        }

        /**
         * Adds a reveal animation on a [SearchView]
         *
         * @param searchView
         * @param onActionCollapse method that will be invoked when [SearchView] collapses
         * @param onActionExpand method that will invoked when [SearchView] expands
         * @param search method that will be invoked when [SearchView.OnQueryTextListener] gets
         * called
         */
        fun setCustomSearchView(searchView: SearchView, startsFromRight: Boolean,
                                onActionCollapse: () -> Unit, onActionExpand: () -> Unit,
                                search: (String) -> Unit) {
            initSearchView(searchView, search)
            searchView.setOnSearchClickListener {
                onCustomSearchViewOptionItemSelected(searchView, startsFromRight, true)
                onActionExpand.invoke()
            }
            searchView.setOnCloseListener {
                onCustomSearchViewOptionItemSelected(searchView, startsFromRight, false)
                onActionCollapse.invoke()
                true
            }
        }

        private fun onCustomSearchViewOptionItemSelected(searchView: SearchView,
                                                         startsFromRight: Boolean,
                                                         show: Boolean): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                circleReveal(searchView, startsFromRight, show)
            else if (show) searchView.onActionViewExpanded()
            else searchView.onActionViewCollapsed()
            return true
        }

        private fun initSearchView(searchView: SearchView, search: (String) -> Unit) {

            searchView.isSubmitButtonEnabled = false

            val searchTextView = searchView.findViewById<AutoCompleteTextView>(android.support.v7.appcompat.R.id.search_src_text)
            try {
                val mCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
                mCursorDrawableRes.isAccessible = true
                mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            searchView.setOnQueryTextChange { search(it) }
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private fun circleReveal(toolbar: Toolbar, containsOverflow: Boolean, show: Boolean) {

            var width = toolbar.width

            if (containsOverflow)
                width -= toolbar.context.resources.getDimensionPixelSize(R.dimen
                        .abc_action_button_min_width_overflow_material)

            val cx = width
            val cy = toolbar.height / 2

            val anim: Animator
            if (show) {
                anim = ViewAnimationUtils.createCircularReveal(toolbar, cx, cy, 0f, width
                        .toFloat())
                anim.duration = ANIMATION_DURATION
                anim.start()
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private fun circleReveal(searchView: SearchView, startsFromRight: Boolean, show: Boolean) {

            val width = searchView.width

            val cx = if (startsFromRight) width else 0
            val cy = searchView.height / 2

            val anim: Animator
            anim = if (show) ViewAnimationUtils.createCircularReveal(searchView, cx, cy, 0f, width.toFloat())
            else ViewAnimationUtils.createCircularReveal(searchView, cx, cy, width.toFloat(),
                    0f)

            anim.duration = ANIMATION_DURATION

            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (!show) {
                        super.onAnimationEnd(animation)
                        searchView.onActionViewCollapsed()
                    }
                }
            })
            anim.start()
        }
    }

}