package edu.up.cs371.textmod;

/**
 * class TextModActivity
 *
 * Allow text to be modified in simple ways with button-presses.
 */
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Random;

public class TextModActivity extends ActionBarActivity implements View.OnClickListener {


    protected Button reverseText;
    protected Button copyButton;
    protected Spinner spinner;
    protected Button alter;
    protected Button spaces;

    // array-list that contains our images to display
    private ArrayList<Bitmap> images;

    // instance variables containing widgets
    private ImageView imageView; // the view that shows the image
    protected EditText textInput;
    protected Button clearText;
    protected Button lowerCase;
    protected Button upperCase;
    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // perform superclass initialization; load the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_mod);

        // set instance variables for our widgets
        imageView = (ImageView)findViewById(R.id.imageView);

        textInput = (EditText)findViewById(R.id.editText);
        reverseText = (Button)findViewById(R.id.button4);
        reverseText.setOnClickListener(this);
        copyButton = (Button)findViewById(R.id.button2);
        copyButton.setOnClickListener(this);
        spaces = (Button)findViewById(R.id.spaceButton);
        spaces.setOnClickListener(this);


        // Set up the spinner so that it shows the names in the spinner array resources
        //
        // get spinner object
        spinner = (Spinner)findViewById(R.id.spinner);
        // get array of strings
        String[] spinnerNames = getResources().getStringArray(R.array.spinner_names);
        // create adapter with the strings
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, spinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        spinner.setAdapter(adapter);

        // load the images from the resources
        //
        // create the arraylist to hold the images
        images = new ArrayList<Bitmap>();
        // get array of image-resource IDs
        TypedArray imageIds2 = getResources().obtainTypedArray(R.array.imageIdArray);
        // loop through, adding one image per string
        for (int i = 0; i < spinnerNames.length; i++) {
            // determine the index; use 0 if out of bounds
            int id = imageIds2.getResourceId(i,0);
            if (id == 0) id = imageIds2.getResourceId(0,0);
            // load the image; add to arraylist
            Bitmap img = BitmapFactory.decodeResource(getResources(), id);
            images.add(img);
        }

        // define a listener for the spinner
        spinner.setOnItemSelectedListener(new MySpinnerListener());
        clearText = (Button) findViewById(R.id.button);
        textInput = (EditText) findViewById(R.id.editText);
        clearText.setOnClickListener(this);
        lowerCase = (Button) findViewById(R.id.button7);
        lowerCase.setOnClickListener(this);
        upperCase = (Button) findViewById(R.id.button6);
        upperCase.setOnClickListener(this);
        alter= (Button) findViewById(R.id.alt);
        alter.setOnClickListener(this);
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_mod, menu);
        return true;
    }

    /**
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
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


    @Override
    public void onClick(View v) {
        if (v == reverseText){
            String temp = textInput.getText()+"";
            String reverse = new StringBuilder(temp).reverse().toString();
            textInput.setText(reverse);
        }
        else if (v == copyButton) {
            String selected = (String) spinner.getSelectedItem();
            String display = textInput.getText() + selected;
            textInput.setText(display);
        }
       else if(v== clearText ) {
            textInput.setText("");
        }
        else if(v== lowerCase){
            textInput.setText((textInput.getText()+"").toLowerCase());
        }
        else if(v== upperCase){
            textInput.setText((textInput.getText()+"").toUpperCase());
        }
        else if(v == spaces) {
            String text = textInput.getText().toString().replace(" ", "");
            textInput.setText(text);
        }
        else if(v== alter){
            String str = new String(textInput.getText()+"");
            char [] chr= str.toCharArray();
            int n = chr.length;
            char ch;
            int i;
            for(i = 0; i < n; i++) {
                if(i % 2 == 0) {
                    ch = Character.toLowerCase(chr[i]);
                    chr[i]=ch;
                } else {
                    ch = Character.toUpperCase(chr[i]);
                    chr[i]=ch;
                }
            }
            textInput.setText(new String(chr)); }


            }






    /**
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *                  android.widget.AdapterView, android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            // set the image to the one corresponding to the index selected by the spinner
            imageView.setImageBitmap(images.get(position));
        }

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(
         *                  android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }
    }
}
