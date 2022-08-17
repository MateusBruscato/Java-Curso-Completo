package entities;

import java.util.ArrayList;
import java.util.List;

public class Instructor {

	private String name;
	List<Course> cursos;
	
	public Instructor(String name) {
		this.name = name;
		this.cursos = new ArrayList<Course>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCursos() {
		return cursos;
	}

	public void setCursos(List<Course> cursos) {
		this.cursos = cursos;
	}
	
	
}
