package pt.ubi.di.projeto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    mySQLiteDBHandler dbHandler;

    private Button button_evento;
    private Button button_ver_lista;
    private Button button_ver_calendario;
    private Button button_info_geral;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_evento = (Button) findViewById(R.id.button_evento);
        button_ver_lista = (Button) findViewById(R.id.button_ver_lista);
        button_ver_calendario = (Button) findViewById(R.id.button_ver_calendario);
        button_info_geral = (Button) findViewById(R.id.button_info_geral);
        dbHandler = new mySQLiteDBHandler(this);

        button_evento.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                openMarcarEventos();
            }
        });

        button_ver_lista.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                openListaEventos();
            }
        });

        button_ver_calendario.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                openCalendario();
            }
        });

        button_info_geral.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                openInfoGeral();
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void openMarcarEventos(){

        Intent intent = new Intent(this, MarcarEvento.class);
        startActivity(intent);
    }

    public void openListaEventos(){

        Intent intent = new Intent(this, ListaEventos.class);
        startActivity(intent);
    }

    public void openCalendario(){

        Intent intent = new Intent(this, VerCalendario.class);
        startActivity(intent);
    }

    public void openInfoGeral(){
        Intent intent = new Intent(this, InfoGeral.class);
        startActivity(intent);
    }

}