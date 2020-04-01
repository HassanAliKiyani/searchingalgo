
public class Algo {
	
	static int chars = 50;
	
	static int max (int a, int b) { return (a > b)? a: b; }
	
	
	void Niv_Improved(String txt,String p) {
		int j,i=0;
		while(i<txt.length() - p.length()) {
			for(j=0;j<p.length();++j)
				if(txt.charAt(i+j)!= p.charAt(j))
					break;
			if(j == p.length())
				System.out.println("pattren at "+i);
			if(j!= 0)
				i+=j;
			else
				i++;
		}
	}
	
	static void Rabin_Karp(String p,String txt,int k) { //k is used for hashing preferable a prime number
		int lenp = p.length();
		int lentxt = txt.length();
		int i,j,l=0,t=0,h=1;
		for (i = 0; i < lenp-1; i++) 
			h = (h*chars)%k; 
		for (i = 0; i < lenp; i++) { 
			l = (chars*l + p.charAt(i))%k; 
			t = (chars*t + txt.charAt(i))%k; 
		} 
		for (i = 0; i <= lentxt - lenp; i++) 
		{ 
			if ( l == t ) { 
				for (j = 0; j < lenp; j++) {
					if (txt.charAt(i+j) != p.charAt(j)) 
						break; 
				} 
				if (j == lenp) 
					System.out.println("Pattern at" + i); 
			} 
			if ( i < lentxt-lenp ) { 
				t = (chars*(t - txt.charAt(i)*h) + txt.charAt(i+lenp))%k; 
				if (t < 0) 
				t = (t + k); 
			} 
		} 
	}
	
	void LPSArray(String p,int length,int [] LPS) {
		LPS[0] = 0;
		int j;
		for(int i = 0;i<length;++i) {
			j = LPS[i-1];
			while(j>0 && p.charAt(i)!=p.charAt(j)) 
				j = LPS[j-1];
			if(p.charAt(i) == p.charAt(j))
				j++;
			LPS[i] = j;
		}
		return ;
	}
	
	static void badChar(char []str, int size,int badchar[]){ 
	int i; 
	for (i = 0; i < chars; i++) 
		badchar[i] = -1; 
	for (i = 0; i < size; i++) 
		badchar[(int) str[i]] = i; 
	} 
	
	void Boyer_Moore(char txt[], char p[]) {
		int lengp = p.length; 
		int lengtxt = txt.length; 

		int badchar[] = new int[chars]; 
		badChar(p, lengp, badchar); 
		int s = 0; 
		while(s <= (lengtxt - lengp)) 
		{ 
			int j = lengp-1; 

			while(j >= 0 && p[j] == txt[s+j]) 
				j--; 
			if (j < 0) 
			{ 
				System.out.println("Patterns occur at shift = " + s); 

				s += (s+lengp < lengtxt)? lengp-badchar[txt[s+lengp]] : 1; 
			} 

			else
				s += max(1, j - badchar[txt[s+j]]); 
		} 
	}

	public static void main(String[] args) {
		String txt = "GEEKS FOR GEEKS"; 
		String pat = "GEEK"; 
		int q = 101; // A prime number 
		Rabin_Karp(pat, txt, q); 
		

	}

}
