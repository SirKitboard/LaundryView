package io.github.adibalwani03.laundryview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Building extends Activity {
	int id;
    String quad;
    String[] bldgNames;
    int[] ids;
	String url = "http://www.laundryview.com/appliance_status_ajax.php?lr=";
	String url2 = "http://www.laundryview.com/classic_laundry_room_ajax.php?lr=";
	String html;
	int washers;
	int driers;
	ArrayList<Machine> machineList;
	ProgressDialog dialog;
	RequestTask requestTask;
	RequestTask requestTask2;
	int status = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = this.getIntent().getExtras().getInt("id");
        quad = this.getIntent().getExtras().getString("quad");
        if(quad.equals("mendy")) {
            bldgNames = getResources().getStringArray(R.array.mendyarr);
            ids = getResources().getIntArray(R.array.mendyids);
            for(int i=0;i<ids.length;i++) {
                if (id == ids[i])
                    setTitle(bldgNames[i] + " Machine Status");
            }
        }
        if(quad.equals("roth")) {
            bldgNames = getResources().getStringArray(R.array.rotharr);
            ids = getResources().getIntArray(R.array.rothids);
            for(int i=0;i<ids.length;i++) {
                if (id == ids[i])
                    setTitle(bldgNames[i] + " Machine Status");
            }
        }
		url += id;
		url2+=id;
		setContentView(R.layout.activity_building);
		//RefreshTimer refreshTimer = new RefreshTimer();
		//refreshTimer.start();
		dialog = ProgressDialog.show(Building.this, "", "Loading. Please wait...", true);
		requestTask = new RequestTask();
		requestTask2 = new RequestTask();
		requestTask.execute(url);
		status=1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.building, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_reload) {
			refreshData();
		}
		return super.onOptionsItemSelected(item);
	}

    public void refreshData() {
        dialog = ProgressDialog.show(Building.this, "", "Loading. Please wait...", true);
        machineList = new ArrayList<Machine>();
        requestTask = new RequestTask();
        requestTask.execute(url);
    }

	public void processData(String result) {
		if(result.length()<1000) {
			String[] temp = result.split("of");
			washers = Integer.parseInt(temp[1].substring(0,temp[1].indexOf("a")).trim());
			driers = Integer.parseInt(temp[2].substring(0,temp[2].indexOf("a")).trim());
			machineList = new ArrayList<Machine>();
            requestTask2 = new RequestTask();
			requestTask2.execute(url2);
		}
		else {
			String[] data = result.split("<span class=\"stat\">");
			for(int i=0;i<washers;i++) {
				String status = data[i+1].substring(0,data[i+1].indexOf("</span>"));
				if(status.trim().equalsIgnoreCase("available")) {
					Machine machine = new Machine(i+1,0,0,true);
					machineList.add(machine);
				}
				else if (status.contains("cycle")) {
					int index = status.indexOf("min");
                    String endedS = status.substring(index - 3, index);
                    int ended = Integer.parseInt(endedS.trim());
					Machine machine = new Machine(i+1,1,ended,true);
					machineList.add(machine);
				}

				else if (status.contains("remaining")) {
					int index = status.indexOf("min");
                    String rem = status.substring(index-3,index);
					int ended = Integer.parseInt(rem.trim());
					Machine machine = new Machine(i+1,2,ended,true);
					machineList.add(machine);
				}
				else if (status.contains("service")) {
					Machine machine = new Machine(i+1,-1,0,true);
					machineList.add(machine);
                    washers++;
				}
			}
            boolean isnextWasher = true;
            while(isnextWasher) {
                String status = data[washers+1].substring(0,data[washers+1].indexOf("</span>"));
                if(status.contains("service")) {
                    Machine machine = new Machine(washers+1,-1,0,true);
                    machineList.add(machine);
                    washers++;
                }
                else isnextWasher = false;
            }
			for(int i=0;i<driers;i++) {
				String status = data[washers+i+1].substring(0,data[washers+i+1].indexOf("</span>"));
				if(status.trim().equalsIgnoreCase("available")) {
					Machine machine = new Machine(washers+i+1,0,0,false);
					machineList.add(machine);
				}
				else if (status.contains("cycle")) {
                    int index = status.indexOf("min");
                    String endedS = status.substring(index-3,index);
                    int ended = Integer.parseInt(endedS.trim());
                    Machine machine = new Machine(washers+i+1,1,ended,false);
					machineList.add(machine);
				}

				else if (status.contains("remaining")) {
                    int index = status.indexOf("min");
                    String rem = status.substring(index-3,index);
                    int ended = Integer.parseInt(rem.trim());
                    Machine machine = new Machine(washers+i+1,2,ended,false);
					machineList.add(machine);
				}
				else if (status.contains("service")) {
					Machine machine = new Machine(washers+i+1,-1,0,false);
					machineList.add(machine);
				}
			}
			display();
		}
	}
	public void display() {
		ListView washersView = (ListView) findViewById(R.id.washerAvail);
		MachineAdapter machineAdapter =  new MachineAdapter(this,R.layout.machine_row,machineList);
		washersView.setAdapter(machineAdapter);
		dialog.hide();

	}

	class RequestTask extends AsyncTask<String, String, String> {
		String response2;

		@Override
		protected String doInBackground(String... uri) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			String responseString = null;
			try {
				response = httpclient.execute(new HttpGet(uri[0]));
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					out.close();
					responseString = out.toString();
				} else {
					//Closes the connection.
					response.getEntity().getContent().close();
					throw new IOException(statusLine.getReasonPhrase());
				}
			} catch (ClientProtocolException e) {

			} catch (IOException e) {

			}
			response2 = responseString;
			return responseString;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			html = result;
			processData(result);
		}

		public String getResponse() {
			return response2;
		}
	}

	/*public void makeRequest() {
		dialog = ProgressDialog.show(Building.this, "", "Loading. Please wait...", true);
		requestTask.execute(url);
	}*/

	/*public class RefreshTimer extends Thread {
		@Override
		public void run() {
			super.run();
			Looper.prepare();
			while (true) {
				switch (status) {
					case 0:
						break;
					case 1:
						makeRequest();
					break;
					case 2:
						try {
							sleep(10000);
							status=1;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		}
	}*/
}