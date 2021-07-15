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
	}
	@Test
	@Order(2)
	void Refund() {
		page.generarReembolso();
		//assertTrue(page.menuInicioDisplayed());
	}
}

