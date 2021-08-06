package Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import Pages.BotonDePagoPage;
import Pages.FlowPage;
import Pages.PagosFlowPage;
import Pages.RefundFlowPage;
import commons.Browser;

class BaseTest {
	protected static RefundFlowPage pageRefund;
	protected static FlowPage pageFlow;
	protected static PagosFlowPage pagePagos;
	protected static BotonDePagoPage pageBotonPagos;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pageFlow = new FlowPage(Browser.CHROME);
		pageRefund = new RefundFlowPage(pageFlow.getAutomator());
		pageRefund.get();
		pageRefund.getAutomator().maximize();
		pagePagos = new PagosFlowPage(pageFlow.getAutomator());
		pageBotonPagos = new BotonDePagoPage(pageFlow.getAutomator());

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		//page.getAutomator().closeAll();
	}
	

}
