package me.rueno.Sortingalgorithms.Logic;

public interface ISortingAlgorithm{
	
	public default String getName(){
		return this.getClass().getSimpleName();
	}
	
	public <C extends Comparable<C>> void swapVisualized(C[] list, int posX, int posY, int currentSpeed) throws InterruptedException;
	public <C extends Comparable<C>> int compareVisualized(C[] list, int posX, int posY, int currentSpeed) throws InterruptedException;
	public void markAsSorted(int posX) throws InterruptedException;
	public void markIntervalAsSortedPopup(int min, int max) throws InterruptedException;
	public <C extends Comparable<C>> void sortVisualized(C[] list) throws InterruptedException;
	public <C extends Comparable<C>> long[] measureAlgorithm(C[] list);
	public void normalizeDisplay();
	public String getAlgorithmInfoText();
	
}