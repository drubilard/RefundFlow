package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import commons.WebAutomator;

public class BotonDePagoPage extends LoadableComponent<BotonDePagoPage> {
	WebAutomator automator;

	@FindBy(css = "body > div.container:nth-child(3) > div.row:nth-child(3) > div.col-md-4:nth-child(2) > a.itemMenu > img.fix-image.img-menu:nth-child(1)")
	private WebElement botonDePagoMenu;

	@FindBy(id = "btnNuevo")
	private WebElement nuevoBotonPago;

	@FindBy(id = "name")
	private WebElement inputNombreProducto;

	@FindBy(id = "monto")
	private WebElement inputMontoProducto;

	@FindBy(id = "descripcion")
	private WebElement inputDescripcion;

	@FindBy(id = "tipoFijo")
	private WebElement radioTipoFijo;

	@FindBy(xpath = "//*[@id=\"formEditaCatalogo\"]/fieldset/div[17]/button[1]")
	private WebElement botonGrabar;

	@FindBy(id = "btnMastarde")
	private WebElement botonMasTarde;

	@FindBy(id = "catalogo")
	private WebElement listaCatalogo;
	
	@FindBy(id = "filtro")
	private WebElement filtroBotonPagoLocator;
	
	@FindBy(xpath = "//ul[@id='catalogo'] /child::li")
	private List<WebElement> botonesPagoLocator;

	int valorRandom = (int) Math.floor(Math.random() * 1000 + 1);
	String nombreBoton = "nombre producto automator " + valorRandom;

	public BotonDePagoPage(WebAutomator automator) {
		this.automator = automator;
		PageFactory.initElements(automator.getDriver(), this);

	}

	public WebAutomator getAutomator() {
		return this.automator;
	}

	public boolean recorrerListaNombre() throws InterruptedException {
		automator.type(filtroBotonPagoLocator, nombreBoton);
		Thread.sleep(1000);
		if(botonesPagoLocator.size()>0) {
			return true;
		}

		return false;

	}

	public boolean crearBotonDePago() throws InterruptedException {
		automator.click(botonDePagoMenu, 10);
		automator.click(nuevoBotonPago, 10);
		automator.waitUntilPresent(inputDescripcion, 10);
		automator.type(inputNombreProducto, nombreBoton);
		automator.type(inputDescripcion, "descripción producto");
		automator.click(radioTipoFijo, 10);
		automator.type(inputMontoProducto, "5000");
		automator.click(botonGrabar, 10);
		automator.click(botonMasTarde, 10);
		automator.waitUntilPresent(filtroBotonPagoLocator, 10);
		return recorrerListaNombre();
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