package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void PlayerMakesOppentent() {
		Player test = new Player("niels","kees");
		
		assertEquals("kees",test.getOppenent().getName());
	}
	
	@Test
	void PlayerASetActiveIsTrue() {
		Player test = new Player("niels","kees");
		test.setActive();
		
		assertEquals(true,test.getActive());
	}
	
	@Test
	void PlayerAssetActivePlayerBisFalse() {
		Player test = new Player("niels","kees");
		test.getOppenent().setActive();
		test.setActive();
		assertEquals(false,test.getOppenent().getActive());
	}
	
	@Test
	void PlayerAKalahaisBowl6() {
		Bowl foo= new Bowl(new Player("niels","kees"));
		
		Wells test = foo.getOwner().getKalaha();
		
		assertEquals(foo.getBowlAway(6),test);
	}

}
