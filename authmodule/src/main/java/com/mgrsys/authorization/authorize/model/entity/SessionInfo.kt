package com.mgrsys.blankproject.model.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
@Suppress("MemberVisibilityCanBePrivate")
data class SessionInfo(
    @SerializedName("accessToken")
    val accessToken: String? = "",
    @SerializedName("accessTokenExpire")
    val accessTokenExpire: String? = "",
    @SerializedName("refreshToken")
    val refreshToken: String? = ""
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString())

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(accessToken)
    parcel.writeString(accessTokenExpire)
    parcel.writeString(refreshToken)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<SessionInfo> {
    override fun createFromParcel(parcel: Parcel): SessionInfo {
      return SessionInfo(parcel)
    }

    override fun newArray(size: Int): Array<SessionInfo?> {
      return arrayOfNulls(size)
    }
  }
}