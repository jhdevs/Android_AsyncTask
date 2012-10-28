package com.jhdevs.test.asynctasks;

import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	MyAsyncTask mt;
	TextView out;

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	
	    out = (TextView) findViewById(R.id.out);
	}

	public void onclick(View v) {
		switch (v.getId()) {
	    case R.id.btnStart:
		    startTask();
		    break;
	    case R.id.btnStatus:
			showStatus();
			break;
	    default:
	    	break;
	    }
	}
	
	/*private void startTask() {
		mt = new MyTask();
	}*/
	
	private void startTask() {
		mt = new MyAsyncTask();
	    mt.execute();
	    //mt.cancel(false);
	}

	private void showStatus() {
		if (mt != null)
			if (mt.isCancelled())
				Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(this, mt.getStatus().toString(), Toast.LENGTH_SHORT).show();
	}

	class MyAsyncTask extends AsyncTask<Void, Void, Void> {
	    @Override
	    protected void onPreExecute() {
	    	super.onPreExecute();
	    	out.setText("Begin");
	    }

	    @Override
	    protected Void doInBackground(Void... params) {
	    	try {
	    		for (int i = 0; i < 5; i++) {
	    			if (isCancelled()) return null;
	    			TimeUnit.SECONDS.sleep(1);
	    		}
	    	} catch (InterruptedException e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	    	super.onPostExecute(result);
	    	out.setText("End");
	    }
	    
	    @Override
	    protected void onCancelled() {
	    	super.onCancelled();
	    	out.setText("Cancel");
	    }
	  }
}
