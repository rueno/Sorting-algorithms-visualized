package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;
import me.rueno.Sortingalgorithms.UI.Components.FeelsOhWait;

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
		long resaves = 0L, comparasions = 0L;
		int n = list.length;
		
		for(int gap = n/2; gap > 0; gap /= 2){
			for(int i = gap; i < n; i += 1){
				int j;
				C temp = list[i]; resaves++;
				
				for (j = i; j >= gap && list[j - gap].compareTo(temp) > 0; j -= gap){ comparasions++;
					list[j] = list[j-gap]; resaves++;
				}
				list[j] = temp; resaves++;
			}
		}
		return new long[] {resaves, comparasions};
	}

	@Override
	public String getAlgorithmInfoText(){
		return "";
	}
	
	@Override
	public JLabel getGif(){
		return new FeelsOhWait(25, 25);
	}
	
}