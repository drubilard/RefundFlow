package commons;

import java.io.File;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Configuration {
	public static final String APP_URL = "https://pst.flow.tuxidev.cl/"; // url de aplicacion de flow
	public static final String CORREO_URL = "https://correotemporal.org/"; // url de correo
	public static final String PASSWORD_CORREO = "1234aa";
	public static String DRIVER_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
			+ File.separator + "resources" + File.separator + "drivers" + File.separator; // ruta del recurso para
																							// setear la property (como
																							// cuando se busca el driver
																							// de chrome)
	public static String mediopagoDefault ="servipag";

	public static final String CHROME_DRIVER = DRIVER_DIR + "chromedriver"; // seteo de ruta para chrome driver
	public static final String GECKO_DRIVER = DRIVER_DIR + "geckodriver"; // setei de ruta para firefox driver
	public static final String USER = "drubilar@tuxpan.com";
	public static final String PASSWORD = "123456aa";
	public static final String ORGANIZACION = "89862200-2";
	public static final String LOCALANULACION = "29724172";
	public static final String FECHAANULACION = "25/11/2020";

	public static final Logger LOGGER = (Logger) LogManager.getLogger("CSE");

	private static String modifyifWindows(String inPath) { // funcion para identificar SO paras saber que extencino
															// poner
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			return inPath + ".exe";
		} else {
			return inPath;
		}
	}

	public static WebDriver createChromeDriver() { // funcion que genera la property adecuada apra chrome
		System.setProperty("webdriver.chrome.driver", modifyifWindows(CHROME_DRIVER));
		return new ChromeDriver();
	}

	public static WebDriver createFirefoxDriver() { // funcion que genera la property adecuada para firefox
		System.setProperty("webdriver.gecko.driver", modifyifWindows(GECKO_DRIVER));
		return new FirefoxDriver();
	}

	public static WebDriver createChromeDriverWithOptions(String downloadFilePath) {

		System.setProperty("webdriver.chrome.driver", modifyifWindows(CHROME_DRIVER));

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);

		return new ChromeDriver(options);

	}

	public static String IdentifySecondTab(String actualityTab, WebDriver driver) {
		String secondWindowTab = "";
		for (String windowHandler : driver.getWindowHandles()) {
			if (!windowHandler.equals(actualityTab)) {
				secondWindowTab = windowHandler;
				// System.out.println("secondwindow:" + secondWindowTab);
			}
		}
		return secondWindowTab;
	}

}
