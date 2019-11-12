package numbers;
import java.lang.Math;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Convert
{
	//	Converts complex to phasor
	public static Phasor toPhasor(Complex a)
	{
		try
		{
			double z = Math.hypot(a.x, a.y);
			double phase = Math.atan(a.y/a.x);
			phase = Math.toDegrees(phase);
			return new Phasor(z, phase);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(new JPanel(), "Error! Division by zero.\nComplex number conversion failed!", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	//	Converts phasor to complex
	public static Complex toComplex(Phasor b)
	{
		double phaseB = Math.toRadians(b.phase);
		double x = b.z * Math.cos(phaseB);
		double y = b.z * Math.sin(phaseB);
		return new Complex(x,y);
	}
	
	// Convets complex or phasor to string
	public static String toString(Complex a)
	{
		String xS = Double.toString(a.x);
		String yS = Double.toString(a.y);
		if (a.x != 0)
		{
			if (a.y > 0)
			{
				return xS+"+"+yS+"i";
			}
			else if (a.y < 0)
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
			if (a.y != 0)
			{
				return yS+"i";
			}
			else
			{
				return "0";
			}
		}
	}
	public static String toString(Phasor b)
	{
		String zS = Double.toString(b.z);
		String phaseS = Double.toString(b.phase);
		
		return zS+" |"+phaseS+"°";
	}
}
