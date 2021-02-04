package mancala.domain;

public class Bowl extends Wells {

	public Bowl(Player p) {
		
		this.owner = p;
		this.stones = 4;
		this.neigborBowl = new Bowl(p, 1);
		this.getBowlAway(13).neigborBowl = this;
		this.setOpposite();
		
		this.owner.setKalaha(this.getBowlAway(6));
		this.owner.getOppenent().setKalaha(this.getBowlAway(13));
		
	}
	
	public Bowl(Player p, int number) {
		this.stones = 4;
		if (number <= 4) {
			this.owner = p;
			this.neigborBowl = new Bowl(p, number +1);	
		}
		else if(number == 5) {
			this.owner = p;
			this.neigborBowl = new Kalaha(p, number +1);
		}
		else if(number >=7 && number < 12) {
			this.owner = p.getOppenent();
			this.neigborBowl = new Bowl(p, number +1);
		}
		else if(number == 12) {
			this.owner = p.getOppenent();
			this.neigborBowl = new Kalaha(p,number +1);
		}
	}
	
	private void setOpposite() {
		int counter = 0;
		int oppCounter = 12;
		
		for(int i = 0;i < 6;i ++) {
			this.getBowlAway(counter).opposite = this.getBowlAway(oppCounter);
			counter ++;
			oppCounter --;
		}
		counter = 7;
		oppCounter = 5;
		
		for(int i = 7;i < 13;i ++) {
			this.getBowlAway(counter).opposite = this.getBowlAway(oppCounter);
			counter ++;
			oppCounter --;
		}
	}
	
	@Override
	protected void passStones(int i) {
		if(i == 1 && this.getStones() == 0 && this.getOwner().getActive() == true) {   
			steal(this);
			OppenentsTurn();
		}
		else{
			this.stones ++;
			if(i > 1) {this.neigborBowl.passStones(i-1);}
			else {OppenentsTurn();}
		}
	}
	
	
	private void steal(Wells bowl) {
		int oppStones = bowl.getOpposite().getStones();
		bowl.getOpposite().clear();
		oppStones ++;
		bowl.clear();
		bowl.getOwner().getKalaha().addStones(oppStones);
	}

	
	private void checkInput(int i) throws Exception{
		if(i == 6 || i == 13) {
			throw new Exception("you can't chooce a kalaha!");
		}
		else if(i < 0 || i > 13) {
			throw new Exception("The given number must be between 0 and 13!");
		}
		else if(this.getBowlAway(i).getStones() ==0) {
			throw new Exception("you can't chooce a empty Bowl!");
		}
	}
	
	public void playMove(int i) throws Exception {
	
		checkInput(i);
		
		Wells temp = this.getBowlAway(i);
		int temp_stones = temp.stones;
		temp.clear();
		temp.neigborBowl.passStones(temp_stones);
		}

	protected void setMidGame(int[] board) {
		for(int i = 0; i < 14; i++) {
			this.getBowlAway(i).stones = board[i];
		}
	}
	
	@Override
	protected int sideEmptyHelper(Player player,int Empty,int count) {
		if(count < 14) {
			if(this.owner == player && this.stones == 0) {
				Empty ++;
			}
			return Empty + this.neigborBowl.sideEmptyHelper(player,0,count +1);
		}
		return Empty;
	}
	
	@Override
	protected void playerGetStonesHelper(Player player,int count) {
		if(count < 14) {
			if(this.getOwner() == player) {
				player.getKalaha().addStones(this.stones);
				this.clear();
			}
			this.neigborBowl.playerGetStonesHelper(player,count +1);
		}
	}

}
