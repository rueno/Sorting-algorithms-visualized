package me.rueno.Sortingalgorithms.Lists;

public enum ListType{
	
	RANDOM("Zufällig"),
	ASCENDING("Aufsteigend"),
	DESCENDING("Absteigend");
	
	private String displayName;
	
	private ListType(String displayName){
		this.displayName = displayName;
	}
	
	@Override
	public String toString(){
		return displayName;
	}
	
}