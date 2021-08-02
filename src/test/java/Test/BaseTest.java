package Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import Pages.BotonDePagoPage;
import Pages.PagosFlowPage;
import Pages.RefundFlowPage;
import commons.Browser;

class BaseTest {
	protected static RefundFlowPage pageRefund;
	protected static PagosFlowPage pagePagos;
	protected static BotonDePagoPage pageBotonPagos;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pageRefund = new RefundFlowPage(Browser.CHROME);
		pageRefund.get();
		pageRefund.getAutomator().maximize();
		pagePagos = new PagosFlowPage(pageRefund.getAutomator());
		pageBotonPagos = new BotonDePagoPage(pageRefund.getAutomator());

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		//page.getAutomator().closeAll();
	}
	

}
