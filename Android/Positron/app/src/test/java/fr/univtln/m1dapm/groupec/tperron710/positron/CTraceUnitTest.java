package fr.univtln.m1dapm.groupec.tperron710.positron;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.entities_view.CTraceMapView;

/**
 * Created by toms on 5/17/16.
 */
public class CTraceUnitTest extends TestCase {

    private static final int NB_PORTALS_FIELD = 3;
    private CLinkEntity[] mLinkArray;
    private CPortalEntity[] mPortalLinkedArray;
    private Set<LatLng> mSetLatLngPortals;
    private LatLng[] mLatLngPortalLinkedArray;


    public static void main(String[] args) {

        CPortalEntity lUnitTestp1 =  new CPortalEntity.CPortalBuilder(1001).latitude(10).longitude(11).build();
        CPortalEntity lUnitTestp2 =  new CPortalEntity.CPortalBuilder(1002).latitude(20).longitude(10).build();
        CPortalEntity lUnitTestp3 =  new CPortalEntity.CPortalBuilder(2001).latitude(15).longitude(7).build();

        List<CPortalEntity> lUnitTestListPortal1 = new ArrayList<>();
        lUnitTestListPortal1.add(lUnitTestp1);
        lUnitTestListPortal1.add(lUnitTestp2);

        List<CPortalEntity> lUnitTestListPortal2 = new ArrayList<>();
        lUnitTestListPortal2.add(lUnitTestp3);
        lUnitTestListPortal2.add(lUnitTestp2);

        List<CPortalEntity> lUnitTestListPortal3 = new ArrayList<>();
        lUnitTestListPortal3.add(lUnitTestp1);
        lUnitTestListPortal3.add(lUnitTestp3);

        CLinkEntity lUnitTestL1= new CLinkEntity.CLinkBuilder(100).portals(lUnitTestListPortal1).build();
        CLinkEntity lUnitTestL2= new CLinkEntity.CLinkBuilder(200).portals(lUnitTestListPortal2).build();
        CLinkEntity lUnitTestL3= new CLinkEntity.CLinkBuilder(300).portals(lUnitTestListPortal3).build();

        List<CLinkEntity> lUnitTestLinkList = new ArrayList<>();
        lUnitTestLinkList.add(lUnitTestL1);
        lUnitTestLinkList.add(lUnitTestL2);
        lUnitTestLinkList.add(lUnitTestL3);

        GoogleMap lMap = null;
        CFieldEntity lUnitTestField = new CFieldEntity.CFieldBuilder(8888).links(lUnitTestLinkList).build();
        CTraceMapView lTraceMap = new CTraceMapView();
        Set<LatLng> lReturnSetTest = new HashSet<>();

        assertEquals(lTraceMap.onDisplayField(lUnitTestField),lReturnSetTest);
    }
}
