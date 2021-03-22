
public class BruteForceAlgorithm {

	public int substringSearch(String txt, String pat) {
		int n = txt.length();
		int m = pat.length();
		for (int i = 0; i <= n-m; i++) {
			int j = 0;
			for(j=0;j<m;j++) {
				if(txt.charAt(i+j) !=  pat.charAt(j)) {
					break;
				}
			}
			if (j==m) {
				return i;
			}
		}

		return -1;
	}
	public static void main(String[] args) {
		BruteForceAlgorithm a = new BruteForceAlgorithm();
		System.out.println(a.substringSearch("ABCDEFGHI", "CDF"));
	}

}
