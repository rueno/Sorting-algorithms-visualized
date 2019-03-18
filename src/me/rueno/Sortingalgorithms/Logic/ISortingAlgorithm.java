package me.rueno.Sortingalgorithms.Logic;

public interface ISortingAlgorithm{
	
	public default String getName(){
		return this.getClass().getSimpleName();
	}
	
	public long mearsureSortTime();
	public <C extends Comparable<C>> void swapVisualized(C[] list, int posX, int posY, int currentSpeed);
	public <C extends Comparable<C>> int compareVisualized(C[] list, int posX, int posY, int currentSpeed);
	public <C extends Comparable<C>> void sortVisualized(C[] list);
	
}