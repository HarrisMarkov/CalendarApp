package pt.ubi.di.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class InfoGeral extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_geral);

        listView = (ListView) findViewById(R.id.listView_infoGeral);
        ArrayList<String> list = new ArrayList<>();
        String str = "1º S - Periodo Letivo\nDE 21/9/2020 ATÉ 18/1/2021\n";
        String str2 = "1º S - Data limite para entrega de dissertação de 2º ciclo\nATÉ 18/1/2021\n";
        String str3 = "1º S - Interdição de Avaliações\nDE 19/1/2021 ATÉ 22/1/2021\n";
        String str4 = "1º S - Exames Época Normal\nDE 25/1/2021 ATÉ 1/2/2021\n";
        String str5 = "1º S - Exames Época Recurso\nDE 8/2/2021 ATÉ 15/2/2021\n";
        String str6 = "INTERRUPÇÃO LETIVA DE 16/2/2021 ATÉ 19/2/2021\n";
        String str7 = "2º S - Periodo Letivo\nDE 22/2/2021 ATÉ 11/6/2021\n";
        String str8 = "2º S - Data limite para entrega de dissertação/relatório de 2º ciclo\nATÉ 14/6/2021\n";
        String str9 = "2º S - Interdição de Avaliações\nDE 15/6/2021 ATÉ 18/6/2021\n";
        String str10 = "2º S - Data limite para entrega de tese de 3º ciclo\nATÉ 21/6/2021\n";
        String str11 = "2º S - Exames Época Normal\nDE 21/6/2021 ATÉ 28/6/2021\n";
        String str12 = "2º S - Interdição de Avaliações\nDE 29/6/2021 ATÉ 2/7/2021\n";
        String str13 = "2º S - Exames Época Recurso\nDE 5/7/2021 ATÉ 12/7/2021\n";
        String str14 = "2º S - Interdição de Avaliações\nDE 13/7/2021 ATÉ 16/7/2021\n";
        String str15 = "2º S - Exames Época Especial\nDE 19/7/2021 ATÉ 26/7/2021\n";
        String str16 = "FIM DO ANO LETIVO - 30/7/2021\n";

        list.add(str);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        list.add(str5);
        list.add(str6);
        list.add(str7);
        list.add(str8);
        list.add(str9);
        list.add(str10);
        list.add(str11);
        list.add(str12);
        list.add(str13);
        list.add(str14);
        list.add(str15);
        list.add(str16);

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);

    }
}