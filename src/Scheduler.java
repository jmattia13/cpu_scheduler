import java.util.Scanner;
import java.util.ArrayList;

public class Scheduler {
	int CPUtime;
	int CPUtimeLeft;
	int startTime = 0;
	int endTime = 0;
	int quantum;
	Scanner keyboard = new Scanner(System.in);
	
	ProcessCreator pc;
	Process runningProcess;
	
	ArrayList<Process> arrayList;
	ArrayList<Process> wait;
	
	
	public Scheduler(){
		
		//Creates and stores processes-----------------
		System.out.println("Enter number of processes: ");
		int numberOfProcesses = keyboard.nextInt();
		
		arrayList = new ArrayList<Process>();
		wait = new ArrayList<Process>();
		
		int processBurstTime;
	
		for(int i=1; i<=numberOfProcesses;i++){
			System.out.println("Enter process " + i + " burst time: ");
			processBurstTime = keyboard.nextInt();	
			pc = new ProcessCreator(i, processBurstTime);
			arrayList.add(pc.getProcess());
		}
		
		
		
		//Generates CPU info----------------------------
		System.out.println("Enter CPU run time: ");
		CPUtime = keyboard.nextInt();
		System.out.println("Enter CPU quantum time: ");
		quantum = keyboard.nextInt();
		
		CPUtimeLeft = CPUtime;
		
	}
	

    public void runProcess(){
    	boolean done = false;
    	//Runs the CPU as long as Ready Queue is not empty
    	while(!done){
    		
    	    if(arrayList.isEmpty())
    	    	done = true;
    	    else{	
    		runningProcess = arrayList.get(0);
    		int burstTime= runningProcess.getBurstTime();
    		
    		System.out.println("Process running on CPU:");
    		System.out.println("Id: " + runningProcess.getId());
    		System.out.println("Burst Time: " + burstTime);
    	
    	
    	
    		startTime = CPUtime - CPUtimeLeft;
    		
    		
    		
        		if(startTime == CPUtime){
        			CPUtimeLeft = CPUtime;
        			startTime = 0;
        		}
        		
        		System.out.println("Start time: " + startTime);
        		
        		if(burstTime <= quantum){
        			endTime = startTime + burstTime;
        			CPUtimeLeft = CPUtimeLeft - burstTime;
        			runningProcess.setBurstTime(0);
        			
        			System.out.println("End time: " + endTime);
        			System.out.println("Process " + runningProcess.getId() + " completed");
        			
        			if(arrayList.size() > 1){
        				for(int i=1; i<arrayList.size(); i++){
        					arrayList.set(i-1, arrayList.get(i));
        				}
        			}
        			arrayList.remove(arrayList.size()-1);
        			

        			
        		}
        		else{
        			runningProcess.contextSwitch();
        			runningProcess.setBurstTime(burstTime - quantum);
        			endTime = startTime + quantum;
        			System.out.println("End time: " + endTime);
        			
        			for(int i=1; i<arrayList.size(); i++){
        				arrayList.set(i-1, arrayList.get(i));
        			}
        			arrayList.remove(arrayList.size()-1);
        			arrayList.add(runningProcess);
        			
        			
        			
        			if(CPUtimeLeft >= quantum){
        				CPUtimeLeft = CPUtimeLeft - quantum;
        				
        			}
        			else{
        				CPUtimeLeft = 0;
        			}
        			
        		}	
        		if(CPUtimeLeft == 0)
        			CPUtimeLeft = CPUtime;
        
    	    }
    	}
    	
    }

}