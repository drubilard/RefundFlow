package Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RefundFlowTest extends BaseTest {
	Map<Integer, String> mediosDePago = pagePagos.getMediosDePago();
	
	/* Setear variable medioPago con el id del medio qeu se quiere utilizar para realizar pagos:
	 * id     medio
	 * 1      Servipag
	 * 2      Multicaja
	 * 4	  Webpay1 
	 */
	
	int medioPago = 1;
	@Test
	@Order(1)
	@DisplayName("Login web de flow")
	void login() {
		assertTrue(pageRefund.login());
	}

	@Test
	@Order(2)
	@DisplayName("Generación solicitud de reembolso")
	//@Disabled
	void generarSolicitudRefund() throws InterruptedException {
		String idTabEmailTemp =pagePagos.crearPago(mediosDePago.get(medioPago));
		assertNotEquals(null, idTabEmailTemp);
		pageRefund.mantenedorReembolso();
		assertTrue(pageRefund.crearReembolso(idTabEmailTemp));
	}

	@Test
	@Order(4)
	@DisplayName("Generación de solicitud y proceso de pago")
	@Disabled
	void generarPago() throws InterruptedException {
			pagePagos.crearPago(mediosDePago.get(medioPago));

	}
}
