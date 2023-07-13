import java.util.*;

public class Traffic {
        private static int V;

        Traffic(int v){ V = v; }

        int findMinKey(int dist[], Boolean visited[])
        {
            int min = Integer.MAX_VALUE, min_index = -1;

            for (int v = 0; v < V; v++)
                if (visited[v] == false && dist[v] < min) {
                    min = dist[v];
                    min_index = v;
                }

            return min_index;
        }


        void printMST(int parent[], int graph[][])
        {
            HashMap<Integer, ArrayList<Integer>> map = new HashMap();
            int count = 0;
            int res = 0;
            for (int i = 1; i < V; i++) {
                res += graph[i][parent[i]];
                graph[i][parent[i]] = 0;
                graph[parent[i]][i] = 0;
            }
            System.out.println(res);
            for(int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (graph[i][j] != 0) {
                        count++;
                        graph[j][i] = 0;
                        if (!map.containsKey(i)) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(j);
                            map.put(i, list);
                        } else {
                            ArrayList<Integer> list = map.get(i);
                            list.add(j);
                            map.put(i, list);
                        }
                    }
                }
            }
            System.out.println(count);
            for(Integer street : map.keySet()){
                for(Integer destStreet : map.get(street)){
                    System.out.println((char)(street + 65)  + " " + (char) (destStreet + 65));
                }
            }

        }

        void primMST(int graph[][])
        {

            int parent[] = new int[V];

            int key[] = new int[V];

            Boolean visited[] = new Boolean[V];


            for (int i = 0; i < V; i++) {
                key[i] = Integer.MAX_VALUE;
                visited[i] = false;
            }
            key[0] = 0;

            parent[0] = -1;

            for (int count = 0; count < V - 1; count++) {

                int u = findMinKey(key, visited);

                visited[u] = true;
                for (int v = 0; v < V; v++)

                    if (graph[u][v] != 0 && visited[v] == false
                            && graph[u][v] < key[v]) {
                        parent[v] = u;
                        key[v] = graph[u][v];
                    }
            }

            printMST(parent, graph);
        }

        public static void main(String[] args)
        {

            Scanner sc = new Scanner(System.in);
            int streets = sc.nextInt();
            int edges =sc.nextInt();
            String s = sc.nextLine();
            Traffic t = new Traffic(streets);

            int graph[][] = new int[streets][streets] ;
            for(int i = 0; i < edges; i++){
                String con = sc.nextLine();
                String[] weight = con.split("\\s+");
                graph[weight[0].charAt(0) - 65][weight[1].charAt(0) - 65] = Integer.valueOf(weight[2]);
                graph[weight[1].charAt(0) - 65][weight[0].charAt(0) - 65] = Integer.valueOf(weight[2]);
            }
            String dest = sc.nextLine();

            t.primMST(graph);
        }
    }
