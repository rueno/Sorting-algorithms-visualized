package me.rueno.Sortingalgorithms.Misc;

public class InterruptableThread extends Thread{
	
	private boolean interrupted;
	
	public InterruptableThread(Runnable r){
		super(r);
		this.interrupted = false;
	}
	
	@Override
	public void interrupt(){
		this.interrupted = true;
	}
	
	@Override
	public boolean isInterrupted(){
		return interrupted;
	}
	
}