package com.pgolubev.practice1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    int cnt = 0;
    ArrayList<String> ops = new ArrayList<>();
    ArrayAdapter<String> ad;
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tvCnt);
        Button b1 = findViewById(R.id.btnInc);
        Button b2 = findViewById(R.id.btnDec);
        Button b3 = findViewById(R.id.btnRst);
        ListView lv = findViewById(R.id.lv);

        if (s != null) {
            cnt = s.getInt("cnt");
            ops = s.getStringArrayList("ops");
        }

        ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ops);
        lv.setAdapter(ad);
        tv.setText(String.valueOf(cnt));

        b1.setOnClickListener(v -> { //+1 кнопка
            int old = cnt;
            cnt++;
            tv.setText("" + cnt);
            ops.add(df.format(new Date()) + " - Увеличено с " + old + " до " + cnt);
            ad.notifyDataSetChanged();
        });

        b2.setOnClickListener(v -> { //-1 кнопка
            int old = cnt;
            cnt--;
            tv.setText("" + cnt);
            ops.add(df.format(new Date()) + " - Уменьшено с " + old + " до " + cnt);
            ad.notifyDataSetChanged();
        });

        b3.setOnClickListener(v -> { //ре сет кнопка
            int old = cnt;
            cnt = 0;
            tv.setText("0");
            ops.add(df.format(new Date()) + " - Сброс (" + old + " → 0)");
            ad.notifyDataSetChanged();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle o) {
        super.onSaveInstanceState(o);
        o.putInt("cnt", cnt);
        o.putStringArrayList("ops", ops);
    }
}
