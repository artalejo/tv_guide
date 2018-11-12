package artalejo.com.epg.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoLogger

/**
 * BaseFragment
 *
 * Default Fragment that must be subclassed from all the fragments in app. Takes care of
 * injection and restore elements in State changes.
 *
 * Implements [ErrorBaseView]: used to show Exceptons in app
 * Implements [LoadingBaseView]: Show and hide loading screens
 *
 */
abstract class BaseFragment : Fragment(), LoadingBaseView, AnkoLogger, EmptyBaseView {

    abstract var layout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layout, null)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}