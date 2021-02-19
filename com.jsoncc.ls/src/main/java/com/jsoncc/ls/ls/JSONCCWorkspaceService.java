/*******************************************************************************
* Copyright (c) 2019 Red Hat Inc. and others.
* All rights reserved. This program and the accompanying materials
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* Contributors:
*     Red Hat Inc. - initial API and implementation
*******************************************************************************/
package com.jsoncc.ls.ls;

import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.services.WorkspaceService;

/**
 * Qute workspace service.
 *
 */
public class JSONCCWorkspaceService implements WorkspaceService {

	private final JSONCCLanguageServer quteLanguageServer;

	public JSONCCWorkspaceService(JSONCCLanguageServer quarkusLanguageServer) {
		this.quteLanguageServer = quarkusLanguageServer;
	}

	@Override
	public void didChangeConfiguration(DidChangeConfigurationParams params) {
		// quteLanguageServer.updateSettings(params.getSettings());
	}

	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {

	}

}
