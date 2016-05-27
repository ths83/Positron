package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CTurretEntity;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 20/05/2016.
 */
public class CResonatorEntityTest extends TestCase {
    private ObjectMapper mMapper = new ObjectMapper();

    public void testSerDeserResonator() throws Exception {
        CResonatorEntity lReso = new CResonatorEntity.CResonatorBuilder(1).energy(50).energyMax(100).name("reso").build();
        String lJson = mMapper.writeValueAsString(lReso);
        System.out.println(lJson);
    }

    public void testSerDeserTurret() throws Exception {
        CTurretEntity lTurret = new CTurretEntity.CTurretBuilder(2).damage(50).build();
        String lJson = mMapper.writeValueAsString(lTurret);
        System.out.println(lTurret);
    }

    public void testSerDeserPortalWithResonators() throws Exception {
        CResonatorEntity lReso = new CResonatorEntity.CResonatorBuilder(1).name("reso1").build();
        CResonatorEntity lReso2 = new CResonatorEntity.CResonatorBuilder(2).name("reso2").build();
        List<CResonatorEntity> lResos = new ArrayList<CResonatorEntity>();
        lResos.add(lReso);
        lResos.add(lReso2);
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(5).resonators(lResos).build();
        //System.out.println(mMapper.writeValueAsString(lPortal));
        System.out.println(mMapper.writeValueAsString(lReso));
    }
}
