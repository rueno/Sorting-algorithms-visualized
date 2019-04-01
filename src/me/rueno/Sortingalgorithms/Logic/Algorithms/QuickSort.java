package me.rueno.Sortingalgorithms.Logic.Algorithms;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class QuickSort extends DefaultVisualizedSortingAlgorithm{
	
	public QuickSort(){
		this(null);
	}
	
	public QuickSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list)  throws InterruptedException{
		qSortVis(list, 0, list.length-1);
	}
	
	private <C extends Comparable<C>> void qSortVis(C[] list, int start, int end)  throws InterruptedException{
		if(start >= end) return;
		
		int middle = start + (end - start) / 2;
		C pivot = list[middle];
		
		int i = start, j = end;
		while(i <= j){
			while(compareVisualized(list, i, indexOf(list, pivot), 4) < 0) i++;
			while(compareVisualized(list, j, indexOf(list, pivot), 4) > 0) j--;
			
			if(i <= j){
				swapVisualized(list, i, j, 4);
				i++;
				j--;
			}
		}
		
		if(start < j) qSortVis(list, start, j);
		if(end > i) qSortVis(list, i, end);
		
	}
	
	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		long[] data = qSort(list, 0, list.length-1);
		return data;
	}
	
	/**
	 * @return {resaves, comparasons}
	 */
	private <C extends Comparable<C>> long[] qSort(C[] list, int start, int end){
		if(start >= end) return new long[] {0, 0};
		
		long resaves = 0, comparasons = 0;
		
		int pivIndex = start + (end - start) / 2;
		C pivot = list[pivIndex]; resaves++;
		
		int i = start, j = end;
		
		while(i <= j){
			while(list[i].compareTo(pivot) < 0){ comparasons++;
				i++;
			} comparasons++;
			while(list[j].compareTo(pivot) > 0){ comparasons++;
				j--;
			}
			
			if(i <= j){
				swap(list, i, j); resaves += 3;
				i++;
				j--;
			}
		}
		
		if(start < j){
			long[] data = qSort(list, start, j);
			resaves += data[0];
			comparasons += data[1];
		}
		if(end > i){
			long[] data = qSort(list, i, end);
			resaves += data[0];
			comparasons += data[1];
		}
		return new long[] {resaves, comparasons};
	}
	
	@Override
	public String getAlgorithmInfoText(){
		return "<html>"
				+ "<h2 style=\"text-align:center;font-family:sans-serif;\">Quicksort</h2>"
				+ "<p style=\"text-align:justify;font-family:sans-serif;\">Quicksort (englisch quick ‚schnell‘ und to sort ‚sortieren‘) ist ein schneller, rekursiver, "
				+ "nicht-stabiler Sortieralgorithmus, der nach dem Prinzip Teile und herrsche (lateinisch Divide et impera!, englisch divide and conquer) "
				+ "arbeitet. Er wurde ca. 1960 von C. Antony R. Hoare in seiner Grundform entwickelt[1] und seitdem von vielen Forschern verbessert. Der Algorithmus"
				+ " hat den Vorteil, dass er über eine sehr kurze innere Schleife verfügt (was die Ausführungsgeschwindigkeit stark erhöht) und ohne zusätzlichen Speicherplatz auskommt "
				+ "(abgesehen von dem für die Rekursion zusätzlichen benötigten Platz auf dem Aufruf-Stack).<br>"
				+ "Zunächst wird die zu sortierende Liste in zwei Teillisten („linke“ und „rechte“ Teilliste) getrennt. Dazu wählt Quicksort "
				+ "ein sogenanntes Pivotelement aus der Liste aus. Alle Elemente, die kleiner als das Pivotelement sind, kommen in die linke"
				+ " Teilliste, und alle, die größer sind, in die rechte Teilliste. Die Elemente, die gleich dem Pivotelement sind, können sich beliebig auf die Teillisten verteilen. Nach der Aufteilung sind die Elemente der linken Liste kleiner oder gleich den Elementen der rechten Liste.\r\n" + 
				"Anschließend muss man also nur noch jede Teilliste in sich sortieren, um die Sortierung zu vollenden. Dazu wird der Quicksort-Algorithmus "
				+ "jeweils auf der linken und auf der rechten Teilliste ausgeführt. Jede Teilliste wird dann wieder in zwei Teillisten aufgeteilt und auf diese jeweils wieder der Quicksort-Algorithmus angewandt, und so fort. Diese Selbstaufrufe werden als Rekursion bezeichnet. Wenn eine Teilliste der Länge eins oder null auftritt, so ist diese bereits sortiert und es erfolgt der Abbruch der Rekursion.\r\n" + 
				"Die Positionen der Elemente, die gleich dem Pivotelement sind, hängen vom verwendeten Teilungsalgorithmus ab. Sie können sich beliebig "
				+ "auf die Teillisten verteilen. Da sich die Reihenfolge von gleichwertigen Elementen zueinander ändern kann, ist Quicksort im Allgemeinen "
				+ "nicht stabil (stabile in-place Varianten existieren jedoch[3]). Das Verfahren muss sicherstellen, dass jede der Teillisten mindestens um eins "
				+ "kürzer ist als die Gesamtliste. Dann endet die Rekursion garantiert nach endlich vielen Schritten. Das kann z. B. dadurch erreicht werden, "
				+ "dass das ursprünglich als Pivot gewählte Element auf einen Platz zwischen den Teillisten gesetzt wird und somit zu keiner Teilliste gehört.<br><br>"
				+ "(Von <a href=\"https://de.wikipedia.org/wiki/Quicksort\">Wikipedia, der freien Enzyklopädie</a>, Zugriff am 01.04.2019)</p>"
				+ "</html>";
	}
	
	@Override
	public JLabel getGif(){
		return new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/resources/images/POGGERS.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
	}
	
}