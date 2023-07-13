import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Maze {

    public static void findPath(char[][] arr, boolean[][] visited, Stack<Character> path, ArrayList<String> totalPaths, int y, int x){
        // base case
        if(arr == null || arr.length == 0) return;

        if(arr[y][x] == 'E'){
            path.push('E');
            String str = "";
            for (char c : path){
                str += c;
            }
            totalPaths.add(str);
            path.pop();
            return;
        }

        if(!visited[y][x] && Character.isLowerCase(arr[y][x])) {
            path.push(arr[y][x]);
            visited[y][x] = true;
        } else return;

        // move up
        if(y - 1 >= 0 && y - 1 < arr.length && x >= 0 && x < arr[0].length){
            findPath(arr, visited, path, totalPaths, y - 1, x);
        }

        // move left
        if(y >= 0 && y < arr.length && x - 1 >= 0 && x - 1 < arr[0].length){
            findPath(arr, visited, path,totalPaths,  y , x - 1);
        }

        // move right
        if(y >= 0 && y < arr.length && x + 1 >= 0 && x + 1 < arr[0].length){
            findPath(arr, visited, path, totalPaths, y, x + 1);
        }

        // move down
        if(x >= 0 && x < arr[0].length && y + 1 >= 0 && y + 1 < arr.length){
            findPath(arr, visited, path, totalPaths, y + 1, x);
        }

        path.pop();
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String st = sc.next();
        FileReader fr = new FileReader(st);
        BufferedReader br = new BufferedReader(fr);


        char[][] arr = new char[11][16];
        boolean[][] visited = new boolean[11][16];
        Stack<Character> path = new Stack<>();
        ArrayList<String> totalPaths = new ArrayList<>();

        for (int k = 0; k < 11; k++){
            String str = br.readLine();
            for (int j = 0; j < 16; j++){
                arr[k][j] = str.charAt(j);
                visited[k][j] = false;
            }
        }

        findPath(arr, visited, path, totalPaths,  1, 0);

        if(totalPaths.size() > 0) {
            System.out.println("" + totalPaths.size() + " treasures are found.");
            System.out.println("Paths are: ");
            for (int i = 0; i < totalPaths.size(); i++) {
                System.out.println("" + (i + 1) + ")" + totalPaths.get(i));
            }
        }
    }
}
