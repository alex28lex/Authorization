package com.magorasystems.pmtoolpush.screen.authorize

import android.content.Context
import android.os.Bundle
import android.view.View
import com.magorasystems.pmtoolpush.util.fragment.BaseFragment
import com.mgrsys.authorization.authmodule.R
import io.reactivex.disposables.Disposable
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Error as error
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Loading as loading
import com.magorasystems.pmtoolpush.screen.viewobject.ViewObject.Success as success


/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */

class SignInFragment : BaseFragment() {

    companion object {
        fun newInstance(): SignInFragment {
            val fragment = SignInFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    lateinit var viewModel: SignInViewModel

    var viewDisposable: Disposable? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun getContentViewLayoutRes(): Int {
        return R.layout.fragment_authorize
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

/*    val loginObservable: Observable<Boolean> = RxValidator(inputEmail)
      .apply {
        add(object : ValidateRule {
          override fun errorMessage(): String {
            return getString(R.string.error_validation_login)
          }

          override fun validate(data: String?): Boolean {
            return data != null && data.isNotEmpty()
          }
        })
      }.asObservable()*/
/*
    val passwordObservable: Observable<Boolean> = RxValidator(inputPassword)
      .apply {
        add(object : ValidateRule {
          override fun errorMessage(): String {
            return getString(R.string.error_validation_password)
          }

          override fun validate(data: String?): Boolean {
            return data != null && data.isNotEmpty()
          }
        })
      }.asObservable()

    viewDisposable = RxCombineValidator(loginObservable, passwordObservable)
      .asObservable()
      .distinctUntilChanged()
      .subscribe { valid -> authorizeButton.isEnabled = valid }

    authorizeButton.setOnClickListener {
      clearMessageQueue()
      viewModel.authorize(
        CredentialsVo(
          inputEmail.editText?.text.toString(),
          inputPassword.editText?.text.toString()
        )
      )
    }

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthorizeViewModel::class.java)

    viewModel.authorizePmToolServiceLiveData.let {
      it.observe(this@AuthorizeFragment, Observer {
        it ?: return@Observer
        when (it) {
          is success -> {
            val service = it.data
            when (service?.status) {
              PmToolService.Status.AUTHORIZED -> {
                showMessageInQueue(
                  "${service.tag} authorized",
                  service.user?.displayName.toString()
                )
              }
              PmToolService.Status.NOT_AUTHORIZED, PmToolService.Status.ERROR -> {
                showMessageInQueue(
                  "${service.tag} auth failed",
                  service.error?.localizedMessage.toString()
                )
              }
            }
          }
        }
      })
    }
    viewModel.authorizeSuccess.observe(this, Observer {
      when (it) {
        is loading -> setProgressViewEnabled(true)
        // is success -> setProgressViewEnabled(false)
        is error -> setProgressViewEnabled(false)
      }
    })
    viewModel.navigateToMain.observe(this, Observer {
      Navigator.fromAuthToMain(this)
    })
  }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }

/*  private val toastMessageQueue: ToastMessageQueue by lazy { ToastMessageQueue(context!!) }

  fun showMessageInQueue(title: String, message: String) {
    toastMessageQueue.showMessageInQueue(title, message)
  }

  fun clearMessageQueue() {
    toastMessageQueue.clearMessageQueue()
  }

  fun setProgressViewEnabled(enabled: Boolean) {
    authorizeButton.isEnabled = !enabled
    progressView.visibility(enabled)
    inputEmail.isEnabled = !enabled
    inputPassword.isEnabled = !enabled
  }*/
}