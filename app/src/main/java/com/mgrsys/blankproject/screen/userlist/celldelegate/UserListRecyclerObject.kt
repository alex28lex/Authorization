package com.mgrsys.blankproject.screen.userlist.celldelegate

import com.mgrsys.blankproject.model.entity.Progress
import com.mgrsys.blankproject.model.entity.User

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class UserListRecyclerObject private constructor(
    val user: User?,
    val progress: Progress?
) {
  companion object {

    fun newInstance(user: User): UserListRecyclerObject {
      return UserListRecyclerObject(user, null)
    }

    fun newInstance(progress: Progress): UserListRecyclerObject {
      return UserListRecyclerObject(null, progress)
    }
  }
}
