package comp231.contactsinfo;

import java.util.ArrayList;
import java.util.HashMap;

import comp231.contactsinfo.DBTools;
import comp231.contactsinfo.NewContact;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;


public class MainActivity extends ListActivity {

	Intent intent;
	TextView contactId;

	DBTools dbTools = new DBTools(this);

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		ArrayList<HashMap<String, String>> contactList =  dbTools.getAllContacts();

		if(contactList.size()!=0) {
						
			ListView listView = getListView();
			listView.setOnItemClickListener(new OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
		
					contactId = (TextView) view.findViewById(R.id.contactId);
					
					String contactIdValue = contactId.getText().toString();	
				  	
					Intent  theIndent = new Intent(getApplication(),EditContact.class);
					
					theIndent.putExtra("contactId", contactIdValue); 
					
					startActivity(theIndent); 
				}
			}); 
			
			
			ListAdapter adapter = new SimpleAdapter( MainActivity.this,contactList, R.layout.contact_entry, new String[] { "contactId","lastName", "firstName"}, new int[] {R.id.contactId, R.id.lastName, R.id.firstName});
			
	
			setListAdapter(adapter);
		}
	}
	
	
	public void showAddContact(View view) {
		Intent theIntent = new Intent(getApplicationContext(), NewContact.class);
		startActivity(theIntent);
	}
}
