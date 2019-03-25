package me.rueno.Sortingalgorithms.Logic.Algorithms;

import java.util.Random;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class BogoSort extends DefaultVisualizedSortingAlgorithm{
	
	private Random random;
	
	public BogoSort(){
		this(null);
	}
	
	public BogoSort(JLabel[] lbls){
		super(lbls);
		this.random = new Random();
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list){
		int a, b;
		int n = list.length;
		while(!isSortedVisualized(list) && !shouldInterrupt()){
			a = random.nextInt(n);
			b = random.nextInt(n);
			
			swapVisualized(list, a, b, 4);
		}
	}
	
	private <C extends Comparable<C>> boolean isSortedVisualized(C[] list){
		for(int i = 0; i < list.length-1 && !shouldInterrupt(); i++){
			if(compareVisualized(list, i, i+1, 4) > 0){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String getAlgorithmInfoText(){
		return "";
	}

	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		return new long[] {0, 0};
	}
	
}