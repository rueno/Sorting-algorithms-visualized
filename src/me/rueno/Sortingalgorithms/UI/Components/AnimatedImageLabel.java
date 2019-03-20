package me.rueno.Sortingalgorithms.UI.Components;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class AnimatedImageLabel extends JLabel{
	
	private static final long serialVersionUID = -6238097470939913778L;
	
	private String resourcePrefix;
	private int delay, imageAmount, width, height;
	private int currentImage;
	private boolean preload, interrupted;
	private ImageIcon[] images;
	
	private Thread renderer;
	
	public AnimatedImageLabel(String resourcePrefix, int delay, int imageAmount, int width, int height, boolean preload){
		this.resourcePrefix = resourcePrefix;
		this.delay = delay;
		this.imageAmount = imageAmount;
		this.width = width;
		this.height = height;
		this.currentImage = 0;
		this.interrupted = false;
		
		if(preload){
			images = new ImageIcon[imageAmount];
			for(int i = 0; i < imageAmount; i++){
				images[i] = new ImageIcon(new ImageIcon(AnimatedImageLabel.class.getResource(resourcePrefix + i + ".png")).getImage()
						.getScaledInstance(width, height, Image.SCALE_SMOOTH));
			}
		}
		
		this.renderer = new Thread(() -> {
			while(true){
				while(!interrupted){
					try{
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
						
						Thread.sleep(delay);
					}catch(InterruptedException interrupted){}
				}
				try{
					Thread.sleep(100);
				}catch(InterruptedException interrupted){}
			}
			
		});
		renderer.setDaemon(true);
		renderer.start();
	}
	
	public void setInterrupted(boolean interrupt){
		this.interrupted = interrupt;
	}
	
}