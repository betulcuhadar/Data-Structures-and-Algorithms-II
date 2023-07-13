public class Node {
    public char data;
    public Node north;
    public Node south;
    public Node west;
    public Node east;

    public Node(char c){
        this.data= c;
    }

    public static void main(String[] args) {
        char val = '=';
        System.out.println("Character: "+val);
        if (Character.isLowerCase(val)) {
            System.out.println("Character is in Lowercase!");
        } else {
            System.out.println("Character is in Uppercase!");
        }
    }
}
