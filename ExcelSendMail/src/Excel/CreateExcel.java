/**
 * @Author		: Hasan Abdul Muhaimeen
 * @Date		: 17/07/21
 * @E-Mail		: abdulmuhaimeen2000@gmail.com
 * @FileName	: SendEmail.java
 * @Description	: This file will validate, Get UserName from E-mail and Send E-Mail with Attachment
 * @Dependancies: This file Requires SendExcelAttachmentByMail.java to execute the program
 */


package Excel;

import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class CreateExcel {
	public static String[] rowHeads;

	public static void createExcel(String fileName) {
		try {
			System.out.println("-------- Creating an Excel Sheet ----------");

			// creating an instance of HSSFWorkbook class
			HSSFWorkbook workbook = new HSSFWorkbook();
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheet = workbook.createSheet("StudentMarksReport");
			// creating the 0th row using the createRow() method
			HSSFRow rowhead = sheet.createRow((short) 0);

			Scanner input = new Scanner(System.in);
			int numberOdColoumns = 0;
			int numberOfRows = 0;
			
			System.out.print("Enter Number of Coloumns: ");
			numberOdColoumns = input.nextInt();
			
			System.out.print("Enter Number of Rows: ");
			numberOfRows = input.nextInt();
			
			//Create Array of Strings with Coloumns length
			rowHeads = new String[numberOdColoumns];

			// Create Head Row (No.of Coloumns)
			createRowHead(sheet, numberOdColoumns);

			
			//Create Number of Rows and Enter Data
			for (int cnt = 0; cnt < numberOfRows; cnt++) {
				createRow(sheet, cnt);
			}
			FileOutputStream fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			// closing the Stream
			fileOut.close();
			// closing the workbook
			workbook.close();
			// prints the message on the console
			System.out.println("Successfully Created the Excel: "+fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createRow(HSSFSheet sheet, int rowNumber) throws Exception {
		String data = "";
		Scanner input = new Scanner(System.in);
		try {
			rowNumber = rowNumber+1;	
			HSSFRow row = sheet.createRow((short) rowNumber);	
			row.createCell(0).setCellValue(rowNumber);	
			for (int i = 0; i < rowHeads.length; i++) {
				// inserting data in the row by row
//				if (i == 0) {
//					row.createCell(i).setCellValue(rowNumber );
//					continue;
//				}
				System.out.print("Enter Data for " + rowHeads[i]+": ");
				data = input.nextLine();
				row.createCell(i+1).setCellValue(data);
			}
			System.out.println("Successfully Created row no: "+rowNumber);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void createRowHead(HSSFSheet sheet, int numberOdColoumns) throws Exception {
		Scanner input = new Scanner(System.in);
		try {
			HSSFRow row = sheet.createRow((short) 0);
			System.out.println("------- Note: S.No Coloumn was creating as By default --------");
			row.createCell(0).setCellValue("S.No");
			for (int i = 0; i < numberOdColoumns; i++) {
				// inserting data in the 0th row (Coloumns)
				System.out.print("Enter Data into Coloumn-" + (i+1)+" : ");
				rowHeads[i] = input.nextLine();
				row.createCell(i+1).setCellValue(rowHeads[i]);
			}
			System.out.println("Successfully Created Head row");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}