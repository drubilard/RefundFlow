package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RefundFlowTest extends BaseTest{

	@Test
	@Order(1)
	void login() {
		page.login();
		page.selectOrganizacion();
		//assertTrue(page.menuInicioDisplayed());
	}
	@Test
	@Order(2)
	void Anulaciones() {
		page.selectMenuAnulaciones();
		page.solicitarAnulacion();
		page.generarAnulacion();
		//assertTrue(page.menuInicioDisplayed());
	}
	

}

