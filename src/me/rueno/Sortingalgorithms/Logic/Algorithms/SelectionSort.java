package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class SelectionSort extends DefaultVisualizedSortingAlgorithm{
	
	public SelectionSort(){
		this(null);
	}
	
	public SelectionSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list)  throws InterruptedException{
		int n = list.length;
		int minIndex;
		int i = 0;
		
		while(i < (n-1) && !shouldInterrupt()){
			int j = i + 1;
			minIndex = i;
			
			while(j < n && !shouldInterrupt()){
				if(compareVisualized(list, j, minIndex, 4) < 0 && !shouldInterrupt()){
					minIndex = j;
				}
				j++;
			}
			swapVisualized(list, i, minIndex, 4);
			markAsSorted(i);
			i++;
		}
		markAsSorted(i);
	}
	
	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		long comparations = 0, resaves = 0;
		
		int n = list.length;
		int minIndex;
		int i = 0;
		int j;
		
		while(i < (n-1) && !shouldInterrupt()){ comparations++;
			j = i + 1; resaves++;
			minIndex = i; resaves++;
			
			while(j < n && !shouldInterrupt()){ comparations++;
				if(list[j].compareTo(list[minIndex]) < 0 && !shouldInterrupt()){ comparations++;
					minIndex = j; resaves++;
				}
				j++; resaves++;
			} comparations++;
			swap(list, i, minIndex); resaves += 3;
			i++; resaves++;
		} comparations++;
		
		return new long [] {resaves, comparations};
	}
	
	@Override
	public String getAlgorithmInfoText(){
		return "<html>"
				+ "<h2 style=\"text-align:center;\">Selectionsort</h2>"
				+ "<p style=\"text-align:justify;\">Der Selectionsort (Sortieren durch Auswählen, alternativ auch"
				+ " Maxsort, Minsort, Selecsort oder Exchancesort genannt) sortiert eine Liste, indem er sich immer das nächst kleine Element"
				+ " aus der Liste raussucht (auswählt) und dieses anschließend an die korrekte Stelle der Liste tauscht."
				+ " Dieser Algorithmus ist vergleichsweise einfach zu implementieren aber nicht allzu effizient. "
				+ "In der Praxis wird der Selectionsort eigentlich nicht eingesetzt, da er im Vergleich zu anderen Sortier"
				+ "verfahren (zum Beispiel QuickSort oder MergeSort) sehr zeitaufwendig ist. </p>"
				+ "<br><br>"
				+ "<h3>Laufzeiten</h3>"
				+ "Average-Case: O(n²)<br>"
				+ "Worst-Case: O(n²)"
				+ "</p>"
				+ "</html>";
	}
	
}