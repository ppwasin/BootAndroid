import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.kotlin.dsl.support.delegates.ProjectDelegate
import java.util.*

const val localSigningConfigs = "debug"
fun BaseAppModuleExtension.setupLocalSign() {
	if (this is ProjectDelegate) {
		val properties = Properties()
		val propertiesFile = project.rootProject.file("keystore.properties")
		if (propertiesFile.exists()) {
			println("file exist")
			properties.load(propertiesFile.inputStream())
			signingConfigs {
				create(localSigningConfigs) {
					storeFile = file(properties.getProperty("storeFile"))
					storePassword = properties.getProperty("storePassword")
					keyAlias = properties.getProperty("keyAlias")
					keyPassword = properties.getProperty("keyPassword")
				}
			}
		}
	}
}