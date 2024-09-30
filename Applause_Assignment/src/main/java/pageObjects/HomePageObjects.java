package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.BaseClass;

public class HomePageObjects extends BaseClass
{
	
	@FindBy(xpath = "//div[@id='usercentrics-root']")
	private WebElement shadowHost;
	
	@FindBy(css = "div#uc-center-container")
	private WebElement privacyPopup;
	
	@FindBy(css = "button[data-testid='uc-accept-all-button']")
	private WebElement privacyPopupAllowButton;
	
	@FindBy(xpath = "//li[contains(@class, 'navigation-main-entry')]//a[contains(@href, 'parfum') or contains(text(), 'parfum')]")
	private WebElement parfumNavigationLink;
	
	public HomePageObjects()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String checkLoadedWebPage() throws InterruptedException
	{
		return getPageTitle();
	}
	
	public void checkPrivacyConcentPopup()
	{
		privacyPopup = shadowHost.getShadowRoot().findElement(By.cssSelector("div#uc-center-container"));
		
		boolean isVisible = isElementVisible(privacyPopup, "Privacy concent popup is visible");
		Assert.assertEquals(isVisible, true, "Privacy concent popup is not visible");
	}
	
	public void clickPrivacyPopupAllowAllbutton()
	{
		privacyPopupAllowButton = shadowHost.getShadowRoot().findElement(By.cssSelector("button[data-testid='uc-accept-all-button']"));
		
		boolean isVisible = isElementVisible(privacyPopupAllowButton, "Privacy concent popup Allow all button is visible");
		Assert.assertEquals(isVisible, true, "Privacy concent popup Allow all button is not visible");
	
		clickElement(privacyPopupAllowButton, "Clicked Allow all button");
	}
	
	public ParfumPageObjects clickParfumNavigationLink()
	{
		boolean isVisible = isElementVisible(parfumNavigationLink, "Parfum navigation link is visible");
		Assert.assertEquals(isVisible, true, "Parfum navigation link is not visible");
	
		clickElement(parfumNavigationLink, "Clicked Parfum navigation link");
		
		return new ParfumPageObjects();
	}
}
