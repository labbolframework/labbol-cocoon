/**
 * 
 */
package com.labbol.cocoon.msg;

/**
 * @author PengFei
 *
 */
public class JsonComboData {

	private String text;
	private String value;

	public JsonComboData() {
	}

	public JsonComboData(String text, String value) {
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
