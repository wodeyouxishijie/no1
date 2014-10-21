package com.doorcii.ad.utils.upload.files;

public class UploadResult {
	
	private boolean success;
	
	private String msg;
	
	private String url;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UploadResult [success=" + success + ", msg=" + msg + ", url="
				+ url + "]";
	}
	
}
