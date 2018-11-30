
/**
 * Student Tester for Maman 13
 */
public class StudentTester
{
    /**
     */
    public static void main (String[] args)
    {
        //-----------------------Country---------------------
        
        System.out.println("***** Tester for Maman 13 *****");
        System.out.println("Testing Country class:\n");
        Country country1 = new Country ("myCountry");
        
        final int CITIES = 100;
        
        country1.addCity("JLM", 20, 30, 25, 35, 850, 3);
        country1.addCity("TLV", 60, 70, 65, 75, 640, 6);
        country1.addCity("BSB", 10, 20, 15, 25, 920, 8);
        

        System.out.println(country1);
        System.out.println();  
        
        String countryName= country1.getCountryName();
        System.out.println(countryName);
        System.out.println();  
        
        int howManyCities= country1.getNumOfCities();
        System.out.println(howManyCities);
        System.out.println();  
        
        String northOf = country1.citiesNorthOf("BSB");
        System.out.println(northOf);
        System.out.println();                 
        
        long residents=country1.getNumOfResidents();
        System.out.println(residents);
        System.out.println();  

        City southernCity = country1.southernmostCity();
        System.out.println(southernCity);
        System.out.println();   
        
        double distance=country1.longestDistance();
        System.out.println(distance);
        System.out.println();  
        
        City[] citiesCopy = country1.getCities(); 
        for(int y=0; y<=2; y++)    
            System.out.println(citiesCopy[y]);
        System.out.println();    
        
        City uniCity = country1.unifyCities("JLM", "TLV");
        System.out.println(country1);
        
        //---------------Matrix-----------------------
        
        System.out.println("\nTesting Matrix class:\n");
        int [][] intArray = new int[4][8];
        intArray[1][7] = 2;
        intArray[0][6] = 5;
        
        Matrix matrix1= new Matrix(intArray);
        Matrix matrix2= new Matrix(7,14);
        System.out.println(matrix1);
        
        matrix1= new Matrix(intArray);
        Matrix horizontalMatrix= matrix1.flipHorizontal();
        System.out.println(horizontalMatrix);
        
        matrix1= new Matrix(intArray);
        Matrix verticalMatrix= matrix1.flipVertical();
        System.out.println(verticalMatrix);
    
        matrix1= new Matrix(intArray);
        Matrix clockwiseMatrix= matrix1.rotateClockwise();
        System.out.println(clockwiseMatrix);
        
        matrix1= new Matrix(intArray);
        Matrix counterClockwiseMatrix= matrix1.rotateCounterClockwise();
        System.out.println(counterClockwiseMatrix);
        
    }
}
