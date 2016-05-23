package fr.univtln.m1dapm.groupec.tperron710.positron.tets;

import junit.framework.TestCase;

import fr.univtln.groupc.rest.CRestDelete;

/**
 * Created by tperron710 on 23/05/16.
 */
public class CDeleteLinkTest extends TestCase{

    public void deleteLink(){
        new CRestDelete().onDeleteLink(1);
        assertTrue(true);
    }
}
