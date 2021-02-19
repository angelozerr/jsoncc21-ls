/*******************************************************************************
* Copyright (c) 2019 Red Hat Inc. and others.
* All rights reserved. This program and the accompanying materials
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* Contributors:
*     Red Hat Inc. - initial API and implementation
*******************************************************************************/
package com.jsoncc.ls.settings;

import com.jsoncc.ls.utils.JSONUtility;

/**
 * Class to hold all settings from the client side.
 * 
 * 
 * This class is created through the deserialization of a JSON object. Each
 * internal setting must be represented by a class and have:
 * 
 * 1) A constructor with no parameters
 * 
 * 2) The JSON key/parent for the settings must have the same name as a
 * variable.
 * 
 * eg: {"symbols" : {...}, "validation" : {...}}
 * 
 */
public class JavaCCGeneralClientSettings {
	private JavaCCValidationSettings validation;

	/**
	 * Returns the validation settings.
	 * 
	 * @return the validation settings.
	 */
	public JavaCCValidationSettings getValidation() {
		return validation;
	}

	/**
	 * Set the validation settings.
	 * 
	 * @param validation the validation settings.
	 */
	public void setValidation(JavaCCValidationSettings validation) {
		this.validation = validation;
	}

	/**
	 * Returns the general settings from the given initialization options
	 * 
	 * @param initializationOptionsSettings the initialization options
	 * @return the general settings from the given initialization options
	 */
	public static JavaCCGeneralClientSettings getGeneralJavaCCSettings(Object initializationOptionsSettings) {
		return JSONUtility.toModel(initializationOptionsSettings, JavaCCGeneralClientSettings.class);
	}
}