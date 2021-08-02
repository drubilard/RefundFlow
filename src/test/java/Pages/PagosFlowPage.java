package Pages;


import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import commons.Configuration;
import commons.SeleniumUtils;
import commons.WebAutomator;

public class PagosFlowPage extends LoadableComponent<PagosFlowPage> {

	// declaracion localizadores

	@FindBy(css = "#navbar-collapse-2 > ul > li:nth-child(3) > a") 
	private WebElement cobrarButtonLocator;

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

	@FindBy(css = "#the-events >tr:first-child >td.acciones > button.open-message")
	private WebElement abrirOrderPagoLocator;

	@FindBy(partialLinkText = "Pagar")
	private WebElement buttonPagarOrderPagoLocator;

	@FindBy(id = "mensaje-iframe")
	private WebElement framePagarOrderPagoLocator;

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

	@FindBy(css = "a[data-id='2']")
	private WebElement buttonServipagPasarelaLocator;

	@FindBy(css = "a[data-id='3']")
	private WebElement buttonMultiCajaPasarelaLocator;
	
	@FindBy(css = "a[data-id='1']")
	private WebElement buttonWebpay1PasarelaLocator;

	@FindBy(css = "a[data-id='15']")
	private WebElement buttonMachPasarelaLocator;
	
	// variables de entorno
	String idpago = null;
	String emailTemporal = null;
	String claveRefund = null;
	private WebAutomator automator;
	private PasarelaPagosFlowPage PasarelaPagos;
	String idTabFlow = null;
	String idTabEmailTemp = null;
	String idTabPagoFlow = null;
	public static final Map<Integer, String> mediosDePago = new HashMap<Integer, String>();
	
	
	//constructor

	public PagosFlowPage(WebAutomator automator) throws Exception {
		this.automator = automator;
		PageFactory.initElements(automator.getDriver(), this);
		PasarelaPagos = new PasarelaPagosFlowPage(automator);
		mediosDePago.put(1, "servipag");
		mediosDePago.put(2, "multicaja");
		mediosDePago.put(3, "mach");
		mediosDePago.put(4, "webpay1");

	}

	public WebAutomator getAutomator() {
		return this.automator;
	}

	public Map<Integer, String> getMediosDePago() {
		return PagosFlowPage.mediosDePago;
	}
	
	
	// creacion de pago
	public String crearPago(String medioPago) {
		idTabFlow = automator.getWindowHandle();
		String descripcionPago = "pago automatizado - test";
		System.out.println("Generando pago para test");
		automator.click(cobrarButtonLocator);
		automator.click(cobrarLocator, 10);
		automator.waitUntilPresent(submitPagoLocator, 10);
		abrirBandejaCorreo(Configuration.CORREO_URL);
		automator.pegarPortapapeles(emailPagoLocator);
		automator.type(descripcionPagoLocator, descripcionPago);
		automator.type(montoPagoLocator, "1000");
		automator.click(submitPagoLocator);
		automator.waitUntilClickable(confirmaPagoLocator, 10);
		if ((descripcionPago.equalsIgnoreCase(automator.getText(conceptoPagoLocator)))) {
			emailTemporal = automator.getText(pagadorLocator);
			automator.click(confirmaPagoLocator);
			if (automator.isDisplayed(confirmacionPagoLocator)) {
				System.out.println("Pago generado con Éxito");
				idpago = automator.getText(idPagoLocator);
				if (aceptarPago(medioPago)) {
					return idTabEmailTemp;
				} else {
					return null;
				}

			} else {
				System.out.println("Problemas en generar la orden de pago");
				return null;

			}

		} else {
			System.out.println("Datos a confirmar del pago no coinciden");
			return null;

		}
	}

	// abrir bandeja de correos de prueba
	public void abrirBandejaCorreo(String url) {
		automator.openNewTabJS();
		idTabEmailTemp = SeleniumUtils.IdentifySecondTab(idTabFlow, automator.getDriver());
		// System.out.println(automator.getWindowHandles());
		// System.out.println(secondTab);
		SeleniumUtils.SwitchWindowTab(idTabEmailTemp, automator.getDriver());
		automator.visit(url);
		automator.waitUntilClickable(emailTemporalLocator, 5);
		automator.click(emailTemporalLocator);
		automator.click(verPassEmailLocator, 10);
		automator.waitUntilPresent(inputEmailPassLocator, 10);
		automator.type(inputEmailPassLocator, Configuration.PASSWORD_CORREO);
		automator.click(guardarEmailPassLocator, 10);
		automator.click(buttonCerrarPassLocator, 10);
		SeleniumUtils.SwitchWindowTab(idTabFlow, automator.getDriver());

	}

	public boolean aceptarPago(String medioPago) {
		String asuntoPago = "Aviso de transacción por pagar - Flow";
		SeleniumUtils.SwitchWindowTab(idTabEmailTemp, automator.getDriver());
		automator.waitUntilValuePresent(correoOrderPagoLocator, 10, asuntoPago);
		automator.click(abrirOrderPagoLocator, 10);
		automator.waitUntilPresent(framePagarOrderPagoLocator, 10);
		automator.switchToIframe(framePagarOrderPagoLocator);
		automator.click(buttonPagarOrderPagoLocator, 10);
		automator.switchTodefaultContent();
		idTabPagoFlow = SeleniumUtils.IdentifySecondTab(idTabEmailTemp, automator.getDriver());
		SeleniumUtils.SwitchWindowTab(idTabPagoFlow, automator.getDriver());
		if(medioPago == null) {
			medioPago = Configuration.mediopagoDefault;
		}
		switch (medioPago) {
		case "servipag":
			return PasarelaPagos.pagoTransaccionServipag(buttonServipagPasarelaLocator);
		case "multicaja":
			return PasarelaPagos.pagoTransaccionMultiCaja(buttonMultiCajaPasarelaLocator);

		case "mach":
			return PasarelaPagos.pagoTransaccionMach(buttonMachPasarelaLocator);

		case "webpay1":
			return PasarelaPagos.pagoTransaccionWebpay1(buttonWebpay1PasarelaLocator);

		default:
			return PasarelaPagos.pagoTransaccionServipag(buttonServipagPasarelaLocator);
		}
		

	}

	@Override
	protected void load() {

	}

	@Override
	protected void isLoaded() throws Error {
	}

}
