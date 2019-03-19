package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;
import me.rueno.Sortingalgorithms.UI.IIncrementable;

public class BubbleSort extends DefaultVisualizedSortingAlgorithm{
	
	public BubbleSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list, IIncrementable incrementable){
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
	
	protected <C extends Comparable<C>> long[] sort(C[] list){
		long comparations = 0, resaves = 0;
		
		int i = 0;
		int n = list.length;
		int j;
		
		while(i < (n - 1)){ comparations++;
			j = n - 1; resaves++;
			while(j > i){ comparations++;
				if(list[j].compareTo(list[j-1]) < 0){ comparations++;
					swap(list, j, j-1); resaves += 3;
				}
				j--; resaves++;
			} comparations++;
			i++; resaves++;
		} comparations++;
		return new long[] {resaves, comparations};
	}
	
}