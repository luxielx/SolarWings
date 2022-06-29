package Wings;

import org.bukkit.entity.Player;

public enum WingType {
	
	EAGLE(1 , "Eagle",100), FAIRY(2 , "Fairy",100),DRAGON(3 , "Dragon",100),BUTTERFLY(4,"Butterfly", 120);
	
	
	int id;
	String name = null;
	int price;
	private WingType(int i , String name , int price){
		this.id = i;
		this.name = name;
		this.price = price;
	}
	public int getid(){
		return id;
	}
	public String getName(){
		return name;
	}
	public int getPrice(){
		return price;
	}
	public void display(Player player){
		
	   switch(this.id){
	   case(1): EagleWing.display(player); break;
	   case(2): FairyWing.display(player); break;
	   case(3): DragonWing.display(player); break;
	   case(4): Butterfly.display(player); break;
	   
	   }
	   
		
	}
	public void displayTrait(Player player) {
		switch(this.id){
		   case(1): EagleWing.displayTrait(player); break;
		   case(2): FairyWing.displayTrait(player); break;
		   case(3): DragonWing.displayTrait(player); break;
		   case(4): Butterfly.displayTrait(player); break;
		   }
		
	}

}
