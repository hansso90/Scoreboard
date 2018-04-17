package nl.teamrockstars.chapter.east.scoreboard.dto.error;

public class ApiFieldError {

	private String field;
	private String code;
	private Object rejectedValue;

	public ApiFieldError(String field, String code, Object rejectedValue) {
		this.field = field;
		this.code = code;
		this.rejectedValue = rejectedValue;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getRejectedValue() {
		return rejectedValue;
	}
	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

}
