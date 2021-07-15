package commons;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class EventListener extends AbstractWebDriverEventListener{
	@Override
	public void beforeAlertAccept(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "beforeAlertAccept", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterAlertAccept(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "afterAlertAccept", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterAlertDismiss(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "afterAlertDismiss", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeAlertDismiss(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "beforeAlertDismiss", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
	String msg = String.format("Method Name:: %s url ::%s driver ::%s", "beforeNavigateTo", url, driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
	String msg = String.format("Method Name:: %s url ::%s driver ::%s", "afterNavigateTo", url, driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeNavigateBack(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "beforeNavigateBack", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterNavigateBack(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "afterNavigateBack", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeNavigateForward(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "beforeNavigateForward", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterNavigateForward(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "afterNavigateForward", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "beforeNavigateRefresh", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterNavigateRefresh(WebDriver driver) {
	String msg = String.format("Method Name:: %s driver ::%s", "afterNavigateRefresh", driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
	String msg = String.format("Method Name:: %s By :: %s WebElement:: %s driver ::%s", "beforeFindBy", by, element,
	driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
	String msg = String.format("Method Name:: %s By :: %s WebElement:: %s driver ::%s", "afterFindBy", by, element,
	driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
	String msg = String.format("Method Name:: %s WebElement:: %s driver ::%s", "beforeClickOn", element, driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
	String msg = String.format("Method Name:: %s WebElement:: %s driver ::%s", "afterClickOn", element, driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
	String msg = String.format("Method Name:: %s WebElement:: %s driver ::%s keys: %s", "beforeChangeValueOf",
	element, driver, Arrays.toString(keysToSend));
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
	String msg = String.format("Method Name:: %s WebElement:: %s driver ::%s keys: %s", "afterChangeValueOf",
	element, driver, Arrays.toString(keysToSend));
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void beforeScript(String script, WebDriver driver) {
	String msg = String.format("Method Name:: %s Script:: %s driver ::%s", "beforeScript", script, driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterScript(String script, WebDriver driver) {
	String msg = String.format("Method Name:: %s Script:: %s driver ::%s", "afterScript", script, driver);
	Configuration.LOGGER.debug(msg);
	}


	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
	String msg = String.format("Method Name:: %s windowName:: %s driver ::%s", "beforeSwitchToWindow", windowName,
	driver);
	Configuration.LOGGER.debug(msg);
	}


	public void afterSwitchToWindow(String windowName, WebDriver driver) {
	String msg = String.format("Method Name:: %s windowName:: %s driver ::%s", "afterSwitchToWindow", windowName,
	driver);
	Configuration.LOGGER.debug(msg);
	}


	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
	String msg = String.format("Method Name:: %s OutputType:: %s", "beforeGetScreenshotAs", target);
	Configuration.LOGGER.debug(msg);
	}


	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
	String msg = String.format("Method Name:: %s OutputType:: %s More info:%s", "beforeGetScreenshotAs", target,
	screenshot);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void onException(Throwable throwable, WebDriver driver) {
	String msg = String.format("Method Name:: %s throwable:: %s driver ::%s", "onException", throwable, driver);
	Configuration.LOGGER.error(msg);
	try {
	SeleniumUtils.takeScreenShot(driver);
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	// throwable.printStackTrace();
	}


	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
	String msg = String.format("Method Name:: %s WebElement:: %s driver ::%s", "beforeGetText", element, driver);
	Configuration.LOGGER.debug(msg);
	}


	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
	String msg = String.format("Method Name:: %s WebElement:: %s driver ::%s text: %s", "afterGetText", element, driver, text);
	Configuration.LOGGER.debug(msg);


	}

}
