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
    private CAttackBuilding mAttackBuilding;
    private CPoseResonator mPoseResonator;
    private CPoseVirus mPoseVirus;
 private CAttackAOE mAttackAOE;
    private CTeamPortalChanged mTeamPortalChanged;
    private CResonatorPosed mResonatorPosed;
    private CCreateLink mCreateLink;
    private CLinkCreated mLinkCreated;
    private CFieldCreated mFieldCreated;

    private CVirusPosed mPosedVirus;

    private CHackPortal mHackPortal;
    private CPortalHacked mPortalHacked;
    private CHackPortalKey mHackPortalKey;
    private CPortalKeyHacked mPortalKeyHacked;
    private CBuildingAttacked mBuildingAttacked;
    private CPoseBuilding mPoseBulding;
    private CBuildingPosed mBuildingPosed;

    public CPayloadBean() {
    }

    public CPayloadBean(CPayloadBeanBuilder pBuilder){
        mType = pBuilder.mType;
       // mObject = pBuilder.mObject;
        mConnection = pBuilder.mConnection;

        mAttackAOE = pBuilder.mAttackAOE;
        mAttackBuilding = pBuilder.mAttackBuilding;
        mPoseResonator = pBuilder.mPoseResonator;
        mTeamPortalChanged = pBuilder.mTeamPortalChanged;
        mResonatorPosed = pBuilder.mResonatorPosed;
        mCreateLink = pBuilder.mCreateLink;
        mLinkCreated = pBuilder.mLinkCreated;
        mFieldCreated = pBuilder.mFieldCreated;
        mHackPortal = pBuilder.mHackPortal;
        mPortalHacked = pBuilder.mPortalHacked;
        mHackPortalKey = pBuilder.mHackPortalKey;
        mPortalKeyHacked = pBuilder.mPortalKeyHacked;
        mBuildingAttacked = pBuilder.mBuildingAttacked;
        mPoseBulding = pBuilder.mPoseBuilding;
        mBuildingPosed = pBuilder.mBuildingPosed;

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

    public CAttackAOE getAttackAOE(){
        return mAttackAOE;
    }


    public void setAttackAOE(CAttackAOE pAttackAOE){
        mAttackAOE = pAttackAOE;
    }


    public CPoseResonator getPoseResonator(){
        return mPoseResonator;
    }

    public CVirusPosed getPosedVirus() {
        return mPosedVirus;
    }

    public void setPosedVirus(CVirusPosed mPosedVirus) {
        this.mPosedVirus = mPosedVirus;
    }

    public CPoseVirus getPoseVirus() {
        return mPoseVirus;
    }

    public void setPoseVirus(CPoseVirus mPoseVirus) {
        this.mPoseVirus = mPoseVirus;
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

    public CLinkCreated getLinkCreated(){
        return mLinkCreated;
    }

    public void setLinkCreated(CLinkCreated pLinkCreated){
        mLinkCreated = pLinkCreated;
    }

    public CFieldCreated getFieldCreated(){
        return mFieldCreated;
    }

    public void setFieldCreated(CFieldCreated pFieldCreated){
        mFieldCreated = pFieldCreated;
    }

    public CHackPortal getHackPortal() { return mHackPortal; }

    public void setHackPortal(CHackPortal pHackPortal) { this.mHackPortal = pHackPortal; }

    public CPortalHacked getPortalHacked() { return mPortalHacked; }

    public void setPortalHacked(CPortalHacked pPortalHacked) { this.mPortalHacked = pPortalHacked; }

    public CHackPortalKey getHackPortalKey() { return mHackPortalKey;}

    public void setHackPortalKey(CHackPortalKey mHackPortalKey) { this.mHackPortalKey = mHackPortalKey; }

    public CPortalKeyHacked getPortalKeyHacked() { return mPortalKeyHacked; }

    public void setPortalKeyHacked(CPortalKeyHacked mPortalKeyHacked) { this.mPortalKeyHacked = mPortalKeyHacked;}

    public CAttackBuilding getAttackBuilding() {return mAttackBuilding;}

    public void setAttackBuilding(CAttackBuilding pAttackBuilding) {this.mAttackBuilding = pAttackBuilding;}

    public CPoseBuilding getPoseBulding() {
        return mPoseBulding;
    }

    public void setPoseBulding(CPoseBuilding pPoseBulding) {
        this.mPoseBulding = pPoseBulding;
    }

    public CBuildingAttacked getBuildingAttacked() {
        return mBuildingAttacked;
    }

    public void setBuildingAttacked(CBuildingAttacked pBuildingAttacked) {
        this.mBuildingAttacked = pBuildingAttacked;
    }

    public CBuildingPosed getBuildingPosed() {
        return mBuildingPosed;
    }

    public void setBuildingPosed(CBuildingPosed pBuildingPosed) {
        this.mBuildingPosed = pBuildingPosed;
    }

    @Override
    public String toString() {
        return "CPayloadBean{" +
                "mType='" + mType + '\'' +
                //", mObject=" + mObject +
                //", mConnection=" + mConnection +
                /*", mAttackBuilding=" + mAttackBuilding +
                ", mPoseResonator=" + mPoseResonator +
                ", mHackPortal=" + mHackPortal +
                ", mAttackAOE=" + mAttackAOE +
                ", mTeamPortalChanged=" + mTeamPortalChanged +
                ", mResonatorPosed=" + mResonatorPosed +*/
                "mLinkCreated='" + mLinkCreated +
                '}';
    }

    public static class CPayloadBeanBuilder{
        private String mType;
        private Object mObject;
        private CConnection mConnection;
        private CAttackBuilding mAttackBuilding;
        private CPoseResonator mPoseResonator;
        private CPoseVirus mPoseVirus;
       private CAttackAOE mAttackAOE;
        private CTeamPortalChanged mTeamPortalChanged;
        private CResonatorPosed mResonatorPosed;
        private CCreateLink mCreateLink;
        private CLinkCreated mLinkCreated;
        private CFieldCreated mFieldCreated;

        private CVirusPosed mVirusPosed;

        private CHackPortal mHackPortal;
        private CPortalHacked mPortalHacked;
        private CHackPortalKey mHackPortalKey;
        private CPortalKeyHacked mPortalKeyHacked;
        private CBuildingAttacked mBuildingAttacked;
        private CPoseBuilding mPoseBuilding;
        private CBuildingPosed mBuildingPosed;


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

        public CPayloadBeanBuilder objectPoseVirus(CPoseVirus pPoseVirus){
            mPoseVirus = pPoseVirus;
            return this;
        }


        public CPayloadBeanBuilder objectAttackAOE(CAttackAOE pAttackAOE){
            mAttackAOE = pAttackAOE;
            return this;
        }

        public CPayloadBeanBuilder objectTeamPortalChanged(CTeamPortalChanged pTeamPortalChanged){
            mTeamPortalChanged = pTeamPortalChanged;
            return this;
        }

        public CPayloadBeanBuilder objectResonatorPosed(CResonatorPosed pResonatorPosed){
            mResonatorPosed = pResonatorPosed;
            return this;
        }

        public CPayloadBeanBuilder objectAttackBuilding(CAttackBuilding pAttackBuilding){
            mAttackBuilding = pAttackBuilding;
            return this;
        }

        public CPayloadBeanBuilder objectLinkCreated(CLinkCreated pLinkCreated){
            mLinkCreated = pLinkCreated;
            return this;
        }

        public CPayloadBeanBuilder objectFieldCreated(CFieldCreated pFieldCreated){
            mFieldCreated = pFieldCreated;
            return this;
        }

        public CPayloadBeanBuilder objectVirusPosed(CVirusPosed pVirusPosed){
            mVirusPosed = pVirusPosed;
            return this;
        }



        public CPayloadBeanBuilder type(String pType){
            mType = pType;
            return this;
        }

        public CPayloadBeanBuilder objectHackPortal (CHackPortal pHackportal){
            mHackPortal = pHackportal;
            return this;
        }

        public CPayloadBeanBuilder objectPortalHacked(CPortalHacked pPortalHacked){
            mPortalHacked = pPortalHacked;
            return this;
        }

        public CPayloadBeanBuilder objectHackPortalKey (CHackPortalKey pHackPortalKey){
            mHackPortalKey = pHackPortalKey;
            return this;
        }

        public CPayloadBeanBuilder objectPortalKeyHacked(CPortalKeyHacked pPortalKeyHacked){
            mPortalKeyHacked = pPortalKeyHacked;
            return this;
        }

        public CPayloadBeanBuilder objectBuildingAttacked(CBuildingAttacked pBuildingAttacked){
            mBuildingAttacked = pBuildingAttacked;
            return this;
        }

        public CPayloadBeanBuilder objectPoseBuilding(CPoseBuilding pPoseBuilding){
            mPoseBuilding = pPoseBuilding;
            return this;
        }

        public CPayloadBeanBuilder objectBuildingPosed (CBuildingPosed pBuildingPosed){
            mBuildingPosed = pBuildingPosed;
            return this;
        }

        public CPayloadBean build(){
            return new CPayloadBean(this);
        }
    }
}