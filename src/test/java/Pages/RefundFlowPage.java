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
	
	@FindBy(css = "li[class='visible-lg']+li :first-child")
	private WebElement ingresarButtonLocator;
	
	@FindBy(id = "email")
	private WebElement emailLocator;
	
	@FindBy(id = "psw")
	private WebElement passwordLocator;
	
	@FindBy(id = "btnSubmit")
	private WebElement submitLoginLocator;
	
	@FindBy(css = "#navbar-collapse-2 > ul > li:nth-child(3) > a")
	private WebElement cobrarButtonLocator;
	
	@FindBy(css = "#navbar-collapse-2 > ul > li.dropdown.open > ul > li > div > div > ul:nth-child(2) > li > a")
	private WebElement reembolsarButtonLocator;
	
	@FindBy(partialLinkText = "Nuevo")
	private WebElement nuevoRefundButtonLocator;
	
	@FindBy(css = "td.dataTables_empty")
	private WebElement emptypagosLocator;
	
	@FindBy(css = "#tablaPagos > tbody > tr:first-child")
	private WebElement pagoLocator;

	@FindBy(id = "continuar")
	private WebElement continuarPagoLocator;
	
	@FindBy(id = "finalizar")
	private WebElement finalizarPagoLocator;
	
	@FindBy(id = "reembolso")
	private WebElement reembolsoPagoLocator;
	
		
	@FindBy(partialLinkText = "Cerrar sesión")
	private WebElement logoutLocator;
		
		
	public RefundFlowPage(Browser browser) throws Exception {
		automator = new WebAutomator(browser);
		PageFactory.initElements(automator.getDriver(), this);

	}

	public WebAutomator getAutomator() {
		return this.automator;
	}

	public void login() {
		automator.click(ingresarButtonLocator, 10);
		automator.waitUntilPresent(emailLocator, 10);
		automator.type(emailLocator, Configuration.USER);
		automator.type(passwordLocator, Configuration.PASSWORD);
		automator.click(submitLoginLocator, 10);
		

	}

	public void mantenedorReembolso() {
		automator.waitUntilPresent(logoutLocator, 10);
		automator.click(cobrarButtonLocator, 10);
		automator.click(reembolsarButtonLocator, 10);


	}

	public void crearReembolso() throws InterruptedException {
		automator.waitUntilPresent(nuevoRefundButtonLocator, 10);
		automator.click(nuevoRefundButtonLocator, 10);
		if(automator.isDisplayed(emptypagosLocator)) {
			System.out.println("NO HAY PAGOS PARA REEMBOLSAR");
		}else {
			automator.click(pagoLocator, 10);
			automator.click(continuarPagoLocator, 10);
			Thread.sleep(1000);
			//automator.waitUntilPresent(reembolsoPagoLocator, 10);
			automator.click(continuarPagoLocator, 10);
			automator.click(finalizarPagoLocator, 10);
			
		}

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
