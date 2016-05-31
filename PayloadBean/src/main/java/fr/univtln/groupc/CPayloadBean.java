package fr.univtln.groupc;

import java.io.Serializable;

/**
 * Created by marti on 26/05/2016.
 */



public class CPayloadBean implements Serializable {
    public final static class PayloadBeanCode extends
            CJSONCoder<CPayloadBean> {
    }

    private String mType;
    //private Object mObject;
    private CConnection mConnection;
    //private CAttackBuilding mAttackBuilding;
    private CPoseResonator mPoseResonator;
    /*private CHackPortal mHackPortal;
    private CAttackAOE mAttackAOE;*/
    private CTeamPortalChanged mTeamPortalChanged;
    private CResonatorPosed mResonatorPosed;
    private CCreateLink mCreateLink;


    public CPayloadBean() {
    }

    public CPayloadBean(CPayloadBeanBuilder pBuilder){
        mType = pBuilder.mType;
       // mObject = pBuilder.mObject;
        mConnection = pBuilder.mConnection;
        /*
        mAttackAOE = pBuilder.mAttackAOE;
        mAttackBuilding = pBuilder.mAttackBuilding;
        mHackPortal = pBuilder.mHackPortal;*/
        mPoseResonator = pBuilder.mPoseResonator;
        mTeamPortalChanged = pBuilder.mTeamPortalChanged;
        mResonatorPosed = pBuilder.mResonatorPosed;
        mCreateLink = pBuilder.mCreateLink;
    }

    public void setType(String pType) {
        mType = pType;
    }

    public String getType() {
        return mType;
    }
/*
    public void setObject(Object pObject){
        mObject = pObject;
    }

    public Object getObject(){
        return mObject;
    }
*/
    public CConnection getConnection(){
        return mConnection;
    }

    public void setConnection(CConnection pConnection){
        mConnection = pConnection;
    }
/*
    public CAttackAOE getAttackAOE(){
        return mAttackAOE;
    }


    public void setAttackAOE(CAttackAOE pAttackAOE){
        mAttackAOE = pAttackAOE;
    }


    public CAttackBuilding getAttackBuilding(){
        return mAttackBuilding;
    }


    public void setAttackBuilding(CAttackBuilding pAttackBuilding){
        mAttackBuilding = pAttackBuilding;
    }


    public CHackPortal getHackPortal(){
        return mHackPortal;
    }


    public void setHackPortal(CHackPortal pHackPortal){
        mHackPortal = pHackPortal;
    }

*/
    public CPoseResonator getPoseResonator(){
        return mPoseResonator;
    }


    public void setPoseResonator(CPoseResonator pPoseResonator){
        mPoseResonator = pPoseResonator;
    }

    public CCreateLink getCreateLink(){
        return mCreateLink;
    }

    public void setCreateLink(CCreateLink pCreateLink){
        mCreateLink = pCreateLink;
    }

    public CResonatorPosed getResonatorPosed(){
        return mResonatorPosed;
    }

    public void setResonatorPosed(CResonatorPosed pResonatorPosed){
        mResonatorPosed = pResonatorPosed;
    }

    public CTeamPortalChanged getTeamPortalChanged(){
        return mTeamPortalChanged;
    }

    public void setTeamPortalChanged(CTeamPortalChanged pTeamPortalChanged){
        mTeamPortalChanged = pTeamPortalChanged;
    }

    @Override
    public String toString() {
        return "CPayloadBean{" +
                "mType='" + mType + '\'' +
                //", mObject=" + mObject +
                ", mConnection=" + mConnection +
                /*", mAttackBuilding=" + mAttackBuilding +*/
                ", mPoseResonator=" + mPoseResonator +/*
                ", mHackPortal=" + mHackPortal +
                ", mAttackAOE=" + mAttackAOE +*/
                ", mTeamPortalChanged=" + mTeamPortalChanged +
                ", mResonatorPosed=" + mResonatorPosed +
                '}';
    }

    public static class CPayloadBeanBuilder{
        private String mType;
        private Object mObject;
        private CConnection mConnection;
        //private CAttackBuilding mAttackBuilding;
        private CPoseResonator mPoseResonator;/*
        private CHackPortal mHackPortal;
        private CAttackAOE mAttackAOE;*/
        private CTeamPortalChanged mTeamPortalChanged;
        private CResonatorPosed mResonatorPosed;
        private CCreateLink mCreateLink;

        public CPayloadBeanBuilder(){};
/*
        public CPayloadBeanBuilder object(Object pObject){
            mObject = pObject;
            return this;
        }*/
        public CPayloadBeanBuilder objectConnection(CConnection pConnection){
            mConnection = pConnection;
            return this;
        }

        public CPayloadBeanBuilder objectPoseResonator(CPoseResonator pPoseResonator){
            mPoseResonator = pPoseResonator;
            return this;
        }

        public CPayloadBeanBuilder objectCreateLink(CCreateLink pCreateLink){
            mCreateLink = pCreateLink;
            return this;
        }
/*
        public CPayloadBeanBuilder objectHackPortal(CHackPortal pHackPortal){
            mHackPortal = pHackPortal;
            return this;
        }

        public CPayloadBeanBuilder objectAttackAOE(CAttackAOE pAttackAOE){
            mAttackAOE = pAttackAOE;
            return this;
        }
*/
        public CPayloadBeanBuilder objectTeamPortalChanged(CTeamPortalChanged pTeamPortalChanged){
            mTeamPortalChanged = pTeamPortalChanged;
            return this;
        }

        public CPayloadBeanBuilder objectResonatorPosed(CResonatorPosed pResonatorPosed){
            mResonatorPosed = pResonatorPosed;
            return this;
        }
/*
        public CPayloadBeanBuilder objectAttackBuilding(CAttackBuilding pAttackBuilding){
            mAttackBuilding = pAttackBuilding;
            return this;
        }
*/


        public CPayloadBeanBuilder type(String pType){
            mType = pType;
            return this;
        }


        public CPayloadBean build(){
            return new CPayloadBean(this);
        }
    }
}