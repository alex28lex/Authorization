package com.mgrsys.blankproject.screen.userlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgrsys.blankproject.R
import com.mgrsys.blankproject.model.entity.User
import com.mgrsys.blankproject.screen.base.BaseFragment
import com.mgrsys.blankproject.screen.base.Resource
import com.mgrsys.blankproject.screen.base.recycler.BaseCellDelegateAdapter
import com.mgrsys.blankproject.screen.base.recycler.MarginItemDecoration
import com.mgrsys.blankproject.screen.base.recycler.OnCellDelegateClickListener
import com.mgrsys.blankproject.screen.userlist.celldelegate.ProgressCellDelegate
import com.mgrsys.blankproject.screen.userlist.celldelegate.UserCellDelegate
import com.mgrsys.blankproject.screen.userlist.celldelegate.UserListRecyclerObject
import kotlinx.android.synthetic.main.fragment_user_list.*

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 * @author Anton Vlasov
 */
class UserListFragment : BaseFragment() {
  private lateinit var viewModel: UserListViewModel

  private var usersAdapter: BaseCellDelegateAdapter<UserListRecyclerObject> = BaseCellDelegateAdapter()

  companion object {
    fun newInstance(): UserListFragment {
      return UserListFragment()
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_user_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val userCellDelegate = UserCellDelegate()
    val progressCellDelegate = ProgressCellDelegate()

    userCellDelegate.setUserClickListener(object : OnCellDelegateClickListener<User> {
      override fun onCellDelegateClick(itemView: View, position: Int, item: User?) {
        item?.let { viewModel.onUserClick(it) }
      }
    })

    usersAdapter.setCellDelegates(userCellDelegate, progressCellDelegate)

    recyclerView.apply {
      adapter = usersAdapter
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecoration(R.dimen.size_xsmall))
    }
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

    viewModel.usersLiveData.observe(this, Observer {
      when (it?.status) {
        Resource.Status.LOADING -> {
          setProgressViewEnabled(true)
          setEmptyViewEnabled(false)
        }
        Resource.Status.SUCCESS -> {
          setProgressViewEnabled(false)
          setEmptyViewEnabled(false)
          usersAdapter.setItems(it.data)
        }
        Resource.Status.ERROR -> {
          setProgressViewEnabled(false)
          setEmptyViewEnabled(true)
          it.error?.let { showMessage(it.localizedMessage) }
        }
      }
    })
  }
}
