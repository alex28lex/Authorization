package com.magorasystems.pmtoolpush.screen.viewobject

/**
 * Developed 2018.
 *
 * @author Valentin S.Bolkonsky
 */
sealed class ViewObject<out T>(val data: T? = null) {

    class Loading<out T>(data: T? = null) : ViewObject<T>(data)
    class Success<out T>(data: T? = null) : ViewObject<T>(data)
    class Error<out T>(val error: String, data: T? = null) : ViewObject<T>(data)
}