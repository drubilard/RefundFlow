package Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import commons.Configuration;
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
	
	@FindBy(css = "input[value='Pagar']")
	private WebElement buttonPagarCryptoLocator;
		
	@FindBy(id = "credito")
	private WebElement buttonCreditoWebpayLocator;
	
	@FindBy(id = "card-number")
	private WebElement inputCardNumberWebpayLocator;
	
	@FindBy(id = "card-exp")
	private WebElement inputCardExpWebpayLocator;
	
	@FindBy(id = "card-cvv")
	private WebElement inputCardCvvWebpayLocator;
	
	@FindBy(css = "button.submit")
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

	public boolean pagoTransaccionWebpay(WebElement elementMedio) {
		automator.click(elementMedio, 10);
		automator.click(buttonPagarFlowLocator, 10);
		automator.click(buttonCreditoWebpayLocator, 10);
		automator.waitUntilPresent(inputCardCvvWebpayLocator, 10);
		automator.type(inputCardNumberWebpayLocator, Configuration.CREDITCARD_NUM);
		automator.type(inputCardExpWebpayLocator, Configuration.CREDITCARD_EXP);
		automator.type(inputCardCvvWebpayLocator, Configuration.CREDITCARD_CVV);
		automator.waitUntilClickable(buttonWebpayContinuarLocator, 10);
		automator.click(buttonWebpayContinuarLocator);
		automator.waitUntilClickable(buttonAceptarTransbankLocator, 10);
		automator.type(inputRutTransbankLocator, Configuration.RUT_TRANSBANK);
		automator.type(inputPassTransbankLocator, Configuration.CLAVE_TRANSBANK);
		automator.click(buttonAceptarTransbankLocator);
		automator.waitUntilClickable(selectOptionTransbankLocator, 10);
		Select selectOSelect = new Select(selectOptionTransbankLocator);
		selectOSelect.selectByValue("TSY");
		automator.click(buttonContinuarTransbankLocator, 10);
		automator.waitUntilPresent(parrafoPagoLocator, 30);
		if (automator.isDisplayed(parrafoPagoLocator)) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean pagoTransaccionCrypto(WebElement elementMedio) {
		automator.click(elementMedio, 10);
		automator.click(buttonPagarFlowLocator, 10);
		automator.click(buttonPagarCryptoLocator, 10);
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
