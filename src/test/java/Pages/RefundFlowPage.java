package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import commons.Configuration;
import commons.SeleniumUtils;
import commons.WebAutomator;

public class RefundFlowPage extends LoadableComponent<RefundFlowPage> {

	// declaracion localizadores

	@FindBy(css = "body > div.page-container > div.yamm.navbar.navbar-static-top > div > div > ul > li:nth-child(3) > a")
	private WebElement cobrarButtonLocator;

	@FindBy(css = "	body > div.page-container > div.yamm.navbar.navbar-static-top > div > div > ul > li.dropdown.open > ul > li > div > ul:nth-child(2) > li > a")
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

	@FindBy(css = "#the-events >tr:first-child >td:first-child.asunto")
	private WebElement correoOrderPagoLocator;

	@FindBy(css = "#the-events >tr:first-child >td.acciones > button.open-message")
	private WebElement abrirOrderPagoLocator;

	@FindBy(partialLinkText = "Continuar")
	private WebElement buttonContinuarReembolsoLocator;

	@FindBy(id = "mensaje-iframe")
	private WebElement framePagarOrderPagoLocator;

	@FindBy(css = "body > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(3) > td > table > tbody > tr > td > table:nth-child(2) > tbody > tr > td > table > tbody > tr > td > b")
	private WebElement claveRefundLocator;

	@FindBy(id = "aceptar")
	private WebElement aceptarRefundLocator;

	@FindBy(css = "#mensaje_modal > div > div > div.modal-header > button > span")
	private WebElement cerrarMailLocator;

	@FindBy(partialLinkText = "Cerrar sesi??n")
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
	public RefundFlowPage(WebAutomator automator) throws Exception {
		this.automator = automator;
		PageFactory.initElements(automator.getDriver(), this);

	}

	public WebAutomator getAutomator() {
		return this.automator;
	}

	// ingreso a mantenedor de reembolsos
	public void mantenedorReembolso() throws InterruptedException {
		SeleniumUtils.SwitchWindowTab(idTabFlow, automator.getDriver());
		automator.waitUntilPresent(logoutLocator, 10);
		automator.click(cobrarButtonLocator, 10);
		automator.click(reembolsarButtonLocator, 10);

	}

	// creacion de solicitud de reembolsos
	public boolean crearReembolso(String emailTemporal) throws InterruptedException {
		automator.waitUntilPresent(nuevoRefundButtonLocator, 10);
		automator.click(nuevoRefundButtonLocator, 10);
		if (automator.isDisplayed(emptypagosLocator)) {
			System.out.println("No hay pagos para reembolsar");
			return false;
		}
		if (buscarReembolso()) {
			automator.click(continuarPagoLocator, 10);
			automator.click(finalizarPagoLocator, 10);
			return aceptarReembolso(emailTemporal);
		} else {

			System.out.println("Problemas para genere reembolso");
			return false;
			// crearPago();
		}

	}

	// buscar pago disponible para reembolsar
	public boolean buscarReembolso() throws InterruptedException {
		int intentos = 0;
		pagosLocator = automator.findElements(By.cssSelector("#tablaPagos > tbody > tr"));
		for (WebElement pagoLocator : pagosLocator) {
			if (intentos >= 5) {
				return false;
			}
			try {
				emailTemporal = automator.getText(automator.find(pagoLocator, By.cssSelector("td:nth-child(2)")));
				// System.out.println("emailTemporal: "+emailTemporal);
				automator.click(pagoLocator, 10);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			automator.click(continuarPagoLocator, 10);
			Thread.sleep(1000);
			if (automator.isDisplayed(reembolsoPagoLocator)) {
				return true;
			} else {
				automator.click(cerrarButtonModalLocator);
			}
			intentos++;
		}
		return false;
	}

	public boolean aceptarReembolso(String idTabEmailTemp) {
		String asuntoRefund = "Solicitud de reembolso - Flow";
		String borrarDominioEmail = null;
		borrarDominioEmail = emailTemporal.substring(0, emailTemporal.indexOf("@"));
		System.out.println("borrarDominioEmail: " + borrarDominioEmail);
		SeleniumUtils.SwitchWindowTab(idTabEmailTemp, automator.getDriver());
		automator.waitUntilValuePresent(correoOrderPagoLocator, 10, asuntoRefund);
		automator.click(cerrarMailLocator, 10);
		automator.click(abrirOrderPagoLocator, 10);
		automator.waitUntilPresent(framePagarOrderPagoLocator, 10);
		automator.switchToIframe(framePagarOrderPagoLocator);
		automator.waitUntilPresent(claveRefundLocator, 10);
		claveRefund = automator.getText(claveRefundLocator);
		automator.click(buttonContinuarReembolsoLocator, 10);
		automator.switchTodefaultContent();
		idTabRefundFlow = SeleniumUtils.IdentifySecondTab(idTabEmailTemp, automator.getDriver());
		SeleniumUtils.SwitchWindowTab(idTabRefundFlow, automator.getDriver());
		automator.click(aceptarRefundLocator);
		return true;

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
