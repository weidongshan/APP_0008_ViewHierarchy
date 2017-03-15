package com.thisway.app_0001_leddemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {

    private String TAG="LedDemo";
    private boolean ledon = false;
    private Button button = null;
    private CheckBox checkBoxLed1 = null;
    private CheckBox checkBoxLed2 = null;
    private CheckBox checkBoxLed3 = null;
    private CheckBox checkBoxLed4 = null;

    public void printViewHierarchy(View parent, int level, int childidx){
        /*
         *   * DecorView child -1 (x, y), (w, h)
         *   ** FrameLayout child 0 (x, y), (w, h)
         *   *** TextView  child 0  (x, y), (w, h)
         *   ** FrameLayout child 1 (x, y), (w, h)
         *   *** Button   child 0   (x, y), (w, h)
         *   *** TextView  child 1  (x, y), (w, h)
         *   *** FrameLayout  child 2 (x, y), (w, h)
         */
        int i;
        String levelStr = "*";
        for (i = 0; i < level; i++)
            levelStr += "*";

        int[] positons = new int[2];
        parent.getLocationOnScreen(positons);

        Log.d(TAG, levelStr + " " + parent.getClass().getName() + " child " + childidx + " (" + positons[0] + ", " + positons[1] + "), ("+parent.getWidth()+", "+parent.getHeight()+")");

        if (parent instanceof ViewGroup){
            View child;
            ViewGroup root = (ViewGroup)parent;
            i = 0;
            while ((child = root.getChildAt(i)) != null){
                printViewHierarchy(child, level+1, i);
                i++;
            }
        }

    }

    class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            View decorView = getWindow().getDecorView();
            printViewHierarchy(decorView, 0, -1);
        }
    }

    public void showdecorViewChild(int idx) {
        ViewGroup decor = (ViewGroup)getWindow().getDecorView();
        View v = decor.getChildAt(idx);
        if (v != null)
            v.setVisibility(View.VISIBLE);
    }

    public void hidedecorViewChild(int idx) {
        ViewGroup decor = (ViewGroup)getWindow().getDecorView();
        View v = decor.getChildAt(idx);
        if (v != null)
            v.setVisibility(View.INVISIBLE);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.LED1:
                if (checked) {
                    // Put some meat on the sandwich
                    Toast.makeText(getApplicationContext(), "LED1 on", Toast.LENGTH_SHORT).show();
                    hidedecorViewChild(1);
                }
                else {
                    // Remove the meat
                    Toast.makeText(getApplicationContext(), "LED1 off", Toast.LENGTH_SHORT).show();
                    showdecorViewChild(1);
                }
                break;
            case R.id.LED2:
                if (checked) {
                    // Put some meat on the sandwich
                    Toast.makeText(getApplicationContext(), "LED2 on", Toast.LENGTH_SHORT).show();
                    hidedecorViewChild(2);
                }
                else {
                    // Remove the meat
                    Toast.makeText(getApplicationContext(), "LED2 off", Toast.LENGTH_SHORT).show();
                    showdecorViewChild(2);
                }
                break;

            case R.id.LED3:
                if (checked) {
                    // Put some meat on the sandwich
                    Toast.makeText(getApplicationContext(), "LED3 on", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Remove the meat
                    Toast.makeText(getApplicationContext(), "LED3 off", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.LED4:
                if (checked) {
                    // Put some meat on the sandwich
                    Toast.makeText(getApplicationContext(), "LED4 on", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Remove the meat
                    Toast.makeText(getApplicationContext(), "LED4 off", Toast.LENGTH_SHORT).show();
                }
                break;
            // TODO: Veggie sandwich
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.BUTTON);

        checkBoxLed1 = (CheckBox) findViewById(R.id.LED1);
        checkBoxLed2 = (CheckBox) findViewById(R.id.LED2);
        checkBoxLed3 = (CheckBox) findViewById(R.id.LED3);
        checkBoxLed4 = (CheckBox) findViewById(R.id.LED4);

        button.setOnClickListener(new MyButtonListener());
/*
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ledon = !ledon;
                if (ledon)
                    button.setText("ALL OFF");
                else
                    button.setText("ALL ON");
            }
        });
*/
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
}
