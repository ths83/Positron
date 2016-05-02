package entities;

/**
 * Created by nmartinez016 on 25/04/16.
 */

public class CSkillEntity {
    private int mId;
    private String mName;
    private int mLevel;

    public CSkillEntity(CSkillBuilder pBuilder){
        mId = pBuilder.mId;
        mName = pBuilder.mName;
    }

    public static class CSkillBuilder{
        private int mId;
        private String mName;

        public CSkillBuilder(int pId){
            mId = pId;
        }

        public CSkillBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CSkillEntity build(){
            return new CSkillEntity(this);
        }
    }
}
