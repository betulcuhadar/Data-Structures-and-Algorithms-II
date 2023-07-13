import java.util.*;

public class Delivery {

    public int V;
    Delivery(int v){
        V = v;
    }
    int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }


    void dijkstra(int graph[][], int src, int dest)
    {
        int dist[] = new int[V]; // The output array.

        Boolean sptSet[] = new Boolean[V];


        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, sptSet);
            sptSet[u] = true;


            for (int v = 0; v < V; v++)

                if (!sptSet[v] && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
        }
        System.out.println(dist[dest]);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int streets = sc.nextInt();
        int edges =sc.nextInt();
        String s = sc.nextLine();

        int graph[][] = new int[streets][streets] ;
        for(int i = 0; i < edges; i++){
            String con = sc.nextLine();
            String[] weight = con.split("\\s+");
            graph[weight[0].charAt(0) - 65][weight[1].charAt(0) - 65] = Integer.valueOf(weight[2]);
            graph[weight[1].charAt(0) - 65][weight[0].charAt(0) - 65] = Integer.valueOf(weight[2]);
        }
        for(int i = 0; i < 5; i++){
            Delivery t = new Delivery(streets);
            String street = sc.nextLine();
            String[] st = street.split("\\s+");
            t.dijkstra(graph, st[0].charAt(0) - 65, st[1].charAt(0) - 65);
        }

    }

}
