package com.example.tp_homework_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private TextViewAdapter adapter;
//    private ArrayList<String> arrayList;
//    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new FirstFragment())
                    .commit();
        }

//        GridView gv = (GridView) findViewById(R.id.gv);
//
//        arrayList = new ArrayList<String>();
//        adapter = new TextViewAdapter(getApplicationContext(), arrayList);
//        gv.setAdapter(adapter);
//
//        for (counter = 1; counter <= 100; counter++) {
//            adapter.add(Integer.toString(counter));
//        }
//
//        Button btn = (Button) findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                adapter.add(Integer.toString(counter++));
//            }
//        });
    }
}

//class TextViewAdapter extends BaseAdapter {
//    private Context context;
//    private ArrayList<String> arrayList;
//
//    public TextViewAdapter(Context context, ArrayList<String> arrayList) {
//        this.context = context;
//        this.arrayList = arrayList;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View gridView;
//
//        if (convertView == null) {
//
//            gridView = new View(context);
//            gridView = inflater.inflate(R.layout.item, null);
//
//            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
//            textView.setText(arrayList.get(position));
//
//            if (position % 2 == 1) {
//                textView.setTextColor(Color.parseColor("#e62e05"));
//            } else {
//                textView.setTextColor(Color.parseColor("#012d73"));
//            }
//        } else {
//            gridView = (View) convertView;
//        }
//
//        return gridView;
//    }
//
//    @Override
//    public int getCount() {
//        return arrayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    public void add(String str) {
//        arrayList.add(str);
//        notifyDataSetChanged();
//    }
//
//}
