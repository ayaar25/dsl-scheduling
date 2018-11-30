
import java.util.HashSet;
import java.util.Set;

public class Room {
	private String name;
	private Set<String> facilities = new HashSet<String>();
	private int capacity;
	
	public Room() {
	}

	public Room(String name, Set<String> facilities, int capacity) {
		this.name = name;
		this.facilities = facilities;
		this.capacity = capacity;
	}
	
	public void addFacility(String facility) {
		this.facilities.add(facility);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<String> facilities) {
		this.facilities = facilities;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public static void printRoom(Room r) {
		System.out.println("Name : " + r.name);
		System.out.println("Capacity : " + r.capacity);
		System.out.println("Facilities : " + r.facilities);
	}
}
