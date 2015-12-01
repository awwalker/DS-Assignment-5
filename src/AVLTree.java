/**
 * This class implements a AVL tree.
 * All of its methods are implemented recursively
 * @author Aaron Walker
 *
 */
public class AVLTree< E extends Comparable <E> > implements BSTInterface<E>{
	protected AVLNode<E> root;
	protected int size;
	
	/**
	 * Constructor
	 * No parameters initializes an AVL tree
	 * with a null root and size zero
	 */
	public AVLTree(){
		this.root = null;
		this.size = 0;
	}
	
	/** Return size of the AVL tree */
	public int getSize(){
		return size;
	}
	
	/** 
	 * Return height of a node
	 * Height is 0 if node is held by a 
	 * leaf
	 */
	public int getHeight ( AVLNode<E> node ){
		if ( node == null ){
			return 0;
		}
		return node.height;
	}
	
	/**
	 * Get the balance factor of a node i.e. the
	 * differences in heights between the left/right
	 * subtrees of a given node
	 * 
	 * @param node the node to check
	 * @return the current balance of a node
	 * must be between -1 and 1 to be a proper
	 * AVL tree
	 */
	public int getBalance ( AVLNode<E> node ){
		if ( node.right == null ){
			return -node.height;
		}
		if ( node.left == null ){
			return node.height;
		}
		//subtract left subtree height from right subtree height
		return node.right.height - node.left.height;
	}
	
	/**
	 * Rebalances the tree based on a left-left 
	 * imbalance at a given node
	 * 
	 * @param node the node where the imbalance occurs
	 * @return reference to the new root of the
	 * now balanced subtree
	 */
	private AVLNode<E> leftRotate( AVLNode<E> node ){//deal with LL Imbalance
		AVLNode<E> subLeft = node.left;
		
		//perform rotation
		node.left = subLeft.right;
		subLeft.right = node;
		
		//update heights
		updateHeight( node );
		updateHeight( subLeft );
		
		return subLeft; //new root
	}
	
	/**
	 * This rebalances a right right imbalance in a tree
	 * at a given node
	 * 
	 * @param node the node where the imbalance occurs
	 * @return a reference to the new root of the now
	 * balanced subtree
	 */
	private AVLNode<E> rightRotate( AVLNode<E> node ){//deal with RR Imbalance
		AVLNode<E> subRight = node.right;
		
		//perform rotation
		node.right = subRight.left;
		subRight.left = node;
		
		//update heights
		updateHeight( node );
		updateHeight( subRight );
		
		return subRight; //new root
	}
	
	/**
	 * This method rebalances a tree with a 
	 * left-right imbalance at a given node
	 * 
	 * @param node the node where the imbalance
	 * occurs
	 * @return reference to the new root of the now
	 * balanced subtree
	 */
	private AVLNode<E> leftRightRotate( AVLNode<E> node ){
		AVLNode<E> subLeft = node.left;
		AVLNode<E> subLeftRight = subLeft.right;
		
		node.left = subLeftRight.right;
		subLeft.right = subLeftRight.left;
		subLeftRight.left = subLeft;
		subLeftRight.right = node;
		
		updateHeight( node );
		updateHeight( subLeft );
		updateHeight( subLeftRight );
		
		return subLeftRight;
	}
	
	/**
	 * This method reblanaces a tree based on
	 * a right-left imbalance at a given node
	 * 
	 * @param node the node at which the imbalance
	 * occurs
	 * @return a reference to the root of the now
	 * balanced subtree
	 */
	private AVLNode<E> rightLeftRotate( AVLNode<E> node ){
		AVLNode<E> subRight = node.right;
		AVLNode<E> subRightLeft = subRight.left;
		
		node.right = subRightLeft.left;
		subRight.left = subRightLeft.right;
		subRightLeft.right = subRight;
		subRightLeft.left = node;
		
		updateHeight( node );
		updateHeight( subRight );
		updateHeight( subRightLeft );
		
		return subRightLeft;
	}
	
	/**
	 * This method adds an item to this
	 * AVL tree
	 * 
	 * @param data the data to be added to 
	 * the tree...unless its null
	 */
	public void insert ( E data ){
		if( data != null ){
			root = insert ( root, data );
			size++;
		}
		
	}
	
	/**
	 * Recursive method for adding an item to this 
	 * AVL tree
	 * 
	 * @param node root of the tree in which the new
	 * node will be added
	 * @param data the data to be added 
	 * @return new root of the tree with the data added
	 */
	private AVLNode<E> insert ( AVLNode<E> node, E data ){
		if ( node == null ){
			node = new AVLNode<E>( data, null, null );
		}
		//find where to add
		else if ( data.compareTo(node.data) < 0 ){
			node.left = insert ( node.left, data );
		}
		else{
			node.right = insert ( node.right, data );
		}
		//have to reblance
		updateHeight( node );
		node = balance( node );
		return node;
		
	}
	
	/**
	 * This method removes data from this AVL tree
	 * unless the data cannot be found or is null
	 * 
	 * @param data the data to be removed
	 */
	public void remove ( E data ){
		if( data != null ){
			root = remove( data, root );
			size--;
		}
	}
	
	/**
	 * This method recursively removes data from
	 * this AVL tree
	 * 
	 * @param data the data to be removed
	 * @param node the root of the tree from which
	 * to remove the data 
	 * @return the new root reference of the tree
	 * after the data has been removed
	 */
	private AVLNode<E> remove( E data, AVLNode<E> node ){
		if( node == null ){
			; //do nothing if root is null
		}
		if( data.compareTo( node.data ) < 0 ){
			node.left = remove( data, node.left );
			//rebalance
			updateHeight( node );
			return balance( node );
		}
		else if( data.compareTo( node.data ) > 0 ){
			node.right = remove( data, node.right );
			//rebalance
			updateHeight( node );
			return balance( node );
		}
		//found data
		else{
			//node is leaf
			if( node.left == null && node.right == null ){
				return null;
			}
			//only 1 child 
			if( node.left == null ){
				return node.right;
			}
			
			if( node.right == null ){
				return node.left;
			}
			//2 children can leave tree unbalanced
			else{
				//find the predecessor value (left subtree)
				E predValue = getPredecessor( node.left );
				//replace data of 'root' with predecessor value
				node.data = predValue;
				node.left = remove( predValue, node.left );
				//rebalance
				updateHeight( node );
				return balance( node );
			}
		}

	}
	
	/**
	 * This method balances this AVL tree at the provided
	 * node based on the four possible imbalances
	 * 
	 * @param node root of the subtree to rebalance
	 * @return reference to the new root of now balanced
	 * subtree
	 */
	private AVLNode<E> balance( AVLNode<E> node ){
		//imbalance on left subtree
		if( getBalance(node) == -2 ){
			AVLNode<E> t = tallerSub( node.left, node.right );
			//single or double rotation
			if( getBalance(t) <= 0 ){
				node = leftRotate( node );
			}
			else if (getBalance(t)== 1){
				node = leftRightRotate( node );
			}
		}
		//imbalance on right subtree
		if( getBalance( node ) == 2){
			AVLNode<E> t = tallerSub( node.left, node.right );
			//single or double rotation
			if( getBalance(t) >= 1 ){
				node = rightRotate( node );
			}
			else if( getBalance(t)== -1 ){
				node = rightLeftRotate( node );
			}
		}
		return node;
	}
	
	/**
	 * This method compares the heights of two AVL
	 * nodes and returns the taller one
	 * 
	 * @param left AVL node to be compared
	 * @param right AVL node to be compared
	 * @return reference to the taller of the two nodes
	 */
	private AVLNode<E> tallerSub( AVLNode<E> left, AVLNode<E> right ){
		if( left == null ){
			return right;
		}
		else if( right == null ){
			return left;
		}
		if( left.height >= right.height ){
			return left;
		}
		else{
			return right;
		}
	}
	
	/**
	 * This method changes the current 
	 * height of a node
	 * @param node the node to update
	 */
	private void updateHeight( AVLNode<E> node ){
		//node is a leaf
		if( node.left == null && node.right == null ){
			node.height = 0;
		}
		//node is part of a right tree
		else if( node.left == null ){
			node.height = node.right.height + 1;
		}
		//node is part of a left tree
		else if( node.right == null ){
			node.height = node.left.height + 1;
		}
		//node has both left and right subtrees
		else{
			node.height = Math.max( node.left.height, node.right.height ) + 1;
		}
	}
	

	/**
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree
	 * to determine the indentation of each item 
	 * @param output the string that accumulated the string representation
	 * of this BST
	 * 
	 * @author Joanna Klukowska
	 */
	private void postOrderPrint(AVLNode<E> tree, int level, StringBuilder output)
	{
		if (tree != null) {
			String spaces = "\n";
			if ( level > 0 ) {
				for( int i = 0; i < level - 1; i++ )
					spaces += "   ";
				spaces += "|--";
			}
			output.append( spaces );
			output.append( tree.data );
			postOrderPrint( tree.left, level + 1, output );
			postOrderPrint( tree.right, level + 1, output );
		}
		//uncomment the part below to show "null children" in the output 
//		else {
//			String spaces = "\n";
//			if (level > 0) {
//				for (int i = 0; i < level - 1; i++)
//					spaces += "   ";
//				spaces += "|--";
//			}
//			output.append(spaces);
//			output.append("null");
//		}
	}

	/**
	 * Produces tree like string representation of this BST.
	 * @return string containing tree-like representation of this BST. 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		postOrderPrint(root, 0, s);
		return s.toString();
	}
	
	/**
	 * This method gets the predecessor node of a passed
	 * in node.
	 * @param node the node to get the predecessor of
	 * @return the value held by the passed in nodes
	 * predecessor 
	 */
	private E getPredecessor( AVLNode<E> node ){
		while( node.right != null ){
			node = node.right;
		}
		return node.data;
	}
	
	/**
	 * Public wrapper method for the 
	 * contains( E data, AVLNode<E> node) method below
	 * 
	 * @param data the data to be checked for
	 * @return a boolean value representing the 
	 * presence of the data in the AVL tree
	 */
	public boolean contains( E data ){
		if( data != null ){
			return contains( data, root);
		}
		return false;
	}
	
	/**
	 * This method searches for a value in a 
	 * passed in tree
	 * @param data the value to search for
	 * @param node the root of an AVL tree or 
	 * subtree at which to start the search
	 * @return boolean value representing the 
	 * presence of the data in the AVL tree
	 */
	private boolean contains( E data, AVLNode<E> node ){
		//should just be like a binary search
		//reached end of tree and never found it
		if( node == null ){
			return false;
		}
		//check out left sub tree
		else if( data.compareTo( node.data ) < 0 ){
			return contains( data, node.left );
		}
		//check out right subtree
		else if( data.compareTo( node.data ) > 0 ){
			return contains( data, node.right );
		}
		//found it
		return true;
	}
	
	@SuppressWarnings("hiding")
	protected class AVLNode <E extends Comparable <E> > {
		
		private E data;
		private AVLNode <E> left;
		private AVLNode <E> right;
		private int height;
		
		public AVLNode ( E data ){
			this( data, null, null );
		}
		
		public AVLNode ( E data, AVLNode<E> left, AVLNode<E> right ){
			this.data = data;
			this.left = left;
			this.right = right;
			if( left == null && right == null ){
				this.height = 0;
			}
			else if( left == null ){
				this.height = 1 + right.height;
			}
			else if( right == null ){
				this.height = 1 + left.height;
			}
			else{
				this.height = 1 + Math.max( left.height, right.height );
			}
		}
		
		public int compareTo( AVLNode <E> other ){
			return this.data.compareTo( other.data );
		}
		
		public E getData(){
			return this.data;
		}
		
		public AVLNode<E> getLeft(){
			return this.left;
		}
		
		public AVLNode<E> getRight(){
			return this.right;
		}
	}
	
}