package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;
import me.rueno.Sortingalgorithms.UI.IIncrementable;

public class SelectionSort extends DefaultVisualizedSortingAlgorithm{
	
	public SelectionSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list, IIncrementable incrementable){
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
	
	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		long comparations = 0, resaves = 0;
		
		int n = list.length;
		int minIndex;
		int i = 0;
		int j;
		
		while(i < (n-1)){ comparations++;
			j = i + 1; resaves++;
			minIndex = i; resaves++;
			
			while(j < n){ comparations++;
				if(list[j].compareTo(list[minIndex]) < 0){ comparations++;
					minIndex = j; resaves++;
				}
				j++; resaves++;
			} comparations++;
			swap(list, i, minIndex); resaves += 3;
			i++; resaves++;
		} comparations++;
		
		return new long [] {resaves, comparations};
	}
	
}