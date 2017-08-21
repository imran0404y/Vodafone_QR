package Libraries;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DesktopWebBrow extends Driver implements NewDriver {

	//public static ThreadLocal<Dictionary> Data = new ThreadLocal<Dictionary>();
	WebDriver remoteDriver = null;

	public DesktopWebBrow() {
	}

	public WebDriver getNewDriver() {
		remoteDriver = getRemoteDriver();
		return remoteDriver;
	}

	@SuppressWarnings("deprecation")
	public WebDriver getRemoteDriver() {

		switch (browser.get().toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", Driver.WorkingDir.get() + "/chromedriver.exe");
			remoteDriver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", Driver.WorkingDir.get() + "/geckodriver.exe");
			// System.setProperty("webdriver.Firefoxdriver", Driver.basepth.get() +
			// "/geckodriver.exe");
			File pathToBinary = new File("C:\\Users\\ImranH2\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("network.proxy.type", 0);
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
			remoteDriver = new FirefoxDriver(ffBinary, firefoxProfile);
			// remoteDriver = new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", Driver.WorkingDir.get() + "/IEDriverServer.exe");
			remoteDriver = new InternetExplorerDriver();
			break;
		}
		return remoteDriver;
	}

}