package commons;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtils {
	public static void printElementInfo(String name, WebElement element) {
		String marker = "------------------------------";
		System.out.println(marker);
		System.out.println("Element meta-data: " + name);
		System.out.println(marker);
		System.out.println("WebElement object:: " + element);
		System.out.println("Outer HTML:: " + element.getAttribute("outerHTML"));
		System.out.println("Inner HTML:: " + element.getAttribute("innerHTML"));
		System.out.println("Text:: " + element.getText());
		System.out.println("Value::" + element.getAttribute("value"));
		System.out.println("Tag:: " + element.getTagName());
		System.out.println("Displayed?:: " + element.isDisplayed());
		System.out.println("Selected?:: " + element.isSelected());
		System.out.println("Enabled?:: " + element.isEnabled());
		System.out.println(marker);
	}

	public static void takeScreenShot(WebDriver driver) throws Exception {
		TakesScreenshot screenShot = (TakesScreenshot) driver;

		File file2 = screenShot.getScreenshotAs(OutputType.FILE);

		String path = String.format("ErrorOnException" + System.currentTimeMillis() + ".png");

		FileUtils.copyFile(file2, new File(path));
	}
	
	public static String IdentifySecondTab(String actualityTab, WebDriver driver){
		String secondWindowTab="";
		for (String windowHandler : driver.getWindowHandles()) {
			if (!windowHandler.equals(actualityTab)) {
				secondWindowTab = windowHandler;
				System.out.println("secondwindow:" + secondWindowTab);
			}
		}
		return secondWindowTab;
	}
}
