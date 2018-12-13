package com.example.stark.randomnumbergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    Button generatebtn;
    EditText input_min,input_max;
    TextView randomtext;

    int min,max;


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generatebtn= findViewById(R.id.Generatebtn);

        input_max=findViewById(R.id.max_input);
        input_min=findViewById(R.id.min_input);
        randomtext=findViewById(R.id.Random_text);

        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(input_min.getText().length()>0&&input_max.getText().length()>0)
                {
                    if(Long.parseLong(input_max.getText().toString())>=2147483647)
                    {
                        input_max.setText("2147483646");
                    }

                    if(Long.parseLong(input_min.getText().toString())>=2147483647)
                    {
                        input_min.setText("2147483646");
                    }


                    min=Integer.parseInt(input_min.getText().toString());
                    max=Integer.parseInt(input_max.getText().toString());
                    if(min>max)
                    {
                        int temp=min;
                        min=max;
                        max=temp;
                        input_min.setText(Integer.toString(min));
                        input_max.setText(Integer.toString(max));
                    }
                }
                else
                {
                    min=0;
                    max=100;
                }



                int num;

                Random rn= new Random();
                num=rn.nextInt((max-min) +1)+min;
                randomtext.setText(String.valueOf(num));


             //       System.out.print("THE NUMBER IS "+ num);

            }
        });

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }



    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
