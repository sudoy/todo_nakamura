package todo;

import java.sql.Date;

// JavaBeans
public class Todo {

	private int id;
	private String title;
	private String detail;
	private int priority;
	private Date limit_day;


	public Todo(int id, String title, String detail, int priority, Date limit_day) {
		super();
		this.id = id;
		this.title = title;
		this.detail = detail;
		this.priority = priority;
		this.limit_day = limit_day;
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

	public Date getLimit_day() {
		return limit_day;
	}
	public void setLimit_day(Date limit_day) {
		this.limit_day = limit_day;
	}

}
