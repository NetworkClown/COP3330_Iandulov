ShapeTest.java
public interface Shape 
{
   public double getArea();
   public String getName();
}

Shape2DTest.java
public interface Shape2D 
{
   public double getVolume();

}

SquareTest.java
public class Square implements Shape 
{
   private double side;
   private String name;
  

   public Square(double side) 
   {
       super();
       this.side=side;
       this.name="square";
   }


   public double getArea() 
   {
       return side*side;
   }

   public String getName() 
   {
       return name;
   }
  
  
}

TriangleTest.java
public class Triangle implements Shape 
{
   private double base;
   private double height;
   private String name;
  

   public Triangle(double base,double height) 
   {
       super();
       this.base=base;
       this.height=height;
       this.name="triangle";
   }
   public double getArea() 
   {
       return (base*height)/2;
   }
   public String getName() 
   {
       return name;
   } 
}

CircleTest.java
public class Circle implements Shape 
{
   private double side;
   private String name;
   public Circle(double side) 
   {
       super();
       this.side=side;
       this.name="circle";
   }
   public double getArea() 
   {
       return side*side;
   }
   public String getName() 
   {
       return name;
   } 
}

CubeTest.java
public class Cube implements Shape, Shape3D 
{
   private double side;
   private String name;
   public Cube(double side) 
   {
       super();
       this.side=side;
       this.name="cube";
   }
   public double getArea() 
   {
       return 6*side*side;
   }
   public String getName() 
   {
       return name;
   }
   public double getVolume() 
   {
       return side*side*side;
   }
}

PyramidTest.java
import java.text.DecimalFormat;
public class Pyramid implements Shape, Shape3D 
{
   private double length;
   private double width;
   private double height;
   private String name;
   public Pyramid(double length,double width,double height) 
   {
       super();
       this.length=length;
       this.width=width;
       this.height=height;
       this.name="pyramid";
   }


   public double getArea() 
   {
       DecimalFormat df=new DecimalFormat("####0.00");
       double resultPyramidArea=(length*width)+(length*Math.sqrt(Math.pow(width/2, 2)+Math.pow(height, 2)))+(width*Math.sqrt(Math.pow(length/2, 2)+Math.pow(height, 2)));
       return Double.parseDouble(df.format(resultPyramidArea));
   }

   public String getName() 
   {
       return name;
   }


   public double getVolume() 
   {
       return length*width*height/3;
   }
}

SphereTest.java
import java.text.DecimalFormat;
public class Sphere implements Shape, Shape3D 
{  
   private double radius;
   private String name;
   public Sphere(double radius) 
   {
       super();
       this.radius=radius;
       this.name="sphere";
   }
  
   public String getName() 
   {
       return name;
   }
   public double getArea() 
   {
       DecimalFormat df=new DecimalFormat("####0.00");
       return Double.parseDouble(df.format(4*Math.PI*radius*radius));
   }
   public double getVolume() 
   {
       DecimalFormat df=new DecimalFormat("####0.00");
       return Double.parseDouble(df.format((1.333333333333)*(Math.PI)*(radius*radius*radius)));
   }
}
