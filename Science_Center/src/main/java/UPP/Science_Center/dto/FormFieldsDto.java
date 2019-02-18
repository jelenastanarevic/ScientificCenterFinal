package UPP.Science_Center.dto;

import java.io.Serializable;
import java.util.List;

import org.camunda.bpm.engine.form.FormField;

public class FormFieldsDto implements Serializable{
	
	String taskId;
	List<FormField> formFields;
	String processInstanceId;
	
	
	public FormFieldsDto(){}
	
	
	public FormFieldsDto(String taskId, String processInstanceId,List<FormField> formFields) {
		super();
		this.taskId = taskId;
		this.formFields = formFields;
		this.processInstanceId = processInstanceId;
	}


	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<FormField> getFormField() {
		return formFields;
	}
	public void setFormField(List<FormField> formFields) {
		this.formFields = formFields;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	

}
