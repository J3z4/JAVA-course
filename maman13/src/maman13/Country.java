package maman13;
/**
 *  This class Represents information about a Country 
 * @author Eliyahu Mashiah
 *@version 01.12.2018
 *
 */
public class Country {
	
	private String _countryName;//the name of the country
	private City [] _cities;//the cities array, which is the country
	private int _noOfCities;//number of cities in the country
	
	//declaring finals
	private final int MAX_NUM_CITIES=1000;//the maximum number of cities allowed in a country
	
	
	/**
	 * Constructor for objects of class Country. Construct a new country with the specified country name. the array size is 1000, and the noOfCities is set to 0.
	 * @param countryName The name of the country
	 */
	public Country(String countryName)
	{
		
		_countryName=countryName;//setting the country's name
		_cities= new City [MAX_NUM_CITIES];//setting the _cities array to be the max number of cities which is 1000
		_noOfCities=0;//initializing the number of cities.
		
	}
	
	/**
	 * 
	 * @param cityName The name of the city
	 * @param cityCenterX The X coordinate of the city's center
	 * @param cityCenterY The Y coordinate of the city's center
	 * @param centralStationX The X coordinate of the city's central station
	 * @param centralStationY The Y coordinate of the city's central station
	 * @param numOfResidents The number of residents in the city
	 * @param noOfNeighborhoods The number of neighborhoods in the city
	 * @return true if the city was added to the country, otherwise returns false.
	 */
	public boolean addCity(String cityName, double cityCenterX, double cityCenterY, double centralStationX, double centralStationY,long numOfResidents,int noOfNeighborhoods)
	{
		if(_noOfCities<MAX_NUM_CITIES)
		{
			City city=new City(cityName,cityCenterX,cityCenterY,centralStationX,centralStationY,numOfResidents,noOfNeighborhoods);
			_cities[_noOfCities]=city;
			_noOfCities++;
			return true;	
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * Returns the number of residents of the country.
	 * @return the number of residents of the country.
	 */
	public long getNumOfResidents()
	{
		long totalResidents=0;
		for (int i=0; i<_noOfCities; i++)
		{
			totalResidents+=_cities[i].getNumOfResidents();//sums up the total number of residents in the country
		}
		return totalResidents;
	}
	
	/**
	 * Calculates the longest distance between all the cities in the country.
	 * @return The longest distance between 2 city centers.if there is only 1 city it returns 0.
	 */
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
	
	/**
	 * returns all the cities that are north of the given city name. 
	 * @param cityName the city's name  
	 * @return returns a string with all the cities north of the given city
	 */
	public String citiesNorthOf(String cityName)
	{
		String s="";
		int indexOfCity=searchArray(cityName);//searches the city in the cities array. if the city is not the array returns -1, otherwise it returns the city's index in the array.
		if(indexOfCity==-1)//if the city's index equals to -1 it means that there is no such city in the array. 
		{
			return "There is no city with name "+cityName;
		}
		
		else
		{
		Point cityCenter=new Point(_cities[indexOfCity].getCityCenter());//creating a new point from the given city cityCenter
		
		for (int i=0; i<_noOfCities; i++)
		{
			if(_cities[i].getCityCenter().isAbove(cityCenter))//checks if the city in the current position is above(north) of the given city. if true it adds it to s.
			{
				s+=_cities[i].toString()+"\n\n";
			}
		}
		if(s.isEmpty())//checks if s is empty. if true it means that there are no cities that are north of the given city
		{
			return "There are no cities north of "+cityName;
		}
			return "The cities north of "+cityName+" are:\n\n"+s;//returns all the cities north of the given city. 
	
		}		
	}
	
	/**
	 * returns the southern most city in the country. 
	 * @return the city information of the southern most city.
	 */
	public City southernmostCity()
	{
		if (_noOfCities==0)
		{
			return null;
		}
		City southernmostCity=new City (_cities[0]);
		for (int i=1; i<_noOfCities;i++)
		{
			if (_cities[i].getCityCenter().isUnder(southernmostCity.getCityCenter()))
			{
			southernmostCity=_cities[i];
			}
		}
		return new City(southernmostCity);
	}
		
	/**
	 * it returns the Country's name
	 * @return country's name
	 */
	public String getCountryName()
	{
		return _countryName;
	}
	
	/**
	 * it returns the number of cities in the country
	 * @return the number of cities in the country
	 */
	public int getNumOfCities()
	{
		return _noOfCities;
	}
	
	/**
	 * returns an array of all the cities in the country 
	 * @return an array of the cities in the country
	 */
	public City [] getCities()
	{
		City [] city= new City[_noOfCities];
		for (int i=0; i<city.length; i++)
		{
			city[i]=new City(_cities[i]);
		}
		return city;
	}
	
	/**
	 * 
	 * @param city1
	 * @param city2
	 * @return
	 */
	public City unifyCities(String city1,String city2)
	{
		Point centralStation;
		int indexCity1=searchArray(city1);
		int indexCity2=searchArray(city2);
		double cityCenterX=( _cities[indexCity1].getCityCenter().getX()+_cities[indexCity2].getCityCenter().getX())/2;
		double cityCenterY=( _cities[indexCity1].getCityCenter().getY()+_cities[indexCity2].getCityCenter().getY())/2;
		long numOfResidents=_cities[indexCity1].getNumOfResidents()+_cities[indexCity2].getNumOfResidents();
		int numOfNeighborhoods=_cities[indexCity1].getNoOfNeighborhoods()+_cities[indexCity2].getNoOfNeighborhoods();//sums the total number of neighborhoods 
		
		if(_cities[indexCity1].getCentralStation().isLeft(_cities[indexCity2].getCentralStation()))//Checking which central station is to the west
		{
			centralStation=new Point(_cities[indexCity1].getCentralStation());
		}
		
		else
		{
			centralStation=new Point(_cities[indexCity2].getCentralStation());
		}
		
		City unifiedCity=new City((city1+"-"+city2),cityCenterX,cityCenterY,centralStation.getX(),centralStation.getY(),numOfResidents,numOfNeighborhoods);
		
		if( _cities[indexCity2].getNumOfResidents()<_cities[indexCity1].getNumOfResidents() || _cities[indexCity1].getNumOfResidents()==_cities[indexCity2].getNumOfResidents())
	    /*checks if there are less residents in the second city than the first city, or if there is an equal number of residents in both cities.
	   *if one of the statements is true it deletes the second city and inserts the unified city in the index of the first city.
	    */		
		{
			_cities[indexCity2]=new City(_cities[_noOfCities-1]);
			_cities[_noOfCities-1]=null;
			_cities[indexCity1]=unifiedCity;
		}
		
		else// deletes the first city and inserts the unified city in the index of the second city
		{
			_cities[indexCity1]=new City(_cities[_noOfCities-1]);
			_cities[_noOfCities-1]=null;
			_cities[indexCity2]=unifiedCity;
		}
		_noOfCities--;
		return new City(unifiedCity);
	}
	
	
/**
 * 
 */
	public String toString()
	{
		String s= "Cities of "+getCountryName()+":\n\n";
		for (int i=0; i<_noOfCities;i++)
		{
				s+=_cities[i].toString()+"\n\n";
		}
			return s;
	}

	private int searchArray(String cityName)
	{
		for (int i=0;i<_noOfCities;i++)
		{
			if(cityName.equals(_cities[i].getCityName()))
			{
				return i;
			}
		}
		return -1;
	}
}//end class
