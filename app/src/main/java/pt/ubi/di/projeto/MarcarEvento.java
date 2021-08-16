package pt.ubi.di.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MarcarEvento extends AppCompatActivity {

    mySQLiteDBHandler dbHandler;

    private EditText nome_evento;
    private DatePicker data_inicio;
    private DatePicker data_fim;
    private Button marcar_evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_marcar_evento);

        nome_evento = (EditText) findViewById(R.id.editText_nome_evento);
        data_inicio = (DatePicker) findViewById(R.id.datePicker_data_inicio);
        data_fim = (DatePicker) findViewById(R.id.datePicker_data_fim);
        marcar_evento = (Button) findViewById(R.id.button_marcar_evento);
        dbHandler = new mySQLiteDBHandler(this);

        marcar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nome_evento.getText().toString();
                if (nome.length() == 0)
                    makeText("Esqueceu-se do nome do Evento!\n");
                else {
                    int start_day = data_inicio.getDayOfMonth();
                    int start_month = data_inicio.getMonth() + 1;
                    int start_year = data_inicio.getYear();
                    int stop_day = data_fim.getDayOfMonth();
                    int stop_month = data_fim.getMonth() + 1;
                    int stop_year = data_fim.getYear();

                    if(start_year <= stop_year){
                        if(start_month <= stop_month){
                            if(start_day <= stop_day){
                                String start = start_day + "/" + start_month + "/" + start_year;
                                String stop = stop_day + "/" + stop_month + "/" + stop_year;
                                boolean check_insert = dbHandler.addData(nome, start, stop);
                                if(check_insert){
                                    makeText("Success!");
                                }
                                else makeText("Too bad...");
                            }
                        }
                    } else {
                        makeText("The starting date cannot be after end date!");
                    }
                }
            }
        });
    }

    private void makeText(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}