package mancala.domain;

// Your test class should be in the same 
// package as the class you're testing.
// Usually the test directory mirrors the
// main directory 1:1. So for each class in src/main,
// there is a class in src/test.

// Import our test dependencies. We import the Test-attribute
// and a set of assertions.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class KalahaTest {
	
	@Test
	public void aNormalKalahaStartsWith0Stones() {
		Wells foo = new Kalaha(new Player("niels", "kees"));
		assertEquals(0, foo.getStones());
	}
	
	@Test
	public void addStonesTokalaha10() {
		Wells foo = new Kalaha(new Player("niels", "kees"));
		foo.addStones(10);
		assertEquals(10,foo.getStones());
	}
	
	@Test
	public void addStonesToKalahaTwoTimes() {
		Wells foo = new Kalaha(new Player("niels", "kees"));
		foo.addStones(10);
		foo.addStones(10);
		assertEquals(20,foo.getStones());
	}
	
	@Test
	void checkActiveStatusAfterKalahaEnd() throws Exception {
		Bowl foo= new Bowl(new Player("niels","kees"));
		foo.getOwner().setActive();
		foo.playMove(2);
		
		assertEquals(false,foo.owner.getOppenent().getActive());
	}
	
	
}

