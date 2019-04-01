package me.rueno.Sortingalgorithms.Logic.Algorithms;

import java.awt.Image;

import javax.swing.ImageIcon;
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
		
		while(i < (n-1)){
			j = i + 1;
			minIndex = i;
			
			while(j < n){
				if(list[j].compareTo(list[minIndex]) < 0){ comparations++;
					minIndex = j; resaves++;
				}
				j++;
			}
			swap(list, i, minIndex); resaves += 3;
			i++; resaves++;
		}
		
		return new long [] {resaves, comparations};
	}
	
	@Override
	public String getAlgorithmInfoText(){
		return "<html>"
				+ "<h2 style=\"text-align:center;\">Selectionsort</h2>"
				+ "<p style=\"text-align:justify;\">Selectionsort (englisch selection ‚Auswahl‘ und englisch sort ‚sortieren‘) ist ein einfacher („naiver“) Sortieralgorithmus, der"
				+ " in-place arbeitet und in seiner Grundform instabil ist, wobei er sich auch stabil implementieren lässt. "
				+ "Alternative Bezeichnungen des Algorithmus sind MinSort (von Minimum) bzw. MaxSort (von Maximum), Selectsort oder ExchangeSort (AustauschSort).<br>"
				+ "Sei S der sortierte Teil des Arrays (vorne im Array) und U der unsortierte Teil (dahinter). Am Anfang ist S noch leer, U entspricht dem ganzen (restlichen) Array."
				+ " Das Sortieren durch Auswählen läuft nun folgendermaßen ab:" + 
				"Suche das kleinste Element in U und vertausche es mit dem ersten Element von U (= das erste Element nach S)." +  
				"Danach ist das Array bis zu dieser Position sortiert. Das kleinste Element wird in S verschoben (indem S einfach als ein "
				+ "Element länger betrachtet wird, und U nun ein Element später beginnt). S ist um ein Element gewachsen, U um ein Element kürzer geworden."
				+ " Anschließend wird das Verfahren so lange wiederholt, bis das gesamte Array abgearbeitet worden ist; S umfasst am Ende das gesamte Array,"
				+ " aufsteigend sortiert, U ist leer.<br><br>"
				+ "(Von <a href=\"https://de.wikipedia.org/wiki/Selectionsort\">Wikipedia, der freien Enzyklopädie</a>, Zugriff am 01.04.2019)</p>"
				+ "<br><br>"
				+ "<h3>Laufzeiten</h3>"
				+ "Average-Case: O(n²)<br>"
				+ "Worst-Case: O(n²)"
				+ "</p>"
				+ "</html>";
	}
	
	@Override
	public JLabel getGif(){
		return new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/resources/images/FeelsBadMan.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
	}
	
}