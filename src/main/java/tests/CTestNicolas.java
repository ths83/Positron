package tests;

import entities.CResonatorEntity;
import entities.CSkillEntity;
import genericdao.CCrudMethods;

/**
 * Created by boblinux on 06/04/16.
 */
public class CTestNicolas {

    public static void main(String[] args) {

        CCrudMethods lCrud = new CCrudMethods();
        CSkillEntity lSkill1 = new CSkillEntity.CSkillBuilder(1).name("gun1").level(1).cost(10).build();
        lCrud.create(lSkill1);


    }
}
