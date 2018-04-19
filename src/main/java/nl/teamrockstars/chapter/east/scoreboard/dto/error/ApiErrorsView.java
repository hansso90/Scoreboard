package nl.teamrockstars.chapter.east.scoreboard.dto.error;

import java.util.List;

public class ApiErrorsView {

	private List<ApiFieldError> fieldErrors;
	private List<ApiGlobalError> globalErrors;

	public ApiErrorsView(List<ApiFieldError> apiFieldErrors, List<ApiGlobalError> apiGlobalErrors) {
		this.fieldErrors = apiFieldErrors;
		this.globalErrors = apiGlobalErrors;
	}
	public List<ApiFieldError> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<ApiFieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	public List<ApiGlobalError> getGlobalErrors() {
		return globalErrors;
	}
	public void setGlobalErrors(List<ApiGlobalError> globalErrors) {
		this.globalErrors = globalErrors;
	}

}
