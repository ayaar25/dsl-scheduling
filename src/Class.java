
public class Class {
	private Time time;
	private Course course;
	private Room room;

	public Class() {
	}

	public Class(Time time, Course course, Room room) {
		this.time = time;
		this.course = course;
		this.room = room;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public static void printClass(Class cls) {
		System.out.println("[Time]");
		System.out.println("Day " + cls.getTime().getDay() + " Start " + cls.getTime().getStart() + " Duration " + cls.getTime().getDuration());
		System.out.println("[Course]");
		Course.printCourse(cls.getCourse());
		System.out.println("[Room]");
		Room.printRoom(cls.getRoom());
	}
}
