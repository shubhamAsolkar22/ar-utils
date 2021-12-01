package com.fkog.finance.ar.result.pageNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fkog.finance.ar.result.Result;

public class PageNumberResult implements Result<List<Integer>> {
	private final List<Integer> extractedPageNumbers;

	public PageNumberResult(List<Integer> extractedPageNumbers) {
		this.extractedPageNumbers = extractedPageNumbers;
	}

	@Override
	public List<Integer> getResultValue() {
		return extractedPageNumbers == null ? Collections.EMPTY_LIST : extractedPageNumbers;
	}

}
