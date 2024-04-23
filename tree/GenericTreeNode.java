package tree;
import java.util.ArrayList;

public class GenericTreeNode<E> {
	E data;
	//<some list of children>
	//ArrayList<GenericTreeNode<E>> children;
	ArrayList<GenericTreeNode<E>> children = new ArrayList<>();
	
	public GenericTreeNode(E theItem) {
		data = theItem;
	}
	
	public void addChild(GenericTreeNode<E> theItem) {
		children.add(theItem);
	}
	
	public void removeChild(E theItem) {
		// Iterate over the children in reverse order
		for (int index = 0; index < children.size(); index++) {
			// Get the child at the specified index
			GenericTreeNode<E> child = children.get(index);
			// If the child's data is equal to theItem
			if (child.data.equals(theItem)) {
				// Remove the child from the list of children
				children.remove(index);
				// Exit the loop
				break;
			}
		}
	}
} 
