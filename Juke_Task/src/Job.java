/**
 * 
 * @author Aingty Eung
 * This class is used as an identifier for the task in the tasklist
 */
public class Job implements Comparable<Job>{
	/**
	 * @task is the name of the task needed to be done
	 * @date is the due date of the task
	 */
	private String task;
	private String date;
	
	/**
	 * This is the constructor for the Job class
	 * @param t is the name of the task
	 * @param d is the due date of the task
	 */
	public Job(String t, String d){
		this.task = t;
		this.date = d;
	}

	/**
	 * This method returns the task's name
	 * @return the task name
	 */
	public String getTask(){
		return this.task;
	}
	
	/**
	 * This method returns the task's date
	 * @return the due date of the task
	 */
	public String getDate(){
		return this.date;
	}
	
	/**
	 * This method override the compareTo, so that it will compare the job by date and then task name.
	 * @return the integer value for the HeapTree to determine if swap is needed
	 */
	@Override
	public int compareTo(Job j) {
		/**
		 * dateWithTime is the array for date and time in index 0 and 1
		 * dateWithoutTime is just the date 
		 * timeWithoutDate is the time at index 0 and 1
		 */
		String[] dateWithTime = j.getDate().split(" ");
		String[] dateWithoutTime = dateWithTime[0].split("/");
		String[] timeWithoutDate = dateWithTime[1].split(":");
		/**
		 * dateWithTime1 is the array for date and time in index 0 and 1 (for the inclusive)
		 * dateWithoutTime1 is just the date (for the inclusive)
		 * timeWithoutDate1 is the time at index 0 and 1 (for the inclusive)
		 */
		String[] dateWithTime1 = this.date.split(" ");
		String[] dateWithoutTime1 = dateWithTime1[0].split("/");
		String[] timeWithoutDate1 = dateWithTime1[1].split(":");
		if(dateWithoutTime1[2].equalsIgnoreCase(dateWithoutTime[2])){// if years are equal
			if(dateWithoutTime1[0].equalsIgnoreCase(dateWithoutTime[0])){// if months are equal
				if(dateWithoutTime1[1].equalsIgnoreCase(dateWithoutTime[1])){//if days are equal
					if(timeWithoutDate1[0].equalsIgnoreCase(timeWithoutDate[0])){//if hours are equal
						if(timeWithoutDate1[1].equalsIgnoreCase(timeWithoutDate[1])){//if minutes are equal
							return -1*this.getTask().compareToIgnoreCase(j.getTask());
						}else{
							return Integer.parseInt(timeWithoutDate[1])- Integer.parseInt(timeWithoutDate1[1]);
						}
					}else{
						return Integer.parseInt(timeWithoutDate[0])- Integer.parseInt(timeWithoutDate1[0]);
					}
				}else{
					return Integer.parseInt(dateWithoutTime[1]) - Integer.parseInt(dateWithoutTime1[1]);
				}
			}else{
				return Integer.parseInt(dateWithoutTime[0]) - Integer.parseInt(dateWithoutTime1[0]);
			}
		}else{
			return Integer.parseInt(dateWithoutTime[2]) - Integer.parseInt(dateWithoutTime1[2]);
		}
	}
	/**
	 * This method is used to override the toString method so that it can print out the Job's name and date
	 * @return Job's name and date
	 */
	@Override 
	public String toString(){
		/**
		 * job is the String to be returned.
		 */
		String job = this.task+ ", "+ this.date;
		return job;
	}
}
