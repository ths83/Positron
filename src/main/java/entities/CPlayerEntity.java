package entities;

import java.util.List;
import java.util.Objects;

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
    private List<CObjectEntity> mObjects;


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

        public CPlayerBuilder(int pId){
            mId = pId;
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
    }
}
