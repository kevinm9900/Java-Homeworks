package recursion;

public class RecursiveMethods {
	// returns factorial of n
	public static int fact(int n)
	{
		if (n <0)
		{
			return -1;
		}
		if(n == 0||n==1)
		{
			return 1;
		}
		return n * fact(n -1);
	}
	// returns the int value as computed by silly recursively
	public static int silly(int x, int y)
	{
		if(x>y)
		{
			return 5;
		}
		if(x == y)
		{
			return 3;
		}
		return silly(x+2, y-3) + silly(x+1, y-2);
		
	}

	// returns the nth fibonacci number in the sequence 0,1,1,2,3,5,8,…
	public static int fib(int n)
	{
		if(n <1)
		{
			return -1;
		}
		if(n == 1)
		{
			return 0;
		}
		if(n ==2)
		{
			return 1;
		}
		return fib(n-1) + fib(n-2);
	}
	// returns the rth position of the nth row of Pascal’s triangle
	public static int triangle(int n, int r)
	{
		if(n<r || r<0)
		{
			return 0;
		}
		if(n==r || r==0)
		{
		return 1;
		}
		return triangle(n-1,r)+ triangle(n-1,r-1);
	}
	
	//assumes: n rings (1 thru n) are on towerA, from smallest to largest with
	// largest on the bottom.
	//task: reports moves to required to move n rings (1 thru n) to towerC, with smallest on top, large on bottom
	// NEVER allows a larger ring to be placed on top of a smaller ring on
	// any tower.
	public static void hanoi(int n, char towerA, char towerB, char towerC)
	{
		if(n <1)
			return;
		if(n ==1)
		{
			moveRing(1, towerA, towerC);
		}
		else
		{
			hanoi(n-1, towerA, towerC, towerB);
			moveRing(n, towerA, towerC);
			hanoi(n-1, towerB, towerA, towerC);
		}
	}
	//receives: ring number to move, n
	public static void moveRing(int n, char source, char dest)
	{
		System.out.println ("move ring" + n + "from" + source + "to" + dest);
	}

	//receives: n1 and n2 values to find greatest common factor of
	//returns the greatest common factor of n1, n2
	public static int gcd(int n1, int n2)
	{
		if(n1 < 0 || n2 <0)
		{
			return -1;
		}
		if(n2 == 0)
		{
			return n1;
		}
		if (n2 >n1)
		{
			return gcd (n2,n1);
		}
		return gcd(n2, n1 % n2);
		
	}

	// receives: n and m, 2 integers for recursive practice
	// returns the recursive value of recv or one of the base case values

	public static int recv(int n, int m)
	{
		if (n<0)
		{
			return (-1 * recv(-n,m));
		}
		if(n == 0)
		{
			return 0;
		}
		return m + recv(n-1,m);
	}

	// sorts array myList from low thru high inclusive
	// recursively sorts
	// if there are at least 2 or more elements from low thru high
	// compute midpoint, mergeSort low thru mid, then mid+1 thru high
	// THEN merges low thru mid and mid+1 thru high
	// method merge is shown below
	public static void mergeSort(int myList[], int low, int high)
	{
		if(low<high)
		{
			int mid = (low+high)/2;
			mergeSort(myList, low, mid);
			mergeSort(myList, mid+1, high);
			merge(myList, low, mid, mid+1, high);
		}
	}
	// merges two sorted lists within myList, used by mergeSort method
	// myList[low:mid] and myList[low1:high]
	public static void merge( int myList[],int low, int mid, int low1,int high)
	{
	int temp[] = new int [(mid-low+1)+(high-low1+1)]; // create a temp to hold merged list
	int s1, s2, d, k; // indexes used to keep track of positions in merging
	s1=low; // start of lower half, s1, upper half start is s2
	s2=low1;
	d=0;
	while(s1<=mid && s2<=high) // while elements in BOTH sublists left
	{
	if(myList[s1] < myList[s2])
	temp[d++] = myList[s1++];
	else
	temp[d++] = myList[s2++];
	}
	while (s1<=mid) // while lower half is not merged, copy rest of it
	{ temp[d++]=myList[s1++];

	}
	while (s2<=high) // while upper half is not merged copy rest into temp
	{ temp[d++]=myList[s2++];
	}

	for(k=0,s1=low;s1<=high; s1++,k++) // now copy temp BACK to myList
	myList[s1]=temp[k];
	}


}
