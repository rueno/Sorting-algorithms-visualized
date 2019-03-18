package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class BubbleSort extends DefaultVisualizedSortingAlgorithm{
	
	public BubbleSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list){
		int i = 0;
		int n = list.length;
		int j;
		
		while(i < (n - 1)){
			j = n - 1;
			while(j > i){
				if(compareVisualized(list, j, j-1, 4) < 0){
					swapVisualized(list, j, j-1, 4);
				}
				j--;
			}
			i++;
		}
	}
	
	@Override
	public long mearsureSortTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}