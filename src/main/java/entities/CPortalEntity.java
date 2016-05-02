package entities;

import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */

public class CPortalEntity {
    private int mId;
    private float mLat;
    private float mLong;
    private int mRadius;
    private List<AObjectEntity> mObjects;
    private List<CResonatorEntity> mResonators;
    public List<CLinkEntity> mLinks;
    private CTeamEntity mTeam;

    public CPortalEntity(CPortalBuilder pBuilder){
        mId = pBuilder.mId;
        mLat = pBuilder.mLat;
        mLong = pBuilder.mLong;
        mRadius = pBuilder.mRadius;
        mObjects = pBuilder.mObjects;
        mResonators = pBuilder.mResonators;
        mTeam = pBuilder.mTeam;
        mLinks = pBuilder.mLinks;
    }

    public static class CPortalBuilder{
        private int mId;
        private float mLat;
        private float mLong;
        private int mRadius;
        private List<AObjectEntity> mObjects;
        private List<CResonatorEntity> mResonators;
        public List<CLinkEntity> mLinks;
        private CTeamEntity mTeam;

        public CPortalBuilder(int pId){
            mId = pId;
        }

        public CPortalBuilder latitude(float pLat){
            mLat = pLat;
            return this;
        }

        public CPortalBuilder longitude(float pLong){
            mLong = pLong;
            return this;
        }

        public CPortalBuilder radius(int pRadius){
            mRadius = pRadius;
            return this;
        }

        public CPortalBuilder objects(List<AObjectEntity> pObjects){
            mObjects = pObjects;
            return this;
        }

        public CPortalBuilder links(List<CLinkEntity> pLinks){
            mLinks = pLinks;
            return this;
        }

        public CPortalBuilder resonators(List<CResonatorEntity> pResonators){
            mResonators = pResonators;
            return this;
        }

        public CPortalBuilder team(CTeamEntity pTeam){
            mTeam = pTeam;
            return this;
        }

        public CPortalEntity build(){
            return new CPortalEntity(this);
        }
    }
}
