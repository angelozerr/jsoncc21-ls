package com.jsoncc.ls.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.lsp4j.Location;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.ReferenceContext;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.Node;

import com.jsoncc.ls.utils.JSONCCPositionUtility;

public class JSONCCReference {

	public List<? extends Location> findReferences(Node grammarFile, Position position, ReferenceContext context,
			CancelChecker cancelChecker) {
		Node node = JSONCCPositionUtility.findNodeAt(grammarFile, position);
		if (node == null) {
			return Collections.emptyList();
		}
//		if (!(node instanceof Identifier)) {
//			return Collections.emptyList();
//		}
//		Identifier identifier = (Identifier) node;
		List<Location> locations = new ArrayList<>();
//		JavaCCSearchUtils.searchReferencedIdentifiers(identifier, targetNode -> {
//			Range targetRange = JSONCCPositionUtility.toRange(targetNode);
//			locations.add(new Location(targetNode.getInputSource(), targetRange));
//		});
		return locations;
	}

}
