
public class Tower_Of_Hanoi {
	static void towerOfHanoi(int disk,char source,char dest,char auxiliary){
		//base case for if there is only one disk
		if(disk == 1) {
			System.out.println("Move disk 1 from rod "+ 
					source+" to rod "+ dest); 
			return; 
		}
		//recursive call
		towerOfHanoi(disk - 1, source, auxiliary, dest); 
		System.out.println("Move disk "+ disk + " from rod " + 
				source +" to rod " + dest ); 
		towerOfHanoi(disk - 1, auxiliary, dest, source); 
	}
	public static void main(String[] args) {

		// Number of disks to start the implementation
		int n = 4; 

		//function call
		towerOfHanoi(n, 'A', 'C', 'B');
	}

}
