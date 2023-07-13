import java.util.Scanner;

public class GraphSOS {
    public String[][] adjMatrix;

    public GraphSOS(int row, int column){
        adjMatrix = new String[row][column];
    }

    public void solve(String[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return;
        if (matrix.length < 2 || matrix[0].length < 2)
            return;
        int m = matrix.length, n = matrix[0].length;
        //Any 'O' connected to a boundary can't be turned to 'X', so ...
        //Start from first and last column, turn 'O' to '*'.
        for (int i = 0; i < m; i++) {
            if (matrix[i][0].charAt(0) == 'O')
                boundaryDFS(matrix, i, 0);
            if (matrix[i][n-1].charAt(0) == 'O')
                boundaryDFS(matrix, i, n-1);
        }
        //Start from first and last row, turn '0' to '*'
        for (int j = 0; j < n; j++) {
            if (matrix[0][j].charAt(0) == 'O')
                boundaryDFS(matrix, 0, j);
            if (matrix[m-1][j].charAt(0) == 'O')
                boundaryDFS(matrix, m-1, j);
        }
        //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j].charAt(0) == 'O')
                    matrix[i][j] = "X";
                else if (matrix[i][j].charAt(0) == '*')
                    matrix[i][j] = "O";
            }
        }
    }
    //Use DFS algo to turn internal however boundary-connected 'O' to '*';
    private void boundaryDFS(String[][] matrix, int i, int j) {
        if (i < 0 || i > matrix.length - 1 || j <0 || j > matrix[0].length - 1)
            return;
        if (matrix[i][j].charAt(0) == 'O')
            matrix[i][j] = "*";
        if (i > 1 && matrix[i-1][j].charAt(0) == 'O')
            boundaryDFS(matrix, i-1, j);
        if (i < matrix.length - 2 && matrix[i+1][j].charAt(0) == 'O')
            boundaryDFS(matrix, i+1, j);
        if (j > 1 && matrix[i][j-1].charAt(0) == 'O')
            boundaryDFS(matrix, i, j-1);
        if (j < matrix[i].length - 2 && matrix[i][j+1].charAt(0) == 'O' )
            boundaryDFS(matrix, i, j+1);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int column = sc.nextInt();
        String empty = sc.nextLine();
        GraphSOS g = new GraphSOS(row, column);
        for(int i = 0; i < row; i++){
            String rows = sc.nextLine();
            String[] element = rows.split("[\s]");
            for (int j = 0; j < column; j++){
                g.adjMatrix[i][j] = element[j];
            }
        }
        g.solve(g.adjMatrix);
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                System.out.print(g.adjMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }
}
