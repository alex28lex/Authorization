Auth module for Magora's Leopold Api authorization Данный модуль представляет из себя набор экранов которые потребуются для реализации авторизации в приложении,rest окружение и обработчик ошибок от сервера по leopold api. Модуль выполен на языке программирования Kotlin и соответствует структуре blank project

Как интегрировать: 

1)нужно вызвать метод инициализации модуля в Application классе проекта:
```kotlin
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AuthModuleInit.init(this)
    }
}
```
2)т.к договорились использовать cicerone ,то описать навигатор в активности Авторизации вашего проекта,для этого активность должна быть унаследована от BaseActivity, ключи экранов находятся в классе Screens в AuthModule

пример:
```kotlin
override fun navigator(): Navigator {
    return object : com.mgrsys.blankproject.screen.base.BaseNavigator(this, R.id.containerViewGroup) {
        override fun createIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
            return null
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? {
            return when (screenKey) {
                Screens.SIGN_IN -> SignInFragment.newInstance()
                Screens.SIGN_UP -> SignUpFragment.newInstance()
                Screens.MAIN -> UserListFragment.newInstance()
                Screens.CHANGE_PASS -> ChangePassFragment.newInstance()
                else -> null
            }
        }
    }
}
```
3)настроить под проект rest: указать endpoint в RestUrls классе, настроить Rest Client url's для методов авторизации в классе AuthRestClient

4)Данные по результамтам логина(access token,refresh toke,user data) хранятся в SessionManager  из AuthModule
