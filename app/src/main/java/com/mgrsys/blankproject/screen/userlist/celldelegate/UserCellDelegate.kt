package com.mgrsys.blankproject.screen.userlist.celldelegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.mgrsys.blankproject.R
import com.mgrsys.blankproject.model.entity.User
import com.mgrsys.blankproject.screen.base.recycler.BaseCellDelegate
import com.mgrsys.blankproject.screen.base.recycler.BaseViewHolder
import com.mgrsys.blankproject.screen.base.recycler.OnCellDelegateClickListener
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
open class UserCellDelegate : BaseCellDelegate<UserListRecyclerObject>() {
  private var userClickListener: OnCellDelegateClickListener<User>? = null

  fun setUserClickListener(userClickListener: OnCellDelegateClickListener<User>) {
    this.userClickListener = userClickListener
  }

  override fun `is`(item: UserListRecyclerObject): Boolean {
    return item.user != null
  }

  override fun holder(parent: ViewGroup): BaseViewHolder<UserListRecyclerObject> {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.item_user, parent, false)
    return UserViewHolder(view)
  }

  protected inner class UserViewHolder(itemView: View) : BaseViewHolder<UserListRecyclerObject>(itemView) {
    val avatarImageView = itemView.imageAvatar
    val nameTextView = itemView.textName

    override fun bind(item: UserListRecyclerObject) {
      val user: User? = item.user

      Glide.with(itemView)
          .load(user?.avatarUrl)
          .apply(RequestOptions.bitmapTransform(CircleCrop()))
          .into(avatarImageView)

      nameTextView.text = user?.login

      itemView.setOnClickListener { _ -> userClickListener!!.onCellDelegateClick(itemView, adapterPosition, user) }
    }
  }
}
