package com.fkog.finance.config;

import java.net.URI;

import com.fkog.finance.property.ExtractorType;
import com.fkog.finance.property.OutputType;

public interface Configuration {
	URI getARFileURI();
	URI getKeywordFileURI();
	ExtractorType getExtractorType();
	void setExtractorType(ExtractorType extractorType);
	OutputType getOutputType();
	void setOutputType(OutputType outputType);
	
}
