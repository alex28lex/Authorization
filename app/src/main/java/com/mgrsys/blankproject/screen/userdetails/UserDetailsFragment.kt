package com.mgrsys.blankproject.screen.userdetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mgrsys.blankproject.R
import com.mgrsys.blankproject.model.entity.User
import com.mgrsys.blankproject.screen.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_details.*

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 * @author Anton Vlasov
 */
class UserDetailsFragment : BaseFragment() {
  private lateinit var viewModel: UserDetailsViewModel

  companion object {
    fun newInstance(user: User): UserDetailsFragment {
      return UserDetailsFragment().apply {
        arguments = Bundle().apply { putParcelable(UserDetailsConsts.KEY_USER, user) }
      }
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_user_details, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel = ViewModelProviders.of(this).get(UserDetailsViewModel::class.java)

    arguments?.let { viewModel.setUser(it.getParcelable(UserDetailsConsts.KEY_USER)) }

    viewModel.userLiveData.observe(this, Observer { user ->
      if (user == null) {
        setProgressViewEnabled(false)
        setEmptyViewEnabled(true)
      } else {
        setProgressViewEnabled(false)
        setEmptyViewEnabled(false)

        Glide.with(this)
            .load(user.avatarUrl)
            .into(avatarImageView)

        nameTextView.text = user.login
      }
    })
  }
}
