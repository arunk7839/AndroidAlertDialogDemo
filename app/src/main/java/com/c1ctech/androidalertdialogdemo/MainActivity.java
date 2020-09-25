package com.c1ctech.androidalertdialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAlertDialogWithButtons, btnAlertDialogWithList, btnAlertDialogWithCustomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get button by its id
        btnAlertDialogWithButtons = findViewById(R.id.btn_adw_buttons);
        btnAlertDialogWithList = findViewById(R.id.btn_adw_list);
        btnAlertDialogWithCustomLayout = findViewById(R.id.btn_adw_customlayout);

        //set click listener on btnAlertDialogWithMessage button
        btnAlertDialogWithButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialogWithButtons();
            }
        });

        //set click listener on btnAlertDialogWithList button
        btnAlertDialogWithList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialogWithList();
            }
        });

        //set click listener on btnAlertDialogWithCustomLayout button
        btnAlertDialogWithCustomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialogWithCustomLayout();
            }
        });

    }

    //create and show alert dialog with title, message, buttons(positive,negative and neutral).
    void createAlertDialogWithButtons() {

        //create an instance of AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //set dialog properties
        //Set the title displayed in the Dialog.
        builder.setTitle(R.string.alert_dialog_title)
                //Set the icon to be used in the title.
                .setIcon(R.drawable.alert_icon)
                //Set the message to display in the Dialog.
                .setMessage(R.string.dialog_message);

        //Set a listener to be invoked when the positive button of the dialog is pressed.
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked OK button
                dialogInterface.dismiss();
                Toast.makeText(getApplicationContext(), "OK pressed ", Toast.LENGTH_SHORT).show();
            }
        });

        //Set a listener to be invoked when the negative button of the dialog is pressed.
        builder.setNegativeButton(R.string.dialog_btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked CANCEL button
                dialogInterface.dismiss();
                Toast.makeText(getApplicationContext(), "CANCEL pressed ", Toast.LENGTH_SHORT).show();

            }
        });

        //Set a listener to be invoked when the neutral button of the dialog is pressed.
        builder.setNeutralButton(R.string.dialog_btn_got_it, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked GOT IT button
                dialogInterface.dismiss();
                Toast.makeText(getApplicationContext(), "GOT IT pressed ", Toast.LENGTH_SHORT).show();

            }
        });

        //create an AlertDialog
        AlertDialog alertDialog = builder.create();

        //display the dialog on screen
        alertDialog.show();


    }

    //create and show alert dialog with title and single-choice list.
    void createAlertDialogWithList() {

        //create an instance of AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //get string array (languages)
        final String[] language = getResources().getStringArray(R.array.languages);

        builder.setTitle(getText(R.string.select_language_title))

                //Set language(list of languages) to be displayed in the dialog as the content.
                .setItems(language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int indexPosition) {

                        // The 'indexPosition' argument contains the index position
                        // of the selected item
                        String selectedItem = Arrays.asList(language).get(indexPosition);
                        Toast.makeText(getApplicationContext(), "Selected Language: " + selectedItem, Toast.LENGTH_LONG).show();

                    }
                });

        //create an AlertDialog
        AlertDialog alertDialog = builder.create();

        //display the dialog on screen
        alertDialog.show();
    }

    //create and show alert dialog with custom layout.
    void createAlertDialogWithCustomLayout() {

        //create an instance of AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //get view object represents custom layout of dialog.
        View view = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);

        //get button and spinner by its id from view object.
        final Spinner spinner = view.findViewById(R.id.spinner);
        Button btnOk = view.findViewById(R.id.btn_ok);

        ArrayList<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.languages)));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set adapter in spinner.
        spinner.setAdapter(adapter);

        //Sets a custom view to be the contents of the alert dialog.
        builder.setView(view);

        //create an AlertDialog
        final AlertDialog dialog = builder.create();

        //set click listener on OK button click
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String selectedItem = (String) spinner.getSelectedItem();
                Toast.makeText(getApplicationContext(), "Selected Language: " + selectedItem, Toast.LENGTH_LONG).show();
            }
        });

        //display the dialog on screen
        dialog.show();

    }
}