package com.mgrsys.blankproject.screen.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), Bindable<T>