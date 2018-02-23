package com.qualitestgroup.kdt.keywords.pepboys;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.qualitestgroup.kdt.commonUtils.SeleniumOperations;
import com.qualitestgroup.kdt.framework.Keyword;
import com.qualitestgroup.kdt.framework.KeywordGroup;
import com.qualitestgroup.kdt.framework.exceptions.KDTException;
import com.qualitestgroup.kdt.framework.exceptions.KDTKeywordExecException;
import com.qualitestgroup.kdt.framework.exceptions.KDTKeywordInitException;
import com.qualitestgroup.kdt.util.elementops.*;

public class PepBoys extends KeywordGroup {

	GetElementIdentifier gei = new GetElementIdentifier(this.getClass());
	SeleniumOperations operations = new SeleniumOperations(this.getClass());
	SoftAssert softAssert = new SoftAssert();
	private static String LOGMSG = "";
	int RandomIndex;
	int productIndex;
	String FullfillmentMethods = "~";
	//Application Name
	private final static String CUR_APP = "PepBoys";
	//fullFillment Methods
	private static final String STH = "Ship to Home";
	private static final String PUIS = "Pick Up in Store";
	private static final String INST = "Installation in Store";
	private static String order_id;
	private static String appointconfirmId;
	HashMap<String, String> map;
	//Storing all the Product with Specified FullFillment Option
	ArrayList<Integer> FetchIndex = new ArrayList<Integer>();
	public static String storeDetails = null;

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ClickMenu
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to click on the menu
	 * items
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>MenuName (Mandatory): Name of the menu item to be clicked (Eg : Tires
	 * / Products / etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 19th December 2017
	 * @modificationDate NA </div>
	 */

	public class ClickMenu extends Keyword {

		private static final String MENU = "MenuName";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(MENU);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// operations.closeAd(driver,"BtnAdClose");
				// Fetch the property from property file
				String eleIdentifier = gei.getProperty("menuLinks");
				// Replacing {MenuName} in XPath with data from excel sheet
				eleIdentifier = eleIdentifier.replaceAll("\\{MenuName\\}", args.get(MENU));
				// Clicking main menu
				operations.clickElement(driver, "xpath", eleIdentifier, false);
			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to click Main menu element", e);
			}
		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ClickSubMenu
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to click on the sub menu
	 * items
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>MenuName (Mandatory): Name of the main menu item to be clicked (Eg :
	 * Tires / Products / etc..)</li>
	 * <li>SubMenuName (Mandatory): Name of the sub menu item to be clicked (Eg
	 * : TiresByVechicle / Batteries / etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 19th December 2017
	 * @modificationDate NA </div>
	 */

	public class ClickSubMenu extends Keyword {

		private static final String MENU = "MenuName";
		private static final String SUBMENU = "SubMenuName";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(MENU, SUBMENU);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// Fetch the property from property file
				String eleIdentifier = gei.getProperty("subMenuLinks");
				// Replacing {MenuName} and {SubMenuName} in XPath with data from excel sheet
				eleIdentifier = eleIdentifier.replace("{MenuName}", args.get(MENU));
				eleIdentifier = eleIdentifier.replace("{SubMenuName}", args.get(SUBMENU));
				System.out.println(eleIdentifier);
				// Clicking sub menu
				operations.clickElement(driver, "xpath", eleIdentifier, false);

			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to click SubMenu element", e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ClickSubCategory
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to click on the sub
	 * category items
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>SubCategoryName (Mandatory): Name of the subCategory to be clicked
	 * (Eg : Car Batteries / Motor oil / etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 26th December 2017
	 * @modificationDate NA </div>
	 */

	public class ClickSubCategory extends Keyword {

		private static final String SUBCATEGORYNAME = "SubCategoryName";

		@Override
		public void init() throws KDTKeywordInitException {
			// TODO Auto-generated method stub
			super.init();
			verifyArgs(SUBCATEGORYNAME);

		}

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stub
			try {
				WebDriver driver = context.getWebDriver();
				// Fetch the property from property file
				String eleIdentifier = gei.getProperty("subCategoryName");
				// Replacing {SubCategoryName} in Xpath with data from excel sheet
				eleIdentifier = eleIdentifier.replace("{SubCategoryName}", args.get(SUBCATEGORYNAME));
				System.out.println(eleIdentifier);
				WebElement element = operations.getWebElement(driver, "xpath", eleIdentifier);
				// Scrolling
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true)", element);
				jse.executeScript("window.scrollBy(0,-150)", element);
				// Clicking sub category
				operations.clickElement(driver, "xpath", eleIdentifier, false);
			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to click SubCategory element", e);
			}

		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseVehicle
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to select the vehicle in
	 * YMME
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>YMMEName (Mandatory): Given the name of dropdown's in comma separated
	 * values (Eg : Year,Make,Model,Engine,Drivetrain,Trim etc..)</li>
	 * <li>YMMEValue (Mandatory): Given the value of dropdown's in comma
	 * separated values (Eg :2011,HONDA,ACCORD,4-2354 2.4L DOHC,4WD/AWD,EX-L V6
	 * COUPE etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 19th December 2017
	 * @modificationDate NA </div>
	 */

	public class ChooseVehicle extends Keyword {

		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(YMMENAME, YMMEVALUE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// YMMENAME & YMMEVALUE are comma separated values
				String VehicleArgsName = args.get(YMMENAME);
				String VehicleArgsValue = args.get(YMMEVALUE);
				//Split the Comma Separated Values
				String[] Vehname = VehicleArgsName.split(",");
				String[] Vehdata = VehicleArgsValue.split(",");
				// Filling YMME
				// Looping Comma Separated Values
				for (int index = 0; index < Vehname.length; index++) {
					// Fetch the property from property file
					String eleIdentifier = gei.getProperty("SelectYMME");
					// Replace the argument {YMME} in XPath with YMMENAME Passed as CSV from excel sheet
					eleIdentifier = eleIdentifier.replace("{YMME}", "" + Vehname[index] + "");
					System.out.println(eleIdentifier);
					// Select the value from DropDown
					operations.SelectComboBoxByVisibleText(driver, "xpath", eleIdentifier, Vehdata[index], false);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException("Unable to choose vehicle in YMME popup", e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseVehicleShopAll
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to select the YMME
	 * vehicle from Shop All Tires page
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>YMMEName (Mandatory): Given the name of dropdown's in comma separated
	 * values (Eg : Year,Make,Model,Engine,Drivetrain,Trim etc..)</li>
	 * <li>YMMEValue (Mandatory): Given the value of dropdown's in comma
	 * separated values (Eg :2011,HONDA,ACCORD,4-2354 2.4L DOHC,4WD/AWD,EX-L V6
	 * COUPE etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 29th December 2017
	 * @modificationDate NA </div>
	 */

	public class ChooseVehicleShopAll extends Keyword {
		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(YMMENAME, YMMEVALUE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			JavascriptExecutor jseee = (JavascriptExecutor) driver;
			jseee.executeScript("window.scrollBy(0,100)");
			// YMMENAME and YMMEVALUE will be comma separated values
			String VehicleArgsName = args.get(YMMENAME);
			String VehicleArgsValue = args.get(YMMEVALUE);
			//Split the Comma Separated Values
			String[] Vehname = VehicleArgsName.split(",");
			String[] Vehdata = VehicleArgsValue.split(",");
			// Looping Comma Separated Values
			for (int index = 0; index < Vehname.length; index++) {
				//Fetch the Property
				String eleIdentifier1 = gei.getProperty("Btn_chooseVehicle_Name");
				// Replace the argument {YMME} in XPath with YMMENAME Passed as CSV from excel sheet
				eleIdentifier1 = eleIdentifier1.replace("{YMME}", "" + Vehname[index] + "");
				System.out.println(eleIdentifier1);
				// Scroll to the element
				WebElement element1 = driver.findElement(By.xpath(eleIdentifier1));
				jseee.executeScript("arguments[0].scrollIntoView(true)", element1);
				jseee.executeScript("window.scrollBy(0,-150);", element1);
				//Fetch the Property
				String eleIdentifier2 = gei.getProperty("Btn_chooseVehicle_Value");
				// Replacing the {value} in XPath with YMMENAME Passed as CSV from excel sheet
				eleIdentifier2 = eleIdentifier2.replace("{value}", "" + Vehdata[index] + "");
				WebElement element = driver.findElement(By.xpath(eleIdentifier2));
				System.out.println(eleIdentifier2);
				// Click the Vehicle Name[Year,Make,Model,Engine,DriveTrain,Trim] from DropDown
				operations.clickElement(driver, "xpath", eleIdentifier1, false);
				// Scroll into view
				jseee.executeScript("arguments[0].scrollIntoView(true)", element);
				jseee.executeScript("window.scrollBy(0,-150);", element);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Click the Vehicle Value from DropDown
				operations.clickElement(driver, "xpath", eleIdentifier2, false);

			}
			//Click See Results Button
			operations.clickElement(driver, "xpath", "Btn_SelVehicleProceed", true);
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseVehicleAndClick
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to select the vehicle
	 * from YMME popup and Click Save/Next/See Results button.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>YMMEName (Mandatory): Given the name of dropdown's in comma separated
	 * values (Eg : Year,Make,Model,Engine,Drivetrain,Trim etc..)</li>
	 * <li>YMMEValue (Mandatory): Given the value of dropdown's in comma
	 * separated values (Eg :2011,HONDA,ACCORD,4-2354 2.4L DOHC,4WD/AWD,EX-L V6
	 * COUPE etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 27th December 2017
	 * @modificationDate NA </div>
	 */

	public class ChooseVehicleAndClick extends Keyword {

		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(YMMENAME, YMMEVALUE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			try {
			try {
				// Call keyword ChooseVehicle to select the vehicle YMME
				Keyword.runK(CUR_APP, "ChooseVehicle", YMMENAME, args.get(YMMENAME), YMMEVALUE, args.get(YMMEVALUE));
				//Click Save Button in YMME PopUp
				operations.clickElement(driver, "xpath", "btn_Save_Veh", true);
			} catch (Exception e) {
				// Click Next/Save/See Results button
				operations.clickElement(driver, "xpath", "Btn_SelVehicleProceed", true);
			}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException("Unable to choose vehicle and Click in YMME popup", e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> NavigateToChooseStore
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to select the YMME
	 * vehicle, choose ZipCode radio and click Next button under Tires page
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>YMMEName (Mandatory): Given the name of dropdown's in comma separated
	 * values (Eg : Year,Make,Model,Engine,Drivetrain,Trim etc..)</li>
	 * <li>YMMEValue (Mandatory): Given the value of dropdown's in comma
	 * separated values (Eg :2011,HONDA,ACCORD,4-2354 2.4L DOHC,4WD/AWD,EX-L V6
	 * COUPE etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 27th December 2017
	 * @modificationDate NA </div>
	 */


	public class NavigateToChooseStore extends Keyword {

		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";
		private static final String ISPREXISTING = "IsPreExisting";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(YMMENAME, ISPREXISTING);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				// operations.closeAd(driver,"BtnAdClose");
				//If need to Choose PreExisting Vehicle 
				if (args.get(ISPREXISTING).equalsIgnoreCase("true")) {
					System.out.println("choose preexisting vehicle");
					//Fetch the PreExisting Vehicle Radio button XPaths
					String FetchXpath = gei.getProperty("radioPreExistingVehicleTotal");
					List<WebElement> ListOfPreexisingVehicle = operations.getWebElements(driver, "xpath", FetchXpath);
					//Randomly choose a Vehicle
					int RandomVehicle = operations.GenerateRandomNumer(driver, ListOfPreexisingVehicle.size());
					//RandomVehicle=6;
					String eleIdentifierTireSize = gei.getProperty("radioPreExistingVehicle");
					eleIdentifierTireSize = eleIdentifierTireSize.replace("{INDEX}", "" + RandomVehicle + "");
					//Click Radio button of Chosen Vehicle
					operations.clickElement(driver, "xpath", eleIdentifierTireSize, false);
					Thread.sleep(1000);
					System.out.println("clicked on radio");
					// Click zipCode radio button
					operations.clickElement(driver, "xpath", "Btn_chooseZip", true);
					Thread.sleep(1000);				
					//operations.clickElement(driver, "xpath", "Btn_SelVehicleProceed", true);					
					String ScrollProperty = gei.getProperty("Btn_SelVehicleProceed");
					WebElement ScrollElement = operations.getWebElement(driver, "xpath", ScrollProperty);
					//Scrolling
				    	jse.executeScript("arguments[0].scrollIntoView(true)", ScrollElement);
				    	jse.executeScript("window.scrollBy(0,-200);", ScrollElement);
				    	// Click Next button
					operations.javaScriptClick(driver, ScrollElement);					
					Thread.sleep(2000);
					boolean IsErrorDisplayed;
					//For Tires, Drivertrain and Trim Will be Present
					//For Wheels, Chassis and Options Will be Present
					//On Randomly choosing an Vehicle, We are not sure all Dropdowns in YMME Will be filled.
					//On Clicking Next, Error Will be displayed "Please enter Required Information"
					try
					{
					IsErrorDisplayed=operations.verifyElementIsDisplayed(driver,"xpath","TextYMMEError",true);
					System.out.println("error displayed"+IsErrorDisplayed);
					if (IsErrorDisplayed) {
						System.out.println("error msg is displayed");
						//Fetch the YMMENAME
						String VehicleArgsName = args.get(YMMENAME);
						//Split the Comma Separated Values
						String[] Vehname = VehicleArgsName.split(",");
						// Filling YMME
						// Looping Comma Separated Values
						for (int index = 0; index < Vehname.length; index++) {
							// Fetch the property from property file
							String eleIdentifier = gei.getProperty("SelectYMME");
							// Replace the argument name {YMME} in XPath with YMMENAME Passed as CSV from Excel Sheet
							eleIdentifier = eleIdentifier.replace("{YMME}", "" + Vehname[index] + "");
							System.out.println(eleIdentifier);
							//Fetch all the available Options of Specific Dropdown to be filled
							Select obj = new Select(operations.getWebElement(driver, "xpath", eleIdentifier));
							List<WebElement> YMMEVehicle = obj.getOptions();
							System.out.println(YMMEVehicle.size());
							//Randomly select the Option
							int Rand = operations.GenerateRandomWithLimit(driver, 2, YMMEVehicle.size());
							// Rand=2;
							System.out.println(Rand);
							WebElement element = operations.getWebElement(driver, "xpath", eleIdentifier);
							//Scrolling
							jse.executeScript("arguments[0].scrollIntoView(true)", element);
							jse.executeScript("window.scrollBy(0,-100);", element);
							String eleIdentifier1 = eleIdentifier;
							//Appending XPath with chosen value
							eleIdentifier1 = eleIdentifier1.concat("/option[" + Rand + "]");
							System.out.println(eleIdentifier1 + "--->");
							//Fetch the text of Randomly selected option Value
							String text = operations.getText(driver, "xpath", eleIdentifier1, false);
							System.out.println(text);
							// Select the value from DropDown
							operations.SelectComboBoxByVisibleText(driver, "xpath", eleIdentifier, text, false);
							Thread.sleep(1000);
						}
						//Fetch Next button Property
						String fetchNext = gei.getProperty("Btn_SelVehicleProceed");
						WebElement ele = operations.getWebElement(driver, "xpath", fetchNext);
						//Scrolling
						jse.executeScript("arguments[0].scrollIntoView(true)", ele);
						jse.executeScript("window.scrollBy(0,-100);", ele);
						//Click Next button
						operations.clickElement(driver, "xpath", "Btn_SelVehicleProceed", true);
						System.out.println("clicked next");
						Thread.sleep(2000);
					}
					else
					{
						//Click Next button
						operations.javaScriptClick(driver, ScrollElement);					
						Thread.sleep(2000);
					}
					}
					catch (Exception e) {
						System.out.println("Error not displayed");
					
				} }
				//To fill YMME With Data in Excel
				else {
					// Call keyword ChooseVehicle to Select vehicle YMME
					Keyword.runK(CUR_APP, "ChooseVehicle", YMMENAME, args.get(YMMENAME), YMMEVALUE,	args.get(YMMEVALUE));
					// Click zipCode radio button
					operations.clickElement(driver, "xpath", "Btn_chooseZip", true);
					Thread.sleep(1000);
					// Click Next button
					operations.clickElement(driver, "xpath", "Btn_SelVehicleProceed", true);
					System.out.println("clicked next");
					Thread.sleep(1000);
				}
				

			} catch (KDTException e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException("Unable to naviagte to store popup", e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseStore
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to Enter the zipCode and
	 * Choose the store based on StoreName in ZipCode modal
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Zip (Mandatory): To enter zipCode in modal (Eg : 19152 etc..)</li>
	 * <li>StoreName (Mandatory): To choose the Store based on name (Eg :
	 * Philadelphia etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 19th December 2017
	 * @modificationDate NA </div>
	 */


	public class ChooseStore extends Keyword {
		private static final String ZIP = "Zip";
		private static final String STORE = "StoreName";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ZIP, STORE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// Enter zipcode
				operations.enterText(driver, "xpath", "Text_EnterZip", args.get(ZIP));
				// Click Find button
				operations.clickElement(driver, "xpath", "Btn_FindStore", true);
				// Fetch property from property file
				String eleIdentifier = gei.getProperty("Btn_SelectStore");
				// Replace the {storeName} in XPath with data passed from excel sheet
				eleIdentifier = eleIdentifier.replaceAll("\\{storeName\\}", args.get(STORE));
				// Fetch the Property
				String storeInformation = gei.getProperty("TextStoreData");
				// Replace the {storeName} in XPath with data passed from excel sheet
			    storeInformation = storeInformation.replaceAll("\\{storeName\\}", args.get(STORE));
			    // Fetching the Store Details for Future Validations
			    storeDetails = operations.getText(driver, "xpath", storeInformation, false);
			    // Click Choose this location
				operations.clickElement(driver, "xpath", eleIdentifier, false);
			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to enter the zipcode and choose the specific store", e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseStoreOnProducts
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to click pepBoysOnline()
	 * and call ChooseStore Keyword in Products Result page under Products
	 * section
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Zip (Mandatory): To enter zipCode in modal (Eg : 19152 etc..)</li>
	 * <li>StoreName (Mandatory): To choose the Store based on name (Eg :
	 * Philadelphia etc..)</li>i>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 27th December 2017
	 * @modificationDate NA </div>
	 */

	public class ChooseStoreOnProducts extends Keyword {
		private static final String ZIP = "Zip";
		private static final String STORE = "StoreName";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ZIP, STORE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				Thread.sleep(1000);
				// Click PepBoysOnline() link in Products page
				operations.clickElement(driver, "xpath", "ChooseStoreLink", true);
				System.out.println("clicked selected store");
				// Call ChooseStore keyword to fill zipCode modal
				Keyword.runK(CUR_APP, "ChooseStore", ZIP, args.get(ZIP), STORE, args.get(STORE));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException("Unable to choose store on products page", e);
			}

		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidatePRP
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the products
	 * in product result page
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 20th December 2017
	 * @modificationDate NA </div>
	 */
	public class ValidatePRP extends Keyword {

		private static final String ADDTOCART = "AddToCart";
		String propertyValue;
		String Pname;
		int lastIndex;

		// Replace the INDEX with randomly chosen value
		public String FetchProperty(String PropertyName, String ActualString, String ReplaceString)
				throws KDTKeywordExecException {
			String prop = gei.getProperty(PropertyName);
			prop = prop.replace(ActualString, ReplaceString);
			System.out.println(prop);
			return prop;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();

			try {
				// operations.closeAd(driver,"BtnAdClose");
				// common validations in PRP				
				FetchIndex.clear();
				// Fetching the Total Product count in PRP
				LOGMSG = "Unable to Fetch Total product count in first page of Product Result page";
				String fetchCount = operations.getText(driver, "xpath", "Text_ProductCount", true);
				String endCount = fetchCount.substring(fetchCount.indexOf("-") + ("-").length(), fetchCount.indexOf("of")).trim();
				lastIndex = Integer.parseInt(endCount);
				System.out.println("end:" + lastIndex);
				// Fetching the selected Pagination value
				LOGMSG = "Unable to fetch selected view value in Product Result page";
				String showValue = operations.getText(driver, "xpath", "SelectView", true);
				int noOfProducts = Integer.parseInt(showValue);
				
				//noOfProducts - Current Pagination Count Value
				//lastIndex - Total Number of Products under the Chosen Vehicle in All Pages if Paginations is Present
				//Get the Total Number of Products in Current Page
				
				if (lastIndex >= noOfProducts) {
					productIndex = noOfProducts;
				} else {
					productIndex = lastIndex;
				}

				System.out.println("total" + productIndex);

				// -------------------------
				//Replacing the FullFillment with Full Form Value
				String Fullfillment;
				if (args.get(ADDTOCART).equalsIgnoreCase("sth")) {
					Fullfillment = STH;
				} else if (args.get(ADDTOCART).equalsIgnoreCase("puis")) {
					Fullfillment = PUIS;
				} else {
					Fullfillment = INST;
				}

				// Sorting on Low to High
				LOGMSG = "Prices are not sorted in Product Result page";
				//Fetch the Default Sort Value
				String defaultSortValue = operations.getText(driver, "xpath", "Select_Defaultsort", true);
				//If Sort Value is not "Price (Low to High)"
				if (!defaultSortValue.equalsIgnoreCase("Price (Low to High)")) {
					//Select Sort Option "Price (Low to High)"
					operations.SelectComboBoxByVisibleText(driver, "xpath", "SelectSort", "Price (Low to High)",true);
				}
				System.out.println("now sorting");
				//Perform Sort Operation
				softAssert.assertTrue(operations.Sort(driver, "Text_PRPPrice", "Ascending", productIndex));

				//Looping all the Products in Curent Page
				for (int index = 1; index <= productIndex; index++) {
					//Fetch the Property and Replace the {INDEX} in XPath with Product Loop Index
					propertyValue = FetchProperty("FindFullFillmentIndexed", "{INDEX}", "" + index);
					//Fetch the Property and Replace the {FULLFILLMENT} in XPath with the value in FullFillment String 
					propertyValue = propertyValue.replace("{FULLFILLMENT}", Fullfillment);
					System.out.println(propertyValue);
					try {
						//Fetching the Product that are available with the value in FullFillment String 
						WebElement Element = operations.getWebElement(driver, "xpath", propertyValue);
						//Adding the index of the Product into List
						FetchIndex.add(index);
						// System.out.println(FetchIndex.get(index-1));
					} catch (Exception e) {
						System.out.println("Fullfillment option not found for the " + index);
						System.out.println(e.toString());
					}

				}

				System.out.println("FetchIndex.size()"+FetchIndex.size());
				//If atLeast One Product with Available FullFillment is Present
				if (FetchIndex.size() > 0) {
					//Randomly Choose a count from List
					RandomIndex = operations.GenerateRandomNumer(driver, FetchIndex.size());
					System.out.println(RandomIndex);
					//System.out.println("---> ====" + FetchIndex.get(RandomIndex));
					//Fetch the Value from List Index
					RandomIndex = FetchIndex.get(RandomIndex - 1);
					// FetchIndex.clear();

					// -----------------------------

					// Generate Random value
					// RandomIndex = operations.GenerateRandomNumer(driver,
					// productIndex);
					// RandomIndex=4;
					// Validating elements in PRP for randomly chosen product
					//Fetch the property and Replace the {INDEX} in XPath with RandomIndex
					LOGMSG = "Unable to Fetch Product name in Product Result page";
					propertyValue = FetchProperty("Text_PRPpname", "{INDEX}", "" + RandomIndex);
					System.out.println("Prop" + propertyValue);
					Pname = operations.getText(driver, "xpath", propertyValue, false);
					LOGMSG = "Image not Present for the product " + Pname + " in Product Result page";
					propertyValue = FetchProperty("imageLinks", "{INDEX}", "" + RandomIndex);
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
					LOGMSG = "Part number not Present for the product " + Pname + " in Product Result page";
					propertyValue = FetchProperty("Text_PRPpart", "{INDEX}", "" + RandomIndex);
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
					LOGMSG = "SKU not Present for the product " + Pname + " in Product Result page";
					propertyValue = FetchProperty("Text_PRPSku", "{INDEX}", "" + RandomIndex);
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
					LOGMSG = "Reviews not Present for the product " + Pname + " in Product Result page";
					propertyValue = FetchProperty("Text_PRPreview", "{INDEX}", "" + RandomIndex);
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
					LOGMSG = "Price stack not Present for the product " + Pname + " in Product Result page";
					propertyValue = FetchProperty("Text_PRPPricestack", "{INDEX}", "" + RandomIndex);
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
					LOGMSG = "Shipping options is not Present for the product " + Pname + " in Product Result page";
					propertyValue = FetchProperty("Text_PRPShipOption", "{INDEX}", "" + RandomIndex);
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
					LOGMSG = "Add to cart button is not Present for the product " + Pname + " in Product Result page";
					propertyValue = FetchProperty("Btn_PRPAddtoCart", "{INDEX}", "" + RandomIndex);
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
					LOGMSG = "Quantity dropdown is not Present for the product " + Pname + " in Product Result page";
					propertyValue = FetchProperty("SelectPRPqty", "{INDEX}", "" + RandomIndex);
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
					
				} else {
					//Unable to Find Products with Specified FullFillment Option
					LOGMSG = "Products under this Vehicle does not have" + Fullfillment + " option.";
				}
			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateTiresPRP
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate some distinct
	 * elements under Tires section Product results page.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 22nd December 2017
	 * @modificationDate NA </div>
	 */
	public class ValidateTiresPRP extends Keyword {

		String propertyValue;
		private static final String ADDTOCART = "AddToCart";

		public String FetchProperty(String PropertyName, String ActualString, String ReplaceString)
				throws KDTKeywordExecException {
			String prop = gei.getProperty(PropertyName);
			prop = prop.replace(ActualString, ReplaceString);
			System.out.println(prop);
			return prop;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// Common validations in PRP
				Keyword.runK(CUR_APP, "ValidatePRP", ADDTOCART, args.get(ADDTOCART));
				System.out.println("in tires validation");
				// Distinct Validations in Tires PRP
				//Fetch the property and Replace the {INDEX} in XPath with RandomIndex
				LOGMSG = "Size not Present in Product Result page";
				propertyValue = FetchProperty("Text_PRPSize", "{INDEX}", "" + RandomIndex);
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
				LOGMSG = "Store Availability tab not Present in Product Result page";
				propertyValue = FetchProperty("Text_TiresStore", "{INDEX}", "" + RandomIndex);
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
				LOGMSG = "Mile Warranty tab not Present in Product Result page";
				propertyValue = FetchProperty("Text_TiresStore", "{INDEX}", "" + RandomIndex);
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
				LOGMSG = "Service description tab not Present in Product Result page";
				propertyValue = FetchProperty("Text_TiresStore", "{INDEX}", "" + RandomIndex);
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateProductsPRP
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate some distinct
	 * elements under Products section Product results page.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 22nd December 2017
	 * @modificationDate NA </div>
	 */
	public class ValidateProductsPRP extends Keyword {

		private static final String ADDTOCART = "AddToCart";
		String propertyValue;

		public String FetchProperty(String PropertyName, String ActualString, String ReplaceString)
				throws KDTKeywordExecException {
			String prop = gei.getProperty(PropertyName);
			prop = prop.replace(ActualString, ReplaceString);
			System.out.println(prop);
			return prop;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// Common validations in PRP.
				Keyword.runK(CUR_APP, "ValidatePRP", ADDTOCART, args.get(ADDTOCART));
				// Distinct Validations in Products PRP
				//Fetch the property and Replace the {INDEX} in XPath with RandomIndex
				LOGMSG = "Available option not Present in Product Result page";
				propertyValue = FetchProperty("Text_ProductAvail", "{INDEX}", "" + RandomIndex);
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));
				LOGMSG = "Product description not Present in Product Result page";
				propertyValue = FetchProperty("Text_ProductDesc", "{INDEX}", "" + RandomIndex);
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", propertyValue, false));

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChoosePRPProduct
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to click a product from
	 * Product result page to proceed to product detail page
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 21st December 2017
	 * @modificationDate NA </div>
	 */

	public class ChoosePRPProduct extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				LOGMSG = "Unable to click Product in Product Result page";
				WebDriver driver = context.getWebDriver();
				// Fetching the property from property file
				String eleIdentifier = gei.getProperty("Text_PRPpname");
				// Replace the {INDEX} in XPath with RandomIndex
				eleIdentifier = eleIdentifier.replace("{INDEX}", "" + RandomIndex + "");
				System.out.println(RandomIndex + "  _>" + eleIdentifier);
				// scrolling to the particular product
				WebElement element = operations.getWebElement(driver, "xpath", eleIdentifier);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true)", element);
				jse.executeScript("window.scrollBy(0,-150);", element);
				// Clicking on Product name to Navigate to PDP
				operations.clickElement(driver, "xpath", eleIdentifier, false);

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidatePDP
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the product
	 * details page
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 20th December 2017
	 * @modificationDate NA </div>
	 */

	public class ValidatePDP extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();

			try {
				// Common validations in PDP
				LOGMSG = "Product name not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPPname", true));
				LOGMSG = "Product image not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "imagePDP", true));
				LOGMSG = "Sku not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPSku", true));
				LOGMSG = "Part number not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPPart", true));
				LOGMSG = "Shipping options not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPShipOption", true));
				LOGMSG = "Reviews not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPreview", true));
				LOGMSG = "Quantity dropdown not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "SelectPDPqty", true));
				LOGMSG = "Zoom button not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ZoomPDPLink", true));

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateTiresPDP
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate some distinct
	 * elements under Tires Section Product details page.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 22nd December 2017
	 * @modificationDate NA </div>
	 */
	public class ValidateTiresPDP extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// Calling Keyword ValidatePDP
				Keyword.runK(CUR_APP, "ValidatePDP");
				// Distinct Validations in Tires PDP
				LOGMSG = "Size not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPSize", true));
				LOGMSG = "Store Availability tab not Present in Product details page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPTiresStore", true));
				LOGMSG = "Mile Warranty tab not Present in Product details page";
				softAssert.assertTrue(
						operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPTiresVehDesc_Mile", true));
				LOGMSG = "Service description tab not Present in Product details page";
				softAssert.assertTrue(
						operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPTiresVehDesc_Service", true));
				LOGMSG = "Description section not Present in Product details page";
				softAssert
						.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPDescription", true));
				LOGMSG = "Specification section not Present in Product details page";
				softAssert.assertTrue(
						operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPSpecifications", true));
				LOGMSG = "Performance section not Present in Product details page";
				softAssert
						.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPPerformance", true));
				LOGMSG = "CustomerReview section not Present in Product details page";
				softAssert.assertTrue(
						operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPCustomerReview", true));

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateProductsPDP
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate some distinct
	 * elements under Products section Product details page.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 22nd December 2017
	 * @modificationDate NA </div>
	 */

	public class ValidateProductsPDP extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// Calling Keyword ValidatePDP
				Keyword.runK(CUR_APP, "ValidatePDP");
				// Distinct Validations in Products PDP
				LOGMSG = "Description section not Present in Product details page";
				softAssert
						.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPProductsDesc", true));
				LOGMSG = "Specification section not Present in Product details page";
				softAssert
						.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPProductsSpec", true));
				LOGMSG = "CustomerReview section not Present in Product details page";
				softAssert.assertTrue(
						operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPProductsReview", true));
				LOGMSG = "Availability text not Present in Product details page";
				softAssert
						.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPProductAvail", true));
				LOGMSG = "Products Attributes not Present in Product details page";
				softAssert
						.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_PDPProductAttr", true));

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> AddToBag
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to add a product from
	 * product result / product details page to cart
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>ISPRP (Mandatory): Whether to add a product from PRP od PDP (Eg :
	 * TRUE or FALSE etc..)</li>
	 * <li>AddToCart (Mandatory): Choose the shipping options (Eg : STH, PUIS
	 * etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 20th December 2017
	 * @modificationDate NA </div>
	 */

	public class AddToBag extends Keyword {
		String propertyValue = "";
		private static final String ADDTOCART = "AddToCart";
		private static final String ISPRP = "IsPRP";
		private static final String CLICKADDTIRES = "IsAddTiresClick";
		boolean IsfetchProp;

		// Fetch the Property and Replace INDEX with RandomIndex
		public String fetchProperty(WebDriver driver, String Property, int index) throws Exception {
			String eleIdentifier = gei.getProperty(Property);
			eleIdentifier = eleIdentifier.replace("{INDEX}", "" + index + "");
			System.out.println("inside ATb" + eleIdentifier);
			WebElement element = operations.getWebElement(driver, "xpath", eleIdentifier);
			JavascriptExecutor jsee = (JavascriptExecutor) driver;
			jsee.executeScript("arguments[0].scrollIntoView(true)", element);
			jsee.executeScript("window.scrollBy(0,-150);", element);
			return eleIdentifier;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ADDTOCART, ISPRP);
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			try {
				WebDriver driver = context.getWebDriver();
				//Appending FullfillmentMethods in a String Separated by ~
				FullfillmentMethods = FullfillmentMethods.concat(args.get(ADDTOCART) + "~");
				Thread.sleep(1000);
				LOGMSG = "Fullfillment Option "+args.get(ADDTOCART)+" not clickable";
				// Adding a Product to cart from PRP or PDP
				// if ISPRP is true - add a product from PRP
				// if ISPRP is false - add a product from PDP
				// STH - ShiptoHome, PUIS - PickUpInStore, INST - Installation
				// in Store
				if (args.get(ISPRP).equalsIgnoreCase("true")) {
					IsfetchProp = false;
					// if fullFillment method is ShiptoHome choose Ship to Home radio button
					if (args.get(ADDTOCART).equalsIgnoreCase("STH")) {						
						propertyValue = fetchProperty(driver, "radioBtnShip", RandomIndex);
						System.out.println("scrolled");
						operations.clickElement(driver, "xpath", propertyValue, IsfetchProp);
					}
					// if fullFillment method is pickUpInStore choose Pick up in store radio button
					else if (args.get(ADDTOCART).equalsIgnoreCase("PUIS")) {
						System.out.println("inside puis");
						propertyValue = fetchProperty(driver, "radioBtnPickUpInStore", RandomIndex);
						System.out.println(propertyValue);
						operations.clickElement(driver, "xpath", propertyValue, IsfetchProp);
						System.out.println("completed puis");
					}
					// if fullFillment method is InstallationinStore choose Installation in store radio button
					else {
						propertyValue = fetchProperty(driver, "radioBtnInstall", RandomIndex);
						// System.out.println(element);
						operations.clickElement(driver, "xpath", propertyValue, IsfetchProp);
						System.out.println("completed inst");
					}
					Thread.sleep(2000);					
					LOGMSG = "Add to cart button not clickable from PRP";
					String eleIdentifier = gei.getProperty("BtnPRPAdd");
					// Fetch the Property and Replace INDEX with RandomIndex
					eleIdentifier = eleIdentifier.replace("{INDEX}", "" + RandomIndex + "");
					WebElement element = operations.getWebElement(driver, "xpath", eleIdentifier);
					//Thread.sleep(2000);
					//Scroll to the Particular Product
					JavascriptExecutor jsee = (JavascriptExecutor) driver;
					jsee.executeScript("window.scrollBy(0,-100);", element);
					Thread.sleep(4000);
					// Click Add to cart button in PRP
					operations.clickElement(driver, "xpath", eleIdentifier, false);
					System.out.println("clicked");
					Thread.sleep(4000);
					//If INST -> See Full Price With Installation Button Present -> Naviagates to PDP
					//CheckBoxes under INST are Not Checked
					try
					{
						/*
						 * For Installation in Store, Clicking add to cart will be
						 * moving to PDP[We have only see full price for
						 * installation button in PRP] click again Add to cart
						 * button to add a product to cart
						 */
						if (args.get(ADDTOCART).equalsIgnoreCase("INST")) {
							LOGMSG = "Moved to PDP, Add to cart button not clickable for INST";
							operations.clickElement(driver, "xpath", "BtnAdd", true);
						}
						Thread.sleep(2000);
						}
					//Else MiniCart PopUps
					catch (Exception e) {
						System.out.println("Not Naviagted to PDP, CheckBoxes are Checked Already");
					}
				}
				// Adding a Product from PDP
				else {
					IsfetchProp = true;
					// choosing FullFillment radio button
					// if fullFillment method is ShiptoHome choose Ship to Home radio button
					if (args.get(ADDTOCART).equalsIgnoreCase("STH")) {
						operations.clickElement(driver, "xpath", "radioBtn_PDPShipToHome", IsfetchProp);
						System.out.println("chosedn STH");
					}
					// if fullFillment method is pickUpInStore choose Pick up in store radio button
					else if (args.get(ADDTOCART).equalsIgnoreCase("PUIS")) {
						operations.clickElement(driver, "xpath", "radioBtn_PDPPickUpInStore", IsfetchProp);
						System.out.println("completed puis from pdp");
					} 
					// if fullFillment method is InstallationinStore choose Installation in store radio button
					else {
						operations.clickElement(driver, "xpath", "radioBtn_PDPInstall", IsfetchProp);
						//Fetch the Text of Add to Cart button
						String readCart = operations.getText(driver, "xpath", "BtnAdd", IsfetchProp);
						try
						{
							//Check Add Tires button
							if (args.get(CLICKADDTIRES).equalsIgnoreCase("true")) {
								System.out.println("clicking add tires");
								LOGMSG = "Add Tires button not clickable in PDP";
								//Click Add Tires button
								operations.clickElement(driver, "xpath", "CheckBoxPDPAddTires", true);
								Thread.sleep(500);
							}
						}
						catch (Exception e) {
							System.out.println("Add Tires button not needed");
						}
						//If it is Installation button
						if (readCart.contains("PRICING WITH INSTALLATION")) {
							LOGMSG = "Pricing with instaalation button is not clickable in PDP";
							//Click Add to Cart button
							operations.clickElement(driver, "xpath", "BtnAdd", IsfetchProp);
							System.out.println("clicked full price with installation");
							Thread.sleep(2000);
						}
					}
					Thread.sleep(2000);
					// Clicking Add to Bag button from PDP
					try {
						LOGMSG = "Add to cart button not clickable for INST from PDP";
						operations.clickElement(driver, "xpath", "BtnAdd", IsfetchProp);
						System.out.println("clicking...");
						Thread.sleep(3000);
						System.out.println("wait completed...");
					} catch (Exception e) {
						System.out.println("No add to cart button");
					}
					System.out.println("clicked add to cart");
				}
			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}

	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> HandleShoppingCart
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate and handle
	 * the Cart popup's
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>AddToCart (Mandatory): FullFillment method (Eg : STH, PUIS, INST
	 * etc..)</li>
	 * <li>Operation (Mandatory): Continue shopping or proceed to cart (Eg :
	 * ViewCart, Continue Shopping etc..)</li>
	 * <li>DayOfWeek (Mandatory): Day of appointment for installation in store
	 * service (Eg : Sat, Sun etc..)</li>
	 * <li>TimeOfDay (Mandatory): Day of appointment for installation in store
	 * service (Eg : 8, 3 etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 20th December 2017
	 * @modificationDate NA </div>
	 */


	public class HandleShoppingCart extends Keyword {
		private static final String ADDTOCART = "AddToCart";
		private static final String OPERATION = "Operation";
		private static final String ISPRP = "IsPRP";

		boolean InvokeAppointment = true;

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(OPERATION, ADDTOCART, ISPRP);
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();
			
			try {
				// Validating elements in View cart Popup
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Validate_sKu_part_cart_popUp", true));
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Validate_quant_price_cart_popUp", true),"FAIL:Not Displayed");
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Validate_img_cart-popup", true),"FAIL:Not Displayed");
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Validate_TotalOrder_cart_popup", true),"FAIL:Not Displayed");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Either to proceed with View cart or Continue Shopping or See Tires
				if (args.get(OPERATION).equals("ViewCart")) {
					//Click View Cart Button
					operations.clickElement(driver, "xpath", "BtnViewCart", true);
					//Validating whether Navigated to Shopping Cart or not
					LOGMSG = "Not navigated to shopping cart page back";
					try {
						softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath",
								"validateShoppingCartPagename", true));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (args.get(OPERATION).equalsIgnoreCase("See Tires")) {
					System.out.println("click see tires");
					//Click See Tires Button
					operations.clickElement(driver, "xpath", "BtnSeeTires", true);
					/*If InvokeAppointment -> True - Fill Appointment
					 *Else - On Clicking See Tires, Navigated to PRP */
					InvokeAppointment = false;
					System.out.println("clicked see tires");
				} else {
					//Click Continue Shopping Button
					operations.clickElement(driver, "xpath", "BtnContShopping", true);
					try {
						//Validating Whether Navigated to PRP or PDP based on ISPRP Value
						if (args.get(ISPRP).equalsIgnoreCase("false")) {
							//If ISPRP - False -> In Product Details page
							LOGMSG = "Not navigated to PDP page back";
							softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ValidateProdNamePDP", true));
							System.out.println("Navigated to PDP");

						} else {
							//If ISPRP - True -> In Product Results page
							LOGMSG = "Not navigated to PRP page back";
							softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ValidateProdsnamePRP", true));
							System.out.println("Navigated to PRP");

						}
					} catch (Exception e) {

					}
				}
				// Filling Appointment modal if FullFillment Option is Installation in Stores and InvokeAppointment is True			
				if (args.get(ADDTOCART).equalsIgnoreCase("INST") && InvokeAppointment) {
					try {
						//Call Keyword InsatllationPopUp
						Keyword.runK(CUR_APP, "InsatllationPopUp");
						Thread.sleep(1000);
					} catch (KDTException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> InsatllationPopUp
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to handle the appointemnt
	 * modal
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>DayOfWeek (Mandatory): Day of appointment for installation in store
	 * service (Eg : Sat, Sun etc..)</li>
	 * <li>TimeOfDay (Mandatory): Day of appointment for installation in store
	 * service (Eg : 8, 3 etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 28th December 2017
	 * @modificationDate NA </div>
	 */

	public class InsatllationPopUp extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			System.out.println("entering instal popup");
			boolean SlotAvailFlag = true;
			try
			{
				while(SlotAvailFlag)
				{
					//Fetch the Property
					String eleIdentifier = gei.getProperty("InstallTimeSlot");
					List<WebElement> TimeSlotElements ;
					//Fetch the Available Time Slots
					TimeSlotElements = operations.getWebElements(driver, "xpath", eleIdentifier);
					System.out.println("Available TimeSlot"+TimeSlotElements.size());
					if(TimeSlotElements.size()>0)
					{
						//Choose Randomly
						int RandomSlot = operations.GenerateRandomNumer(driver, TimeSlotElements.size());
						System.out.println( TimeSlotElements.get(RandomSlot));
						//Click chosen Slot
						operations.javaScriptClick(driver, TimeSlotElements.get(RandomSlot));			
						// Click Schedule appointment button
						operations.clickElement(driver, "xpath", "Btn_scheduleAppointment", true);	
						SlotAvailFlag = false;
					}
					else
					{
						try
						{
							// Click Next 5 days Button
							operations.clickElement(driver, "xpath", "Btn_NextFiveDays", true);	
							System.out.println("Clicked Next 5 days Button");
							SlotAvailFlag = true;
						}
						catch (Exception e) {
							// TODO: handle exception
							SlotAvailFlag = false;
							System.out.println(e.toString());
							Keyword.addComment("No Appointment Slots are Available");
						}
						
					}
				}
			}
			catch (Exception e) {
				throw new KDTKeywordExecException("Unable to Choose Time Slot in Installation PopUp", e);
			}			
		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> LoggedInCheckOut
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to do logged in checkout
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Email (Mandatory): Email data to login (Eg : xyz@gmail.com
	 * etc..)</li>
	 * <li>Password (Mandatory): Password data to login (Eg : ***** etc..)</li>
	 * <li>GiftCardNum (Mandatory): GiftCardN number to validate (Eg :
	 * 6006491368999905019 etc..)</li>
	 * <li>PinCardNum (Mandatory): Pin card number for gift card (Eg : 4452
	 * etc..)</li>
	 * <li>CardNum (Mandatory): Payment card number (Eg :4111111111111111
	 * etc..)</li>
	 * <li>ExpMonth (Mandatory): Card Expiration month (Eg : 03 etc..)</li>
	 * <li>ExpYear (Mandatory): Card Expiration year (Eg : 2018 etc..)</li>
	 * <li>Security (Mandatory): Card security number (Eg : 123 etc..)</li>
	 * <li>AddToCart (Mandatory): FullFillment method (Eg : STH, PUIS, INST
	 * etc..)</li>
	 * <li>CheckoutOption (Mandatory): To do normal checkout or Pay in Store (Eg
	 * : Pay in store, Checkout etc..)</li>
	 * <li>ChangeFullfilment (Optional): Change fullFillment in Shopping cart
	 * page (Eg : STH, PUIS, INST etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 22nd December 2017
	 * @modificationDate NA </div>
	 */
	public class LoggedInCheckOut extends Keyword {

		private static final String EMAIL = "Email";
		private static final String PASSWORD = "Password";
		private static final String GIFTCARDNUM = "GiftCardNum";
		private static final String PINCARDNUM = "PinCardNum";
		private static final String CARDNUM = "CardNum";
		private static final String EXPMONTH = "ExpMonth";
		private static final String EXPYEAR = "ExpYear";
		private static final String SECURITY = "Security";
		private static final String CHANGEFULLFILLMENT = "ChangeFullfilment";
		private static final String CHECKOUTOPTION = "CheckoutOption";
		private static final String ISLOGGEEDIN = "IsLoggedIn";

		@Override
		public void init() throws KDTKeywordInitException {

			super.init();
			verifyArgs(EMAIL, PASSWORD, GIFTCARDNUM, PINCARDNUM, CARDNUM, EXPMONTH, EXPYEAR, SECURITY);

		}

		@Override
		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			// operations.clickElement(driver, "xpath", "BtnCheckOut", true);
			try {
				// Choosing checkout option either Pay in Store or Checkout
				if (args.get(CHECKOUTOPTION).equalsIgnoreCase("pay in store")) {
					//Click Pay in Store Button
					operations.clickElement(driver, "xpath", "BtnCheckOutPayinStore", true);
					System.out.println("clicked pay in store");
				} else {
					//Click Checkout button
					operations.clickElement(driver, "xpath", "BtnCheckOut", true);
					operations.WaitForPageload(driver);
				}
				// operations.closeAd(driver,"BtnAdClose");
				//If need to do Log In Checkout ISLOGGEEDIN should be False
				if (args.get(ISLOGGEEDIN).equalsIgnoreCase("false")) {
					//Call Keyword Login
					Keyword.runK(CUR_APP, "Login", EMAIL, args.get(EMAIL), PASSWORD, args.get(PASSWORD));
				}
				/*If Default ship address is present, choose it
				 * Else Click Next*/
				try
				{
					// Choose default ship address
					operations.clickElement(driver, "xpath", "RadioBtnDEfault", true);
				}
				catch (Exception e) {
					//If it's a New User Account - No Billing address Radio buttons are Present.
					System.out.println("No Radio Billing Address button are Present");				
				}
				// If Pay in Store Click Finalize order button
				if (args.get(CHECKOUTOPTION).equalsIgnoreCase("pay in store")) {
					//Click Click Finalize order button
					operations.clickElement(driver, "xpath", "BtnFinalOrder", true);
				}
				// Else Proceed to next
				else {
					//Click Next button
					operations.clickElement(driver, "xpath", "BtnNextSaveAddress", true);
					// operations.closeAd(driver,"BtnAdClose");
					// System.out.println("add to cart "+args.get(ADDTOCART));
					try {
						 //If FullFillment method is ship to home, we have Delivery methods tab
						if (FullfillmentMethods.contains("STH") || args.get(CHANGEFULLFILLMENT).equalsIgnoreCase("STH"))						{
							// Verify address popUp
							operations.clickElement(driver, "xpath", "verifyAddressPopup", true);
							// Proceed to next
							operations.clickElement(driver, "xpath", "BtnNextDeliveryMethod", true);
							// operations.closeAd(driver,"BtnAdClose");
							if (FullfillmentMethods.contains("STH")) {
								// Enter Gift card number
								operations.enterText(driver, "xpath", "GiftCardTextBox", args.get(GIFTCARDNUM));
								// Enter Gift card pin
								operations.enterText(driver, "xpath", "GiftPinTextBox", args.get(PINCARDNUM));
								// Click Apply button
								operations.clickElement(driver, "xpath", "GiftApplyBtn", true);
								// Click Visa Radio button
								operations.clickElement(driver, "xpath", "VisaBtn", true);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("CHANGEFULLFILLMENT is not present" + e.toString());
					}
					// Enter Card Number
					operations.enterText(driver, "xpath", "CardBtn", args.get(CARDNUM));
					// Choose Card expiration Month
					operations.SelectComboBoxByVisibleText(driver, "xpath", "DropDwnSExpMonth", args.get(EXPMONTH),
							true);
					// Choose Card expiration Year
					operations.SelectComboBoxByVisibleText(driver, "xpath", "DropDwnSExpYear", args.get(EXPYEAR), true);
					// Enter Security Code
					operations.enterText(driver, "xpath", "TxtSecurityCode", args.get(SECURITY));
					// Check Agree button
					operations.clickElement(driver, "xpath", "BtnAgree", true);
					// Click Submit button
					operations.clickElement(driver, "xpath", "SubmitBtn", true);
					driver.manage().deleteAllCookies();
					//softAssert.assertAll();
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}

	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseTiresOnAvail
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to do select a product in
	 * Tires PRP based on availability
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Avail (Mandatory): Availability (Eg : Today etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 28th December 2017
	 * @modificationDate NA </div>
	 */

	public class ChooseTiresOnAvail extends Keyword {

		private static final String AVAILABILITY = "Avail";
		String property;

		// Fetch the Property and Replace {INDEX} with Index passed
		public String fetchProperty(WebDriver driver, String Property, int index) throws Exception {
			String eleIdentifier = gei.getProperty(Property);
			eleIdentifier = eleIdentifier.replace("{INDEX}", "" + index + "");
			System.out.println("inside ATb" + eleIdentifier);
			return eleIdentifier;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			// TODO Auto-generated method stub
			super.init();
			verifyArgs(AVAILABILITY);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stub
			try {
				WebDriver driver = context.getWebDriver();
				ArrayList<Integer> FetchAvailProductIndex = new ArrayList<Integer>();
				//Loop all the Product's index in FetchIndex List
				//FetchIndex list has Product's index with Specified FullFillment Option
				for (int index = 0; index < FetchIndex.size(); index++) {
					//Fetch the Property and Replace {INDEX} in XPath with index of the loop
					property = fetchProperty(driver, "Text_PRPAvail", FetchIndex.get(index));
					String PropertyVal = fetchProperty(driver, "Text_PRPAvailToolTip", FetchIndex.get(index));
					WebElement element = operations.getWebElement(driver, "xpath", PropertyVal);
					// Mouse Hovering to store Availability
					Actions action = new Actions(driver);
					action.moveToElement(element).click().build().perform();
					// Thread.sleep(500);
					// Fetching the available text
					String getAvailText = operations.getText(driver, "xpath", property, false);
					System.out.println("available " + getAvailText);
					// Compare the data with excel and Add all the Product index into FetchAvailProductIndex
					if (getAvailText.equalsIgnoreCase(args.get(AVAILABILITY))) {
						FetchAvailProductIndex.add(FetchIndex.get(index));
					}
				}
				System.out.println(FetchAvailProductIndex.size());

				//If atLeast One Product is Present based on Availability
				if (FetchAvailProductIndex.size() > 1) {
					// Generate a random number make RandomIndex
					RandomIndex = operations.GenerateRandomNumer(driver, FetchAvailProductIndex.size());
					System.out.println(RandomIndex);
					//Fetch the Product index from List
					RandomIndex = FetchAvailProductIndex.get(RandomIndex - 1);
					System.out.println(RandomIndex);
				} else {
					System.out.println("No Products based on Availble");
				}

			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to choose Product based on availability", e);
			}

		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseProductsOnAvail
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to do select a product in
	 * Products PRP based on availability
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateProductsPRP</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Avail (Mandatory): Availability (Eg : Available today etc..)</li>
	 * </ul> 
	 * @author
	 * @version 001
	 * @creationDate 28th December 2017
	 * @modificationDate NA </div>
	 */

	public class ChooseProductsOnAvail extends Keyword {

		private static final String AVAILABILITY = "Avail";
		String property;

		// Fetch the Property and Replace {INDEX} with Index passed
		public String fetchProperty(WebDriver driver, String Property, int index) throws Exception {
			String eleIdentifier = gei.getProperty(Property);
			eleIdentifier = eleIdentifier.replace("{INDEX}", "" + index + "");
			System.out.println("inside ATb" + eleIdentifier);
			return eleIdentifier;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			// TODO Auto-generated method stub
			super.init();
			verifyArgs(AVAILABILITY);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stub
			try {
				WebDriver driver = context.getWebDriver();
				ArrayList<Integer> FetchAvailProductIndex = new ArrayList<Integer>();
				//Loop all the Products in FetchIndex
				//FetchIndex list has Products with Specified FullFillment Option
				for (int index = 0; index < FetchIndex.size(); index++) {
					//Fetch the Property and Replace {INDEX} in XPath with index of the loop
					property = fetchProperty(driver, "Text_ProductsPRPAvail", FetchIndex.get(index));
					// Read the Product Availability data [ex: Available Today]
					String getAvailText = operations.getText(driver, "xpath", property, false);
					System.out.println(getAvailText);
					// Compare the data with excel and Add all the Product index into FetchAvailProductIndex
					if (getAvailText.equalsIgnoreCase(args.get(AVAILABILITY))) {
						System.out.println("available" + getAvailText);
						FetchAvailProductIndex.add(FetchIndex.get(index));
					}					
				}
				if (FetchAvailProductIndex.size() > 1) {
					// Genearte a random number make RandomIndex
					RandomIndex = operations.GenerateRandomNumer(driver, FetchAvailProductIndex.size());
					System.out.println(RandomIndex);
					//Fetch the Product index from List
					RandomIndex = FetchAvailProductIndex.get(RandomIndex - 1);
					System.out.println(RandomIndex);
				} else {
					System.out.println("No Products based on Availble");
				}
			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to choose Product based on availability under products", e);
			}

		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> GuestCheckOut
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to do guest checkout
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>FirstName (Mandatory): First Name for the shipment (Eg : xyz
	 * etc..)</li>
	 * <li>LastName (Mandatory): Last Name for the shipment (Eg : abc
	 * etc..)</li>
	 * <li>Address1 (Mandatory): Address for the shipment (Eg : New street
	 * etc..)</li>
	 * <li>City (Mandatory): City for the shipment (Eg : Avenel etc..)</li>
	 * <li>State (Mandatory): State for the shipment (Eg : NJ etc..)</li>
	 * <li>ZipCode (Mandatory): ZipCode for the shipment (Eg : 07001 etc..)</li>
	 * <li>PhoneNumber (Mandatory): Phone Number for the shipment (Eg :
	 * 2365201478 etc..)</li>
	 * <li>Email (Mandatory): Email data for the shipment (Eg : xyz@gmail.com
	 * etc..)</li>
	 * <li>CardNumber (Mandatory): Payment card number (Eg :4111111111111111
	 * etc..)</li>
	 * <li>ExpiryMonth (Mandatory): Card expiration month (Eg : 03 etc..)</li>
	 * <li>ExpiryYear (Mandatory): Card expiration year (Eg : 2018 etc..)</li>
	 * <li>SecurityCode (Mandatory): Card security number (Eg : 123 etc..)</li>
	 * <li>AddToCart (Mandatory): FullFillment method (Eg : STH, PUIS, INST
	 * etc..)</li>
	 * <li>CheckoutOption (Mandatory): To do normal checkout or Pay in Store (Eg
	 * : Pay in store, Checkout etc..)</li>
	 * <li>ChangeFullfilment (Optional): Change fullFillment in Shopping cart
	 * page (Eg : STH, PUIS, INST etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 27th December 2017
	 * @modificationDate NA </div>
	 */


	public class GuestCheckOut extends Keyword {
		private static final String FIRSTNAME = "FirstName";
		private static final String LASTNAME = "LastName";
		private static final String ADDRESS1 = "Address1";
		private static final String CITY = "City";
		private static final String STATE = "State";
		private static final String ZIPCODE = "ZipCode";
		private static final String PHONENUMBER = "PhoneNumber";
		private static final String EMAIL = "Email";
		private static final String CARDNUMBER = "CardNumber";
		private static final String EXPMONTH = "ExpiryMonth";
		private static final String EXPYEAR = "ExpiryYear";
		private static final String SECURITYCODE = "SecurityCode";
		private static final String CHANGEFULLFILLMENT = "ChangeFullfilment";
		private static final String CHECKOUTOPTION = "CheckoutOption";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(FIRSTNAME, LASTNAME, ADDRESS1, CITY, STATE, ZIPCODE, PHONENUMBER, EMAIL, CARDNUMBER, EXPMONTH,
					EXPYEAR, SECURITYCODE, CHECKOUTOPTION);
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();

			try {
				// operations.clickElement(driver, "xpath", "BtnCheckOut",
				// true);
				// Choosing checkout option either Pay in Store or Checkout
				if (args.get(CHECKOUTOPTION).equalsIgnoreCase("pay in store")) {
					//Click Pay in Store Button
					operations.clickElement(driver, "xpath", "BtnCheckOutPayinStore", true);
					System.out.println("clicked pay in store");
				} else {
					//Click Checkout button
					operations.clickElement(driver, "xpath", "BtnCheckOut", true);
				}
				// Clicks on Check Out as Guest Button
				operations.clickElement(driver, "xpath", "btn_GuestCheckout", true);
				// Enters FirstName
				operations.enterText(driver, "xpath", "TxtBoxFirstName", args.get(FIRSTNAME));
				// Enters LastName
				operations.enterText(driver, "xpath", "TxtBoxLastName", args.get(LASTNAME));
				// Enters Address 1
				operations.enterText(driver, "xpath", "TxtBoxAddress", args.get(ADDRESS1));
				// Enters city
				operations.enterText(driver, "xpath", "TxtBoxCity", args.get(CITY));
				// selects State
				operations.SelectComboBoxByVisibleText(driver, "xpath", "SelectState", args.get(STATE), true);
				// Enters Zipcode
				operations.enterText(driver, "xpath", "TxtBoxZipCode", args.get(ZIPCODE));
				// Enters PhoneNumber
				operations.enterText(driver, "xpath", "TxtBoxPhoneNumber", args.get(PHONENUMBER));
				// Enters Email address
				operations.enterText(driver, "xpath", "TxtBoxEmailGuest", args.get(EMAIL));
				// Re-Enters Email Address
				operations.enterText(driver, "xpath", "TxtBoxConfirmEmailGuest", args.get(EMAIL));
				// Scrolling
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)", "");
				// If Pay in Store means click Finalize order button
				if (args.get(CHECKOUTOPTION).equalsIgnoreCase("pay in store")) {
					operations.clickElement(driver, "xpath", "BtnFinalOrder", true);
				} else {
					// Clicks on Next Button
					operations.clickElement(driver, "xpath", "BtnNextSaveAddress", true);
					try {
						// if FullFillment method is Ship to Home we have Delivery method Tab
						if (FullfillmentMethods.contains("STH") || args.get(CHANGEFULLFILLMENT).equalsIgnoreCase("STH")) {
							// Click on Confirm address
							operations.clickElement(driver, "xpath", "verifyAddressPopup", true);
							// Scroll
							JavascriptExecutor obj = (JavascriptExecutor) driver;
							obj.executeScript("window.scrollBy(0,500)", "");
							// Click Next button
							operations.clickElement(driver, "xpath", "btnGuestNext", true);
							driver.findElement(By.xpath("//div[@class='radio']//div[@class='visaCardIcon']")).click();
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("CHANGEFULLFILLMENT is not present" + e.toString());
					}
					Thread.sleep(500);
					// Enter card number
					operations.enterText(driver, "xpath", "CardBtn", args.get(CARDNUMBER));
					// Select Card Expiration Month
					operations.SelectComboBoxByVisibleText(driver, "xpath", "DropDwnSExpMonth", args.get(EXPMONTH),
							true);
					// Select Card Expiration Year
					operations.SelectComboBoxByVisibleText(driver, "xpath", "DropDwnSExpYear", args.get(EXPYEAR), true);
					// Enters Security code
					operations.enterText(driver, "xpath", "TxtSecurityCode", args.get(SECURITYCODE));
					// Checks the checkBox after entering the details
					operations.clickElement(driver, "xpath", "BtnAgree", true);
					// operations.selectCheckBox(driver,"xpath",chkBox);
					// Clicks on submit Order
					operations.clickElement(driver, "xpath", "SubmitBtn", true);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChangeFullfillmentMethodinCart
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to change the
	 * fullfillment method in cart page.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>ChangeFullfilment (Optional): Change fullFillment in Shopping cart
	 * page (Eg : STH, PUIS, INST etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */


	public class ChangeFullfillmentMethodinCart extends Keyword {

		private static final String CHANGEFULLFILLMENT = "ChangeFullfilment";
		private static final String STH = "Ship to Home";
		private static final String PUIS = "Pick Up In Store";
		private static final String INSTALL = "Installation";
		String property;

		@Override
		public void init() throws KDTKeywordInitException {
			// TODO Auto-generated method stub
			super.init();
			verifyArgs(CHANGEFULLFILLMENT);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stub
			try {
				WebDriver driver = context.getWebDriver();
				//Fetch the Property
				String eleIdentifier = gei.getProperty("radioBtn_chngeFullfillment");
				// Replace XPath with respective fullFillment method value
				LOGMSG = "Unable to change shipping method in cart";
				if (args.get(CHANGEFULLFILLMENT).equalsIgnoreCase("STH")) {
					eleIdentifier = eleIdentifier.replace("{FFtype}", STH);
				} else if (args.get(CHANGEFULLFILLMENT).equalsIgnoreCase("PUIS")) {
					eleIdentifier = eleIdentifier.replace("{FFtype}", PUIS);
				} else {
					eleIdentifier = eleIdentifier.replace("{FFtype}", INSTALL);
				}
				// Change fullFillment method - Choose radio button
				operations.clickElement(driver, "xpath", eleIdentifier, false);
				// Validate Prices in Cart page
				LOGMSG = "Sub Total not present in cart page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartSubTotal", true));
				LOGMSG = "State Tax not Present in cart page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartStateTax", true));
				LOGMSG = "Local Tax not Present in cart page ";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartLocalTax", true));
				LOGMSG = "Grand total not Present in cart page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartGrandTotal", true));

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> SideMenuClick
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to choose the options
	 * from pancake
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>SideMenuOption (Optional): Menu name (Eg : Home etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */
	public class SideMenuClick extends Keyword {

		private static final String SIDEMENUOPTION = "SideMenuOption";
		private static final String ISSUBCATEGORY = "IsSubCategory";
		private static final String SUBMENUOPTION = "SubMenuOption";
		private static final String SUBMENUSUBOPTION = "SubMenuSubOption";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(SIDEMENUOPTION, ISSUBCATEGORY, SUBMENUOPTION, SUBMENUSUBOPTION);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				Thread.sleep(4000);
				//Click Pancake Button
				operations.clickElement(driver, "xpath", "Btn_Menu", true);
				//Pancake With SubCategory
				if (args.get(ISSUBCATEGORY).equalsIgnoreCase("true")) {
					Thread.sleep(2000);
					//Fetch the Property
					String eleIdentifierMainMenu = gei.getProperty("Btn_Side_Menu_OptionsWithSubCate");
					//Replace {sideMenuOption} in XPath with SIDEMENUOPTION Value passed from Excel
					eleIdentifierMainMenu = eleIdentifierMainMenu.replace("{sideMenuOption}", args.get(SIDEMENUOPTION));
					Thread.sleep(2000);
					//Click SideMenu at Level1
					operations.clickElement(driver, "xpath", eleIdentifierMainMenu, false);
					Thread.sleep(2000);
					//Fetch the Property
					String eleIdentifierSubMenu = gei.getProperty("Btn_Side_MenuSubMenuWithSubCate");
					//Replace {subMenu} in XPath with SUBMENUOPTION Value passed from Excel
					eleIdentifierSubMenu = eleIdentifierSubMenu.replace("{subMenu}", args.get(SUBMENUOPTION));
					Thread.sleep(2000);
					//Click SubMenu at Level2
					operations.clickElement(driver, "xpath", eleIdentifierSubMenu, false);
					Thread.sleep(2000);
					//Fetch the Property
					String eleIdentifierSubMenuSub = gei.getProperty("Btn_Side_MenuSumMenu_SubOption");
					//Replace {subMenuSubOptin} in XPath with SUBMENUSUBOPTION Value passed from Excel
					eleIdentifierSubMenuSub = eleIdentifierSubMenuSub.replace("{subMenuSubOptin}",
							args.get(SUBMENUSUBOPTION));
					Thread.sleep(2000);
					//Click SubMenu at Level3
					operations.clickElement(driver, "xpath", eleIdentifierSubMenuSub, false);

				} 
				//Pancake Without SubCategory [Ex: Home]
				else {
					//Fetch the Property
					String eleIdentifier = gei.getProperty("Btn_Side_Menu_Options");
					//Replace {SideMenuOption} in XPath with SIDEMENUOPTION Value passed from Excel
					eleIdentifier = eleIdentifier.replace("{SideMenuOption}", args.get(SIDEMENUOPTION));
					//Click SideMenu
					operations.clickElement(driver, "xpath", eleIdentifier, false);
				}

			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to click Side menu element", e);
			}
		}

	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> Search
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to do search function
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Texttosearch (Optional): Search term (Eg : Air Filters etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class Search extends Keyword {
		private static final String SEARCH_TEXT = "Texttosearch";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(SEARCH_TEXT);

		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try
			{
				WebDriver driver = context.getWebDriver();
				// Click on Search textBox
				operations.clickElement(driver, "xpath", "search_txt_box", true);
				// Enter search term
				operations.enterText(driver, "xpath", "search_txt_box", args.get(SEARCH_TEXT));
				// Click search button
				operations.clickElement(driver, "xpath", "search_icon", true);
			}
			catch (Exception e) {
				throw new KDTKeywordExecException("Unable to Perform Search Operation", e);
			}
		}
	}

	// #################### Keyword Ends ###################
	
	// #################### Keyword Starts #################
		/**
		 * <div align="left">
		 * <p>
		 * <b><i>Keyword Name:</i></b> ChoosePayInStoreProduct
		 * </p>
		 * <p>
		 * <b><i>Description:</i></b> This Keyword is used to do randomly select a product with "Pay In Store" Option.
		 * </P>
		 * <b><i>Dependencies:</i></b>
		 * <ul>
		 * <li>Launch</li>
		 * <li>NavigateTo</li>
		 * <li>ClickMenu</li>
		 * <li>ClickSubMenu</li>
		 * <li>ChooseVehicle</li>
		 * <li>ChooseStore</li>
		 * <li>ValidatePRP</li>
		 * <li>ValidateTiresPRP</li>
		 * <li>ValidateProductsPRP</li>
		 * </ul>
		 * <b><i>Arguments:</i></b>
		 * <ul>
		 * <li>PayInStoreAvail :  (Eg :Pay In Store ..)</li>
		 * </ul>
		 * @author
		 * @version 001
		 * @creationDate 
		 * @modificationDate NA </div>
		 */

	public class ChoosePayInStoreProduct extends Keyword {

		private static final String PAYINSTOREAVAIL = "PayInStoreAvail";
		String property;

		// Fetch the Property and Replace {INDEX} in XPath with RandomIndex
		public String fetchProperty(WebDriver driver, String Property, int index) throws Exception {
			String eleIdentifier = gei.getProperty(Property);
			eleIdentifier = eleIdentifier.replace("{INDEX}", "" + index + "");
			System.out.println("inside ATb" + eleIdentifier);
			return eleIdentifier;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			// TODO Auto-generated method stub
			super.init();
			verifyArgs(PAYINSTOREAVAIL);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stub
			try {
				WebDriver driver = context.getWebDriver();				
				ArrayList<Integer> FetchAvailPayInStoreProduct = new ArrayList<Integer>();
				//Looping all the Product's in FetchIndex
				//FetchIndex list has the Product's Index with Specified FullFillment Methods
				for (int index = 0; index < FetchIndex.size(); index++) {
					// Fetch the Property and Replace {INDEX} in XPath with index of the loop
					property = fetchProperty(driver, "Text_PRPProductPayInStore", FetchIndex.get(index));
					// Read the Product Pay in Store Availability data [ex: Pay in Store Available]
					String getPayInStorePdt = operations.getText(driver, "xpath", property, false);
					System.out.println(getPayInStorePdt);
					// Compare the data with excel and Add all the Product index into FetchAvailProductIndex
					if (getPayInStorePdt.equalsIgnoreCase(args.get(PAYINSTOREAVAIL))) {
						System.out.println("Pay in store text" + getPayInStorePdt);
						//Adding the index to FetchAvailPayInStoreProduct list 
						FetchAvailPayInStoreProduct.add(FetchIndex.get(index));
					}					
				}
				//If AtLeast One Product is Having Pay in Store Option
				if (FetchAvailPayInStoreProduct.size() > 1) {
					// Genearte a random number make RandomIndex
					RandomIndex = operations.GenerateRandomNumer(driver, FetchAvailPayInStoreProduct.size());
					System.out.println(RandomIndex);
					// Fetch the index from List
					RandomIndex = FetchAvailPayInStoreProduct.get(RandomIndex - 1);
					System.out.println(RandomIndex);
				} else {
					System.out.println("No Products are Present for Pay in Store Option");
				}
			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to find Product with pay in store option", e);
			}

		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseWheelsPRP
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to do randomly select a
	 * "Wheel" for the vehicle from the Wheels PRP page
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>NavigateToChooseStore</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class ChooseWheelsPRP extends Keyword {

		String propertyValue;
		String Pname;
		int lastIndex;

		// Fetch the Property and Replace the {INDEX} with index value passed
		public String fetchProp(int index, String Property) throws KDTKeywordExecException {
			String eleIdentifier = gei.getProperty(Property);
			eleIdentifier = eleIdentifier.replace("{INDEX}", "" + index + "");
			return eleIdentifier;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();

			try {
				// operations.closeAd(driver,"BtnAdClose");
				Thread.sleep(500);
				//Fetch the Property
				String eleIdentifier = gei.getProperty("WheelContainer");
				//Fetch the Total number of Wheels Dipslayed
				List<WebElement> getTotalWheels = operations.getWebElements(driver, "xpath", eleIdentifier);
				System.out.println("total wheels displayed" + getTotalWheels.size());
				//Randomly choose the value
				int wheelRandomIndex = operations.GenerateRandomNumer(driver, getTotalWheels.size());
				System.out.println(wheelRandomIndex);
				//Fetch the Property and Replace the {INDEX} in XPath with wheelRandomIndex value
				propertyValue = fetchProp(wheelRandomIndex, "Btn_WheelsSeeDetails");
				//Click See Details button based on Chosen Wheel
				operations.clickElement(driver, "xpath", propertyValue, false);
				System.out.println("clicked see details");

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}
	}
	// #################### Keyword Ends ###################
	
	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> Login
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to Login to the Application with valid Credentials
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Email : Email Id (Eg : abc@gmail.com)</li>
	 *  <li>Password : Password (Eg : ********)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class Login extends Keyword {
		public static final String EMAIL = "Email";
		public static final String PASSWORD = "Password";

		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(EMAIL, PASSWORD);
		}

		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			try {
				try
				{
					//Current Page is Checkout					
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCheckoutHeader", true));
				}
				catch (Exception e) {
					// TODO: handle exception					
					// Click Login Button in Page
					operations.clickElement(driver, "xpath", "btn_Homelogin",true);
					System.out.println("In Checkout page");
				}
				// operations.closeAd(driver,"BtnAdClose");
				// Enters UserName
				operations.enterText(driver, "xpath", "TxtBoxEmail", args.get(EMAIL));
				// Enters Password
				operations.enterText(driver, "xpath", "TxtBoxpassword", args.get(PASSWORD));
				// Click Sign in button
				operations.clickElement(driver, "xpath", "btn_login", true);
				// operations.closeAd(driver,"BtnAdClose");
			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to Perform Login", e);
			}

		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> MyStores
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate "My Stores"
	 * tab under "My Account" page.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */


	public class MyStores extends Keyword {

		String Property;

		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();
			try {
				//Click My Stores tab
				LOGMSG = "My Stores Link is not Clickable in My account page";
				operations.clickElement(driver, "xpath", "MyStoreLink", true);
				LOGMSG = "See All Reviews is not present in stores page for all the listed stores";
				try
				{
					//Fetch property
					Property = gei.getProperty("SeeAllReviewTable");
					//Find the number of rows in MyStores Table
					List<WebElement> TotalStores = operations.getWebElements(driver, "xpath", Property);
					//Loop based on table count
					for (int index = 1; index <= TotalStores.size(); index++) {
						try {
							//Fetch the Property whether row contains SeeAllReviews Link is Present or not
							Property = gei.getProperty("SeeAllReviewLink");
							//Replace {INDEX} in XPath with loop index
							Property = Property.replace("{INDEX}", "" + index);
							//Click SeeAllReviews link
							operations.clickElement(driver, "xpath", Property, false);
							//Break the loop
							break;
						} catch (Exception e) {
							//SeeAllReviews Link is not Present
							System.out.println("Not contains Review Link");
						}
					}
	
					// operations.clickElement(driver, "xpath", "SeeAllReviewLink",
					// true);
					// operations.WaitForPageload(driver);
					
					//Get Child Window ID
					map = operations.getWindowId(driver);
					//Switch to child window
					driver.switchTo().window(map.get("childWinID"));
					//Validations in Specific Store page
					LOGMSG = "PepBoys text is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextStoreHeading", true));
					LOGMSG = "Make appointment button is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "BtnStoreMakeAppointment", true));
					LOGMSG = "Address is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextStoreAddress", true));
					LOGMSG = "Get Directions button is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "BtnStoreGetDirections", true));
					LOGMSG = "Get Tow Truck button is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "BtnStoreTowTruck", true));
					LOGMSG = "Full service Time is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyStoreFullService", true));
					LOGMSG = "Part and Accesories Time is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyStoreAccessoreisService", true));
					LOGMSG = "Local Ad button is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "BtnStoreLocalAd", true));
					LOGMSG = "View Coupons Button is not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "BtnStoreViewCoupons", true));
					LOGMSG = "Headers text are not present in stores page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyStoreHeader1", true));
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyStoreHeader2", true));
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyStoreHeader3", true));
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyStoreHeader4", true));
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyStoreHeader5", true));
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyStoreHeader6", true));
					//Close the Child window
					driver.close();
					//Switch to main Window
					driver.switchTo().window(map.get("parentWinID"));
				}
				catch (Exception e) {
					//If None of the row has SeeAllReviews Link, Validations will fail and gets Catched!
					System.out.println(LOGMSG);
				}				
				LOGMSG = "Add Store button is not Clickable in My Stores page";
				//Click Add Store button
				operations.clickElement(driver, "xpath", "BtnAddStore", true);
				System.out.println("clicked add store");
				Thread.sleep(1000);
				//Verify ZipCode Modal is Present
				LOGMSG = "Zipcode Modal is not displayed on Clicking Add Store link";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Zipcode_popup", true));
				Thread.sleep(1000);
				//Close the ZipCode Modal
				operations.clickElement(driver, "xpath", "BtnZipcodeClose", true);
				System.out.println("clicked zipcodeClose");
				LOGMSG = "Change Store button is not Clickable in My Stores page";
				//Click Change Store Button
				operations.clickElement(driver, "xpath", "ChangeStoreLink", true);
				System.out.println("clicked change store");
				Thread.sleep(1000);
				//Verify ZipCode Modal is Present
				LOGMSG = "Zipcode Modal is not displayed on Clicking Change Store link";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Zipcode_popup", true));

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> MyStoreAddRemove
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to add or remove
	 * previously added store.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Zip : Zip Code (Eg : 19152,198023 etc..)</li>
	 * <li>StoreName : Store Name (Eg : Philadelphia, Talleyville)</li>
	 * <li>RemoveStoreName : Store Name (Eg : Philadelphia, Talleyville)</li>
	 * <li>IsRemove : boolean value (Eg : true.false)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class MyStoreAddRemove extends Keyword {
		private static final String ZIP = "Zip";
		private static final String STORE = "StoreName";
		private static final String REMOVE_STORE = "RemoveStoreName";
		private static final String REMOVE = "IsRemove";
		String remove;

		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ZIP, STORE, REMOVE_STORE, REMOVE);
			remove = args.get(REMOVE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stub
			WebDriver driver = context.getWebDriver();
			try {
				LOGMSG = "My Stores Link is not Clickable in My account page";
				//Click on MyStore
				operations.clickElement(driver, "xpath", "MyStoreLink", true);
				LOGMSG = "Add Store button is not Clickable in My Stores page";
				//Click Add Store button
				operations.clickElement(driver, "xpath", "BtnAddStore", true);
				//Call ChooseStore to Fill ZipCode Modal
				Keyword.runK(CUR_APP, "ChooseStore", ZIP, args.get(ZIP), STORE, args.get(STORE));
				//Fetch the Property
				String Propertyvalue = gei.getProperty("Text_StoreName");
				//Replace {StoreName} in XPath with Value passed in Excel sheet
				Propertyvalue = Propertyvalue.replace("{StoreName}", args.get(STORE));
				LOGMSG = "Added store is not Present";
				//Validate added store is Present or not
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", Propertyvalue, false));
				//If need to Remove the Store Pass Value as True
				if (remove.equalsIgnoreCase("true")) {
					//Fetch the Property 
					String rstore = gei.getProperty("Text_RemoveStoreName");
					//Replace {StoreName} in XPath with Value passed in Excel sheet
					rstore = rstore.replace("{StoreName}", args.get(REMOVE_STORE));
					operations.bringElementintoView(driver, rstore);
					LOGMSG = "Remove Button is not Clickable in My Stores page";
					//Click Remove button
					operations.clickElement(driver, "xpath", rstore, false);
					Thread.sleep(500);
					LOGMSG = "Yes Button is not Clickable under Remove Store PopUp in My Stores page";
					//In Remove PopUp, Click Yes button
					operations.clickElement(driver, "xpath", "BtnRemoveYes", true);
					Thread.sleep(500);
					LOGMSG = "Store not removed";
					//Verify Store removed or not
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", rstore, false));
					Thread.sleep(500);
				}
			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to Add and Remove Stores in My account page", e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> MyVehicles
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate "My Vehicle"
	 * tab.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class MyVehicles extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			try {
				WebDriver driver = context.getWebDriver();
				LOGMSG = "My Vehicle tab not clickable";
				//Click MyVehicles
				operations.clickElement(driver, "xpath", "MyVehicleLink", true);
				//Validations under MyVehciles Tab
				LOGMSG = "Vehicle text is not present";
				operations.verifyElementIsDisplayed(driver, "xpath", "Text_MyVehicle", true);
				LOGMSG = "Vehicle Info not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_MyVehicleInfo", true));
				operations.verifyElementIsDisplayed(driver, "xpath", "Text_MyVehicleMileage", true);
				LOGMSG = "Mileage Info not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_MyVehicleMileageInfo", true));
				LOGMSG = "Recall Notice not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Text_MyVehicleRecall", true));

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG + e);
			}
		}

	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> Logout
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the Logged In
	 * user and Logout of the application
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>UserName :username (Eg : .........)</li>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class Logout extends Keyword {

		private static final String USERNAME = "UserName";

		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(USERNAME);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				System.out.println("entered logout");
				LOGMSG = "Username not Present";
				//Fetch the Property
				String Property = gei.getProperty("TextUserName");
				//Replace {UserName} in XPath with data Passed from Excel
				Property = Property.replace("{UserName}", args.get(USERNAME));
				//Validate UserName in Present or not
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", Property, false));
				LOGMSG = "My account button not clickable";
				//Click My Account button in header
				operations.clickElement(driver, "xpath", "BtnAccount", true);
				Thread.sleep(1000);
				LOGMSG = "Logout button not clickable";
				//Click LogOut button from DropDown
				operations.clickElement(driver, "xpath", "TextLogout", true);
			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG + e);
			}

		}

	}
	// #################### Keyword Ends ###################
	
	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateMyAccountDashBoard
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the "My Account DashBoard" for a logged In user 
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class ValidateMyAccountDashBoard extends Keyword {
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			try {
				// MyVehicle
				LOGMSG = "My vehicles is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextMyAccount_MyVehicle", true));
				LOGMSG = "My vehicles list is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "MyVehicle_List", true));
				// RecentOrder
				LOGMSG = "Recent orders is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextMyAccount_RecentOrders", true));	
				//Fetch the Property
				String getProperty = gei.getProperty("TextMyAccount_RecentOrders");
				System.out.println(getProperty);
				//Get WebElement
				WebElement element = operations.getWebElement(driver, "xpath", getProperty);
				//Scroll
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				operations.javaScriptScrollToViewElement(driver, element);
				jse.executeScript("window.scrollBy(0,-100)", element);
				/*If Orders Already Placed using Account, Recently Placed Order List will be Present
				 * Else Shop Online link will be Present*/
				try {					
					LOGMSG = "Recent Order History is not present";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Table_RecentOrder", true));
					LOGMSG = "Recent Order History link is not present";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "RecentOrdersLink", true));
					
				} catch (Exception e) {
					System.out.println("catched!!");
					LOGMSG = "Shop Online link is not present under Recent Orders";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "RecentOrderShopOnlineLink", true));
				}				
				// Serviceorder
				/*If Orders Already Placed using Account, Recently Placed Service Order List will be Present
				 * Else Shop Online link will be Present*/
				LOGMSG = "Service orders is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextMyAccount_ServiceOrders", true));
				try {			
					LOGMSG = "Service Order History is not present";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Table_ServiceOrder", true));
					LOGMSG = "Service Order History link is not present";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ServiceOrderLink", true));
				} catch (Exception e) {			
					System.out.println("catched!!");
					LOGMSG = "Shop Online link is not present under Service Orders";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ServiceOrderShopOnlineLink", true));
				}				
				System.out.println("validations completed");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException(LOGMSG + e);
			}

		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> MyOrdersValidation
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the "My
	 * Orders" page for a logged In user
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class MyOrdersValidation extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				LOGMSG = "My orders tab is not clickable";
				//Click My Orders
				operations.clickElement(driver, "xpath", "MyOrderLink", true);
				//Validate Orders Table
				LOGMSG = "Order Number text is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderNumber", true));
				LOGMSG = "Order Date text is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderDate", true));
				LOGMSG = "Order Amount text is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderAmount", true));
				LOGMSG = "Order Status text is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderStatus", true));
				LOGMSG = "Order Placed text is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderPlaced", true));
				LOGMSG = "Order Return text is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderReturn", true));
				LOGMSG = "Orders Items are not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "orderItems", true));
				LOGMSG = "Order number is not clickable";
				//Get the number of rows in Order Table and Randomly choose one Order Number
				//Fetch Property
				String OrderProp = gei.getProperty("OrderNumbers");
				//Get the Total count of Rows
				List<WebElement> OrderNumbersList = operations.getWebElements(driver, "xpath", OrderProp);
				System.out.println(OrderNumbersList.size());
				//Randomly choose a Order Number
				int OrderNumberIndex = operations.GenerateRandomNumer(driver, OrderNumbersList.size());
				System.out.println(OrderNumberIndex);
				//Fetch Property
				String OrderNumberProperty = gei.getProperty("RandomOrderNumber");
				//Replace {INDEX} in XPath with Randomly Chosen Index
				OrderNumberProperty = OrderNumberProperty.replace("{INDEX}", "" + OrderNumberIndex);				
				System.out.println(OrderNumberProperty);
				//Click on Randomly chosen Order Number
				operations.clickElement(driver, "xpath", OrderNumberProperty, false);
				//Validate Order Dialog PopUp Elements
				LOGMSG = "Ordered dialog popup is not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "OrderDialogPopup", true));
				LOGMSG = "Ordered items are not present";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyOrderPlacedItems", true));
				LOGMSG = "Order quantity text is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderQuantity", true));
				LOGMSG = "Order Item text is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderItem", true));
				LOGMSG = "Order UnitPrice text is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderUnitPrice", true));
				LOGMSG = "Order Total text is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderTotal", true));
				LOGMSG = "Order SubTotal text is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderSubtotal", true));
				LOGMSG = "Order State sales tax text is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderStateSales", true));
				LOGMSG = "Order Local sales tax text is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderLocalSales", true));
				LOGMSG = "Order Grandtotal text is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderGrandTotal", true));
				/*
				 * LOGMSG="Order Tender total text is not present in order info popup"
				 * ; softAssert.assertTrue(operations.verifyElementIsDisplayed(
				 * driver, "xpath","TextOrderTotalTender",true));
				 */
				LOGMSG = "Order Billing Information is not present in order info popup";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextBillingInfo", true));
				/*
				 * LOGMSG="Order Shipping Information is not present in order info popup"
				 * ; softAssert.assertTrue(operations.verifyElementIsDisplayed(
				 * driver, "xpath","TextShippingInfo",true));
				 */
				LOGMSG = "Print button is not present in order info popup";
				//Click Print Button
				operations.clickElement(driver, "xpath", "BtnPrint", true);
				Thread.sleep(2000);
				LOGMSG = "Invoice not in PDF Format";
				//Fetch Property of Downloads Path
				String FetchFilePath = gei.getProperty("DownloadedFilePath");
				//Call isFileDownloaded() to validate whether File is Download or not
				softAssert.assertTrue(operations.isFileDownloaded(FetchFilePath, "Invoice"));

			}

			catch (Exception e) {

				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> EditMyProfile
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to edit the Profile and
	 * Validate the "My Profile" page for a logged In user
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * 
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class EditMyProfile extends Keyword {

		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();

			try {
				LOGMSG = "My Profile tab is not clickable";
				//Click My Profile
				operations.clickElement(driver, "xpath", "MyProfileLink", true);	
				LOGMSG = "Log in details are not present under My Profile Page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextProfileLogin", true));				
				LOGMSG = "Edit button under PepBoys Profile is not present in My Profile Page";
				//Click Edit button under Log in
				operations.clickElement(driver, "xpath", "BtnProfileLoginEdit", true);
				Thread.sleep(1000);
				LOGMSG = "Edit Profile Popup is not present in My Profile Page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ProfileLoginPopup", true));
				//Click Close button under Login PopUp
				operations.clickElement(driver, "xpath", "BtnProfileClose", true);
				Thread.sleep(1000);
				LOGMSG = "Billing info details are not present under My Profile Page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextProfileBilling", true));
				LOGMSG = "Edit button is not present under Billing Section in My Profile Page";
				//Click Edit button under Billing Section
				operations.clickElement(driver, "xpath", "BtnProfileBillingEdit", true);
				Thread.sleep(1000);
				LOGMSG = "Billing info Popup is not present in My Profile Page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ProfileBillingPopup", true));
				//Click Close button under Billing PopUp
				operations.clickElement(driver, "xpath", "BtnProfileClose", true);
				Thread.sleep(1000);
				LOGMSG = "Shipping info details are not present under My Profile Page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextProfileShipping", true));
				LOGMSG = "Edit button is not present under Shipping Section in My Profile Page";
				//Click Edit button under Shipping Section
				operations.clickElement(driver, "xpath", "BtnProfileShippingEdit", true);
				Thread.sleep(1000);
				LOGMSG = "Shipping info Popup is not present in My Profile Page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ProfileShippingPopup", true));
				//Click Close button under Shipping PopUp
				operations.clickElement(driver, "xpath", "BtnProfileClose", true);
				Thread.sleep(1000);
				// Fetch the Property
				String Fetch = gei.getProperty("TextProfilePepBoys");
				//Get WebElement
				WebElement FetchBirthElement = operations.getWebElement(driver, "xpath", Fetch);
				LOGMSG = "PepBoys Profile is not present under My Profile Page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextProfilePepBoys", true));
				//Scroll to view
				operations.javaScriptScrollToViewElement(driver, FetchBirthElement);
				//Fetch Default Birth Data Present
				String DefaultBirthValue = operations.getText(driver, "xpath", "TextProfileBirthData", true);
				/*
				 * String DefaultGenderValue = operations.getText(driver,
				 * "xpath", "TextProfileGenderData", true); String
				 * DefaultLanguageValue = operations.getText(driver, "xpath",
				 * "TextProfileLanguageData", true); String DefaultServiceValue
				 * = operations.getText(driver, "xpath",
				 * "TextProfileServiceData", true);
				 */
				LOGMSG = "Edit button is not clickable under PepBoys Profile in My Profile Page";
				operations.clickElement(driver, "xpath", "BtnProfileEdit", true);

				// checks all the checkBoxes
				String CHECKBOX = gei.getProperty("checkBoxProfile");
				List<WebElement> CheckboxElements = operations.getWebElements(driver, "xpath", CHECKBOX);
				for (WebElement element : CheckboxElements) {
					if (!element.isSelected()) {
						element.click();
					}
				}

				// DROP DOWN verification
				// birthMonth Selection
				LOGMSG = "Unable to Select Birth Month Option in PepBoys Profile PopUp";
				//Fetch Property
				String BirthMonthValue = gei.getProperty("SelectBirthMonthWithOption");
				//Find the length of all option
				List<WebElement> BitrhMonth = operations.getWebElements(driver, "xpath", BirthMonthValue);
				//Randomly choose the option except having "Select Your Month"
				int BirthMonthIndex = 2 + (int) (Math.random() * (BitrhMonth.size() - 2));
				//ConCat Random Value to XPath
				BirthMonthValue = BirthMonthValue.concat("[" + BirthMonthIndex + "]");
				//Fetch the text of Randomly Chosen Option
				String BirthValue = operations.getText(driver, "xpath", BirthMonthValue, false);
				//Select the birth Month
				operations.SelectComboBoxByVisibleText(driver, "xpath", "SelectBirthMonth", BirthValue, true);

				// Gender selection
				//Fetch Property
				LOGMSG = "Unable to Select Gender Option in PepBoys Profile PopUp";
				String GenderOption = gei.getProperty("SelectGenderWithOption");
				//Find the length of all option
				List<WebElement> gender = operations.getWebElements(driver, "xpath", GenderOption);
				//Randomly choose the option except having "Select Your Gender"
				int GenderIndex = 2 + (int) (Math.random() * (gender.size() - 2));
				//ConCat Random Value to XPath
				GenderOption = GenderOption.concat("[" + GenderIndex + "]");
				//Fetch the text of Randomly Chosen Option
				String GenderValue = operations.getText(driver, "xpath", GenderOption, false);
				//Select the Gender
				operations.SelectComboBoxByVisibleText(driver, "xpath", "SelectGender", GenderValue, true);

				// Language selection
				//Fetch Property
				LOGMSG = "Unable to Select Language Option in PepBoys Profile PopUp";
				String LanguageOption = gei.getProperty("SelectLanguageWithOption");
				//Find the length of all option
				List<WebElement> language = operations.getWebElements(driver, "xpath", LanguageOption);
				//Randomly choose the option except having "Select Your Language"
				int LangugageIndex = 2 + (int) (Math.random() * (language.size() - 2));
				//ConCat Random Value to XPath
				LanguageOption = LanguageOption.concat("[" + LangugageIndex + "]");
				//Fetch the text of Randomly Chosen Option
				String LanguageValue = operations.getText(driver, "xpath", LanguageOption, false);
				//Select the Language
				operations.SelectComboBoxByVisibleText(driver, "xpath", "SelectLanguage", LanguageValue, true);

				// service selection
				//Fetch Property
				LOGMSG = "Unable to Select Service Option in PepBoys Profile PopUp";
				String ServiceOption = gei.getProperty("SelectServiceWithOption");
				//Find the length of all option
				List<WebElement> service = operations.getWebElements(driver, "xpath", ServiceOption);
				//Randomly choose the option except having "Select Your Service"
				int ServiceIndex = 2 + (int) (Math.random() * (service.size() - 2));
				//ConCat Random Value to XPath
				ServiceOption = ServiceOption.concat("[" + ServiceIndex + "]");
				//Fetch the text of Randomly Chosen Option
				String ServiceValue = operations.getText(driver, "xpath", ServiceOption, false);
				//Select the Service
				operations.SelectComboBoxByVisibleText(driver, "xpath", "SelectService", ServiceValue, true);
				
				LOGMSG = "Unable to Click Submit button in PepBoys Profile PopUp";
				//Click Submit button
				operations.clickElement(driver, "xpath", "BtnProfileSubmit", true);

				//Fetch the Birth Month Value After Updating
				String UpdatedBirth = operations.getText(driver, "xpath", "TextProfileBirthData", true);
				String UpdatedGender = operations.getText(driver, "xpath", "TextProfileGenderData", true);
				String UpdatedLanguage = operations.getText(driver, "xpath", "TextProfileLanguageData", true);
				String UpdatedService = operations.getText(driver, "xpath", "TextProfileServiceData", true);

				//Compare Before and After Editing Birth
				//Validate Whether Birth Data is Updated or Not
				if (!(UpdatedBirth.equalsIgnoreCase(DefaultBirthValue))) {
					LOGMSG = "Profile Info has not been Updated";
				}
				/*
				 * if (!(UpdatedGender.equalsIgnoreCase(DefaultGenderValue))) {
				 * LOGMSG="Gender Value is not updated"; } if
				 * (!(UpdatedLanguage.equalsIgnoreCase(DefaultLanguageValue))) {
				 * LOGMSG="Language Value is not updated"; } if
				 * (!(UpdatedService.equalsIgnoreCase(DefaultServiceValue))) {
				 * LOGMSG="Service Value is not updated"; }
				 */

				/*
				 * WebElement element = operations.getWebElement(driver,
				 * "xpath", "TextProfileLogin");
				 * operations.javaScriptScrollToViewElement(driver,element);
				 */

				// Broken links
				/*String temp;
				String Prop = props.getElementProperty("ProfileLinks");
				List<WebElement> AllLinks = operations.getWebElements(driver, "xpath", Prop);
				for (WebElement ele : AllLinks) {
					if (ele.getAttribute("href") != null) {
						// temp = ele.getAttribute("href");
						// System.out.println(temp);
						int responseCode = operations.isLinkBroken(new URL(ele.getAttribute("href")));
						System.out.println(responseCode);

					}
				}*/

			}

			catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}

	}
	// #################### Keyword Ends ###################

	
	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> MyVehicleAddRemove
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to add a new vehicle or remove a previously added vehicle for logged in user. 
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * 
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>YMMEName (Mandatory): Given the name of dropdown's in comma separated
	 * values (Eg : Year,Make,Model,Engine,Drivetrain,Trim etc..)</li>
	 * <li>YMMEValue (Mandatory): Given the value of dropdown's in comma
	 * separated values (Eg :2011,HONDA,ACCORD,4-2354 2.4L DOHC,4WD/AWD,EX-L V6
	 * COUPE etc..)</li>
	 * <li>IsRemove(Mandatory) : boolean value to ensure the removing of vehicle (Eg :true,false)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class MyVehicleAddRemove extends Keyword {

		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";
		private static final String REMOVE = "IsRemove";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(YMMENAME, YMMEVALUE, REMOVE);

		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();

			try {
				// LOGMSG="My Vehicle tab is not Clickable";
				// operations.clickElement(driver, "xpath", "MyVehicleLink",
				// true);
				int VehicleAddedIndex = 1;
				//Fetch the Property
				String VehiclePath = gei.getProperty("VehiclesInfo");
				List<WebElement> NoOfVehicle;
				//Get the Count of Total number of Vehicles in My Vehicle Page
				NoOfVehicle = operations.getWebElements(driver, "xpath", VehiclePath);
				int VehicleSize = NoOfVehicle.size();
				LOGMSG = "Unable to click Add Vehicle button in My Vehicle Page";
				//Click Add Vehicle button
				operations.clickElement(driver, "xpath", "BtnAddVehicle", true);
				Thread.sleep(2000);
				//Adding Vehicle
				LOGMSG = "Unable to choose YMME in My Vehicle Page";
				//Call ChooseVehicleAndClick Keyword
				Keyword.runK(CUR_APP, "ChooseVehicleAndClick", YMMENAME, args.get(YMMENAME), YMMEVALUE,args.get(YMMEVALUE));
				System.out.println(VehicleSize);
				Thread.sleep(2000);
				LOGMSG = "Added Vehicle is not Present in My Vehicle Page";
				//Fetching Added Vehicle index to Remove
				//Looping all The Vehicle List to Know whether Vehicle is Successfully added or not
				for (int index = 1; index <= VehicleSize; index++) {
					//Fetch Property
					String fetchProp = gei.getProperty("VehiclesIndexedInfo");
					//Replace {INDEX} with index of the loop
					fetchProp = fetchProp.replace("{INDEX}", "" + index + "");
					System.out.println(fetchProp);
					//Get the Count of Vehicle Specifications[EX: 2010 Honda Escape 4-2488 2.5L DOHC] under each row 
					List<WebElement> getVehicles = operations.getWebElements(driver, "xpath", fetchProp);
					System.out.println("element size " + getVehicles.size());
					//Loop all
					for (WebElement element : getVehicles) {
						System.out.println(element.toString());
						String details = element.getAttribute("innerText");
						System.out.println(details);
						//Compare the data in Excel with Data Present in Table
						if ((args.get(YMMEVALUE).contains(details))) {
							//Mark the index in VehicleAddedIndex to remove
							VehicleAddedIndex = index;
							System.out.println("added vehicle is present");
						} 
						else
						{
							//If Does not matches break it and move to next Vehicle
							break;
						}
					}
				}

				//To remove the Vehicle, REMOVE should be TRUE
				if (args.get(REMOVE).equalsIgnoreCase("True")) {
					System.out.println("entering remove");
					//Fetch Property
					String fetchProperty = gei.getProperty("VehicleRemoveLink");
					//Replace {INDEX} with Added Vehicle index named VehicleAddedIndex
					fetchProperty = fetchProperty.replace("{INDEX}", "" + VehicleAddedIndex + "");
					LOGMSG = "Unable to Click remove button in My Vehicle Page";
					operations.clickElement(driver, "xpath", fetchProperty, false);
					LOGMSG = "Unable to Click Yes in Remove PopUp under My Vehicle Page";
					operations.clickElement(driver, "xpath", "BtnRemoveYes", true);
					Thread.sleep(2000);
					//Get the Count of Total Number of Vehicle Present after Removal
					VehiclePath = gei.getProperty("VehiclesInfo");
					NoOfVehicle = operations.getWebElements(driver, "xpath", VehiclePath);
					VehicleSize = NoOfVehicle.size();
					System.out.println(VehicleSize);
					LOGMSG = "Vehicle not Removed Properly in My Vehicle Page";
					//Looping all The Vehicle List to Know whether Vehicle is Removed Successfully or not
					for (int index = 1; index <= VehicleSize; index++) {
						//Fetch Property
						String fetchProp = gei.getProperty("VehiclesIndexedInfo");
						//Replace {INDEX} with index of the loop
						fetchProp = fetchProp.replace("{INDEX}", "" + index + "");
						System.out.println(fetchProp);
						//Get the Count of Vehicle Specifications[EX: 2010 Honda Escape 4-2488 2.5L DOHC] under each row 
						List<WebElement> getVehiclesAfterAdding = operations.getWebElements(driver, "xpath", fetchProp);
						System.out.println("element size " + getVehiclesAfterAdding.size());
						//Loop all
						for (int count = 0; count < getVehiclesAfterAdding.size(); count++) {
							//Fetch the text of Vehicle Specification
							String details = getVehiclesAfterAdding.get(count).getAttribute("innerText");
							System.out.println(details);
							//If does no match break it
							if (!(args.get(YMMEVALUE).contains(details))) {
								break;
							} 
							/*check all the specifications are matched*/							
							else {
								if (count == (getVehiclesAfterAdding.size() - 1)) {
									System.out.println("added vehicle is removed");
								}
								continue;
							}
						}
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}

	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateMyAccountTabs
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate all the tabs
	 * under "My Accounts" Page for logged In user.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class ValidateMyAccountTabs extends Keyword {
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			try {
				//Fetch the Property
				String FetchTabProperty = gei.getProperty("MyAccountTabLinks");
				//Get the number of Tab count in My account page
				List<WebElement> TabElements = operations.getWebElements(driver, "xpath", FetchTabProperty);
				int NumberOfTab = TabElements.size();
				//Fetch the Property
				String FetchLinksProperty = gei.getProperty("MyAccountLinks");
				System.out.println(FetchLinksProperty);
				LOGMSG = "Tabs are not clickable";
				//Loop all the Tabs
				for (int tabCount = 1; tabCount <= NumberOfTab; tabCount++) {
					List<WebElement> FetchTabLinksinMyaccount = new ArrayList<WebElement>();
					//Fetch the Property
					FetchTabProperty = gei.getProperty("MyAccountTabIndexedLinks");
					//Replace {INDEX} with index of the loop
					FetchTabProperty = FetchTabProperty.replace("{INDEX}", "" + tabCount);
					System.out.println(FetchTabProperty);
					operations.clickElement(driver, "xpath", FetchTabProperty, false);
					Thread.sleep(1000);
					/*try {
						System.out.println("TabCount " + tabCount);
						LOGMSG = "Unable to Fetch Links in My account page";
						//Fetch the property
						FetchLinksProperty = props.getElementProperty("MyAccountLinks");
						//Fetch all links and store in FetchLinksProperty List
						FetchTabLinksinMyaccount = operations.getWebElements(driver, "xpath", FetchLinksProperty);
						for (WebElement element : FetchTabLinksinMyaccount) {
							//If Webelement's has Attribute "href" not null
							if (element.getAttribute("href") != null) {
								System.out.println(element.getAttribute("href"));
								//Check link is broken or not
								int responseCode = operations.isLinkBroken(new URL(element.getAttribute("href")));
								System.out.println(responseCode);
							}
						}
					} catch (Exception e) {
						//If no links are available, it is Catches!
						System.out.println("No links are Available");
						System.out.println(e.toString());
					}*/
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException(LOGMSG + e);
			}

		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> MyServiceAppointment
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to add a new vehicle or
	 * remove a previously added vehicle for logged in user.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>ServiceVehicle : vehicle Name(Eg:2010 HONDA ACCORD)</li>
	 * <li>Date : date of appointment(Eg:02/10/2018 )</li>
	 * <li>Mileage :mileage of the vehicle(Eg:200)</li>
	 * <li>ServiceProvider :service provider store(Eg:Philadelphia, PA (Store
	 * #07955)</li>
	 * <li>TotalCost :cost of the vehicle(Eg:200</li>
	 * <li>Service :(Eg:AC / Heating System )</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class MyServiceAppointment extends Keyword {

		private static final String SERVICEVEHICLE = "ServiceVehicle";
		private static final String DATE = "Date";
		private static final String MILEAGE = "Mileage";
		private static final String SERVPROVIDER = "ServiceProvider";
		private static final String TOTALCOST = "TotalCost";
		private static final String SERVICE = "Service";

		public void init() throws KDTKeywordInitException {
			super.init();
		}

		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			try {

				LOGMSG = "My Service Appointments tab is not clickable";
				//Click My Service Appointments
				operations.clickElement(driver, "xpath", "MyServiceLink", true);
				Thread.sleep(1000);
				System.out.println("clicked tab");
				LOGMSG = "Unable to select vehicle in My Service Appointments Page";
				//Select one vehicle from available list
				operations.SelectComboBoxByVisibleText(driver, "xpath", "SelectServiceVehicleToView",args.get(SERVICEVEHICLE), true);
				Thread.sleep(1000);
				//Fetch the Property
				String FetchTableProp = gei.getProperty("ServiceVehicle");
				//Get the Count of all Selected Service Vehicle in Table
				List<WebElement> ServiceTableCount = operations.getWebElements(driver, "xpath", FetchTableProp);
				System.out.println(ServiceTableCount.size());
				//Loop all
				//Check Whether Chosen Vehicle name is same as list of Vehicle Displayed
				for (int index = 1; index <= ServiceTableCount.size(); index++) {
					//Fetch the Property
					String FetchVehicle = gei.getProperty("TextServiceVehicle");
					//Replace {INDEX} with loop index
					FetchVehicle = FetchVehicle.replace("{INDEX}", "" + index);
					System.out.println(FetchVehicle);					
					//Get Text of Vehicle
					String DisplayedVehicle = operations.getText(driver, "xpath", FetchVehicle, false);
					//Compare
					if (!(args.get(SERVICEVEHICLE).equalsIgnoreCase(DisplayedVehicle))) {
						System.out.println("Unmatched Vehicle in Row " + index);
					} else {
						System.out.println("Matched" + args.get(SERVICEVEHICLE));
					}
					System.out.println("vehicle validated");
				}

				LOGMSG = "Unable to Click Add Service History Button";
				//Click Add Service History Button
				operations.clickElement(driver, "xpath", "BtnAddServiceHistory", true);
				Thread.sleep(1000);
				//Update the data in Add Service History PopUp
				LOGMSG = "Unable to Select vehicle in  Add Service History PopUp";
				operations.SelectComboBoxByVisibleText(driver, "xpath", "SelectServiceVehicle",	args.get(SERVICEVEHICLE), true);
				LOGMSG = "Unable to Enter Date in  Add Service History PopUp";
				operations.enterText(driver, "xpath", "TextBoxServiceDate", args.get(DATE));
				LOGMSG = "Unable to Enter Mileage in  Add Service History PopUp";
				operations.enterText(driver, "xpath", "TextBoxServiceMileage", args.get(MILEAGE));
				LOGMSG = "Unable to Enter Service Provider in  Add Service History PopUp";
				operations.enterText(driver, "xpath", "TextBoxServiceVendpor", args.get(SERVPROVIDER));
				LOGMSG = "Unable to Enter Total Cost in  Add Service History PopUp";
				operations.enterText(driver, "xpath", "TextBoxServiceCost", args.get(TOTALCOST));
				LOGMSG = "Unable to Select ServiceType in  Add Service History PopUp";
				String FetchService = gei.getProperty("SelectServiceType");
				FetchService = FetchService.replace("{SERVICE}", args.get(SERVICE));
				WebElement element = operations.getWebElement(driver, "xpath", FetchService);
				//Scroll
				JavascriptExecutor ex = (JavascriptExecutor) driver;
				ex.executeScript("arguments[0].scrollIntoView(true)", element);
				ex.executeScript("window.scrollBy(0,-150)", element);
				//Click Service to be Performed
				operations.clickElement(driver, "xpath", FetchService, false);
				LOGMSG = "Unable to Click Save in Add Service History PopUp";
				//Click Save button
				operations.clickElement(driver, "xpath", "BtnServiceAddHistorySave", true);
				Thread.sleep(2000);
				LOGMSG = "Unable to Click Print Service History Button";
				//Click Print button
				operations.clickElement(driver, "xpath", "BtnPrintServiceHistory", true);
				Thread.sleep(2000);
				LOGMSG = "File not downloaded";
				//Get the File Download path
				String FetchFilePath = gei.getProperty("DownloadedFilePath");
				//Call isFileDownloaded() to make sure whether file Downloaded in PDF or not
				softAssert.assertTrue(operations.isFileDownloaded(FetchFilePath, "AppointmentHistory.pdf"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException(LOGMSG + e);
			}

		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChoosePreSelectedVehicle
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to fill the YMME pop-up
	 * by randomly choosing the pre-selected vehicle from the List.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>Login</li>
	 * <li>NavigateToChooseStore</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class ChoosePreSelectedVehicle extends Keyword {
		  @Override
		  
		  public void exec() throws KDTKeywordExecException {
		   try {
		    WebDriver driver = context.getWebDriver();
		    Thread.sleep(10000); 
		    List<WebElement> lst = operations.getWebElements(driver, "xpath",  gei.getProperty("radio_Btn_preselectedVehiclecount"));
		    int size = lst.size();
		    System.out.println("Size of the list is:"+size);
		    int randomindex = 0;
		    for(int i=0 ; i<size ; i++)
		    {
			    randomindex = operations.GenerateRandomNumer(driver, size);
			    Thread.sleep(5000);
			    String eleIdentifierTireSize = gei.getProperty("radio_Btn_preselectedVehicle");
			    eleIdentifierTireSize = eleIdentifierTireSize.replaceAll("\\{Index\\}", "" + randomindex + "");
			    System.out.println("Element Identifier :"+eleIdentifierTireSize);
			    Thread.sleep(5000);
			    operations.clickElement(driver, "xpath", eleIdentifierTireSize, false);
			    Thread.sleep(5000);
			    WebElement Nextbutton = operations.getWebElement(driver, "xpath", gei.getProperty("Btn_SelVehicleProceed1"));
			    if(Nextbutton.isEnabled())
			    {
			    operations.clickElement(driver, "xpath", "Btn_SelVehicleProceed1", true);
			    Thread.sleep(5000);
			    break;
			    }
			    else
			    {
			    WebElement wb =driver.findElement(By.xpath("//select[contains(@class,'drivetrainSelector')]")); 
			    Select sel = new Select(wb);
			    sel.selectByIndex(2);
			     WebElement makeAppointment = operations.getWebElement(driver, "xpath", gei.getProperty("btn_makeappointTbg"));
			     try{
			    	 JavascriptExecutor js = (JavascriptExecutor) driver;
			    	 js.executeScript("window.scrollBy(0,-150)");
			    	 operations.javaScriptScrollToViewElement(driver, makeAppointment);
			     	
			     }catch(Exception e){}
			     if(makeAppointment.isDisplayed())
			     {
			      System.out.println("make Appointment is displayed");
			     }
			     else
			     {
			    	 Thread.sleep(10000);
			    	 WebElement wb1 =driver.findElement(By.xpath("//select[contains(@class,'trimSelector')]")); 
					    Select sel1 = new Select(wb1);
					    try{
					    sel1.selectByIndex(2);
					    operations.clickElement(driver, "xpath", "Btn_SelVehicleProceed1", true);
					    break;}
					    catch(Exception e){
					    	continue;
					    }
			     }
		    }
		    }
		    System.out.println("clicked on next");

		   } catch (Exception e) {
		    System.out.println("No preselected vehicleavailable");
		    throw new KDTKeywordExecException(LOGMSG + e);
		   }
		  }
		 }
	
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateWheelPRP
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the Wheel PRP
	 * page.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>NavigateToChooseStore</li>
	 * <li>ChooseStore</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>CarColor : colour of the car(Eg:Pure White)</li>
	 * <li>WheelFilterValues : Filter Values of the wheels(Eg:7.5,18,Black,Fk
	 * Ethos,221.0|252.99 )</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */
	
	public class ValidateWheelPRP extends Keyword {

		private static final String FILTERARGS = "WheelFilterArgs";
		int RandomWheelIndex;
		String Property;

		public String FetchProperty(String PropertyName, String ActualString, String ReplaceString)
				throws KDTKeywordExecException {
			String prop = gei.getProperty(PropertyName);
			prop = prop.replace(ActualString, ReplaceString);
			System.out.println(prop);
			return prop;
		}

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(FILTERARGS);

		}
		
		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();

				// Color drop down is presented in with choice of color and should update image as color is changed.
			
				try{
					LOGMSG = "Unable to click Color Dropdown in Wheels PRP";
					//Validate Color DropDown
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "SelectWheel", true));
					Thread.sleep(5000);
					LOGMSG = "Unable to Choose Color in Wheels PRP";
					//Choose Color Passed from Excel
					operations.clickElement(driver, "xpath", "SelectWheel", true);
					//Fetch Property
					Property = FetchProperty("SelectCarColor", "[{INDEX}]", "");
					System.out.println("Props.. " + Property);
					//Get the List of Colors and Choose one Color Randomly
					List<WebElement> CarColor;
					CarColor = operations.getWebElements(driver, "xpath", Property);
					//Generate Random Number
					int RandomColor = operations.GenerateRandomNumer(driver, CarColor.size());	
					//Fetch Property and Replace {INDEX} in XPath with Random Value 
					Property = FetchProperty("SelectCarColor", "{INDEX}", ""+RandomColor);
					//Fetch Image Src To Compare
					String ColorSrc = operations.GetElementAttribute(driver, "xpath", Property, "data-imageurl", false);
					System.out.println(ColorSrc);
					//Click Specified  Color
					operations.clickElement(driver, "xpath", Property, false);
					Thread.sleep(3000);
					String UpdatedColorSrc;
					LOGMSG = "Unable to see the Updated Car Color in Wheels PRP";
					//Fetch the Color of Main Image
					String FetchUpdatedColorPRoperty = gei.getProperty("ImageChangedCarColor");
					List<WebElement> UpdatedColorImageElements = operations.getWebElements(driver, "xpath",	FetchUpdatedColorPRoperty);
					//Loop All WebElements and Compare Whether Color Has been Updated in Main Image or not
					for (WebElement Element : UpdatedColorImageElements) {
						//Fetch Color from Main Image
						UpdatedColorSrc = Element.getAttribute("src");
						//Compare Source of Colors
						if (ColorSrc.equals(UpdatedColorSrc)) {
							System.out.println("Updated CarColor");
							break;
						}
					}
				}
				catch (Exception e) {
					LOGMSG="";
				}
				

				// *************************************************************************************************************
				// Customer should be able to see the selected car in the selected color with a super-imposed image of the first
				// available wheel.		
				
				LOGMSG = "Unable to see the First avialable wheel with super imposed image in Wheels PRP";
				//Fetch the First Wheel ID
				String WheelID = operations.GetElementAttribute(driver, "xpath", "ImageFirstWheel", "data-vendorid",true);
				System.out.println("Wheel id" + WheelID);
				String UpdatedWheelSrc;
				//Fetch Image Wheels of Main Image
				String FetchImageWheelPooperty = gei.getProperty("ImageWheel");
				List<WebElement> WheelElements = operations.getWebElements(driver, "xpath", FetchImageWheelPooperty);
				int count = 0;
				//Loop all Wheel image and Compare Whether Main Image has First Wheel
				for (WebElement Element : WheelElements) {
					//Fetch Src - Wheel of Main Image
					UpdatedWheelSrc = Element.getAttribute("src");
					//Check Whether Source Contains ID of First Wheel
					if (UpdatedWheelSrc.contains(WheelID)) {
						System.out.println("Updated Wheel image");
						count++;
						//Count is 2 - Because both Real and Front Wheel of Main Image shoul be updated.
						if (count == 2) {
							System.out.println("both front and rear wheels images are updated");
							break;
						}
					}
				}

				// ******************************************************************************************************************
				// Verify customer is presented with multiple wheel options for the car.

				LOGMSG = "Unable to Verify Multiple Wheels Options for the car";
				//Fetch the Count of Visible Wheels
				String FetchWheelItems = gei.getProperty("VerifyWheelItems");
				List<WebElement> WheelItems = operations.getWebElements(driver, "xpath", FetchWheelItems);
				System.out.println(FetchWheelItems);
				System.out.println("wheel size" + WheelItems.size());
				//Loop all the Visible Wheels
				for (WebElement Element:WheelItems) {
					//Fetch Property and Replace {ID} in XPath with Looping Element's ID 
					Property = FetchProperty("ImageWheelOptions", "{ID}", Element.getAttribute("id"));
					System.out.println("Wheel Image " + Property);
					//Validate Wheels are Displayed
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", Property, false));
				}

				// ******************************************************************************************************************
				// Verify the wheel options are displaying up to 3 wheels

				LOGMSG = "Unable to Verify Wheels Options for the car";
				//Loop All Visible Wheels
				for (WebElement Element:WheelItems) {
					//Fetch the Property and Replace {ID} in XPath with Looping Element's ID
					Property = FetchProperty("VerifyWheel", "{ID}", Element.getAttribute("id"));
					System.out.println("Fetch Wheel" + Property);
					//Verify Wheels options are Displayed
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", Property, false));
				}

				// ******************************************************************************************************************

				// The customer should be presented with the following
				// information for the different wheel options:
				// o Price
				// o Price for 4
				// o Store availability
				// o Width and size
				// o Tire sizes that fit that particular wheel
				// o A view details button

				//Validating Wheels Specifications
				LOGMSG = "Per Wheel Cost is not Present in Wheels PRP";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextPerWheelCost", true));
				LOGMSG = "Four Wheel Cost is not Present in Wheels PRP";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextFourWheelCost", true));
				LOGMSG = "Store Availability is not Present in Wheels PRP";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextStoreAvailability", true));
				LOGMSG = "Wheel Size is not Present in Wheels PRP";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextWheelSize", true));
				LOGMSG = "Tire Size is not Present in Wheels PRP";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextWheelTiresize", true));
				LOGMSG = "See Details button is not Present in Wheels PRP";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "BtnWheelSeeDetails", true));

				// ******************************************************************************************************************

				// Pagination

				LOGMSG = "Pagination arrows are not working in Wheels PRP";
				//Fetching the Ending Pagination Count of Current Page
				String GetWheelCount = operations.getText(driver, "xpath", "TextWheelsPagination", true);
				String GetWheelsCountinPage = GetWheelCount.substring(GetWheelCount.indexOf("- ") + ("- ").length(),GetWheelCount.indexOf(" of"));
				System.out.println(GetWheelsCountinPage);
				//Convert to Integer
				int TotalWheelinCurrentPage = Integer.parseInt(GetWheelsCountinPage);
				/* If More than 3 Wheels are Present for Specified Vehicle - Pagination will be Enabled
				 * Else No Pagination*/
				if (TotalWheelinCurrentPage == 3) {
					LOGMSG = "Right Pagination arrows are not Clickable in Wheels PRP";
					//Click Right arrow
					operations.clickElement(driver, "xpath", "WheelsPaginationRightLink", true);
					//Get Starting Pagination Count of Current Page
					String UpdatedWheelCount = operations.getText(driver, "xpath", "TextWheelsPagination", true);
					String UpdatedPage = UpdatedWheelCount.substring(UpdatedWheelCount.indexOf("Displaying ") + ("Displaying ").length(),UpdatedWheelCount.indexOf(" -"));
					//Convert to Integer
					int UpdatedCount = Integer.parseInt(UpdatedPage);
					//Compare Count is Properly Updated
					if (UpdatedCount == TotalWheelinCurrentPage + 1) {
						LOGMSG = "Left Pagination arrows are not Clickable in Wheels PRP";
						//Click Left Pagination to move back to Previous Page
						operations.clickElement(driver, "xpath", "WheelsPaginationRightLink", true);
						System.out.println("Pagination Works fine in Wheels Page");
					}
				}

				// ******************************************************************************************************************
				
				//Verify wheels result is displaying as distinct results for multiple sizes for customers application
				
				System.out.println("starting distinct result for multiple sizes....");				
				LOGMSG = "Unable to fetch the Total number of Wheels in Wheels PRP";
				//Get the Total Number of Wheels for Chosen Vehicle
				GetWheelCount = operations.getText(driver, "xpath", "TextWheelsPagination", true);
				GetWheelsCountinPage = GetWheelCount.substring(GetWheelCount.indexOf("of ") + ("of ").length(),GetWheelCount.length());
				System.out.println(GetWheelsCountinPage);
				//Convert to Integer
				int TotalNoOfWheels = Integer.parseInt(GetWheelsCountinPage);
				System.out.println("Total Wheels in number"+TotalNoOfWheels);
				//Make Initial Loop Break Value as True
				boolean breakLoop = true;
				boolean paginationClick = true;
				String WheelSize="";
				int PaginationCount =0;
				
				LOGMSG = "Distinct Results for multiple sizes are not present in Wheels PRP";
				for(;breakLoop;)
				{
					//Fetch the Visible Wheels
					String FetchWheelItemsTotal = gei.getProperty("VerifyWheelItems");
					List<WebElement> TotalWheels = operations.getWebElements(driver, "xpath", FetchWheelItemsTotal);
					System.out.println("wheel size --->" + TotalWheels.size());
					//if Wheels are present validate and compare size
					//else break the outer loop
					if(TotalWheels.size()>0)
					{
						//Loop all the Wheels
						for(WebElement element:TotalWheels)
						{
							//System.out.println(element.findElement(By.xpath("//div[@class='wheelSizeLabel']")).getText());
							//Fetch the Property and Replace {ID} in XPath with WebElement's attribute ID
							String WheelProp = FetchProperty("TextWheelItemsSize", "{ID}", element.getAttribute("id"));
							//Fetch the Wheel Size
							String GetSizeText = operations.getText(driver, "xpath", WheelProp, false);
							//Check for distinct sizes
							//If Sizes are Different  Break the Inner and Outer For Loop
							if(!WheelSize.equals(GetSizeText) && (!WheelSize.isEmpty())){
								System.out.println("Distinct results are present for multiple sizes");
								breakLoop= false;
								break;
							}
							//Initially WheelsSize is Empty
							//Store the Wheel's Size in WheelSize
							else
							{
								WheelSize = GetSizeText;
								System.out.println(WheelSize);
							}
							LOGMSG = "Unable to fetch Ending Pagination Count of Current Page";
							//Fetch the Ending Pagination Count of Current Page
							GetWheelCount = operations.getText(driver, "xpath", "TextWheelsPagination", true);
							GetWheelsCountinPage = GetWheelCount.substring(GetWheelCount.indexOf("- ") + ("- ").length(),GetWheelCount.indexOf(" of"));
							System.out.println(GetWheelsCountinPage);
							//Convert to Integer
							int NoOfWheelsinCurrentPage = Integer.parseInt(GetWheelsCountinPage);
							/*EX: If Total Number of Wheels is less than 3, Pagination Arrows will not be Present
							 * And We need to Break the Outer Loop */
							if(TotalNoOfWheels==NoOfWheelsinCurrentPage)
							{
								//break outer loop
								breakLoop= false;
								//do not pagination - which will not be available
								paginationClick = false;
							}
						}
						if(paginationClick)
						{
							LOGMSG = "Unable to Click Right Pagination Arrow in Wheels PRP";
							operations.clickElement(driver, "xpath", "WheelsPaginationrightExact", true);
							System.out.println("Clicking Paginations..");
							PaginationCount++;
						}
					}
					//No Wheels are Present, Break the Outer Loop
					else
					{
						breakLoop= false;
					}
					
				}
				//To Navigate Back to Page 1 of Wheels PRP
				for(;PaginationCount>0;PaginationCount--)
				{
					LOGMSG = "Unable to Click Left Pagination Arrow in Wheels PRP";
					operations.clickElement(driver, "xpath", "WheelsPaginationleftExact", true);
				}
				
				// ******************************************************************************************************************

				// Verify customer can navigate the product details page by
				// clicking on product name link
				
				WheelItems.clear();
				System.out.println("Fetch wheel items..-->"+FetchWheelItems);
				Thread.sleep(1000);
				LOGMSG = "Unable to Navigate to PDP from Wheels PRP";
				//Fetch Visible Wheels
				WheelItems = operations.getWebElements(driver, "xpath", FetchWheelItems);
				//Randomly choose a Wheel
				RandomWheelIndex = operations.GenerateRandomNumer(driver, WheelItems.size());
				WebElement elementName = WheelItems.get(RandomWheelIndex-1);		
				//Fetch the ID of Randomly chosen Wheel
				String AttrIDValue = elementName.getAttribute("id");
				System.out.println("attr value "+AttrIDValue);
				//Fetch the Property and Replace {ID} i XPath with Randomly Chosen Wheel ID
				Property = FetchProperty("TextWheelName", "{ID}", AttrIDValue);
				//Fetch the Wheel name of Randomly chosen Wheel to Validate
				String WheelNameinPRP = operations.getText(driver, "xpath", Property, false);
				//Fetch the Property and Replace {ID} in XPath with Randomly Chosen Wheel ID
				Property = FetchProperty("BtnIndexedWheelSeeDetails", "{ID}",AttrIDValue);
				LOGMSG = "Unable to Click See Details button in Wheels PRP";
				//Click See Details Button
				operations.clickElement(driver, "xpath", Property, false);
				//Wait for Page to Load
				operations.WaitForPageload(driver);
				// operations.closeAd(driver,"BtnAdClose");
				LOGMSG = "Not Navigated to PDP from Wheels PRP";
				//Get the Product Name in PDP
				String WheelNameinPDP = operations.getText(driver, "xpath", "Text_PDPPname", true);
				//Compare the Product Name in PRP and PDP to Make Sure Whether Navigated to Correct PDP
				if (!(WheelNameinPDP.equals(WheelNameinPRP))) {
					System.out.println("Not Navigated to Wheels Product page");
				}
				//Navigate back to Wheels PRP
				driver.navigate().back();
				//Wait for Page to Load
				operations.WaitForPageload(driver);
				// operations.closeAd(driver,"BtnAdClose");

				// ******************************************************************************************************************

				// The customer should be able to see any available offers for a
				// particular wheel by clicking on the offer icon in the upper
				// left hand corner of the wheel image.

				try
				{
					LOGMSG = "Unable to Click Wheel Offer icon in Wheels PRP";
					//Fetch the Property and Replace {ID} in XPath with Randomly Chosen Wheel ID
					Property = FetchProperty("BtnWheelOffer", "{ID}", AttrIDValue);
					//Click Offer Icon
					operations.clickElement(driver, "xpath", Property, false);
					LOGMSG = "Unable to fetch Offer Tooltip content in Wheels PRP";
					//Fetch the Property and Replace {ID} in XPath with Randomly Chosen Wheel ID
					Property = FetchProperty("TextWheelMsg", "{ID}", AttrIDValue);
					//Validate Offer Message is Displayed
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", Property, false));
				}
				catch (Exception e) {
					System.out.println(LOGMSG);
				}

				// ******************************************************************************************************************

				// The customer should be able to use the following filters to
				// dynamically filter down wheels results:
				// o Width
				// o Diameter
				// o Finish
				// o Brand
				// o Price
				
				int FilterCount=0;
				String StoreRandom="";
				boolean FetchRandomAgainFlag = true;
				//Fetch Filter Values
				String WheelFilterValues = args.get(FILTERARGS);
				//Split Comma Separated Values
				String arr_Values[] = WheelFilterValues.split(",");
				int NoOfFilters = arr_Values.length;
				//Loop all the Filters and Click CheckBox
				for (int index = 0; index < NoOfFilters; index++) {
					//Fetch the Property and Replace {FILTER} in XPath with Looping Filter Values
					Property = FetchProperty("CheckboxWheelFilter", "{FILTER}", arr_Values[index]);
					System.out.println(Property);
					List<WebElement> FilterValues;
					try
					{
						//Fetch all the Values under Specified Filter
						FilterValues = operations.getWebElements(driver, "xpath", Property);
						//If Filter Values are Present
						if(FilterValues.size()>0)
						{
							FilterCount=0;
							//Loop it
							for(;FetchRandomAgainFlag;)
							{
								if(FilterValues.size()==FilterCount)
								{		
									System.out.println("All Filter values are Verified. Hence Breaking it..");
									break;
								}		
								//Generate a Random Number
								int RandomFilter = operations.GenerateRandomNumer(driver, FilterValues.size());
								System.out.println("RandomFilter -> "+RandomFilter);
								//StoreRandom - To Store All Random numbers and make sure Same Number is not chose again
								/*If StoreRandom is Empty -> [First Random number] Store in StoreRandom separated by "~"
								 *If StoreRandom is not Empty - Store in StoreRandom separated by "~"
								 *If Randomly chosen Value is not Present in StoreRandom - Store in StoreRandom separated by "~"
								 *Else - Skip the Below Steps and Choose New Random Number*/								
								if((!StoreRandom.isEmpty() && !(StoreRandom.contains(""+RandomFilter))) || StoreRandom.isEmpty())
								{								
									StoreRandom = StoreRandom.concat(""+RandomFilter).concat("~");
									System.out.println(StoreRandom);
									FilterCount++;
								}
								else
								{
									continue;
								}
								//Fetch the Property and Replace {FILTER} in XPath with Looping Filter Values
								Property = FetchProperty("CheckboxWheelFilterValue", "{FILTER}", arr_Values[index]);
								//Replace {INDEX} in Property String with Randomly chosen Value
								Property =  Property.replace("{INDEX}", ""+RandomFilter);
								System.out.println(Property);
								LOGMSG = "Unable to click Filters in Wheels PRP";
								//Check the checkBox
								operations.clickElement(driver, "xpath", Property, false);	
								System.out.println("clicked Filter");
								//Wait for Page to Load
								operations.WaitForPageload(driver);
								//Validate Wheels are displayed
								//Fetch the Property
								FetchWheelItems = gei.getProperty("VerifyWheelItems");
								WheelItems.clear();
								//Fetch all Visible Wheels
								WheelItems = operations.getWebElements(driver, "xpath", FetchWheelItems);
								System.out.println("Wheel Displayed"+ WheelItems.size());
								//If Wheels are Displayed
								if(WheelItems.size()>1)
								{
									//Validate Wheels
									for (WebElement element: WheelItems) 
									{	
										//Fetch the Property and Replace {ID} in XPath with Looing WebElements ID
										Property = FetchProperty("VerifyWheel","{ID}", element.getAttribute("id"));
										System.out.println("Wheels verified after Filter" + Property);
										//Validate Wheels are Displayed
										softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", Property, false));
									}
									//Break Inner Loop for Particular filter
									FetchRandomAgainFlag = false;
									System.out.println("Wheels Displayed");
								}
								else
								{
									//Else Continue Inner Loop and Choose Some Other Filter Value
									FetchRandomAgainFlag = true;
									LOGMSG = "Unable to uncheck Filters in Wheels PRP";
									//UnCheck the checkBox
									operations.clickElement(driver, "xpath", Property, false);	
									System.out.println("Breaking inner loop");
								}
							}	
							//To Fetch Filter Values For Next Filter Option
							FetchRandomAgainFlag = true;
							StoreRandom="";
						}
					}
					catch (Exception e) {
						System.out.println("Filter Args not Present");
					}
				}				
				

				// ******************************************************************************************************************

				// Customer UnCheck the filter CheckBox

				LOGMSG = "Unable to uncheck the Selected Filters in Wheels PRP";
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				jse1.executeScript("window.scrollBy(0, -500)", "");
				Thread.sleep(1000);
				//Fetch the Property
				String FetchChkboxProp = gei.getProperty("CheckboxWheels");
				//Get all Filters
				List<WebElement> selectElements = operations.getWebElements(driver, "xpath", FetchChkboxProp);
				//UnCheck all CheckBoxes
				for (WebElement ele : selectElements) {

					if (ele.isSelected()) {
						ele.click();
					}

				}
				Thread.sleep(1000);
			}

			catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
		}
	
	
	// #################### Keyword Ends ###################
	
	
	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateShoppingCart
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the Wheel PRP page. 
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>AddToCart (Mandatory): Choose the shipping options (Eg : STH, PUIS
	 * etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */
	
	public class ValidateShoppingCart extends Keyword {
		private static final String ADDTOCART = "AddToCart";
		private static final String ISREMOVEFROMCART = "IsRemoveFromCart";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ADDTOCART, ISREMOVEFROMCART);
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();
			LOGMSG = "Sub Total not present in cart page";
			try {
				//If FullFillment Method is Installation in Store
				if (args.get(ADDTOCART).equalsIgnoreCase("INST") && args.get(ISREMOVEFROMCART).equalsIgnoreCase("true")) {
					Thread.sleep(2000);
					//Fetch the total number of items in cart
					List<WebElement> lst = operations.getWebElements(driver, "xpath", "CountTotalItemsCart");
					System.out.println(lst.size());
					//Loop all
					for (int i = 0; i < lst.size(); i++) {
						//Fetch the Property
						String eleIdentifier = gei.getProperty("TextCartInstallationPrice");
						//Replace {Index} with loop index
						eleIdentifier = eleIdentifier.replaceAll("{Index}", "" + i + "");
						System.out.println(eleIdentifier);
						LOGMSG = "Installation Price not present in cart page:: " + i;
						//Validating Installation Price in Cart
						softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", eleIdentifier, false));
					}
					//Fetch Shop fee Price
					String shopFeePricebeforeRemove = operations.getText(driver, "xpath", "TextValidateShopFeePrice",true);
					//Remove $
					shopFeePricebeforeRemove = shopFeePricebeforeRemove.replace("$", "");
					//Convert into Float
					float priceBefore = Float.parseFloat(shopFeePricebeforeRemove);
					System.out.println(priceBefore);
					try {
						//Call RemoveFromCart Keyword
						Keyword.runK(CUR_APP, "RemoveFromCart");
						//Fetch the Price after Removalmof Item from Cart
						String shopFeePriceAfterRemove = operations.getText(driver, "xpath", "TextValidateShopFeePrice",true);
						//Remove $
						shopFeePriceAfterRemove = shopFeePriceAfterRemove.replace("$", "");
						//Convert into Float
						float priceAfter = Float.parseFloat(shopFeePriceAfterRemove);
						System.out.println(priceAfter);
						//Compare Before Removal from Cart Price > After Removal from Cart Price 
						if (priceBefore > priceAfter) {
							System.out.println();
						}
					} catch (Exception E) {
						throw new KDTKeywordExecException("product has not been removed", E);

					}
				}
				//Validating Prices in Cart Page
				LOGMSG = "Shopping Fee not present in cart page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartShopFee", true));
				LOGMSG = "Sub Total not present in cart page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartSubTotal", true));
				LOGMSG = "State Tax not Present in cart page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartStateTax", true));
				LOGMSG = "Local Tax not Present in cart page ";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartLocalTax", true));
				LOGMSG = "Grand total not Present in cart page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCartGrandTotal", true));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseVehicle_TBG
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to select the vehicle in
	 * YMME under Tire Buying Guide
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Year (Mandatory): Select the value of year dropdown (Eg : 2010 / 2018
	 * / etc..)</li>
	 * <li>Make (Mandatory): Select the value of make dropdown (Eg : Honda /
	 * Ford / etc..)</li>
	 * <li>Model (Mandatory): Select the value of Model dropdown (Eg : Civic /
	 * Escape / etc..)</li>
	 * <li>Engine (Mandatory): Select the value of Engine dropdown (Eg : 4-1998
	 * 2.0L DOHC / V6-181 3.0L DOHC / etc..)</li>
	 * <li>DriveTrain (Mandatory): Select the value of DriveTrain dropdown (Eg :
	 * 4WD/AWD / 2WD / etc..)</li>
	 * <li>Trim (Mandatory): Select the value of Trim dropdown (Eg : HYBRID GVW
	 * 4680-4720 / LIMITED GVW 4500-4520 XLT GVW 4460-4520 / etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */
	
	public class ChooseVehicle_TBG extends Keyword {
		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(YMMENAME, YMMEVALUE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try
			{
				WebDriver driver = context.getWebDriver();
				// YMMENAME & YMMEVALUE are comma separated values
				String VehicleArgsName = args.get(YMMENAME);
				String VehicleArgsValue = args.get(YMMEVALUE);
				//Split the Comma Separated Values
				String[] Vehname = VehicleArgsName.split(",");
				String[] Vehdata = VehicleArgsValue.split(",");
				// Filling YMME
				// Looping Comma Separated Values
				for (int index = 0; index < Vehname.length; index++) {
					// Fetch the property from property file
					String eleIdentifier = gei.getProperty("SelectYMME_TBG");
					// Replace the argument {YMME} in XPath with YMMENAME Passed as CSV from excel sheet
					eleIdentifier = eleIdentifier.replace("{YMME}", "" + Vehname[index] + "");
					System.out.println(eleIdentifier);
					// Select the value from DropDown
					operations.SelectComboBoxByVisibleText(driver, "xpath", eleIdentifier, Vehdata[index], false);
				}			
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException("Unable to choose the Vehicle in Tires Buying Guide Page", e);
			}
		}
	}

	// #################### Keyword Ends ###################


	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseVehicleAndClick_TBG
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to select the vehicle
	 * from YMME popup under TBG and Click Save/Next/See Results button.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle_TBG</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>YMMEName (Mandatory): Given the name of dropdown's in comma separated
	 * values (Eg : Year,Make,Model,Engine,Drivetrain,Trim etc..)</li>
	 * <li>YMMEValue (Mandatory): Given the value of dropdown's in comma
	 * separated values (Eg :2011,HONDA,ACCORD,4-2354 2.4L DOHC,4WD/AWD,EX-L V6
	 * COUPE etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */
	
	public class ChooseVehicleAndClick_TBG extends Keyword {
		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(YMMENAME, YMMEVALUE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// Call keyword ChooseVehicle_TBG to select the vehicle YMME
				Keyword.runK(CUR_APP, "ChooseVehicle_TBG", YMMENAME, args.get(YMMENAME), YMMEVALUE,
						args.get(YMMEVALUE));
				Thread.sleep(1000);
				/*
				 * List<WebElement> lst = operations.getWebElements(driver,
				 * "xpath", "radio_Btn_selectsize_Count_Tbg"); int size =
				 * lst.size(); int randomindex =
				 * operations.GenerateRandomNumer(driver, size); String
				 * eleIdentifierTireSize =
				 * props.getElementProperty("radio_Btn_selectsize_Tbg",CUR_APP
				 * ); eleIdentifierTireSize =
				 * eleIdentifierTireSize.replaceAll("{Index}",
				 * ""+randomindex+""); operations.clickElement(driver,
				 * "xpath",eleIdentifierTireSize , false);
				 */
				try {
					// Click next or save or see results button
					operations.clickElement(driver, "xpath", "btn_makeappointTbg", true);
					System.out.println("clicked on makeappointment");
					Thread.sleep(1000);
				} catch (Exception e) {
					// String eleIdentifier =
					// props.getElementProperty("btn_makeappointTbg");
					// WebElement element = operations.getWebElement(driver,
					// "xpath", eleIdentifier);
					// JavascriptExecutor jse = (JavascriptExecutor)driver;
					// jse.executeScript("arguments[0].scrollIntoView(true)",element);
					// jse.executeScript("window().scrollBy(0,100)",element);
					// Click next button
					operations.clickElement(driver, "xpath", "Btn_SelVehicleProceed1", true);
					System.out.println("clicked on next");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException("Unable to choose Vehicle And Click Next/Save/See Results button in Tires Buying Guide Page,", e);
			}
		}
	}

	// #################### Keyword Ends ###################


	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> Shopping_Winter_Tires
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate all the
	 * Pop-ups under Tire Buying Guide and select the random tire sizen for the
	 * selected vehicle.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle_TBG</li>
	 * <li>ChooseVehicleAndClick_TBG</li>
	 * <li>ChooseStore</li>
	 * </ul>
	 *  <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Header1 (Mandatory):header of the Pop-up "Select Your Tire Size" (Eg : Select Your Tire Size)</li>
	 * <li>HEADER2 (Mandatory):header of the Pop-up "Please rank these features"(Eg :  Please rank these features in order of importance to you.)</li>
	 * <li>WinterTiresOption (Mandatory):to change the Winter Tires(Yes,No)</li>
	 * <li>TradeWearOption (Mandatory):to reset the tradeWearoptions(Eg : Next,Reset)</li>
	 * <li>IsClickDontSeeTires (Mandatory):To choose "See" or "Dont See" tire size option(Eg : true,false)</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */


	public class Shopping_Winter_Tires extends Keyword {
		private static final String HEADER1 = "Header1";
		private static final String HEADER2 = "Header2";
		private static final String SELECTTIRE = "WinterTiresOption";
		private static final String RESET = "TradeWearOption";
		private static final String ISCLICK = "IsClickDontSeeTires";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(HEADER2, HEADER1, SELECTTIRE, RESET, ISCLICK);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				//Fetch the Property
				String eleIdentifier1 = gei.getProperty("headerverify");
				//Replace {header} in XPath with Value Passed from Excel sheet
				eleIdentifier1 = eleIdentifier1.replace("{header}", args.get(HEADER1));
				LOGMSG="What Vehicle Would You Like Tires For? Header is not Present in PopUp";
				boolean displayed1 = operations.verifyElementIsDisplayed(driver, "xpath", eleIdentifier1, false);
				if (displayed1) {
					//If Header is Displayed
					//Get the Count of Pre Selected Vehicle
					List<WebElement> lst = operations.getWebElements(driver, "xpath", "radio_Btn_selectsize_Count_Tbg");
					int size = lst.size();
					//If AtLeast One Vehicle Is Available
					if (size > 1) {
						//Choose Random Count
						int randomindex = operations.GenerateRandomNumer(driver, size);
						//Fetch the Property
						String eleIdentifierTireSize = gei.getProperty("radio_Btn_selectsize_Tbg");
						//Replace {Index} in XPath with Randomly Chosen Index
						eleIdentifierTireSize = eleIdentifierTireSize.replaceAll("{Index}", "" + randomindex + "");
						LOGMSG="Unable to Click PreSelected Vehilce in Tires Buying Guide PopUp";
						operations.clickElement(driver, "xpath", eleIdentifierTireSize, false);
					}
					//If to Click Don't See Tires size button
					if (args.get(ISCLICK).equalsIgnoreCase("True")) {
						LOGMSG="Unable to Click Don't See Tires size button in Tires Buying Guide PopUp";
						operations.clickElement(driver, "xpath", "btn_Dont_See", true);

					} 
					//To Proceed with Shopping Winter Size
					else {
						//If Shopping Winter Size is Yes
						if (args.get(SELECTTIRE).equalsIgnoreCase("yes")) {
							LOGMSG="Unable to Click Yes button under Shopping Winter Size in Tires Buying Guide PopUp";
							operations.clickElement(driver, "xpath", "toggle", true);
							Keyword.addComment("clicked on toggle button");
							System.out.println("clicked on toggle button");

						} else {
							LOGMSG="Unable to Click Next button in Tires Buying Guide PopUp";
							operations.clickElement(driver, "xpath", "Next_bttn", true);
							Thread.sleep(10000);
							Keyword.addComment("clicked on next button " + HEADER2 + "page");
							//Fetch the Property
							String eleIdentifier2 = gei.getProperty("headerverify");
							//Replace {header} in XPath with data Passed from Excel sheet
							eleIdentifier2 = eleIdentifier2.replace("{header}", args.get(HEADER2));
							System.out.println("eleIdentifier2");
							LOGMSG="Rank Header is not Present in PopUp";
							boolean displayed2 = operations.verifyElementIsDisplayed(driver, "xpath", eleIdentifier2,
									false);
							//If Rank Header is Displayed
							if (displayed2) {
								//If need to click Reset Button
								if (args.get(RESET).equalsIgnoreCase("reset")) {
									LOGMSG="Unable to Click Reset button in Tires Buying Guide PopUp";
									//Click Reset Button
									operations.clickElement(driver, "xpath", "reset_btn", true);
									Keyword.addComment("clicked on reset button " + HEADER2 + "page");
									System.out.println("clicked on reset button: can rank accordingly ");
								}
							}
						}
						LOGMSG="Unable to Click Next button under Rank Header in Tires Buying Guide PopUp";
						operations.clickElement(driver, "xpath", "Next_bttn", true);
					}
				}
			} catch (Exception e) {
				Keyword.addComment("Failed to handle popups");
			}
		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateTRPPopUp
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate all the
	 * products from the Pop=up under Tire Buying Guide and navigate to TiresPRP
	 * page selected vehicle.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle_TBG</li>
	 * <li>ChooseVehicleAndClick_TBG</li>
	 * <li>Shopping_Winter_Tires</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Header3 (Mandatory):header of the Pop-up "Choose From Your Best
	 * Match" (Eg : Choose From Your Best Match)</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */
	
	public class ValidateTRPPopUp extends Keyword {
		private static final String HEADER3 = "Header3";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(HEADER3);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				//Fetch the Property
				String eleIdentifier = gei.getProperty("headerverify");
				//Replace {header} in XPath with data Passed from Excel Sheet
				eleIdentifier = eleIdentifier.replace("{header}", args.get(HEADER3));
				//Validate the header
				boolean displayed = operations.verifyElementIsDisplayed(driver, "xpath", eleIdentifier, false);
				//If Header is displayed
				if (displayed) {
					Keyword.addComment("navigated to page" + HEADER3);
				} else {
					Keyword.addComment("could not navigated to page" + HEADER3);
				}

				//Verify Item name is present
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Item_Name", true),
						"FALSE:item name is not present");
				//Verify Item Size is present
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Size_of_item", true),
						"item size is not present");
				//Verify Item Quantity is present
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "Quantity", true),
						"item quantity is not present");

				try {
					//Verify Item Rating is present
					operations.verifyElementIsDisplayed(driver, "xpath", "star_rating", true);
					Keyword.addComment("product  review is present ");
				} catch (Exception e) {
					Keyword.addComment("product review is not present");
				}

				try {
					//Verify FullFillment Option is present
					operations.verifyElementIsDisplayed(driver, "xpath", "ship_to_home", true);
					Keyword.addComment("ship to home radio button is present");
				} catch (Exception e) {
					Keyword.addComment("ship to home radio button is not present");
				}

				try {
					//Verify See More Tires button is present
					operations.verifyElementIsDisplayed(driver, "xpath", "see_more_tires", true);
					Keyword.addComment("see_more_tires is present");
				} catch (Exception e) {
					Keyword.addComment("see_more_tires is not present");
				}
				try {
					//Verify Add to Cart button is present
					operations.verifyElementIsDisplayed(driver, "xpath", "add_to_cart", true);
					Keyword.addComment("add to cart is present");
				} catch (Exception e) {
					Keyword.addComment("add to cart is not present");
				}
				//Verify Prices are  present
				String top_prize = operations.getText(driver, "xpath", "top_prize", true);
				String bottom_prize = operations.getText(driver, "xpath", "bottom_prize", true);
				System.out.println(top_prize);
				System.out.println(bottom_prize);

				softAssert.assertEquals(top_prize, bottom_prize);
				String eleIdentifierr = gei.getProperty("see_moe_tires");

				System.out.println(eleIdentifierr);
				//Fetch the webElement
				WebElement element = operations.getWebElement(driver, "xpath", eleIdentifierr);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				try {
					//Click See More Tires button
					operations.clickElement(driver, "xpath", eleIdentifierr, false);
					System.out.println("clicked see more results");
				} catch (Exception e) {
					//Catched if See More Tires button is not Visible
					//Scroll
					System.out.println("catched for scrolling");
					jse.executeScript("arguments[0].scrollIntoView(true)", element);
					jse.executeScript("window.scrollBy(0,100)", element);
					//Click See More Tires button
					operations.clickElement(driver, "xpath", eleIdentifierr, false);
				}

				Keyword.addComment("scrolled to view see more tires");
				System.out.println("scrolled to view see more tires");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> MakeAppointment
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to make an Appointment
	 * from home Page,Choose vehicle,Store and desired service, and choose the
	 * timeslot for installation for the selected vehicle.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * </ul>
	 * <ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>YMMEName (Mandatory): Given the name of dropdown's in comma separated
	 * values (Eg : Year,Make,Model,Engine,Drivetrain,Trim etc..)</li>
	 * <li>YMMEValue (Mandatory): Given the value of dropdown's in comma
	 * separated values (Eg :2011,HONDA,ACCORD,4-2354 2.4L DOHC,4WD/AWD,EX-L V6
	 * COUPE etc..)</li>
	 * <li>DayOfWeek (Mandatory): Day of appointment for installation in store
	 * service (Eg : Sat, Sun etc..)</li>
	 * <li>TimeOfDay (Mandatory): Day of appointment for installation in store
	 * service (Eg : 8, 3 etc..)</li>
	 * <li>Zip (Mandatory): To enter zipCode in modal (Eg : 19152 etc..)</li>
	 * <li>StoreName (Mandatory): To choose the Store based on name (Eg :
	 * Philadelphia etc..)</li>
	 * <li>ServiceName(mandatory): To choose the desired service for the
	 * vehicle(Eg:Oil service,etc..</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */
	
	public class MakeAppointment extends Keyword {
		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";
		private static final String ZIP = "Zip";
		private static final String STORE = "StoreName";
		private static final String SERVICENAME = "ServiceName";
		private static final String DAYOFWEEK = "DayOfWeek";
		private static final String TIMEOFDAY = "TimeOfDay";
		private static final String FIRStNAME = "FirstName";
		private static final String LASTNAME = "LastName";
		private static final String EMAILADD = "EmailAdress";
		private static final String PHONENUM = "PhoneNumber";
		private static final String ISUPDATE = "IsUpdate";
		
		

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			{
				verifyArgs(SERVICENAME,ISUPDATE);
			}
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {WebDriver driver = context.getWebDriver();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				if(args.get(ISUPDATE).equalsIgnoreCase("false")){
				
				String eleIdentifier = gei.getProperty("make_appointment");
				operations.clickElement(driver, "xpath", eleIdentifier, false);
				Keyword.runK(CUR_APP, "ChooseStore", ZIP, args.get(ZIP), STORE, args.get(STORE));
				Thread.sleep(4000);
				operations.clickElement(driver, "xpath", "Btn_add_Vahicle", true);
				Keyword.runK(CUR_APP, "ChooseVehicleAndClick", YMMENAME, args.get(YMMENAME), YMMEVALUE,
						args.get(YMMEVALUE));
				Thread.sleep(2000);}

				// Select the Desired Service
				
				String eleIdentifierService = gei.getProperty("ChooseServiceTxt");
				eleIdentifierService = eleIdentifierService.replace("{Service}", args.get(SERVICENAME));
				System.out.println(eleIdentifierService);
				WebElement elementService = operations.getWebElement(driver, "xpath", eleIdentifierService);
				
				js.executeScript("arguments[0].scrollIntoView(true)", elementService);
				js.executeScript("window.scrollBy(0,-150)");
				Thread.sleep(2000);
				operations.clickElement(driver, "xpath", eleIdentifierService, false);

				// Select the desired sub Service
				if(args.get(SERVICENAME).equalsIgnoreCase("oil")){
					List<WebElement> lst = operations.getWebElements(driver, "xpath",  gei.getProperty("CheckBoxSelect_ServiceOil"));
					int size = lst.size();
				    System.out.println("Size of the list is:"+size);
				    int randomindex = 0;
				    for(int i=0 ; i<size ; i++)
				    {
					    randomindex = operations.GenerateRandomNumer(driver, size);
					    Thread.sleep(5000);
					    WebElement checkbox =lst.get(randomindex-1);
					    js.executeScript("arguments[0].scrollIntoView(true)", checkbox);
						js.executeScript("window.scrollBy(0,-150)");
					    checkbox.click();
					    Thread.sleep(2000);
					    
					    
				    }
				}else{
					String eleIdentifierServiceCheckBox = gei.getProperty("CheckBoxSelect_ServiceGeneral");
					eleIdentifierServiceCheckBox = eleIdentifierServiceCheckBox.replace("{Service}", args.get(SERVICENAME));
					System.out.println(eleIdentifierServiceCheckBox);
					List<WebElement> lst = operations.getWebElements(driver, "xpath",eleIdentifierServiceCheckBox);
					int size = lst.size();
				    System.out.println("Size of the list is:"+size);
				    int randomindex = 0;
				    for(int i=0 ; i<size ; i++)
				    {
					    randomindex = operations.GenerateRandomNumer(driver, size);
					    Thread.sleep(5000);
					    WebElement checkbox =lst.get(randomindex-1);
					    js.executeScript("arguments[0].scrollIntoView(true)", checkbox);
						js.executeScript("window.scrollBy(0,-150)");
					    checkbox.click();
					    Thread.sleep(2000);
					    break;
					    
				    }
				 }
				
				
				
					
				
				if(args.get(ISUPDATE).equalsIgnoreCase("false")){
					String eleIdentifierNext = gei.getProperty("makeappointNext");
					WebElement elementNext = operations.getWebElement(driver, "xpath", eleIdentifierNext);
					js.executeScript("arguments[0].scrollIntoView(true)", elementNext);
					js.executeScript("window.scrollBy(0,-150)");
					Thread.sleep(4000);
					//operations.clickElement(driver, "xpath", eleIdentifierNext, false);
					operations.javaScriptClick(driver, elementNext);
					Thread.sleep(1000);
				String eleIdentifierTime = gei.getProperty("SElectCalenderTime_Makeappoint");
				eleIdentifierTime = eleIdentifierTime.replace("{dayOfTheWeek}", args.get(DAYOFWEEK));
				eleIdentifierTime = eleIdentifierTime.replace("{timeOfTheDay}", args.get(TIMEOFDAY));
				System.out.println(eleIdentifierTime);
				WebElement elementTime = operations.getWebElement(driver, "xpath", eleIdentifierTime);
				Thread.sleep(3000);
				js.executeScript("arguments[0].scrollIntoView(true)", elementTime);
				js.executeScript("window.scrollBy(0,-150)");
				operations.clickElement(driver, "xpath", eleIdentifierTime, false);
				Thread.sleep(2000);
				operations.clickElement(driver, "xpath", "Btn_Save_Appointment", true);

				
				  /*operations.enterText(driver, "xpath", "Txt_firstname",
				  args.get(FIRStNAME)); operations.enterText(driver, "xpath",
				 "Txt_lastname", args.get(LASTNAME));
				  operations.enterText(driver, "xpath", "Txt_email",
				  args.get(EMAILADD)); operations.enterText(driver, "xpath",
				  "Txt_phNo", args.get(PHONENUM));
				  operations.clickElement(driver, "xpath", "Txt_reminder",
				  true);*/
				 

				String eleIdentifierSchedule = gei.getProperty("Btn_Schedule_makeAppointment");
				WebElement elementSchedule = operations.getWebElement(driver, "xpath", eleIdentifierSchedule);
				
				js.executeScript(
						 "arguments[0].scrollIntoView(true)",elementSchedule);
						 js.executeScript("window.scrollBy(0,-150)");
				Thread.sleep(2000);
				operations.clickElement(driver, "xpath", "Btn_Schedule_makeAppointment", true);
				Thread.sleep(2000);
				appointconfirmId = operations.getText(driver, "xpath", "AppointmentConfirmationID", true);
				System.out.println(appointconfirmId);}
				else{
					// in Update Service Page
					
					String eleIdentifierUpdate = gei.getProperty("BtnUpdateEditServicePage");
					WebElement elementUpdate = operations.getWebElement(driver, "xpath", eleIdentifierUpdate);
					js.executeScript("arguments[0].scrollIntoView(true)", elementUpdate);
					js.executeScript("window.scrollBy(0,-150)");
					Thread.sleep(4000);
					//operations.clickElement(driver, "xpath", eleIdentifierNext, false);
					operations.javaScriptClick(driver, elementUpdate);
					Thread.sleep(1000);
					
					
				}

				// js.executeScript("window.scrollBy(0,130)");
				// Thread.sleep(2000);
				// operations.clickElement(driver, "xpath",
				// "Btn_Next_scheduleappoint", true);

			
			}

			catch (Exception e) {
				e.printStackTrace();
				throw new KDTKeywordExecException("Fail", e);
			}
		}
	}
	
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> AppointmentConfirmationPage
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the
	 * "Appointment Confirmation Page" for "Make Appointment" on home page and
	 * to check whether all the links in the page are navigated to corresponding
	 * pages.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>MakeAppointment</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */

	public class AppointmentConfirmationPage extends Keyword {
		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				//Fetch Property
				String confirmationMsg = operations.getText(driver, "xpath", "Txt_Appoint_confirm", true);
				System.out.println(confirmationMsg);
				//Verify the text whether Appointment has been submitted
				softAssert.assertTrue(confirmationMsg.contains("has been submitted"), "FAIL:Appointment not confirmed");
				System.out.println("Print Page");
				String expTitlePrintPage = "Express Check-in";
				LOGMSG="Unable to Click Print button in Appointment Confirm Page";
				//Click on Print Confirmation Page
				operations.clickElement(driver, "xpath", "BtnPrintConfirmpage", true);
				//Print Confirmation Will open in New window
				//Get the Child Window ID
				map = operations.getWindowId(driver);
				//Switch to Child Window
				driver.switchTo().window(map.get("childWinID"));
				/*
				 * Set<String> set = driver.getWindowHandles(); Iterator<String>
				 * itr = set.iterator(); String parentWinID = itr.next(); String
				 * childWinID = itr.next();
				 * driver.switchTo().window(childWinID);
				 */
				//Verify title
				String actualTitlePrintPage = driver.getTitle();
				System.out.println(actualTitlePrintPage);
				softAssert.assertTrue(actualTitlePrintPage.contains(expTitlePrintPage), "Fail:Title not verified");
				//Close the child Window
				driver.close();
				//Switch back to Main Window
				driver.switchTo().window(map.get("parentWinID"));
				System.out.println("View Map Page");
				String expTitleMapPage = "Find the nearest";
				LOGMSG="Unable to Click View on Map button in Appointment Confirm Page";
				//Click View on Map button
				operations.clickElement(driver, "xpath", "BtnViewOnMapConfirmPage", true);
				/*
				 * Set<String> set2 = driver.getWindowHandles();
				 * Iterator<String> itr2 = set2.iterator(); String parentWinID2
				 * = itr2.next(); String childWinID2 = itr2.next();
				 * driver.switchTo().window(childWinID2);
				 */
				
				HashMap<String, String> map = operations.getWindowId(driver);
				//Switch to Child Window
				driver.switchTo().window(map.get("childWinID"));
				//Fetch Title
				String actualTitleMapPage = driver.getTitle();
				System.out.println(actualTitleMapPage);
				//Verify Title
				softAssert.assertTrue(actualTitleMapPage.contains(expTitleMapPage), "Fail:Title not verified");
				//Close the child Window
				driver.close();
				//Switch back to Main Window
				driver.switchTo().window(map.get("parentWinID"));
				System.out.println("LookUp Page");
				LOGMSG="Unable to Click Lookup button in Appointment Confirm Page";
				//Click Look up button
				operations.clickElement(driver, "xpath", "BtnLookUpConfirmPage", true);
				//Fetch Title in Lookup PopUp
				String confirmationMsgLookUp = operations.getText(driver, "xpath", "textLookUpPageTitle", true);
				System.out.println(confirmationMsgLookUp);
				//Verify Title in Lookup PopUp
				softAssert.assertTrue(confirmationMsgLookUp.contains("Your Reward Numbers"), "FAIL:");
				LOGMSG="Unable to Click Close button under Lookup PopUp in Appointment Confirm Page";
				//Click Close button
				operations.clickElement(driver, "xpath", "textLookUpPageClose", true);
				System.out.println("Edit Page");
				LOGMSG="Unable to Click Edit button in Appointment Confirm Page";
				operations.clickElement(driver, "xpath", "BtnEditConfirmPage", true);
				//Fetch title
				String confirmationMsgEdit = operations.getText(driver, "xpath", "txtEditPageTitle", true);
				System.out.println(confirmationMsgEdit);
				//Validate Title
				softAssert.assertTrue(confirmationMsgEdit.contains("Your Appointment"), "FAIL:");
				//Navigate back to Previous page
				driver.navigate().back();
				Thread.sleep(2000);
			} catch (Exception e) {

			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> UpdateAppointment
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to update(cancel or Edit) the 
	 * "Appointment Confirmed" for "Make Appointment" on home page
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>MakeAppointment</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */

	public class UpdateAppointment extends Keyword {
		private static final String UPDATEOPTION = "UpdateOption";
		private static final String SERVICENAME = "ServiceName";
		private static final String ISUPDATE = "IsUpdate";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			{
				verifyArgs(UPDATEOPTION,SERVICENAME,ISUPDATE);
			}
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// cancel the Appointment
				if (args.get(UPDATEOPTION).equalsIgnoreCase("cancel")) {
					operations.clickElement(driver, "xpath", "BtnCancelConfirmPage", true);
					//String eleIdentifierUpdate = props.getElementProperty("BtnUpdateStatus_Id");
					//System.out.println(appointconfirmId);
					//eleIdentifierUpdate = eleIdentifierUpdate.replace("{orderId}", appointconfirmId);
					//System.out.println(eleIdentifierUpdate);
					//WebElement element = operations.getWebElement(driver, "xpath", eleIdentifierUpdate);
					//operations.clickElement(driver, "xpath", eleIdentifierUpdate, false);
					operations.clickElement(driver, "xpath", "BtnUpdateStatus", true);
					System.out.println("Appointment Detail Page");
					String confirmationMsgAppointDetail = operations.getText(driver, "xpath",
							"TxtAppointmentDetailsTitle", true);
					System.out.println(confirmationMsgAppointDetail);
					softAssert.assertTrue(confirmationMsgAppointDetail.equals("Appointment Details"), "FAIL:");
					String eleIdentifierCancel = gei.getProperty("BtnAppointmentCancelAppoinDetailPage");
					eleIdentifierCancel = eleIdentifierCancel.replace("{orderId}", appointconfirmId);
					System.out.println(eleIdentifierCancel);
					WebElement elementCancel = operations.getWebElement(driver, "xpath", eleIdentifierCancel);
					operations.clickElement(driver, "xpath", eleIdentifierCancel, false);
					operations.clickElement(driver, "xpath", "BtnAppointmentCancelServiceappoinPage", true);

				}
				// Edit the Appointment
				else if (args.get(UPDATEOPTION).equalsIgnoreCase("edit")) {
					operations.clickElement(driver, "xpath", "BtnEditConfirmPage", true);
					//String eleIdentifierUpdate = props.getElementProperty("BtnUpdateStatus_Id");
					//eleIdentifierUpdate = eleIdentifierUpdate.replace("{orderId}", appointconfirmId);
					//System.out.println(eleIdentifierUpdate);
					//WebElement element = operations.getWebElement(driver, "xpath", eleIdentifierUpdate);
					//operations.clickElement(driver, "xpath", eleIdentifierUpdate, false);
					operations.clickElement(driver, "xpath", "BtnUpdateStatus", true);
					String confirmationMsgAppointDetail = operations.getText(driver, "xpath",
							"TxtAppointmentDetailsTitle", true);
					System.out.println(confirmationMsgAppointDetail);
					softAssert.assertTrue(confirmationMsgAppointDetail.equals("Appointment Details"), "FAIL:");
					//click on edit Appointment icon on Appointment Detail Page.
					operations.clickElement(driver, "xpath", "BtnAppointmentEditAppoinDetailPage", true);
					System.out.println("Entering Appointment edit Page and validating");
					LOGMSG="Edit Service button is not displayed Appointment Confirm Page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "BtnAppointmentEditServiceAppoinPage", true),"Fail:");
					LOGMSG="Edit Date & Time button is not displayed Appointment Confirm Page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath","BtnAppointmentEditDateAppoinPage",  true),"Fail:");
					LOGMSG="Edit Contact Information button is not displayed Appointment Confirm Page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver,  "xpath","BtnAppointmentEditContactAppoinPage", true),"Fail:");
					//click on edit Services .
					operations.clickElement(driver, "xpath", "BtnAppointmentEditServiceAppoinPage", true);
					Keyword.runK(CUR_APP, "MakeAppointment", SERVICENAME, args.get(SERVICENAME), ISUPDATE,
							args.get(ISUPDATE));
			
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new KDTKeywordExecException("Fail", e);

			}
		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> Makeappointment_TBG
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to make an Appointment
	 * from "Tires buying Guide",Choose vehicle,Store and desired service, and choose the
	 * timeslot for installation for the selected vehicle.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 *  <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle_TBG</li>
	 * <li>ChooseVehicleAndClick_TBG</li>	 
	 * </ul>
	 * <ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>YMMEName (Mandatory): Given the name of dropdown's in comma separated
	 * values (Eg : Year,Make,Model,Engine,Drivetrain,Trim etc..)</li>
	 * <li>YMMEValue (Mandatory): Given the value of dropdown's in comma
	 * separated values (Eg :2011,HONDA,ACCORD,4-2354 2.4L DOHC,4WD/AWD,EX-L V6
	 * COUPE etc..)</li>
	 * <li>DayOfWeek (Mandatory): Day of appointment for installation in store
	 * service (Eg : Sat, Sun etc..)</li>
	 * <li>TimeOfDay (Mandatory): Day of appointment for installation in store
	 * service (Eg : 8, 3 etc..)</li>
	 * <li>Zip (Mandatory): To enter zipCode in modal (Eg : 19152 etc..)</li>
	 * <li>StoreName (Mandatory): To choose the Store based on name (Eg :
	 * Philadelphia etc..)</li>
	 * <li>ServiceName(mandatory): To choose the desired service for the
	 * vehicle(Eg:Oil service,etc..</li>
	 * <li>FirstName (Mandatory): First Name for the shipment (Eg : xyz
	 * etc..)</li>
	 * <li>LastName (Mandatory): Last Name for the shipment (Eg : abc
	 * etc..)</li>
	 * <li>PhoneNumber (Mandatory): Phone Number for the shipment (Eg :
	 * 2365201478 etc..)</li>
	 * <li>Email (Mandatory): Email data for the shipment (Eg : xyz@gmail.com
	 * etc..)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */
	
	public class Makeappointment_TBG extends Keyword {
		private static final String SERVICENAME = "ServiceName";
		private static final String DAYOFWEEK = "DayOfWeek";
		private static final String TIMEOFDAY = "TimeOfDay";
		private static final String FIRStNAME = "FirstName";
		private static final String LASTNAME = "LastName";
		private static final String EMAILADD = "EmailAdress";
		private static final String PHONENUM = "PhoneNumber";
		private static final String YMMENAME = "YMMEName";
		private static final String YMMEVALUE = "YMMEValue";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			{
				verifyArgs(SERVICENAME, DAYOFWEEK, TIMEOFDAY, FIRStNAME, LASTNAME, EMAILADD, PHONENUM, YMMENAME,
						YMMEVALUE);
			}
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {

				WebDriver driver = context.getWebDriver();
				Thread.sleep(2000);
				//Click on "AddVehicle" button to select the desired vehicle
				operations.clickElement(driver, "xpath", "Btn_add_Vahicle", true);
				//"ChooseVehicleAndClick" keyword called to fill the YMME pop up.
				Keyword.runK(CUR_APP, "ChooseVehicleAndClick", YMMENAME, args.get(YMMENAME), YMMEVALUE,
						args.get(YMMEVALUE));
				//choose the desired Service for the selected vehicle
				String eleIdentifier = gei.getProperty("ChooseServiceTxt");
				eleIdentifier = eleIdentifier.replace("{Service}", args.get(SERVICENAME));
				System.out.println(eleIdentifier);
				WebElement element = operations.getWebElement(driver, "xpath", eleIdentifier);
				JavascriptExecutor js = (JavascriptExecutor)driver;
				 js.executeScript(
				 "arguments[0].scrollIntoView(true)",element);
				 js.executeScript("window.scrollBy(0,-150)");
				Thread.sleep(2000);
				operations.clickElement(driver, "xpath", eleIdentifier, false);
				
				//click on the checkbox for oil
				if(args.get(SERVICENAME).equalsIgnoreCase("oil")){
					List<WebElement> lst = operations.getWebElements(driver, "xpath",  gei.getProperty("CheckBoxSelect_ServiceOil"));
					int size = lst.size();
				    System.out.println("Size of the list is:"+size);
				    int randomindex = 0;
				    for(int i=0 ; i<size ; i++)
				    {
					    randomindex = operations.GenerateRandomNumer(driver, size);
					    Thread.sleep(5000);
					    WebElement checkbox =lst.get(randomindex-1);
					    js.executeScript("arguments[0].scrollIntoView(true)", checkbox);
						js.executeScript("window.scrollBy(0,-150)");
					    checkbox.click();
					    Thread.sleep(2000);
					    
				    }
				}//click on the checkbox for other services
				else{
					String eleIdentifierServiceCheckBox = gei.getProperty("CheckBoxSelect_ServiceGeneral");
					eleIdentifierServiceCheckBox = eleIdentifierServiceCheckBox.replace("{Service}", args.get(SERVICENAME));
					System.out.println(eleIdentifierServiceCheckBox);
					List<WebElement> lst = operations.getWebElements(driver, "xpath",eleIdentifierServiceCheckBox);
					int size = lst.size();
				    System.out.println("Size of the list is:"+size);
				    int randomindex = 0;
				    for(int i=0 ; i<size ; i++)
				    {
					    randomindex = operations.GenerateRandomNumer(driver, size);
					    Thread.sleep(5000);
					    WebElement checkbox =lst.get(randomindex-1);
					    js.executeScript("arguments[0].scrollIntoView(true)", checkbox);
						js.executeScript("window.scrollBy(0,-150)");
					    checkbox.click();
					    Thread.sleep(2000);
					    
				    }
				}
				
				
				
				//Click on "Next" button and navigate to installation pop-up
				String eleIdentifierNext = gei.getProperty("makeappointNext");
				WebElement elementNext = operations.getWebElement(driver, "xpath", eleIdentifierNext);
				 js.executeScript(
						 "arguments[0].scrollIntoView(true)",elementNext);
						 js.executeScript("window.scrollBy(0,-150)");
				Thread.sleep(4000);
				operations.clickElement(driver, "xpath", "makeappointNext", true);
				
				//Choose the installation time
				String eleIdentifier1 = gei.getProperty("SElectCalenderTime_Makeappoint");
				eleIdentifier1 = eleIdentifier1.replace("{dayOfTheWeek}", args.get(DAYOFWEEK));
				eleIdentifier1 = eleIdentifier1.replace("{timeOfTheDay}", args.get(TIMEOFDAY));
				System.out.println(eleIdentifier1);
				WebElement elementTime = operations.getWebElement(driver, "xpath", eleIdentifier1);
				 js.executeScript(
						 "arguments[0].scrollIntoView(true)",elementTime);
						 js.executeScript("window.scrollBy(0,-150)");
				Thread.sleep(2000);
				operations.clickElement(driver, "xpath", eleIdentifier1, false);
				Thread.sleep(2000);
				
				//click on the "Save" button to save the Appointment
				operations.clickElement(driver, "xpath", "Btn_Save_Appointment", true);
				String eleIdentifierSchedule = gei.getProperty("Btn_Schedule_makeAppointment");
				WebElement elementSchedule = operations.getWebElement(driver, "xpath", eleIdentifierSchedule);
				
				js.executeScript(
						 "arguments[0].scrollIntoView(true)",elementSchedule);
						 js.executeScript("window.scrollBy(0,-150)");
				Thread.sleep(2000);
				operations.clickElement(driver, "xpath", "Btn_Schedule_makeAppointment", true);
				
				String confirmationMsg = operations.getText(driver, "xpath", "Txt_Appoint_confirm", true);
				softAssert.assertTrue(confirmationMsg.contains("has been submitted"), "FAIL:Appointment not confirmed");
			} catch (Exception e) {
				e.printStackTrace();
				throw new KDTKeywordExecException("Fail", e);
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseTiresAndCompare
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to randomly choose more than one Tire and compare the selected Tires.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * </ul>
	 * <ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>IsChoose (Mandatory): Boolean Value , compares tires if value is true else validates
	 * (Eg : true, false)</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */

	public class ChooseTiresAndCompare extends Keyword {

		String Property;
		private static final String ISCHOOSEANDCOMPARE = "IsChoose";

		// Replace the INDEX with randomly chosen value
		public String FetchProperty(String PropertyName, String ActualString, String ReplaceString)
				throws KDTKeywordExecException {
			String prop = gei.getProperty(PropertyName);
			prop = prop.replace(ActualString, ReplaceString);
			System.out.println(prop);
			return prop;
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				LOGMSG = "Tires fitting your vehicle text is not present in Compare Tires page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyWheelsTiresPRP", true));
				LOGMSG = "Tires not present in Compare Tires page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyTires", true));
				//To choose 3Tires and Compare
				if (args.get(ISCHOOSEANDCOMPARE).equalsIgnoreCase("true")) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					List<Integer> Compare = new ArrayList<Integer>();
					// Property = FetchProperty("CheckboxPRPCompare",
					// "[{INDEX}]", "");
					// List<WebElement> NoOfChkbox =
					// operations.getWebElements(driver, "xpath", Property);
					ArrayList<WebElement> NoOfChkbox = new ArrayList<WebElement>();
					LOGMSG = "Unable to Fetch Total Number of Product's Compare Checkbox in PRP";
					//Fetch Property
					Property = gei.getProperty("CheckboxTotal");
					Thread.sleep(1000);
					//Get the Total Number of Products
					NoOfChkbox = operations.getWebElements(driver, "xpath", Property);
					System.out.println(NoOfChkbox.size());
					/*for (WebElement e : NoOfChkbox) {
						System.out.println(e.toString());
					}*/
					int TotalCount = NoOfChkbox.size();
					System.out.println("TotalCount -" + TotalCount);
					int noOfProductstoChose;
					/* noOfProductstoChose - Total Number of Products to be Chosen for Comparison
					 * If Total Number of Products in PRP is Greater than 3 - Make noOfProductstoChose as 3
					 * else Total Number of Products in PRP is Lesser than 3 - Make noOfProductstoChose as Total Number of Products in PRP */
					if (TotalCount >= 3) {
						noOfProductstoChose = 3;
					} else {
						noOfProductstoChose = NoOfChkbox.size();
					}
					LOGMSG = "Unable to Fetch Selected Pagination Count in PRP";
					//Get the Pagination Count from DropDown
					String showValue = operations.getText(driver, "xpath", "SelectView", true);
					//Convert to Integer
					int TotalProductinPage = Integer.parseInt(showValue);
					int TotalRandomcount;
					/* TotalRandomcount - To Generate a RandomNumber With this Range
					 * If Total Number of Products in All Page is Greater than Total Number of Product in Current Page
					 * 		TotalRandomcount - Total Number of Products in Current Page  
					 * Else TotalRandomcount - noOfProductstoChose[Total Number of Products in Current Page]*/
					if (NoOfChkbox.size() > TotalProductinPage) {
						TotalRandomcount = TotalProductinPage;
					} else {
						TotalRandomcount = noOfProductstoChose;
					}
					LOGMSG = "Unable to Choose Products in PRP for Comparison";
					//Looping to get Random Products for Comparison
					for (int index = 0; index < noOfProductstoChose; index++) {
						// System.out.println("looping");
						// System.out.println(Compare.size());
						//Generate Random Number
						int randomNumber = operations.GenerateRandomNumer(driver, TotalRandomcount);
						/*To Make Sure Not adding Same Randomly generated Number As Twice
						 * If Compare List is not Empty - Check Whether Random Number is Already Avail in Compare List
						 * 		If Already Chosen - Decrement Loop Index
						 * 		Else Add the RandomIndex into Compare List
						 * Else Add Into Compare List[For First Random Product] */
						if (Compare.size() != 0) {
							System.out.println("entered");
							if (!Compare.contains(randomNumber)) {
								Compare.add(index, randomNumber);
								System.out.println("added");
							} else {
								index--;
							}
						} else {
							Compare.add(index, randomNumber);
						}
						LOGMSG = "Unable to Click Compare Button of Products in PRP";
						//Fetch Property and Replace {INDEX} in XPath with randomNumber
						Property = FetchProperty("CheckboxPRPCompare", "{INDEX}", "" + randomNumber);
						//Scroll to Element
						WebElement scrollElement = operations.getWebElement(driver, "xpath", Property);
						operations.javaScriptScrollToViewElement(driver, scrollElement);
						js.executeScript("window.scrollBy(0,-150)");
						//Click Compare Button						
						operations.clickElement(driver, "xpath", Property, false);
						System.out.println(Compare.get(index));
					}
					LOGMSG = "Unable to Click Compare Tires Button in PRP";
					Property = gei.getProperty("BtnCompareTires");
					//Scroll to WebElement
					WebElement element = operations.getWebElement(driver, "xpath", Property);
					operations.javaScriptScrollToViewElement(driver, element);
					js.executeScript("window.scrollBy(0,-150)");
					//Click Compare Tires Button
					operations.clickElement(driver, "xpath", "BtnCompareTires", true);
					//Wait for Page to Load
					operations.WaitForPageload(driver);
					System.out.println("Clicked");
					//Validation in Compare Tires Page
					LOGMSG = "Compare Tires text is not present in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCompareTires", true));
					LOGMSG = "Tires are not present in side by side in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "VerifyCompareTire", true));
					LOGMSG = "DEscription not present in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCTDescription", true));
					LOGMSG = "Warranty not present in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCTWarranty", true));
					LOGMSG = "Part number not present in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCTPartNumber", true));
					LOGMSG = "Availability not present in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCTAvail", true));
					LOGMSG = "Discount not present in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCTDiscounts", true));
					LOGMSG = "Tread Type not present in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCTTreadType", true));
					LOGMSG = "Performance Characteristics not present in Compare Tires page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextCTPerfChar", true));
				}

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateOrderConfirmation
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to validate "Order Confirmation Page" after the order is placed.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * <li>GuestCheckOut</li>
	 * </ul>
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */

	public class ValidateOrderConfirmation extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// operations.clickOnCloseForPopUp(driver);
				//Validating Confirm Order Page
				LOGMSG = "Thank you for your order is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmThankYouHeader", true),	LOGMSG);
				LOGMSG = "Summary Table Items (image) Header is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmItemsHeader", true),LOGMSG);
				LOGMSG = "Summary Table Items(product name) Header is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmSummaryHeader", true),LOGMSG);
				LOGMSG = "Summary Table Quantity Header is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmQuantityHeader", true),LOGMSG);
				LOGMSG = "Summary Table price Header is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmTotalHeader", true),LOGMSG);
				System.out.println("The Product Items - price column name: Actual: "+ operations.getText(driver, "xpath",gei.getProperty("TextOrderConfirmTotalHeader"), false)+ "Expected: total");
				LOGMSG = "Order number is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmOrderNumber", true),LOGMSG);
				//Validating Product Details
				LOGMSG = "Product image is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "ImageOrderConfirmProduct", true), LOGMSG);
				LOGMSG = "Product Name is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmaItemName", true),LOGMSG);
				LOGMSG = "Product quantity is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmItemQuantity", true),LOGMSG);
				LOGMSG = "Product price is not displayed in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmItemPrice", true),LOGMSG);
				//Validating Billing Info
				LOGMSG = "Billing Info is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmBillingInfo", true),LOGMSG);
				System.out.println("Billing Information : Actual: "	+ operations.getText(driver, "xpath", "TextOrderConfirmBillingInfo", true)+ "Expected: billing information");
				LOGMSG = "Billing Information Phone: is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmBillingInfoPhone", true),LOGMSG);
				LOGMSG = "Billing Information Email: is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderconfirmBillingInfoEmail", true),LOGMSG);
				//Validating Shipping Info
				try {
					LOGMSG = "Shipping Information header is not present in order confirmation page";
					softAssert.assertTrue(	operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmShippingInfo", true),	LOGMSG);
					System.out.println("Shipping Information: Actual: "	+ operations.getText(driver, "xpath",gei.getProperty("TextOrderConfirmShippingInfo"), false)+ "Expected: shipping information");
					LOGMSG = "Confirmation Shipping Information Phone: is not present in order confirmation page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath","TextOrderConfirmShippingInfoPhone", true), LOGMSG);
				} catch (Exception e) {
					System.out.println("Shipping Information not verified");
					System.out.println("The Product Items - price column name: Actual: "+ gei.getProperty("TextOrderConfirmShippingInfo") + "Expected: total");
				}
				//Validating Payment Info
				try {
					LOGMSG = "Payment Information header is not present in order confirmation page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmPaymentInfo", true),LOGMSG);
					System.out.println("Payment Information: Actual: "+ operations.getText(driver, "xpath",	gei.getProperty("TextOrderConfirmPaymentInfo"), false)+ "Expected: payment information");
					LOGMSG = "Payment Information credit card expiry date is not present in order confirmation page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath","TextOrderConfirmPaymentInfoExpDate", true), LOGMSG);
				} catch (Exception e) {
					LOGMSG = "Payment Information - not yet paid message is not present in order confirmation page";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath","TextOrderConfirmPaymentInfoNotYetPaidMessage", true), LOGMSG);
					System.out.println("Payment Information verified");
				}
				//Validating Prices
				LOGMSG = "Price Headers - item subtotal is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath","TextOrderConfirmItemSubtotalHeader", true), LOGMSG);
				LOGMSG = "Price Headers - shipping and handling is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath","TextOrderConfirmShippingAndHandlingHeader", true), LOGMSG);
				LOGMSG = "Price Headers - govt imposed fees is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath","TextOrderConfirmGovtImposedFeesHeader", true), LOGMSG);
				LOGMSG = "Price Headers - tax is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmTaxHeader", true),LOGMSG);
				LOGMSG = "Price Headers - grand total  is not present in order confirmation page";
				softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextOrderConfirmGrandTotal", true),LOGMSG);

			} catch (Exception e) {
				throw new KDTKeywordExecException("Unable to verify data in confirmation Page", e);
			}
		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> OperationsInCart
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to validate all the links present in the "Mini Shopping Cart".
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * </ul>
	 * <ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>IsClickImg (Mandatory):click on the image in mini shopping cart.
	 *(Eg : true,false)</li>
	 *<li>IsClickTitle (Mandatory):click on the title of the product in mini shopping cart.
	 *(Eg : true,false)</li>
	 *<li>IsClose (Mandatory):click on the close button in mini shopping cart.
	 *(Eg : true,false)</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */
	
	public class OperationsInCart extends Keyword {
		private static final String ISCLICKIMG = "IsClickImg";
		private static final String ISCLICKTITLE = "IsClickTitle";
		private static final String ISCLOSE = "IsClose";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ISCLICKIMG, ISCLICKTITLE, ISCLOSE);

		}

		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();
			try {
				Thread.sleep(4000);
				//Click Product Image in MiniCart PopUp and Validated Whether it is Navigated to PDP
				if (args.get(ISCLICKIMG).equalsIgnoreCase("true")) {
					LOGMSG = "Unable to Fetch Product Name in Mini Cart PopUp";
					//Fetch the Product name in Cart
					String minicartProdName = operations.getText(driver, "xpath", "validateProdNameMiniCart", true);
					LOGMSG = "Product Image is Not Clickable in Mini Cart PopUp";
					//Click Product Image
					operations.clickElement(driver, "xpath", "imgMinicart", true);
					System.out.println(minicartProdName);
					LOGMSG = "Unable to Fetch Product Name in PDP";
					//Fetch Product name in PDP
					String PDPProdName = operations.getText(driver, "xpath", "ValidateProdNamePDP", true);
					System.out.println(PDPProdName);
					LOGMSG = "Not navigated to PDP page";
					//Comparing the Product Name's in PDP and Mini Cart PopUp
					softAssert.assertEquals(minicartProdName, PDPProdName);
				} 
				//Click Close Button in MiniCart PopUp
				else if (args.get(ISCLOSE).equalsIgnoreCase("true")) {
					LOGMSG = "Close Button is Not Clickable in Mini Cart PopUp";
					//Click Close Button
					operations.clickElement(driver, "xpath", "Btn_closeMinicart", true);
					System.out.println("clicked on close button");
					System.out.println(operations.verifyElementIsDisplayed(driver, "xpath", "ValidateProdNamePDP", true));
				} 
				//Click Product Name in MiniCart PopUp and Validated Whether it is Navigated to PDP
				else if (args.get(ISCLICKTITLE).equalsIgnoreCase("true")) {
					LOGMSG = "Unable to Fetch Product Name in Mini Cart PopUp";
					//Fetch the Product name in Cart
					String minicartProdName = operations.getText(driver, "xpath", "validateProdNameMiniCart", true);
					LOGMSG = "Product Name is Not Clickable in Mini Cart PopUp";
					//Click Product Name
					operations.clickElement(driver, "xpath", "validateProdNameMiniCart", true);
					System.out.println(minicartProdName);
					//Fetch Product name in PDP
					String PDPProdName = operations.getText(driver, "xpath", "ValidateProdNamePDP", true);
					System.out.println(PDPProdName);
					LOGMSG = "Not navigated to PDP page";
					//Comparing the Product Name's in PDP and Mini Cart PopUp
					softAssert.assertEquals(minicartProdName, PDPProdName);

				}

			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ValidateWheelsCartPage
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to validate the
	 * "Computerised wheel Allignment" feature in "shopping Cart" for Wheels.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>NavigateToChooseStore</li>
	 * <li>ChooseStore</li>
	 * <li>ChooseWheelsPRP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * </ul>
	 * <ul>
	 * @author 
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */
	
	public class ValidateWheelsCartPage extends Keyword {

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stu
			try {
				WebDriver driver = context.getWebDriver();
				LOGMSG = "Unable to Fetch Price of Computerized Wheel Alignment";
				//Get the Price of ComPuterized Wheel Panel
				String PricebeforeAdd = operations.getText(driver, "xpath", "TextComputerisedWheelPanelPrice", true);
				//Remove $
				PricebeforeAdd = PricebeforeAdd.replace("$", "");
				System.out.println(PricebeforeAdd);
				//Convert to Float
				float priceBefore = Float.parseFloat(PricebeforeAdd);
				System.out.println(priceBefore + "");
				//Click Computerized Wheel Alignment
				LOGMSG = "Unable to Click Computerized Wheel Alignment link";
				operations.clickElement(driver, "xpath", "BtnComputerisedWheelPanelAdd", true);
				Thread.sleep(5000);
				LOGMSG = "Unable to Fetch Price of After Adding Computerized Wheel Alignment";
				//Get the Price of ComPuterized Wheel Panel After Adding Feature
				String PriceAfterAdd = operations.getText(driver, "xpath", "TextComputerisedWheelPanelPrice", true);
				//Remove $
				PriceAfterAdd = PriceAfterAdd.replace("$", "");
				//Convert to Float
				float priceAfter = Float.parseFloat(PriceAfterAdd);
				System.out.println(priceAfter + "");
				//Compare Before and After Adding Computerized Wheel Alignment
				if (priceBefore != priceAfter) {
					System.out.println("Price Applied");
				}
			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}

		}

	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChangeStore
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to change the selected store on the "Shopping cart" Page.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * </ul>
	 * <ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>Zip (Mandatory): To enter zipCode in modal (Eg : 19152,19803 etc..)</li>
	 * <li>StoreName (Mandatory): To choose the Store based on name (Eg :
	 * Philadelphia etc..)</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class ChangeStore extends Keyword {

		private static final String ZIP = "Zip";
		private static final String STORE = "StoreName";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ZIP, STORE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// operations.clickOnCloseForPopUp(driver);
				LOGMSG = "Unable to Click Change Store Link in Cart Page";
				//Click Change Store
				operations.clickElement(driver, "xpath", "Btn_ChangeStore", true);
				Thread.sleep(7000);
				//Call ChooseStore to Enter Zipcode and Choose Store
				Keyword.runK(CUR_APP, "ChooseStore", ZIP, args.get(ZIP), STORE, args.get(STORE));
				driver.navigate().refresh();
				//Fetch the Store Name to Verify Store is Updated
				String storeName = operations.getText(driver, "xpath", gei.getProperty("storeName"),false);
				LOGMSG = "Store name is not updated in Cart Page";
				softAssert.assertTrue(storeDetails.toLowerCase().contains(storeName.toLowerCase()),	"Store name is not changed to :" + args.get(STORE) + " The value displayed is :" + storeName);
				System.out.println("Changed Store Details:" + storeDetails);
			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChangeQuantity
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to change the Quantity of the Products in the "Shopping Cart" page.
	`* </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * </ul>
	 * <ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>ChangedQuantity (Mandatory):value for Quantity to be changed (Eg
	 * : 2.4,etc...)</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */

	public class ChangeQuantity extends Keyword {

		private static final String CHANGEDQUANTITY = "ChangedQuantity";

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(CHANGEDQUANTITY);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				// operations.clickOnCloseForPopUp(driver);
				String eleIdentifier = gei.getProperty("chooseQuantity");
				LOGMSG = "Unable to Fetch Quantity in Cart Page";
				//Fetching Selected Quantity
				String value = operations.getText(driver, "xpath",	gei.getProperty("SelectedQuantity"), false);
				LOGMSG = "Unable to Select Desired Quantity in Cart Page";
				//Select Quantity passed from Excel Sheet
				operations.SelectComboBoxByVisibleText(driver, "xpath", eleIdentifier, args.get(CHANGEDQUANTITY),false);
				driver.navigate().refresh();
				//Fetch the Updated Quantity
				String changedValue = operations.getText(driver, "xpath", "SelectedQuantity", true);
				LOGMSG = "Quantity is not Updated in Cart Page";
				//Validating Whether Quantity is Updated 
				softAssert.assertTrue(changedValue.equals(CHANGEDQUANTITY),	"Values dont match. Value before change:" + value + " and value after change:" + changedValue);
				System.out.println("changed Quantity on cart from " + value + " to " + changedValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> RemoveFromCart
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> " This Keyword is used to validate the "Remove" Feature from the Shopping Cart Page by randomly choosing the products in cart.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA </div>
	 */

	public class RemoveFromCart extends Keyword {

		public void init() throws KDTKeywordInitException {
			super.init();
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();

			try {
				//Fetch the number of Remove button in Cart Page
				String FetchXpath1 = gei.getProperty("BtnRemove_from_cartList");				
				List<WebElement> ListOfProductsInCart = operations.getWebElements(driver, "xpath", FetchXpath1);
				int sizeBeforeRemove = ListOfProductsInCart.size();
				/*If No of PRoducts is Greater than 1*
				 * 		Randomly choose a Product and Remove from Cart
//				 * Else Remove the Product Present in Cart */
				if (sizeBeforeRemove > 1) {
					//Generate a Random Number
					int RandomVehicle = operations.GenerateRandomNumer(driver, ListOfProductsInCart.size());
					//Fetch Property
					String productRemove = gei.getProperty("BtnRemove_from_cart");
					//Replace {Index} in XPath with Random index
					productRemove = productRemove.replace("{Index}", "" + RandomVehicle + "");
					System.out.println(productRemove);
					LOGMSG = "Unable to Click Remove Link in Cart Page";
					//Click Remove Link
					operations.clickElement(driver, "xpath", productRemove, false);					
					operations.bringElementintoView(driver, "BtnConfirm_remove");
					LOGMSG = "Unable to Click Remove Button in Confirm Popup";
					//Click Remove button in Confirm PopUp
					operations.clickElement(driver, "xpath", "BtnConfirm_remove", true);
					//Fetch the number of Remove[Products] button in Cart after Removal
					String FetchXpath2 = gei.getProperty("BtnRemove_from_cartList");
					List<WebElement> ListOfProductsInCartAfterRemove = operations.getWebElements(driver, "xpath",
							FetchXpath1);
					int sizeAfterRemove = ListOfProductsInCartAfterRemove.size();
					LOGMSG = "Product not removed Properly in Cart Page";
					/*If Count is Lesser after Removal Then Product is Removed Successfully*/
					if (sizeBeforeRemove == (sizeAfterRemove + 1)) {
						System.out.println("Product removed");
					}
				} 
				//If only 1 Product is Present in Cart page
				else {
					LOGMSG = "Unable to Click Remove Link in Cart Page";
					//Click Remove Link
					operations.clickElement(driver, "xpath", FetchXpath1, false);
					operations.bringElementintoView(driver, "BtnConfirm_remove");
					LOGMSG = "Unable to Click Remove Button in Confirm Popup";
					//Click Remove button in Confirm PopUp
					operations.clickElement(driver, "xpath", "BtnConfirm_remove", true);
					LOGMSG = "Product Not Removed Properly in Cart";
					softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath", "TextEmptyShoppingCartPage", true),"Not Removed");
					System.out.println("Product is removed");
					LOGMSG = "Shopping Cart is not Empty in Cart Page";
					//Validate Shopping Cart is Empty
					Boolean b = operations.verifyElementIsDisplayed(driver, "xpath", "TextEmptyShoppingCartPage", true);
					if (b) {
						System.out.println("Product removed");
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}	
	// #################### Keyword Ends ###################
	
	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> FooterLink
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate all the
	 * products from the Pop=up under Tire Buying Guide and navigate to TiresPRP
	 * page selected vehicle.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * <li>GuestCheckOut</li>
	 * <li>GetOrderId</li>
	 * </ul>
	 * <ul>
	 * <b><i>Arguments:</i></b>
	 * <li>LinkName (Mandatory):Links in the footer of the page " (Eg : Track an
	 * Order)</li>
	 * </ul>
	 * 
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */
	public class FooterLink extends Keyword {

		private static final String LINKNAME = "LinkName";

		@Override
		public void init() throws KDTKeywordInitException {
			// TODO Auto-generated method stub
			super.init();
			verifyArgs(LINKNAME);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stub
			try {
				WebDriver driver = context.getWebDriver();
				String eleIdentifier = gei.getProperty("linkname").replace("{linkname}",
						args.get(LINKNAME));
				WebElement element = operations.getWebElement(driver, "xpath", eleIdentifier);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true)", element);
				jse.executeScript("window.scrollBy(0,-150)", element);
				operations.clickElement(driver, "xpath", eleIdentifier, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> Quick_Order_Lookup
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to validate the "Order
	 * History Page" of the placed orders based on the "order Id" and return the
	 * item if needed.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * <li>GuestCheckOut</li>
	 * <li>GetOrderId</li>
	 * <li>FooterLink</li>
	 * </ul>
	 * <ul>
	 * <b><i>Arguments:</i></b>
	 * <li>EmailAddress (Mandatory):emial id " (Eg : abc@gmail.com)</li>
	 * </ul>
	 * 
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */
	public class Quick_Order_Lookup extends Keyword {

		private static final String EMAILADDRESS = "EmailAddress";

		@Override
		public void init() throws KDTKeywordInitException {
			// TODO Auto-generated method stub
			super.init();
			verifyArgs(EMAILADDRESS);
		}

		@Override
		public void exec() throws KDTKeywordExecException {
			// TODO Auto-generated method stub
			try {
				WebDriver driver = context.getWebDriver();
				operations.enterText(driver, "xpath", "order_number_txt", order_id);
				Keyword.addComment("entered the order number:" + order_id);
				operations.enterText(driver, "xpath", "email_address_text", args.get(EMAILADDRESS));
				Keyword.addComment("entered the emailaddress:" + EMAILADDRESS);
				operations.clickElement(driver, "xpath", "view_order_status", true);
				Keyword.addComment("Successfully clicked on view order status button");
				String eleIdentifier = gei.getProperty("return_items").replace("{ordernumber}",
						order_id);
				operations.clickElement(driver, "xpath", eleIdentifier, false);
				Thread.sleep(5000);
				boolean displayed = operations.verifyElementIsDisplayed(driver, "xpath", "return_item_popup", true);
				if (displayed) {
					System.out.println("Reached popup return item popup");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################
	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> GetOrderId
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to fetch the "Oreder Id"
	 * of the placed Orders.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicle</li>
	 * <li>ChooseStore</li>
	 * <li>ValidatePRP</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidatePDP</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>AddToBag</li>
	 * <li>HandleShoppingCart</li>
	 * <li>GuestCheckOut</li>
	 * </ul>
	 * 
	 * @author
	 * @version 001
	 * @creationDate
	 * @modificationDate NA </div>
	 */
	public class GetOrderId extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();

		}

		@Override
		public void exec() throws KDTKeywordExecException {
			try {
				WebDriver driver = context.getWebDriver();
				String eleIdentifier = gei.getProperty("order_numb");
				String order = operations.getText(driver, "xpath", eleIdentifier, false);
				order = order.trim();
				String[] id = order.split(" ");
				order_id = id[id.length - 1];
				System.out.println("order_id:" + order_id);
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
	}

	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################

	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> VerifyProductsUnderRecommendations
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to Verify Recommended Products in different Pages.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ClickSubCategory</li>
	 * <li>ChooseVehicleAndClick</li>
	 * <li>ChooseStoreOnProducts</li>
	 * <li>ValidateProductsPRP</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidateProductsPDP</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * <li>AddToBag</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * <li>HandleShoppingCart</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * <li>GuestCheckOut</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * </ul>
	 * <b><i>Arguments:IsCartPage</i></b>
	 * <ul>
	 * <li>IsCartPage (Mandatory) : Value is true if its in Mini Cart page or cart page. False in rest of pages</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate 29th December 2017
	 * @modificationDate NA
	 * </div>
	 */

	public class VerifyProductsUnderRecommendations extends Keyword {

		public static final String ISCARTPAGE = "IsCartPage";
		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ISCARTPAGE);
		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();
			//operations.clickOnCloseForPopUp(driver);
			try{

				int index = 1;
				int ProductsSize = 0;
				String ImageProp , TitleProp, RatingProp,ReviewProp,PriceProp,AddToCartProp;
				ArrayList<String> ProductTitle = new ArrayList<String>();
				if(args.get(ISCARTPAGE).equalsIgnoreCase("false"))
				{
					//Get into this if its cart page or mini cart Page
					ArrayList<WebElement> recommendedProductImage = operations.getWebElements(driver,"xpath",  gei.getProperty("RecommendedProductsList"));
					//Get the product size for recommended products and store it in variable ProductsSize
					ProductsSize = recommendedProductImage.size();
					//Print the size of recommended Products
					System.out.println("Products Size:"+ProductsSize);
					//Fetch property RecommendedProductImage and store it in ImageProp Variable
					ImageProp = gei.getProperty("RecommendedProductImage");
					//Fetch property RecommendedProductTitle and store it in TitleProp Variable
					TitleProp = gei.getProperty("RecommendedProductTitle");
					//Fetch property RecommendedProductRating and store it in RatingProp Variable
					RatingProp = gei.getProperty("RecommendedProductRating");
					//Fetch property RecommendedProductReviews and store it in ReviewProp Variable
					ReviewProp = gei.getProperty("RecommendedProductReviews");
					//Fetch property RecommendedProductPrice and store it in PriceProp Variable
					PriceProp = gei.getProperty("RecommendedProductPrice");
					//Fetch property RecommendedProductAddToCart and store it in AddToCartProp Variable
					AddToCartProp = gei.getProperty("RecommendedProductAddToCart");
				}
				else
				{
					//Get into this if its any other page - PDP , PRP , Order confirmation
					ArrayList<WebElement> recommendedProductImage = operations.getWebElements(driver,"xpath",  gei.getProperty("RecommendedProductsCartList"));
					//Get the product size for recommended products and store it in variable ProductsSize
					ProductsSize = recommendedProductImage.size();
					System.out.println("Products Size:"+ProductsSize);
					//Fetch property RecommendedProductImage and store it in ImageProp Variable
					ImageProp = gei.getProperty("RecommendedProductCartImage");
					//Fetch property RecommendedProductTitle and store it in TitleProp Variable
					TitleProp = gei.getProperty("RecommendedProductCartTitle");
					//Fetch property RecommendedProductRating and store it in RatingProp Variable
					RatingProp = gei.getProperty("RecommendedProductCartRating");
					//Fetch property RecommendedProductReviews and store it in ReviewProp Variable
					ReviewProp = gei.getProperty("RecommendedProductCartReviews");
					//Fetch property RecommendedProductPrice and store it in PriceProp Variable
					PriceProp = gei.getProperty("RecommendedProductCartPrice");
					//Fetch property RecommendedProductAddToCart and store it in AddToCartProp Variable
					AddToCartProp = gei.getProperty("RecommendedProductCartAddToCart");
				}
				if(ProductsSize!=0){
					String ImageProperty,TitleProperty,RatingProperty,PriceProperty,ReviewProperty,AddToCartProperty,oldProductTitle;
					while(index<=ProductsSize)
					{
						//Replace {option} to the value i in ImageProp and store it in variable ImageProperty
						ImageProperty = ImageProp.replaceAll("\\{INDEX\\}", ""+index);
						//Replace {option} to the value i in TitleProp and store it in variable TitleProperty 
						TitleProperty = TitleProp.replaceAll("\\{INDEX\\}", ""+index);
						//Replace {option} to the value i in RatingProp and store it in variable RatingProperty 
						RatingProperty = RatingProp.replaceAll("\\{INDEX\\}", ""+index);
						//Replace {option} to the value i in ReviewProp and store it in variable ReviewProperty 
						ReviewProperty = ReviewProp.replaceAll("\\{INDEX\\}", ""+index);
						//Replace {option} to the value i in PriceProp and store it in variable PriceProperty 
						PriceProperty = PriceProp.replaceAll("\\{INDEX\\}", ""+index);
						//Replace {option} to the value i in AddToCartProp and store it in variable AddToCartProperty 
						AddToCartProperty = AddToCartProp.replaceAll("\\{INDEX\\}", ""+index);

						//Verify if the Product Image is displayed for recommended product
						softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath",ImageProperty, false),"Image not displayed for Recommended Product: "+index);
						//Verify if the Product Title is displayed for recommended product
						softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath",TitleProperty, false),"Title not displayed for Recommended Product: "+index);
						//Print the title of the recommended product
						System.out.println("Product Title "+index+":"+operations.getText(driver, "xpath", TitleProperty, false));
						oldProductTitle = operations.getText(driver, "xpath", TitleProperty, false);
						if(ProductTitle.contains(oldProductTitle))
						{
							throw new Exception("Duplicate products found.");
						}
						else
						{
							ProductTitle.add(oldProductTitle);		
							System.out.println("No duplicates are found");
						}										
						//Verify if the Product Price is displayed for recommended product
						softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath",PriceProperty, false),"Price not displayed for Recommended Product: "+index);
						//Verify if the Product Rating is displayed for recommended product
						softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath",RatingProperty, false),"Rating not displayed for Recommended Product: "+index);
						//Verify if the Product Review is displayed for recommended product
						softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath",ReviewProperty, false),"Review not displayed for Recommended Product: "+index);
						//Verify if the Product Add To Cart button is displayed for recommended product
						softAssert.assertTrue(operations.verifyElementIsDisplayed(driver, "xpath",AddToCartProperty, false),"Add To Cart not displayed for Recommended Product: "+index);
						index++;
					}

					System.out.println("Verified Recommeded Product Image, Title, Price, Rating, Review and AddToCart button for number of Products:"+index);
				}
				else {
					try {
						operations.verifyElementIsDisplayed(driver, "xpath", "Text_ZeroResults", true);
						// Check if the REcommendations are not displayed for zero results
						Keyword.addComment("Search results is zero. Recommendations must not be displayed.");
						System.out.println("No recommendations found. Products size in recommendations :" + ProductsSize);
					} catch (Exception ex) {
						Keyword.addComment("Search results contains Products. Recommendations must be displayed.");
						throw new Exception("Recommendations Not Displayed in page: " + args.get(ISCARTPAGE));
					}
				}
			}
			catch(Exception e)
			{
				throw new KDTKeywordExecException("Unable to Validate Products in REcommendations", e);
			}
		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################

	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> ChooseAProductFromRecommendations
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to Choose a Recommended Product in different Pages.
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * <li>ClickMenu</li>
	 * <li>ClickSubMenu</li>
	 * <li>ChooseVehicleAndClick</li>
	 * <li>ValidateTiresPRP</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * <li>ChoosePRPProduct</li>
	 * <li>ValidateTiresPDP</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * <li>AddToBag</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * <li>ChooseAProductFromRecommendations</li>
	 * <li>HandleShoppingCart</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * <li>GuestCheckOut</li>
	 * <li>VerifyProductsUnderRecommendations</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate 29th December 2017
	 * @modificationDate NA
	 * </div>
	 */

	public class ChooseAProductFromRecommendations extends Keyword {

		@Override
		public void init() throws KDTKeywordInitException {
			super.init();

		}

		@Override
		public void exec() throws KDTKeywordExecException {

			WebDriver driver = context.getWebDriver();
			//operations.clickOnCloseForPopUp(driver);
			try{
				int i = 0;
				int ProductsSize = 0;
				String TitleProp,AddToCartProp;
				ArrayList<WebElement> recommendedProductImage = operations.getWebElements(driver,"xpath",  gei.getProperty("RecommendedProductsCartList"));
				//Get the product size for recommended products and store it in variable ProductsSize
				ProductsSize = recommendedProductImage.size();
				//Print the size of recommendedProductImage  list
				System.out.println("Products Size:"+ProductsSize);
				//Fetch the property RecommendedProductCartTitle and store it in a variable TitleProp
				TitleProp = gei.getProperty("RecommendedProductCartTitle");
				//Fetch the property RecommendedProductCartAddToCart and store it in a variable AddToCartProp
				AddToCartProp = gei.getProperty("RecommendedProductCartAddToCart");
				String TitleProperty,AddToCartProperty ;
				//Generate a random number i which can have a max length as ProductsSize
				i = operations.GenerateRandomNumer(driver, ProductsSize);
				//Replace {option} to the value i in TitleProp and store it in variable TitleProperty 
				TitleProperty = TitleProp.replaceAll("\\{INDEX\\}", ""+i);
				//Replace {option} to the value i in AddToCartProp and store it in variable AddToCartProperty 
				AddToCartProperty = AddToCartProp.replaceAll("\\{INDEX\\}", ""+i);
				//Click on AddToCartProperty element/click on addToCart button for recommended product
				operations.clickElement(driver, "xpath", AddToCartProperty, false);
				//Print the title of selected recommended Product
				System.out.println("Selected Product:"+operations.getText(driver, "xpath", TitleProperty, false)+" from recommedations:"+i);

			}
			catch(Exception e)
			{
				throw new KDTKeywordExecException("Unable to choose a product from recommendations ", e);
			}
		}
	}
	
	// #################### Keyword Ends ###################

	// #################### Keyword Starts #################

	/**
	 * <div align="left">
	 * <p>
	 * <b><i>Keyword Name:</i></b> FindAStore
	 * </p>
	 * <p>
	 * <b><i>Description:</i></b> This Keyword is used to Find a Store from HomePage
	 * </P>
	 * <b><i>Dependencies:</i></b>
	 * <ul>
	 * <li>Launch</li>
	 * <li>NavigateTo</li>
	 * </ul>
	 * <b><i>Arguments:</i></b>
	 * <ul>
	 * <li>ZIP (Mandatory) : Enter ZipCode and Find Stores near you from Home Page</li>
	 * </ul>
	 * @author 
	 * @version 001
	 * @creationDate 
	 * @modificationDate NA
	 * </div>
	 */
	
	public class FindAStore extends Keyword {
	
		private static final String ZIP = "Zip";
	
		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(ZIP);
		}
	
		@Override
		public void exec() throws KDTKeywordExecException {
			WebDriver driver = context.getWebDriver();
			try {
				// Select findStore Link in homePage
				LOGMSG = "Unable to Click FindStore Link in Home Page";
				operations.clickElement(driver, "Xpath", "SelectFindStore", true);
				LOGMSG = "Unable to Enter ZipCode in FindStore Dropdown";
				// Enter ZipCode
				operations.enterText(driver, "xpath", "TxtFindStoreZip", args.get(ZIP));
				LOGMSG = "Unable to Click Find Button in FindStore Dropdown";
				// Click find button
				operations.clickElement(driver, "xpath", "Btn_HomeFindStore", true);
				// Validate Store Near you
				LOGMSG = "Unable to Fetch the Find Stores Near you in FindStore Dropdown";
				// Fetch the Store
				String StoreLabel = operations.getText(driver, "xpath", "StoresLink", true);
				// Compare
				if (StoreLabel.contains(args.get(ZIP))) {
					Keyword.addComment("Store names verified in a zipcode " + args.get(ZIP));
				} else {
					Keyword.addComment("Store names doesnot verified");
				}
				LOGMSG = "Store Lists are not Displayed in FindStore Dropdown";
				// Validate Store List for Searched Store
				if (operations.verifyElementIsDisplayed(driver, "xpath", "StoreList", true)) {
					Keyword.addComment("Stores names are displayed");
				} else {
					Keyword.addComment("Stores names are not displayed");
				}
	
			} catch (Exception e) {
				throw new KDTKeywordExecException(LOGMSG, e);
			}
		}
	}
			
	// #################### Keyword Ends ###################	

	// #################### Keyword Starts #################

	/**
	* <div align="left">
	* <p>
	* <b><i>Keyword Name:</i></b> ClickBrand
	* </p>
        * <p>
	* <b><i>Description:</i></b> This Keyword is used to Choose a Brand in Brand Page
	* </P>
	* <b><i>Dependencies:</i></b>
	* <ul>
	* <li>Launch</li>
	* <li>NavigateTo</li>
	* </ul>
	* <b><i>Arguments:</i></b>
	* <ul>
	* <li>Brand (Mandatory) : To Choose Options under Brand page[Ex: Tire By Vehicle, Tire By Size]</li>
	* </ul>
	* @author 
	* @version 001
	* @creationDate 
	* @modificationDate NA
	* </div>
	*/

	public class ClickBrand extends Keyword {
		private static final String BRAND = "Brand";			
	
		@Override
		public void init() throws KDTKeywordInitException {
			super.init();
			verifyArgs(BRAND);
		}
		@Override
			public void exec() throws KDTKeywordExecException{
			WebDriver driver = context.getWebDriver();
			try {
				//Fetch Property
				String eleIdentifier = gei.getProperty("BrandLink");
				//Replace {Brand} i XPath with Data passed from Excel
				eleIdentifier = eleIdentifier.replace("{Brand}", args.get(BRAND));
				//Click on the link in Brand Page
				operations.clickElement(driver, "Xpath", eleIdentifier,false);
			    }
			catch (Exception e) {
				throw new KDTKeywordExecException("Unable to Click on Options in Brand Page");
			}
		}
	}

	// #################### Keyword Ends ###################		

}
