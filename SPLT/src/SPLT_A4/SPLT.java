package SPLT_A4;

public class SPLT implements SPLT_Interface {
	public BST_Node root;
	int size;
	  
	public SPLT(){
		size = 0;
		root = null;
	}
	  
	@Override
	//used for testing, please leave as is
	public BST_Node getRoot(){
		return root;
	}

	@Override
	public void insert(String s) { 
		if (size == 0){
			root = new BST_Node(s);		//make root
		} else {
			root = root.insertNode(s); 
		}
		if (root.justMade){
			size ++;					
			root.justMade = false;
		}
		
	}

	@Override
	public void remove(String s) {  
		if (root == null){

		}
		if (contains(s)) {
			if (root.left == null){
				root = root.right;
			} else {
				BST_Node rightNode = root.right;
				if (rightNode != null){
					root = root.left.findMax();
				} else {
					root = root.left;
				}
				if (rightNode != null) {
					root.right = rightNode;
				}
				if (root.right != null){
					root.right.par = root;
				}
			}
			if (root != null) {
				root.par = null;
			}
			size --;
		}
		
		
	}
	
	@Override
	public String findMin() {
		if (size != 0) {
			root = root.findMin();
			return root.data;
		} else {
			return null;
		}
	}

	@Override
	public String findMax() {
		if (size != 0 ) {
			root = root.findMax();
			return root.data;
		} else {
			return null;
		}
	}

	@Override
	public boolean empty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		
		if (size == 0) {
			return false;
		}
		root = root.containsNode(s);
		return root.data.equals(s);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if (size == 0) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

	
}