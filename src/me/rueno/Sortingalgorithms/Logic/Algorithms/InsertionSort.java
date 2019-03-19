package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class InsertionSort extends DefaultVisualizedSortingAlgorithm{
	
	public InsertionSort(JLabel[] labels){
		super(labels);
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
				}else{
					j = 0;
				}
			}
			i++;
		}
	}
	
	protected <C extends Comparable<C>> long[] sort(C[] list){
		long resaves = 0, comparations = 0;
		
		int n = list.length;
		int i = 1;
		int j;
		while(i < n){ comparations++;
			j = i; resaves++;
			while(j > 0){ comparations++;
				if(list[j].compareTo(list[j-1]) < 0){ comparations++;
					swap(list, j, j-1); resaves += 3;
					j--; resaves++;
				}else {
					j = 0; resaves++;
				}
			} comparations++;
			i++; resaves++;
		} comparations++;
		return new long[] {resaves, comparations};
	}
	
}