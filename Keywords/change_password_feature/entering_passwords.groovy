package change_password_feature

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

public class entering_passwords {
	@Keyword
	def clickonchangepassword(String oldpasswd, String newpassword, String confirmpassword,extentTest ){

		//click on the user menu
		WebUI.click(findTestObject('Object Repository/change_password/unityuser'))

		//click on the change password option
		WebUI.click(findTestObject('Object Repository/change_password/changepass_menu'))

		extentTest.log(LogStatus.PASS, 'Clicked on the change password icon  ')

		//verify the title
		println(WebUI.verifyElementPresent(findTestObject('Object Repository/change_password/title'), 20))

		String title = WebUI.getText(findTestObject('Object Repository/change_password/title'))
		def returnvalue= WebUI.verifyMatch(title, "Change Password", true)
		if(returnvalue)
			extentTest.log(LogStatus.PASS, ('Verify the Title ' + title) + 'is present')
		else
			extentTest.log(LogStatus.FAIL, ('Verify the Title::  ' + title) + 'is not present')

		//set the old password
		WebUI.setText(findTestObject('Object Repository/change_password/oldpasswd'), oldpasswd)


		WebUI.delay(1)

		WebUI.setText(findTestObject('Object Repository/change_password/newpasswd'),newpassword )

		WebUI.delay(1)

		WebUI.setText(findTestObject('Object Repository/change_password/confrmpasswd'), confirmpassword)
		WebUI.delay(1)

		WebUI.delay(2)

		extentTest.log(LogStatus.PASS, 'Entered oldpassword::' + oldpasswd)

		extentTest.log(LogStatus.PASS, 'Entered new password::' + newpassword)
		extentTest.log(LogStatus.PASS, 'Entered Confirm password::' + confirmpassword)

	}
	@Keyword
	def validatemessages(String message, String msg2, String msg3,extentTest ){
		//verify the error message
		TestObject error_msg1 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Notificactions/Notification_Item'),
				'text', 'equals', message, true)

		TestObject error_msg2 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Notificactions/Notification_Item'),
				'text', 'equals', msg2, true)

		TestObject error_msg3 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Notificactions/Notification_Item'),
				'text', 'equals', msg3, true)

		println(WebUI.verifyElementPresent(error_msg1, 4))

		String errormesage = WebUI.getText(error_msg1)

		extentTest.log(LogStatus.PASS, 'Error message 1:: ' + message)

		extentTest.log(LogStatus.PASS, 'Error messages 2::   ' + msg2)

		extentTest.log(LogStatus.PASS, 'Error messages 3::  ' + msg3)
	}
}


