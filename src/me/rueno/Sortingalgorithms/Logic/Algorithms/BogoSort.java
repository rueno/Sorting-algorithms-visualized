package me.rueno.Sortingalgorithms.Logic.Algorithms;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class BogoSort extends DefaultVisualizedSortingAlgorithm{
	
	private Random random;
	
	public BogoSort(){
		this(null);
	}
	
	public BogoSort(JLabel[] lbls){
		super(lbls);
		this.random = new Random();
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list) throws InterruptedException{
		int a, b;
		int n = list.length;
		while(!isSortedVisualized(list)){
			a = random.nextInt(n);
			b = random.nextInt(n);
			
			swapVisualized(list, a, b, 4);
		}
	}
	
	private <C extends Comparable<C>> boolean isSortedVisualized(C[] list) throws InterruptedException{
		for(int i = 0; i < list.length-1; i++){
			if(compareVisualized(list, i, i+1, 4) > 0){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public JLabel getGif(){
		return new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/resources/images/FeelsBadMan.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
	}
	
	@Override
	public String getAlgorithmInfoText(){
		return "<html>"
				+ "<h2 style=\"text-align:center;font-family:sans-serif;\">Bogosort</h2>"
				+ "<p style=\"text-align:justify;font-family:sans-serif;\">Bogosort, Alexsort, Monkeysort oder Stupidsort bezeichnet ein nicht-stabiles Sortierverfahren, bei dem die Elemente"
				+ " so lange zufällig gemischt werden, bis sie sortiert sind. Wegen der langen Laufzeit ist Bogosort der Prototyp eines schlechten Algorithmus. Bogosort wird"
				+ " insbesondere in der Informatik-Ausbildung in den Bereichen Datenstrukturen und Algorithmen verwendet, um an einem Extrembeispiel die Eigenschaften von Sortierverfahren"
				+ " im Allgemeinen zu verdeutlichen.<br><br>"
				+ "(Von <a href=\"https://de.wikipedia.org/wiki/Bogosort\">Wikipedia, der freien Enzyklopädie</a>, Zugriff am 01.04.2019)</p>"
				+ "</html>";
	}

	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		return new long[] {0, 0};
	}
	
}