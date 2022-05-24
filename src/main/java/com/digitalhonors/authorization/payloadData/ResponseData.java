package com.digitalhonors.authorization.payloadData;

public class ResponseData {

	// private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public ResponseData(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
