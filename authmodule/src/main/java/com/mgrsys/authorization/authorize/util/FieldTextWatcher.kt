package com.mgrsys.authorization.authorize.util

import android.text.Editable
import android.text.TextWatcher

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
abstract class FieldTextWatcher : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(editable: Editable) {
        afterTextChanged(editable.toString())
    }


    protected abstract fun afterTextChanged(s: String)
}
