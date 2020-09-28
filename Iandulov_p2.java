import java.util.Scanner;

public class BMIcal 
{

    public static void main(String[] args) 
    {
        scanner in=new scanner(System.in);
        system.out.print("Please, write how many pounds you weight: ");
        double weight=in.nextDouble();
        system.out.print("Please, write your height in inches: ");
        double height=in.nextDouble();
    }
        try 
        {
            BMI bmi=new BMI(weight,height);
            bmi.printBMIResult();
        } 
}

public class BMI 
{
   private double weight,height;
   public BMI(double weight,double height) 
   {
     this.height=height;   
     this.weight=weight;   
   }
     
   public double getheight() 
   {
     return height;
   }

   public void setheight(double height) 
   {
     this.height=height;
   }
   public double getweight() 
   {
     return weight;
   }

   public void setweight(double weight) 
   {
     this.weight=weight;
   }
   public void printBMIResult() 
   {
     double BMI=calculateBMI();
     String result;
     if(BMI<=18.5) 
     {
       result="Underweight";
     }
     else if(BMI<25) 
     {
       result="Normal weight";
     } 
     else if(BMI<30) 
     {
       result="Overweight";
     }
     else
     {
       result="Obesity";
     }
     System.out.printf("In result,your body mass index is %.1f (%s)",BMI,result);
   }
}
