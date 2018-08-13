package com.mgrsys.authorization.authorize.screen

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magorasystems.pmtoolpush.screen.authorize.SignInViewModel
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject
import com.magorasystems.pmtoolpush.screen.viewobject.auth.CredentialsVo
import com.magorasystems.pmtoolpush.util.fragment.BaseFragment
import com.mgrsys.authorization.authmodule.R
import io.reactivex.disposables.Disposable


/**
 * Developed 2018.
 *
 * @author mihaylov
 */

class MainFragment : BaseFragment() {

    private lateinit var viewModel: SignInViewModel

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    var viewDisposable: Disposable? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun getContentViewLayoutRes(): Int {
        return R.layout.fragment_main
    }

}