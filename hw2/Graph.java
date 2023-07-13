import java.util.*;

public class Graph {

    private int V;

    // Adjacency List as ArrayList of ArrayList's
    private HashMap<Integer, ArrayList<Integer>> adj;

    // Constructor
    Graph()
    {
        adj = new HashMap<>();
    }

    void addEdge(int v, int w) {
        if(adj.containsKey(v)) adj.get(v).add(w);
        else {
            ArrayList list = new ArrayList();
            list.add(w);
            adj.put(v, list);
        }
        /*
        if(adj.containsKey(w)) adj.get(w).add(v);
        else {
            ArrayList list = new ArrayList();
            list.add(v);
            adj.put(w, list);
        }

         */
    }

    public boolean hasEdge(int source, int target){
        return adj.containsKey(source) && adj.get(source) != null && adj.get(source).contains(target);
    }

    public void checkCourse(int target, Stack<Integer> stack){
        for(int course : adj.keySet()){
            if(hasEdge(course, target)){
                stack.push(course);
                checkCourse(course, stack);
            }
        }
    }
    public void printCourses(int target){
        Stack<Integer> stack = new Stack<Integer>();
        checkCourse(target, stack);
        while (stack.empty() == false)
            System.out.print(stack.pop() + " ");
    }

    void topologicalSortUtil(int v, HashMap<Integer, Boolean> visited, Stack<Integer> stack) {
        // Mark the current node as visited.
        visited.put(v, true);


        // Recur for all the vertices adjacent
        // to thisvertex

        for(int i : adj.get(v)){
            System.out.println(i);
            if (!visited.get(i))
                topologicalSortUtil(i, visited, stack);
        }

        // Push current vertex to stack
        // which stores result
        stack.push(v);
    }

    void topologicalSort(int target, HashMap<Integer, Boolean> visited) {
        Stack<Integer> stack = new Stack<Integer>();

        // Mark all the vertices as not visited


        // Call the recursive helper
        // function to store
        // Topological Sort starting
        // from all vertices one by one
        topologicalSortUtil(target, visited, stack);
        if(stack.empty()) {
            System.out.println("There is no prerequisite for this course!");
            return;
        }
        // Print contents of stack
        while (stack.empty() == false)
            System.out.print(stack.pop() + " ");
    }
    public static void main(String args[])
    {
        // Create a graph given in the above diagram
        Graph g = new Graph();
        Scanner sc = new Scanner(System.in);
        int numOfCourses = sc.nextInt();
        String courses = sc.next();
        int numOfPreCourses = sc.nextInt();
        for(int j = 0; j < numOfPreCourses; j++){
            String preCourses = sc.next();
            String[] course = preCourses.split("[-]");
            int course1 = Integer.valueOf(course[0]);
            int course2 = Integer.valueOf(course[1]);
            g.addEdge(course1, course2);
        }
        String[] coursesVisited = courses.split("[,]");
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for(int i = 0; i < numOfCourses; i++){
            int cours = Integer.valueOf(coursesVisited[i]);
            visited.put(cours, false);

            System.out.println(cours + " + " + g.adj.get(cours));
        }
        int target = sc.nextInt();
        // Function Call

        //g.topologicalSort(target, visited);
        g.printCourses(target);
    }

}
