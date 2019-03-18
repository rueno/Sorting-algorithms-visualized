package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class QuickSort extends DefaultVisualizedSortingAlgorithm{
	
	public QuickSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public long mearsureSortTime(){
		return 0;
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list){
		qSort(list, 0, list.length-1);
	}
	
	private <C extends Comparable<C>> void qSort(C[] list, int start, int end){
		if(start >= end) return;
		
		int pivIndex = start + (end - start) / 2;
//		C pivot = list[pivIndex];
		
		int i = start, j = end;
		while(i <= j){
			while(compareVisualized(list, i, pivIndex, 4) < 0) i++;
			while(compareVisualized(list, j, pivIndex, 4) > 0) j--;
//			while(list[i].compareTo(pivot) < 0) i++;
//			while(list[j].compareTo(pivot) > 0) j--;
			
			if(i <= j){
				if(j == pivIndex) pivIndex = i;
				if(i == pivIndex) pivIndex = j;
				
				swapVisualized(list, i, j, 4);
				i++;
				j--;
			}
		}
		
		if(start < j) qSort(list, start, j);
		if(end > i) qSort(list, i, end);
		
	}
	
}