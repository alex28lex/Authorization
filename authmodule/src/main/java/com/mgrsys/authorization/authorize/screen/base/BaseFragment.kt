package com.magorasystems.pmtoolpush.util.fragment

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mgrsys.authorization.authorize.model.view.MessageView

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
abstract class BaseFragment : Fragment(), MessageView {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return if (getContentViewLayoutRes() != 0) {
            inflater.inflate(getContentViewLayoutRes(), container, false)
        } else {
            throw RuntimeException("""It's needed to return correct layout resource
               |in getContentViewLayoutRes() method""".trimMargin())
        }
    }

    @LayoutRes
    abstract fun getContentViewLayoutRes(): Int

    override fun showMessage(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
        }
    }
}
