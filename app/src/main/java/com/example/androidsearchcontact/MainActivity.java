package com.example.androidsearchcontact;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements 
	OnQueryTextListener, LoaderCallbacks<Cursor>{
	
//	SimpleCursorAdapter simpleCursorAdapter;
private List<CallData> listofphonehistory = new ArrayList<CallData>();
	String cursorFilter;

    //    Context is an abstract class...which means what exactly?
    private Context context = null;

    //create a ListView object called listview
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;


//initialise the object called listview, it's a ListView object and the exact id it will appear in is called ListView_ContactsData
        listview = (ListView) findViewById(R.id.ListView_ContactsData);

        //populate ListView_CallData with data
//CustomAdapter is the class we are going to use. We will use it to create CustomAdapter
//objects which will appear in the MainActivity activity, using the listofphonehistory
//        PhoneContactsAdapter adapter = new PhoneContactsAdapter(MainActivity.this, listofphonehistory);
//        listview.setAdapter(adapter);


        String[] from = new String[]{
                Contacts.DISPLAY_NAME,
                Contacts._ID};
        
        int[] to = new int[]{
                android.R.id.text1,
        		android.R.id.text2};
//************************************************
//        String[] selectionArgs = { Contacts.LOOKUP_KEY };
//        Cursor cursortwo = (Cursor)l.getItemAtPosition(position);
//        String[] selectionArgs = { cursortwo.getString(cursortwo.getColumnIndex(Contacts.DISPLAY_NAME)) };
//        cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));
//        int counter = selectionArgs.length();

//        Cursor cursortest = getContentResolver().query (ContactsContract.Contacts.CONTENT_URI,
//                selectionArgs,
//                null,
//                null,
//                null);
//        Log.e("count contacts", "" + cursortest.getCount());
//        Log.e("count", "" + selectionArgs.length());

//        ***********************
        simpleCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null,
                from,
                to, 
                0);
        
        setListAdapter(simpleCursorAdapter);
        
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main, menu);
    	
    	MenuItem item = menu.add("Search");
        item.setIcon(android.R.drawable.ic_menu_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        SearchView searchView = new SearchView(MainActivity.this);
        searchView.setOnQueryTextListener(this);
        item.setActionView(searchView);
    	
        return true;
    }

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		Uri baseUri;
        if (cursorFilter != null) {
            baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI,
                    Uri.encode(cursorFilter));
        } else {
            baseUri = Contacts.CONTENT_URI;
        }

        String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
                + Contacts.DISPLAY_NAME + " != '' ))";
        
        String[] projection = new String[] {
            Contacts._ID,
            Contacts.DISPLAY_NAME,
            Contacts.CONTACT_STATUS,
            Contacts.CONTACT_PRESENCE,
            Contacts.PHOTO_ID,
            Contacts.LOOKUP_KEY,
        };
        
        CursorLoader cursorLoader = new CursorLoader(
        		MainActivity.this, 
        		baseUri,
                projection, 
                select, 
                null,
                Contacts.DISPLAY_NAME);


        Log.e("count contacts prj", "" + projection.length);

        return cursorLoader;


	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		simpleCursorAdapter.swapCursor(arg1);
        Log.e("count contacts arg1", "" + arg1.getCount());
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		simpleCursorAdapter.swapCursor(null);
	}

	@Override
	public boolean onQueryTextChange(String arg0) {
		cursorFilter = !TextUtils.isEmpty(arg0) ? arg0 : null;
        getLoaderManager().restartLoader(0, null, this);
        return true;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
		int item_ID = cursor.getInt(cursor.getColumnIndex(Contacts._ID));
		String item_DisplayName = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));
		String item_LookUp = cursor.getString(cursor.getColumnIndex(Contacts.LOOKUP_KEY));
		
		String item = String.valueOf(item_ID) + ": " + item_DisplayName + "\n" 
				+ "LOOKUP_KEY: " + item_LookUp;

		Toast.makeText(getApplicationContext(), 
				item, 
				Toast.LENGTH_LONG).show();
		
	}
    
}
