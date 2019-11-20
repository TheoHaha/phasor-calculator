package numbers;

public class MyNumber
{
	public static MyNumber parseMyNumber(String s)
	{
		if (s.contains("|"))
			return Phasor.parsePhasor(s);
		else
			return Complex.parseComplex(s);
	}
	
	public void show()
	{
		if (this.getClass()==new Phasor().getClass())
			((Phasor)this).show();
		else if (this.getClass()==new Complex().getClass())
			((Complex)this).show();
	}
	
	public String toString()
	{
		if (this.getClass()==new Phasor().getClass())
			return ((Phasor)this).toString();
		else if (this.getClass()==new Complex().getClass())
			return ((Complex)this).toString();
		
		return null;
	}
}
