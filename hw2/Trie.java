import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    TrieNode() {
        children = new TrieNode[2];
        isEnd = false;
    }
}

class Trie {
    TrieNode root;
    Trie() {
        root = new TrieNode();
    }

    void insert(int[] num) {
        TrieNode curr = root;
        for (int i = 0; i < num.length; i++) {
            int index = num[i];
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEnd = true;
    }

    int[] findMinXOR(int[] mask) {
        TrieNode curr = root;
        int[] res = new int[mask.length];
        for (int i = 0; i < mask.length; i++) {
            int index = mask[i] ^ 1;
            int min0 = 2;
            int min1 = 2;
            if(curr.children[0] != null) min0 = mask[i] ^ 0;
            if(curr.children[1] != null) min1 = mask[i] ^ 1;
            int min = Math.min(min0, min1);
            res[i] = min;
            System.out.println(min);
            System.out.println(curr);
            curr = curr.children[min];
            /*
            System.out.println(curr.children[index]);
            if (curr.children[index] != null) {
                res[i] = index;
                curr = curr.children[index];
            } else {
                res[i] = mask[i];
                curr = curr.children[mask[i]];
            }
             */
        }
        return res;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][9];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            for(int j = 0;j < str.length(); j++){
                nums[i][j] = Integer.valueOf(str.charAt(j) + "");
            }
        }
        String s = sc.nextLine();
        int[] mask = new int[9];
        for(int j = 0;j < s.length(); j++){
            mask[j] = Integer.valueOf(s.charAt(j) + "");
        }

        Trie trie = new Trie();
        for (int[] num : nums) {
            trie.insert(num);
        }
        int[] res = trie.findMinXOR(mask);
        for (int i : res) {
            System.out.print(i);
        }
        System.out.println();
    }
}
