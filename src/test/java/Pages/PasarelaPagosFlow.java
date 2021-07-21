package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import commons.WebAutomator;

public class PasarelaPagosFlow extends LoadableComponent<PasarelaPagosFlow> {
	WebAutomator automator;
	// WebElement buttonPagarFlowLocator;
	// WebElement parrafoPagoLocator;
	@FindBy(id = "pagar")
	private WebElement buttonPagarFlowLocator;

	@FindBy(css = "input[value='Pagar']")
	private WebElement buttonPagarServipagLocator;

	@FindBy(css = "div.col-md-6.col-md-offset-3.box-pay > h3")
	private WebElement parrafoPagoLocator;

	//multicaja
	@FindBy(css = "input[value='Continuar']")
	private WebElement buttonPagarMultiCajaLocator;

	
	public PasarelaPagosFlow(WebAutomator automator) {
		this.automator = automator;
		PageFactory.initElements(automator.getDriver(), this);
	}

	public boolean pagoTransaccionServipag(WebElement elementMedio) {
		automator.click(elementMedio, 10);
		automator.click(buttonPagarFlowLocator, 10);
		automator.click(buttonPagarServipagLocator, 10);
		automator.waitUntilPresent(parrafoPagoLocator, 10);
		if (automator.isDisplayed(parrafoPagoLocator)) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean pagoTransaccionMultiCaja(WebElement elementMedio) {
		automator.click(elementMedio, 10);
		automator.click(buttonPagarFlowLocator, 10);
		automator.click(buttonPagarMultiCajaLocator, 10);
		automator.waitUntilPresent(parrafoPagoLocator, 10);
		if (automator.isDisplayed(parrafoPagoLocator)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub

	}
}
