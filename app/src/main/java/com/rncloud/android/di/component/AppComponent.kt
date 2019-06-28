import android.app.Application
import com.rncloud.android.RNCloudApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/****
 * Application Component
 * Author: Kondal Rao
 *****/
@Singleton
@Component(
    modules = [
//        AppModule::class,
        NetworkModule::class,
        ActivityBuilderModule::class,
        AndroidSupportInjectionModule::class
    ]
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