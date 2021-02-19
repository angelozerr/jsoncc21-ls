/*******************************************************************************
* Copyright (c) 2020 Red Hat Inc. and others.
* All rights reserved. This program and the accompanying materials
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*     Red Hat Inc. - initial API and implementation
*******************************************************************************/
package com.jsoncc.ls.services;

import java.util.List;

import org.eclipse.lsp4j.CodeLens;
import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DocumentHighlight;
import org.eclipse.lsp4j.DocumentLink;
import org.eclipse.lsp4j.DocumentSymbol;
import org.eclipse.lsp4j.FoldingRange;
import org.eclipse.lsp4j.Location;
import org.eclipse.lsp4j.LocationLink;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.ReferenceContext;
import org.eclipse.lsp4j.SymbolInformation;
import org.eclipse.lsp4j.TextEdit;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.Node;

import com.jsoncc.ls.settings.JavaCCCodeLensSettings;
import com.jsoncc.ls.settings.JavaCCCompletionSettings;
import com.jsoncc.ls.settings.JavaCCFoldingSettings;
import com.jsoncc.ls.settings.JavaCCFormattingOptions;
import com.jsoncc.ls.settings.JavaCCValidationSettings;
import com.jsoncc.ls.settings.SharedSettings;

/**
 * The JavaCC language service.
 * 
 * @author Angelo ZERR
 *
 */
public class JSONCCLanguageService {

	private final JSONCCCompletions completions;
	private final JSONCCCodeLens codelens;
	private final JSONCCHighlighting highlighting;
	private final JSONCCDefinition definition;
	private final JSONCCFormatter formatter;
	private final JSONCCReference references;
	private final JSONCCSymbolsProvider symbolsProvider;
	private final JSONCCDiagnostics diagnostics;
	private final JSONCCFoldings foldings;
	private final JSONCCDocumentLink documentLink;

	public JSONCCLanguageService() {
		this.completions = new JSONCCCompletions();
		this.codelens = new JSONCCCodeLens();
		this.highlighting = new JSONCCHighlighting();
		this.definition = new JSONCCDefinition();
		this.formatter = new JSONCCFormatter();
		this.references = new JSONCCReference();
		this.symbolsProvider = new JSONCCSymbolsProvider();
		this.diagnostics = new JSONCCDiagnostics();
		this.foldings = new JSONCCFoldings();
		this.documentLink = new JSONCCDocumentLink();
	}

	/**
	 * Returns completion list for the given position
	 * 
	 * @param JavaCCParser       the Qute JavaCCParser
	 * @param position           the position where completion was triggered
	 * @param completionSettings the completion settings.
	 * @param formattingSettings the formatting settings.
	 * @param cancelChecker      the cancel checker
	 * @return completion list for the given position
	 */
	public CompletionList doComplete(Node grammarFile, Position position,
			JavaCCCompletionSettings completionSettings, JavaCCFormattingOptions formattingSettings,
			CancelChecker cancelChecker) {
		return completions.doComplete(grammarFile, position, completionSettings, formattingSettings, cancelChecker);
	}

	public List<DocumentHighlight> findDocumentHighlights(Node grammarFile, Position position,
			CancelChecker cancelChecker) {
		return highlighting.findDocumentHighlights(grammarFile, position, cancelChecker);
	}

	public List<? extends LocationLink> findDefinition(Node grammarFile, Position position,
			CancelChecker cancelChecker) {
		return definition.findDefinition(grammarFile, position, cancelChecker);
	}

	public List<DocumentSymbol> findDocumentSymbols(Node grammarFile, CancelChecker cancelChecker) {
		return symbolsProvider.findDocumentSymbols(grammarFile, cancelChecker);
	}

	public List<SymbolInformation> findSymbolInformations(Node grammarFile, CancelChecker cancelChecker) {
		return symbolsProvider.findSymbolInformations(grammarFile, cancelChecker);
	}

	/**
	 * Validate the given JavaCC <code>grammarFile</code>.
	 * 
	 * @param grammarFile        the grammar file.
	 * @param validationSettings the validation settings.
	 * @param cancelChecker      the cancel checker.
	 * @return the result of the validation.
	 */
	public List<Diagnostic> doDiagnostics(Node grammarFile, JavaCCValidationSettings validationSettings,
			CancelChecker cancelChecker) {
		return diagnostics.doDiagnostics(grammarFile, validationSettings, cancelChecker);
	}

	public List<? extends CodeLens> getCodeLens(Node grammarFile, JavaCCCodeLensSettings codeLensSettings,
			CancelChecker cancelChecker) {
		return codelens.getCodeLens(grammarFile, codeLensSettings, cancelChecker);
	}

	public List<? extends Location> findReferences(Node grammarFile, Position position, ReferenceContext context,
			CancelChecker cancelChecker) {
		return references.findReferences(grammarFile, position, context, cancelChecker);
	}

	public List<FoldingRange> getFoldingRanges(Node grammarFile, JavaCCFoldingSettings context,
			CancelChecker cancelChecker) {
		return foldings.getFoldingRanges(grammarFile, context, cancelChecker);
	}

	public List<DocumentLink> findDocumentLinks(Node grammarFile) {
		return documentLink.findDocumentLinks(grammarFile);
	}

	public List<? extends TextEdit> format(Node grammarFile, Range range, SharedSettings settings) {
		return formatter.format(grammarFile, range, settings);
	}
}
