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

	@FindBy(css = "#modalError > div > div > div.modal-footer > button")
	private WebElement cerrarButtonModalLocator;

	@FindBy(css = "#navbar-collapse-2 > ul > li.dropdown.open > ul > li > div > div > ul:nth-child(1) > li:nth-child(1) > a")
	private WebElement cobrarLocator;

	@FindBy(css = "button[type= 'submit']")
	private WebElement submitPagoLocator;

	@FindBy(id = "emailPag")
	private WebElement emailPagoLocator;

	@FindBy(id = "desc")
	private WebElement descripcionPagoLocator;

	@FindBy(id = "monto")
	private WebElement montoPagoLocator;

	@FindBy(id = "confirma")
	private WebElement confirmaPagoLocator;

	@FindBy(id = "confEmailPagador")
	private WebElement pagadorLocator;

	@FindBy(id = "confConcepto")
	private WebElement conceptoPagoLocator;

	@FindBy(css = "#confirmaPage > h3")
	private WebElement confirmacionPagoLocator;

	@FindBy(id = "OrdenId")
	private WebElement idPagoLocator;

	@FindBy(partialLinkText = "Cerrar sesión")
	private WebElement logoutLocator;
	String idpago = null;

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
		if (automator.isDisplayed(emptypagosLocator)) {
			System.out.println("No hay pagos para reembolsar");
		} else {
			automator.click(pagoLocator, 10);
			automator.click(continuarPagoLocator, 10);
			Thread.sleep(1000);
			if (automator.isDisplayed(reembolsoPagoLocator)) {
				automator.click(continuarPagoLocator, 10);
				automator.click(finalizarPagoLocator, 10);
			} else {
				System.out.println("Pagos ya reembolsados");
				crearPago();
				

			}

		}

	}

	public String crearPago() {
		String descripcionPago = "pago automatizado - test";
		idpago = null;
		System.out.println("Generando pago para test");
		automator.click(cerrarButtonModalLocator);
		automator.click(cobrarButtonLocator);
		automator.click(cobrarLocator, 10);
		automator.waitUntilPresent(submitPagoLocator, 10);
		automator.type(emailPagoLocator, Configuration.USER);
		automator.type(descripcionPagoLocator, descripcionPago);
		automator.type(montoPagoLocator, "1000");
		automator.click(submitPagoLocator);
		automator.waitUntilClickable(confirmaPagoLocator, 10);
		if ((descripcionPago.equalsIgnoreCase(automator.getText(conceptoPagoLocator)))
				&& (Configuration.USER.equalsIgnoreCase(automator.getText(pagadorLocator)))) {
			automator.click(confirmaPagoLocator);
			if (automator.isDisplayed(idPagoLocator)) {
				System.out.println("Pago generado con éxito");
				idpago = automator.getText(idPagoLocator);
				return idpago;
			} else {
				System.out.println("Problemas en generar el pago");
				return idpago;
			}

		} else {
			System.out.println("Datos a confirmar del pago no coinciden");
			return idpago;

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
