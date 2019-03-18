package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class SelectionSort extends DefaultVisualizedSortingAlgorithm{
	
	public SelectionSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public long mearsureSortTime(){
		return 0;
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list){
		int n = list.length;
		int minIndex;
		int i = 0;
		
		while(i < (n-1)){
			int j = i + 1;
			minIndex = i;
			
			while(j < n){
				if(compareVisualized(list, j, minIndex, 4) < 0){
					minIndex = j;
				}
				j++;
			}
			swapVisualized(list, i, minIndex, 4);
			i++;
		}
	}
	
}