package com.example.beerlist;

import java.util.ArrayList;

public class BeerSet 
{
	private static BeerSet beerSet;
	private static ArrayList<Beer> beers = new ArrayList<Beer>();
	
	private BeerSet()
	{
		beers.add(new Beer("Laundry", "Clean my clothes"));
		beers.add(new Beer("Do Dishes", "Clean my dirty dishes"));
		beers.add(new Beer("Homework", "Code code code"));
		beers.add(new Beer("Shopping", "Because I like pretty things"));
	}
	
	public static BeerSet newInstance()
	{
		if(beerSet == null)
		{
			beerSet = new BeerSet();
		}
		
		return beerSet;
	}
	
	public ArrayList<Beer> getBeers()
	{
		return beers;
	}
	
	public static void addBeer(Beer beer)
	{
		beers.add(beer);
	}
	
	public static void deleteBeer(Beer beer)
	{
		beers.remove(beer);
	}
}
