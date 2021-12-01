package com.fkog.finance.ar.extractor.pageNumber;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.fkog.finance.ar.extractor.AbstractExtractor;
import com.fkog.finance.ar.result.Result;
import com.fkog.finance.ar.result.pageNumber.PageNumberResult;

public class PageNumberExtractor extends AbstractExtractor<List<Integer>> {

	private static Logger LOGGER = Logger.getLogger(PageNumberExtractor.class);

	public PageNumberExtractor(URI dataFileURI) {
		super(dataFileURI);
	}

	public Result performExtract() {
		List<Integer> extractedPageNumbers = new ArrayList<>();
		try {
			File f = new File(this.dataFileURI);
			PDDocument pdoc = PDDocument.load(f);

			for (int pageNumber = 1; pageNumber < pdoc.getNumberOfPages(); pageNumber++) {

				PDFTextStripper s = new PDFTextStripper();
				s.setStartPage(pageNumber);
				s.setEndPage(pageNumber);
				String contents = s.getText(pdoc);
				String pageContents = contents.toLowerCase();
				Set<String> balanceSheetKWClone = new HashSet<String>(getKeywords());

				for (String kw : getKeywords()) {
					if (pageContents.contains(kw))
						balanceSheetKWClone.remove(kw);
				}

				int hitCount = getKeywords().size() - balanceSheetKWClone.size();

//		    System.out.println("Hit count"+hitCount);

				if (hitCount >= threshold) {
					extractedPageNumbers.add(pageNumber);
				}
			}

			pdoc.close();
		} catch (IOException e) {
			LOGGER.error("performExtract() failed", e);
		}
		return new PageNumberResult(extractedPageNumbers);
	}

}
