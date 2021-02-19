package com.jsoncc.ls.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.lsp4j.FoldingRange;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.Node;

import com.jsoncc.ls.settings.JavaCCFoldingSettings;

class JSONCCFoldings {

	public List<FoldingRange> getFoldingRanges(
			Node grammarFile, JavaCCFoldingSettings context,
			CancelChecker cancelChecker) {
		Node root = grammarFile.getRoot();
		if (root == null) {
			return Collections.emptyList();
		}
		List<FoldingRange> result = new ArrayList<>();
		List<Node> children = root.children();
		for (Node child : children) {
			int startLine = child.getBeginLine() - 1;
			int endLine = child.getEndLine() - 1;
			if (startLine < endLine) {
				FoldingRange foldingRange = new FoldingRange(startLine, endLine);
				result.add(foldingRange);
			}
		}
		return result;
	}

}
