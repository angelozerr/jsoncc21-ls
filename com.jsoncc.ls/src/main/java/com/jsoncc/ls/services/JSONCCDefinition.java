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

import java.util.Collections;
import java.util.List;

import org.eclipse.lsp4j.LocationLink;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.Node;

/**
 * Qute definition support.
 *
 */
class JSONCCDefinition {

	public List<? extends LocationLink> findDefinition(Node grammarFile, Position position,
			CancelChecker cancelChecker) {
		/*Node node = JSONCCPositionUtility.findNodeAt(grammarFile, position);
		if (node == null) {
			return Collections.emptyList();
		}
		if (!(node instanceof Identifier)) {
			return Collections.emptyList();
		}
		Identifier identifier = (Identifier) node;
		Identifier originIdentifier = JavaCCSearchUtils.searchOriginIdentifier(identifier);
		if (originIdentifier != null) {
			List<LocationLink> locations = new ArrayList<>();
			LocationLink location = JSONCCPositionUtility.createLocationLink(identifier, originIdentifier);
			locations.add(location);
			return locations;
		}*/
		return Collections.emptyList();
	}

}
