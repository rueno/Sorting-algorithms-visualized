package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class InsertionSort extends DefaultVisualizedSortingAlgorithm{
	
	public InsertionSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public long mearsureSortTime(){
		return 0;
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list){
		int n = list.length;
		int i = 1;
		int j;
		while(i < n){
			j = i;
			while(j > 0){
				if(compareVisualized(list, j, j-1, 4) < 0){
					swapVisualized(list, j, j-1, 4);
					j--;
				}else j = 0;
			}
			i++;
		}
	}
	
}