
import com.rncloud.android.view.activity.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/****
 * The module which provides the android injection service to activities
 * Author: Lajesh Dineshkumar
 * Company: Lavaira
 * Created on: 4/3/19
 * Modified on: 4/3/19
 *****/
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
}