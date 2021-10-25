package com.techcross.finance;

import java.net.URI;
import java.net.URISyntaxException;

import com.techcross.finance.ar.extractor.pageNumber.PageNumberExtractor;
import com.techcross.finance.ar.result.pageNumber.PageNumberResult;

public class App {
	public static void main(String[] args) throws URISyntaxException {
		PageNumberExtractor pne = new PageNumberExtractor(
				URI.create("file:///D:/shubham_files/stocks/biscuits/britania/AR2020-21.pdf"));
		pne.setKeywords(App.class.getClassLoader().getResource("balanceSheetKW.txt").toURI());
		pne.setThreshold(5);

		PageNumberResult pnr = (PageNumberResult) pne.performExtract();
		
		System.out.println(pnr.getResultValue());

	}
}
