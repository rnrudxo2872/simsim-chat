package com.kucom.simsim.domain;

public class chatDTO {
	private String sender;
	private String message;
	private int status;
	private String sendDate;
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	
	@Override
	public String toString() {
		return "chatDTO [sender=" + sender + ", message=" + message + ", status=" + status + ", sendDate=" + sendDate
				+ "]";
	}
}
