grammar SCHEDULE;

statement
	: defRoom
	| defCourse
	| defClass
	| showSchedule
	| showCourse
	| showRoom
	| showClass
	| delClass
	;
prog 
	: statement*
	;
defRoom 
	: 'ROOM' 'NAME' NUMBER 
	  'CAPACITY' NUMBER 
	  'FACILITY' IDENTIFIER* 
	;
defCourse 
	: 'COURSE' 'NAME' IDENTIFIER 
	  'ID' CourseID 
	  'LECTURER' IDENTIFIER 
	  'REQUIREMENT' IDENTIFIER* 
	  'CAPACITY' NUMBER 
	  defConstraint
	  defPreference
	;
defConstraint
	: 'CONSTRAINT' defTime* 
	;
defPreference
	: 'PREFERENCE' defTime*
	;
defTime 
	: 'DAY' Day 
	  'AT' timeRange+
	;
defClass 
	: 'CLASS' 'ID' CourseID 
	  'ROOM' NUMBER 
	  'TIME' defTime
	 ;
timeRange
	: 'START' NUMBER 'DURATION' NUMBER;
showSchedule
	: 'SHOW' 'SCHEDULE';
showCourse
	: 'SHOW' 'COURSE';
showRoom
	: 'SHOW' 'ROOM';
showClass
	: 'SHOW' 'CLASS' 'DAY' Day 'AT' NUMBER;
delClass
	: 'DELETE' 'CLASS' 'ID' CourseID 'ROOM' NUMBER 'TIME' 'DAY' Day 'AT' timeRange;	
Day 
	: 'monday' 
	| 'tuesday' 
	| 'wednesday' 
	| 'thursday' 
	| 'friday' 
	;
CourseID 
	: 'IF' NUMBER 
	;
IDENTIFIER 
	: [A-Za-z]+
	;
NUMBER
	: [0-9]+ 
	;
WS : [ \u000C\t\r\n]+ -> skip ; // skip spaces, tabs, newlines

