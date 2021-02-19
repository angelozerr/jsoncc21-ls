package com.jsoncc.ls.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.lsp4j.CodeLens;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.Node;

import com.jsoncc.ls.settings.JavaCCCodeLensSettings;

public class JSONCCCodeLens {

	public List<? extends CodeLens> getCodeLens(Node grammarFile, JavaCCCodeLensSettings codeLensSettings,
			CancelChecker cancelChecker) {
		Node root = grammarFile; //.getRoot();
		if (root == null) {
			return Collections.emptyList();
		}
		boolean supportedByClient = true;
		List<CodeLens> lenses = new ArrayList<>();
		Map<String, CodeLens> cache = new HashMap<>();

		/*List<Node> children = root.children();
		for (Node child : children) {
			if (JavaCCSearchUtils.isProductionNode(child)) {
				Identifier identifier = child.firstChildOfType(Identifier.class);
				if (identifier != null) {
					Range range = JSONCCPositionUtility.toRange(child);
					CodeLens codeLens = new CodeLens(range);
					codeLens.setCommand(
							new ReferenceCommand(identifier.getInputSource(), range.getStart(), supportedByClient));
					cache.put(identifier.getImage(), codeLens);
					lenses.add(codeLens);
				}
			}
		}
		for (Node child : children) {
			for (Node child1 : child.children()) {
				upateCodelens(child1, cache);
			}
		}*/
		return lenses;
	}

	/*private void upateCodelens(Node node, Map<String, CodeLens> cache) {
		if (node instanceof Identifier) {
			if (!JavaCCSearchUtils.isProductionIdentifier((Identifier) node)) {
				Identifier identifier = (Identifier) node;
				CodeLens codeLens = cache.get(identifier.getImage());
				if (codeLens != null) {
					((ReferenceCommand) codeLens.getCommand()).increment();
				}
			}
		} else {
			for (Node child : node.children()) {
				upateCodelens(child, cache);
			}
		}
	}*/

}
