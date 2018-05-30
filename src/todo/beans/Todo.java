package todo.beans;

import java.sql.Date;

// JavaBeans
public class Todo {

	private int id;
	private String title;
	private String detail;
	private int priority;
	private Date limitDay;


	public Todo(int id, String title, String detail, int priority, Date limitDay) {
		super();
		this.id = id;
		this.title = title;
		this.detail = detail;
		this.priority = priority;
		this.limitDay = limitDay;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getLimitDay() {
		return limitDay;
	}
	public void setLimitDay(Date limitDay) {
		this.limitDay = limitDay;
	}

}
