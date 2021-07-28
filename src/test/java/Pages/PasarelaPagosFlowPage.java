package Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import commons.WebAutomator;

public class PasarelaPagosFlowPage extends LoadableComponent<PasarelaPagosFlowPage> {
	WebAutomator automator;
	
	// WebElement buttonPagarFlowLocator;
	// WebElement parrafoPagoLocator;
	@FindBy(id = "pagar")
	private WebElement buttonPagarFlowLocator;

	//servipag
	@FindBy(css = "input[value='Pagar']")
	private WebElement buttonPagarServipagLocator;

	//multicaja
	@FindBy(css = "input[value='Continuar']")
	private WebElement buttonPagarMultiCajaLocator;
	
	
	@FindBy(id = "credito")
	private WebElement buttonCreditoWebpayLocator;
	
	@FindBy(id = "card-number")
	private WebElement inputCardNumberWebpayLocator;
	
	@FindBy(id = "card-exp")
	private WebElement inputCardExpWebpayLocator;
	
	@FindBy(id = "card-cvv")
	private WebElement inputCardCvvWebpayLocator;
	
	@FindBy(css = "button.next")
	private WebElement buttonWebpayContinuarLocator;
	
	@FindBy(id = "rutClient")
	private WebElement inputRutTransbankLocator;
	
	@FindBy(id = "passwordClient")
	private WebElement inputPassTransbankLocator;
	
	@FindBy(css = "input[type='submit'][value='Aceptar']")
	private WebElement buttonAceptarTransbankLocator;

	@FindBy(id = "vci")
	private WebElement selectOptionTransbankLocator;
	
	@FindBy(css = "input[type='submit'][value='Continuar']")
	private WebElement buttonContinuarTransbankLocator;
	
	@FindBy(css = "div.col-md-6.col-md-offset-3.box-pay > h3")
	private WebElement parrafoPagoLocator;
	
	
	

	
	public PasarelaPagosFlowPage(WebAutomator automator) {
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
	

	public boolean pagoTransaccionMach(WebElement elementMedio) {
		automator.click(elementMedio, 10);
		automator.click(buttonPagarFlowLocator, 10);
		if (automator.isDisplayed(parrafoPagoLocator)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean pagoTransaccionWebpay1(WebElement elementMedio) {
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
