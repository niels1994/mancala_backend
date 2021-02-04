package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import java.lang.Exception;

class BowlTest {

	@Test
	public void aNormalBowlStartsWith4Stones() {
		Bowl foo = new Bowl(new Player("niels", "kees"));
		assertEquals(4, foo.getStones());
		
	}
	
	@Test
	public void BowlKalahaGetBowl3AwayGot4Stones() {
		Bowl foo = new Bowl(new Player("niels", "kees"));
		Wells test = foo.getBowlAway(4);
		assertEquals(4,test.getStones());
	}
	
	@Test
	public void BowlKalahaGetBowl6AwayGot0Stones() {
		Bowl foo = new Bowl(new Player("niels", "kees"));
		Wells test = foo.getBowlAway(6);
		assertEquals(0,test.getStones());
	}
	
	@Test
	void getOppesiteOfBowl4Stones() {
		Bowl foo = new Bowl(new Player("niels", "kees"));
		
		assertEquals(4,foo.getBowlAway(2).getOpposite().getStones());
	}
	
	@Test
	void checkOwnerOfBowl() {
		Bowl foo = new Bowl(new Player("niels", "kees"));
		
		assertEquals("niels",foo.owner.getName());
	}
	
	@Test
	void checkOppenentNmameOfBowl() {
		Bowl foo = new Bowl(new Player("niels", "kees"));
		
		assertEquals("kees",foo.owner.getOppenent().getName());
	}

	@Test
	void passStonesFromBowl_seccondBowl5Stones() {
		Bowl foo = new Bowl(new Player("niels", "kees"));
		
		foo.getBowlAway(0).passStones(4);
		
		assertEquals(5,foo.getBowlAway(1).stones);
		
	}
	
	@Test
	void setBowlAschoice_bowlIsEmpty() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		
		foo.playMove(0);
		
		assertEquals(0,foo.getBowlAway(0).stones);
	}
	
	@Test
	void setBowlAsChoice_Kalaha1Stone() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.getOwner().setActive();
		foo.playMove(5);
		
		assertEquals(1,foo.getBowlAway(6).stones);
	}
	
	@Test
	void setBowlAsChoice_6Or13_doNothing() {
		Bowl foo= new Bowl(new Player("niels","kees"));
		
		Exception exception = assertThrows(Exception.class,() -> foo.playMove(6));
		
		assertEquals("you can't chooce a kalaha!", exception.getMessage());
	}
	
	@Test
	void setBowlasChoice0_5Has4Stones() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		
		foo.playMove(0);
		
		assertEquals(4,foo.getBowlAway(5).stones);
	}
	
	@Test
	void setBowlasChoice0_4Has5Stones() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		
		foo.playMove(0);
		
		assertEquals(5,foo.getBowlAway(4).stones);
	}
	
	@Test
	void StealBowl5_bowl4IsEmpty() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.getOwner().setActive();
		foo.playMove(4);
		foo.getOwner().setActive();
		foo.playMove(0);
		
		assertEquals(0,foo.getBowlAway(4).getStones());
		
	}
	
	@Test
	void StealBowl5_Bowl8IsEmpty() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.getOwner().setActive();
		foo.playMove(4);
		foo.getOwner().setActive();
		foo.playMove(0);
		
		assertEquals(0,foo.getBowlAway(8).getStones());
	}
	
	@Test
	void StealBowl5_KalahaPlayerActiveHas7() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.getOwner().setActive();
		foo.playMove(4);
		foo.playMove(0);
		
		assertEquals(5,foo.getBowlAway(7).getStones());
	}
	
	@Test
	void setBowlThatIsEmpty() {
		Bowl foo = new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 4,0,4,5,0,4,0,4,4,4,4,4,4,0 });
		Exception exception = assertThrows(Exception.class,() -> foo.playMove(1));
		assertEquals("you can't chooce a empty Bowl!", exception.getMessage());
		
	}
	
	@Test
	void setBowlHigherThan15() {
		Bowl foo = new Bowl(new Player("niels","kees"));
		Exception exception = assertThrows(Exception.class,() -> foo.playMove(17));
		assertEquals("The given number must be between 0 and 13!", exception.getMessage());
		
	}
	
	@Test
	void checkActiveStatusAfterNormalTurn() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.playMove(5);
		assertEquals(true,foo.owner.getOppenent().getActive());
	}
	
	@Test
	void checkActiveStatusAfterSteal() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 4,0,4,5,0,4,0,4,4,4,4,4,4,0 });
		foo.getOwner().setActive();
		foo.playMove(0);
		
		assertEquals(true,foo.owner.getOppenent().getActive());
	}
	
	@Test
	void checkActiveStatusEmptyOppentBowl() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.setMidGame(new int[]{ 4,4,4,5,3,2,10,0,4,4,4,4,4,10 });
		foo.getOwner().setActive();
		foo.playMove(4);
		
		assertEquals(true,foo.owner.getOppenent().getActive());
	}
	
}
