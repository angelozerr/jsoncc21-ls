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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.Node;

import com.jsoncc.ls.ls.commons.BadLocationException;
import com.jsoncc.ls.ls.commons.snippets.ISnippetLineContext;
import com.jsoncc.ls.ls.commons.snippets.SnippetRegistry;
import com.jsoncc.ls.settings.JavaCCCompletionSettings;
import com.jsoncc.ls.settings.JavaCCFormattingOptions;
import com.jsoncc.ls.utils.JSONCCPositionUtility;

/**
 * The Qute completions
 * 
 * @author Angelo ZERR
 *
 */
class JSONCCCompletions {

	public class LineContext implements ISnippetLineContext {

		private final Node node;

		private final Position position;

		public LineContext(Node node, Position position) {
			this.node = node;
			this.position = position;
		}

		@Override
		public String getLineDelimiter() {
			return System.lineSeparator();
		}

		@Override
		public String getWhitespacesIndent() {
			return "";
		}

		@Override
		public Range getReplaceRange(String expr) throws BadLocationException {
			Position start = position;
			Position end = position;
			return new Range(start, end);
		}

	}

	private static final Logger LOGGER = Logger.getLogger(JSONCCCompletions.class.getName());
	private boolean snippetsLoaded;

	/**
	 * Returns completion list for the given position
	 * 
	 * @param template           the Qute template
	 * @param position           the position where completion was triggered
	 * @param completionSettings the completion settings.
	 * @param formattingSettings the formatting settings.
	 * @param cancelChecker      the cancel checker
	 * @return completion list for the given position
	 */
	public CompletionList doComplete(Node template, Position position,
			JavaCCCompletionSettings completionSettings, JavaCCFormattingOptions formattingSettings,
			CancelChecker cancelChecker) {
		CompletionList list = new CompletionList();
		Node node = JSONCCPositionUtility.findNodeAt(template, position);
		fillCompletionSnippets(node, position, list);
		if (node != null) {

		}
		return list;
	}

	private void fillCompletionSnippets(Node node, Position position, CompletionList list) {
		/*
		 * if (node == null || node instanceof Text) { initSnippets();
		 * ISnippetLineContext lineContext = new LineContext(node, position);
		 * Collection<CompletionItem> snippetItems =
		 * SnippetRegistry.getInstance().getCompletionItems(lineContext, true, context
		 * -> { if (!"qute".equals(context.getType())) { return false; } return true;
		 * }); if (!snippetItems.isEmpty()) { list.getItems().addAll(snippetItems); } }
		 */
	}

	private void initSnippets() {
		if (snippetsLoaded) {
			return;
		}
		try {
			try {
				SnippetRegistry.getInstance().load(JSONCCCompletions.class.getResourceAsStream("qute-snippets.json"));
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Error while loading Qute snippets", e);
			}
		} finally {
			snippetsLoaded = true;
		}

	}

}