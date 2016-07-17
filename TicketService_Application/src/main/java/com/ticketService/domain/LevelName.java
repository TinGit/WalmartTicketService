package com.ticketService.domain;

public enum LevelName {

	ORCHESTRA("Orchestra"),MAIN("Main"),BALCONY1("Balcony 1"),BALCONY2("Balcony 2");
	
	private String name;

    LevelName(String name){
		this.name= name;
	}
	
	public String getName() {
		return name;
	}

	public String toString(){
		return this.name;
	}
	
	
}
