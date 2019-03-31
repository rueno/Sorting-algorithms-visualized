package me.rueno.Sortingalgorithms.Misc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GlobalVars{
	
	static{
		GlobalVars.scheduler = Executors.newScheduledThreadPool(4);
	}
	
	public static float SPEED_MULTIPLIER = 1.0F;
	
	public static ScheduledExecutorService scheduler;
	
}