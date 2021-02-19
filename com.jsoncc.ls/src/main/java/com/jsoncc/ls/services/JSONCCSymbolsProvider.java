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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.DocumentSymbol;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.SymbolInformation;
import org.eclipse.lsp4j.SymbolKind;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.Node;

import com.jsoncc.ls.utils.JSONCCPositionUtility;

/**
 * Qute symbol provider.
 *
 */
class JSONCCSymbolsProvider {

	public List<DocumentSymbol> findDocumentSymbols(Node grammarFile, CancelChecker cancelChecker) {
		List<DocumentSymbol> symbols = new ArrayList<>();
		Node root = grammarFile; //.getRoot();
		if (root != null) {
			for (int i = 0; i < root.getChildCount(); i++) {
				Node child = root.getChild(i);
				findDocumentSymbols(child, symbols, grammarFile, cancelChecker);
			}
		}
		return symbols;
	}

	private void findDocumentSymbols(Node node, List<DocumentSymbol> symbols, Node grammarFile,
			CancelChecker cancelChecker) {
		if (!isNodeSymbol(node)) {
			return;
		}
		cancelChecker.checkCanceled();

		String name = nodeToName(node);
		Range selectionRange = getSymbolRange(node);

		Range range = selectionRange;
		List<DocumentSymbol> children = new ArrayList<>();
		DocumentSymbol symbol = new DocumentSymbol(name, getSymbolKind(node), range, selectionRange, null, children);
		symbols.add(symbol);

		if (node.getChildCount() > 0) {
			for (int i = 0; i < node.getChildCount(); i++) {
				Node child = node.getChild(i);
				findDocumentSymbols(child, children, grammarFile, cancelChecker);
			}
		}
	}

	private SymbolKind getSymbolKind(Node node) {
//		if (node instanceof Block) {
//			return SymbolKind.Module;
//		}
//		if (node instanceof Text) {
//			return SymbolKind.Constant;
//		}
//		if (node instanceof Interpolation) {
//			return SymbolKind.Function;
//		}
//		if (node instanceof Comment) {
//			return SymbolKind.Struct;
//		}
//		if (node instanceof UserSection) {
//			return SymbolKind.Method;
//		}
//		if (node instanceof IF) {
//			return SymbolKind.Event;
//		}
		return SymbolKind.Field;
	}

	private String nodeToName(Node node) {
		return node.getClass().getSimpleName(); // + ( node.isDirty() ? " (dirty)" : "");
	}

	private static Range getSymbolRange(Node node) {
		return JSONCCPositionUtility.toRange(node);
	}

	private boolean isNodeSymbol(Node node) {
		return true;
	}

	public List<SymbolInformation> findSymbolInformations(Node grammarFile, CancelChecker cancelChecker) {
		List<SymbolInformation> symbols = new ArrayList<>();
		Node root = grammarFile.getRoot();
		if (root != null) {

		}
		return symbols;
	}
}
