/**
 * 
 */
package com.labbol.cocoon.msg;

/**
 * @author PengFei
 */
public class JsonFormData<T> {

	private boolean success;

	private T data;

	public JsonFormData() {

	}

	public JsonFormData(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
