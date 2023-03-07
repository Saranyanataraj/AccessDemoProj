import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


def Browser = GlobalVariable.G_Browser


extentTest.log(LogStatus.PASS, 'Navigated to Acces Instance - '+GlobalVariable.G_BaseUrl)


if (Browser == 'Edge Chromium') {
	WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeChromium_Details_link'))

	WebUI.delay(3)

	WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeChromium_proceed_link'))

	WebUI.delay(3)
	WebUI.deleteAllCookies()
}
if (Browser == 'IE') {

	WebUI.click(findTestObject('Object Repository/GenericObjects/IE_Details_Link'))

	WebUI.delay(3)
	WebUI.waitForElementVisible(findTestObject('Object Repository/GenericObjects/EdgeProceedeLink'), 2)
	WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeProceedeLink'))

	WebUI.delay(3)
}
WebUI.setText(findTestObject('LoginPage/username_txtbx'), GlobalVariable.G_userName)

WebUI.setText(findTestObject('LoginPage/password_txtbx'), GlobalVariable.G_Password)


extentTest.log(LogStatus.PASS, 'Entered Creds - username - '+GlobalVariable.G_userName +' password - '+GlobalVariable.G_Password)

WebUI.click(findTestObject('LoginPage/login_btn'))
extentTest.log(LogStatus.PASS, 'Clicked on Login Button ')


def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('null'),
		10,extentTest, 'AltairAccess Logo ')

if (jobsTab) {
	WebUI.click(findTestObject('null'))
}

extentTest.log(LogStatus.PASS, 'Verified AltairAccess Logo post login ')
