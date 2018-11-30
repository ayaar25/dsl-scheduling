import java.util.ArrayList;
import java.util.List;

public class Cell {
	private List<Class> cell = new ArrayList<Class>();
	
	public Cell() {
	}
	
	public Cell(List<Class> cell) {
		this.cell = cell;
	}
	
	public void addClass(Class lecture) {
		this.cell.add(lecture);
	}

	public List<Class> getCell() {
		return this.cell;
	}

	public void setCell(List<Class> cell) {
		this.cell = cell;
	}
	
	public boolean checkAvailable(Class lecture) {
		int class_found = 0;
		for(Class element : cell) {
		    if (element.getRoom().getName() == lecture.getRoom().getName()) {
		    	class_found++;
		    } 
		}
		
		if (lecture.getRoom().getCapacity() < lecture.getCourse().getTotalStudents()) {
			class_found++;
		}
		
		// Check room's facilities
    	for (String requirement : lecture.getCourse().getRequirements()) {
    		if (!lecture.getRoom().getFacilities().contains(requirement)) {
				class_found++;
			}
    	}
    	
		return (class_found == 0);
	}
	
	public static void printCell(List<Class> cell) {
		for(int i=0;i<cell.size();i++) {
			Class.printClass(cell.get(i));
		}
	}
}
