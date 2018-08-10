package com.magorasystems.pmtoolpush.util.fragment

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       return if(getContentViewLayoutRes() != 0){
           inflater.inflate(getContentViewLayoutRes(), container, false)
       }else{
           throw RuntimeException("""It's needed to return correct layout resource
               |in getContentViewLayoutRes() method""".trimMargin())
       }
    }

    @LayoutRes
    abstract fun getContentViewLayoutRes():Int

}
