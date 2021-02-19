package com.jsoncc.ls.services;

import java.util.Arrays;
import java.util.List;

import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.TextEdit;
import org.parsers.jsonc.Node;

import com.jsoncc.ls.settings.SharedSettings;

public class JSONCCFormatter {

	public List<? extends TextEdit> format(Node grammarFile, Range range, SharedSettings settings) {
//		JavaFormatter formatter = new JavaFormatter();
//		String formattedContent = formatter.format(grammarFile);
//		TextEdit textEdit = new TextEdit(JSONCCPositionUtility.toRange(grammarFile), formattedContent);
		return Arrays.asList();
	}

}
