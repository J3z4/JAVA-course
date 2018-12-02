package maman13;
/**
 * 
 * @author Eliyahu Mashiah
 *@version 01.12.2018
 *
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
	
	/**
	 * 
	 * @param cityName
	 * @param cityCenterX
	 * @param cityCenterY
	 * @param centralStationX
	 * @param centralStationY
	 * @param numOfResidents
	 * @param noOfNeighborhoods
	 * @return
	 */
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
	
	/**
	 * 
	 * @return
	 */
	public long getNumOfResidents()
	{
		long totalResidents=0;
		for (int i=0; i<_noOfCities; i++)
		{
			totalResidents+=_cities[i].getNumOfResidents();
		}
		return totalResidents;
	}
	
	/**
	 * 
	 * @return
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
	 * 
	 * @param cityName the city's name  
	 * @return returns a string with all the cities north of the given city
	 */
	public String citiesNorthOf(String cityName)
	{
		String s="";
		int indexOfCity=searchArray(_cities, cityName);//searches the city in the cities array. if the city is not the array returns -1, otherwise it returns the city's index in the array.
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
				s+=_cities[i].toString()+"\n";
			}
		}
		if(s.isEmpty())//checks if s is empty. if true it means that there are no cities that are north of the given city
		{
			return "There are no cities north of "+cityName;
		}
			return "the cities north of "+cityName+" are:\n\n"+s;//returns all the cities north of the given city. 
	
		}		
	}
	
	/**
	 * 
	 * @return
	 */
	public City southernmostCity()
	{
		if (_noOfCities==0)
		{
			return null;
		}
		else
		{
			Point southernmostCity=new Point (_cities[0].getCityCenter());
			int indexOfCity=0;
			for (int i=1; i<_noOfCities;i++)
			{
				if (_cities[i].getCityCenter().isUnder(southernmostCity))
				{
				southernmostCity=new Point (_cities[i].getCityCenter());
				indexOfCity=i;
				}
			}
			return new City(_cities[indexOfCity]);
		}
	}
		
	/**
	 * 
	 * @return
	 */
	public String getCountryName()
	{
		return _countryName;
	}
	
	public int getNumOfCities()
	{
		return _noOfCities;
	}
	
	public City [] getCities()
	{
		City [] city= new City[_noOfCities];
		for (int i=0; i<city.length; i++)
		{
			city[i]=_cities[i];
		}
		return city;
	}
	
	public City unifyCities(String city1,String city2)
	{
		int i=0;
		Point centralStation;
		int indexCity1=searchArray(_cities,city1);
		int indexCity2=searchArray(_cities,city2);
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
		
		if( _cities[indexCity1].getNumOfResidents()>_cities[indexCity2].getNumOfResidents() || _cities[indexCity1].getNumOfResidents()==_cities[indexCity2].getNumOfResidents())
		{
			_cities[indexCity2]=null;
			i=indexCity2;
			_cities[indexCity1]=unifiedCity;
		}
		
		else
		{
			_cities[indexCity1]=null;
			i=indexCity1;
			_cities[indexCity2]=unifiedCity;
		}
		_noOfCities--;
		for (int j=i; j<_noOfCities;j++)
		{
			_cities[j]=_cities[j+1];
		}
		return unifiedCity;
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

	private int searchArray(City [] cities, String cityName)
	{
		for (int i=0;i<_noOfCities;i++)
		{
			if(cityName==cities[i].getCityName())
			{
				return i;
			}
		}
		return -1;
	}
}//end class
