package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class InsertionSort extends DefaultVisualizedSortingAlgorithm{
	
	public InsertionSort(){
		super(null);
	}
	
	public InsertionSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list)  throws InterruptedException{
		int n = list.length;
		int i = 1;
		int j;
		
		while(i < n && !shouldInterrupt()){
			j = i;
			while(j > 0 && !shouldInterrupt()){
				if(compareVisualized(list, j, j-1, 4) < 0 && !shouldInterrupt()){
					swapVisualized(list, j, j-1, 4);
					j--;
				}else{
					j = 0;
				}
			}
			markIntervalAsSortedPopup(0, i);
			i++;
		}
		for(int i1 = 0; i1 < list.length && !shouldInterrupt(); i1++){
			markAsSorted(i1);
		}
	}
	
	protected <C extends Comparable<C>> long[] sort(C[] list){
		long resaves = 0, comparations = 0;
		
		int n = list.length;
		int i = 1;
		int j;
		while(i < n && !shouldInterrupt()){ comparations++;
			j = i; resaves++;
			while(j > 0 && !shouldInterrupt()){ comparations++;
				if(list[j].compareTo(list[j-1]) < 0 && !shouldInterrupt()){ comparations++;
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
	
	@Override
	public String getAlgorithmInfoText(){
		return "<html>"
				+ "<h2 style=\"text-align:center;\">Insertionsort</h2>"
				+ "<p style=\"text-align:justify;\">Der Insertionsort (Sortieren durch Einfügen) sortiert eine Liste, indem er ein Element aus der unsortierten Teilliste entnimmt,"
				+ "und an der korrekten Stelle in der sortierten Teilliste einfügt. "
				+ " Dieser Algorithmus ist vergleichsweise einfach zu implementieren und bei bereits sortierten Listen relativ effizient. "
				+ "In der Praxis wird der Insertionsort eigentlich nicht eingesetzt, da er im Vergleich zu anderen Sortier"
				+ "verfahren (zum Beispiel QuickSort oder MergeSort) dennoch sehr zeitaufwendig ist. </p>"
				+ "<br><br>"
				+ "<h3>Laufzeiten</h3>"
				+ "Average-Case: O(n²)<br>"
				+ "Worst-Case: O(n²)"
				+ "</p>"
				+ "</html>";
	}
	
}