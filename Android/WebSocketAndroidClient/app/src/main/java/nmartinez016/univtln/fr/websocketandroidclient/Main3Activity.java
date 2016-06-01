package nmartinez016.univtln.fr.websocketandroidclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main3Activity extends Activity {

    private TextView mTextView;
    private ClientTyrus mClientTyrus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mClientTyrus = new ClientTyrus();
        mClientTyrus.run();
        Log.d("tag", "run fait");

    }
}
