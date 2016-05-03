package entities;

import fr.univtln.bruno.exemple.simplerest.CSmsReceived;

/**
 * Created by boblinux on 06/04/16.
 */
public class CTestJobs {

    public static void main(String[] args) {
        CSmsReceived SmsReceived1 = new CSmsReceived.SmsReceivedBuilder()
                .Data("coords X Y").DateReceiving("janvier").Phone("+33565456").ID(0).build();
        CSmsReceived SmsReceived2 = new CSmsReceived.SmsReceivedBuilder()
                .Data("coords X Y").ID(1).build();
        CSmsReceived SmsReceivedFull = new CSmsReceived.SmsReceivedBuilder()
                .Data("coords X Y").DateReceiving("janvier").Phone("+33565456")
                .Processed("zadazd").ID(2).build();

        CWeaponEntity pistolet = new CWeaponEntity.CWeaponBuilder().damage(50).id(10).name("9mm").build();
        CWeaponEntity ak47 = new CWeaponEntity.CWeaponBuilder().damage(50).id(10).name("kalach").build();
        System.out.println(pistolet);
        System.out.println(ak47);

        CBuildingEntity drone = new CBuildingEntity.CBuildingBuilder().damage(0).name("drone 045").level(25).build();
        System.out.println(drone);

        CResonatorEntity resonatorEntity = new CResonatorEntity.CResonatorBuilder().energy(100).id(0).build();

    }
}
