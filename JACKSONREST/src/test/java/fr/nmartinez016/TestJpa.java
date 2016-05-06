package fr.nmartinez016;

import junit.framework.TestCase;

/**
 * Created by marti on 05/05/2016.
 */


public class TestJpa extends TestCase {

    public void testPersist() throws Exception {
        CCrudServiceBean<CClassA> lCrud = new CCrudServiceBean<CClassA>();
        CClassA lA = new CClassA();
        lA.setId(5);
        lA.setmName("testA");
        lCrud.create(lA);
        System.out.println(lA);
        System.out.println(lCrud.getEm().contains(lA));
        assertTrue(lCrud.getEm().contains(lA));
        CClassA lGottenA = lCrud.find(CClassA.class, 5);
        System.out.println(lGottenA);
        assertEquals(lA, lGottenA);
    }
}
