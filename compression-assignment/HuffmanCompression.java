import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/******************************************************************************
 *  Compilation:  javac HuffmanCompression.java
 *
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 *  Typing "java HuffmanCompression compress < abra.txt"
 *  will print the compressed version of the input text
 *  
 *  Typing "Java HuffmanCompression compress < abra.txt > output.txt"
 *  will print the compressed file to a file called output.txt
 *  
 *  Typing "java HuffmanCompression decompress < output.txt"
 *  Will print the decompressed version of the text that was just compressed
 *  
 *  Typing "java HuffmanCompression compress < abra.txt | java BinaryDump 60"
 *  Will print information about binary details like how many bits etc
 *  
 ******************************************************************************/


/**
 *  Compress reads a sequence of 8 bit bytes from the standard input, then compresses them
 *  using huffman codes with an 8-bit alphabet, writes the results to standard input
 *  
 *  buildTrie builds the huffman trie with the given frequencies
 *  
 *  writeTrie writes the bitstring encoded trie to standard output
 *
 *  buildCode makes a lookup table from symbols and their encodings
 *  
 *  decompress reads a sequence of bits that represent a huffman compressed message from standard input, 
 *  expands then and writes the results to standard output
 *  
 *  
 */
public class HuffmanCompression {

	// alphabet size of extended ASCII
	private static final int R = 256;

	// Do not instantiate.
	private HuffmanCompression() { }

	// Huffman trie node
	private static class Node implements Comparable<Node> {
		private final char ch;
		private final int freq;
		private final Node left, right;

		Node(char ch, int freq, Node left, Node right) {
			this.ch    = ch;
			this.freq  = freq;
			this.left  = left;
			this.right = right;
		}

		// is the node a leaf node?
		private boolean isLeaf() {
			assert ((left == null) && (right == null)) || ((left != null) && (right != null));
			return (left == null) && (right == null);
		}

		// compare, based on frequency
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}

	/**
	 * Reads a sequence of 8-bit bytes from standard input; compresses them
	 * using Huffman codes with an 8-bit alphabet; and writes the results
	 * to standard output.
	 * @throws FileNotFoundException 
	 */
	public static void compress() throws FileNotFoundException {
		// read the input
		String stringInQuestion = BinaryStdIn.readString();

		char[] input = stringInQuestion.toCharArray();

		// tabulate frequency counts
		int[] frequency = new int[R];

		for (int i = 0; i < input.length; i++) {
			frequency[input[i]]++;
		}

		// build Huffman trie

		Node rootNode = buildTrie(frequency);
		// build code table

		String[] string = new String[R];

		buildCode(string, rootNode, "");

		// print trie for decoder

		writeTrie(rootNode);
		// print number of bytes in original uncompressed message
		BinaryStdOut.write(input.length);

		// use Huffman code to encode input
		for (int i = 0; i < input.length; i++) {
			String stringCode = string[input[i]];
			for (int j = 0; j < stringCode.length(); j++) {
				if (stringCode.charAt(j) == '0') {
					BinaryStdOut.write(false);
				}
				else if (stringCode.charAt(j) == '1') {
					BinaryStdOut.write(true);
				}
				else throw new IllegalStateException("State is not allowed, its illegal");
			}
		}
		BinaryStdOut.close();
	}


	/**
	 * Reads a sequence of bits that represents a Huffman-compressed message from
	 * standard input; expands them; and writes the results to standard output.
	 */
	public static void decompress() {

		// read in Huffman trie from input stream
		Node rootNode = readTrie();

		// number of bytes to write

		int intLength = BinaryStdIn.readInt();
		// decode using the Huffman trie

		for (int i = 0; i < intLength; i++) {
			Node z = rootNode;
			while (!z.isLeaf()) {
				boolean bitTrueFalse = BinaryStdIn.readBoolean();
				if (bitTrueFalse) {
					z = z.right;
				}
				else   {  z = z.left;
				}
			}
			BinaryStdOut.write(z.ch, 8);
		}
		BinaryStdOut.close();
	}

	// build the Huffman trie given frequencies
	private static Node buildTrie(int[] freq) {

		// initialze priority queue with singleton trees
		MinPQ<Node> pq = new MinPQ<Node>();
		for (char i = 0; i < R; i++)
			if (freq[i] > 0)
				pq.insert(new Node(i, freq[i], null, null));

		// special case in case there is only one character with a nonzero frequency
		if (pq.size() == 1) {
			if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
			else                 pq.insert(new Node('\1', 0, null, null));
		}

		// merge two smallest trees
		while (pq.size() > 1) {
			Node left  = pq.delMin();
			Node right = pq.delMin();
			Node parent = new Node('\0', left.freq + right.freq, left, right);
			pq.insert(parent);
		}
		return pq.delMin();
	}


	// write bitstring-encoded trie to standard output
	private static void writeTrie(Node x) {
		if (x.isLeaf()) {
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch, 8);
			return;
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);
	}

	// make a lookup table from symbols and their encodings
	private static void buildCode(String[] st, Node x, String s) {
		if (!x.isLeaf()) {
			buildCode(st, x.left,  s + '0');
			buildCode(st, x.right, s + '1');
		}
		else {
			st[x.ch] = s;
		}
	}



	private static Node readTrie() {
		boolean isLeaf = BinaryStdIn.readBoolean();
		if (isLeaf) {
			return new Node(BinaryStdIn.readChar(), -1, null, null);
		}
		else {
			return new Node('\0', -1, readTrie(), readTrie());
		}
	}

	/**
	 * Sample client that calls {@code compress()} if the command-line
	 * argument is "compress" an {@code decompress()} if it is "decompress".
	 *
	 * @param args the command-line arguments
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		   if (args[0].equals("compress")) {
			   compress();
			   }
	        else if (args[0].equals("decompress")) {
	        	decompress();
	        }
	        else throw new IllegalArgumentException("There is an illegal command line arguement");
	    
	}

}

