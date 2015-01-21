package co.mobilemakers.stylizer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Type;

public class MainActivity extends ActionBarActivity {

    final  static  String LOG_TAG = "MainActivity";

    TextView textViewBody;
    TextView errorTextView;
    EditText colorText;
    CheckBox checkNormal;
    CheckBox checkBold;
    CheckBox checkItalic;
    RadioButton radioNormalFont;
    RadioButton radioCustomFont;
    Button buttonStylize;
    Typeface typeFace;
    String typeFacePath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorTextView =  (TextView)findViewById(R.id.error_text_view);
        textViewBody = (TextView)findViewById(R.id.text_view_body);
        colorText = (EditText)findViewById(R.id.edit_text_color);
        checkNormal = (CheckBox)findViewById(R.id.check_box_normal);
        checkBold= (CheckBox)findViewById(R.id.check_box_bold);
        checkItalic = (CheckBox)findViewById(R.id.check_box_italic);
        radioNormalFont = (RadioButton)findViewById(R.id.radio_normal_font);
        radioCustomFont = (RadioButton)findViewById(R.id.radio_custom_font);
        buttonStylize = (Button)findViewById(R.id.button_stylize);

        checkNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNormal();
            }
        });
        checkItalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               oncClickItalic();
            }
        });

        checkBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oncClickBold();
            }
        });

        radioNormalFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioNormalFont();
            }
        });

        radioCustomFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioCustomFont();
            }
        });

        buttonStylize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onButtonClick();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickNormal(){
        if(checkNormal.isChecked()){
            checkBold.setEnabled(false);
            checkItalic.setEnabled(false);
            checkBold.setChecked(false);
            checkItalic.setChecked(false);
            Log.d(LOG_TAG,"Click Check Normal:"+checkNormal.isChecked());
        }
        else {
            checkBold.setEnabled(true);
            checkItalic.setEnabled(true);
            Log.d(LOG_TAG,"Click Check Normal:"+checkNormal.isChecked());
        }
    }
    public void oncClickBold(){

        if(!checkBold.isChecked() && !checkItalic.isChecked()){
            checkBold.setEnabled(false);
            checkItalic.setEnabled(false);
            checkBold.setChecked(false);
            checkItalic.setChecked(false);
            checkNormal.setChecked(true);
        }
    }

    public void oncClickItalic(){

        if(!checkBold.isChecked() && !checkItalic.isChecked()){
            checkBold.setEnabled(false);
            checkItalic.setEnabled(false);
            checkBold.setChecked(false);
            checkItalic.setChecked(false);
            checkNormal.setChecked(true);
        }
    }


    public void onClickRadioNormalFont(){
        radioCustomFont.setChecked(false);

    }

    public void onClickRadioCustomFont(){
        radioNormalFont.setChecked(false);
    }

    public void onButtonClick(){

        if(radioCustomFont.isChecked()) {
            try {
                if(checkNormal.isChecked()) {
                    typeFace = Typeface.createFromAsset(getAssets(), "fonts/Averia-Regular.ttf");
                }
                else if(checkItalic.isChecked() && !checkBold.isChecked()){
                    typeFace = Typeface.createFromAsset(getAssets(), "fonts/Averia-Italic.ttf");
                }
                else if(checkBold.isChecked() &&  !checkItalic.isChecked()){
                    typeFace = Typeface.createFromAsset(getAssets(), "fonts/Averia-Bold.ttf");
                }
                else  if(checkBold.isChecked() && checkItalic.isChecked()){
                    typeFace = Typeface.createFromAsset(getAssets(), "fonts/Averia-BoldItalic.ttf");
                }

                textViewBody.setTypeface(typeFace);

            } catch (Exception e) {
                e.printStackTrace();
                Log.d(LOG_TAG, "Exception to try typeFace:" + e.getMessage());

            }

        }else {
            if(checkNormal.isChecked()) {
                textViewBody.setTypeface(null, Typeface.NORMAL);
            }
            else if(checkItalic.isChecked() && !checkBold.isChecked()){
                textViewBody.setTypeface(null, Typeface.ITALIC);
            }
            else if(checkBold.isChecked() && !checkItalic.isChecked()){
                textViewBody.setTypeface(null, Typeface.BOLD);
            }
            else  if(checkBold.isChecked() && checkItalic.isChecked()){
                textViewBody.setTypeface(null, Typeface.BOLD_ITALIC);
            }
        }

        try {
            textViewBody.setTextColor(Color.parseColor(colorText.getText().toString().trim().toUpperCase()));
            errorTextView.setText("");
        }
        catch(Exception e){
            errorTextView.setTypeface(null, Typeface.BOLD);
            errorTextView.setTextColor(Color.RED);
            errorTextView.setText("Error: Color '"+colorText.getText()+"' not exist.");
        }

    }


}
