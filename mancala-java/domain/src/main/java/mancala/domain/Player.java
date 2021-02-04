package mancala.domain;

public class Player {
	private String name;
	private Player opponent;
	private Boolean isActive = false;
	private Wells ownKalaha;
	
	public Player (String name1,String name2){
		this.name = name1;
		this.opponent = new Player(name2);
		this.opponent.setOpponent(this);
		
	}
	Player(String name2) {
		this.name = name2;
		this.opponent = null;
	}
	
	private void setOpponent(Player name) {
		this.opponent = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Player getOppenent() {
		return this.opponent;
	}
	
	public Boolean getActive() {
		return isActive;
	}
	public void setActive() {
		this.isActive = true;
		this.getOppenent().isActive = false;
		
	}
	public Wells getKalaha() {
		return this.ownKalaha;
	}
	
	public void setKalaha(Wells kalaha) {
		this.ownKalaha = kalaha;
	}
	
	public Player checkWinner() {
		if(this.getKalaha().getStones() > this.getOppenent().getKalaha().getStones()) {
			return this;
		}
		else if (this.getKalaha().getStones() < this.getOppenent().getKalaha().getStones()) {
			return this.getOppenent();
		}
		else {return null;}
	}
	

}
