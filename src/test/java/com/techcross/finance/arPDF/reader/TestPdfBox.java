package com.techcross.finance.arPDF.reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * @author shubham asolkar 
 *
 */
public class TestPdfBox {
	
	private static final Set<String> BALANCE_SHEET_KEYWORDS = new HashSet<String>();
	
	/*TODO: load these keywords from txt file stored in project resources*/
	static {
		BALANCE_SHEET_KEYWORDS.add("non-current assets");
		BALANCE_SHEET_KEYWORDS.add("non-current liabilities");
		BALANCE_SHEET_KEYWORDS.add("current assets");
		BALANCE_SHEET_KEYWORDS.add("current liabilities");
		BALANCE_SHEET_KEYWORDS.add("consolidated balance sheet");
		BALANCE_SHEET_KEYWORDS.add("property, plant and equipment");
		BALANCE_SHEET_KEYWORDS.add("inventories");
		
	}
	private static final int HIT_THRESHOLD = 4;

	public static void main(String[] args) throws IOException {
		InputStream is = TestPdfBox.class.getClassLoader().getResourceAsStream("AR2020-21.pdf");// new File("D:\\shubham_files\\coding\\development\\javaProjects\\ws22_10_2021\\ar-utils\\src\\test\\resources\\AR2020-21.pdf");
		PDDocument pdoc = PDDocument.load(is);
		// Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = new PDFTextStripper();
		// Retrieving text from PDF document
//		String text = pdfStripper.getText(document);
//		System.out.println(text);
		// Closing the document
		
		for(int pageNumber = 1; pageNumber < pdoc.getNumberOfPages(); pageNumber++){

		    PDFTextStripper s = new PDFTextStripper();
		    s.setStartPage(pageNumber);
		    s.setEndPage(pageNumber);
		    String contents = s.getText(pdoc);
		    String pageContents = contents.toLowerCase();
		    Set<String> balanceSheetKWClone = new HashSet<String>(BALANCE_SHEET_KEYWORDS);
		    
		    for(String kw : BALANCE_SHEET_KEYWORDS) {
		    	if(pageContents.contains(kw))
		    		balanceSheetKWClone.remove(kw);
		    }
		    
		    int hitCount = BALANCE_SHEET_KEYWORDS.size()-balanceSheetKWClone.size();
		    
//		    System.out.println("Hit count"+hitCount);
		    
		    if(hitCount>=HIT_THRESHOLD){
		    	System.err.println(pageNumber);
		    }
		}
		
		pdoc.close();
	}
}
