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
		return "<html>"
				+ "<h2 style=\"text-align:center;\">Shellsort</h2>"
				+ "<p style=\"text-align:justify;\">Shellsort bedient sich prinzipiell des Insertionsorts. Es versucht den Nachteil auszugleichen, dass hier Elemente in der Sequenz"
				+ " oft über weite Strecken verschoben werden müssen. Dies macht Insertionsort ineffizient. Shellsort verfolgt den Ansatz, dass die Sequenz zuerst in einzelne "
				+ "Untersequenzen zerlegt wird und diese sortiert werden. Die Aufteilung erfolgt in jedem Schritt in einer anderen Anzahl. Für die Aufteilung werden die "
				+ "Elemente nicht umkopiert, sondern die Elemente haben einen gewissen konstanten Abstand zueinander. Beispielsweise Faktor 4 bedeutet Aufteilung in 4 Untersequenzen,"
				+ " deren Elemente aus der Originalsequenz gebildet werden durch Abstand 4, also Indizes 0, 4, 8, … bildet eine Untersequenz, Indizes 1, 5, 9, … eine andere. Wurde der "
				+ "Sortierschritt vollzogen, so nennt man die Sequenz dann 4-sortiert. Der gesamte Shellsort führt dann beispielsweise zu einer Sequenz, die erst 4-sortiert wird, dann "
				+ "2-sortiert, und zuletzt mit normalem Insertionsort sozusagen 1-sortiert.<br><br>"
				+ "(Von <a href=\"https://de.wikipedia.org/wiki/Shellsort\">Wikipedia, der freien Enzyklopädie</a>, Zugriff am 01.04.2019)</p>"
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