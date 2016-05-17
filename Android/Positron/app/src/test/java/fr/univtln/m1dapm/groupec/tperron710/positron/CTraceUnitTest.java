package fr.univtln.m1dapm.groupec.tperron710.positron;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

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
    //private Set<LatLng> mSetLatLngPortals;
    //private LatLng[] mLatLngPortalLinkedArray;


    public void testLinkRequest() {

        GoogleMap lMap = null;

        LatLng[]lLatLngLink = new LatLng[2];
        CPortalEntity lUnitTestp1 =  new CPortalEntity.CPortalBuilder(1001).latitude(10).longitude(11).build();
        CPortalEntity lUnitTestp2 =  new CPortalEntity.CPortalBuilder(1002).latitude(20).longitude(10).build();
        lLatLngLink[0] = new LatLng(10,11);
        lLatLngLink[1] = new LatLng(20,10);

        List<CPortalEntity> lUnitTestListPortal1 = new ArrayList<>();
        lUnitTestListPortal1.add(lUnitTestp1);
        lUnitTestListPortal1.add(lUnitTestp2);

        CLinkEntity lUnitTestL1= new CLinkEntity.CLinkBuilder(100).portals(lUnitTestListPortal1).build();

        CTraceMapView lTrace = new CTraceMapView();
        System.out.println(lLatLngLink[0]);
        System.out.println(lLatLngLink[1]);

        LatLng[]lLatLngs = lTrace.onDisplayLink(lMap,lUnitTestL1);


        assertEquals(lLatLngs[0],lLatLngLink[0]);
        assertEquals(lLatLngs[1],lLatLngLink[1]);

    }

    public void testFieldRequest(){
        GoogleMap lMap = null;

        LatLng[]lLatLngLink = new LatLng[3];
        CPortalEntity lUnitTestp1 =  new CPortalEntity.CPortalBuilder(1001).latitude(10).longitude(11).build();
        CPortalEntity lUnitTestp2 =  new CPortalEntity.CPortalBuilder(1002).latitude(20).longitude(10).build();
        CPortalEntity lUnitTestp3 =  new CPortalEntity.CPortalBuilder(1003).latitude(23).longitude(10).build();
        lLatLngLink[0] = new LatLng(10,11);
        lLatLngLink[2] = new LatLng(20,10);
        lLatLngLink[1] = new LatLng(23,10);

        List<CPortalEntity> lUnitTestListPortal1 = new ArrayList<>();
        lUnitTestListPortal1.add(lUnitTestp1);
        lUnitTestListPortal1.add(lUnitTestp2);

        List<CPortalEntity> lUnitTestListPortal2 = new ArrayList<>();
        lUnitTestListPortal1.add(lUnitTestp3);
        lUnitTestListPortal1.add(lUnitTestp2);

        List<CPortalEntity> lUnitTestListPortal3 = new ArrayList<>();
        lUnitTestListPortal1.add(lUnitTestp1);
        lUnitTestListPortal1.add(lUnitTestp3);

        List<CPortalEntity> lP1 = new ArrayList<>();
        List<CPortalEntity> lP2 = new ArrayList<>();
        List<CPortalEntity> lP3 = new ArrayList<>();

        lP1.add(lUnitTestp1);
        lP1.add(lUnitTestp2);
        lP2.add(lUnitTestp2);
        lP2.add(lUnitTestp3);
        lP3.add(lUnitTestp3);
        lP3.add(lUnitTestp1);

        CLinkEntity lUnitTestL1= new CLinkEntity.CLinkBuilder(100).portals(lP1).build();
        CLinkEntity lUnitTestL2= new CLinkEntity.CLinkBuilder(101).portals(lP2).build();
        CLinkEntity lUnitTestL3= new CLinkEntity.CLinkBuilder(102).portals(lP3).build();

        System.out.println(lUnitTestL1.toString());
        System.out.println(lUnitTestL2.toString());
        System.out.println(lUnitTestL3.toString());


        List<CLinkEntity> lLinkList = new ArrayList<>();
        lLinkList.add(lUnitTestL1);
        lLinkList.add(lUnitTestL2);
        lLinkList.add(lUnitTestL3);

        System.out.println(lLinkList);

        CFieldEntity lField = new CFieldEntity.CFieldBuilder(1).build();
        lField.setLinks(lLinkList);
        System.out.println(lField.toString());

        CTraceMapView lTrace = new CTraceMapView();
        System.out.println(lLatLngLink[0]);
        System.out.println(lLatLngLink[1]);
        System.out.println("hey" + lLatLngLink[2]);

        LatLng[]lLatLngs = lTrace.onDisplayField(lMap,lField);


        assertEquals(lLatLngs[0],lLatLngLink[0]);
        assertEquals(lLatLngs[1],lLatLngLink[1]);
        assertEquals(lLatLngs[2],lLatLngLink[2]);
    }
}
