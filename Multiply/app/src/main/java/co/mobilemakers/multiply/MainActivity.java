package co.mobilemakers.multiply;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void multiply(View button){
         float val1, val2;
        EditText value1 = (EditText) findViewById(R.id.editText);
        EditText value2 = (EditText) findViewById(R.id.editText2);
        TextView textViewResult = (TextView) findViewById(R.id.textView3);

        try{
            val1 = Float.parseFloat(value1.getText().toString());
            val2 = Float.parseFloat(value2.getText().toString());

            textViewResult.setTextColor(Color.BLUE);
            textViewResult.setText("Result: "+ val1* val2);
        }
        catch(NumberFormatException e){
            textViewResult.setTextColor(Color.RED);
            textViewResult.setText("Does not compute");
        } catch (Exception e){
            textViewResult.setTextColor(Color.RED);
            textViewResult.setText("ERROR:"+e.getMessage());
        }

    }
}
