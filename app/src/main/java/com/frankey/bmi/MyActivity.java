package com.frankey.bmi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MyActivity extends Activity {

    //delcaring initial variables
    TextView bmi_answer, bmi_status, bmi_info;
    EditText weight, height1, height2;
    Button calculate;
    Spinner spinnerWeight, spinnerHeight1, spinnerHeight2;
    String sWeight, sHeight1, sHeight2, spinnerWeightText, spinnerHeight1Text, spinnerHeight2Text;

    InputMethodManager inputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setTitle("Body mass index");

        //Shared preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        bmi_status = (TextView) findViewById(R.id.textViewBMIstatus);
        weight = (EditText) findViewById(R.id.editTextWeight);
        height1 = (EditText) findViewById(R.id.editTextHeight1);
        height2 = (EditText) findViewById(R.id.editTextHeight2);
        spinnerWeight = (Spinner) findViewById(R.id.spinnerWeight);
        spinnerHeight1 = (Spinner) findViewById(R.id.spinnerHeight1);
        spinnerHeight2 = (Spinner) findViewById(R.id.spinnerHeight2);


        //get Shared preferences for Weight Hint
        String weightHint = prefs.getString(getString(R.string.pref_weight_key),
                getString(R.string.pref_weight_metric));

        //get Shared preferences for Height Hint
        String heightHint = prefs.getString(getString(R.string.pref_height_key),
                getString(R.string.pref_height_metric));

        bmi_answer = (TextView) findViewById(R.id.textViewBMIAnswer);
        bmi_info = (TextView) findViewById(R.id.textViewBMIinfo);
        calculate = (Button) findViewById(R.id.buttonBMI);
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sWeight= weight.getText().toString();
                sHeight1 = height1.getText().toString();
                sHeight2 = height2.getText().toString();
                spinnerWeightText = spinnerWeight.getSelectedItem().toString();
                spinnerHeight1Text = spinnerHeight1.getSelectedItem().toString();
                spinnerHeight2Text = spinnerHeight2.getSelectedItem().toString();


                if (sWeight.equals("") || sHeight1.equals("") || sHeight2.equals("")) {

                }

                else {
                    Double dWeight = Double.parseDouble(weight.getText().toString());
                    Double dHeight1 = Double.parseDouble(height1.getText().toString());
                    Double dHeight2 = Double.parseDouble(height2.getText().toString());
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    if (spinnerWeightText.equals("pounds") && spinnerHeight1Text.equals("feet") &&
                            spinnerHeight2Text.equals("inches")) {
                        Double dHeightTotal = ((dHeight1*12) + (dHeight2));
                        int answer = (int) Math.round((dWeight / ((dHeightTotal) * (dHeightTotal)))*703);
                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText(getString(R.string.normal_weight));
                            bmi_info.setText(getString(R.string.what_does_this_mean));
                        }
                        else if (answer < 18) {
                            if (answer < 16){
                                bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                                bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                                bmi_status.setText("Severe thinness");
                                bmi_info.setText(getString(R.string.what_does_this_mean));
                            }
                            bmi_answer.setTextColor(getResources().getColor(R.color.orange_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.orange_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText(getString(R.string.what_does_this_mean));
                        }
                        else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText(getString(R.string.what_does_this_mean));
                        }
                    }
                    else if (spinnerWeightText.equals("kilograms") && spinnerHeight1Text.equals("feet") &&
                            spinnerHeight2Text.equals("inches")){
                        Double dHeightTotal = ((dHeight1*12) + (dHeight2));
                        int answer = (int) Math.round(((dWeight*2.20462) / ((dHeightTotal) * (dHeightTotal)))*703);
                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText("Normal weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer < 18) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                    }
                    else if (spinnerWeightText.equals("pounds") && spinnerHeight1Text.equals("metres") &&
                            spinnerHeight2Text.equals("inches")){
                        Double dHeightTotal = ((dHeight1*39.3700787) + (dHeight2));
                        int answer = (int) Math.round((dWeight / ((dHeightTotal) * (dHeightTotal)))*703);
                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText("Normal weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer < 18) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                    }
                    else if (spinnerWeightText.equals("pounds") && spinnerHeight1Text.equals("feet") &&
                            spinnerHeight2Text.equals("centimetres")){
                        Double dHeightTotal = ((dHeight1*12) + (dHeight2*0.393700787));
                        int answer = (int) Math.round((dWeight / ((dHeightTotal) * (dHeightTotal)))*703);
                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText("Normal weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer < 18) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                    }
                    else if (spinnerWeightText.equals("kilograms") && spinnerHeight1Text.equals("metres") &&
                            spinnerHeight2Text.equals("inches")){
                        Double dHeightTotal = ((dHeight1) + (dHeight2*0.0254));
                        int answer = (int) Math.round(dWeight / ((dHeightTotal) * (dHeightTotal)));
                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText("Normal weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer < 18) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                    }
                    else if (spinnerWeightText.equals("kilograms") && spinnerHeight1Text.equals("metres") &&
                            spinnerHeight2Text.equals("centimetres")){
                        Double dHeightTotal = ((dHeight1) + (dHeight2/100));
                        int answer = (int) Math.round(dWeight / ((dHeightTotal) * (dHeightTotal)));
                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText("Normal weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer < 18) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                    }
                    else if (spinnerWeightText.equals("pounds") && spinnerHeight1Text.equals("metres") &&
                            spinnerHeight2Text.equals("centimetres")){
                        Double dHeightTotal = ((dHeight1) + (dHeight2/100));
                        int answer = (int) Math.round((dWeight*0.453592) / ((dHeightTotal) * (dHeightTotal)));
                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText("Normal weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer < 18) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                        else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }
                    }


                }
            }
        });


        height1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    String sWeight = weight.getText().toString();
                    String sHeight = height1.getText().toString();

                    if (sWeight.equals("") && sHeight.equals("")) {

                    } else if (!sWeight.equals("") && sHeight.equals("")) {

                    } else if (!sHeight.equals("") && sWeight.equals("")) {

                    } else {
                        Double dHeight = Double.parseDouble(height1.getText().toString());
                        Double dWeight = Double.parseDouble(weight.getText().toString());
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        int answer = (int) Math.round(dWeight / ((dHeight / 100) * (dHeight / 100)));

                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText("Normal weight");
                            bmi_info.setText("What does this mean, exactly?");
                        } else if (answer < 18) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText("What does this mean, exactly?");
                        } else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }

                    }
                }

                return false;
            }
        });

        weight.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    String sWeight = weight.getText().toString();
                    String sHeight = height1.getText().toString();

                    if (sWeight.equals("") && sHeight.equals("")) {

                    } else if (!sWeight.equals("") && sHeight.equals("")) {

                    } else if (!sHeight.equals("") && sWeight.equals("")) {

                    } else {
                        Double dHeight = Double.parseDouble(height1.getText().toString());
                        Double dWeight = Double.parseDouble(weight.getText().toString());
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        int answer = (int) Math.round(dWeight / ((dHeight / 100) * (dHeight / 100)));

                        bmi_answer.setText(Integer.toString(answer));
                        if (answer >= 18 && answer <= 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.green_500));
                            bmi_status.setText("Normal weight");
                            bmi_info.setText("What does this mean, exactly?");
                        } else if (answer < 18) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Under weight");
                            bmi_info.setText("What does this mean, exactly?");
                        } else if (answer > 25) {
                            bmi_answer.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setTextColor(getResources().getColor(R.color.red_500));
                            bmi_status.setText("Over weight");
                            bmi_info.setText("What does this mean, exactly?");
                        }

                    }
                }

                return false;
            }
        });

        bmi_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BMI_info_normal.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.action_about) {
            startActivity(new Intent(this, About.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
