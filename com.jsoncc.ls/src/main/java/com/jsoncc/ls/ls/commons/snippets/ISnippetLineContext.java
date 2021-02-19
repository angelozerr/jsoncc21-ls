package com.jsoncc.ls.ls.commons.snippets;

import org.eclipse.lsp4j.Range;

import com.jsoncc.ls.ls.commons.BadLocationException;

public interface ISnippetLineContext {

	String getLineDelimiter();

	String getWhitespacesIndent();

	Range getReplaceRange(String expr) throws BadLocationException;

}
