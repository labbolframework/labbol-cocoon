/**
 * 
 */
package com.labbol.cocoon.login;

import com.labbol.core.exception.RequestException;

/**
 * @author PengFei
 */
public class LoginException extends RequestException {

	private static final long serialVersionUID = -536575856258902835L;

	public LoginException(int status) {
		super(status);
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String message, int status) {
		super(status, message);
	}

}
