package com.mgrsys.blankproject.screen.userlist.celldelegate

import com.mgrsys.blankproject.screen.base.recycler.BaseProgressCellDelegate

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class ProgressCellDelegate : BaseProgressCellDelegate<UserListRecyclerObject>() {

  override fun `is`(item: UserListRecyclerObject): Boolean {
    return item.progress != null
  }
}
