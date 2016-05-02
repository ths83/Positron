package fr.univtln.bruno.exemple.simplerest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class CSmsReceived implements Serializable{
    @XmlElement
    private String dateReceiving; // optional
    @XmlElement
    private  String processed; // optional
    @XmlElement
    private String phone; // optional
    @XmlElement
    private String data; // optional
    @XmlElement
    private int ID; // optionnal

    private CSmsReceived(){}


    private CSmsReceived(SmsReceivedBuilder builder) {
        this.dateReceiving = builder.dateReceiving;
        this.processed = builder.processed;
        this.phone = builder.phone;
        this.data = builder.data;
        this.ID = builder.ID;
    }

    public String getDateReceiving() {
        return dateReceiving;
    }

    public String getProcessed() {
        return processed;
    }

    public String getPhone() {
        return phone;
    }

    public String getData() {
        return data;
    }

    public String toString(){
        return "dateReceiving : " + dateReceiving + " processed : " + processed +"\n"
                + " phone : " + phone + " data : " + data + " ID : " + ID;
    }

    public int getID(){
        return ID;
    }

    public static class SmsReceivedBuilder {
        private String dateReceiving;
        private String processed;
        private String phone;
        private String data;
        private int ID;

        public SmsReceivedBuilder Data(String data) {
            this.data = data;
            return this;
        }

        public SmsReceivedBuilder DateReceiving(String dateReceiving) {
            this.dateReceiving = dateReceiving;
            return this;
        }

        public SmsReceivedBuilder Phone(String phone) {
            this.phone = phone;
            return this;
        }

        public SmsReceivedBuilder Processed(String processed) {
            this.processed = processed;
            return this;
        }

        public SmsReceivedBuilder ID(int ID) {
            this.ID = ID;
            return this;
        }

        public CSmsReceived build() {
            return new CSmsReceived(this);
        }


    }
}