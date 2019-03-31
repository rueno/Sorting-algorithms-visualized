package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class ShellSort extends DefaultVisualizedSortingAlgorithm{
	
	public ShellSort(){
		this(null);
	}
	
	public ShellSort(JLabel[] panels){
		super(panels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list)  throws InterruptedException{
		int n = list.length;
		
		for(int gap = n/2; gap > 0 && !shouldInterrupt(); gap /= 2){
			for(int i = gap; i < n && !shouldInterrupt(); i += 1){
				int j;
				C temp = list[i];
				
				for (j = i; j >= gap && compareVisualized(list, j - gap, indexOf(list, temp), 4) > 0 && !shouldInterrupt(); j -= gap){
					swapVisualized(list, j, j-gap, 4);
				}
				swapVisualized(list, j, indexOf(list, temp), 4);
			}
		}
	}
	
	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		int n = list.length;
		
		for(int gap = n/2; gap > 0 && !shouldInterrupt(); gap /= 2){
			for(int i = gap; i < n && !shouldInterrupt(); i += 1){
				int j;
				C temp = list[i];
				
				for (j = i; j >= gap && list[j - gap].compareTo(temp) > 0 && !shouldInterrupt(); j -= gap){
					list[j] = list[j-gap];
				}
				list[j] = temp;
			}
		}
		return new long[] {0L, 0L};
	}

	@Override
	public String getAlgorithmInfoText(){
		return "";
	}
	
}