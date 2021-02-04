package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScenarioTest {

	@Test
	void SetA4StartingTest() throws Exception{
		Bowl foo= new Bowl(new Player("niels","kees"));
		
		foo.playMove(3);
		
		assertEquals(5,foo.getBowlAway(7).stones);
	}
	
	@Test
	void setEmptyBowl() throws Exception{
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 4,0,4,5,0,4,0,4,4,4,4,4,4,0 });

		Exception exception = assertThrows(Exception.class,() -> foo.playMove(1));
		assertEquals("you can't chooce a empty Bowl!", exception.getMessage());
		
	}
	
	
	@Test
	void SetA1Steal() throws Exception{
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 4,0,4,5,0,4,0,4,4,4,4,4,4,0 });
		
		foo.getOwner().setActive();
		foo.playMove(0);
		
		assertEquals(0,foo.getBowlAway(8).getStones());
	}
	
	@Test
	void SetA1EmptOppententl() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 4,0,4,5,0,4,0,4,0,4,4,4,4,0 });
		
		foo.getOwner().setActive();
		foo.playMove(0);
		
		assertEquals(1,foo.getBowlAway(6).getStones());
	}
	
	@Test
	void Set3CantStealOwnStones() throws Exception{
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 4,0,4,5,4,4,0,4,0,4,4,4,4,0 });
		
		foo.getOwner().setActive();
		
		foo.playMove(3);
		
		assertEquals(5,foo.getBowlAway(4).getStones());
	}
	
	@Test
	void SetA5GoAroundTheBoard() throws Exception{
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 4,0,4,5,4,13,0,4,0,4,4,4,4,0 });
		
		foo.getOwner().setActive();
		
		foo.playMove(5);
		
		assertEquals(0,foo.getBowlAway(7).getStones());
	}
	
	@Test
	void Set5BoardPlayerNielsisEmpty() throws Exception{
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 0,0,0,0,0,1,10,4,0,4,4,4,4,10 });
		
		foo.getOwner().setActive();
		
		foo.playMove(5);
		
		assertEquals(true,foo.sideEmpty(foo.owner));
	}
	
	@Test
	void playerOppenentGetsAllStones() throws Exception{
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 0,0,0,0,0,1,10,5,0,5,5,5,0,10 });
		foo.getOwner().setActive();
		
		foo.playerGetsStones(foo.owner.getOppenent());
		assertEquals(30,foo.owner.getOppenent().getKalaha().getStones());
	}
	
	@Test
	void checkWinner() {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 0,0,0,0,0,0,10,0,0,0,0,0,0,50 });
		assertEquals(foo.owner.getOppenent(),foo.owner.checkWinner());
	}	
}
