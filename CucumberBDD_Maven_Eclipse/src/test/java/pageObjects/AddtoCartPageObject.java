package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.GenericUtils;

public class AddtoCartPageObject {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public GenericUtils genericUtils;
	
	By productName= By.xpath("//input[@id='twotabsearchtextbox' and @Placeholder='Search Amazon.in']");
	
    String targetProduct = "Vega Atom ISI Certified Smooth Matt Finish Open Face Helmet for Men and Women with Clear Visor(Dull Black, Size:M)";
    
    private By suggestionsContainer = By.cssSelector(".left-pane-results-container");
    private By searchSuggestions = By.xpath("//div[@class='s-suggestion s-suggestion-ellipsis-direction' and @role='button' and @aria-label]");

	
	public AddtoCartPageObject(WebDriver driver)
	{
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		this.genericUtils=new GenericUtils(driver);
		
	}
	
	
	public void searchProduct(String product) {
		driver.findElement(productName).sendKeys(product);
		
	}
	
	
	

	public void selectSearchSuggestion(String productSuggestion) {
        // Wait for the suggestions container to be present
        wait.until(ExpectedConditions.presenceOfElementLocated(suggestionsContainer));

        List<WebElement> suggestions = null;
        int attempts = 0;

        while (attempts < 3) { // Retry logic for stale elements
            try {
                // Wait for suggestions to be present and visible
                suggestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchSuggestions));
                
                for (WebElement suggestion : suggestions) {
                    wait.until(ExpectedConditions.visibilityOf(suggestion));
                }

                // Loop through the suggestions list and find the correct one
                for (WebElement suggestion : suggestions) {
                    String suggestionText = suggestion.getDomAttribute("aria-label");

                    if (suggestionText != null && suggestionText.equalsIgnoreCase(productSuggestion)) {
                        try {
                            // Ensure the suggestion is clickable and click it
                            wait.until(ExpectedConditions.elementToBeClickable(suggestion)).click();
                            System.out.println("Clicked on: " + productSuggestion);
                            return; // Exit the method after clicking
                        } catch (Exception e) {
                            System.out.println("Unable to click on the suggestion: " + productSuggestion);
                        }
                    }
                }

                throw new NoSuchElementException("Suggestion with text '" + productSuggestion + "' not found.");
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale Element Exception occurred. Retrying... Attempt: " + (attempts + 1));
                attempts++;
            }
        }

        throw new RuntimeException("Failed to select search suggestion after multiple attempts.");
    }



	
	public void verifySearchedProductsPage() {
		// TODO Auto-generated method stub
		WebElement resultTextElement = driver.findElement(By.xpath("//div[@class='sg-col-inner']//h2[@class='a-size-base a-spacing-small a-spacing-top-small a-text-normal']//span[@class='a-color-state a-text-bold']"));
		String actualText = resultTextElement.getText().trim().replace("\"", ""); // Remove double quotes
		String expectedText = "helmet for bike";

		// Compare after removing quotes
		if (actualText.equalsIgnoreCase(expectedText)) {
		    System.out.println("Text matched: " + actualText);
		} else {
		    System.out.println("Text did not match. Expected: " + expectedText + ", but found: \"" + actualText + "\"");
		}

	}

	
	
	
	
	public void clickOneProduct() {

	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".s-main-slot")));

	    List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

	    for (WebElement product : products) {
	        try {
	            WebElement productLabel = product.findElement(By.xpath(".//a/h2/span"));
	            if (productLabel.getText().trim().equalsIgnoreCase(targetProduct)) {
	                wait.until(ExpectedConditions.elementToBeClickable(productLabel.findElement(By.xpath("./ancestor::a")))).click();
	                genericUtils.SwitchWindowToChild();
	                break;
	            }
	        } catch (Exception e) {
	            System.out.println("Skipping a product due to missing elements.");
	        }
	    }
	}


	public void verifyLoadedProductName() {
	    // Define the expected product title
	    
	    // Define the locator for the product title element
	    By verifyProductNameLocator = By.xpath("//div/h1/span[@id='productTitle']");
	    
	    // With implicit wait already configured, find the product title element directly
	    WebElement productTitleElement = driver.findElement(verifyProductNameLocator);
	    
	    // Retrieve and trim the loaded product title text
	    String loadedProductName = productTitleElement.getText().trim();
	    
	    // Compare the loaded product name with the expected product name (case-insensitive)
	    if (targetProduct.equalsIgnoreCase(loadedProductName)) {
	        System.out.println("The product loaded successfully: " + loadedProductName);
	    } else {
	        System.out.println("Product mismatch. Expected: " + targetProduct + ", but found: " + loadedProductName);
	    }
	}

	
	public void clickAddtoCartButton()
	{
		By addtoCart= By.xpath("//input[@id='add-to-cart-button']");
		
		driver.findElement(addtoCart).click();
		
	}


public void verifyProductAdded()
{
	By checkGoToCartButton=By.xpath("//a[normalize-space(text())='Go to Cart']");
	
	if(driver.findElement(checkGoToCartButton).isEnabled())
	{
		System.out.println("The product added successfully "+ driver.findElement(checkGoToCartButton).getText());
	}
	
	driver.findElement(checkGoToCartButton).click();
	
	By checkProductInCart = By.xpath("//span[@class='a-truncate-cut']");
	
	if(driver.findElement(checkProductInCart).getText().equalsIgnoreCase(targetProduct))
	{
		System.out.println("The product added is in cart");
	}
}
	
}






/*

Details:

presenceOfElementLocated(By locator)
This condition only ensures that the element exists in the DOM. It does not guarantee that the element is visible or enabled.

elementToBeClickable(By locator)
This condition is actually a composite check that confirms:

The element is present in the DOM.
The element is visible.
The element is enabled.
*/
