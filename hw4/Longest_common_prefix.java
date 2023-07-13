import java.util.Scanner;

public class Longest_common_prefix {

    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;

    //  node
    static class Node
    {
        Node[] children = new Node[ALPHABET_SIZE];

        // isLeaf is true if the node represents
        // end of a word
        boolean isLeaf;

        // constructor
        public Node() {
            isLeaf = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };

    static Node root;
    static int indexs;

    // If not present, inserts the key into the
    // If the key is a prefix of  node, just marks
    // leaf node
    static void insert(String key)
    {
        int length = key.length();
        int index;

        Node pCrawl = root;

        for (int level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new Node();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isLeaf = true;
    }

    // Counts and returns the number of children of the
    // current node
    static int countChildren(Node node)
    {
        int count = 0;
        for (int i=0; i<ALPHABET_SIZE; i++)
        {
            if (node.children[i] != null)
            {
                count++;
                indexs = i;
            }
        }
        return (count);
    }

    // Perform a walk on the  and return the
    // longest common prefix string
    static String walk()
    {
        Node pCrawl = root;
        indexs = 0;
        String prefix = "";

        while (countChildren(pCrawl) == 1 &&
                pCrawl.isLeaf == false)
        {
            pCrawl = pCrawl.children[indexs];
            prefix += (char)('a' + indexs);
        }
        return prefix;
    }

    // A Function to construct
    static void construct(String arr[], int n)
    {
        for (int i = 0; i < n; i++)
            insert (arr[i]);
        return;
    }

    // A Function that returns the longest common prefix
    // from the array of strings
    static String commonPrefix(String arr[], int n)
    {
        root = new Node();
        construct(arr, n);

        // Perform a walk on the
        return walk();
    }

    // Driver program to test above function
    public static void main(String args[])
    {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String arr[] = new String[n];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            arr[i] = str;
        }

        String ans = commonPrefix(arr, n);

        if (ans.length() != 0)
            System.out.println("The longest common prefix is "+ans);
        else
            System.out.println("There is no common prefix");
    }
}
