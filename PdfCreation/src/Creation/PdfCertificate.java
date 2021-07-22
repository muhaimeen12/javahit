/**
 * @Author		: Hasan Abdul Muhaimeen
 * @Date		: 19/07/21
 * @E-Mail		: abdulmuhaieen2000@gmail.com
 * @FileName	: PdfECertificate.java
 * @Description	: This file contains the Main Source Code to create a PDF using iText.ja Libraries
 * @Dependancies: This file Requires cert.jpeg image to create the PDF & to execute the program
 */
package Creation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

public class PdfCertificate{
	public static void main(String[] args) throws Exception {

		// Enter a valid Path with FileName
		String pdfName = "pdfECertificate.pdf";

		// Create a PDF
		createEmptyCertificatePdf();

		// Add Content to Certificate (Add Image & Content)
		addContentToCertificate(pdfName);

	}

	private static void createEmptyCertificatePdf() throws Exception {
		try {
			// 1. Create document
			Document document = new Document(PageSize.A4);
			// 2. Create PdfWriter
			PdfWriter.getInstance(document, new FileOutputStream("temp.pdf"));
			// 3. Open document
			document.open();
			// 4. Add content
			document.add(new Paragraph(" "));
			// 5. Close document
			document.close();
			System.out.println("Created Empty Certificate PDF");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private static void addContentToCertificate(String pdfName) throws Exception {
		Scanner input = new Scanner(System.in);
		try {
			// Create Reader Instance
			PdfReader pdfReader = new PdfReader("temp.pdf");
			// Create Stamper Instance
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(pdfName));
			// Set the Default Zoom-75%
			PdfDestination pdfDest = new PdfDestination(PdfDestination.XYZ, 0, pdfReader.getPageSize(1).getHeight(),
					0.75f);
			PdfAction action = PdfAction.gotoLocalPage(1, pdfDest, pdfStamper.getWriter());
			pdfStamper.getWriter().setOpenAction(action);
			// Create the Image Instance
			Image image = Image.getInstance("cert.jpeg");
			// Get all the Pages and Perform Operation on Particular Page
			for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
				if (i == 1) {

					// -------------------- Background Image ----------------
					// Add Background Image (put content under)
					PdfContentByte content = pdfStamper.getUnderContent(i);
					image.setAbsolutePosition(0, 0);
					image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
					content.addImage(image);

					// --------------- Header Data ---------------------
					String headerData = "This is Certificate is Proudly Awarded to";
					// Enable During Final Test (To get input from User)
//						System.out.print("Enter Header Data : ");
//						headerData = input.nextLine();
					setData(pdfStamper, headerData, BaseFont.TIMES_BOLDITALIC, 26, BaseColor.MAGENTA, 530, 520, 80,490);

					// ---------------Main Content ----------------------
					// Add Student Name
					String studentName = "Student name";
//						System.out.print("Enter Student Name (FirstName SurnNme): ");
//						studentName = input.nextLine();
//						studentName = "Mr. "+studentName;
					setData(pdfStamper, studentName, BaseFont.TIMES_BOLDITALIC, 34, BaseColor.BLUE, 530, 416, 80, 370);

					// ---------Middle Data (Reason of Certificate) ------------------
					// Prize Details & Standard Format of Data
					String midData = "Project on: PDF E-CERTIFICATE CREATION USING JAVA\n\n"
							+ "Successfylly Completed the project and submitted on-time"
							+ " and his participation very good. The programming language used for this"
							+ " project is \"JAVA\" and \"iText Library\" for creating and Generating the PDF E-Certificate.\n"
							+ "\nWe wish him goo luck for his future.....";
					// Enable During Final Test (To get input from User)
//						System.out.print("Enter the Main Content: : ");
//						midData = input.nextLine();

					setData(pdfStamper, midData, BaseFont.TIMES_BOLDITALIC, 14, BaseColor.GRAY, 530, 220, 70, 340);

					// ----------Footer Data -------------------

					// Add Current Date
					Date date = new Date();
					setData(pdfStamper, date.toString(), BaseFont.TIMES_BOLDITALIC, 16, BaseColor.RED, 200, 170, 50,100);
					setData(pdfStamper, "Acquired on", BaseFont.TIMES_BOLDITALIC, 14, BaseColor.DARK_GRAY, 200, 120, 50,95);

					// Add Name of the Head Person
					String nameOfHeadPerson = "Director name";
					String direc = "Director of Haaris Infotech Pvt. Ltd.";
					// Enable During Final Test (To get input from User)
//						System.out.print("Enter Head/Director Name : ");
//						nameOfHeadPerson = input.nextLine();
//						nameOfHeadPerson = "Mr. "+nameOfHeadPerson;
					setData(pdfStamper, nameOfHeadPerson, BaseFont.TIMES_BOLDITALIC, 18, BaseColor.GREEN, 360, 170, 575,100);
					setData(pdfStamper, direc, BaseFont.TIMES_BOLDITALIC, 12, BaseColor.DARK_GRAY, 360, 120, 575, 100);
				}

			}
			//pdfReader.close();
			pdfStamper.close();
			System.out.println("Successfully added content & created the PDF E-Certificate : " + pdfName);

		} catch (IOException e) {
			System.out.println("Exception while adding Content To Certificate");
			e.printStackTrace();
		}

	}

	private static void setData(PdfStamper pdfStamper, String data, String fontType, float fontSize,
			BaseColor fontColor, float lowerX, float lowerY, float upperX, float upperY) throws Exception {
		try {

			TextField textField = new TextField(pdfStamper.getWriter(), new Rectangle(lowerX, lowerY, upperX, upperY),"newTextField");
			textField.setOptions(TextField.MULTILINE | TextField.READ_ONLY);
			textField.setAlignment(Element.ALIGN_CENTER);
			textField.setTextColor(fontColor);
			BaseFont baseFont = BaseFont.createFont(fontType, BaseFont.WINANSI, BaseFont.EMBEDDED);
			textField.setFont(baseFont);
			textField.setFontSize(fontSize);
			textField.setText(data);
			// is no longer multiple-line
			pdfStamper.addAnnotation(textField.getTextField(), 1);
			System.out.println(data);
		} catch (Exception ex) {
			System.out.println("Exception while Setting Data: " + data);
			ex.printStackTrace();
		}

	}

}

