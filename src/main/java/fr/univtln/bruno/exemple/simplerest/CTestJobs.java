package fr.univtln.bruno.exemple.simplerest;

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

        System.out.println(SmsReceived1.toString());
        System.out.println(SmsReceived2.toString());
        System.out.println(SmsReceivedFull.toString());
        System.out.println(SmsReceived1.getData());
    }
}
