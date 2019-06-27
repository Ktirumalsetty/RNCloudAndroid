
import android.app.Application
import arch.lavaira.com.android_kotlin_mvvm.di.modules.ActivityBuilderModule
import arch.lavaira.com.android_kotlin_mvvm.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/****
 * Application Component
 * Author: Kondal Rao
 *****/
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilderModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(rnCloudApplication: RNCloudApplication)
}