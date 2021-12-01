package com.fkog.finance;

import java.net.URI;
import java.net.URISyntaxException;

import com.fkog.finance.ar.extractor.pageNumber.PageNumberExtractor;
import com.fkog.finance.ar.result.pageNumber.PageNumberResult;
import com.fkog.finance.config.Configuration;
import com.fkog.finance.config.impl.ConfigurationImpl;
import com.fkog.finance.property.ExtractorType;
import com.fkog.finance.property.OutputType;

public class App {
	public static void main(String[] args) {
		if (args.length < 2)
			throw new IllegalArgumentException(
					"Must pass atleast 2 parameters\n 1. AR file path \n 2. Keyword filePath ");
		Configuration config = new ConfigurationImpl(args[0], args[1]);
		if (args.length > 2) {
			for (int i = 2; i < args.length; i++) {
				if ("-extType".equals(args[i])) {
					i++;
					config.setExtractorType(ExtractorType.valueOf(args[i]));
				} else if ("-outputType".equals(args[i])) {
					i++;
					config.setOutputType(OutputType.valueOf(args[i]));
				}
			}
		}

	}

	public static void main2(String[] args) throws URISyntaxException {
		PageNumberExtractor pne = new PageNumberExtractor(
				URI.create("file:///D:/shubham_files/stocks/biscuits/britania/AR2020-21.pdf"));
		pne.setKeywords(App.class.getClassLoader().getResource("balanceSheetKW.txt").toURI());
		pne.setThreshold(5);

		PageNumberResult pnr = (PageNumberResult) pne.performExtract();

		System.out.println(pnr.getResultValue());

	}
}
