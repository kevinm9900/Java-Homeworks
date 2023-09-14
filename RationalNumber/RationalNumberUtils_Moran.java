package rationalnumbers;
import rationalnumbers.RationalNumber;
import rationalnumbers.RationalNumberImpl_Moran;


public class RationalNumberUtils_Moran {

	//rv = r1+r2, where + is regular numerical addition
			public static RationalNumber add(RationalNumber r1, RationalNumber r2)
			{
				
				int comdenom = r1.getDenominator() * r2.getDenominator();
				int num1 = r1.getNumerator() * r2.getDenominator();
				int num2 = r2.getNumerator() * r1.getDenominator();
				int sum = num1 + num2;

				RationalNumber rationalnum = new RationalNumberImpl_Moran(sum, comdenom);
				return rationalnum;
				
			}
	//rv = r1-r2, where - is regular numerical subtraction
			public static RationalNumber subtract(RationalNumber r1, RationalNumber r2)
			{
				int comdenom = r1.getDenominator() * r2.getDenominator();
				int num1 = r1.getNumerator() * r2.getDenominator();
				int num2 = r2.getNumerator() * r1.getDenominator();
				int difference = num1 - num2;
				
				return new RationalNumberImpl_Moran(difference,comdenom);
			}
	//rv = r1*r2, where * is regular numerical multiplication
			public static RationalNumber multiply(RationalNumber r1, RationalNumber r2)
			{
				int num = r1.getNumerator() * r2.getNumerator();
				int denom = r1.getDenominator() * r2.getDenominator();
				
				return new RationalNumberImpl_Moran(num,denom);
			}
	//rv =  r1/r2, where + is regular numerical division
			public static RationalNumber divide(RationalNumber r1, RationalNumber r2)
			{
				
				int num2 = r2.getDenominator();
				int denom2 = r2.getNumerator();
				
				int num3 = r1.getNumerator() * num2;
				int denom3 = r1.getDenominator() * denom2;
				
				return new RationalNumberImpl_Moran(num3,denom3);
			}
	// rv = -r1, where - is regular numerical negation
			public static RationalNumber negate(RationalNumber r1)
			{
				
				int num3 = -(r1.getNumerator());
				int denom3 = (r1.getDenominator());
				return new RationalNumberImpl_Moran(num3,denom3);
			}

			
}
