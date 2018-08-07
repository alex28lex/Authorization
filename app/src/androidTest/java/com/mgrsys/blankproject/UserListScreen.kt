package com.mgrsys.blankproject

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.mgrsys.blankproject.application.App
import com.mgrsys.blankproject.application.di.Scopes
import com.mgrsys.blankproject.environment.BaseActivityTest
import com.mgrsys.blankproject.fake.FakeEmptyUsersDataSource
import com.mgrsys.blankproject.screen.userlist.UserListActivity
import com.mgrsys.blankproject.screen.userlist.di.UserListModule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import io.reactivex.Flowable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import toothpick.Scope
import toothpick.Toothpick

/**
 * For starting test in Kotlin, you DONT NEED click on green triangle. It`s bug on kotlin tests.
 *
 * For starting test go to Edit Configurations -> Android Instrumented Tests -> Add new configuration
 * After that, choose class for starting all tests (or choose single function).
 * @since 2018
 * @author Anton Vlasov - whalemare
 */
@RunWith(AndroidJUnit4::class)
@Suppress("IllegalIdentifier")
class UserListScreen : BaseActivityTest<UserListActivity>(UserListActivity::class.java) {

  lateinit var scope: Scope
  lateinit var app: App

  @Before
  override fun setup() {
    app = InstrumentationRegistry.getTargetContext().applicationContext as App
    scope = Toothpick.openScopes(Scopes.APPLICATION, Scopes.USER_LIST)
  }

  @After
  fun tearDown() {
    Toothpick.reset(scope)
    scope.installModules(UserListModule(getFragment()))
  }

  @Test
  fun should_open_screen() {
    startActivity()
    assertDisplayed(android.R.id.content)
  }

  @Test
  fun should_show_empty_message() {
    scope.installTestModules(object : toothpick.config.Module() {
      init {
        bind(UsersDataSource::class.java)
            .withName(Rest::class.java)
            .toInstance(FakeEmptyUsersDataSource())
      }
    })

    startActivity()

    assertDisplayed(R.string.error_empty)
  }

  @Test
  fun should_show_single_person() {
    scope.installTestModules(object : toothpick.config.Module() {
      init {
        bind(UsersDataSource::class.java)
            .withName(Rest::class.java)
            .toInstance(object {
              override val users: Flowable<List<UserDto>>?
                get() = Flowable.just(
                    listOf(
                        UserDto(
                            id = 1,
                            login = "whalemare",
                            avatarUrl = "https://avatars3.githubusercontent.com/u/9128731?s=460&v=4"
                        )
                    )
                )

              override fun getUser(login: String): Flowable<UserDto>? {
                return Flowable.just(
                    UserDto(
                        id = 1,
                        login = "whalemare",
                        avatarUrl = "https://avatars3.githubusercontent.com/u/9128731?s=460&v=4"
                    )
                )
              }
            })
      }
    })

    startActivity()

    BaristaVisibilityAssertions.assertDisplayed("whalemare")
  }

  @Test
  fun should_show_multi_person() {
    scope.installTestModules(object : toothpick.config.Module() {
      init {
        bind(UsersDataSource::class.java)
            .withName(Rest::class.java)
            .toInstance(object {
              override val users: Flowable<List<UserDto>>?
                get() = Flowable.just(
                    listOf(
                        UserDto(
                            id = 1,
                            login = "whalemare",
                            avatarUrl = "https://avatars3.githubusercontent.com/u/9128731?s=460&v=4"
                        ),
                        UserDto(
                            id = 2,
                            login = "vbolkonsky",
                            avatarUrl = "https://avatars0.githubusercontent.com/u/11171373?s=460&v=4"
                        )
                    )
                )

              override fun getUser(login: String): Flowable<UserDto>? {
                return Flowable.just(
                    UserDto(
                        id = 1,
                        login = "whalemare",
                        avatarUrl = "https://avatars3.githubusercontent.com/u/9128731?s=460&v=4"
                    ),
                    UserDto(
                        id = 2,
                        login = "vbolkonsky",
                        avatarUrl = "https://avatars0.githubusercontent.com/u/11171373?s=460&v=4"
                    )
                )
              }
            })
      }
    })

    startActivity()

    BaristaVisibilityAssertions.assertDisplayed("whalemare")
    BaristaVisibilityAssertions.assertDisplayed("vbolkonsky")
  }
}