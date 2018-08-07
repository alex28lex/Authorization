package com.mgrsys.blankproject.fake

import io.reactivex.Flowable

/**
 * @since 2018
 * @author Anton Vlasov - whalemare
 */
class FakeEmptyUsersDataSource {
  override val users: Flowable<List<UserDto>>?
    get() = Flowable.never()

  override fun getUser(login: String): Flowable<UserDto>? {
    return Flowable.never()
  }
}