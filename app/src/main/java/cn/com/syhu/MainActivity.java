package cn.com.syhu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.net.SocketException;
import java.util.List;

import cn.com.syhu.util.Utils;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private TextView talk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        talk = findViewById(R.id.talkToMiao);

        talkToMiao();
    }

    private void talkToMiao() {
        List<String> ipv4s = null;
        try {
            ipv4s = Utils.getIpv4s();
        } catch (Exception e) {
            Log.e(TAG, "getIpv4: " + e.getMessage());
        }
        if (ipv4s == null || ipv4s.size() < 1) {
            Toast.makeText(this.getApplicationContext(), "权限有点异常", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Log.i(TAG, "talkToMiao: ipv4 size: " + ipv4s.size());
        stringBuilder.append("我猜是第一个");
        for (String ipv4 : ipv4s) {
            stringBuilder.append(ipv4).append('\n');
        }

        talk.clearComposingText();
        talk.append(stringBuilder);
    }
}