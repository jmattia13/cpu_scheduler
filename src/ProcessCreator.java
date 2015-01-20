
public class ProcessCreator{
	
	Process p;
	
	//Generates a random number
	
	public ProcessCreator(int processNumber, int burstTime){
			p = new Process(processNumber, burstTime);
	}
	public Process getProcess(){
		return p;
	}

}