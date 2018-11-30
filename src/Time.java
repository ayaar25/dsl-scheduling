public class Time {
	private int day;
	private int start;
	private int duration;
	
	
	public Time() {
		this.day = 0;
		this.start = 0;
		this.duration = 0;
	}

	public Time(int day, int start, int duration) {
		this.day = day;
		this.start = start;
		this.duration = duration;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
