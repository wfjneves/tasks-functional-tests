package neves.jesus.felipe.wesley.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

public class HelthCheckIT {

	@Test
	public void healthCheck() throws MalformedURLException {
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.6:4444/wd/hub"), DesiredCapabilities.chrome());
		try {
			driver.navigate().to("http://192.168.1.6:9999/tasks");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		} finally {
			driver.quit();
		}
	}

}
