package me.rueno.Sortingalgorithms;

import javax.swing.UIManager;

import me.rueno.Sortingalgorithms.UI.VisualizedSortingAlgorithm;

public class Main{
	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		VisualizedSortingAlgorithm ui = new VisualizedSortingAlgorithm();
		ui.setVisible(true);
	}
}