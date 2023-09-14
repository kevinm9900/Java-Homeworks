package rationalnumbers;

public class RationalNumberImpl_Moran implements RationalNumber{
	private int r1;
	private int r2;
	
	
	public RationalNumberImpl_Moran(int a, int b)
	{
		assert b !=0 : "Denominator cannot be 0";	
		int gcd = 0;
		
		boolean NegNum = false;
		boolean NegDenum = false;
		
		if(a==0)
		{
			this.r1=0;
			this.r2=1;
		}
		else
		{
			if(a<0)
			{
				a= a*(-1);
				NegNum = true;
			}
			if(b<0)
			{
				b = b*(-1);
				NegDenum = true;
			}
			for(int i = 1; i <= a && i <= b; i++)
			{
				if(a % i==0 && b % i==0)
					gcd = i;
			}
			
			if(NegNum && NegDenum)
			{
				this.r1 = a/gcd;
				this.r2 = b/gcd;
			}
			else if(NegNum && !NegDenum)
			{
				this.r1 =-(a)/gcd;
				this.r2= b/gcd;
			}
			else if(!NegNum && NegDenum)
			{
				this.r1 = -(a)/gcd; //keep the num with negative sign
				this.r2 = b/gcd;
			}
			else if(!NegNum && !NegDenum)
			{
				this.r1 = a/gcd;
				this.r2 = b/gcd;
			}
		}
				
		
	}
	//rv is the numerator of the reduced form of this rational number 
	//Ex: since 5/3 is the reduced form of 10/6, (10/6).getNumerator() = 5
	public int getNumerator()
	{
		return this.r1;
	}
	
	//rv is the numerator of the reduced form of this rational number
	//Ex: since 5/3 is the reduced form of 10/6, (10/6).getDenominator() = 3
	public int getDenominator()
	{
		assert r2>0;
		
		return this.r2;
	}

	//rv is the double equivalent of this rational number 
	//Ex: (5/10).getValue() = 0.5
	public double getValue()
	{
		return (double)this.r1 /(double)this.r2;
	}
	public String toString()
	{
		String fraction = r1 + "/" + r2;
		return fraction;
	}
	
}

