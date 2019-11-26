package com.example.beerlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class BeerFragment extends Fragment implements BeerConstants
{
	private Beer beer;
	private int id;
	private View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		id = getActivity().getIntent().getIntExtra(EXTRA_TODO_ID, 0);
		
		if(id == -1)
		{
			beer = new Beer("", "");
		}
		else
		{
			beer = BeerSet.newInstance().getBeers().get(id);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_to_do, container, false);
		initTitle();
		initDesc();
		
		return view;
	}
	
	public void initTitle()
	{
		EditText editText = (EditText)view.findViewById(R.id.edittext_beer_title);
		editText.setText(beer.getTitle());
	}

	public void initDesc()
	{
		EditText editText = (EditText)view.findViewById(R.id.edittext_beer_desc);
		editText.setText(beer.getDescription());
	}

	@Override
	public void onPause() {
		super.onPause();
		
		EditText editText = (EditText)view.findViewById(R.id.edittext_beer_title);
		beer.setTitle(editText.getText().toString());
		
		editText = (EditText)view.findViewById(R.id.edittext_beer_desc);
		beer.setDesc(editText.getText().toString());
		
		if(id == -1)BeerSet.addBeer(beer);
	}
}
