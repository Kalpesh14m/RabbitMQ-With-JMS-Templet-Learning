package com.bunny.model;

public class Response {
	private String msg;
	private int statusCode;
	private Object data;

	public Response() {
	}

	public Response(String msg, int statusCode) {
		this.msg = msg;
		this.statusCode = statusCode;
	}

	public Response(String msg, int statusCode, Object data) {
		this.msg = msg;
		this.statusCode = statusCode;
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
