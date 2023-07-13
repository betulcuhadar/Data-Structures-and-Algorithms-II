import java.util.*;

public class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String str) {
        int n = str.length();
        TrieNode pChild = root;

        for (int i = 0; i < n; i++) {
            int index = str.charAt(i) - 'a';
            if (pChild.children[index] == null) {
                pChild.children[index] = new TrieNode();
            }
            pChild = pChild.children[index];
            pChild.count++;
        }
    }

    String commonPrefix() {
        TrieNode pChild = root;
        String prefix = "";

        while (pChild.count != 1) {
            System.out.println(pChild.count);
            boolean flag = false;
            for (int i = 0; i < 26; i++) {
                if( pChild.children[i] != null && pChild.children[i].count == 1) break;
                if (pChild.children[i] != null) {
                    prefix += (char)(i + 'a');
                    pChild = pChild.children[i];

                    System.out.println(prefix);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        return prefix;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            trie.insert(str);
        }
        String prefix = trie.commonPrefix();
        System.out.println("Longest common prefix: " + prefix);
        TrieNode pChild = trie.root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            pChild = pChild.children[index];
        }
        System.out.println("Number of strings: " + pChild.count);
    }
}
