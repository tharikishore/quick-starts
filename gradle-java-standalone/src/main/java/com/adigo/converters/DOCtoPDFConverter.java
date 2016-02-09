package com.adigo.converters;

import java.io.*;

import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.model.fields.FieldUpdater;

import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.ParserConfigurationException;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.text.pdf.PdfWriter;

import org.w3c.tidy.Tidy;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;

public class DOCtoPDFConverter {

	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("Usage: 'java DOCtoPDFConverter inputfilepath outputfilepath'");
			return;
		}

		try{
			fromDoc(args[0], args[1]);
			//fromDocX("docx.docx");
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}

	public static void writeToFile(String fileName, String content){
		FileOutputStream fop = null;
		File file;
		try {

			file = new File(fileName);
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void fromDocX(String inputfilepath) throws Exception {
		String outputfilepath = "output/"+inputfilepath+".pdf";
		
		// Font regex (optional)
		// Set regex if you want to restrict to some defined subset of fonts
		// Here we have to do this before calling createContent,
		// since that discovers fonts
		String regex = null;
		// Windows:
		// String
		// regex=".*(calibri|camb|cour|arial|symb|times|Times|zapf).*";
		//regex=".*(calibri|camb|cour|arial|times|comic|georgia|impact|LSANS|pala|tahoma|trebuc|verdana|symbol|webdings|wingding).*";
		// Mac
		// String
		// regex=".*(Courier New|Arial|Times New Roman|Comic Sans|Georgia|Impact|Lucida Console|Lucida Sans Unicode|Palatino Linotype|Tahoma|Trebuchet|Verdana|Symbol|Webdings|Wingdings|MS Sans Serif|MS Serif).*";
		PhysicalFonts.setRegex(regex);

		// Document loading (required)
		WordprocessingMLPackage wordMLPackage;
		if (inputfilepath==null) {
			// Create a docx
			System.out.println("No imput path passed, creating dummy document");
			 wordMLPackage = WordprocessingMLPackage.createPackage();
		} else {
			// Load .docx or Flat OPC .xml
			System.out.println("Loading file from " + inputfilepath);
			wordMLPackage = WordprocessingMLPackage.load(new java.io.File(inputfilepath));
		}
		
		// Refresh the values of DOCPROPERTY fields 
		FieldUpdater updater = new FieldUpdater(wordMLPackage);
		updater.update(true);
		
		// Set up font mapper (optional)
		Mapper fontMapper = new IdentityPlusMapper();
		wordMLPackage.setFontMapper(fontMapper);
		
		// .. example of mapping font Times New Roman which doesn't have certain Arabic glyphs
		// eg Glyph "ي" (0x64a, afii57450) not available in font "TimesNewRomanPS-ItalicMT".
		// eg Glyph "ج" (0x62c, afii57420) not available in font "TimesNewRomanPS-ItalicMT".
		// to a font which does
		PhysicalFont font 
				= PhysicalFonts.get("Arial Unicode MS"); 
			// make sure this is in your regex (if any)!!!
//		if (font!=null) {
//			fontMapper.put("Times New Roman", font);
//			fontMapper.put("Arial", font);
//		}
//		fontMapper.put("Libian SC Regular", PhysicalFonts.get("SimSun"));

		// FO exporter setup (required)
		// .. the FOSettings object
    	FOSettings foSettings = Docx4J.createFOSettings();
		/*if (saveFO) {
			foSettings.setFoDumpFile(new java.io.File(inputfilepath + ".fo"));
		}*/

		foSettings.setWmlPackage(wordMLPackage);
		
		// Document format: 
		// The default implementation of the FORenderer that uses Apache Fop will output
		// a PDF document if nothing is passed via 
		// foSettings.setApacheFopMime(apacheFopMime)
		// apacheFopMime can be any of the output formats defined in org.apache.fop.apps.MimeConstants eg org.apache.fop.apps.MimeConstants.MIME_FOP_IF or
		// FOSettings.INTERNAL_FO_MIME if you want the fo document as the result.
		//foSettings.setApacheFopMime(FOSettings.INTERNAL_FO_MIME);
		
		// exporter writes to an OutputStream.		

		OutputStream os = new java.io.FileOutputStream(outputfilepath);
    	
		// Specify whether PDF export uses XSLT or not to create the FO
		// (XSLT takes longer, but is more complete).
		
		// Don't care what type of exporter you use
		Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
		
		// Prefer the exporter, that uses a xsl transformation
		// Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
		
		// Prefer the exporter, that doesn't use a xsl transformation (= uses a visitor)
		// .. faster, but not yet at feature parity
		// Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_NONXSL);
    	
		System.out.println("Saved: " + outputfilepath);

		// Clean up, so any ObfuscatedFontPart temp files can be deleted 
		if (wordMLPackage.getMainDocumentPart().getFontTablePart()!=null) {
			wordMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
		}		
		// This would also do it, via finalize() methods
		updater = null;
		foSettings = null;
		wordMLPackage = null;	
    }

	private static void fromDoc(String fileName, String outputFile) {


		HWPFDocumentCore wordDocument;
		try {
			wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream(fileName));
			WordToHtmlConverter wordToHtmlConverter;
			try {
				wordToHtmlConverter = new WordToHtmlConverter(
	            DocumentBuilderFactory.newInstance().newDocumentBuilder()
				                    .newDocument());
				wordToHtmlConverter.processDocument(wordDocument);
			    Document htmlDocument = wordToHtmlConverter.getDocument();
			    ByteArrayOutputStream out = new ByteArrayOutputStream();
			    DOMSource domSource = new DOMSource(htmlDocument);
			    StreamResult streamResult = new StreamResult(out);

			    TransformerFactory tf = TransformerFactory.newInstance();
			    Transformer serializer;
				try {
					serializer = tf.newTransformer();
					serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
					serializer.setOutputProperty(OutputKeys.INDENT, "yes");
					serializer.setOutputProperty(OutputKeys.METHOD, "html");
					serializer.transform(domSource, streamResult);
					out.close();
 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    String result = new String(out.toByteArray());

				writeToFile(fileName+".tmp.html", result);

				Tidy tidy = new Tidy(); // obtain a new Tidy instance
				tidy.setXHTML(true); // set desired config options using tidy setters 
				tidy.setTidyMark(true);

				tidy.parse(new FileInputStream(fileName+".tmp.html"), new FileOutputStream(fileName+".html")); // run tidy, providing an input and output stream
				com.itextpdf.text.Document iTextdocument = new com.itextpdf.text.Document();
		        // step 2
		        PdfWriter writer = PdfWriter.getInstance(iTextdocument, new FileOutputStream(outputFile));
		        // step 3
		        iTextdocument.open();
		        // step 4
		        XMLWorkerHelper.getInstance().parseXHtml(writer, iTextdocument,
                new FileInputStream(fileName+".html")); 
				//step 5
				iTextdocument.close();
 
		        System.out.println( "PDF Created!" );

			    System.out.println(result);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			   
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}