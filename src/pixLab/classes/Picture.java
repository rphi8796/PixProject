package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
import javax.swing.JFileChooser;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void zeroRed()
  {
	  Pixel [][] pixels = this.getPixels2D();
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setRed(0);
		  }
	  }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel northPixel = null;
    Pixel southPixel = null;
    int height = pixels.length;
    int width = pixels[0].length;
    for (int col = 0; col < width; col++)
    {
      for (int row = 0; row < height / 2; row++)
      {
        northPixel = pixels[row][col];
        southPixel = pixels[height - 1 - row][col];
        southPixel.setColor(northPixel.getColor());
      }
    } 
  }
  
  public void mirrorSeagull()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topleftPixel = pixels[225][225];
	  int end = 350;
	  int bottom = 330;
	  Pixel primePixel = null;
	  Pixel newPixel = null;
	  Pixel newPixel2 = null;
	  int width = pixels[0].length;
	  int height = pixels.length;
	  for (int row = 225; row < bottom; row += 1)
	  {
		  for (int col = 225; col < end; col += 1)
		  {
					primePixel = pixels[row][col];
					newPixel = pixels[row - 225][col - 225];
					newPixel.setColor(primePixel.getColor());
		  }
	  }
	  System.out.println(width);  
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  public void copy1(Picture fromPic, int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length &&toRow < toPixels.length; fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  public void cyan()
  {
	  Pixel [][] pixels = this.getPixels2D();
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setRed(0);  
		  }
	  }
  }
  public void magenta()
  {
	  Pixel [][] pixels = this.getPixels2D();
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setGreen(0);
		  }
	  }
  }
  public void yellow()
  {
	  Pixel [][] pixels = this.getPixels2D();
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setBlue(0);
		  }
	  }
  }
  
  public void verticalShift(int factor)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture noBlue = new Picture("arch.jpg");
	  int width = pixels[0].length;
	  int height =  pixels.length;
	  Pixel first = null;
	  Pixel second = null;
	  int axis = (int) (((Math.random() * (width - 80)) + 80) / factor);

	  Pixel[][] colored = noBlue.getPixels2D();

	  for (int row = 0; row < height; row += 1)
	  {	
		  for (int col = axis; col < width; col += 1)
		  {	
			  first = pixels[row][col];
			  second = colored[row][col - axis];
			  second.setColor(first.getColor());
		  }
	  }
	  for (int row = 0; row < height; row += 1)
	  {			
		  for (int col = 0; col < axis - 1; col += 1)
		  {
			  first = pixels[row][col];
			  second = colored[row][col + width - axis - 1];
			  second.setColor(first.getColor());
		  }
	  }
	  for (int row = 0; row < height; row += 1)
	  {			
		  for (int col = 0; col < width; col += 1)
		  {
			  first = pixels[row][col];
			  second = colored[row][col];
			  first.setColor(second.getColor());
		  }
	  }
  }
  
  /**
   * copies image 3 times and makes the color values adjust to have cyan, yellow, and magenta  
   * make the one picture have first priority and don't move it
   * make another divide into 7 layers and have it move to adjust over
   * pick two colors randomly that are complementary
   * also take a square from a picture and make its color on of the two and put it somewhere
   * there needs to be some opacity
   */
  
  
  public void glitch()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture pic = new Picture("arch.jpg");
	  Picture noBlue = new Picture("arch.jpg");
	  
	  Picture noBlueShift = noBlue;
	  Picture noBlueShift2 = noBlue;
	  noBlueShift.verticalShift(1);
	  noBlueShift.yellow();
	  noBlueShift2.verticalShift(2);
	  noBlueShift2.yellow();
	  Picture noGreen = new Picture("arch.jpg");

	  int width = pixels[0].length;
	  int height =  pixels.length;
	  
	  
	  Pixel[][] colored1 = noBlueShift.getPixels2D();
	  Pixel[][] colored2 = noBlueShift2.getPixels2D();
	  
	  noBlue.verticalShift(1);
	  
	  noGreen.magenta();
	  
	  
	  for (int row = 30; row < 90; row += 1)
	  {
		  for (int col = 0; col < width; col += 1)
		  {
			  Pixel tint = colored1[row][col];
			  pixels[row][col].setColor(tint.getColor());
		  }
	  } 
	  
	  
	  for (int row = 200; row < height; row += 1)
	  {
		  for (int col = 0; col < width; col += 1)
		  {
			  Pixel tint = colored2[row][col];
			  pixels[row][col].setColor(tint.getColor());
		  }
	  } 
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
	Picture arch = new Picture("arch.jpg");
	arch.explore();
	arch.glitch();
	arch.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
