package com.adigo.converters;


import java.io.*;
//POI libraries to read Excel File
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.*;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfNumber;
import java.util.Iterator;
//itext libraries to write PDF file
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class EXCELtoPDFConverter {

	public class Rotate extends PdfPageEventHelper {
 
        protected PdfNumber orientation = PdfPage.LANDSCAPE;
 
        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }
 
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }  

	public static final String[] FILE_TYPES = new String[] { "xls", "xlsx" };


	public static void main(String[] args){
		try{
			EXCELtoPDFConverter con = new EXCELtoPDFConverter();
			con.convertExcel("Unified Portal - Awarness Campaign v1.xlsx");
			con.convertExcel("Source.xls");
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void convertExcel(String fileName) throws Exception{
    	FileInputStream input_document =  new FileInputStream(fileName);
        // Read workbook into HSSFWorkbook
        Workbook my_xls_workbook = null;
		if (fileName.toLowerCase().endsWith(FILE_TYPES[0])) {
			my_xls_workbook = new HSSFWorkbook(input_document);
		} else {
			my_xls_workbook = new XSSFWorkbook(input_document);
		}
        // Read worksheet into HSSFSheet
        Sheet my_worksheet = my_xls_workbook.getSheetAt(0); 
        // To iterate over the rows
        Iterator<Row> rowIterator = my_worksheet.iterator();
        //We will create output PDF document objects at this point
        Document iText_xls_2_pdf = new Document();
        iText_xls_2_pdf.setPageSize(PageSize.LETTER.rotate());
        PdfWriter writer = PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream("output/"+fileName+".pdf"));

        Rotate event = new Rotate();
        writer.setPageEvent(event);
        
        iText_xls_2_pdf.open();
        //event.setOrientation(PdfPage.LANDSCAPE);
        //we have two columns in the Excel sheet, so we create a PDF table with two columns
        //Note: There are ways to make this dynamic in nature, if you want to.
        PdfPTable my_table = null;
        //We will use the object below to dynamically add new data to the table
        PdfPCell table_cell;


        rowIterator = my_worksheet.iterator();

        int maxCellCount = 0;

        //Identify the size of biggest row
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next(); 
            Iterator<Cell> cellIterator = row.cellIterator();
	        int cellCount = 0;

            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next(); //Fetch CELL
                cellCount++;
            }
            if(cellCount > maxCellCount)
            	maxCellCount = cellCount;
        }

        my_table = new PdfPTable(maxCellCount);
        rowIterator = my_worksheet.iterator();

        while(rowIterator.hasNext()) {
            Row row = rowIterator.next(); 
            Iterator<Cell> cellIterator = row.cellIterator();

            for(int i = 0 ; i < maxCellCount ; i++){
            	Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
            	                table_cell=new PdfPCell(new Phrase(cell.toString()));
                //feel free to move the code below to suit to your needs
                my_table.addCell(table_cell);
            }

/*            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next(); //Fetch CELL
                //Push the data from Excel to PDF Cell
                table_cell=new PdfPCell(new Phrase(cell.toString()));
                //feel free to move the code below to suit to your needs
                my_table.addCell(table_cell);
                cellCount++;
                //next line
            }
*/            
        }



        //Finally add the table to PDF document
        iText_xls_2_pdf.add(my_table);                       
        iText_xls_2_pdf.close();                
        //we created our pdf file..
        input_document.close(); //close xls
	}
}