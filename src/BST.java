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
		if ( node == null ){
			return 0;
		}
		return getHeight( node.left ) - getHeight( node.right );
	}
	
	private BSTNode<E> leftRotate( BSTNode<E> node){
		//temporary for rotation
		BSTNode<E> subLeft = node.left;
		BSTNode<E> subLeftRight = subLeft.right;
		//perform rotation
		subLeft.right = node;
		node.left = subLeftRight;
		//update heights
		node.height = Math.max( getHeight( node.left ), getHeight( node.right ) ) + 1;
		subLeft.height = Math.max( getHeight( subLeft.left ), getHeight( subLeft.right ) ) + 1;
		
		return subLeft; //new root
	}
	
	private BSTNode<E> rightRotate( BSTNode<E> node){
		//temporary for rotation
		BSTNode<E> subRight = node.right;
		BSTNode<E> subRightLeft = subRight.left;
		//perform rotation
		subRight.left = node;
		node.right = subRightLeft;
		//update heights
		node.height = Math.max( getHeight( node.left ), getHeight( node.right ) ) + 1;
		subRight.height = Math.max( getHeight( subRight.left ), getHeight( subRight.right ) ) + 1;
		
		return subRight; //new root
	}
	
	public void insert ( E item ){
		root = recInsert ( root, item);
	}
	
	private BSTNode<E> recInsert ( BSTNode<E> node, E newData ){
		if ( node == null ){
			node = new BSTNode<E>( newData );
		}
		else if ( newData.compareTo(node.data) < 0 ){
			node.left = recInsert ( node.left, newData );
		}
		else{
			node.right = recInsert ( node.right, newData );
		}
		node.height = Math.max( getHeight( node.left ), getHeight( node.right ) ) + 1;
		
		int balance = getBalance( node );
		
		//right tree is heavy
		if( balance == 2 && newData.compareTo( node.left.data ) < 0 ){
			return leftRotate( node );
		}
		//left tree is heavy
		if( balance == -2 && newData.compareTo( node.right.data ) > 0 ){
			return rightRotate( node );
		}
		
		if( balance == 2 && newData.compareTo( node.left.data ) > 0 ){
			node.left = rightRotate( node.left );
			return leftRotate( node );
		}
		if( balance == -2 && newData.compareTo( node.right.data ) < 0 ){
			node.right = leftRotate( node.right );
			return rightRotate( node );
		}
		
		return node;
		
	}
	
	/*
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree
	 * to determine the indentation of each item 
	 * @param output the string that accumulated the string representation
	 * of this BST
	 */
	private void preOrderPrint(BSTNode<E> tree, int level, StringBuilder output)
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
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right, level + 1, output);
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
	public String toStringTreeFormat() {
		StringBuilder s = new StringBuilder();

		preOrderPrint(root, 0, s);
		return s.toString();
	}

	public void remove ( E item ){
		
	}
	
	public boolean contains ( E item ){
		boolean truth = true;
		return truth;
	}
	
	@SuppressWarnings("hiding")
	protected class BSTNode <E extends Comparable <E> > {
		
		private E data;
		private BSTNode <E> left;
		private BSTNode <E> right;
		private int height;
		
		public BSTNode ( E data ){
			this.data = data;
			this.left = null;
			this.right = null;
			this.height = 1;
		}
		
		public int compareTo ( BSTNode <E> other){
			return this.data.compareTo(other.data);
		}
		
		
	}
	
}