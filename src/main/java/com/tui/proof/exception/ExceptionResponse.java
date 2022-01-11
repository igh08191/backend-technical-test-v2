package com.tui.proof.exception;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
	private Date timeStamp;
	private String message;
	private String detail;
	private Map<String, String> validationErrors;

	public ExceptionResponse(Date timeStamp, String message, String detail) {
		this.timeStamp=timeStamp;
		this.message=message;
		this.detail=detail;
	}
}
