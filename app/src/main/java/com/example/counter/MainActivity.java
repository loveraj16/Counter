package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView result;
    Button start,stop,reset;
    Counter myTask;
    int sum=0,c=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=(TextView)findViewById(R.id.textView);
        start=(Button)findViewById(R.id.button);
        stop=(Button)findViewById(R.id.button2);
        reset=(Button)findViewById(R.id.button3);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum=0;
                c=1;
                result.setText("0");
            }
        });
    }

    public void startCounter(View view) {
        myTask=(Counter)new Counter().execute();
    }

    public void stopCounter(View view) {
        myTask.cancel(true);
    }


    private class Counter extends AsyncTask<Void,Integer,Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            result.setText("0");
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            for (int i=c; isCancelled()==false ; i++){
                sum=sum+i;
                c++;
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isCancelled()) break;;
            }
            return sum;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            result.setText(values[0].toString());
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
            Toast.makeText(MainActivity.this,integer.toString(),Toast.LENGTH_LONG).show();
        }
    }
}