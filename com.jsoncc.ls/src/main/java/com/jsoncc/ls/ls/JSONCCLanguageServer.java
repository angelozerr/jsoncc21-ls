/*******************************************************************************
* Copyright (c) 2020 Red Hat Inc. and others.
* All rights reserved. This program and the accompanying materials
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* Contributors:
*     Red Hat Inc. - initial API and implementation
*******************************************************************************/
package com.jsoncc.ls.ls;

import static com.jsoncc.ls.utils.VersionHelper.getVersion;
import static org.eclipse.lsp4j.jsonrpc.CompletableFutures.computeAsync;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.InitializedParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

import com.jsoncc.ls.ls.api.JavaCCLanguageClientAPI;
import com.jsoncc.ls.ls.api.JavaCCLanguageServerAPI;
import com.jsoncc.ls.ls.commons.ParentProcessWatcher.ProcessLanguageServer;
import com.jsoncc.ls.ls.commons.client.ExtendedClientCapabilities;
import com.jsoncc.ls.ls.commons.client.InitializationOptionsExtendedClientCapabilities;
import com.jsoncc.ls.services.JSONCCLanguageService;
import com.jsoncc.ls.settings.SharedSettings;
import com.jsoncc.ls.settings.capabilities.JavaCCCapabilityManager;
import com.jsoncc.ls.settings.capabilities.ServerCapabilitiesInitializer;

/**
 * JavaCC language server.
 *
 */
public class JSONCCLanguageServer implements LanguageServer, ProcessLanguageServer, JavaCCLanguageServerAPI {

	private static final Logger LOGGER = Logger.getLogger(JSONCCLanguageServer.class.getName());

	private final JSONCCLanguageService javaccLanguageService;
	private final JSONCCTextDocumentService textDocumentService;
	private final WorkspaceService workspaceService;

	private Integer parentProcessId;
	private JavaCCLanguageClientAPI languageClient;
	private JavaCCCapabilityManager capabilityManager;

	public JSONCCLanguageServer() {
		javaccLanguageService = new JSONCCLanguageService();
		textDocumentService = new JSONCCTextDocumentService(this, new SharedSettings());
		workspaceService = new JSONCCWorkspaceService(this);
	}

	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
		LOGGER.info("Initializing JavaCC server " + getVersion() + " with " + System.getProperty("java.home"));

		this.parentProcessId = params.getProcessId();

		ExtendedClientCapabilities extendedClientCapabilities = InitializationOptionsExtendedClientCapabilities
				.getExtendedClientCapabilities(params);
		capabilityManager.setClientCapabilities(params.getCapabilities(), extendedClientCapabilities);

		textDocumentService.updateClientCapabilities(params.getCapabilities());
		ServerCapabilities serverCapabilities = ServerCapabilitiesInitializer
				.getNonDynamicServerCapabilities(capabilityManager.getClientCapabilities());

		InitializeResult initializeResult = new InitializeResult(serverCapabilities);
		return CompletableFuture.completedFuture(initializeResult);
	}

	/*
	 * Registers all capabilities that do not support client side preferences to
	 * turn on/off
	 *
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.lsp4j.services.LanguageServer#initialized(org.eclipse.lsp4j.
	 * InitializedParams)
	 */
	@Override
	public void initialized(InitializedParams params) {
		capabilityManager.initializeCapabilities();
	}

	@Override
	public CompletableFuture<Object> shutdown() {
		return computeAsync(cc -> new Object());
	}

	@Override
	public void exit() {
		exit(0);
	}

	@Override
	public void exit(int exitCode) {
		System.exit(exitCode);
	}

	public TextDocumentService getTextDocumentService() {
		return this.textDocumentService;
	}

	public WorkspaceService getWorkspaceService() {
		return this.workspaceService;
	}

	public JavaCCLanguageClientAPI getLanguageClient() {
		return languageClient;
	}

	public JavaCCCapabilityManager getCapabilityManager() {
		return capabilityManager;
	}

	public void setClient(LanguageClient languageClient) {
		this.languageClient = (JavaCCLanguageClientAPI) languageClient;
		this.capabilityManager = new JavaCCCapabilityManager(languageClient);
	}

	@Override
	public long getParentProcessId() {
		return parentProcessId != null ? parentProcessId : 0;
	}

	public JSONCCLanguageService getJavaCCLanguageService() {
		return javaccLanguageService;
	}

}
