
import com.rncloud.android.api.APIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/****
 * Application Module which is responsible for providing the app wide instances.
 * Author: Lajesh Dineshkumar
 * Company: Lavaira
 * Created on: 4/3/19
 * Modified on: 4/3/19
 *****/
@Module(includes = [(ViewModelModule::class)])
class AppModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : APIService {
        return retrofit.create(APIService::class.java)
    }


//    @Provides
//    @Singleton
//    fun provideUserkRepository(api: APIService, scheduler: SchedulerContract): UserRepository {
//        return UserRepository(api, scheduler)
//    }

//    @Provides
//    @Singleton
//    fun provideScheduler(): SchedulerContract {
//        return SchedulerProvider()
//    }
}