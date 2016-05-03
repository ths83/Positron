package entities;

/**
 * Created by nmartinez016 on 25/04/16.
 */
public class B extends A {

    private String mName;

    public B(BBuilder pBuilder){

        super(pBuilder.mId);
        mName = pBuilder.mName;
    }

    @Override
    public String toString() {
        return "B{" +
                "mName='" + mName + '\'' +
                "} " + super.toString();
    }

    public static class BBuilder{
        private int mId;
        private String mName;

        public BBuilder(int pId){
            mId = pId;
        }

        public BBuilder name(String pName){
            mName = pName;
            return this;
        }

        public B build(){
            return new B(this);
        }
    }

    public static void main(String[] args) {
        B lB = new BBuilder(50).name("raul").build();
        System.out.println(lB);
    }
}


