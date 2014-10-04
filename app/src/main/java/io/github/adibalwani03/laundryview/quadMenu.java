package io.github.adibalwani03.laundryview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class QuadMenu extends Activity {
	String quad;
	String[] bldgNames;
	int[] ids;
	int num = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		quad = this.getIntent().getExtras().getString("quad");
		setContentView(R.layout.activity_quad_menu);
		if (quad.equals("mendy")) {
            setTitle("Mendelson Quad");
			bldgNames = getResources().getStringArray(R.array.mendyarr);
			ids = getResources().getIntArray(R.array.mendyids);
			createMenu();
		}
        else if (quad.equals("roth")) {
            setTitle("Roth Quad");
            bldgNames = getResources().getStringArray(R.array.rotharr);
            ids = getResources().getIntArray(R.array.rothids);
            createMenu();
        }
        else if (quad.equals("kelly")) {
            setTitle("Kelly Quad");
            bldgNames = getResources().getStringArray(R.array.kellyarr);
            ids = getResources().getIntArray(R.array.kellyids);
            createMenu();
        }
        else if (quad.equals("table")) {
            setTitle("Tabler Quad");
            bldgNames = getResources().getStringArray(R.array.tablarr);
            ids = getResources().getIntArray(R.array.tablids);
            createMenu();
        }
        else if (quad.equals("roose")) {
            setTitle("Roosevelt Quad");
            bldgNames = getResources().getStringArray(R.array.roosearr);
            ids = getResources().getIntArray(R.array.rooseids);
            createMenu();
        }
        else if (quad.equals("south")) {
            setTitle("Southampton");
            bldgNames = getResources().getStringArray(R.array.southarr);
            ids = getResources().getIntArray(R.array.southids);
            createMenu();
        }
        else if (quad.equals("schom")) {
            setTitle("Schomburg Apartments");
            bldgNames = getResources().getStringArray(R.array.schomarr);
            ids = getResources().getIntArray(R.array.schomids);
            createMenu();
        }
        else if (quad.equals("chapin")) {
            setTitle("Chapin Apartments");
            bldgNames = getResources().getStringArray(R.array.chaparr);
            ids = getResources().getIntArray(R.array.chapids);
            createMenu();
        }
        else if (quad.equals("west")) {
            setTitle("West Apartments");
            bldgNames = getResources().getStringArray(R.array.westarr);
            ids = getResources().getIntArray(R.array.westids);
            createMenu();
        }
        else if (quad.equals("hquad")) {
            setTitle("H Quad");
            bldgNames = getResources().getStringArray(R.array.hquadarr);
            ids = getResources().getIntArray(R.array.hquadids);
            createMenu();
        }
        else if (quad.equals("noble")) {
            setTitle("Mendelson Quad");
            bldgNames = getResources().getStringArray(R.array.noblearr);
            ids = getResources().getIntArray(R.array.noblidse);
            createMenu();
        }
	}

	public void createMenu() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.quadList);
		for (int i = 0; i < bldgNames.length; i++) {
			Button btn = new Button(this);
			btn.setText(bldgNames[i]);
			final int pos = i;
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(getBaseContext(), Building.class);
					intent.putExtra("id",ids[pos]);
                    intent.putExtra("quad", "mendy");
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
