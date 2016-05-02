package fr.univtln.groupeC.entities;

import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */
public class CPlayerEntity {

    private int mId;
    private String mNickName;
    private String mEmail;
    private CTeamEntity mTeam;
    private int mHacks;
    private int mThiefLevel;
    private int mHackerLevel;
    private List<AObjectEntity> mObjects;


    public CPlayerEntity(CPlayerBuilder pBuilder){
        mId = pBuilder.mId;
        mNickName = pBuilder.mNickName;
        mEmail = pBuilder.mEmail;
        mTeam = pBuilder.mTeam;
    }

    public static class CPlayerBuilder{
        private int mId;
        private String mNickName;
        private String mEmail;
        private CTeamEntity mTeam;

        public CPlayerBuilder id(int pId){
            mId = pId;
            return this;
        }

        public CPlayerBuilder nickname(String pNickName){
            mNickName = pNickName;
            return this;
        }

        public CPlayerBuilder email(String pEmail){
            mEmail = pEmail;
            return this;
        }

        public CPlayerBuilder team(CTeamEntity pTeam){
            mTeam = pTeam;
            return this;
        }

        public CPlayerEntity build(){
            return new CPlayerEntity(this);
        }


    }
}