package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import commons.Browser;
import commons.Configuration;
import commons.SeleniumUtils;
import commons.WebAutomator;
import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

public class RefundFlowPage extends LoadableComponent<RefundFlowPage> {
	private WebAutomator automator;
	// declaracion localizadores
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

	private List<WebElement> pagosLocator;

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

	@FindBy(css = "#botoncopiar.input-group-text")
	private WebElement emailTemporalLocator;

	@FindBy(id = "OrdenId")
	private WebElement idPagoLocator;
	
	@FindBy(css = "#the-events >tr:first-child >td:first-child.asunto")
	private WebElement correoOrderPagoLocator;

	@FindBy(css = "	#the-events >tr:first-child >td.acciones > button.open-message")
	private WebElement abrirOrderPagoLocator;
	
	@FindBy(partialLinkText = "Pagar")
	private WebElement buttonPagarOrderPagoLocator;
	
	@FindBy(partialLinkText ="Continuar")
	private WebElement buttonContinuarReembolsoLocator;
	
	@FindBy(id = "mensaje-iframe")
	private WebElement framePagarOrderPagoLocator;	
	
	@FindBy(css = "body > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(3) > td > table > tbody > tr > td > table:nth-child(2) > tbody > tr > td > table > tbody > tr > td > b")
	private WebElement claveSeguridadRefundLocator;		
	
	@FindBy(css = "span[data-target='#show_password_modal']")
	private WebElement verPassEmailLocator;
	
	@FindBy(css = "a[data-target='#recover_email_modal']")	
	private WebElement recuperarEmailLocator;
	
	@FindBy(css = "input[id='email-password']")	
	private WebElement inputEmailPassLocator;
	
	@FindBy(css = "#update-password-btn.input-group-text")
	private WebElement guardarEmailPassLocator;	
	
	@FindBy(css = "#show_password_modal > div.modal-dialog.modal-lg > div > div.modal-header > button")	
	private WebElement buttonCerrarPassLocator;	
	
	@FindBy(css = "#recover_email_modal > div.modal-dialog.modal-lg > div > div.modal-body > div:nth-child(2) > div > input")	
	private WebElement inputRecuperarEmailLocator;	
	
	@FindBy(css = "	#recover_email_modal > div.modal-dialog.modal-lg > div > div.modal-body > div.form-group.text-center > input")
	private WebElement inputRecuperarPassLocator;	
	
	@FindBy(css = "	#recover_email_modal_action_btn.btn")	
	private WebElement buttonRecuperarEmailLocator;	

	@FindBy(partialLinkText = "Cerrar sesión")	
	private WebElement logoutLocator;

	// variables de entorno

	String idpago = null;
	String emailTemporal = null;

	public RefundFlowPage(Browser browser) throws Exception {
		automator = new WebAutomator(browser);
		PageFactory.initElements(automator.getDriver(), this);

	}

	public WebAutomator getAutomator() {
		return this.automator;
	}

	// login web de flow
	public void login() {
		automator.click(ingresarButtonLocator, 10);
		automator.waitUntilPresent(emailLocator, 10);
		automator.type(emailLocator, Configuration.USER);
		automator.type(passwordLocator, Configuration.PASSWORD);
		automator.click(submitLoginLocator, 10);

	}

	// ingreso a mantenedor de reembolsos
	public void mantenedorReembolso() {
		automator.waitUntilPresent(logoutLocator, 10);
		automator.click(cobrarButtonLocator, 10);
		automator.click(reembolsarButtonLocator, 10);

	}

	// creacion de solicitud de reembolsos
	public void crearReembolso() throws InterruptedException {
		automator.waitUntilPresent(nuevoRefundButtonLocator, 10);
		automator.click(nuevoRefundButtonLocator, 10);
		if (automator.isDisplayed(emptypagosLocator)) {
			System.out.println("No hay pagos para reembolsar");
		} else {
			abrirBandejaCorreo(Configuration.CORREO_URL);
			if (buscarReembolso()) {
				automator.click(continuarPagoLocator, 10);
				automator.click(finalizarPagoLocator, 10);
				aceptarReembolso();
			} else {
				System.out.println("Pagos ya reembolsados");
				idpago = crearPago();
				aceptarPago(idpago);

			}

		}

	}

	// buscar pago disponible para reembolsar
	public boolean buscarReembolso() throws InterruptedException {
		pagosLocator = automator.findElements(By.cssSelector("#tablaPagos > tbody > tr"));
		for (WebElement pagoLocator : pagosLocator) {
			try {
				emailTemporal = automator.getText(automator.find(pagoLocator,By.cssSelector("td:nth-child(2)")));
				//System.out.println("emailTemporal: "+emailTemporal);
				automator.click(pagoLocator, 10);
			} catch (Exception e) {
				return false;
			}
			automator.click(continuarPagoLocator, 10);
			Thread.sleep(1000);
			if (automator.isDisplayed(reembolsoPagoLocator)) {
				return true;
			} else {
				automator.click(cerrarButtonModalLocator);
			}
		}
		return false;
	}
	// creacion de pago
	public String crearPago() {
		String descripcionPago = "pago automatizado - test";
		System.out.println("Generando pago para test");
		// automator.click(cerrarButtonModalLocator);
		automator.click(cobrarButtonLocator);
		automator.click(cobrarLocator, 10);
		automator.waitUntilPresent(submitPagoLocator, 10);
		automator.pegarPortapapeles(emailPagoLocator);
		automator.type(descripcionPagoLocator, descripcionPago);
		automator.type(montoPagoLocator, "1000");
		automator.click(submitPagoLocator);
		automator.waitUntilClickable(confirmaPagoLocator, 10);
		if ((descripcionPago.equalsIgnoreCase(automator.getText(conceptoPagoLocator)))) {
			emailTemporal=automator.getText(pagadorLocator);
			automator.click(confirmaPagoLocator);
			if (automator.isDisplayed(confirmacionPagoLocator)) {
				System.out.println("Pago generado con éxito");
				idpago = automator.getText(idPagoLocator);
				return idpago;
			} else {
				System.out.println("Problemas en generar la orden de pago");
				return idpago;
			}

		} else {
			System.out.println("Datos a confirmar del pago no coinciden");
			return idpago;

		}
	}

	// abrir bandeja de correos de prueba
	public void abrirBandejaCorreo(String url) {
		automator.openNewTabJS();
		// System.out.println(automator.getWindowHandles());
		String secondTab = SeleniumUtils.IdentifySecondTab(automator.getWindowHandle(), automator.getDriver());
		// System.out.println(secondTab);
		SeleniumUtils.SwitchWindowTab(secondTab, automator.getDriver());
		automator.visit(url);
		automator.waitUntilClickable(emailTemporalLocator, 5);
		automator.click(emailTemporalLocator);
		automator.click(verPassEmailLocator, 10);
		automator.waitUntilPresent(inputEmailPassLocator, 10);
		automator.type(inputEmailPassLocator, Configuration.PASSWORD_CORREO);
		automator.click(guardarEmailPassLocator, 10);
		automator.click(buttonCerrarPassLocator, 10);
		secondTab = SeleniumUtils.IdentifySecondTab(automator.getWindowHandle(), automator.getDriver());
		SeleniumUtils.SwitchWindowTab(secondTab, automator.getDriver());

	}

	public void aceptarPago(String idpago) {
		String secondTab = SeleniumUtils.IdentifySecondTab(automator.getWindowHandle(), automator.getDriver());
		SeleniumUtils.SwitchWindowTab(secondTab, automator.getDriver());
		automator.waitUntilPresent(correoOrderPagoLocator, 10);
		automator.click(abrirOrderPagoLocator, 10);
		automator.waitUntilPresent(framePagarOrderPagoLocator, 10);
		automator.switchToIframe(framePagarOrderPagoLocator);
		automator.click(buttonPagarOrderPagoLocator, 10);
		automator.switchTodefaultContent();
		
	}
	
	public void aceptarReembolso() {
		
		String borrarDominioEmail =null;
		borrarDominioEmail = emailTemporal.substring(0,emailTemporal.indexOf("@"));
		System.out.println("borrarDominioEmail: "+borrarDominioEmail);
		String secondTab = SeleniumUtils.IdentifySecondTab(automator.getWindowHandle(), automator.getDriver());
		SeleniumUtils.SwitchWindowTab(secondTab, automator.getDriver());
		automator.click(recuperarEmailLocator, 10);
		automator.waitUntilPresent(buttonRecuperarEmailLocator, 10);
		automator.type(inputRecuperarEmailLocator, borrarDominioEmail);
		automator.type(inputRecuperarPassLocator, Configuration.PASSWORD_CORREO);
		automator.click(buttonRecuperarEmailLocator, 10);
		automator.waitUntilPresent(correoOrderPagoLocator, 10);
		automator.click(abrirOrderPagoLocator, 10);
		automator.waitUntilPresent(framePagarOrderPagoLocator, 10);
		automator.switchToIframe(framePagarOrderPagoLocator);
		automator.click(buttonContinuarReembolsoLocator, 10);
		automator.switchTodefaultContent();
		
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
