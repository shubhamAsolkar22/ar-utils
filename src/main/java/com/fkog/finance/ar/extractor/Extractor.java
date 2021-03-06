package com.fkog.finance.ar.extractor;

import java.net.URI;
import java.util.List;
import java.util.Set;

import com.fkog.finance.ar.result.Result;

public interface Extractor<T> {

	void setKeywords(URI keywordsFileURI);


	void setThreshold(int threshold);

	Result<T> performExtract();

	void setKeywords(Set<String> keywords);

}
