package graph;

 

public class GraphTester {

	private static DiGraph graph = new DiGraphImpl();
	
	public static void main(String[] args) {
	
		// Add nodes
		TESTaddNode("A");
		TESTaddNode("B");
		TESTaddNode("E");
		TESTaddNode("C");
		TESTaddNode("D");
		TESTaddNode("Kramer");
		TESTaddNode("F");
		TESTaddNode("G");
		System.out.println();


		// Add edges				
		TESTaddEdge("A", "B", 1);
		TESTaddEdge("B", "C", 2);
		TESTaddEdge("B", "D", 5);
		TESTaddEdge("C", "D", 1);
		TESTaddEdge("D", "E", 12);
		TESTaddEdge("E", "F", 2);
		TESTaddEdge("F", "G", 5);
		System.out.println();

		// Describe		
		TESTdescribeGraph();
		System.out.println();

		// Test reachablity		
		TESTnodeIsReachable("A", "B");
		TESTnodeIsReachable("A", "C");
		TESTnodeIsReachable("A", "D");
				
		TESTnodeIsReachable("B", "A");
		TESTnodeIsReachable("B", "C");
		TESTnodeIsReachable("B", "D");
				
		TESTnodeIsReachable("C", "A");
		TESTnodeIsReachable("C", "B");
		TESTnodeIsReachable("C", "D");
				
		TESTnodeIsReachable("D", "A");
		TESTnodeIsReachable("D", "B");
		TESTnodeIsReachable("D", "C");
		System.out.println();
				
		// Test hasCycles
		TESThasCycles();
		System.out.println();

		// Test fewest hops
		TESTfewestHops("B", "D");
		TESTfewestHops("A", "B");
		TESTfewestHops("C", "G");
		System.out.println();
				
		// Test shortest path
		TESTshortestPath("B", "C");
		TESTshortestPath("B", "D");
		TESTshortestPath("A", "G");
		System.out.println();
			
	}
	
	private static void TESTfewestHops(String nodeFrom, String nodeTo) {
		
		System.out.println("Fewest hops in the graph from  " + nodeFrom + " to " + nodeTo + ": " + graph.fewestHops(graph.getNode(nodeFrom), graph.getNode(nodeTo)));
		
	}

	private static void TESTshortestPath(String nodeFrom, String nodeTo) {

		System.out.println("From [" + nodeFrom + "] to [" + nodeTo + "], the graph has a shortest path of " + graph.shortestPath(graph.getNode(nodeFrom), graph.getNode(nodeTo)));
		
		
	}
	
	public static void TESThasCycles() {

		System.out.println("The graph has " + (graph.hasCycles() ? " " : "NO") + " cycles");

	}

	public static void TESTaddEdge(String from, String to, Integer weight) {

		say("Attempting to addEdge [" + from + "] to [" + to + "] with weight [" + weight + "]");
		say(graph.addEdge(graph.getNode(from), graph.getNode(to), weight) ? "SUCCESS" : "FAIL");

	}
	
	public static void TESTaddNode(String value) {
		say("Attempting to addNode [" + value + "]");
		say(graph.addNode(new GraphNode(value)) ? "SUCCESS" : "FAIL");

	}

	public static void TESTnodeIsReachable(String nodeFrom, String nodeTo) {

		System.out.println("There is " + (graph.nodeIsReachable(graph.getNode(nodeFrom), graph.getNode(nodeTo)) ? "a" : "NO")
				+ " path from [" + nodeFrom + "] to [" + nodeTo + "]");

	}

	public static void TESTdescribeGraph() {

		for(GraphNode thisNode : graph.getNodes()) {
			System.out.println("Node [" + thisNode.getValue() + "] ");
			
				if(thisNode.getNeighbors().isEmpty()) {
					System.out.println("has no outgoing connections");
					
				}else {
					System.out.println();
					for (GraphNode neighbor : thisNode.getNeighbors()) {
						System.out.println("is connected to [" + neighbor.getValue() + "]");
					}
	
				}
				
				System.out.println();
				
		}
		
	}

	public static void say(String s) {

		System.out.println(s);
		
	}
	
}
