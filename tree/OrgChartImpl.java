package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

 
public class OrgChartImpl implements OrgChart{

	//Employee is your generic 'E'..
	private List<GenericTreeNode<Employee>> nodes = new ArrayList<>();

	@Override
	public void addRoot(Employee e) {
		GenericTreeNode<Employee> rootEmployee = new GenericTreeNode<Employee>(e);
		nodes.add(rootEmployee);
	}

	@Override
	public void clear() {
		// Clear nodes list
		nodes.clear();
	}

	@Override
	public void addDirectReport(Employee manager, Employee newPerson) {
		// Assuming the manager for NewPerson is in the list, loop through each of the nodes
		for (int i = 0; i < nodes.size(); i++) {
			// Get the currentEmployee
			GenericTreeNode<Employee> currentEmployee = nodes.get(i);
			
			// If currentEmployee data is equal to the data of the manager
			if (currentEmployee.data.equals(manager)) {
				// Create a new node out of this newPerson
				GenericTreeNode<Employee> newE = new GenericTreeNode<Employee>(newPerson);
				
				// Add child to the current employee's (manager's) list of children
				currentEmployee.addChild(newE);
				
				// Add the new node to the list of nodes
				nodes.add(newE);
				break;
			}
		}
	}

	@Override
	public void removeEmployee(Employee firedPerson) {
		// Iterate over each node in nodes
		for (int index = 0; index < nodes.size(); index++) {
			GenericTreeNode<Employee> node = nodes.get(index);
			// If the data of node is equal to firedPerson
			if (node.data.equals(firedPerson)) {
				// Find the supervisor node
				GenericTreeNode<Employee> supervisor = findSupervisor(node);
				// If supervisor is not null
				if (supervisor != null) {
					// Add the children of notToRemove to supervisors children
					supervisor.children.addAll(node.children);
					// Remove firedPerson from supervisor's children
					supervisor.removeChild(firedPerson);
				}
				// Remove node from nodes list
				nodes.remove(node);
				break;
			}
		}
	}
	
	private GenericTreeNode<Employee> findSupervisor(GenericTreeNode<Employee> employeeNode) {
		// Iterate over the list of node to find the supervisor
		for (GenericTreeNode<Employee> node : nodes) {
			// If the children of the node contains employeeNode
			if (node.children.contains(employeeNode)) {
				// The supervisor has been found, so return it
				return node;
			}
		}
		// Otherwise, if no supervisor is found, return null
		return null;
	}

	@Override
	public void showOrgChartDepthFirst() {
		if (nodes == null) {
			return;
		}
		// Start the recursive run from the root node
		showOrgChartDepthFirst(nodes.get(0), 0);
		
	}
	
	public void showOrgChartDepthFirst(GenericTreeNode<Employee> node, int index) {
		// If node is null
		if (node == null) {
			return;
		}
		
		// Display the current node's data
		System.out.println(node.data);
		
		// Sort the children
		sortChildren(node.children);

        for (GenericTreeNode<Employee> child : node.children) {
        	// Recursively call the function with the child and the next index
        	showOrgChartDepthFirst(child, index + 1);
        }
		
	}

	@Override
	public void showOrgChartBreadthFirst() {
		// Check if list of nodes is empty
		if (nodes.get(0) == null) {
			return;
		}
		
		// Create an empty queue and an empty list of explored employees
		Queue<GenericTreeNode<Employee>> queue = new LinkedList<>();
		List<GenericTreeNode<Employee>> explored = new ArrayList<>();
		
		// For each node in the list of nodes
		
		// Add the root to the queue
		queue.add(nodes.get(0)); 
			
		// While the queue is not empty
		while (!queue.isEmpty()) {
			// Process the node and its children
			GenericTreeNode<Employee> employee = queue.remove();
			
			// Display the data of the current node
			System.out.println(employee.data);
				
			// Update the explored employees list
			explored.add(employee);
				
			// Add the children of the employee to the queue
			for (GenericTreeNode<Employee> child : employee.children) {
				queue.add(child);
			}
				
		}
	}
	
	private void sortChildren(List<GenericTreeNode<Employee>> children) {
		Collections.sort(children, new Comparator<GenericTreeNode<Employee>>() {
			@Override
			public int compare(GenericTreeNode<Employee> node1, GenericTreeNode<Employee> node2) {
				return node1.data.getId().compareTo(node2.data.getId());
			}
		});
	}
}
