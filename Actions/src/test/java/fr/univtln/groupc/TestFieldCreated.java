package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.entities.CFieldEntity;
import junit.framework.TestCase;

/**
 * Created by marti on 01/06/2016.
 */
public class TestFieldCreated extends TestCase {

    private ObjectMapper mMapper = new ObjectMapper();


    public void testSerDeserFieldCreated() throws Exception {
        System.out.println(mMapper.writeValueAsString(new CFieldCreated()));
    }


}
