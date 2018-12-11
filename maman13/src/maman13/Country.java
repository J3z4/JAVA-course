package maman13;
/**
 *  This class Represents information about a Country 
 * @author Eliyahu Mashiah
 *@version 11.12.2018
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
	 * adds a city to the cities array if there are less than 1000 cities and returns true, otherwise returns false
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
		if(_noOfCities<MAX_NUM_CITIES)//checks if the number of cities that are currently in the array is smaller than the max number of cities
		{
			City city=new City(cityName,cityCenterX,cityCenterY,centralStationX,centralStationY,numOfResidents,noOfNeighborhoods);
			_cities[_noOfCities]=city;
			_noOfCities++;//adds 1 to the number of cities that are currently in the array 
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
			for(int i=0; i<_noOfCities;i++)//
			{
				for (int j=i+1; j<_noOfCities; j++)
				{
					if(maxDistance<(_cities[i].getCityCenter()).distance(_cities[j].getCityCenter()))//checks if the distance from 2 city centers, the city center  in index [i] to the city in index [j] is bigger than the current max distance
						maxDistance=(_cities[i].getCityCenter()).distance(_cities[j].getCityCenter());//sets the value of the new distance. 
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
		if (_noOfCities==0)//checks if there are no cities in the country if true returns null 
		{
			return null;
		}
		City southernmostCity=new City (_cities[0]);
		for (int i=1; i<_noOfCities;i++)
		{
			if (_cities[i].getCityCenter().isUnder(southernmostCity.getCityCenter()))//checks if the city in the i index of the array is southern to the current southern most city. the southern most city becomes the city in the i index
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
	 * Creates an array the size of the country's cities
	 * @return an array of the cities in the country
	 */
	public City [] getCities()
	{
		City [] city= new City[_noOfCities];
		for (int i=0; i<city.length; i++)
		{
			city[i]=new City(_cities[i]);//copies all the cities from the original array to the new array
		}
		return city;
	}
	
	/**
	 * unifies 2 cities into one city. the name of the unified city is city1-city2, the city center is the middle point of both city centers, the central station is the western station of the two, and the number of residents and neighborhoods is the sum of both cities. 
	 * @param city1 the name of the first city.
	 * @param city2 the name of the second city.
	 * @return
	 */
	public City unifyCities(String city1,String city2)
	{
		Point centralStation;
		int indexCity1=searchArray(city1);//searches the index of the first city in the _cities array
		int indexCity2=searchArray(city2);//searches the index of the second city in the _cities array
		double cityCenterX=( _cities[indexCity1].getCityCenter().getX()+_cities[indexCity2].getCityCenter().getX())/2;//sets the X coordinate of the new city center to be in the middle of the existing city centers 
		double cityCenterY=( _cities[indexCity1].getCityCenter().getY()+_cities[indexCity2].getCityCenter().getY())/2;//sets the Y coordinate of the new city center to be in the middle of the existing city centers
		long numOfResidents=_cities[indexCity1].getNumOfResidents()+_cities[indexCity2].getNumOfResidents();//sets the number of residents to be the total number of residents of both cities
		int numOfNeighborhoods=_cities[indexCity1].getNoOfNeighborhoods()+_cities[indexCity2].getNoOfNeighborhoods();//sums the total number of neighborhoods 
		
		if(_cities[indexCity1].getCentralStation().isLeft(_cities[indexCity2].getCentralStation()))//Checking which central station is to the west
		{
			centralStation=new Point(_cities[indexCity1].getCentralStation());//sets the central station of the unified city to be the the central station of the first city. 
		}
		
		else
		{
			centralStation=new Point(_cities[indexCity2].getCentralStation());//sets the central station of the unified city to be the the central station of the second city. 
		}
		
		City unifiedCity=new City((city1+"-"+city2),cityCenterX,cityCenterY,centralStation.getX(),centralStation.getY(),numOfResidents,numOfNeighborhoods);//creating the unified city
		
		if( _cities[indexCity2].getNumOfResidents()<_cities[indexCity1].getNumOfResidents() || _cities[indexCity1].getNumOfResidents()==_cities[indexCity2].getNumOfResidents())
	    /*checks if there are less residents in the second city than the first city, or if there is an equal number of residents in both cities.
	   *if one of the statements is true it deletes the second city and inserts the unified city in the index of the first city.
	    */		
		{
			_cities[indexCity2]=new City(_cities[_noOfCities-1]);
			_cities[_noOfCities-1]=null;
			_cities[indexCity1]=unifiedCity;
		}
		
		else// inserts the last city in the index of the first city, deletes the last city and inserts the unified city in the index of the second city
		{
			_cities[indexCity1]=new City(_cities[_noOfCities-1]);//inserts the last the city in the index of the first city
			_cities[_noOfCities-1]=null;//sets the last city in the array to null 
			_cities[indexCity2]=unifiedCity;//inserts the unified city in the second city place
		}
		_noOfCities--;
		return new City(unifiedCity);
	}
	
	
/**
 * Returns a string representation of Country in the format
 *Cities of Israel:
 *
 *City Name: TelAviv
 *City Center: (10.0,10.0)
 *Central Station: (8.0,8.0)
 *Number of Residents: 10000
 *Number of Neighborhoods: 5
 *
 *City Name: Jerusalem
 *City Center: (3.0,20.0)
 *Central Station: (4.0,18.0)
 *Number of Residents: 20000
 *Number of Neighborhoods: 8
 * @return A String representation of this country
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
	
	private int searchArray(String cityName)//a method which searches the _cities array for a specific city and returns the index of given city.if the city wasn't found it returns -1 
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
