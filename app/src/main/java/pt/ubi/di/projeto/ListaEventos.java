package pt.ubi.di.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListaEventos extends AppCompatActivity {

    mySQLiteDBHandler dbHandler;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        //Button button_delete = (Button) findViewById(R.id.button_apagar_lista);
        ListView list_eventos = (ListView) findViewById(R.id.listView_results);
        dbHandler = new mySQLiteDBHandler(this);

        ArrayList<String> list = new ArrayList<>();
        Cursor data = dbHandler.getData();

        if(data.getCount() == 0){
            makeText("The database is empty...");
        } else{
            while(data.moveToNext()){
                String str = "";
                if(data.getString(1).equals(data.getString(2))){
                    str = "EVENTO: " + data.getString(0) + "\nDIA " + data.getString(1) + "\n";
                } else {
                    str = "EVENTO: " + data.getString(0) + "\nDE " + data.getString(1) + " ATÉ " + data.getString(2) + "\n";
                }
                list.add(str);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                list_eventos.setAdapter(listAdapter);
            }
        }

        /*
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteAll();
            }
        });
        */

    }

    public void openMenu(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void makeText(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}