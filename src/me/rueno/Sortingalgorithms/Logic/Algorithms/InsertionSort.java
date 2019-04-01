package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;
import me.rueno.Sortingalgorithms.UI.Components.FeelsOhWait;

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
			markIntervalAsSortedPopup(0, i);
			i++;
		}
		for(int i1 = 0; i1 < list.length; i1++){
			markAsSorted(i1);
		}
	}
	
	protected <C extends Comparable<C>> long[] sort(C[] list){
		long resaves = 0, comparations = 0;
		
		int n = list.length;
		int i = 1;
		int j;
		while(i < n){
			j = i; resaves++;
			while(j > 0){
				if(list[j].compareTo(list[j-1]) < 0){ comparations++;
					swap(list, j, j-1); resaves += 3;
					j--;
				}else {
					j = 0;
				}
			}
			i++;
		}
		return new long[] {resaves, comparations};
	}
	
	@Override
	public String getAlgorithmInfoText(){
		return "<html>"
				+ "<h2 style=\"text-align:center;\">Insertionsort</h2>"
				+ "<p style=\"text-align:justify;\">nsertionsort (auch Einfügesortierenmethode oder Sortieren durch Einfügen, englisch insertion ‚Einfügung‘ und englisch sort ‚sortieren‘) ist ein einfaches stabiles Sortierverfahren (d. h. die Reihenfolge von Elementen mit gleichem Schlüsselwert bleibt unverändert). Es ist leicht zu implementieren, effizient bei kleinen oder bereits teilweise sortierten Eingabemengen. Außerdem benötigt Insertionsort keinen zusätzlichen Speicherplatz, da der Algorithmus in-place arbeitet. Ein weiterer Vorteil besteht darin, dass Insertionsort als Online-Algorithmus eingesetzt werden kann.\r\n" + 
				"\r\n" + 
				"Insertionsort entnimmt der unsortierten Eingabefolge ein beliebiges Element und fügt es an richtiger Stelle in die (anfangs leere) Ausgabefolge ein. Geht man hierbei "
				+ "in der Reihenfolge der ursprünglichen Folge vor, so ist das Verfahren stabil. Wird auf einem Array gearbeitet, so müssen die Elemente hinter dem neu eingefügten"
				+ " Element verschoben werden. Dies ist die eigentlich aufwändige Operation von Insertionsort. Das Auffinden der richtigen Einfügeposition kann über eine binäre Suche "
				+ "vergleichsweise effizient erfolgen. Grundsätzlich gilt aber, dass Insertionsort weit weniger effizient arbeitet als andere anspruchsvollere Sortierverfahren.<br><br>"
				+ "(Von <a href=\"https://de.wikipedia.org/wiki/Insertionsort\">Wikipedia, der freien Enzyklopädie</a>, Zugriff am 01.04.2019)</p>"
				+ "<br><br>"
				+ "<h3>Laufzeiten</h3>"
				+ "Average-Case: O(n²)<br>"
				+ "Worst-Case: O(n²)"
				+ "</p>"
				+ "</html>";
	}
	
	@Override
	public JLabel getGif(){
		return new FeelsOhWait(25, 25);
	}
	
}