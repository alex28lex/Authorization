package com.mgrsys.blankproject.application.di

/*import com.magorasystems.pmtoolpush.screen.authorize.AuthorizeViewModel*/
import dagger.Component
import javax.inject.Singleton

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
@Singleton
@Component(modules = [AuthAppModule::class, AuthModuleRetrofitModule::class])
interface AuthModuleComponent {

}