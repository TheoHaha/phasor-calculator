package numbers;

import java.util.*;

public class Complex extends MyNumber
{
	
	double x,y;
	
	/*
	 *	Complex numbers look like this:
	 * 	a = x + y*i
	 * 	Where i the imaginary root of -1.
	 */
	
	//	Constructor
	public Complex()
	{
		x=0;
		y=0;
	}
	public Complex(double a, double b)
	{
		x=a;
		y=b;
	}
	
	//	Getters
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	//	Setters
	public void setX(double anX)
	{
		x = anX;
	}
	public void setY(double aY)
	{
		y = aY;
	}
	
	//	Prints the complex number
	public void show()
	{
		String xS = Double.toString(x);
		String yS = Double.toString(y);
		if (x != 0)
		{
			if (y > 0)
			{
				System.out.println(xS+"+"+yS+"i");
			}
			else if (y < 0)
			{
				System.out.println(xS+yS+"i");
			}
			else
			{
				System.out.println(xS);
			}
		}
		else
		{
			if (y != 0)
			{
				System.out.println(yS+"i");
			}
			else
			{
				System.out.println(0);
			}
		}
	}
	
	//	Converts the complex number to a string
	public String toString()
	{
		String xS = Double.toString(x);
		String yS = Double.toString(y);
		if (x != 0)
		{
			if (y > 0)
			{
				return xS+"+"+yS+"i";
			}
			else if (y < 0)
			{
				 return xS+yS+"i";
			}
			else
			{
				return xS;
			}
		}
		else
		{
			if (y != 0)
			{
				return yS+"i";
			}
			else
			{
				return "0";
			}
		}
	}
	
	//	Parses a complex number from a string (essentially converts from string to complex)
	//	TODO: Make this method better (coming in v1.x.1)
	public static Complex parseComplex(String s)
	{
		String sanitizedText = s.replaceAll("i$", "");
		sanitizedText = sanitizedText.replaceAll("\\+", " ");
		sanitizedText = sanitizedText.replaceAll("\\-", " -");
		Scanner scanner = new Scanner(sanitizedText);
		
		int i;
		Double[] xyList = {0.0 , 0.0};
		for(i=0; scanner.hasNextDouble(); i++)
		{
			xyList[i] = scanner.nextDouble();
			if (i==2)
				break;
		}
		scanner.close();
		
		if(i==1)
		{
			if (s.contains("i"))
			{
				return new Complex(0,xyList[0]);
			}
			else
			{
				return new Complex(xyList[0],0);
			}
		}
		else
		{
			return new Complex(xyList[0],xyList[1]);
		}
	}
	
	//	Operations
	//	Addition
	private static Complex add(Complex a1, Complex a2)
	{
		return new Complex(a1.x + a2.x, a1.y + a2.y);
	}
	// 	Phasors invlolved
	private static Complex add(Complex a1, Phasor b2)
	{
		Complex a2 = Convert.toComplex(b2);
		return Complex.add(a1, a2);
	}
	private static Complex add(Phasor b1, Complex a2)
	{
		Complex a1 = Convert.toComplex(b1);
		return Complex.add(a1, a2);
	}
	private static Complex add(Phasor b1, Phasor b2)
	{
		Complex a1 = Convert.toComplex(b1);
		Complex a2 = Convert.toComplex(b2);
		return Complex.add(a1, a2);
	}
	public static Complex add(MyNumber n1, MyNumber n2)
	{
		Complex a1 = new Complex();
		Complex a2 = new Complex();
		Phasor b1 = new Phasor();
		Phasor b2 = new Phasor();
		if(n1.getClass()==a1.getClass())
		{
			a1 = (Complex)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Complex.add(a1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Complex.add(a1, b2);
			}
		}
		else if(n1.getClass()==b1.getClass())
		{
			b1 = (Phasor)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Complex.add(b1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Complex.add(b1, b2);
			}
		}
		return new Complex();
	}
	
	//	Subtraction
	private static Complex sub(Complex a1, Complex a2)
	{
		return new Complex(a1.x - a2.x, a1.y - a2.y);
	}
	// 	Phasors invlolved
	private static Complex sub(Complex a1, Phasor b2)
	{
		Complex a2 = Convert.toComplex(b2);
		return Complex.sub(a1, a2);
	}
	private static Complex sub(Phasor b1, Complex a2)
	{
		Complex a1 = Convert.toComplex(b1);
		return Complex.sub(a1, a2);
	}
	private static Complex sub(Phasor b1, Phasor b2)
	{
		Complex a1 = Convert.toComplex(b1);
		Complex a2 = Convert.toComplex(b2);
		return Complex.sub(a1, a2);
	}
	public static Complex sub(MyNumber n1, MyNumber n2)
	{
		Complex a1 = new Complex();
		Complex a2 = new Complex();
		Phasor b1 = new Phasor();
		Phasor b2 = new Phasor();
		if(n1.getClass()==a1.getClass())
		{
			a1 = (Complex)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Complex.sub(a1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Complex.sub(a1, b2);
			}
		}
		else if(n1.getClass()==b1.getClass())
		{
			b1 = (Phasor)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Complex.sub(b1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Complex.sub(b1, b2);
			}
		}
		return new Complex();
	}
	
	//	Multiplication
	private static Complex mul(Complex a1, Complex a2)
	{
		return new Complex(a1.x * a2.x - a1.y * a2.y, a1.y * a2.x + a1.x * a2.y);
	}
	// 	Phasors invlolved
	private static Complex mul(Complex a1, Phasor b2)
	{
		Complex a2 = Convert.toComplex(b2);
		return Complex.mul(a1, a2);
	}
	private static Complex mul(Phasor b1, Complex a2)
	{
		Complex a1 = Convert.toComplex(b1);
		return Complex.mul(a1, a2);
	}
	private static Complex mul(Phasor b1, Phasor b2)
	{
		Complex a1 = Convert.toComplex(b1);
		Complex a2 = Convert.toComplex(b2);
		return Complex.mul(a1, a2);
	}
	public static Complex mul(MyNumber n1, MyNumber n2)
	{
		Complex a1 = new Complex();
		Complex a2 = new Complex();
		Phasor b1 = new Phasor();
		Phasor b2 = new Phasor();
		if(n1.getClass()==a1.getClass())
		{
			a1 = (Complex)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Complex.mul(a1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Complex.mul(a1, b2);
			}
		}
		else if(n1.getClass()==b1.getClass())
		{
			b1 = (Phasor)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Complex.mul(b1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Complex.mul(b1, b2);
			}
		}
		return new Complex();
	}
	
	//	Division
	private static Complex div(Complex a1, Complex a2)
	{
		Phasor b1 = Convert.toPhasor(a1);
		Phasor b2 = Convert.toPhasor(a2);
		return Convert.toComplex(Phasor.div(b1, b2));
	}
	// 	Phasors invlolved
	private static Complex div(Complex a1, Phasor b2)
	{
		Complex a2 = Convert.toComplex(b2);
		return Complex.div(a1, a2);
	}
	private static Complex div(Phasor b1, Complex a2)
	{
		Complex a1 = Convert.toComplex(b1);
		return Complex.div(a1, a2);
	}
	private static Complex div(Phasor b1, Phasor b2)
	{
		Complex a1 = Convert.toComplex(b1);
		Complex a2 = Convert.toComplex(b2);
		return Complex.div(a1, a2);
	}
	public static Complex div(MyNumber n1, MyNumber n2)
	{
		Complex a1 = new Complex();
		Complex a2 = new Complex();
		Phasor b1 = new Phasor();
		Phasor b2 = new Phasor();
		if(n1.getClass()==a1.getClass())
		{
			a1 = (Complex)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Complex.div(a1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Complex.div(a1, b2);
			}
		}
		else if(n1.getClass()==b1.getClass())
		{
			b1 = (Phasor)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Complex.div(b1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Complex.div(b1, b2);
			}
		}
		return new Complex();
	}
	
}
