import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus as LogStatus
import internal.GlobalVariable as GlobalVariable

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
            def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'), 
                10, extentTest, 'Jobs Link')

            //def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),
            //10)
            if (jobsTab) {
                WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

                WebUI.verifyElementPresent(findTestObject('Object Repository/Files/Page_Jobs-Altair Access/Shell Script App'), 
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

