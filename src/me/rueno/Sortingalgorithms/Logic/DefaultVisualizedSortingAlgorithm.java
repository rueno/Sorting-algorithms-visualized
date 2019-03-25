package me.rueno.Sortingalgorithms.Logic;

import java.awt.Color;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.UIManager;

import me.rueno.Sortingalgorithms.Misc.GlobalVars;

public abstract class DefaultVisualizedSortingAlgorithm implements ISortingAlgorithm{
	
	private final Color defaultBackground;
	
	private JLabel[] labels;
	
	public DefaultVisualizedSortingAlgorithm(JLabel[] panels){
		this.labels = panels;
		this.defaultBackground = UIManager.getColor("panel.background");
	}
	
	public void setNewLabels(JLabel[] labels){
		this.labels = labels;
	}
	
	/**
	 * @return new long[] {resaves, comparations};
	 */
	protected abstract <C extends Comparable<C>> long[] sort(C[] list);
	
	protected final <C extends Comparable<C>> int indexOf(C[] list, C c){
		return Arrays.asList(list).indexOf(c);
	}
	
	protected final <C extends Comparable<C>> boolean isSorted(C[] list){
		for(int i = 0; i < list.length-1; i++){
			if(list[i].compareTo(list[i+1]) > 0){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public abstract String getAlgorithmInfoText();
	
	@Override
	public final <C extends Comparable<C>> long[] measureAlgorithm(C[] list) {
		long start = System.currentTimeMillis();
		long[] sort = sort(list);
		return new long[] { (System.currentTimeMillis() - start), sort[0], sort[1] };
	}
	
	@Override
	public final void markAsSorted(int posX){
		labels[posX].setBackground(Color.GREEN);
		sleepAppropiateAmountOfTimeForCompare();
	}
	
	public final void markIntervalAsSortedPopup(int min, int max){
		for(int i = min; i <= max; i++){
			labels[i].setBackground(Color.GREEN);
		}
		sleepAppropiateAmountOfTimeForCompare();
		sleepAppropiateAmountOfTimeForCompare();
		for(int i = min; i <= max; i++){
			labels[i].setBackground(defaultBackground);
		}
	}
	
	@Override
	public abstract <C extends Comparable<C>> void sortVisualized(C[] list);
	
	@Override
	public final <C extends Comparable<C>> void swapVisualized(C[] list, int posX, int posY, int currentSpeed){
		if(posX == posY){
			JLabel lbl = labels[posX];
			lbl.setBackground(Color.CYAN);
			sleepAppropiateAmountOfTimeForCompare();
			lbl.setBackground(defaultBackground);
			sleepAfterStep();
			return;
		}
		
		C temp = list[posX];
		list[posX] = list[posY];
		list[posY] = temp;
		
		JLabel panelX = labels[posX];
		JLabel panelY = labels[posY];
		
		panelX.setBackground(Color.CYAN);
		panelY.setBackground(Color.CYAN);
		
		//runter schieben
		for(int currentYDeviation = 0; currentYDeviation < (int) (panelX.getHeight() * 1.5D); currentYDeviation += currentSpeed){
			panelX.setLocation(panelX.getX(), panelX.getY() + currentSpeed);
			panelY.setLocation(panelY.getX(), panelY.getY() + currentSpeed);
			sleepAppropiateAmountOfTimeForSwap();
		}
		
		//Zum jeweiligen anderen Standpunkt verschieben
		int deviationX, deviationY;
		
		int difFromXToY = labels[posX].getX() - labels[posY].getX();
		int difFromYToX = -difFromXToY;
		
		int loops = Math.abs(difFromXToY / currentSpeed);
		deviationX = difFromYToX / loops;
		deviationY = difFromXToY / loops;
		
		
		for(int i = 0; i < loops; i++) {
//			PanelX verschieben
			panelX.setLocation(panelX.getX() + deviationX, panelX.getY());
			//PanelY verschieben
			panelY.setLocation(panelY.getX() + deviationY, panelY.getY());
			sleepAppropiateAmountOfTimeForSwap();
		}
		
		panelX.setLocation((int) (panelX.getX() + difFromYToX % currentSpeed), panelX.getY());
		panelY.setLocation((int) (panelY.getX() + difFromXToY % currentSpeed), panelY.getY());
		
//		while(panelX.getX() != y){
//			//PanelX verschieben
//			panelX.setLocation(panelX.getX() + 1, panelX.getY());
//			//PanelY verschieben
//			panelY.setLocation(panelY.getX() - 1, panelY.getY());
//			sleepAppropiateAmountOfTimeForSwap(currentSpeed);
//		}
		
		//Hinauf schieben (Fast �quivalent zu runter schieben, eventuell eine Mehtode anlegen?
		for(int currentYDeviation = (int) (panelX.getHeight() * 1.5D); currentYDeviation > 0; currentYDeviation -= currentSpeed){
			panelX.setLocation(panelX.getX(), panelX.getY() - currentSpeed);
			panelY.setLocation(panelY.getX(), panelY.getY() - currentSpeed);
			sleepAppropiateAmountOfTimeForSwap();
		}
		
		//Jetzt noch die Panel im Panelarray vertauschen
		JLabel tempPanel = labels[posX];
		labels[posX] = labels[posY];
		labels[posY] = tempPanel;
		
		labels[posX].setBackground(defaultBackground);
		labels[posY].setBackground(defaultBackground);
		sleepAfterStep();
	}
	
	protected <C extends Comparable<C>> void swap(C[] list, int posX, int posY){
		C temp = list[posX];
		list[posX] = list[posY];
		list[posY] = temp;
	}
	
	/**
	 * Visualises a comparation. Equivalent to calling list[posX].compareTo(list[posY])
	 */
	@Override
	public final <C extends Comparable<C>> int compareVisualized(C[] list, int posX, int posY, int speed){
		//Visualisieren
		if(posX == posY) list[posX].compareTo(list[posY]);
		labels[posX].setBackground(Color.RED);
		labels[posY].setBackground(Color.RED);
		sleepAppropiateAmountOfTimeForCompare();
		
		labels[posX].setBackground(defaultBackground);
		labels[posY].setBackground(defaultBackground);
		sleepAfterStep();
		
		return list[posX].compareTo(list[posY]);
	}
	
	private void sleepAppropiateAmountOfTimeForCompare(){
		try{
			Thread.sleep((long) (500 * (2 - GlobalVars.SPEED_MULTIPLIER)));
		}catch(InterruptedException interrupted){
			interrupted.printStackTrace();
		}
	}
	
	private void sleepAfterStep(){
		try{
			Thread.sleep((long) (300 * (2 - GlobalVars.SPEED_MULTIPLIER)));
		}catch(InterruptedException interrupted){
			interrupted.printStackTrace();
		}
	}
	
	private void sleepAppropiateAmountOfTimeForSwap(){
		try{
			Thread.sleep((long) (17 * (2 - GlobalVars.SPEED_MULTIPLIER)));
		}catch(InterruptedException interrupted){
			interrupted.printStackTrace();
		}
	}
	
}