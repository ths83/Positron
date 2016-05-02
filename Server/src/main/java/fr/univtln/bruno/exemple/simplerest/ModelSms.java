package fr.univtln.bruno.exemple.simplerest;

/**
 * Created by boblinux on 06/04/16.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by bruno on 03/10/14.
 */
public class ModelSms extends Observable {
    final List<CSmsReceived> sms = new ArrayList();


    public CSmsReceived getSMS(final int ID) {
        for (CSmsReceived smsReceived : sms)
            if (smsReceived.getID() == ID) return smsReceived;
        return null;
    }


    public void ajouterSms(String data, int id) {

        CSmsReceived SmsReceived1 = new CSmsReceived.SmsReceivedBuilder()
            .Data(data).ID(0).build();
        ajouterSms(SmsReceived1);
    }

    public void ajouterSms(CSmsReceived smsReceived) {
        sms.add(smsReceived);
    }


    public void supprimerSms(final int ID) {
        supprimerSms(getSMS(ID));
    }

    public void supprimerSms(CSmsReceived smsReceived) {
        sms.remove(smsReceived);
    }


    public List<CSmsReceived> getAuteurs() {
        return sms;
    }

}
