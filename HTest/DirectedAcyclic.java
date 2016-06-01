import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.io.*;

public class DirectedAcyclic<V> {
    
    
    private Map<V,List<V>> neighbors = new HashMap<V,List<V>>();
    
    // String representation of graph
    public String toString () {
        StringBuffer s = new StringBuffer();
        for (V v: neighbors.keySet()) s.append("\n    " + v + " -> " + neighbors.get(v));
        return s.toString();                
    }
    
    // Adds a vertex. If already present, does nothing.
    public void add (V vertex) {
        if (neighbors.containsKey(vertex)) return;
        neighbors.put(vertex, new ArrayList<V>());
    }
    
    
    
    // Adds an edge to the graph. If either of the source or
    // destination is absent, adds it.
    public void add (V from, V to) {
        this.add(from); this.add(to);
        neighbors.get(from).add(to);
    }
    
    // Main function
    public static void main (String[] args)throws IOException {
        // Initializes an empty graph
        DirectedAcyclic<String> graph = new DirectedAcyclic<String>();
       
        boolean permission = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (permission == true) {
           
            System.out.println("Enter the souce node");
            String source = br.readLine();
            System.out.println("Enter the destination node");
            String destination = br.readLine();
            
            graph.add(source, destination);
            System.out.println("Would you like to continue? Type yes or no.");
            if (br.readLine().compareTo("yes") == 0) {
                permission = true;
            } else {
                permission = false;
            }
        }
        
        
        System.out.println("The directed graph is as follows:");
        System.out.println(graph);
    }
}