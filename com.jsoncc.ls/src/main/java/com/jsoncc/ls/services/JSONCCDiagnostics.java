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

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.Node;

import com.jsoncc.ls.settings.JavaCCValidationSettings;

/**
 * JavaCC diagnostics support.
 *
 */
class JSONCCDiagnostics {

	/**
	 * Validate the given JavaCC <code>grammarFile</code>.
	 * 
	 * @param grammarFile        the JavaCC grammar file.
	 * @param validationSettings the validation settings.
	 * @param cancelChecker      the cancel checker.
	 * @return the result of the validation.
	 */
	public List<Diagnostic> doDiagnostics(Node grammarFile, JavaCCValidationSettings validationSettings,
			CancelChecker cancelChecker) {
		if (validationSettings == null) {
			validationSettings = JavaCCValidationSettings.DEFAULT;
		}
		//LSPJavaCCErrorReporter reporter = (LSPJavaCCErrorReporter) grammarFile.getGrammar().getReporter();
		//return reporter.getDiagnostics();
		return Collections.emptyList();
	}

}
