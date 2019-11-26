package com.example.beerlist;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class BeerListFragment extends ListFragment implements BeerConstants {
	ArrayList<Beer> beers;
	public static final String LOG_KEY = "Portia";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		beers = BeerSet.newInstance().getBeers();
		
		Log.i(LOG_KEY, "In on create for beerlistfragment");
		
		ArrayAdapter<Beer> adapter = new BeerAdapter(beers);
		
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		
		ListView listView = (ListView)view.findViewById(android.R.id.list);
		registerForContextMenu(listView);
		
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.to_do_list, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.menu_item_new_beer:
				Intent intent = new Intent(getActivity(), BeerActivity.class);
				intent.putExtra(EXTRA_TODO_ID, -1);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);
		getActivity().getMenuInflater().inflate(R.menu.to_do_list_context, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		int position = info.position;
		BeerAdapter adapter = (BeerAdapter)getListAdapter();
		Beer beer  = adapter.getItem(position);
		
		switch(item.getItemId())
		{
			case R.id.menu_item_delete_beer:
				BeerSet.deleteBeer(beer);
				adapter.notifyDataSetChanged();
				return true;
		}
		
		return super.onContextItemSelected(item);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		
		Intent intent = new Intent(getActivity(), BeerActivity.class);
		intent.putExtra(EXTRA_TODO_ID, position);
		startActivity(intent);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((BeerAdapter) getListAdapter()).notifyDataSetChanged();
	}
	
	private class BeerAdapter extends ArrayAdapter<Beer>
	{
		public BeerAdapter(ArrayList<Beer> beers){
			super(getActivity(), 0, beers);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null)
			{
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_beer, null);
			}
			
			Beer beer = getItem(position);
			
			TextView titleView = (TextView)convertView.findViewById(R.id.textview_list_title);
			titleView.setText(beer.getTitle());
			
			TextView descView = (TextView)convertView.findViewById(R.id.textview_list_desc);
			descView.setText(beer.getDescription());
			
			return convertView;
		}		
		
	}
}








