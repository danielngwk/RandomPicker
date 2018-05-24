package sg.edu.rp.c346.randompicker;

import android.content.Context;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.id.input;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    EditText et;
    EditText et2;
    Button btn;
    Button btn2;
    Button btn4;
    Button btn3;
    ListView lv;
    ListView lv2;
    TextView tv;
    TextView tv2;
    ArrayList<String> alinput = new ArrayList<String>();
    ArrayAdapter<String> aainput;
    ArrayList<String> alinput2 = new ArrayList<String>();
    ArrayAdapter<String> aainput2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.imageView3);
        img.setImageResource(R.drawable.thinking);
        et = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn4 = (Button) findViewById(R.id.button4);
        btn3 = (Button) findViewById(R.id.button3);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView3);
        lv = (ListView) findViewById(R.id.listview);
        lv2 = (ListView) findViewById(R.id.listview2);
        registerForContextMenu(lv);
        registerForContextMenu(lv2);
        aainput2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, alinput2);

        aainput = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, alinput);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et.getText().toString();

                if (input.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Walao leave it empty for what", Toast.LENGTH_LONG).show();
                } else {
                    lv.setAdapter(aainput);

                    alinput.add(input);
                    aainput.notifyDataSetChanged();
                    et.setText("");

                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = et2.getText().toString();
                lv2.setAdapter(aainput2);

                if (input1.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Walao leave it empty for what", Toast.LENGTH_LONG).show();
                } else {
                    alinput2.add(input1);
                    aainput2.notifyDataSetChanged();
                    et2.setText("");

                }
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aainput.clear();
                aainput2.clear();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Random randomizer = new Random();
                Random randomizer2 = new Random();
                String random = alinput.get(randomizer.nextInt(alinput.size()));
                String random2 = alinput2.get(randomizer2.nextInt(alinput.size()));

                tv.setText(random);
                tv2.setText(random2);
            }

        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listview) { //For first listview
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_listview_1, menu);

        }
        if (v.getId() == R.id.listview2) { //For second listview
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_listview_2, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position; //Use this for getting the list item value
        View view = info.targetView;


        switch (item.getItemId()) {
            case R.id.remove:
                for (int a = 0; a < alinput.size(); a++) {
                    if (aainput.getItem(index) == alinput.get(a)) {
                        aainput.remove(aainput.getItem(0));
                        Log.d("onContextItemSelected", "Remove Pressed");
                    } else {
                        aainput2.remove(aainput2.getItem(0));
                        Log.d("onContextItemSelected", "Remove Pressed");
                    }
                }
//                for (int b = 0; b< alinput2.size(); b++){
//                    if (aainput2.getItem(0) == alinput2.get(b)){
//                        aainput2.remove(aainput2.getItem(0));
//
//                    }
//                    else{
//                        return false;
//                    }
//                }


                break;

        }
        return super.onContextItemSelected(item);

    }
}









