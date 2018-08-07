package com.mgrsys.blankproject.screen.base.recycler

import android.content.res.Resources
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
class MarginItemDecoration @JvmOverloads constructor(@param:DimenRes @field:DimenRes
                                                     private val marginLeft: Int,
                                                     @param:DimenRes @field:DimenRes
                                                     private val marginTop: Int,
                                                     @param:DimenRes @field:DimenRes
                                                     private val marginRight: Int,
                                                     @param:DimenRes @field:DimenRes
                                                     private val marginBottom: Int,
                                                     @param:DimenRes @field:DimenRes
                                                     private val marginBetween: Int,
                                                     orientationMode: OrientationMode? = OrientationMode.VERTICAL) : RecyclerView.ItemDecoration() {

  private val orientationMode: OrientationMode

  constructor(@DimenRes margin: Int) : this(margin, margin, margin, margin, margin,
      OrientationMode.VERTICAL) {
  }

  constructor(@DimenRes margin: Int,
              orientationMode: OrientationMode) : this(margin, margin, margin, margin, margin,
      orientationMode) {
  }

  constructor(@DimenRes marginBorder: Int,
              @DimenRes marginBetween: Int) : this(marginBorder, marginBorder, marginBorder, marginBorder, marginBetween,
      OrientationMode.VERTICAL) {
  }

  constructor(@DimenRes marginBorder: Int,
              @DimenRes marginBetween: Int,
              orientationMode: OrientationMode) : this(marginBorder, marginBorder, marginBorder, marginBorder, marginBetween,
      orientationMode) {
  }

  constructor(@DimenRes marginHorizontal: Int,
              @DimenRes marginVertical: Int,
              @DimenRes marginBetween: Int) : this(marginHorizontal, marginVertical, marginHorizontal, marginVertical, marginBetween,
      OrientationMode.VERTICAL) {
  }

  constructor(@DimenRes marginHorizontal: Int,
              @DimenRes marginVertical: Int,
              @DimenRes marginBetween: Int,
              orientationMode: OrientationMode) : this(marginHorizontal, marginVertical, marginHorizontal, marginVertical, marginBetween,
      orientationMode) {
  }

  init {
    this.orientationMode = orientationMode ?: OrientationMode.VERTICAL
  }

  override fun getItemOffsets(outRect: Rect,
                              view: View,
                              parent: RecyclerView,
                              state: RecyclerView.State?) {
    val resources = view.resources
    val pxLeft = getPixelSize(resources, marginLeft)
    val pxTop = getPixelSize(resources, marginTop)
    val pxRight = getPixelSize(resources, marginRight)
    val pxBottom = getPixelSize(resources, marginBottom)
    val pxBetween = getPixelSize(resources, marginBetween) / 2

    val count = parent.adapter.itemCount - 1
    val position = parent.getChildAdapterPosition(view)
    val firstPosition = position == 0
    val lastPosition = position == count

    when (orientationMode) {
      OrientationMode.HORIZONTAL -> {
        if (firstPosition) {
          outRect.left = pxLeft
          outRect.right = pxBetween
        } else if (lastPosition) {
          outRect.left = pxBetween
          outRect.right = pxRight
        } else {
          outRect.left = pxBetween
          outRect.right = pxBetween
        }
        outRect.top = pxTop
        outRect.bottom = pxBottom
      }
      OrientationMode.VERTICAL -> {
        if (firstPosition) {
          outRect.top = pxTop
          outRect.bottom = pxBetween
        } else if (lastPosition) {
          outRect.top = pxBetween
          outRect.bottom = pxBottom
        } else {
          outRect.top = pxBetween
          outRect.bottom = pxBetween
        }
        outRect.left = pxLeft
        outRect.right = pxRight
      }
      else -> throw IllegalStateException(
          "There is no such orientation mode. Please, choose from " +
              "OrientationMode.HORIZONTAL or OrientationMode.VERTICAL values. " +
              "Or choose nothing to default OrientationMode.VERTICAL value.")
    }
  }

  private fun getPixelSize(resources: Resources, @DimenRes margin: Int): Int {
    return if (margin == 0) 0 else resources.getDimensionPixelSize(margin)
  }

  enum class OrientationMode {
    HORIZONTAL,
    VERTICAL
  }
}
