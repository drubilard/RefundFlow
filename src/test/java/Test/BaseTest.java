package Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import Pages.PagosFlowPage;
import Pages.RefundFlowPage;
import commons.Browser;

class BaseTest {
	protected static RefundFlowPage pageRefund;
	protected static PagosFlowPage pagePagos;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pageRefund = new RefundFlowPage(Browser.CHROME);
		pageRefund.get();
		pageRefund.getAutomator().maximize();
		pagePagos = new PagosFlowPage(pageRefund.getAutomator());
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		//page.getAutomator().closeAll();
	}
	

}
