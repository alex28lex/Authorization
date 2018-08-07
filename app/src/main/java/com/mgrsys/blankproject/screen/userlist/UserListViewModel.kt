package com.mgrsys.blankproject.screen.userlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mgrsys.blankproject.application.di.AppComponentHolder
import com.mgrsys.blankproject.model.entity.User
import com.mgrsys.blankproject.model.usecase.UsersUseCase
import com.mgrsys.blankproject.screen.Screens
import com.mgrsys.blankproject.screen.base.Resource
import com.mgrsys.blankproject.screen.userlist.celldelegate.UserListRecyclerObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 * @author Anton Vlasov
 */
@Suppress("ProtectedInFinal")
class UserListViewModel : ViewModel() {
  @Inject
  protected lateinit var router: Router

  @Inject
  protected lateinit var usersUseCase: UsersUseCase
  private var usersDisposable: Disposable? = null
  private val _usersLiveData = MutableLiveData<Resource<List<UserListRecyclerObject>>>()
  val usersLiveData: LiveData<Resource<List<UserListRecyclerObject>>>
    get() = _usersLiveData

  init {
    AppComponentHolder.component()?.inject(this)

    usersDisposable = usersUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { _usersLiveData.value = Resource.loading() }
        .subscribe(
            { _usersLiveData.value = Resource.success(it.map { UserListRecyclerObject.newInstance(it) }) },
            { _usersLiveData.value = Resource.error(it) }
        )
  }

  fun onUserClick(user: User) {
    router.navigateTo(Screens.USER_DETAILS, user)
  }

  override fun onCleared() {
    super.onCleared()
    usersDisposable?.dispose()
  }
}
