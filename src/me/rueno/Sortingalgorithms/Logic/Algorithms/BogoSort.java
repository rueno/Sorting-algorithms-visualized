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
		while(!isSortedVisualized(list) && !shouldInterrupt()){
			a = random.nextInt(n);
			b = random.nextInt(n);
			
			swapVisualized(list, a, b, 4);
		}
	}
	
	private <C extends Comparable<C>> boolean isSortedVisualized(C[] list) throws InterruptedException{
		for(int i = 0; i < list.length-1 && !shouldInterrupt(); i++){
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
				+ "<h2 style=\"text-align:center;\">Bogosort</h2>"
				+ "<p style=\"text-align:justify;\">Der Bogosort (Sortieren durch Zufall) sortiert eine Liste überprüfen auf Sortiertheit und vertauschen von zwei "
				+ " zufälligen Elementen."
				+ " Der Algorithmus existiert, um die Komplexität von Laufzeitanalysen und schlechten Algorithmen zu verde"
				+ "utlichen. In der Praxis wird der Bogosort eigentlich nicht eingesetzt, da der Bogosort im Vergleich zu anderen Sortier"
				+ "verfahren (zum Beispiel QuickSort oder MergeSort) sehr zeitaufwendig ist. Die Laufzeiten zu errechnen ist eigentlich unmöglich, da"
				+ " durch zufälliges Tauschen von zwei Elementen theoretisch eine Laufzeit von undendlich Zeit benötigt werden kann. Da der Algorithmus zufällig sortiert, "
				+ "Gibt es keinen Worst-Case und keinen Average-Case. Der Best-Case ist eine bereits sortierte Liste, da der Algorithmus sofort erkennt, das diese bereits in "
				+ "sortierter Form vorliegt und so nichts weiter sortieren muss.</p>"
				+ "<br><br>"
				+ "<h3>Laufzeiten</h3>"
				+ "Sind unmöglich zu ermitteln"
				+ "</p>"
				+ "</html>";
	}

	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		return new long[] {0, 0};
	}
	
}