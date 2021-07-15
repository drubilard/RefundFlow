package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAutomator {
	private WebDriver driver;
	private WebDriverWait ewait;
	private Actions action;
	private JavascriptExecutor jse;
	private void setDriver(WebDriver normalDriver) {
		EventFiringWebDriver edriver = new EventFiringWebDriver(normalDriver);
		edriver.register(new EventListener());
		driver = edriver;
		ewait = new WebDriverWait(normalDriver, 30);
		action = new Actions(normalDriver);
		 jse = (JavascriptExecutor) normalDriver;

	}

	private WebDriver createLocalDriver(Browser browser) throws Exception {
		System.out.println("Local driver - git");
		WebDriver normalDriver = null;
		switch (browser) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", modifyIfWindows(Configuration.CHROME_DRIVER));
			normalDriver = new ChromeDriver();
			break;
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", modifyIfWindows(Configuration.GECKO_DRIVER));
			normalDriver = new FirefoxDriver();
			break;
		default:
			throw new Exception("Unsupported browser: " + browser);
		}

		return normalDriver;
	}
	
	public WebAutomator(Browser browser) throws Exception {
		WebDriver normalDriver = createLocalDriver(browser);
		this.setDriver(normalDriver);
		
	}
	private static String modifyIfWindows(String inPath) {
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			return inPath + ".exe";
		} else {
			return inPath;
		}
	}
	public void type(WebElement element, String text) {
		element.sendKeys(text);
	}
	public boolean isDispleyed(WebElement element) {
		return element.isDisplayed();
	}

	public void type(By locator, String text) {
		find(locator).sendKeys(text);
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public void visit(String url) {
		driver.get(url);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public WebDriverWait getWaiter() {
		return this.ewait;
	}

	public void closeAll() {
		driver.quit();
	}

	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public WebElement find(By locator, Integer timeout) {
		WebElement element = new WebDriverWait(driver, timeout)
				.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	public void click(By locator) {
		find(locator).click();
	}

	public void click(WebElement element) {
		element.click();
	}

	public void click(By locator, Integer timeout) {
		waitUntilClickable(locator, timeout);
		find(locator).click();
	}

	public void click(WebElement element, Integer timeout) {
		waitUntilClickable(element, timeout);
		element.click();
	}
	

	public void clickThroughJavaScript(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(By locator) {
		return find(locator).getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	/* Wait methods */

	public WebElement waitUntilPresent(By locator, int timeout) {
		return new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitUntilPresent(WebElement element, int timeout) {
		
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));

		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));

		}
	}

	public WebElement waitUntilClickable(By locator, int timeout) {
		return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitUntilClickable(WebElement element, int timeout) {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));

		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));

		}	}

	public WebDriverWait waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		return wait;
	}
	

	public void submit(WebElement element) {
		element.submit();
	}

	public void submit(By locator) {
		find(locator).submit();
	}
	public String url() {
		return driver.getCurrentUrl();
	}
	
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}
	
	public void switchTo(String tab) {
		driver.switchTo().window(tab);
	}
	
	//ACTION
	public void movetoElementClick(WebElement element) {
		action.moveToElement(element).click().perform();
	}
	
	//IFRAME
	public void switchToIframe (WebElement element) {
		driver.switchTo().frame(element);
	}
	
	//JS
	public void clickJs(WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}
	
}

