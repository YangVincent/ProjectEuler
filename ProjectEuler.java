import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.util.*;
public class ProjectEuler 
{
	//fields
	HashMap<String, Integer> dictionary;
	//constructors
	public ProjectEuler(){}
	//methods
	public static void main(String[] args)
	{
		ProjectEuler tester = new ProjectEuler();
		//Scanner main = new Scanner(tester.fileReader());
		String namesScores = tester.readFileAsString("p022_names.txt");
		//int in = tester.getInput();
		System.out.println("1: "+tester.multiplesOfThreeAndFive(1000));
		System.out.println("2: "+tester.evenFibNumbers());
		System.out.println("3: "+tester.largestPrimeFactor());
		System.out.println("4: "+tester.largestPalindromeProduct());
		System.out.println("5: "+tester.smallestMultiple());
		System.out.println("6: "+tester.sumSquareDifference());
		System.out.println("7: "+tester.prime());
		System.out.println("8: "+tester.largestProduct()); 
		System.out.println("9: "+tester.pythagoreanTriplet());
		System.out.println("10:"+tester.summationOfPrimes());
		System.out.println("11:"+tester.largestProductInAGrid());
		//System.out.println("12:"+tester.highlyDivisibleTriangularNumber());
		System.out.println("13:"+tester.largeSum());
		//System.out.println("14:"+tester.largestCollatzSequence());
		System.out.println("15:"+tester.latticePaths());
		System.out.println("16:"+tester.powerDigitSum());
		//System.out.println("17:"+tester.numberLetterCounts());//fix
		//System.out.println("19:"+tester.countingSundays());//fix
		System.out.println("20:"+tester.factorialDigitSum());
		System.out.println("22:"+tester.namesScores(namesScores));
		System.out.println("25:"+tester.thousandDigitFibonacciNumber());
		System.out.println("28:"+tester.numberSpiralDiagonals());
		System.out.println("30:"+tester.digitFifthPowers());
		System.out.println("31:"+tester.coinSums());
		System.out.println("36:"+tester.doubleBasePalindromes());
		System.out.println("42:"+tester.codedTriangleNumbers());
		System.out.println("43:"+tester.substringDivisibility());
		System.out.println("46:"+tester.goldbachsOtherConjecture());//fix
		System.out.println("47:"+tester.distinctPrimesFactors());//fix
		System.out.println("48:"+tester.selfPowers());
	//System.out.println("92:"+tester.squareDigitChains()); done but slow
		System.out.println("145:"+tester.reversibleUnderBillion());
	}
	//helpers
	private String readFileAsString(String filePath)
	{
		FileInputStream reader = null;
		String result = null;
			try
			{
				byte[] buffer = new byte[(int) new File(filePath).length()];
				reader = new FileInputStream(filePath);
				reader.read(buffer);
				result = new String(buffer, "UTF-8");
			}
			catch (IOException ex)
			{
				System.out.println("File cannot be read.");
				return null;
			}
			finally
			{
				try
				{
					if (reader!= null)
					{
						reader.close();
					}
				}
				catch(IOException ex)
				{
					System.out.println("FIle cannot be closed." );
					return null;
				}
			}
		return result;
	}
	private File fileReader()
	{
		File file = new File("names.txt");
		return file;
	}
	private boolean isPrime(long n){
		if(n <= 3)
		     return n > 1;
		if(n % 2 == 0 || n % 3 == 0){
			return false;
		}
		for(int i = 5; i < Math.sqrt(n)+1; i+=6)
		{
			if(n % i == 0 || n % (i+2) == 0)
			{//n&(i+2) has to be there because it is incremented by 6
				return false;
			}
		}
		return true;
	}
	private int getInput()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("What is the input?");
		return s.nextInt();
	}
	private int fibonacci(int number)
	{
        if(number == 1 || number == 2)
            return 1;
        int fibo1=1, fibo2=1, fibonacci=1;
        for(int i= 3; i<= number; i++){
            fibonacci = fibo1 + fibo2; //Fibonacci number is sum of previous two Fibonacci number
            fibo1 = fibo2;
            fibo2 = fibonacci;
        }
        return fibonacci; //Fibonacci number
    }  
	private String backwards(String s)
	{
		return new StringBuilder(s).reverse().toString();
	}
	private boolean isPalindrome(long num)
	{
		return backwards(num+"").equals(num+"");
	}
	private int numFactors(int num)//get this to work... does it work?
	{
		int[] factors = new int[500];
		int counter = 0;
		for (int i = 0; true; i++)
		{
			if (isPrime(i))
			{
				if (num%i==0)
				{
					factors[counter++]=i;
					num/=i--;
				}
			}
			if (num==1)
				return counter-1;
		}
	}
	private int lengthOfCollatzChain(long j, int[] lengths)
	{
		long i = j;
		int counter = 0;
		if (i<=0)
			return 1;
		while (i!=1)
		{
			counter++;
			if (i%2==0)
				i=i/2;
			else
				i=i*3+1;
		}
		return counter;
	}
	private BigInteger factorial(int i)
	{
		BigInteger factorial= BigInteger.ONE;
		for (int j = i; j>0; j--)
		{
			factorial = factorial.multiply(new BigInteger(j+""));
		}
		return factorial;
	}
	private int sumOfNumbers(long pow)
	{
		int sum = 0;
		BigInteger b = new BigInteger(""+pow);
		String bs = b.toString();
		System.out.println("bs is " + bs);
		for (int i = 0; i < bs.length(); i++)
		{
			System.out.println("sum is " + sum);
			sum+=Integer.parseInt(bs.substring(i, i+1));
		}
		return sum;
	}
	private int sumOfNumbers(BigInteger pow)
	{
		int sum = 0;
		BigInteger b = pow;
		String bs = b.toString();
		for (int i = 0; i < bs.length(); i++)
		{
			sum+=Integer.parseInt(bs.substring(i, i+1));
		}
		return sum;
	}
	private int fifthSumOfNumbers(int j)
	{
		int sum = 0;
		BigInteger b = new BigInteger(j+"");
		String bs = b.toString();
		for (int i = 0; i < bs.length(); i++)
		{
			sum+=Math.pow(Integer.parseInt(bs.substring(i, i+1)), 5);
		}
		return sum;
	}
	private int squareDigits(int j)
	{
		int sum = 0;
		BigInteger b = new BigInteger(j+"");
		String bs = b.toString();
		for (int i = 0; i < bs.length(); i++)
		{
			sum+=Math.pow(Integer.parseInt(bs.substring(i, i+1)), 2);
		}
		return sum;
	}
	private int reverse(int n)
	{
		return Integer.parseInt(backwards(n+""));
	}
	private boolean allOdd(int n, StringBuilder str, int length)
	{
		for (int i = 0; i < length && i < str.length(); i++)
		{
			if (Integer.parseInt(str.charAt(i)+"")%2==0)
				return false;
		}
		return true;
	}
	private int getNameValue(String s)
	{
		int value = 0;
		for (int i = 0; i < s.length(); i++)
			value+=dictionary.get(s.substring(i, i+1))+1;
		return value;
	}
	private int numberLetter(int i)
	{
		if (i==0)
			return 0;
		if (i == 1 || i==2 || i==6 || i==10)
		{
			return 3;
		}
		else if (i==3||i==7||i==8||i==50||i==60)
		{
			return 5;
		}
		else if (i==4||i==5||i==9)
		{
			return 4;
		}
		else if (i==11||i==12 || i==20 || i==30 || i==40||i==80||i==90)
		{
			return 6;
		}
		else if (i==13||i==14||i==19||i==18)
		{
			return 8;
		}
		else if (i==15||i==16||i==70)
			return 7;
		else if (i==17)
			return 9;
		else if (i==100)
				return 10;
		else if (i%100==0)
		{
			return numberLetter(i/100)+7;
		}
		else if (i>=100)
		{
			return numberLetter(i/100)+7+numberLetter(i%100)+3;
		}
		else if (i>10)
		{
			return numberLetter(i/10*10)+numberLetter(i%10);//tens then ones
		}
		System.out.println("why is it here");
		return -1;
	}
	private long toBinary(long i)
	{
		BigInteger bi = BigInteger.ONE;
		while(true)
		{
			if (i<=0)
				break;
			if (i%2==0)
			{
				bi = bi.multiply(new BigInteger("10"));
				bi = bi.add(BigInteger.ONE);
				i/=2;
			}
			else
			{
				i/=2;
				bi = bi.multiply(new BigInteger("10"));
			}
		}
		bi=bi.multiply(new BigInteger("10"));
		bi=bi.add(BigInteger.ONE);
		return bi.longValue();
	}
	private boolean isBinaryPolynomial(int n)
	{
		String binNum = Integer.toBinaryString(n);
		if (binNum.equals(backwards(binNum)))
		{
			return true;
		}
		return false;
	}
	private int sum(String s) 
	{
		dictionary = new HashMap<String, Integer>();
		int index = 65;
		for (int i = 0; i < 26; i++)
		{
			dictionary.put(((char)index)+"", new Integer(i));
			index++;
		}
		int sum=0;
		for (int i = 0; i < s.length(); i++)
		{
			sum+=dictionary.get(s.substring(i, i+1))+1;
		}
		return sum;
	}
	private String binomialCoefficient(int i, int j)
	{
		//iCk
		return factorial(i).divide(factorial(i-j).multiply(factorial(j))).toString();
	}

	//problems
	private String latticePaths()
	{
		return binomialCoefficient(40, 20);
	}
	private int distinctPrimesFactors()
	{
		return 1;
	}
	private long codedTriangleNumbers()
	{
		int result = 0;
		String[] words = readFileAsString("p042_words.txt").split("\",\"");
		words[0] = words[0].substring(1);
		words[words.length-1]= words[words.length-1].substring(0, words[words.length-1].length()-1); 
		for(int i = 0; i < words.length; i++)
		{
		    double wordsum = (Math.sqrt(1+8*sum(words[i])) - 1.0) / 2.0;
		    if (wordsum == ((int)wordsum)) 
		    {
		        result++;
		    }
		}
		return result;
	}
	private long doubleBasePalindromes()
	{
		int sum = 0;
		for (int i = 1; i <1000001; i++)
		    if (isPalindrome(i))
		    {
		        if (isBinaryPolynomial(i))
		            sum += i;
		    }
		return sum;
	}
	private long goldbachsOtherConjecture()
	{
		return 1;
	}
	private long substringDivisibility()
	{
		//d4d5d6 has to be divisible by 5, so d6 has to be 0 or 5
		//d6d7d8 has to be divisible by 11, and d6 is 0 or 5 so there are only 011, 022, and 099. 
		//these are not pandigital numbers, so d6 has to be 5. 
		//if d6 is 5, then d6d7d8 has to be the 500s divisible by 11: 506, 517, 528, 539, 561, 572, 583, and 594
		//d7d8d9 has to be divisible by 13, so d6d7d8d9 has to be 5286, 5390, 5728, or 5832
		//same for d8d9d10, so d6d7d8d9d10 must be 52867, 53901, or 57289
		//d5d6d7 has to be divisible by 7/must end on 52, 01, or 89. therefore we have 53901 or 57289
		//d2d3d4 has to be divisible by 2 so d4 has to be even. 0952867, 4952867, 0357289, 4357289, 6357289 are choices.
		//d3d4d5 has to be divisible by 3 so it must end on 09, 49, 03, 43, or 63. (no repeat digits)
		//digit sum: sum of respective digits must be divisible by 3 for it to be divisibe by 3.
		//30952867, 60357289, 06357289 are left. there are still 1 and 4, and these have no rules so 
		//1430952867 +  1460357289 +  1406357289 + 4130952867 + 4160357289 +4106357289 = 
		return 16695334890l;
	}
	private int numberLetterCounts()
	{
		int letters = 0;
		//one to a thousand
		for (int i = 1; i < 20; i++)
		{
			letters+=numberLetter(i);
		}
		return letters;
	}
	private int coinSums()
	{
		int differentWays = 0;
		int[] values = {1, 2, 5, 10, 20, 50, 100, 200};
		for (int q = 200; q >=0; q-=200)
		{
			for (int w = q; w>=0; w-=100)
			{
				for (int e = w; e >= 0; e-=50)
				{
					for (int r = e; r >= 0; r-=20)
					{
						for (int t = r; t>=0; t-=10)
						{
							for (int y = t; y >= 0; y-=5)
							{
								for (int u = y; u >= 0; u-=2)
								{
										differentWays++;
								}
							}
						}
					}
				}
			}
		}
		return differentWays;
	}
	private int numberSpiralDiagonals()
	{
		int sum = 1;
		int counter = 0;
		int num =1;
		for (int i = 2; true;)
		{
			counter++;
			num+=i;
			sum+=num;
			if (i>=1000&&counter>=4)
				break;
			if (counter>=4)
			{
				i+=2;
				counter=0;
			}
		}
		return sum;
	}
	private long namesScores(String file)
	{
		//initialize dictionary
		dictionary = new HashMap<String, Integer>();
		int index = 65;
		for (int i = 0; i < 26; i++)
		{
			dictionary.put(((char)index)+"", new Integer(i));
			index++;
		}
		long sum=0;
		String[] s = file.substring(1, file.length()-1).split("\",\"");
		Sort sort = new Sort();
		ArrayList<Comparable> names = new ArrayList<Comparable>();
		//names.get(0)
		for (String i: s)
			names.add(i);
		sort.mergeSort(names);
		int counter = 1;
		//System.out.println("name value first is " + getNameValue((String)names.get(0)));
		for (Comparable<String> i: names)
		{
			sum+=getNameValue(i.toString())*counter++;
		}
		return sum;
	}
	private int reversibleUnderBillion()
	{
		//two digit: 20 solutions, must brute force. all must be odd. 
		//three digit: middle digit = odd, so 3rd digit must have carryover+odd
		//four digit: 600 solutions, none can have carryover
		//five digit
		//six digit
		//seven digit
		//eight digit//refer to 2 and 4.
		//nine digit: n/a
		int sum = 0;
		for (int i = 1; i < 10; i++)
		{
			if (i%2==0)
				sum+=(20*(Math.pow(30, (i/2)-1)));
			else if (i%4==0)//8 are like 2, 4, and 6
			{}
			else if (i%4==3)
			{
				sum+=(5*20*(Math.pow((25*20),(i/4))));
			}
		}
		return sum;
	}
	private int squareDigitChains()
	{
		int max = 10000000;//ten million
		int temp = 0;
		int counter = 0;
		for (int i = 2; i < max; i++)
		{
			temp = i;
			while(temp!=1 && temp!=89)
			{
				temp = squareDigits(temp);
			}
			if (temp == 89)
				counter++;
		}
		return counter;
	}
	private int thousandDigitFibonacciNumber()
	{
		BigInteger bi = BigInteger.ONE;
		BigInteger one = BigInteger.ONE;
		BigInteger two = BigInteger.ONE;
		for (int i = 3; true; i++)
		{
			bi = one.add(two);				
			one = two.add(BigInteger.ZERO);
			two = bi.add(BigInteger.ZERO);	
			if (bi.toString().length()>=1000)
				return i;
		}
	}
	private long selfPowers()
	{
		long sum = 0;
		long temp = 1;
		int length=0;
		for (int i = 1; i<=1000; i++)
		{
			//get power
			for (int j = 0; j < i; j++)
			{
				temp*=i;
				length=(temp+"").length();
				if (length>10)
				{
					temp = new BigInteger((temp+"").substring(length-10)).longValue();
				}
				length = 0;
			}
			//multiply by power
			//delete if greater than 10
			//add to sum
			sum+=temp;
			temp = 1;
			length = (sum+"").length();
			if (length>10)
				sum = new BigInteger((sum+"").substring(length-10, length)).longValue();
			//delete if greater than 10
		}
		return sum;
	}
	private int powerDigitSum()
	{
		BigInteger pow = BigInteger.ONE;
		BigInteger two = new BigInteger(""+2);
		for (int i = 0; i < 1000; i++)
		{
			pow = pow.multiply(two);
		}
		return sumOfNumbers(pow);
	}
	private int digitFifthPowers()
	{
		//x*9^5, x=6, round to 355000
		int sum = 0;
		for (int i = 2; i < 355000; i++)
		{
			if (fifthSumOfNumbers(i)==i)
				sum+=i;
		}
		return sum;
	}
	private int factorialDigitSum()
	{
		BigInteger factorial = factorial(99).multiply(new BigInteger(100+""));
		//System.out.println("factorial is " + factorial(99));
		int sum = 0;
		String strFactorial = factorial.toString();
		for (int i = 0; i < strFactorial.length(); i++)
		{
			sum+=Integer.parseInt(strFactorial.substring(i, i+1));
		}
		return sum;
	}
	
	private int largestCollatzSequence()
	{
		int[] lengths = new int[27114424+1];
		int longest=0;
		for (int i = 837799; i<837799; i++)
		{
			if (lengthOfCollatzChain(i, lengths)>longest)
				longest=lengthOfCollatzChain(i, lengths);
		}
		return longest;
	}
	private long summationOfPrimes()
	{
		long sum=0;
		int max = 2000000;
		for (int i = 0; i < max; i++)
		{
			if (isPrime(i))
			{
				sum+=i;
			}
		}
		return sum;
	}
	private long highlyDivisibleTriangularNumber()
	{
		int[] factors = new int[500];
		//generate number (natural)
		int counter = 1;
		for (int natural = 1; true; natural+=counter)
		{
			counter++;
			if (numFactors(natural)>500)
				return natural;
		}
		
	}
	private long largeSum()
	{
		BigInteger sum = BigInteger.ZERO;
		String val = "37107287533902102798797998220837590246510135740250"
				+" 46376937677490009712648124896970078050417018260538"
				+" 74324986199524741059474233309513058123726617309629"
				+" 91942213363574161572522430563301811072406154908250"
				+" 23067588207539346171171980310421047513778063246676"
				+" 89261670696623633820136378418383684178734361726757"
				+" 28112879812849979408065481931592621691275889832738"
				+" 44274228917432520321923589422876796487670272189318"
				+" 47451445736001306439091167216856844588711603153276"
				+" 70386486105843025439939619828917593665686757934951"
				+" 62176457141856560629502157223196586755079324193331"
				+" 64906352462741904929101432445813822663347944758178"
				+" 92575867718337217661963751590579239728245598838407"
				+" 58203565325359399008402633568948830189458628227828"
				+" 80181199384826282014278194139940567587151170094390"
				+" 35398664372827112653829987240784473053190104293586"
				+" 86515506006295864861532075273371959191420517255829"
				+" 71693888707715466499115593487603532921714970056938"
				+" 54370070576826684624621495650076471787294438377604"
				+" 53282654108756828443191190634694037855217779295145"
				+" 36123272525000296071075082563815656710885258350721"
				+" 45876576172410976447339110607218265236877223636045"
				+" 17423706905851860660448207621209813287860733969412"
				+" 81142660418086830619328460811191061556940512689692"
				+" 51934325451728388641918047049293215058642563049483"
				+" 62467221648435076201727918039944693004732956340691"
				+" 15732444386908125794514089057706229429197107928209"
				+" 55037687525678773091862540744969844508330393682126"
				+" 18336384825330154686196124348767681297534375946515"
				+" 80386287592878490201521685554828717201219257766954"
				+" 78182833757993103614740356856449095527097864797581"
				+" 16726320100436897842553539920931837441497806860984"
				+" 48403098129077791799088218795327364475675590848030"
				+" 87086987551392711854517078544161852424320693150332"
				+" 59959406895756536782107074926966537676326235447210"
				+" 69793950679652694742597709739166693763042633987085"
				+" 41052684708299085211399427365734116182760315001271"
				+" 65378607361501080857009149939512557028198746004375"
				+" 35829035317434717326932123578154982629742552737307"
				+" 94953759765105305946966067683156574377167401875275"
				+" 88902802571733229619176668713819931811048770190271"
				+" 25267680276078003013678680992525463401061632866526"
				+" 36270218540497705585629946580636237993140746255962"
				+" 24074486908231174977792365466257246923322810917141"
				+" 91430288197103288597806669760892938638285025333403"
				+" 34413065578016127815921815005561868836468420090470"
				+" 23053081172816430487623791969842487255036638784583"
				+" 11487696932154902810424020138335124462181441773470"
				+" 63783299490636259666498587618221225225512486764533"
				+" 67720186971698544312419572409913959008952310058822"
				+" 95548255300263520781532296796249481641953868218774"
				+" 76085327132285723110424803456124867697064507995236"
				+" 37774242535411291684276865538926205024910326572967"
				+" 23701913275725675285653248258265463092207058596522"
				+" 29798860272258331913126375147341994889534765745501"
				+" 18495701454879288984856827726077713721403798879715"
				+" 38298203783031473527721580348144513491373226651381"
				+" 34829543829199918180278916522431027392251122869539"
				+" 40957953066405232632538044100059654939159879593635"
				+" 29746152185502371307642255121183693803580388584903"
				+" 41698116222072977186158236678424689157993532961922"
				+" 62467957194401269043877107275048102390895523597457"
				+" 23189706772547915061505504953922979530901129967519"
				+" 86188088225875314529584099251203829009407770775672"
				+" 11306739708304724483816533873502340845647058077308"
				+" 82959174767140363198008187129011875491310547126581"
				+" 97623331044818386269515456334926366572897563400500"
				+" 42846280183517070527831839425882145521227251250327"
				+" 55121603546981200581762165212827652751691296897789"
				+" 32238195734329339946437501907836945765883352399886"
				+" 75506164965184775180738168837861091527357929701337"
				+" 62177842752192623401942399639168044983993173312731"
				+" 32924185707147349566916674687634660915035914677504"
				+" 99518671430235219628894890102423325116913619626622"
				+" 73267460800591547471830798392868535206946944540724"
				+" 76841822524674417161514036427982273348055556214818"
				+" 97142617910342598647204516893989422179826088076852"
				+" 87783646182799346313767754307809363333018982642090"
				+" 10848802521674670883215120185883543223812876952786"
				+" 71329612474782464538636993009049310363619763878039"
				+" 62184073572399794223406235393808339651327408011116"
				+" 66627891981488087797941876876144230030984490851411"
				+" 60661826293682836764744779239180335110989069790714"
				+" 85786944089552990653640447425576083659976645795096"
				+" 66024396409905389607120198219976047599490197230297"
				+" 64913982680032973156037120041377903785566085089252"
				+" 16730939319872750275468906903707539413042652315011"
				+" 94809377245048795150954100921645863754710598436791"
				+" 78639167021187492431995700641917969777599028300699"
				+" 15368713711936614952811305876380278410754449733078"
				+" 40789923115535562561142322423255033685442488917353"
				+" 44889911501440648020369068063960672322193204149535"
				+" 41503128880339536053299340368006977710650566631954"
				+" 81234880673210146739058568557934581403627822703280"
				+" 82616570773948327592232845941706525094512325230608"
				+" 22918802058777319719839450180888072429661980811197"
				+" 77158542502016545090413245809786882778948721859617"
				+" 72107838435069186155435662884062257473692284509516"
				+" 20849603980134001723930671666823555245252804609722"
				+" 53503534226472524250874054075591789781264330331690";
		BigInteger[] arr = new BigInteger[100];
		String[] split = val.split(" ");
		for (int i = 0; i < 100; i++)
		{
			arr[i] = new BigInteger(split[i]);
		}
		for (BigInteger i: arr)
		{
			sum=sum.add(i);
		}
		String end = sum.toString();
		String firstTen = end.substring(0, 10);
		//BigInteger value = new BigInteger(val);
		return new Long(""+firstTen).longValue();
	}
	private int countingSundays()
	{
		int numSundays=0;
		GregorianCalendar cal = new GregorianCalendar();
		return numSundays;
	}
	private int largestProductInAGrid()
	{
		String grid = 
				"08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08"
				+" 49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00"
				+" 81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65"
				+" 52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91"
				+" 22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80"
				+" 24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50"
				+" 32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70"
				+" 67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21"
				+" 24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72"
				+" 21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95"
				+" 78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92"
				+" 16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57"
				+" 86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58"
				+" 19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40"
				+" 04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66"
				+" 88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69"
				+" 04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36"
				+" 20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16"
				+" 20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54"
				+" 01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int[][] grid2d = new int[20][20]; //i is horizontal, j is vertical. 
		String regex = " ";
		String[] stringGrid = grid.split(regex);
		int[] grid1d = new int[400];
		for (int i = 0; i < 400; i++)
		{
			grid1d[i] = Integer.parseInt(stringGrid[i]);
		}
		//split to 2d
		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 20; j++)
			{
				grid2d[i][j] = grid1d[i*20+j];//i is vertical, j is horizontal
			}
		}
		int Product = 0;
        int largest = 0;
        // Check horizontally
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 17; j++){
                Product = grid2d[i][j] * grid2d[i][j + 1] * grid2d[i][j + 2] * grid2d[i][j + 3];
                if(Product > largest){
                    largest = Product;
                }
            }   
        }
        // Check vertically
        for(int i = 0; i < 17; i ++){
            for(int j = 0; j < 20; j++){
                Product = grid2d[i][j] * grid2d[i + 1][j] * grid2d[i + 2][j] * grid2d[i + 3][j];
                if(Product > largest){
                    largest = Product;
                }
            }
        }
        // Check diagonally right
        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 17; j++){
                Product = grid2d[i][j] * grid2d[i + 1][j + 1] * grid2d[i + 2][j + 2] * grid2d[i + 3][i + 3];
                if(Product > largest){
                    largest = Product;
                }
            }
        }
        // Check diagonally left
        for(int i = 0; i < 17; i ++){
            for(int j = 3; j < 20; j ++){
                Product = grid2d[i][j] * grid2d[i + 1][j - 1] * grid2d[i + 2][j  - 2] * grid2d[i + 3][j - 3];
                if(Product > largest){
                    largest = Product;
                }
            }
        }
		return largest;
	}
	private int pythagoreanTriplet() //375, 200, 425
	{
		int num = 1000;
	    for (int a = 1; a < num; a++)
	        for (int b = 2; b < a; b++)//check every a and b
	            {
	                if (a*a +b*b == (num-a-b)*(num-a-b))//for every a, b, and c that add up to 1000 and are 
	                	//pythagorean triplets
	                    return a*b*(num-a-b); //ans = 31875000 
	            }
	    return -1;
	}
	private long largestProduct()
	{
		String num = 
				"73167176531330624919225119674426574742355349194934"
				+ "96983520312774506326239578318016984801869478851843"
				+ "85861560789112949495459501737958331952853208805511"
				+ "12540698747158523863050715693290963295227443043557"
				+ "66896648950445244523161731856403098711121722383113"
				+ "62229893423380308135336276614282806444486645238749"
				+ "30358907296290491560440772390713810515859307960866"
				+ "70172427121883998797908792274921901699720888093776"
				+ "65727333001053367881220235421809751254540594752243"
				+ "52584907711670556013604839586446706324415722155397"
				+ "53697817977846174064955149290862569321978468622482"
				+ "83972241375657056057490261407972968652414535100474"
				+ "82166370484403199890008895243450658541227588666881"
				+ "16427171479924442928230863465674813919123162824586"
				+ "17866458359124566529476545682848912883142607690042"
				+ "24219022671055626321111109370544217506941658960408"
				+ "07198403850962455444362981230987879927244284909188"
				+ "84580156166097919133875499200524063689912560717606"
				+ "05886116467109405077541002256983155200055935729725"
				+ "71636269561882670428252483600823257530420752963450";
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int counter = 1, currentIndex=0;
		long largestProduct=1, product=1;
        for(int i = 0; i < num.length(); i++)  
        {
            String b = "" + num.charAt(i);
            currentIndex = Integer.parseInt(b);
            arr.add(currentIndex);
            counter++;
            if(counter == 13) 
            {
                for(int x:arr) 
                {
                    product*=x;
                }
                if(product>largestProduct) 
                {
                    largestProduct = product;
                }
               arr.remove(0);
               product = 1;
            }
            counter=arr.size();
        }
		return largestProduct;
	}
	private long prime()
	{
		int count = 1;
        int i = 3;
        while (count < 10001) {
            if (isPrime(i)) ++count;
            i += 2;
        }
        return i-2;
	}
	private int sumSquareDifference()
	{
		int sumSquares=0, sumNaturals =0;
		int N = 100;
		sumNaturals = N * (N+1)/ 2;
		sumSquares=(N * (N + 1) * (2 * N + 1)) / 6;
		sumNaturals*=sumNaturals;
		return sumNaturals-sumSquares;
	}
	private int smallestMultiple()
	{
		int smallestMultiple = 20;
		boolean divisible = true;
		while(true)
		{
			smallestMultiple+=20;
			for (int i = 1; i < 20; i++)
			{
				if (smallestMultiple%i!=0)
				{
					divisible = false;
					break;
				}
			}
			if (divisible)
				return smallestMultiple;
			divisible = true;	
		}
	}
	private int largestPalindromeProduct()
	{
		int largestPalindrome=1;
		//generate 3 digit numbers
		int product = 0;
		for (int i = 100; i < 999; i++)
		{
			for (int j = 100; j < 999; j++)
			{
				product = j*i;
				//check if palindromes
				if (isPalindrome(product) && product > largestPalindrome)
				{
					largestPalindrome = product;
				}
			}
		}
		//multiply them
		//check if they are palindromes
		//check if they beat the largest
		return largestPalindrome;
	}
	private int multiplesOfThreeAndFive(int max)
	{
		int numFive = max/5;
		int numThree = max/3;
		int[] multiplesOfFive = new int[numFive];
		int[] multiplesOfThree = new int[numThree];
		//find multiples of five
		int counter = 0;
		for (int i = 0; i < max; i+=5)
		{
			multiplesOfFive[counter] = i;
			counter++;
		}
		counter = 0;
		for (int i = 0; i < max; i+=3)
		{
			if (i%5!=0)
			{
				multiplesOfThree[counter] = i;
				counter++;
			}
		}
		int sum= 0;
		for (int i: multiplesOfFive)
			sum+=i;
		for (int i: multiplesOfThree)
			sum+=i;
		return sum;
		//find multiples of three
	}
	private long evenFibNumbers()
	{
		long sum = 0;
		int max = 4000000;//four million
		for (int i=1;;i++)
		{
			if (fibonacci(i)>max)
				break;
			if (fibonacci(i)%2==0)
				sum+=fibonacci(i);
		}
		return sum;
	}
	private long largestPrimeFactor()
	{
		long n = 600851475143L;
		long maxFactor = 0;
		for (int i = 2; i <= Math.round(Math.sqrt(n)); i++)
        {
                        // if the current number is prime and it is getting divided by the N variable
                        // without reminder , compare it to the max factor
            if (isPrime(i))
            {
                if (n % i == 0 && i > maxFactor)
                {
                    maxFactor = i;
                }
            }
        }
        return maxFactor;
	}
}
