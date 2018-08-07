package com.mgrsys.blankproject.model.repository.users

import com.mgrsys.blankproject.model.entity.User
import io.reactivex.Single

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
interface UsersRepository {

  fun user(login: String): Single<User>

  fun users(): Single<List<User>>
}
