//Student ID: 14009228
//Date: 20 November 2015

package W10Practical;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

	private IBinarySearchTree sut;
	
	@Before
	public void setUpTree() {
		sut = new BinarySearchTree();
	}
	
	@Test
	public void size() {
		assertEquals(0, sut.size());
	
		sut.add(5);
		assertEquals(1, sut.size());
		
		sut.add(4);
		assertEquals(2, sut.size());
	}

	@Test
	public void add() {
		sut.add(5);
		sut.add(4);
		assertEquals(2, sut.size());
		assertTrue(sut.contains(5) && sut.contains(4));
	}
	
	@Test
	public void addDuplicateElem() {
		sut.add(5);
		sut.add(4);
		sut.add(3);
		sut.add(4);
		
		assertEquals(3, sut.size());
	}
	
	@Test
	public void contains() {
		assertFalse(sut.contains(4));
		
		sut.add(5);
		sut.add(4);
		sut.add(3);
		
		assertTrue(sut.contains(4));
		assertFalse(sut.contains(100));
	}
	
	@Test
	public void remove() {
		sut.add(5);
		sut.add(4);
		sut.add(3);
		assertEquals(3, sut.size());
		assertTrue(sut.contains(3));
		
		sut.remove(3);
		assertEquals(2, sut.size());
		assertFalse(sut.contains(3));
	}
	
	@Test
	public void removeNodeWithOneChild() {
		sut.add(5);
		sut.add(4);
		sut.add(3);
		assertEquals(3, sut.size());
		assertTrue(sut.contains(4));
		
		sut.remove(4);
		assertEquals(2, sut.size());
		assertFalse(sut.contains(4));
	}
	
	@Test
	public void removeNodeWithTwoChildren() {

		//case in which largest value smaller has no children
		sut.add(5);
		sut.add(3);
		sut.add(2);
		sut.add(4);
		assertEquals(4, sut.size());
		assertTrue(sut.contains(3));
		
		sut.remove(3);
		assertEquals(3, sut.size());
		assertFalse(sut.contains(3));
		
		//case in which largest value smaller has a left child
		sut.add(1);
		assertEquals(4, sut.size());
		assertTrue(sut.contains(2));
		
		sut.remove(2);
		assertEquals(3, sut.size());
		assertFalse(sut.contains(2));
	}
	
	@Test
	public void union() {
		BinarySearchTree sut2 = new BinarySearchTree();
		sut2.add(5);
		sut2.add(9);
		sut2.add(3);
		sut2.add(8);
		assertEquals(4, sut2.size());
		
		sut.add(9);
		sut.add(15);
		sut.add(8);
		sut.add(13);
		assertEquals(4, sut.size());
		assertFalse(sut.contains(5) || sut.contains(3));
		
		sut.union(sut2);
		assertEquals(6, sut.size());
		assertTrue(sut.contains(5) && sut.contains(3));
	}
	
	@Test
	public void intersection() {
		BinarySearchTree sut2 = new BinarySearchTree();
		sut2.add(5);
		sut2.add(9);
		sut2.add(3);
		sut2.add(8);
		
		sut.add(9);
		sut.add(15);
		sut.add(8);
		sut.add(13);
		assertEquals(4, sut.size());
		
		sut.intersection(sut2);
		assertEquals(2, sut.size());
		assertTrue(sut.contains(8) && sut.contains(9));
	}
}