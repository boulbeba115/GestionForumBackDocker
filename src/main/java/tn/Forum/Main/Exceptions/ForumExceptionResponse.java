package tn.Forum.Main.Exceptions;

public class ForumExceptionResponse {

	private String exceptionMessage;

	public ForumExceptionResponse(String exceptionMessage) {
		super();
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
