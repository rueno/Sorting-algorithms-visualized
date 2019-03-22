package me.rueno.Sortingalgorithms.UI.Components;

public class PikaRun extends AnimatedImageLabel{
	
	private static final long serialVersionUID = 1572305695373512728L;
	
	public PikaRun(int width, int height){
		super("/resources/images/pikaRun/pikaRun_", 100, 4, width, height, true);
		setSize(width, height);
	}
	
}