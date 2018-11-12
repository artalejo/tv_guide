package artalejo.com.epg.ui.emptyFragment

import android.os.Bundle
import android.view.View
import artalejo.com.epg.R
import artalejo.com.epg.ui.base.BaseFragment
import kotlinx.android.synthetic.main.empty_fragment.*

class EmptyFragment : BaseFragment() {
    companion object {
        val TAG = EmptyFragment::class.java.simpleName
        val TITLE = "TITLE"
        fun newInstance(title: String = "") : EmptyFragment {
            val epgDetailFragment = EmptyFragment()
            val args = Bundle()
            args.putString(TITLE, title)
            epgDetailFragment.arguments = args
            return epgDetailFragment
        }
    }

    override var layout = R.layout.empty_fragment
    private var title: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { title = it.getString(TITLE, "") }
        fragment_title.text = title
    }
}