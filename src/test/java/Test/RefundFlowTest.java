package Test;



import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RefundFlowTest extends BaseTest {

	@Test
	@Order(1)
	@DisplayName("Login web de flow")
	void login() {
		assertTrue(page.login());
	}

	@Test
	@Order(2)
	@DisplayName("Generaci�n solicitud de reembolso")
	// @Disabled
	void generarSolicitudRefund() throws InterruptedException {
		page.crearPago();
		page.mantenedorReembolso();
		assertTrue(page.crearReembolso());
	}

	@Test
	@Order(4)
	@DisplayName("Generaci�n de solicitud y proceso de pago")
	@Disabled
	void generarPago() throws InterruptedException {
		page.crearPago();
		
	}
}
