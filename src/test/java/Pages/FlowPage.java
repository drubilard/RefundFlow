package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import commons.Browser;
import commons.Configuration;
import commons.WebAutomator;

public class FlowPage extends LoadableComponent<FlowPage> {

	// declaracion localizadores
	@FindBy(css = "li[class='visible-lg']+li :first-child")
	private WebElement ingresarButtonLocator;

	@FindBy(id = "email")
	private WebElement emailLocator;

	@FindBy(id = "psw")
	private WebElement passwordLocator;

	@FindBy(id = "btnSubmit")
	private WebElement submitLoginLocator;


	@FindBy(partialLinkText = "Cerrar sesión")
	private WebElement logoutLocator;

	
	
	// variables de entorno
	String idpago = null;
	String emailTemporal = null;
	String claveRefund = null;
	private WebAutomator automator;
	String idTabFlow = null;
	String idTabEmailTemp = null;
	String idTabRefundFlow = null;

	
	
	// constructor
	public FlowPage(Browser browser) throws Exception {
		automator = new WebAutomator(browser);
		PageFactory.initElements(automator.getDriver(), this);

	}

	public WebAutomator getAutomator() {
		return this.automator;
	}

	// login web de flow
	public boolean login() {
		idTabFlow = automator.getWindowHandle();
		automator.click(ingresarButtonLocator, 10);
		automator.waitUntilPresent(emailLocator, 10);
		automator.type(emailLocator, Configuration.USER);
		automator.type(passwordLocator, Configuration.PASSWORD);
		automator.click(submitLoginLocator, 10);
		automator.waitUntilPresent(logoutLocator, 10);
		return automator.isDisplayed(automator.waitUntilPresent(logoutLocator, 10));

	}



	@Override
	protected void load() {
		automator.visit(Configuration.APP_URL);
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(automator.getTitle().equals("Flow - Plataforma de pagos online - Chile"), "Page not loaded");
	}

}
