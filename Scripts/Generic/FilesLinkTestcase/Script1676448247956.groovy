import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus as LogStatus
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

/*WebUI.openBrowser('')
 WebUI.navigateToUrl(GlobalVariable.G_BaseUrl)
 WebUI.delay(3)
 WebUI.maximizeWindow()*/
def Browser = GlobalVariable.G_Browser

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

try {
    extentTest.log(LogStatus.PASS, 'Navigated to url - ' + GlobalVariable.G_BaseUrl)

    WebUI.setText(findTestObject('LoginPage/username_txtbx'), GlobalVariable.G_userName)

    extentTest.log(LogStatus.PASS, 'Entered Username  - ' + username)

    WebUI.setText(findTestObject('LoginPage/password_txtbx'), GlobalVariable.G_Password)

    extentTest.log(LogStatus.PASS, 'Entered Password  - ' + password)

    WebUI.click(findTestObject('LoginPage/login_btn'))

    extentTest.log(LogStatus.PASS, 'Clicked Login Button')

    WebUI.delay(2)

    switch (userChoice) {
        case 'Valid':
            def filesTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/GenericObjects/TitleLink_Files'), 
                10, extentTest, 'Files Link')

            //def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),
            //10)
            if (filesTab) {
                WebUI.click(findTestObject('Object Repository/GenericObjects/TitleLink_Files'))

                WebUI.verifyElementPresent(findTestObject('Object Repository/Files/Page_Files-Altair Access/tile_view_button'), 
                    10)
            }
            
            break
        case 'Invalid':
            def errUserName = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/LoginPage/ErrorMsg_Incorrect_Username_or_Password'), 
                2)

            if (errUserName) {
                extentTest.log(LogStatus.PASS, 'Error msg is displyed for Incorrect Username or Password')
            }
            
            break
        case 'Blank':
            def errBlank = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/LoginPage/ErrorMsg_Incorrect_Username_or_Password'), 
                2)

            if (errBlank) {
                extentTest.log(LogStatus.PASS, 'Error msg is displyed for Incorrect Username or Password')
            }
            
            break
    }
}
catch (Exception ex) {
    String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

    WebUI.takeScreenshot(screenShotPath)

    String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'

    extentTest.log(LogStatus.FAIL, ex)

    extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(p))

    KeywordUtil.markFailed('ERROR: ' + e)
} 
catch (StepErrorException e) {
    String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

    WebUI.takeScreenshot(screenShotPath)

    String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'

    extentTest.log(LogStatus.FAIL, ex)

    extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(p))

    KeywordUtil.markFailed('ERROR: ' + e)
} 
finally { 
    extent.endTest(extentTest)

    extent.flush()
}

WebUI.acceptAlert()

