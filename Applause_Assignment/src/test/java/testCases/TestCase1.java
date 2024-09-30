package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pageObjects.HomePageObjects;
import pageObjects.ParfumPageObjects;
import utilities.BaseClass;
import utilities.ExcelReadWrite;
import utilities.ExtentManager;
import utilities.TestUtils;

public class TestCase1 extends BaseClass
{
	HomePageObjects hp;
	ParfumPageObjects pp;
	
	@Test(enabled=true, priority=1, description="TestCase1")
	public void testCase1() throws InterruptedException
	{
		hp = new HomePageObjects();
		
		String title = hp.checkLoadedWebPage();
		
		if(title.contains("Online-Parfümerie") && title.contains("DOUGLAS") )
		{
			Assert.assertEquals(title, "Online-Parfümerie ✔️ Parfum & Kosmetik kaufen | DOUGLAS", "Incorrect website homepage is loaded");
			TestUtils.log().info("Correct website homepage is loaded: "+title);
			ExtentManager.test.log(Status.INFO, "Correct website homepage is loaded: "+title);
			
		}else {
			Assert.assertEquals(false, true, "Incorrect website homepage is loaded");
			TestUtils.log().info("Incorrect website homepage is loaded: "+title);
			ExtentManager.test.log(Status.FAIL, "Incorrect website homepage is loaded: "+title);
		}
		
		hp.checkPrivacyConcentPopup();
		hp.clickPrivacyPopupAllowAllbutton();
		
		pp = hp.clickParfumNavigationLink();
		
	}
	
	@Test(enabled=true, priority=2, description="TestCase2")
	public void testCase2() throws InterruptedException
	{	
		pp.removeElementFocus();
		
		pp.checkProductOverviewHeadline();
		pp.verifyProductOverviewHeadline();
		pp.checkProductFacetList();
		pp.checkProductListing();
		
	}
	
	@Test(enabled=true, priority=3, description="TestCase3")
	public void testCase3() throws Exception
	{	
		pp.clickHighlightsFacet();
		
		String highlightFacetSale = ExcelReadWrite.readExcelData("Sheet1", 2, 0);
		
		pp.clickHighlightsFacetSale();

		pp.verifySelectedFacet(highlightFacetSale);
		
		pp.verifyFilteredProductListing("Sheet1", 2, 1);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();	
		
		pp.listAllFilteredProducts("Sheet1", 5, 0);
	}
	
	@Test(enabled=true, priority=4, description="TestCase4")
	public void testCase4() throws Exception
	{	
		pp.clickMarkeFacet();
		
		pp.clickMarkeFacet1916();
		
		String markeFacet1916 = ExcelReadWrite.readExcelData("Sheet2", 2, 1);
		pp.verifySelectedFacet(markeFacet1916);
		
		pp.verifyFilteredProductListing("Sheet2", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.verifyFilteredProductMarke1916Name();
		
		pp.listAllFilteredProducts("Sheet2", 5, 0);
	}
	
	@Test(enabled=true, priority=5, description="TestCase5")
	public void testCase5() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		//select highlight facet
		pp.clickHighlightsFacet();
		
		String highlightFacetSale = ExcelReadWrite.readExcelData("Sheet1", 2, 0);
		
		pp.clickHighlightsFacetSale();

		pp.verifySelectedFacet(highlightFacetSale);
		
		pp.clickProduktartFacet();
		
		pp.clickProduktartFacetEauDeParfum();
		
		String produktartFacetEauDeParfum = ExcelReadWrite.readExcelData("Sheet3", 2, 1);
		
		pp.verifySelectedFacet(produktartFacetEauDeParfum);
		
		pp.verifyFilteredProductListing("Sheet3", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.verifyFilteredProductProduktartName(produktartFacetEauDeParfum);
		
		pp.listAllFilteredProducts("Sheet3", 5, 0);
	}
	
	@Test(enabled=true, priority=6, description="TestCase6")
	public void testCase6() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		//select highlight facet
		pp.clickHighlightsFacet();
		
		String highlightFacetSale = ExcelReadWrite.readExcelData("Sheet1", 2, 0);
		
		pp.clickHighlightsFacetSale();

		pp.verifySelectedFacet(highlightFacetSale);
		
		pp.clickFurWenFacet();
		
		pp.clickFurWenFacetMannlich();
		
		String furWenFacetMannlich = ExcelReadWrite.readExcelData("Sheet4", 2, 1);
		
		pp.verifySelectedFacet(furWenFacetMannlich);
		
		pp.verifyFilteredProductListing("Sheet4", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.listAllFilteredProducts("Sheet4", 5, 0);
	}
	
	@Test(enabled=true, priority=7, description="TestCase7")
	public void testCase7() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		//select highlight facet
		pp.clickHighlightsFacet();
		
		String highlightFacetSale = ExcelReadWrite.readExcelData("Sheet1", 2, 0);
		
		pp.clickHighlightsFacetSale();

		pp.verifySelectedFacet(highlightFacetSale);
		
		pp.clickGeschenkfurFacet();
		
		pp.clickGeschenkfurFacetGeburtstag();
		
		String geschenkfurFacetGeburtstag = ExcelReadWrite.readExcelData("Sheet5", 2, 1);
		
		pp.verifySelectedFacet(geschenkfurFacetGeburtstag);
		
		pp.verifyFilteredProductListing("Sheet5", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.listAllFilteredProducts("Sheet5", 5, 0);
	}
	
	@Test(enabled=true, priority=8, description="TestCase8")
	public void testCase8() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		pp.clickHighlightsFacet();
		
		String highlightFacetNeu = ExcelReadWrite.readExcelData("Sheet6", 2, 0);
		
		pp.clickHighlightsFacetNeu();

		pp.verifySelectedFacet(highlightFacetNeu);
		
		pp.verifyFilteredProductListing("Sheet6", 2, 1);
		pp.verifySelectedFacetOnProductNewEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();	
		
		pp.listAllFilteredProducts("Sheet6", 5, 0);
	}
	
	@Test(enabled=true, priority=9, description="TestCase9")
	public void testCase9() throws Exception
	{	
		pp.clickMarkeFacet();
		
		pp.clickMarkeFacet4711AcquaColonia();
		
		String markeFacet4711AcquaColonia = ExcelReadWrite.readExcelData("Sheet7", 2, 1);
		pp.verifySelectedFacet(markeFacet4711AcquaColonia);
		
		pp.verifyFilteredProductListing("Sheet7", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.verifyFilteredProductMarke4711AcquaColoniaName();
		
		pp.listAllFilteredProducts("Sheet7", 5, 0);
	}
	
	@Test(enabled=true, priority=10, description="TestCase10")
	public void testCase10() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		pp.clickHighlightsFacet();
		
		String highlightFacetNeu = ExcelReadWrite.readExcelData("Sheet8", 2, 0);
		
		pp.clickHighlightsFacetNeu();

		pp.verifySelectedFacet(highlightFacetNeu);
		
		pp.clickProduktartFacet();
		
		pp.clickProduktartFacetEauDeParfum();
		
		String produktartFacetEauDeParfum = ExcelReadWrite.readExcelData("Sheet8", 2, 1);
		pp.verifySelectedFacet(produktartFacetEauDeParfum);
		
		pp.verifyFilteredProductListing("Sheet8", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.verifyFilteredProductProduktartName(produktartFacetEauDeParfum);
		
		pp.listAllFilteredProducts("Sheet8", 5, 0);
	}
	
	@Test(enabled=true, priority=11, description="TestCase11")
	public void testCase11() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		pp.clickHighlightsFacet();
		
		String highlightFacetNeu = ExcelReadWrite.readExcelData("Sheet9", 2, 0);
		
		pp.clickHighlightsFacetNeu();

		pp.verifySelectedFacet(highlightFacetNeu);
		
		pp.clickFurWenFacet();
		
		pp.clickFurWenFacetMannlich();
		
		String furWenFacetMannlich = ExcelReadWrite.readExcelData("Sheet9", 2, 1);
		pp.verifySelectedFacet(furWenFacetMannlich);
		
		pp.verifyFilteredProductListing("Sheet9", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.listAllFilteredProducts("Sheet9", 5, 0);
	}
	
	@Test(enabled=true, priority=12, description="TestCase12")
	public void testCase12() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		pp.clickHighlightsFacet();
		
		String highlightFacetNeu = ExcelReadWrite.readExcelData("Sheet10", 2, 0);
		
		pp.clickHighlightsFacetNeu();

		pp.verifySelectedFacet(highlightFacetNeu);
		
		pp.clickGeschenkfurFacet();
		
		pp.clickGeschenkfurFacetNikolaus();
		
		String geschenkfurFacetNikolaus = ExcelReadWrite.readExcelData("Sheet10", 2, 1);
		pp.verifySelectedFacet(geschenkfurFacetNikolaus);
		
		pp.verifyFilteredProductListing("Sheet10", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.listAllFilteredProducts("Sheet10", 5, 0);
	}
	
	@Test(enabled=true, priority=13, description="TestCase13")
	public void testCase13() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		pp.clickHighlightsFacet();
		
		String highlightFacetLimitiert = ExcelReadWrite.readExcelData("Sheet11", 2, 0);
		
		pp.clickHighlightsFacetLimitiert();

		pp.verifySelectedFacet(highlightFacetLimitiert);
		
		pp.verifyFilteredProductListing("Sheet11", 2, 1);
		pp.verifySelectedFacetOnProductNewEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();	
		
		pp.listAllFilteredProducts("Sheet11", 5, 0);
	}
	
	@Test(enabled=true, priority=14, description="TestCase14")
	public void testCase14() throws Exception
	{	
		pp.clickMarkeFacet();
		
		pp.clickMarkeFacet4711AcquaColonia();
		
		String markeFacet4711AcquaColonia = ExcelReadWrite.readExcelData("Sheet12", 2, 1);
		pp.verifySelectedFacet(markeFacet4711AcquaColonia);
		
		pp.verifyFilteredProductListing("Sheet12", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.verifyFilteredProductMarke4711AcquaColoniaName();
		
		pp.listAllFilteredProducts("Sheet12", 5, 0);
	}
	
	@Test(enabled=true, priority=15, description="TestCase15")
	public void testCase15() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		pp.clickHighlightsFacet();
		
		String highlightFacetLimitiert = ExcelReadWrite.readExcelData("Sheet13", 2, 0);
		
		pp.clickHighlightsFacetLimitiert();

		pp.verifySelectedFacet(highlightFacetLimitiert);
		
		pp.clickProduktartFacet();
		
		pp.clickProduktartFacetEauDeParfum();
		
		String produktartFacetEauDeParfum = ExcelReadWrite.readExcelData("Sheet13", 2, 1);
		pp.verifySelectedFacet(produktartFacetEauDeParfum);
		
		pp.verifyFilteredProductListing("Sheet13", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.verifyFilteredProductProduktartName(produktartFacetEauDeParfum);
		
		pp.listAllFilteredProducts("Sheet13", 5, 0);
	}
	
	@Test(enabled=true, priority=16, description="TestCase16")
	public void testCase16() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		pp.clickHighlightsFacet();
		
		String highlightFacetLimitiert = ExcelReadWrite.readExcelData("Sheet14", 2, 0);
		
		pp.clickHighlightsFacetLimitiert();

		pp.verifySelectedFacet(highlightFacetLimitiert);
		
		pp.clickFurWenFacet();
		
		pp.clickFurWenFacetMannlich();
		
		String furWenFacetMannlich = ExcelReadWrite.readExcelData("Sheet14", 2, 1);
		pp.verifySelectedFacet(furWenFacetMannlich);
		
		pp.verifyFilteredProductListing("Sheet14", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.listAllFilteredProducts("Sheet14", 5, 0);
	}
	
	@Test(enabled=true, priority=17, description="TestCase17")
	public void testCase17() throws Exception
	{	
		pp.clearFilter();
		pp.checkNoFilterFacet();
		
		pp.clickHighlightsFacet();
		
		String highlightFacetLimitiert = ExcelReadWrite.readExcelData("Sheet15", 2, 0);
		
		pp.clickHighlightsFacetLimitiert();

		pp.verifySelectedFacet(highlightFacetLimitiert);
		
		pp.clickGeschenkfurFacet();
		
		pp.clickGeschenkfurFacetNikolaus();
		
		String geschenkfurFacetNikolaus = ExcelReadWrite.readExcelData("Sheet15", 2, 1);
		pp.verifySelectedFacet(geschenkfurFacetNikolaus);
		
		pp.verifyFilteredProductListing("Sheet15", 2, 2);
		pp.verifySelectedFacetOnProductDiscountEyeCatcher();
		pp.validateCountOfFilteredProductsAndDiscountEyecatchers();
		
		pp.listAllFilteredProducts("Sheet15", 5, 0);
	}
}
