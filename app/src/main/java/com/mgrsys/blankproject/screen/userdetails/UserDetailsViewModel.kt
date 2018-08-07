package com.mgrsys.blankproject.screen.userdetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mgrsys.blankproject.model.entity.User

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 * @author Anton Vlasov
 */
class UserDetailsViewModel : ViewModel() {
  private val _userLiveData = MutableLiveData<User>()
  val userLiveData: LiveData<User>
    get() = _userLiveData

  fun setUser(user: User) {
    _userLiveData.value = user
  }
}
