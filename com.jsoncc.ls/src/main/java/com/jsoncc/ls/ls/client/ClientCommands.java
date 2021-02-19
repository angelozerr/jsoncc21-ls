/*******************************************************************************
* Copyright (c) 2019-2020 Red Hat Inc. and others.
* All rights reserved. This program and the accompanying materials
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*     Red Hat Inc. - initial API and implementation
*******************************************************************************/
package com.jsoncc.ls.ls.client;

/**
 * Commonly used client commands
 * 
 * @author Angelo ZERR
 *
 */
public class ClientCommands {

	private ClientCommands() {
	}

	/**
	 * Show references
	 */
	public static final String SHOW_REFERENCES = "javacc.show.references";

	/**
	 * Open settings command.
	 * This custom command is sent to the client in order to have the client
	 * open its settings UI.
	 */
	public static final String OPEN_SETTINGS = "javacc.open.settings";

}