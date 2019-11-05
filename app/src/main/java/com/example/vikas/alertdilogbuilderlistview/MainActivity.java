package com.example.vikas.alertdilogbuilderlistview;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayAdapter<String> arrayAdapter;
    String[] arrayNmae;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText =(EditText)findViewById(R.id.editText);

        arrayNmae= getResources().getStringArray(R.array.cityNames);
        //arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,arrayNmae);

        editText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Select your city");

        builder.setSingleChoiceItems(arrayNmae, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String data = arrayNmae[which];
                editText.setText(data);
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                if(selectedPosition==-1)
                {

                    Toast.makeText(MainActivity.this, "Select you city", Toast.LENGTH_SHORT).show();
                }
                else {

                    String data = arrayNmae[selectedPosition];
                    editText.setText(data);
                }
            }
        });
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String data=arrayAdapter.getItem(which);
                editText.setText(data);
            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
