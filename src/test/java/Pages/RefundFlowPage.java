package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import commons.Browser;
import commons.Configuration;
import commons.WebAutomator;

public class RefundFlowPage extends LoadableComponent<RefundFlowPage> {
	private WebAutomator automator;
	@FindBy(id = "UsuarioRut")
	private WebElement usuarioLocator;
	
	
	public RefundFlowPage(Browser browser) throws Exception {
		automator = new WebAutomator(browser);
		PageFactory.initElements(automator.getDriver(), this);

	}

	public WebAutomator getAutomator() {
		return this.automator;
	}

	public void login() {


	}

	public void selectOrganizacion() {


	}

	public void selectMenuAnulaciones() {

	}

	public void solicitarAnulacion() {

		
	}
	public void generarAnulacion() {


		
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
