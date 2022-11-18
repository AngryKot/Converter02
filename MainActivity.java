package com.example.converter02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Spinner spFrom;
    private Spinner spTo;
    private EditText etFrom;
    private TextView tvTo;

    /*private RadioButton lenght;
    private RadioButton weight;
    private RadioButton square;*/

    private ArrayAdapter<Unit> adp;//инициализация адаптера
    private ArrayList<Unit> Lengths = new ArrayList<>();
    private ArrayList<Unit> Squares = new ArrayList<>();
    private ArrayList<Unit> Weights = new ArrayList<>();

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spFrom = findViewById(R.id.lstFrom);
        spTo = findViewById(R.id.lstTo);
        tvTo = findViewById(R.id.tvTo);
        etFrom = findViewById(R.id.txtFrom);

        RadioGroup radioGroup = findViewById(R.id.rg_RadGroup);

        /*lenght=findViewById(R.id.rb_Lenght);
        lenght.setOnClickListener(radioButtonClickListener);

        weight=findViewById(R.id.rb_Weight);
        weight.setOnClickListener(radioButtonClickListener);

        square=findViewById(R.id.rb_Square);
        square.setOnClickListener(radioButtonClickListener);*/

        adp = new ArrayAdapter<Unit>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        adp.addAll(Lengths);//добавление в адаптер всех элементов массива lengths т к изначально выбрана radioButtonLength

        spFrom.setAdapter(adp);//подключение адаптера к спиннеру From
        spTo.setAdapter(adp);//подключение адаптера к спиннеру To

        Lengths.add(new Unit(0.001, "mm"));//добавление элемента в массив с названием и коэффициентом
        Lengths.add(new Unit(0.01, "cm"));
        Lengths.add(new Unit(1.0, "m"));
        Lengths.add(new Unit(1000.0, "km"));

        Squares.add(new Unit(0.000001, "mm2"));
        Squares.add(new Unit(0.0001, "cm2"));
        Squares.add(new Unit(1.0, "m2"));
        Squares.add(new Unit(1000000.0, "km2"));

        Weights.add(new Unit(0.001, "mg"));
        Weights.add(new Unit(1.0, "g"));
        Weights.add(new Unit(1000.0, "kg"));
        Weights.add(new Unit(1000000.0, "t"));


        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //метод отслеживания включения переключателя, при выборе одного из переключателей происходит очищение адаптера и заполнение его новыми данными из массива
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                        case R.id.rb_Lenght:
                            adp.clear();
                            adp.addAll(Lengths);
                            break;
                            case R.id.rb_Square:
                            adp.clear();
                            adp.addAll(Squares);
                            break;
                        case R.id.rb_Weight:
                            adp.clear();
                            adp.addAll(Weights);
                            break;



                }
            }
        });




    }

    public void onClick(View view)  {

        if (!etFrom.getText().toString().isEmpty()) {
            Double result = Double.parseDouble(etFrom.getText().toString()) * (
                            adp.getItem(
                                    spFrom.getSelectedItemPosition()
                            ).coefficient
                          / adp.getItem(spTo.getSelectedItemPosition()).coefficient
                            );
            tvTo.setText(result.toString());
        } else {
            Toast.makeText(getApplicationContext(), "Требуется заполнить поле From",
                    Toast.LENGTH_SHORT).show();
        }

    }


            //ArrayAdapter <String> adp = new <String> ArrayAdapter(this, android.R.layout.simple_list_item_1);


}