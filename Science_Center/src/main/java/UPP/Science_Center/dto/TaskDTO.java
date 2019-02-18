package UPP.Science_Center.dto;

public class TaskDTO {

	private String type;
	private String processInstanceId;
	private String taskId;
	
	
	public TaskDTO(){}
	
	
	public TaskDTO(String type, String processInstanceId, String taskId) {
	
		this.type = type;
		this.processInstanceId = processInstanceId;
		this.taskId = taskId;
	}
	


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
