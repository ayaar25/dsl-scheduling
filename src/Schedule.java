import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private Cell[][] schedule = new Cell[5][11];
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private	ArrayList<Course> courses = new ArrayList<Course>();
	
	public Schedule() {
		for (int i=0; i<5; i++) {
			for (int j=0; j<11; j++) {
				this.schedule[i][j] = new Cell();
			}
		}
	}

	public Cell[][] getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Cell[][] schedule) {
		this.schedule = schedule;
	}
	
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public Course getCoursesFromID(String courseID) {
		int i=0;
		boolean found = false;
		while (i< courses.size() && !found) {
			if (courses.get(i).getID().equals(courseID)) {
				found = true;
				return courses.get(i);
			}
		}
		return null;
	}
	
	public Room getRoomsFromID(String courseID) {
		int i=0;
		boolean found = false;
		while (i< rooms.size() && !found) {
			if (rooms.get(i).getName().equals(courseID)) {
				found = true;
				return rooms.get(i);
			}
		}
		return null;
	}
	
	public void assignClassToSchedule(Class lecture, int day, int hour) {
		for (int i=0; i<lecture.getTime().getDuration(); i++) {
			this.getSchedule()[day][hour+i].addClass(lecture);
		}
	}
	
	public boolean isTimeRangeAvailable(Class lecture, int day, int hour) {
		int count = 0;
		for (int i=0; i<lecture.getTime().getDuration(); i++) {
			if (this.getSchedule()[day][hour+i].checkAvailable(lecture)) {
				count++;
			}
		}
		
		return (count == lecture.getTime().getDuration());
	}
	
	public void addClassToSchedule(Class lecture) {
		int day = lecture.getTime().getDay();
		int hour = lecture.getTime().getStart();
		int duration = lecture.getTime().getDuration();
		int constraint = 0;
		
		// check if the time asked is not in the constraint
		for (Time time : lecture.getCourse().getConstraints()) {
			if (time.getDay() == lecture.getTime().getDay() && 
					(time.getStart() + time.getDuration() > lecture.getTime().getStart()) &&
							time.getStart() <= lecture.getTime().getStart()) {
				constraint++;
			}
		}
		
		boolean check_fail = true;
		// if time asked available then check success
		if (constraint == 0) {
			if (this.isTimeRangeAvailable(lecture, day, hour)) {
				check_fail = false;
			}
		}
		// if time asked not available then search preference
		int i = 0;
		while (check_fail && i < lecture.getCourse().getPreferences().size()) {
			Time pref = lecture.getCourse().getPreferences().get(i);
			// check every time range in selected preference
			int j = 0;
			while (check_fail && j < (pref.getDuration()-duration+1)) {
				day = pref.getDay();
				hour = pref.getStart() + j;
				if (this.isTimeRangeAvailable(lecture, day, hour)) {
					check_fail = false;
				} else {
					j++;
				}
			}
			i++;
		}
		if (!check_fail) {
			this.assignClassToSchedule(lecture, day, hour);
			System.out.println("Class is scheduled on " + day + " at start " + hour + " duration " + duration);
		} else {
			System.out.println("Class is not valid");
		}
		
	}
	
	public void removeClassInSchedule(Class lecture) {
		Class.printClass(lecture);
		int[] index = new int[lecture.getTime().getDuration()];
		int start = lecture.getTime().getStart();
		int count_deleted = 0;
		for (int i = 0; i<lecture.getTime().getDuration(); i++) {
			int count_idx = 0;
			for (Class element: this.getSchedule()[lecture.getTime().getDay()][start + i].getCell()) {
				if (element.getCourse().getName() == lecture.getCourse().getName() &&
						element.getRoom().getName() == lecture.getRoom().getName()) {
					index[count_deleted] = count_idx;
					count_deleted++;
					count_idx++;
				} 
			}
		}
		if (count_deleted == lecture.getTime().getDuration()) {
			for (int i = 0; i < lecture.getTime().getDuration(); i++) {
				this.getSchedule()[lecture.getTime().getDay()][start + i].getCell().remove(index[i]);
			}
			System.out.println("Class is deleted");
			
		} else {
			System.out.println("Class is not found");
		}
	}
	
	public static void printSchedule(Schedule schedule) {
		for (int i=0; i<5;i++) {
			for (int j=0;j<11;j++) {
				System.out.println("Day " + i + " Time " + j);
				for (int k=0;k<schedule.getSchedule()[i][j].getCell().size();k++) {
					Class.printClass(schedule.getSchedule()[i][j].getCell().get(k));
				}
				System.out.println();
			}
		}
	}
}
