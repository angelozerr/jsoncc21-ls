package com.jsoncc.ls.services;

import java.util.Collections;
import java.util.List;

import org.eclipse.lsp4j.DocumentLink;
import org.parsers.jsonc.Node;

class JSONCCDocumentLink {

	public List<DocumentLink> findDocumentLinks(Node grammarFile) {
		Node root = grammarFile.getRoot();
		if (root == null) {
			return Collections.emptyList();
		}
		/*List<GrammarInclusion> inclusions = root.childrenOfType(GrammarInclusion.class);
		if (inclusions != null) {
			List<DocumentLink> documentLinks = new ArrayList<>();
			for (GrammarInclusion inclusion : inclusions) {
				// Test if it's INCLUDE("JSON.javacc")
				StringLiteral include = getIncludeStringLiteral(inclusion);
				if (include != null) {
					// StringLiteral = "JSON.javacc"
					File includedFile = getIncludeFile(include);
					String target = toUri(includedFile).toString();
					Range range = JSONCCPositionUtility.toRange(include);
					DocumentLink link = new DocumentLink(range, target);
					documentLinks.add(link);
				}
			}
			return documentLinks;
		}*/
		return Collections.emptyList();
	}
}
