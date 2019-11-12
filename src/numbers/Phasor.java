package numbers;

import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Phasor extends MyNumber
{

	double z,phase;

	/*
	 * 	Phasors look like this:
	 *	a = z | φ°
	 */

	//	Constructor
	public Phasor(double a, double b)
	{
		z=a;
		phase=b;
	}
	public Phasor()
	{
		z=0;
		phase=0;
	}

	//	Getters
	public double getZ()
	{
		return z;
	}
	public double getPhase()
	{
		return phase;
	}
	//	Setters
	public void setZ(double aZ)
	{
		z = aZ;
	}
	public void setPhase(double aPhase)
	{
		phase = aPhase;
	}

	//	 Prints the phasor
	public void show()
	{
		System.out.println(z+" |"+phase+"°");
	}

	// 	Converts the phasor to a string
	public String toString()
	{
		String zS = Double.toString(z);
		String oS = Double.toString(phase);
		
		return zS+" |"+oS+"°";
	}
	
	// 	Parses a phasor from a string (essentially converts from string to phasor)
	//	TODO: Make this method better (coming in v1.X.1)
	public static Phasor parsePhasor(String s)
	{
		String sanitizedText = s.replaceAll("°$", "");
		sanitizedText = sanitizedText.replaceAll("\\|", " ");
		Scanner scanner = new Scanner(sanitizedText);
		
		int i;
		Double[] xyList = {0.0 , 0.0};
		for(i=0; scanner.hasNextDouble(); i++)
		{
			xyList[i] = scanner.nextDouble();
		}
		scanner.close();
		
		return new Phasor(xyList[0],xyList[1]);
	}

	//	Addition
	private static Phasor add(Phasor b1, Phasor b2)
	{
		Complex temp1 = Convert.toComplex(b1);
		Complex temp2 = Convert.toComplex(b2);
		Complex result = Complex.add(temp1, temp2);
		return Convert.toPhasor(result);
	}
	//	Complex numbers invloved
	private static Phasor add(Phasor b1, Complex a2)
	{
		Complex a1 = Convert.toComplex(b1);
		Complex result = Complex.add(a1, a2);
		return Convert.toPhasor(result);
	}
	private static Phasor add(Complex a1, Phasor b2)
	{
		Complex a2 = Convert.toComplex(b2);
		Complex result = Complex.add(a1, a2);
		return Convert.toPhasor(result);
	}
	private static Phasor add(Complex a1, Complex a2)
	{
		Complex result = Complex.add(a1, a2);
		return Convert.toPhasor(result);
	}
	public static Phasor add(MyNumber n1, MyNumber n2)
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
				return Phasor.add(a1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Phasor.add(a1, b2);
			}
		}
		else if(n1.getClass()==b1.getClass())
		{
			b1 = (Phasor)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Phasor.add(b1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Phasor.add(b1, b2);
			}
		}
		return new Phasor();
	}

	//	Subtraction
	private static Phasor sub(Phasor b1, Phasor b2)
	{
		Complex temp1 = Convert.toComplex(b1);
		Complex temp2 = Convert.toComplex(b2);
		Complex result = Complex.sub(temp1, temp2);
		return Convert.toPhasor(result);
	}
	//	Complex numbers invloved
	private static Phasor sub(Phasor b1, Complex a2)
	{
		Complex a1 = Convert.toComplex(b1);
		Complex result = Complex.sub(a1, a2);
		return Convert.toPhasor(result);
	}
	private static Phasor sub(Complex a1, Phasor b2)
	{
		Complex a2 = Convert.toComplex(b2);
		Complex result = Complex.sub(a1, a2);
		return Convert.toPhasor(result);
	}
	private static Phasor sub(Complex a1, Complex a2)
	{
		Complex result = Complex.sub(a1, a2);
		return Convert.toPhasor(result);
	}
	public static Phasor sub(MyNumber n1, MyNumber n2)
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
				return Phasor.sub(a1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Phasor.sub(a1, b2);
			}
		}
		else if(n1.getClass()==b1.getClass())
		{
			b1 = (Phasor)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Phasor.sub(b1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Phasor.sub(b1, b2);
			}
		}
		return new Phasor();
	}

	//	Multiplication
	private static Phasor mul(Phasor b1, Phasor b2)
	{
		return new Phasor(b1.z*b2.z, b1.phase+b2.phase);
	}
	//	Complex numbers invloved
	private static Phasor mul(Phasor b1, Complex a2)
	{
		Phasor b2 = Convert.toPhasor(a2);
		return Phasor.mul(b1,b2);
	}
	private static Phasor mul(Complex a1, Phasor b2)
	{
		Phasor b1 = Convert.toPhasor(a1);
		return Phasor.mul(b1,b2);
	}
	private static Phasor mul(Complex a1, Complex a2)
	{
		Phasor b1 = Convert.toPhasor(a1);
		Phasor b2 = Convert.toPhasor(a2);
		return Phasor.mul(b1,b2);
	}
	public static Phasor mul(MyNumber n1, MyNumber n2)
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
				return Phasor.mul(a1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Phasor.mul(a1, b2);
			}
		}
		else if(n1.getClass()==b1.getClass())
		{
			b1 = (Phasor)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Phasor.mul(b1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Phasor.mul(b1, b2);
			}
		}
		return new Phasor();
	}

	//	Division
	private static Phasor div(Phasor b1, Phasor b2)
	{
		try 
		{
			return new Phasor(b1.z/b2.z, b1.phase-b2.phase);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JPanel(), "Error! Division by zero.\nPhasor division failed!", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	//	Complex numbers invloved
	private static Phasor div(Phasor b1, Complex a2)
	{
		Phasor b2 = Convert.toPhasor(a2);
		return Phasor.div(b1,b2);
	}
	private static Phasor div(Complex a1, Phasor b2)
	{
		Phasor b1 = Convert.toPhasor(a1);
		return Phasor.div(b1,b2);
	}
	private static Phasor div(Complex a1, Complex a2)
	{
		Phasor b1 = Convert.toPhasor(a1);
		Phasor b2 = Convert.toPhasor(a2);
		return Phasor.div(b1,b2);
	}
	public static Phasor div(MyNumber n1, MyNumber n2)
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
				return Phasor.div(a1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Phasor.div(a1, b2);
			}
		}
		else if(n1.getClass()==b1.getClass())
		{
			b1 = (Phasor)n1;
			if(n2.getClass()==a2.getClass())
			{
				a2 = (Complex)n2;
				return Phasor.div(b1, a2);
			}
			else if(n2.getClass()==b2.getClass())
			{
				b2 = (Phasor)n2;
				return Phasor.div(b1, b2);
			}
		}
		return new Phasor();
	}

}
