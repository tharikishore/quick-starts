package com.adigo.converters;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

import java.util.*;

// For ppt
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;//
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.POIXMLProperties.*;
import org.apache.poi.xslf.usermodel.*;
import java.awt.image.BufferedImage;

//For PDF
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;

//Using iText
import java.awt.geom.AffineTransform;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.awt.geom.Dimension;
import com.itextpdf.text.Image;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import java.awt.Graphics2D;
import java.awt.Color;
import com.itextpdf.text.Rectangle;
import java.awt.geom.Rectangle2D;

public class PPTtoPDFConverter{

	public static void main(String[] args){
		try{
			convertPPTToPDF("crops_in_Chembakolli.ppt",
			"crops_in_Chembakolli.pdf");
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}

	public static void convertPPTToPDF(String sourcepath, String destinationPath) throws Exception {
		String fileType = null;
		if(sourcepath.endsWith(".ppt"))
			fileType=".ppt";
		else if(sourcepath.endsWith(".pptx"))
			fileType=".pptx";

	    FileInputStream inputStream = new FileInputStream(new File(sourcepath));
	    double zoom = 1.1;
	    AffineTransform at = new AffineTransform();
	    at.setToScale(zoom, zoom);
	    com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document();
	    PdfWriter pdfWriter = PdfWriter.getInstance(pdfDocument, new FileOutputStream(destinationPath));
	    PdfPTable table = new PdfPTable(1);
	    pdfWriter.open();
	    pdfDocument.open();
	    java.awt.Dimension pgsize = null;
	    Image slideImage = null;
	    BufferedImage img = null;
	    if (fileType.equalsIgnoreCase(".ppt")) {
	        HSLFSlideShow ppt = new HSLFSlideShow(inputStream);
	        pgsize = ppt.getPageSize();
	        List<HSLFSlide> slide = ppt.getSlides();
	        pdfDocument.setPageSize(new Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
	        pdfWriter.open();
	        pdfDocument.open();
	        for (int i = 0; i < slide.size(); i++) {
	            img = new BufferedImage((int) Math.ceil(pgsize.width * zoom), (int) Math.ceil(pgsize.height * zoom), BufferedImage.TYPE_INT_RGB);
	            Graphics2D graphics = img.createGraphics();
	            graphics.setTransform(at);

	            graphics.setPaint(Color.white);
	            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
	            slide.get(i).draw(graphics);
	            graphics.getPaint();
	            slideImage = Image.getInstance(img, null);
	            table.addCell(new PdfPCell(slideImage, true));
	        }
	    }
	    if (fileType.equalsIgnoreCase(".pptx")) {
	        XMLSlideShow ppt = new XMLSlideShow(inputStream);
	        pgsize = ppt.getPageSize();
	        List<XSLFSlide> slide = ppt.getSlides();
	        pdfDocument.setPageSize(new Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
	        pdfWriter.open();
	        pdfDocument.open();
	        for (int i = 0; i < slide.size(); i++) {
	        	System.out.println("Saving Page "+i);
	            img = new BufferedImage((int) Math.ceil(pgsize.width * zoom), (int) Math.ceil(pgsize.height * zoom), BufferedImage.TYPE_INT_RGB);
	            Graphics2D graphics = img.createGraphics();
	            graphics.setTransform(at);

	            graphics.setPaint(Color.white);
	            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
	            slide.get(i).draw(graphics);
	            graphics.getPaint();
	            slideImage = Image.getInstance(img, null);
	            table.addCell(new PdfPCell(slideImage, true));
	        }
	    }
	    pdfDocument.add(table);
	    pdfDocument.close();
	    pdfWriter.close();
	    System.out.println("Powerpoint file converted to PDF successfully");
	}

}
