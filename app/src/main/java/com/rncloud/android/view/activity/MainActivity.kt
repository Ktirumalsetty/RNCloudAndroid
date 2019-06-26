import com.rncloud.android.R
import com.rncloud.android.ui.login.LoginViewModel


/****
 * MainActivity class
 * Author: Lajesh Dineshkumar
 * Company: Lavaira
 * Created on: 4/3/19
 * Modified on: 4/3/19
 *****/
class MainActivity : BaseActivity<LoginViewModel, ActivityMainBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_login

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<LoginViewModel> {
      return LoginViewModel::class.java
    }


}
