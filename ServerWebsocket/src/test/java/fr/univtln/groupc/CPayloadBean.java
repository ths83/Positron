package fr.univtln.groupc;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by marti on 18/05/2016.
 */


public class CPayloadBean implements Serializable {
    public final static class PayloadBeanCode extends
            JSONCoder<CPayloadBean> {
    }

    private Date mDate;
    private String mMessage;
    private A mA;

    public PayloadBean() {
    }

    public PayloadBean(PayloadBeanBuilder pBuilder){
        mDate = pBuilder.mDate;
        mSender = pBuilder.mSender;
        mMessage = pBuilder.mMessage;
        mA = pBuilder.mA;
    }

    public PayloadBean(Date date,Personne sender,String message, A a) {
        mDate = date;
        mSender = sender;
        mMessage = message;
        mA = a;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setSender(Personne mSender) {
        this.mSender = mSender;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public void setA(A a){
        this.mA = a;
    }

    public String getMessage() {
        return mMessage;
    }

    public Date getDate() {
        return mDate;
    }

    public Personne getSender() {
        return mSender;
    }

    public A getA(){
        return mA;
    }

    @Override
    public String toString() {
        return "PayloadBean{" +
                "mDate=" + mDate +
                ", mSender=" + mSender +
                ", mMessage='" + mMessage + '\'' +
                ", mA='" + mA + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayloadBean that = (PayloadBean) o;

        if (!mDate.equals(that.mDate)) return false;
        if (!mMessage.equals(that.mMessage)) return false;
        if (!mSender.equals(that.mSender)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mDate.hashCode();
        result = 31 * result + mSender.hashCode();
        result = 31 * result + mMessage.hashCode();
        return result;
    }

    public static class PayloadBeanBuilder{
        private Date mDate;
        private Personne mSender;
        private String mMessage;
        private A mA;

        public PayloadBeanBuilder(){};

        public PayloadBeanBuilder date(Date pDate){
            mDate = pDate;
            return this;
        }

        public PayloadBeanBuilder sender(Personne pSender){
            mSender = pSender;
            return this;
        }

        public PayloadBeanBuilder message(String pMessage){
            mMessage = pMessage;
            return this;
        }

        public PayloadBeanBuilder a(A pA){
            mA = pA;
            return this;
        }

        public PayloadBean build(){
            return new PayloadBean(this);
        }
    }
}
