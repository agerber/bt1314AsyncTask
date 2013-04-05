package com.example.btasynctask01;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:tools="http://schemas.android.com/tools"
//android:id="@+id/LinearLayout1"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//android:orientation="vertical"
//tools:context=".MainActivity" >
//
//<Button
//    android:id="@+id/btnStart"
//    android:layout_width="fill_parent"
//    android:layout_height="wrap_content"
//    android:text="Start" />
//
//<ProgressBar
//    android:id="@+id/pgbShow"
//    style="?android:attr/progressBarStyleHorizontal"
//    android:layout_width="fill_parent"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="8dp"
//    android:max="100" />
//
//</LinearLayout>

public class MainActivity extends Activity implements OnClickListener {

	ProgressBar pgbBar;
	Button btnStart;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pgbBar = (ProgressBar) findViewById(R.id.pgbShow);
		pgbBar.setProgress(0);
		
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		BackgroundAsyncTask batTask = new BackgroundAsyncTask();
		batTask.execute("100","20");
		
	}

	// ################################################
	// AsyncTask to perform some task
	// ################################################
	//private inner class that handles the thread -- beyond the UI thread 
	//the Params passed into the AsyncTask
	//	new BackgroundAsyncTask().execute("100","20");
	  //params, the type of the parameters sent to the task upon execution.
	  //values, the type of the progress units published during the background computation.
	  //result, the type of the result of the background computation.
	
	  //you can change the types of the generic params to whatever objects you want
	private class BackgroundAsyncTask extends AsyncTask<String, Integer, Double> {


		//passed in as var-args. put however many you want. 
		@Override
		protected Double doInBackground(String... params) {

			int nTotal = Integer.parseInt(params[0]);
			int nDelay = Integer.parseInt(params[1]);

			int nCount = 0;
			
			while (nCount++ < nTotal) {
				//the following method calls onProgressUpdate(nCount)
				publishProgress(nCount);
				SystemClock.sleep(nDelay);
			}
			return new java.util.Random().nextDouble();
		
		}

		//passed in as a chunk of data. 
		@Override
		protected void onProgressUpdate(Integer... values) {

			pgbBar.setProgress(values[0]);
		}
		
		
		@Override
		protected void onPostExecute(Double result) {

			Toast.makeText(MainActivity.this, "onPostExecute "+ result,
					Toast.LENGTH_LONG).show();

		}

		//totally optional
		@Override
		protected void onPreExecute() {

			Toast.makeText(MainActivity.this, "onPreExecute", Toast.LENGTH_LONG)
					.show();

		}



	}

}
