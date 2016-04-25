package entities;

/**
 * Created by nmartinez016 on 25/04/16.
 */
public abstract class A {
    private int mId;

    public A(int pId) {
        mId = pId;
    }

    public A(ABuilder pBuilder){
        mId = pBuilder.mId;
    }

    @Override
    public String toString() {
        return "A{" +
                "mId=" + mId +
                '}';
    }

    public class ABuilder{
        private int mId;

        public ABuilder(int pId){
            mId = pId;
        }
    }
}
