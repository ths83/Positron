package fr.univtln.groupeC.tests;

import fr.univtln.groupeC.entities.CPlayerEntity;
import fr.univtln.groupeC.entities.CResonatorEntity;
import fr.univtln.groupeC.entities.CTeamEntity;
import fr.univtln.groupeC.entities.CTurretEntity;

/**
 * Created by arouani277 on 02/05/16.
 */
public class CTestsBoby {
    public static void main(String[] args) {
        CTurretEntity c1 = new CTurretEntity
                .CTurretBuilder().level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).id(0).latitude(10.5)
                .longitude(11.2).name("c1").radius(100).build();

        System.out.println("#### Infos Tourelle ####");
        System.out.println(c1.toString());

        CResonatorEntity cr = new CResonatorEntity.CResonatorBuilder().energy(100)
                .latitude(10.5).energyMax(200).id(1)
                .level(9).longitude(5.2).name("cr")
                .radius(54).build();

        System.out.println("#### Infos Résonateur ####");
        System.out.println(cr.toString());

        CTeamEntity ctm = new CTeamEntity.CTeamBuilder(0).color("red").build();
        CPlayerEntity cp = new CPlayerEntity.CPlayerBuilder().email("bob@z.fr").id(0).nickname("mahmoud").build();


    }
}
