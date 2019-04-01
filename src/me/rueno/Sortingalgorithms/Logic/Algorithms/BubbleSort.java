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
		
		while(i < (n - 1)){
			j = n - 1;
			while(j > i){
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
		
		while(i < (n - 1)){
			j = n - 1;
			while(j > i){
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
				+ "<h2 style=\"text-align:center;font-family:sans-serif;\">Bubblesort</h2>"
				+ "<p style=\"text-align:justify;font-family:sans-serif;\">Bubblesort (auch Sortieren durch Aufsteigen oder Austauschsortieren) "
				+ "ist ein Algorithmus, der vergleichsbasiert eine Liste von Elementen sortiert. Dieses Sortierverfahren"
				+ " arbeitet in-place und sortiert stabil. In der Praxis wird Bubblesort kaum eingesetzt, da andere Verfahren ein besseres Laufzeitverhalten haben. "
				+ "Der Algorithmus spielt allerdings in der Lehre eine Rolle, da er als einfach zu erklären bzw. zu demonstrieren gilt. Des Weiteren "
				+ "eignet sich der Algorithmus, um Techniken wie schrittweise Optimierungen, Laufzeit- bzw. Komplexitäts- und Korrektheitsanalyse einzuführen.<br>"
				+ "In der Bubble-Phase wird die Eingabe-Liste von links nach rechts durchlaufen. Dabei wird in jedem Schritt das aktuelle Element mit dem rechten Nachbarn verglichen. Falls die beiden Elemente das Sortierkriterium verletzen, werden sie getauscht. Am Ende der Phase steht bei auf- bzw. absteigender Sortierung das größte bzw. kleinste Element der Eingabe am Ende der Liste.\r\n" + 
				"Die Bubble-Phase wird solange wiederholt, bis die Eingabeliste vollständig sortiert ist. Dabei muss das letzte Element des vorherigen "
				+ "Durchlaufs nicht mehr betrachtet werden, da die restliche zu sortierende Eingabe keine größeren bzw. kleineren Elemente mehr enthält." + 
				"Je nachdem, ob auf- oder absteigend sortiert wird, steigen die größeren oder kleineren Elemente wie Blasen im Wasser (daher der Name) immer"
				+ " weiter nach oben, das heißt, an das Ende der Liste. Es werden stets zwei Zahlen miteinander in „Bubbles“ vertauscht.<br><br>"
				+ "(Von <a href=\"https://de.wikipedia.org/wiki/Bubblesort\">Wikipedia, der freien Enzyklopädie</a>, Zugriff am 01.04.2019)</p>"
				+ "</html>";
	}
	
	@Override
	public JLabel getGif(){
		return new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/resources/images/FeelsBadMan.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
	}
	
}