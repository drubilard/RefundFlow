package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RefundFlowTest extends BaseTest {

	@Test
	@Order(1)
	@DisplayName("Login web de flow")
	void login() {
		page.login();
	}

	@Test
	@Order(2)
	@DisplayName("Generación solicitud de reembolso")
	void generarSolicitudRefund() throws InterruptedException {
		page.mantenedorReembolso();
		page.crearReembolso();
		// assertTrue(page.menuInicioDisplayed());
	}

	@Nested
	class nuevoTab {

		@Test
		@Order(3)
		@DisplayName("Aceptar solicitud de reembolso")
		void aceptarSolicitudRefund() {
		}
	}
}
