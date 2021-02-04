package mancala.domain;

public class Kalaha extends Wells{
	
	public Kalaha(Player p) {
		this.stones = 0;
		this.owner = p;
	
	}
	
	public Kalaha(Player p, int number) {
		this.stones = 0;
		if (number == 6) {
			this.owner = p;
			this.neigborBowl = new Bowl(p,number + 1);
		}
		
		else if (number == 13) {
			this.owner = p.getOppenent();
			this.neigborBowl = null;
		}
	}
	

	
	@Override
	protected void passStones(int i) {
		if(this.owner.getActive() == false) {
			this.neigborBowl.passStones(i);
		}
		
		else if(i == 1) {
			this.addStones(i);
			this.owner.setActive();	
		}									
			
		else if(i > 1) {
			this.stones ++;
			this.neigborBowl.passStones(i-1);
		}
	}
	
	@Override
	protected int sideEmptyHelper(Player player,int Empty,int count) {
		if(count < 14) {
			return Empty + this.neigborBowl.sideEmptyHelper(player,0,count +1);
		}
		return Empty;
	}
	
	@Override
	protected void playerGetStonesHelper(Player player,int count) {
		if(count < 14) {
			this.neigborBowl.playerGetStonesHelper(player,count +1);
		}
	}

} 