
import androidx.annotation.IntDef

/****
 * Keep all the Build/ deployment configurations here
 * Author: Lajesh Dineshkumar
 * Company: Lavaira
 * Created on: 4/3/19
 * Modified on: 4/3/19
 *****/
object Configuration {

    const val SIT_HOST_NAME = "kryptostext-dev.azurewebsites.net"
    const val DEV_HOST_NAME = "kryptostext-dev.azurewebsites.net"
    const val UAT_HOST_NAME = "kryptostext-dev.azurewebsites.net"
    const val PROD_HOST_NAME = "kryptostext-dev.azurewebsites.net"

    private const val SIT = 0

    private const val DEV = 1

    private const val UAT = 2

    private const val PROD = 3

    @DeploymentType
    private val defaultEnvironment = DEV


    // HOST Urls
    private const val URL_SIT =  "https://$SIT_HOST_NAME/api" //Put the SIT url here

    private const val URL_DEV = "https://$DEV_HOST_NAME/api"// Put the development url here

    private const val URL_PROD =  "https://$PROD_HOST_NAME/api" // Put the production url here

    private const val URL_UAT =  "https://$UAT_HOST_NAME/api" // Put the UAT url here

    val baseURL: String
        get() {

            return when (defaultEnvironment) {

                SIT -> URL_SIT

                DEV -> URL_DEV

                UAT -> URL_UAT

                PROD -> URL_PROD

                else -> URL_DEV
            }
        }


    @IntDef(SIT, DEV, UAT, PROD)
    private annotation class DeploymentType
}