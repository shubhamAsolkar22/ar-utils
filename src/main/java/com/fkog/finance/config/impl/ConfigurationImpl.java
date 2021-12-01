package com.fkog.finance.config.impl;

import java.net.URI;

import com.fkog.finance.config.Configuration;
import com.fkog.finance.property.ExtractorType;
import com.fkog.finance.property.OutputType;

public class ConfigurationImpl implements Configuration {
	private final URI arFileURI;
	private final URI kwFileURI;
	private ExtractorType extractorType = ExtractorType.PageNumberExtractor;
	private OutputType outputType = OutputType.CONSOLE;

	public ConfigurationImpl(URI arFileURI, URI kwFileURI) {
		this.arFileURI = arFileURI;
		this.kwFileURI = kwFileURI;
	}

	public ConfigurationImpl(String arFileURIStr, String kwFileURIStr) {
		this.arFileURI = URI.create(arFileURIStr);
		this.kwFileURI = URI.create(kwFileURIStr);
	}

	@Override
	public URI getARFileURI() {
		return this.arFileURI;
	}

	@Override
	public URI getKeywordFileURI() {
		return this.kwFileURI;
	}
	@Override
	public ExtractorType getExtractorType() {
		return extractorType;
	}
	@Override
	public void setExtractorType(ExtractorType extractorType) {
		this.extractorType = extractorType;
	}
	@Override
	public OutputType getOutputType() {
		return outputType;
	}
	@Override
	public void setOutputType(OutputType outputType) {
		this.outputType = outputType;
	}

}
