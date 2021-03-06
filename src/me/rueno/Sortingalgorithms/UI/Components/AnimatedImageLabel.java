package me.rueno.Sortingalgorithms.UI.Components;

import java.awt.Image;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Misc.GlobalVars;

public abstract class AnimatedImageLabel extends JLabel{
	
	private static final long serialVersionUID = -6238097470939913778L;
	private int currentImage;
	private boolean interrupted;
	private ImageIcon[] images;
	
	private ScheduledFuture<?> future;
	
	public AnimatedImageLabel(String resourcePrefix, int delay, int imageAmount, int width, int height, boolean preload){
		this.currentImage = 0;
		this.interrupted = false;
		if(preload){
			images = new ImageIcon[imageAmount];
			for(int i = 0; i < imageAmount; i++){
				images[i] = new ImageIcon(new ImageIcon(AnimatedImageLabel.class.getResource(resourcePrefix + i + ".png")).getImage()
						.getScaledInstance(width, height, Image.SCALE_SMOOTH));
			}
			this.setIcon(images[0]);
		}
		
		future = GlobalVars.scheduler.scheduleAtFixedRate(() -> {
			if(!interrupted){
				ImageIcon next;
				
				if(preload){
					next = images[currentImage];
				}else{
					Image scaled = new ImageIcon(AnimatedImageLabel.class.getResource(resourcePrefix + currentImage + ".png")).getImage()
							.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					next = new ImageIcon(scaled);
				}
				this.currentImage = (currentImage + 1) % imageAmount;
				this.setIcon(next);
				
				repaint();
			}
		}, 0, delay, TimeUnit.MILLISECONDS);
		
	}
	
	public void setInterrupted(boolean interrupt){
		this.interrupted = interrupt;
	}
	
	
	public void terminate(){
		future.cancel(false);
	}
}