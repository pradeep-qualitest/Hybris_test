package com.qualitestgroup.kdt.commonUtils;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qualitestgroup.kdt.framework.exceptions.KDTKeywordExecException;
import com.qualitestgroup.kdt.util.elementops.GetElementIdentifier;


/**
 * Description : The Class Element operation is a wrapper class for the basic
 * operations like Click Entering the text Execute java script get Text
 * 
 * @author Akshit Dwivedi
 * **/

public class SeleniumOperations {
	
	GetElementIdentifier gei = null;
	public SeleniumOperations(Class<?> appName) {
		gei = new GetElementIdentifier(appName);
	}
	
	public void executeJScript(WebDriver driver, String jsIdentifier) throws Exception {
		try {			
			String eleIdentifier = gei.getProperty(jsIdentifier);
			System.out.println(eleIdentifier);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("" + eleIdentifier + "");
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not execute Java Script method", e);
		}
	}

	public void bringElementintoView(WebDriver driver, String eleId) throws Exception {
		try {			
			String eleIdentifier = gei.getProperty("JSbringtoView");
			String jsCode = eleIdentifier.replace("####", eleId);
			System.out.println(jsCode);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("" + jsCode + "");
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not execute Java Script method", e);
		}
	}

	public String getText(WebDriver driver, String locator, String identifier, boolean isPropertyValidate)
			throws Exception {
		String returnText = null;
		WebElement ele;
		try {
			Thread.sleep(2000);
			if (isPropertyValidate) {				
				String eleIdentifier = gei.getProperty(identifier);
				System.out.println("eleIdentifier: " + eleIdentifier);
				ele = getWebElement(driver, locator, eleIdentifier);
			} else {
				ele = getWebElement(driver, locator, identifier);
			}
			returnText = ele.getText();
			System.out.println("Return text" + returnText);
			return returnText;
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not find the webElement to get the Text", e);
		}
	}

	public boolean isEnabled(WebDriver driver, String locator, String identifier) throws Exception {
		try {
			String eleIdentifier = gei.getProperty(identifier);
			System.out.println("isEnabled" + eleIdentifier);
			WebElement ele = getWebElement(driver, locator, identifier);
			boolean status = ele.isSelected();
			return status;
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could find the checkbox webElement", e);
		}
	}

	public boolean verifyElementIsDisplayed(WebDriver driver, String locator, String identifier,
			boolean isPropertyValidate) throws Exception {
		try {
			boolean elementPresent = false;
			WebElement ele;
			if (isPropertyValidate) {
				String eleIdentifier = gei.getProperty(identifier);
				System.out.println("verifyElementIsDisplayed" + eleIdentifier);
				ele = getWebElement(driver, locator, eleIdentifier);
			} else {
				ele = getWebElement(driver, locator, identifier);
			}
			elementPresent = ele.isDisplayed();
			return elementPresent;
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not find the webElement", e);
		}
	}

	public boolean WaitForElementProperty(WebDriver driver, String locator, String attribute, String AtribVal,
			int timeout) throws Exception {
		try {
			WebElement ele = driver.findElement(By.xpath(gei.getProperty(locator)));
			while (timeout > 0) {
				if (ele.getAttribute(attribute).equalsIgnoreCase(AtribVal)) {
					return true;
				}
				timeout--;
				Thread.sleep(1000);
			}
			;
			return false;
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could find the Attribute", e);
		}
	}

	public void clickElement(WebDriver driver, String locator, String keyValue, boolean isPropertyValidate)
			throws KDTKeywordExecException {
		try {
			WebElement ele;
			if (isPropertyValidate) {
				String eleIdentifier = gei.getProperty(keyValue);
				System.out.println(eleIdentifier);
				ele = getWebElement(driver, locator, eleIdentifier);
			} else {
				ele = getWebElement(driver, locator, keyValue);
			}

			ele.click();
		} catch (Exception e) {
			// new
			throw new KDTKeywordExecException("Could not Click the element.", e);
		}
	}

	public WebElement getWebElement(WebDriver driver, String locator, String identifier) throws Exception {
		try {
			if (locator.toLowerCase().contains("linkText")) {
				return driver.findElement(By.linkText(identifier));
			} else if (locator.toLowerCase().contains("id")) {
				return driver.findElement(By.id(identifier));
			} else if (locator.toLowerCase().contains("name")) {
				return driver.findElement(By.name(identifier));
			} else if (locator.toLowerCase().contains("xpath")) {
				return driver.findElement(By.xpath("" + identifier + ""));
			} else if (locator.toLowerCase().contains("cssSelector")) {
				return driver.findElement(By.cssSelector(identifier));
			} else if (locator.toLowerCase().contains("partialLinkText")) {
				return driver.findElement(By.partialLinkText(identifier));
			} else if (locator.toLowerCase().contains("className")) {
				return driver.findElement(By.className(identifier));
			} else if (locator.toLowerCase().contains("tagName")) {
				return driver.findElement(By.tagName(identifier));
			}
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not find the matching element", e);
		}
		return null;
	}

	public ArrayList<WebElement> getWebElements(WebDriver driver, String locator, String identifier) throws Exception {
		ArrayList<WebElement> elements = new ArrayList<WebElement>();
		try {
			if (locator.toLowerCase().contains("linkText")) {
				elements.addAll(driver.findElements(By.linkText(identifier)));
				return elements;
			} else if (locator.toLowerCase().contains("id")) {
				elements.addAll(driver.findElements(By.id(identifier)));
				return elements;
			} else if (locator.toLowerCase().contains("name")) {
				elements.addAll(driver.findElements(By.name(identifier)));
				return elements;
			} else if (locator.toLowerCase().contains("xpath")) {
				elements.addAll(driver.findElements(By.xpath("" + identifier + "")));
				return elements;
			} else if (locator.toLowerCase().contains("cssSelector")) {
				elements.addAll(driver.findElements(By.cssSelector(identifier)));
				return elements;
			} else if (locator.toLowerCase().contains("partialLinkText")) {
				elements.addAll(driver.findElements(By.partialLinkText(identifier)));
				return elements;
			} else if (locator.toLowerCase().contains("className")) {
				elements.addAll(driver.findElements(By.className(identifier)));
				return elements;
			} else if (locator.toLowerCase().contains("tagName")) {
				elements.addAll(driver.findElements(By.tagName(identifier)));
				return elements;
			}
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not find the matching element", e);
		}
		return null;
	}

	public void javaScriptClick(WebDriver driver, WebElement element) throws KDTKeywordExecException {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			throw new KDTKeywordExecException("Not able to click on the element using javascript", e);
		}

	}

	public void javaScriptScrollToViewElement(WebDriver driver, WebElement element) throws KDTKeywordExecException {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			throw new KDTKeywordExecException("Not able to scroll to the element", e);
		}

	}

	public void enterText(WebDriver driver, String locator, String keyValue, String data)
			throws KDTKeywordExecException {
		try {
			String eleIdentifier = gei.getProperty(keyValue);
			System.out.println(eleIdentifier);
			WebElement ele;
			ele = getWebElement(driver, locator, eleIdentifier);
			ele.sendKeys(data);
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not enter the text in to textbox: " + keyValue + ". ", e);
		}
	}

	public void cleardata(WebDriver driver, String locator, String keyValue) throws KDTKeywordExecException {
		try {
			String eleIdentifier = gei.getProperty(keyValue);
			System.out.println(eleIdentifier);
			WebElement ele;
			ele = getWebElement(driver, locator, eleIdentifier);
			ele.clear();
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not clear the data.", e);
		}
	}

	public void SelectComboBoxByVisibleText(WebDriver driver, String locator, String keyValue, String selectvalue,
			boolean IsPropertyValidate) throws KDTKeywordExecException {
		try {
			Select obj;
			if (IsPropertyValidate) {
				String eleIdentifier = gei.getProperty(keyValue);
				System.out.println(eleIdentifier);
				obj = new Select(getWebElement(driver, locator, eleIdentifier));
			} else {
				obj = new Select(getWebElement(driver, locator, keyValue));
			}
			obj.selectByVisibleText(selectvalue);
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not select the value in the Combo box.", e);
		}
	}

	public void SelectComboBoxByIndex(WebDriver driver, String locator, String keyValue, int index,
			boolean IsPropertyValidate) throws KDTKeywordExecException {
		try {
			Select obj;
			if (IsPropertyValidate) {
				String eleIdentifier = gei.getProperty(keyValue);
				System.out.println(eleIdentifier);
				obj = new Select(getWebElement(driver, locator, eleIdentifier));
			} else {
				obj = new Select(getWebElement(driver, locator, keyValue));
			}
			obj.selectByIndex(index);
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not select the value in the Combo box.", e);
		}
	}

	public void verifyScreenText(WebDriver driver, String locator, String keyValue, String verifyExpString)
			throws KDTKeywordExecException {
		WebElement ele;
		String actualText = "";
		try {
			String eleIdentifier = gei.getProperty(keyValue);
			System.out.println(eleIdentifier);
			ele = getWebElement(driver, locator, eleIdentifier);
			actualText = ele.getText();
		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not extract the String.", e);
		}

		if (!actualText.trim().toLowerCase().contains(verifyExpString.trim().toLowerCase())) {
			throw new KDTKeywordExecException(
					"The Expected value: " + verifyExpString + " and Actual Value: " + actualText + "dosenot match");
		}
	}

	public void WaitForPageload(WebDriver driver) {
		String BrowserStatus = null;
		do {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				BrowserStatus = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState;");
			} catch (Exception e) {

			}
		} while (!(BrowserStatus.compareToIgnoreCase("complete") == 0));
		BrowserStatus = null;
	}

	public int GenerateRandomNumer(WebDriver driver, int Index) {
		Random rand = new Random();
		return rand.nextInt(Index) + 1;
	}

	public boolean Sort(WebDriver driver, String keyValue, String sortOrder, int pageIndex)
			throws KDTKeywordExecException {

		try {
			ArrayList<Float> priceArray = new ArrayList<Float>();
			String eleIdentifier = gei.getProperty(keyValue);
			List<WebElement> fetchPrices = getWebElements(driver, "xpath", eleIdentifier);
			for (int index = 0; index < pageIndex; index++) {
				String removeCurrency = fetchPrices.get(index).getText();
				System.out.println(removeCurrency);
				if (!removeCurrency.isEmpty()) {
					removeCurrency = removeCurrency.substring(removeCurrency.indexOf("$") + ("$").length(),
							removeCurrency.length());
					priceArray.add(index, Float.parseFloat(removeCurrency));
					System.out.println(removeCurrency);
				}
			}
			if (sortOrder.equalsIgnoreCase("Ascending")) {
				boolean isSorted = true;
				for (int i = 1; i < priceArray.size(); i++) {
					System.out.println(i + "-->" + priceArray.get(i));
					System.out.println(i - 1 + "-->" + priceArray.get(i - 1));
					if ((priceArray.get(i)) < (priceArray.get(i - 1))) {
						isSorted = false;
						break;
					}
				}
				priceArray.clear();
				fetchPrices.clear();
				return isSorted;
			}
			/*
			 * else { boolean isSorted = true; for (int i = 1; i < lst2.size();
			 * i++) { if ((lst2.get(i)) < (lst2.get(i - 1))) { isSorted = false;
			 * break; } } return isSorted; }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Boolean) null;
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File file = new File(downloadPath);
		File[] FolderContents = file.listFiles();
		for (int i = 0; i < FolderContents.length; i++) {
			if (FolderContents[i].getName().contains(fileName)) {
				System.out.println("file found");
				if (FolderContents[i].getName().contains(".pdf")) {
					// File has been found, it can now be deleted:
					FolderContents[i].delete();
					System.out.println("file deleted");
					return true;
				}
			}
		}
		return false;
	}

	public int isLinkBroken(URL url) {		
		try {
			System.out.println(url.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// connection.setRequestMethod("GET");
			connection.connect();
			System.out.println("connected");
			int response = connection.getResponseCode();
			System.out.println("-->" + response);
			return response;
		} catch (IOException exp) {
			System.out.println(exp.toString());
			return 0;
		}
	}

	public String GetElementAttribute(WebDriver driver, String locator, String keyValue, String AttributeName,
			boolean isPropertyValidate) throws KDTKeywordExecException {
		WebElement Element;
		try {
			if (isPropertyValidate) {
				String eleIdentifier = gei.getProperty(keyValue);
				System.out.println(eleIdentifier);
				Element = getWebElement(driver, locator, eleIdentifier);
			} else {
				Element = getWebElement(driver, locator, keyValue);
			}

			String AttributeValue = Element.getAttribute(AttributeName);
			return AttributeValue;

		} catch (Exception e) {
			throw new KDTKeywordExecException("Could not find the element attribute", e);
		}
	}	

	public int GenerateRandomWithLimit(WebDriver driver, int max, int min) {
		return min + (int) (Math.random() * (max - min));
	}

	public HashMap<String, String> getWindowId(WebDriver driver) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("parentWinID", itr.next());
		map.put("childWinID", itr.next());
		return map;

	}
}


