/**
 * 
 */
package com.labbol.cocoon.msg;

import dream.first.extjs.base.msg.DreamFirstExtjsJsonMsg;

/**
 * @author PengFei
 */
public class JsonMsg extends DreamFirstExtjsJsonMsg {

	public JsonMsg(boolean success) {
		super(success);
	}

	public JsonMsg(boolean success, String msg) {
		super(success, msg);
	}

}