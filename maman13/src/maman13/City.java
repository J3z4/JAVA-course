package maman13;
/**
 * This class Represents information about a city
 *
 * @author Eliyahu Mashiah
 * @version 24.11.2018
 */
public class City {
	
	
	private String _cityName;//the city's name
	private Point _cityCenter;//the city's center coordinates
	private Point _centralStation;//the city's central station coordinates
	private long _numOfResidents;//the number of residents in the city
	private int _noOfNeighborhoods;//the number of neighborhoods in the city
	
	//Declaring finals
	private final long	MIN_RESIDENTS=0;// the minimum number of residents in a city
	private final int MIN_NEIGHBORHOODS=1;// the minimum number of neighborhoods in a city
	
	/**
	 * Constructor for objects of class City. Construct a new city with the specified info. If numOfResidents is smaller than MIN_RESIDENTS, MIN_RESIDENTS is used. If noOfNeighborhoods is smaller than MIN_NEIGHBORHOODS, MIN_NEIGHBORHOODS is used.
	 * @param cityName The name of the city
	 * @param cityCenterX The X coordinate of the city's center
	 * @param cityCenterY The Y coordinate of the city's center
	 * @param centralStationX The X coordinate of the city's central station
	 * @param centralStationY The Y coordinate of the city's central station
	 * @param numOfResidents  The number of residents in the city
	 * @param noOfNeighborhoods The number of neighborhoods in the city
	 */
	public City(String cityName, double cityCenterX, double cityCenterY, double centralStationX, double centralStationY,long numOfResidents,int noOfNeighborhoods)
	{
		_cityName=cityName;//sets the city name to be the given city name
		_cityCenter=new Point (cityCenterX,cityCenterY);//creating the city center point using the given city center X and Y coordinates
		_centralStation=new Point (centralStationX,centralStationY);//creating the city's central station point using the given city's central station X and Y coordinates
		
		if (numOfResidents<MIN_RESIDENTS)//checking if given number of residents is smaller than the minimum number of residents
			numOfResidents=MIN_RESIDENTS;//sets the number of residents to be the minimum number of residents
		
		if(noOfNeighborhoods<MIN_NEIGHBORHOODS)//checking if given number of neighborhoods is smaller than the minimum number of neighborhoods
			noOfNeighborhoods=MIN_NEIGHBORHOODS;//sets the number of neighborhoods to be the minimum number of neighborhoods
		
		_numOfResidents=numOfResidents;//sets the number of residents to be the given number of residents
		_noOfNeighborhoods=noOfNeighborhoods;//sets the number of neighborhoods to be the given number of neighborhoods
	}
	
	/**
	 * Constructor for objects of class City. Copy constructor, construct a city using another city.
	 * @param other The city from which to construct the new object
	 */
	public City(City other)
	{
		_cityName=other._cityName;
		_cityCenter=new Point(other._cityCenter);
		_centralStation=new Point(other._centralStation);
		_numOfResidents=other._numOfResidents;
		_noOfNeighborhoods=other._noOfNeighborhoods;
	}
	
	/**
	 * Returns the name of the city.
	 * @return The name of the city
	 */
	public String getCityName()
	{
		return _cityName;
	}
	
	/**
	 * Returns the center of the city as a Point object.
	 * @return the center of the city as a Point object.
	 */
	public Point getCityCenter()
	{
		return new Point(_cityCenter);
	}
	
	/**
	 * Returns the central station of the city as a Point object.
	 * @return the central station of the city as a Point object.
	 */
	public Point getCentralStation()
	{
		return new Point(_centralStation);
	}
	
	/**
	 * Returns the number of residents of the city.
	 * @return the number of residents of the city.
	 */
	public long getNumOfResidents()
	{
		return _numOfResidents;
	}
	
	/**
	 * Returns the number of neighborhoods of the city.
	 * @return the number of neighborhoods of the city.
	 */
	public int getNoOfNeighborhoods()
	{
		return _noOfNeighborhoods;
	}
	
	/**
	 * Sets the name of the city.
	 * @param cityName The new name of the city
	 */
	public void setCityName(String cityName)
	{
		_cityName=cityName;//sets the new name of the city
	}
	
	/**
	 * Sets the center point of the city.
	 * @param cityCenter the new center point of the city
	 */
	public void setCityCenter(Point cityCenter)
	{
		_cityCenter=new Point(cityCenter);//sets the new coordinates of the city center
	}
	
	/**
	 * Sets the central station point of the city.
	 * @param centralStation The new central station point of the city
	 */
	public void setCentralStation(Point centralStation)
	{
		_centralStation=new Point(centralStation);//sets the new coordinates of the central station
	}
	
	/**
	 * Sets the number of residents of the city, but only if it is bigger than MIN_RESIDENTS
	 * @param numOfResidents The new number of residents in the city
	 */
	public void setNumOfResidents(long numOfResidents)
	{
		if(numOfResidents>=MIN_RESIDENTS)//checking if the number of residents is bigger than the minimum number of residents
			_numOfResidents=numOfResidents;//updating the current number of residents
	}
	
	/**
	 * Sets the number of neighborhoods of the city, but only if it is bigger than MIN_NEIGHBORHOODS
	 * @param noOfNeighborhoods The new number of neighborhoods in the city
	 */
	public void setNoOfNeighborhoods(int noOfNeighborhoods)
	{
		if(noOfNeighborhoods>=MIN_NEIGHBORHOODS)//checking if the number of neighborhoods is bigger than the minimum number of neighborhoods
			_noOfNeighborhoods=noOfNeighborhoods;//updating the current number of neighborhoods
	}
	
	/**
	 * Returns a string representation of this City in the format
	 * City Name: Tel Aviv
	 * City Center: (5.0,8.0)
	 * central Station: (3.0,4.0)
	 * NumOfResidents: 1004
	 * No Of Neighborhoods: 5
	 * @return A String representation of this city
	 */
	public String toString()
	{
		return ("City Name: "+_cityName+"\n"+"City Center: "+_cityCenter+"\n"+"Central Station: "+_centralStation+"\n"+"Number of Residents: "+_numOfResidents+"\n"+"Number of Neighborhoods: "+_noOfNeighborhoods);
	}
	
	/**
	 * Adds the given number of residents - either positive or negative number - to the city. If the resulted number of residents is smaller than MIN_RESIDENTS, MIN_RESIDENTS is set to be the new number of residents of the city, and false is returned. Otherwise (i.e. the resulted number is bigger or equal to MIN_RESIDENTS), true is returned.
	 * @param residentsUpdate The new number of residents
	 * @return false if resulted number of residents is smaller than MIN_RESIDENTS, true otherwise
	 */
	public boolean addResidents(long residentsUpdate)
	{
		if (_numOfResidents+residentsUpdate<MIN_RESIDENTS)//checking if the current number of residents plus the residents update is smaller than the minimum number of residents
		{
			_numOfResidents=MIN_RESIDENTS;//setting the number of residents to be the minimum number of residents
			return false;
		}
		else
		{
			_numOfResidents+=residentsUpdate;//adding the new number of residents to the current number of residents
			return true;
		}
			
	}
	
	/**
	 * Move the location of the central station of the city with the given deltas.
	 * @param deltaX How much the x coordinate of the central station of the city is to be moved
	 * @param deltaY How much the y coordinate of the central station of the city is to be moved
	 */
	public void moveCentralStation(double deltaX, double deltaY)
	{
			_centralStation.move(deltaX, deltaY);//moves the central station by Delta X and Delta Y
	}
	
	/**
	 * Calculates the distance between the center of this city and its central station.
	 * @return The distance between the center of this city and its central station.
	 */
	public double distanceBetweenCenterAndStation()
	{
		return _cityCenter.distance(_centralStation);//calculating and returning the distance between the city center and its central station
	}
	
	/**
	 * Creates a new city with a new name, and where its center and central station are moved by the given deltas from this city. Its number of residents is set to MIN_RESIDENTS and its number of neighborhoods is set to MIN_NEIGHBORHOODS.
	 * @param newCityName The name of the new city
	 * @param dX How much the x coordinates of the city's center and central station are to be moved for the new city
	 * @param dY How much the y coordinates of the city's center and central station are to be moved for the new city 
	 * @return A new city with a new name, and where its center and central station are equal to the moved X and Y by the given deltas from this city. Its number of residents is set to MIN_RESIDENTS and its number of neightborhoods is set to MIN_NEIGBORHOODS.
	 */
	public City newCity( String newCityName, double dX, double dY)
	{
		Point newCityCenter=new Point(_cityCenter);//creating a new city center using the current city center coordinates
		Point newCentralStation=new Point(_centralStation);//creating a new central station using the current central station coordinates
		newCityCenter.move(dX, dY);//moving the current coordinates of the new city center to the new coordinates
		newCentralStation.move(dX, dY);//moving the current coordinates of the new central station to the new coordinates
		return new City(newCityName,newCityCenter.getX(),newCityCenter.getY(),newCentralStation.getX(),newCentralStation.getY(),MIN_RESIDENTS,MIN_NEIGHBORHOODS);// returning the new city
	}
}//end class
