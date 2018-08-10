package com.mgrsys.blankproject.model.repository.users


import io.reactivex.Single

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
interface AuthRepository {

  fun login(): Single<Void>

}
