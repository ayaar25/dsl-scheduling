import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Course {
	private String name;
	private String id;
	private String lecturer;
	private Set<String> requirements = new HashSet<String>();
	private int totalStudents;
	private ArrayList<Time> preferences;
	private boolean available;
	private ArrayList<Time> constraints;

	
	public Course() {
	}

	public Course(String name, String id, String lecturer, Set<String> requirements, int totalStudents, ArrayList<Time> preferences, boolean available,
			ArrayList<Time> constraints) {
		this.name = name;
		this.id = id;
		this.lecturer = lecturer;
		this.requirements = requirements;
		this.totalStudents = totalStudents;
		this.preferences = preferences;
		this.available = available;
		this.constraints = constraints;
	}

	
	public void addRequirement(String requirement) {
		this.requirements.add(requirement);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return id;
	}
	
	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public Set<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(Set<String> requirements) {
		this.requirements = requirements;
	}

	public ArrayList<Time> getPreferences() {
		return preferences;
	}

	public void setPreferences(ArrayList<Time> preferences) {
		this.preferences = preferences;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public ArrayList<Time> getConstraints() {
		return constraints;
	}

	public void setConstraints(ArrayList<Time> constraints) {
		this.constraints = constraints;
	}

	public int getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}
	
	public static void printCourse(Course c) {
		System.out.println("Course Name : " + c.name);
		System.out.println("Course ID : " + c.id);
		System.out.println("Lecturer : " + c.lecturer);
		System.out.println("Total Students : " + c.totalStudents);
		System.out.println("Requirements : " + c.requirements);
		System.out.println("Preferences : ");
		for (Time t : c.preferences) {
			System.out.println("Day " + t.getDay() + " Start " + t.getStart() + " Duration " + t.getDuration());
		}
		System.out.println("Constraints : ");
		for (Time t : c.constraints) {
			System.out.println("Day " + t.getDay() + " Start " + t.getStart() + " Duration " + t.getDuration());
		}
	}
}
