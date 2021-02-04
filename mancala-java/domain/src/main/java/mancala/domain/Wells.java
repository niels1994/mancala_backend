package mancala.domain;

public abstract class  Wells {
	
	protected int stones;
	protected Player owner;
	protected Wells neigborBowl;
	protected Wells opposite;

	
	
	public Wells getBowlAway(int count) {
		Wells temp;
		if(count == 0) {
			temp = this;
		}
		else {
			temp = this.neigborBowl.getBowlAway(count -1);
			}
		return temp;
		}
	
	public void addStones(int i) {this.stones += i;}
	
	public int getStones() {return this.stones;};
	
	public Wells getNeigborBowl() {return this.neigborBowl;}
	
	public Player getOwner() {return this.owner;}
	
	public Wells getOpposite() {return this.opposite;}
	
	protected abstract void passStones(int i);

	protected void clear() {
		this.stones = 0;
	}

	public boolean sideEmpty(Player player) {
		int empty = 0;
		empty = this.sideEmptyHelper(player,empty,0);
		if(empty == 6) {return true;}
		else {return false;}
	}

	protected abstract int sideEmptyHelper(Player player, int empty,int count);

	public void playerGetsStones(Player player) {
		this.neigborBowl.playerGetStonesHelper(player,0);
	}

	protected abstract void playerGetStonesHelper(Player player, int i);

	protected void OppenentsTurn() {
		if(this.owner.getActive() == true) {
			this.owner.getOppenent().setActive();
		}
		else {
			this.owner.setActive();
		}
	}
	
}