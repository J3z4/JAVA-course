package maman13;
/**
 * 
 * @author Eliyah Mashiah
 *@version 26.11.2018 
 */
public class Matrix {

	private int [] [] _matrix;
	
	/**
	 * Constructs a Matrix for a two-dimensional array; the dimensions dimensions as well as the values of this Matrix will be the same as the dimensions and values of the two-dimensional array. 
	 * @param array two-dimensional array with a random size and values which will be used to construct the matrix
	 */
	public Matrix(int [] [] array)
	{
		_matrix=new int [getRows(array)][getColumns(array)];//duplicate the given array size to _matrix
		
		for (int i=0; i<getRows(_matrix); i++ )//row counter
		{
	        for (int j = 0; j < getColumns(_matrix); j++)//column counter
	        {
	        	_matrix[i][j]=array[i][j];//fills _matrix array with the "array" array values
	        }
		}
	}
	
	/**
	 * Constructs a size1 by size2 Matrix of zeroes. 
	 * @param size1 The number of rows in the array
	 * @param size2 The number of columns in the array
	 */
	public Matrix(int size1, int size2)
	{
    _matrix= new int[size1][size2];//constructs a two dimensional array using the given sizes; 
    
    for (int i = 0; i < size1; i++)//row counter
    {
        for (int j = 0; j < size2; j++)//column counter
        {
            _matrix[i][j] = 0;//fills _matrix array with 0
        }
    }
    
	}
	/**
	 * Returns a string representation of Matrix.
	 * @return A String representation of the Matrix
	 */
	public String toString()
	{
		String s="";
		for (int i=0; i<getRows(_matrix); i++)//row counter
		{
			for (int j=0; j<getColumns(_matrix); j++)//column counter
			{
				s+=_matrix[i][j];
				if(j<getColumns(_matrix)-1)//checks if it's the last column of the row if true it doesn't print a tab 
				{
					s+="\t";
				}
			}
			s+="\n";//prints a new line in the end of a row. 
		}
		return s;//returns the string representation of the matrix
	}
	
	public Matrix flipHorizontally()
	{
		int [] [] matrix= copyMatrix();
		int temp;
        for (int i = 0; i < getColumns(matrix)/2; i++)
        {
            for (int j = 0; j < getRows(matrix); j++)
            {
            	temp = matrix[j][i];
            	matrix[j][i] = matrix[j][getColumns(matrix) - i - 1];
            	matrix[j][getColumns(matrix) - i - 1] = temp;
            }
        }
 
        return new Matrix(matrix);

	}
	public Matrix flipVertically()
	{
		int [] [] matrix= copyMatrix();
		int temp;
        for (int i = 0; i < getRows(matrix)/2; i++)
        {
            for (int j = 0; j < getColumns(matrix); j++)
            {
            	temp = matrix[i][j];
            	matrix[i][j] = matrix[getRows(matrix) - i - 1][j];
            	matrix[getRows(matrix) - i - 1][j] = temp;
            }
        }
 
        return new Matrix(matrix);

	}
	
	
	public Matrix rotateClockwise()
	{
		
		int [] [] originalMatrix= copyMatrix();
		int [] [] clockwiseMatrix= new int [getColumns(originalMatrix)][getRows(originalMatrix)];
		for (int i = 0; i < getColumns(clockwiseMatrix); i++)
		{
			for (int j = 0; j <  getRows(clockwiseMatrix); j++)
			{
					clockwiseMatrix[j][getColumns(clockwiseMatrix)-1-i]=originalMatrix[i][j];
			}
			
		}
		
		return new Matrix(clockwiseMatrix);
	}
	
	public Matrix rotateCounterClockwise()
	{
		
		int [] [] originalMatrix= copyMatrix();
		int [] [] counterClockwiseMatrix= new int [getColumns(originalMatrix)][getRows(originalMatrix)];
		for (int i = 0; i < getColumns(counterClockwiseMatrix); i++)
		{
			for (int j = 0; j <  getRows(counterClockwiseMatrix); j++)
			{
					counterClockwiseMatrix[getRows(counterClockwiseMatrix)-1-j][i]=originalMatrix[i][j];
			}
			
		}
		
		return new Matrix(counterClockwiseMatrix);
	}
	
	private int getRows(int [] [] array)
	{
		int rows=array.length;
		return rows;
	}
	
	private int getColumns(int [] [] array)
	{
		int columns=array[0].length;
		return columns;
	}
	
	
	private int [] [] copyMatrix()
	{
	    int [][] copyMatrix= new int [getRows(_matrix)] [getColumns(_matrix)];
	    for(int i = 0; i< getRows(_matrix); i++){
	        for (int j = 0; j < getColumns(_matrix); j++){
	           	copyMatrix[i][j] = _matrix[i][j];
	        }
	    }
	    return copyMatrix;
	}
}//end class
