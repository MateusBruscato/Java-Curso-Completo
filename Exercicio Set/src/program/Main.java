package program;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import entities.Course;
import entities.Instructor;
import entities.User;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Instructor instructor = new Instructor("Alex");
		Course courseA = new Course("A");
		Course courseB = new Course("B");
		Course courseC = new Course("C");
		System.out.print("How many students for course A? ");
		int studentsA = sc.nextInt();
		
		for(int i = 0; i < studentsA; i++) {
			User novo = new User(sc.nextInt());
			courseA.getUsers().add(novo);
		}
		
		System.out.print("How many students for course B? ");
		int studentsB = sc.nextInt();
		
		for(int i = 0; i < studentsB; i++) {
			User novo = new User(sc.nextInt());
			courseB.getUsers().add(novo);
		}
		
		System.out.print("How many students for course C? ");
		int studentsC = sc.nextInt();
		
		for(int i = 0; i < studentsC; i++) {
			User novo = new User(sc.nextInt());
			courseC.getUsers().add(novo);
		}
		
		instructor.getCursos().add(courseA);
		instructor.getCursos().add(courseB);
		instructor.getCursos().add(courseC);
		
		Set<Integer> instructorStudents = new HashSet<>();
		
		for(Course curso: instructor.getCursos()) {
			for(User user: curso.getUsers())
			{
				instructorStudents.add(user.getUserCode());
			}
		}

		System.out.println("Total Students: " + instructorStudents.size());
		
		sc.close();
	}

}
