/**
 * @Author		: Hasan Abdul Muhaimeen
 * @Date		: 19/07/21
 * @E-Mail		: abdulmuhaimeen2000@gmail.com
 * @FileName	: SendExcelAttachmentByMail.java
 * @Description	: This file contains the Main Source Code
 * @Dependancies: This file Requires SendEmail.java & CreateExcel to execute the program
 */

package Excel;

public class SendExcelAttachmentByMail {

	public static void main(String[] args) {
		String subject ="Greetings From Haaris Infotech Pvt. Ltd. !!!";
		String textBody = "Greetings To You.\n\n"
				+ "We're Welcoming You!!.\n"
				+ "Kindly check the attchment of your Results data\n\n"
				+ "And For More information Join our Github and Connect/Follow us on LinkedIN !!!!\n"
				+ "Our GitHub URL: https://github.com/TahirShaik\n"
				+ "LinkedIN URL: https://www.linkedin.com/in/tahir1920 \n"
				+ "Visit Our Portfolio: https://tahirshaik.github.io/WebDevlopment-/FirstPortfoliowebDesign/index.html"
				+ "\n\n\nThanks & Regards,\nTahir Shaik,\nE-Mail: tahirsk1920@gmail.com ,\n"
				+ "Phone No: +91-9177327808.";
	
		String fromMail = "muhaimeen227@gmail.com";
		String password = "812617106013";
		String toMail = "abdulmuhaimeen2000@gmail.com";
		// declare file name to be create
		String fileName = "ProgressReport.xlsx";
		//Create Excel File
		CreateExcel.createExcel(fileName);
		//Validate E-Mail
		if(SendEmail.isValidEmailAddress(toMail)==false) {
			System.out.println("Invaid To E-Mail");
		}
		//Get Username
		String userName = SendEmail.getUserName(toMail);
		//Send E-mail by attaching the Excel file
		SendEmail.sendFromGmail(fromMail,password,toMail,subject,"Dear Mr."+userName+",\n"+textBody, fileName);
		
		
	}
}
