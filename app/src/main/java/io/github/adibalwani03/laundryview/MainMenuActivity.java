package io.github.adibalwani03.laundryview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainMenuActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolBar);
}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
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

	public void mendy(View v) {
		Intent intent = new Intent(this, QuadMenu.class);
		intent.putExtra("quad", "mendy");
		startActivity(intent);
	}
    public void roth(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "roth");
        startActivity(intent);
    }

    public void hquad(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "hquad");
        startActivity(intent);
    }

    public void chapin(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "chapin");
        startActivity(intent);
    }

    public void kelly(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "kelly");
        startActivity(intent);
    }

    public void noble(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "noble");
        startActivity(intent);
    }

    public void roose(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "roose");
        startActivity(intent);
    }

    public void schom(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "schom");
        startActivity(intent);
    }

    public void table(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "table");
        startActivity(intent);
    }

    public void west(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "west");
        startActivity(intent);
    }

    public void south(View v) {
        Intent intent = new Intent(this, QuadMenu.class);
        intent.putExtra("quad", "south");
        startActivity(intent);
    }
}
