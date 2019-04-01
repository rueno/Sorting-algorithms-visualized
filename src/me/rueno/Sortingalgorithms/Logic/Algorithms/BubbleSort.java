package me.rueno.Sortingalgorithms.Logic.Algorithms;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class BubbleSort extends DefaultVisualizedSortingAlgorithm{
	
	public BubbleSort(){
		this(null);
	}
	
	public BubbleSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list)  throws InterruptedException{
		int i = 0;
		int n = list.length;
		int j;
		
		while(i < (n - 1) && !shouldInterrupt()){
			j = n - 1;
			while(j > i && !shouldInterrupt()){
				if(compareVisualized(list, j, j-1, 4) < 0){
					swapVisualized(list, j, j-1, 4);
				}
				j--;
			}
			markAsSorted(i);
			i++;
		}
		markAsSorted(list.length-1);
	}
	
	protected <C extends Comparable<C>> long[] sort(C[] list) {
		long comparations = 0, resaves = 0;
		
		int i = 0;
		int n = list.length;
		int j;
		
		while(i < (n - 1) && !shouldInterrupt()){
			j = n - 1;
			while(j > i && !shouldInterrupt()){
				if(list[j].compareTo(list[j-1]) < 0){ comparations++;
					swap(list, j, j-1); resaves += 3;
				}
				j--;
			}
			i++;
		}
		return new long[] {resaves, comparations};
	}
	
	@Override
	public String getAlgorithmInfoText(){
		return "<html>"
				+ "<h2 style=\"text-align:center;\">Bubblesort</h2>"
				+ "<p style=\"text-align:justify;\">Der Bubblesort (Sortieren durch Auf- bzw. Absteigen) sortiert eine Liste durch Vergleichen und Tauschen."
				+ " Der Algorithmus eignet sich besonders zum demonstrieren und zum Einstieg in Sortieralgorithmen, da er sehr "
				+ "leicht zu verstehen ist. In "
				+ "der Praxis wird der Bubblesort eigentlich nicht eingesetzt, da der Bubblesort im Vergleich zu anderen Sortier"
				+ "verfahren (zum Beispiel QuickSort oder MergeSort) sehr zeitaufwendig ist.</p>"
				+ "<br><br>"
				+ "<h3>Laufzeiten</h3>"
				+ "<p>Worst-Case: O(n²)<br>"
				+ "Average-Case: O(n²)<br>"
				+ "</p>"
				+ "</html>";
	}
	
	@Override
	public JLabel getGif(){
		return new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/resources/images/FeelsBadMan.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
	}
	
}