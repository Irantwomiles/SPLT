package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par;
	boolean justMade;
	
	BST_Node(String data){
		this.data=data;
		this.justMade = true;
	}
	
	BST_Node(String data, BST_Node left,BST_Node right, BST_Node par){ //feel free to modify this constructor to suit your needs
	    this.data=data;
	    this.left=left;
	    this.right=right;
	    this.par=par;
	    this.justMade=true;
	  }
	
	
	public String getData(){
		return data;
	}
	public BST_Node getLeft(){
		return left;
	}
	public BST_Node getRight(){
		return right;
	}
	
	public BST_Node containsNode(String s){ 
		if(data.equals(s)){
			splay(this);
			return this;
		}
		if(data.compareTo(s) < 0 ){
			if(right!=null){
			return right.containsNode(s);
			} else {
				splay(this);
				return this;
			}
		}
		if(data.compareTo(s) > 0){      //s  less than data
			if(left!=null){
			return left.containsNode(s);
			} else {
				splay(this);
				return this;
			}
		}
		
		return null; 
	}
	
	
	public BST_Node insertNode(String s){
		if (data.compareTo(s) < 0) {
			if (right != null){
				return right.insertNode(s);
			}
			right = new BST_Node(s, null, null, this);
			BST_Node root = right;
			splay(right);
			return root;
		}
		if (data.compareTo(s) > 0) {
			if (left != null){
				return left.insertNode(s);
			}
			left = new BST_Node(s, null, null, this);
			BST_Node root = left;
			splay(left);
			return root;
		}
		
		splay(this);
		return this;
		
	
	}
	public BST_Node findMin(){
		if(left == null){
			splay(this);
			return this;
			
		}else {
			return left.findMin();
		}
		
	}
	public BST_Node findMax(){
		if(right == null){
			splay(this);
			return this;
		}
		else {
			return right.findMax();
		}
		
	}
	public int getHeight(){
		int leftside = 0;
		int rightside = 0;
		if(left != null) {
			leftside += left.getHeight()+1;
		}
		if(right!=null) {
			rightside += right.getHeight()+1;
		}
		return Integer.max(leftside, rightside);
	}
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}
	
	private void rotateLeft(BST_Node node) {
		BST_Node parent = node.par;
		if (node.left != null) {
			node.left.par = parent;
		}
		if (parent.par != null) {
			if (parent == parent.par.left) {
				parent.par.left = node;
			} 
			else {
				parent.par.right = node;
			}
		}
		parent.right = node.left;
		node.left = parent;
		node.par = parent.par;
		parent.par = node;
		
		}
	
	private void rotateRight(BST_Node node) {
		BST_Node parent = node.par;
		if (node.right != null) {
			node.right.par = parent;
		}
		if (parent.par != null) {
			if (parent == parent.par.left) {
				parent.par.left = node;
				
			} 
			else {
				parent.par.right = node;
			}
		}
		
			parent.left = node.right;
			node.right = parent;
			node.par = parent.par;
			parent.par = node;	
		}

	
	
	public void splay(BST_Node toSplay) {
		while (toSplay.par != null) {
			BST_Node parent = toSplay.par;
			BST_Node grandParent = parent.par;
			
			if (grandParent == null) { 			//directly under the root
				if (toSplay != parent.left) { 		
					rotateLeft(toSplay);
				} else { 						
					rotateRight(toSplay);
				}
			} else if (toSplay == parent.left) { 	// left of parent
				if (parent != grandParent.left) {	
					rotateRight(toSplay);			// zig zag 
					rotateLeft(toSplay);
					
				} else { 						
					rotateRight(toSplay);
					rotateRight(toSplay);
				}
			} else if (toSplay == parent.right) {	//right of the parent
				if (parent != grandParent.right) { 	
					rotateLeft(toSplay);			// zag zig  
					rotateRight(toSplay);
					
				} else { 						
					rotateLeft(toSplay);
					rotateLeft(toSplay);
				}
			}
		}
	}
}