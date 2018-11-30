import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SCHEDULEParserListener extends SCHEDULEBaseListener {
	Schedule schedule;
	
	public SCHEDULEParserListener() {
		this.schedule = new Schedule();
	}
	
	public SCHEDULEParserListener(Schedule s) {
		this.schedule = s;
	}
	
	private int day2int(String day) {
		if (day.equals("monday")){
			return 0;
		} else if (day.equals("tuesday")) {
			return 1;
		} else if (day.equals("wednesday")) {
			return 2;
		} else if (day.equals("thursday")) {
			return 3;
		} else if (day.equals("friday")) {
			return 4;
		} else {
			return 0;
		}
	}
	
	@Override
	public void exitDefRoom(SCHEDULEParser.DefRoomContext ctx) {
		String roomName = ctx.NUMBER(0).getText();
		int capacity = Integer.parseInt(ctx.NUMBER(1).getText());
		Set<String> facilities = new HashSet<String>();
		for (int i=0; i<ctx.IDENTIFIER().size(); i++) {
			facilities.add(ctx.IDENTIFIER().get(i).toString());
		}
		
		Room r = new Room(roomName,facilities,capacity);
		
		this.schedule.addRoom(r);
	}
	
	@Override
	public void exitDefCourse(SCHEDULEParser.DefCourseContext ctx) {
		String courseName = ctx.IDENTIFIER(0).getText();
		String courseID = ctx.CourseID().getText();
		String lecturer = ctx.IDENTIFIER(1).getText();
		Set<String> reqs = new HashSet<String>();
		for (int i=2; i<ctx.IDENTIFIER().size(); i++) {
			reqs.add(ctx.IDENTIFIER().get(i).toString());
		}
		int capacity = Integer.parseInt(ctx.NUMBER().getText());
		
		ArrayList<Time> constraint = new ArrayList<Time>();
		for (int i=0; i<ctx.defConstraint().defTime().size(); i++) {
			int day = this.day2int(ctx.defConstraint().defTime(i).Day().getText());
			for (int j=0; j<ctx.defConstraint().defTime(i).timeRange().size(); j++) {
				int start = Integer.parseInt(ctx.defConstraint().defTime(i).timeRange(j).NUMBER(0).getText());
				int duration = Integer.parseInt(ctx.defConstraint().defTime(i).timeRange(j).NUMBER(1).getText());
				
				Time t = new Time(day, start, duration);
				constraint.add(t);
			}
		}
		
		ArrayList<Time> preference = new ArrayList<Time>();
		for (int i=0; i<ctx.defPreference().defTime().size(); i++) {
			int day = this.day2int(ctx.defPreference().defTime(i).Day().getText());
			for (int j=0; j<ctx.defPreference().defTime(i).timeRange().size(); j++) {
				int start = Integer.parseInt(ctx.defPreference().defTime(i).timeRange(j).NUMBER(0).getText());
				int duration = Integer.parseInt(ctx.defPreference().defTime(i).timeRange(j).NUMBER(1).getText());
				
				Time t = new Time(day, start, duration);
				preference.add(t);
			}
		}
		
		Course c = new Course(courseName,courseID,lecturer,reqs,capacity,preference,false,constraint);
		
		this.schedule.addCourse(c);
	}
	
	@Override
	public void exitDefClass(SCHEDULEParser.DefClassContext ctx) {
		String courseID = ctx.CourseID().getText();
		String roomID = ctx.NUMBER().getText();
		int day = day2int(ctx.defTime().Day().getText());
		
		Class c = new Class();
		
		if (ctx.defTime().timeRange().size() > 1) {
			System.out.println("Cannot schedule class with more than 1 start time and duration");
		} else {
			int startTime = Integer.parseInt(ctx.defTime().timeRange(0).NUMBER(0).getText());
			int duration = Integer.parseInt(ctx.defTime().timeRange(0).NUMBER(1).getText());
			
			Time t = new Time(day, startTime, duration);
			
			c.setTime(t);
			c.setCourse(schedule.getCoursesFromID(courseID));
			c.setRoom(schedule.getRoomsFromID(roomID));
		}
//		for (int x=0; x < ctx.defTime().timeRange().size(); x++) {
//			int startTime = Integer.parseInt(ctx.defTime().timeRange(x).NUMBER(0).getText());
//			int duration = Integer.parseInt(ctx.defTime().timeRange(x).NUMBER(1).getText());
//			
//			Time t = new Time(day, startTime, duration);
//			Class c = new Class (t, schedule.getCoursesFromID(courseID), schedule.getRoomsFromID(roomID));
//			listClass.add(c);
//		}
//		Cell cell = new Cell(listClass);
		
		System.out.println("[[Class Schedule Entry]]");
		Class.printClass(c);
		System.out.println();
		
		this.schedule.addClassToSchedule(c);
		
	}
	
	@Override
	public void exitShowCourse(SCHEDULEParser.ShowCourseContext ctx) {
		for (int i=0; i<schedule.getCourses().size(); i++) {
			Course.printCourse(schedule.getCourses().get(i));
		}
	}

	@Override
	public void exitShowClass(SCHEDULEParser.ShowClassContext ctx) {
		int day = day2int(ctx.Day().getText());
		int hour = Integer.parseInt(ctx.NUMBER().getText());
		Cell.printCell(schedule.getSchedule()[day][hour].getCell());
	}

	@Override
	public void exitShowSchedule(SCHEDULEParser.ShowScheduleContext ctx) {
		Schedule.printSchedule(schedule);
	}

	@Override
	public void exitShowRoom(SCHEDULEParser.ShowRoomContext ctx) {
		for (int i=0;i<schedule.getRooms().size();i++) {
			Room.printRoom(schedule.getRooms().get(i));
		}
	}

	@Override
	public void exitDelClass(SCHEDULEParser.DelClassContext ctx) {
		String courseID = ctx.CourseID().getText();
		String roomID = ctx.NUMBER().getText();
		int day = day2int(ctx.Day().getText());
		
		Class c = new Class();
		
		int startTime = Integer.parseInt(ctx.timeRange().NUMBER(0).getText());
		int duration = Integer.parseInt(ctx.timeRange().NUMBER(1).getText());
		
		Time t = new Time(day, startTime, duration);
		
		c.setTime(t);
		c.setCourse(schedule.getCoursesFromID(courseID));
		c.setRoom(schedule.getRoomsFromID(roomID));
		
		this.schedule.removeClassInSchedule(c);		
	}
	
}
