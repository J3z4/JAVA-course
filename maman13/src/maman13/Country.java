package maman13;
/**
 * 
 * @author Eliyah Mashiah
 *@version 26.11.2018
 *hello team
 */
public class Country {
	
	private String _countryName;
	private City [] _cities;
	private int _noOfCities;
	
	//declaring finals
	private final int MAX_NUM_CITIES=1000;
	
	public Country(String countryName)
	{
		
		_countryName=countryName;
		_cities= new City [MAX_NUM_CITIES];
		_noOfCities=0;
		
	}
	public boolean addCity(String cityName, double cityCenterX, double cityCenterY, double centralStationX, double centralStationY,long numOfResidents,int noOfNeighborhoods)
	{
		if(_noOfCities<MAX_NUM_CITIES)
		{
			City city=new City(cityName,cityCenterX,cityCenterY,centralStationX,centralStationY,numOfResidents,noOfNeighborhoods);
			_cities[_noOfCities]=city;
			_noOfCities+=1;
			return true;	
		}
		else 
		{
			return false;
		}
	}
	
	
	public String getCountryName()
	{
		return _countryName;
	}
	
	
	public String toString()
	{
		String s= "Cities of "+getCountryName()+":\n\n";
		for (int i=0; i<_noOfCities;i++)
		{
				s+=_cities[i].toString()+"\n\n";
		}
			return s;
	}
	
	public long getNumOfResidents()
	{
		long totalResidents=0;
		for (int i=0; i<_noOfCities; i++)
		{
			totalResidents+=_cities[i].getNumOfResidents();
		}
		return totalResidents;
	}
	public double longestDistance()
	{
		double maxDistance=0;
		if (_noOfCities<2)
		{
			return maxDistance;
		}
		else
		{
			for(int i=0; i<_noOfCities;i++)
			{
				for (int j=i+1; j<_noOfCities; j++)
				{
					if(maxDistance<(_cities[i].getCityCenter()).distance(_cities[j].getCityCenter()))
						maxDistance=(_cities[i].getCityCenter()).distance(_cities[j].getCityCenter());
				}
			}
			return maxDistance;
		}
	}
	public String citiesNorthOf(String cityName)
	{
		int indexOfCity=0;
		String s="";
		while(cityName!=_cities[indexOfCity].getCityName()&& indexOfCity<_noOfCities)
		{
			indexOfCity++;
		}
		
		if(indexOfCity==_noOfCities-1 && _cities[indexOfCity].getCityName()!=cityName)
		{
			return "There is no city with name "+cityName;
		}
		else
		{
		Point cityCenter=_cities[indexOfCity].getCityCenter();
		for (int i=0; i<_noOfCities;i++)
		{
			if(_cities[i].getCityCenter().isAbove(cityCenter))
			{
				s+=_cities[i].toString()+"\n\n";
			}
		}
		return s;
	
		}
	}
}//end class
