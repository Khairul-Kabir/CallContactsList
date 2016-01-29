package com.example.callcontactslist;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private final int PICK_CONTACT = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    
    public void callContacts(View v) {
    	
    	Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    	startActivityForResult(intent, PICK_CONTACT);
    }
    
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(reqCode, resultCode, data);
    	
    	if(reqCode == PICK_CONTACT) {
    		if(resultCode == ActionBarActivity.RESULT_OK) {
    			Uri contactData = data.getData();
    			Cursor c = getContentResolver().query(contactData, null, null, null, null);
    			
    			if(c.moveToFirst()) {
    				String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
    				Toast.makeText(this, "You've picked:" + name, Toast.LENGTH_LONG).show();
    			}
    		}
    	}
    }
}