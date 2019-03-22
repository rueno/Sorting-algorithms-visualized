package me.rueno.Sortingalgorithms.Logic.Algorithms;

import java.util.Arrays;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class ShellSort extends DefaultVisualizedSortingAlgorithm{
	
	public ShellSort(JLabel[] panels){
		super(panels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list){
		int n = list.length;
		
		for(int gap = n/2; gap > 0; gap /= 2){
			for(int i = gap; i < n; i += 1){
				int j;
				C temp = list[i];
				
				for (j = i; j >= gap && compareVisualized(list, j - gap, indexOf(list, temp), 4) > 0; j -= gap){
					swapVisualized(list, j, j-gap, 4);
				}
				swapVisualized(list, j, indexOf(list, temp), 4);
			}
		}
	}
	
	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		int n = list.length;
		
		for(int gap = n/2; gap > 0; gap /= 2){
			for(int i = gap; i < n; i += 1){
				int j;
				C temp = list[i];
				
				for (j = i; j >= gap && list[j - gap].compareTo(temp) > 0; j -= gap){
					list[j] = list[j-gap];
				}
				list[j] = temp;
			}
		}
		return new long[] {0L, 0L};
	}

	@Override
	public String getAlgorithmInfoText() {
		// TODO Auto-generated method stub
		return null;
	}
	
}