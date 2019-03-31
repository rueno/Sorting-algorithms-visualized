package me.rueno.Sortingalgorithms.Misc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GlobalVars{
	
	static{
		GlobalVars.scheduler = Executors.newScheduledThreadPool(4, (Runnable r) -> {
			Thread thread = new Thread(r);
			thread.setDaemon(true);
			thread.setName("GlobalVarsScheduler " + System.currentTimeMillis());
			thread.setPriority(Thread.MAX_PRIORITY);
			return thread;
		});
	}
	
	public static float SPEED_MULTIPLIER = 1.0F;
	
	public static ScheduledExecutorService scheduler;
	
}