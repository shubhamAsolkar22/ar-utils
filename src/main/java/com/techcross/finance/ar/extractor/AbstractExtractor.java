package com.techcross.finance.ar.extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public abstract class AbstractExtractor<T> implements Extractor<T> {
	
	private static Logger LOGGER = Logger.getLogger(AbstractExtractor.class);
	
	protected URI dataFileURI;
	protected URI keywordsFileURI;
	protected Set<String> keywords;
	protected int threshold = 0;

	public AbstractExtractor(URI dataFileURI) {
		this.dataFileURI = dataFileURI;
	}

	@Override
	public void setKeywords(URI keywordsFileURI) {
		this.keywordsFileURI = keywordsFileURI;
	}

	@Override
	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}

	protected Set<String> getKeywords() {
		this.keywords = new HashSet<String>();
		if (this.keywordsFileURI != null) {
			File f = new File(this.keywordsFileURI);
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(f))) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					keywords.add(line.replaceAll("\\r?\\n", ""));
				}
			} catch (Exception e) {
				LOGGER.error("getKeywords() failed",e);
			}
		}
		return keywords;

	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
}
