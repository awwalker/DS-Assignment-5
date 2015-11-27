/**
 * This class implements a binary search tree.
 * All of its methods are implemented recursively
 * @author Aaron Walker
 *
 */
public class BST<E extends Comparable <E> > implements BSTInterface<E>{
	private BSTNode<E> root;
	
	public BST(){
		this.root = null;
	}
	public int getHeight ( BSTNode<E> node){
		if ( node == null ){
			return 0;
		}
		return node.height;
	}
	
	public int getBalance ( BSTNode<E> node){
		if ( node.right == null ){
			return -node.height;
		}
		if ( node.left == null ){
			return node.height;
		}
		return node.right.height - node.left.height;
	}
	
	private BSTNode<E> leftRotate( BSTNode<E> node){//deal with LL Imbalance
		//temporary for rotation
		BSTNode<E> subLeft = node.left;
		//perform rotation
		
		node.left = subLeft.right;
		subLeft.right = node;
		
		updateHeight(node);
		updateHeight(subLeft);
		
		return subLeft; //new root
	}
	
	private BSTNode<E> rightRotate( BSTNode<E> node){//deal with RR Imbalance
		//temporary for rotation
		BSTNode<E> subRight = node.right;
		
		//perform rotation
		node.right = subRight.left;
		subRight.left = node;
		
		//update heights
		updateHeight(node);
		updateHeight(subRight);
		
		return subRight; //new root
	}
	
	private BSTNode<E> leftRightRotate( BSTNode<E> node){
		BSTNode<E> subLeft = node.left;
		BSTNode<E> subLeftRight = subLeft.right;
		
		node.left = subLeftRight.right;
		subLeft.right = subLeftRight.left;
		subLeftRight.left = subLeft;
		subLeftRight.right = node;
		
		updateHeight(node);
		updateHeight(subLeft);
		updateHeight(subLeftRight);
		
		return subLeftRight;
	}
	
	private BSTNode<E> rightLeftRotate( BSTNode<E> node){
		BSTNode<E> subRight = node.right;
		BSTNode<E> subRightLeft = subRight.left;
		
		node.right = subRightLeft.left;
		subRight.left = subRightLeft.right;
		subRightLeft.right = subRight;
		subRightLeft.left = node;
		
		updateHeight(node);
		updateHeight(subRight);
		updateHeight(subRightLeft);
		
		return subRightLeft;
	}
	
	public void insert ( E item ){
		root = insert ( root, item);
	}
	
	private BSTNode<E> insert ( BSTNode<E> node, E newData ){
		if ( node == null ){
			node = new BSTNode<E>( newData, null, null );
		}
		else if ( newData.compareTo(node.data) < 0 ){
			node.left = insert ( node.left, newData );
		}
		else{
			node.right = insert ( node.right, newData );
		}
		
		updateHeight(node);
		node = balance(node);
		return node;
		
	}
	
	public void remove ( E data ){
		if( data != null ){
			root = remove( data, root );
		}
	}
	
	private BSTNode<E> remove( E data, BSTNode<E> node ){
		if( node == null ){
			;
		}
		if( data.compareTo( node.data ) < 0 ){
			node.left = remove( data, node.left );
			updateHeight(node);
			return balance(node);
		}
		else if ( data.compareTo( node.data ) > 0 ){
			node.right = remove( data, node.right );
			updateHeight(node);
			return balance(node);
		}
		else{
			if( node.left == null && node.right == null ){
				return null;
			}
			
			if( node.left == null ){
				return node.right;
			}
			
			if( node.right == null ){
				return node.left;
			}
			
			else{
				E predValue = getPredecessor(node.left);
				node.data = predValue;
				node.left = remove( predValue, node.left );
				updateHeight(node);
				return balance(node);
			}
		}

	}

	private BSTNode<E> balance( BSTNode<E> node){
		
		if( getBalance(node) == -2){
			BSTNode<E> t = tallerSub(node.left, node.right);
			if(getBalance(t) == -1 || getBalance(t) == 0){
				node = leftRotate(node);
			}
			else if (getBalance(t)== 1){
				node = leftRightRotate(node);
			}
		}
		if( getBalance( node ) == 2){
			BSTNode<E> t = tallerSub(node.left, node.right);
			if(getBalance(t) == 0 || getBalance(t) == 1){
				node = rightRotate(node);
			}
			else if(getBalance(t)== -1 ){
				node = rightLeftRotate(node);
			}
		}
		return node;
	}
	
	private BSTNode<E> tallerSub( BSTNode<E> left, BSTNode<E> right){
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
	
	private void updateHeight(BSTNode<E> node){
		if(node.left == null && node.right == null){
			node.height = 0;
		}
		else if(node.left == null){
			node.height = node.right.height + 1;
		}
		else if(node.right == null){
			node.height = node.left.height + 1;
		}
		else{
			node.height = Math.max(node.left.height, node.right.height) + 1;
		}
	}
	/*
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree
	 * to determine the indentation of each item 
	 * @param output the string that accumulated the string representation
	 * of this BST
	 */
	private void postOrderPrint(BSTNode<E> tree, int level, StringBuilder output)
	{
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.data);
			postOrderPrint(tree.left, level + 1, output);
			postOrderPrint(tree.right, level + 1, output);
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
	
	private E getPredecessor( BSTNode<E> node ){
		while( node.right != null ){
			node = node.right;
		}
		return node.data;
	}
	
	public boolean contains ( E data ){
		if ( data != null ){
			return contains( data, root);
		}
		return false;
	}
	
	private boolean contains( E data, BSTNode<E> node){
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
	protected class BSTNode <E extends Comparable <E> > {
		
		protected E data;
		protected BSTNode <E> left;
		protected BSTNode <E> right;
		protected int height;
		
		public BSTNode ( E data ){
			this(data, null, null);
		}
		
		public BSTNode ( E data, BSTNode<E> left, BSTNode<E> right){
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
				this.height = 1 + Math.max(left.height, right.height);
			}
		}
		
		public int compareTo ( BSTNode <E> other){
			return this.data.compareTo(other.data);
		}
		
	}
	
}