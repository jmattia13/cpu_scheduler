public class Process {
	int id;
	int burstTime;
	int contextSwitch;
	
	public Process(){
		
	}
	public Process(int ID, int BT){
		id = ID;
		burstTime = BT;
	}
	public int getId() {
        return id;
    }
	
	public void setBurstTime(int BurstTime){
		burstTime = BurstTime;
	}
	public int getBurstTime(){
		return burstTime;
	}
	
	public void contextSwitch(){
		contextSwitch++;
	}
	public int getContextSwitch(){
		return contextSwitch;
	}
    

}