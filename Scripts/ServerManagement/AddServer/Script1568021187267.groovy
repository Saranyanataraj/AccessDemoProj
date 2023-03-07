import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil
import junit.framework.Assert


WebUI.callTestCase(findTestCase('Obsolete/Login'), [('username') : GlobalVariable.G_AdminUser, ('password') : GlobalVariable.G_AdminPasswd], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('ServerManagement/Icon_ServerSettings'))

WebUI.click(findTestObject('ServerManagement/button_AddServer'))

WebUI.delay(2)

WebUI.setText(findTestObject('ServerManagement/TxtBx_Server Name'), 'Server92')

WebUI.setText(findTestObject('ServerManagement/TxtBx_URL'), 'https://172.16.80.12:5243/pas')

WebUI.setText(findTestObject('ServerManagement/TxtBx_UserName'), 'pbsadmin')

WebUI.setText(findTestObject('ServerManagement/TxtBx_Password'), 'pbsadmin')

WebUI.setText(findTestObject('ServerManagement/TxtBx_RootDir'), '/stage')

WebUI.click(findTestObject('ServerManagement/button_Add'))

WebUI.verifyElementPresent(findTestObject('null'), 10)

WebUI.click(findTestObject('ServerManagement/button_Done'))

def isNotificationPresent=WebUI.verifyElementPresent(findTestObject('ServerManagement/div_Sever92'), 5)

Assert.assertTrue(isNotificationPresent)

if(isNotificationPresent)
{
	KeywordUtil.markPassed("Server Registered")
}
else
{
	KeywordUtil.markFailed("Registeration Failed")
}