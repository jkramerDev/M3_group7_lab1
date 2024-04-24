package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
 
//test comment another and another
public class DiGraphImpl implements DiGraph{

	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {
		// If the node is null
		if (node == null) {
			// Return false
			return false;
		}
		
		// If node not in nodeList
		if (!nodeList.contains(node)) {
			// Add node to nodeList
			nodeList.add(node);
			// return true
			return true;
		}
		// return false
		return false;
	}

	@Override
	public Boolean removeNode(GraphNode node) {
		// If the node is null
		if (node == null) {
			// Return false
			return false;
		}
		
		// if node in nodeList
		if (nodeList.contains(node)) {
			// Remove node from nodeList
			nodeList.remove(node);
			// For each otherNode in nodeList
			for (GraphNode otherNode : nodeList) {
				// Remove edge between node and otherNode
				removeEdge(node, otherNode);
			}
			// Return true
			return true;
		}
		
		// Return false
		return false;
	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {
		// For all of the nodes in NodeList
		for (GraphNode vertex : nodeList) {
			// Find targetNode with node value
			if (vertex!= null && vertex.equals(node)) {
				// Set targetNode value to newNodeValue
				vertex.setValue(newNodeValue);
				// Return true
				return true;
			}
		}
		
		// Return false
		return false; 
	}

	@Override
	public String getNodeValue(GraphNode node) {
		// Check if the noeList contains the node
		if (nodeList.contains(node)) {
			// Return the value of the node
			return node.getValue();
		}
		
		// Return null
		return null;
	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {
		// If fromNode and toNode both exist in nodeList
		if (nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			// Add edge between fromNode and toNode with weight
			fromNode.addNeighbor(toNode, weight);
			// Return true
			return true;
		}
		else if (!nodeList.contains(fromNode) && !nodeList.contains(toNode)) {
			// Add fromNode and toNode to nodeList
			addNode(fromNode);
			addNode(toNode);
			// Add edge between fromNode and toNode with weight
			fromNode.addNeighbor(toNode, weight);
			// Return true
			return true;
		}
		
		// Return false
		return false;
	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {
		// If fromNode and toNode both exist in nodeList
		if (nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			// Remove edge between fromNode and toNode
			fromNode.removeNeighbor(toNode);
			// Return true
			return true;
		}
		
		// Return true
		return false;
	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {
		// If edge exists between fromNode and toNode
		if (getEdgeValue(fromNode, toNode) != null) {
			// Remove edge between fromNode and toNode
			removeEdge(fromNode, toNode);
			// Add edge between fromNode and toNode with newWeight
			addEdge(fromNode, toNode, newWeight);
			// Return true
			return true;
		}
		// Otherwise, return false
		return false;
	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		// Create a list of fromNode's neighbours
		List<GraphNode> neighbors  = fromNode.getNeighbors();
		
		// Iterate over each neighbour
		for (GraphNode neighbor : neighbors) {
			// If the neighbour is equal to toNode
			if (neighbor.equals(toNode)) {
				// Return the weight of the edge between fromNode and toNode
				return fromNode.getDistanceToNeighbor(neighbor);
			}
		}
		// Otherwise, return null
		return null;
	}

	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node) {
		// Create a list of node's neighbours 
		List<GraphNode> adjacent = node.getNeighbors();
		// Return the list of neighbours of a given node
		return adjacent;
	}

	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {
		// Create a list of fromNode's adjacent nodes
		List<GraphNode> fromAdjacent = getAdjacentNodes(fromNode);
		
		// If toNode is in the list of adjacent nodes
		if (fromAdjacent.contains(toNode)) {
			// Return true
			return true;
		}
		// Otherwise, return false
		return false;
	}

	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {
		// Create queue to manage nodes
		Queue<GraphNode> queue = new LinkedList<>();
				
		// Create set to manage visitedNodes
		Set<GraphNode> visitedNodes = new HashSet<>();
				
		// Start from the fromNode
		queue.add(fromNode);
				
		// Add fromNode to the list of visited nodes
		visitedNodes.add(fromNode);
		
		while (!queue.isEmpty()) {
			// Get the current node from the queue
			GraphNode element = queue.remove();
			
			// Create a list of the current node's neighbours 
			List<GraphNode> neighbours = element.getNeighbors();
			
			// For each neighbour in the list of neighbours
			for (GraphNode neighbour : neighbours) {
				// If the neighbour is not null and is not in the list of visited nodes
				if (neighbour != null && !visitedNodes.contains(neighbour)) {
					// Add the neighbour to the queue
					queue.add(neighbour);
					// Add the neighbour to the list of visited nodes
					visitedNodes.add(neighbour);

				}
				// If the value of the neighbour is equal to the value of the toNode
				if (neighbour.getValue().equals(toNode.getValue())) {
					// Return the true
					return true;
				}	
			}
		}
			
		// Otherwise, return false
		return false;
	}

	@Override
	public Boolean hasCycles() {
		// For each node in nodeList
		for (GraphNode g : nodeList) {
			// If the node is reachable from itself
			if (nodeIsReachable(g, g)) {
				// Return true
				return true;
			}
		}
		// Otherwise, return false
		return false;
	}

	@Override
	public List<GraphNode> getNodes() {
		// Return the list of nodes in the graph
		return nodeList;
	}

	@Override
	public GraphNode getNode(String nodeValue) {
		// For each node in nodeList
		for (GraphNode vertex : nodeList) {
			// If the value of the node equals nodeValue
			if (vertex.getValue().equals(nodeValue)) {
				// Return node
				return vertex;
			}
		}
		// Otherwise, return null
		return null;
	}

	@Override
	public int fewestHops(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		// Initialise variable to track the number of hops for each visited node
		int hops = 0;

		// Create queue to manage nodes
		Queue<GraphNode> queue = new LinkedList<>();
				
		// Create set to manage visitedNodes
		Set<GraphNode> visitedNodes = new HashSet<>();
				
		// Start from the fromNode
		queue.add(targetFromNode);
				
		// Add fromNode to the list of visited nodes
		visitedNodes.add(targetFromNode);
		
		while (!queue.isEmpty()) {
			// Update the number of hops
			hops ++;
			
			// Get the current node from the queue
			GraphNode element = queue.remove();
			
			// Create a list of the current node's neighbours 
			List<GraphNode> neighbours = element.getNeighbors();
			
			// For each neighbour in the list of neighbours
			for (GraphNode neighbour : neighbours) {
				// If the neighbour is not null and is not in the list of visited nodes
				if (neighbour != null && !visitedNodes.contains(neighbour)) {
					// Add the neighbour to the queue
					queue.add(neighbour);
					// Add the neighbour to the list of visited nodes
					visitedNodes.add(neighbour);

				}
				// If the value of the neighbour is equal to the value of the toNode
				if (neighbour.getValue().equals(targetToNode.getValue())) {
					// Return the number of hops
					return hops;
				}
						
			}
		}
			
		// Return the number of hops
		return hops;
	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		// Initialise distances for all nodes
		Map<GraphNode, Integer> distances = new HashMap<>();
		
		for (GraphNode node : nodeList) {
			distances.put(node, Integer.MAX_VALUE);
		}
		
		// Distance from fromNode to itself is zero
		distances.put(targetFromNode, 0);
		
		// Create a priority queue to store nodes based on their tentative distances
		PriorityQueue<GraphNode> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
		
		// Add fromNode to the queue
		queue.add(targetFromNode);
		
		// While the queue is not empty
		while (!queue.isEmpty()) {
			// Retrieve current node from queue
			GraphNode current = queue.remove();
			
			// If the current node is equal to the destination node
			if (current.equals(targetToNode)) { 
				// Return its distance
				return distances.get(current);
			}
			
			// Update distances for each neighbour
			for (GraphNode neighbour : current.getNeighbors()) {
				// Get the distance of the current node and add the distance between current and its neighbour
				int distanceToNeighbour = distances.get(current) + current.getDistanceToNeighbor(neighbour);
				// If the distance calculated above is less than the distance in distances
				if (distanceToNeighbour < distances.get(neighbour)) {
					// Update the distance associated with that neighbour using distanceToNeighbour
					distances.put(neighbour, distanceToNeighbour);
					// Add neighbour to the queue
					queue.add(neighbour);
				}
			}
		}
		// Otherwise, if the destination is unreachable, return -1
		return -1;
	}
	
}