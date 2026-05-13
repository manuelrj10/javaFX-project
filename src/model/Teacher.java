package model;

public class Teacher {
	private int teacher_id;
	private String teacher_name;
	private String teacher_phone;
	private Double teacher_salary;

	public Teacher(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public Teacher(String teacher_name, String teacher_phone, Double teacher_salary) {

		this.teacher_name = teacher_name;
		this.teacher_phone = teacher_phone;
		this.teacher_salary = teacher_salary;
	}

	public Teacher(int teacher_id, String teacher_name, String teacher_phone, Double teacher_salary) {
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.teacher_phone = teacher_phone;
		this.teacher_salary = teacher_salary;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public String getTeacher_phone() {
		return teacher_phone;
	}

	public Double getTeacher_salary() {
		return teacher_salary;
	}

}
