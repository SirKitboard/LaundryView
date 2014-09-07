package io.github.adibalwani03.laundryview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class quadMenu extends Activity {
	String quad;
	String[] bldgNames;
	int[] ids;
	int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    quad = this.getIntent().getExtras().getString("quad");
        setContentView(R.layout.activity_quad_menu);
	    if(quad.equals("mendy")){
		    bldgNames = getResources().getStringArray(R.array.mendyarr);
		    ids = getResources().getIntArray(R.array.mendyids);
		    createMenu();
	    }
    }
	public void createMenu() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.quadRelative);
		for(int i=0;i<bldgNames.length;i++) {
			Button btn = new Button(this);
			btn.setText(bldgNames[i]);
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(getBaseContext(), building.class);
					intent.putExtra("id",ids[num++]);
					startActivity(intent);
				}
			});
			layout.addView(btn);
		}
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mendy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
