import java.util.ArrayList;

/**
 * This class implements a binary search tree.
 * All of its methods are implemented recursively
 * @author Aaron Walker
 *
 */
public class AVLTree< E extends Comparable <E> > implements BSTInterface<E>{
	protected AVLNode<E> root;
	protected int size;
	
	public AVLTree(){
		this.root = null;
		this.size = 0;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getHeight ( AVLNode<E> node ){
		if ( node == null ){
			return 0;
		}
		return node.height;
	}
	
	public int getBalance ( AVLNode<E> node ){
		if ( node.right == null ){
			return -node.height;
		}
		if ( node.left == null ){
			return node.height;
		}
		return node.right.height - node.left.height;
	}
	
	private AVLNode<E> leftRotate( AVLNode<E> node ){//deal with LL Imbalance
		//temporary for rotation
		AVLNode<E> subLeft = node.left;
		//perform rotation
		
		node.left = subLeft.right;
		subLeft.right = node;
		
		updateHeight( node );
		updateHeight( subLeft );
		
		return subLeft; //new root
	}
	
	private AVLNode<E> rightRotate( AVLNode<E> node ){//deal with RR Imbalance
		//temporary for rotation
		AVLNode<E> subRight = node.right;
		
		//perform rotation
		node.right = subRight.left;
		subRight.left = node;
		
		//update heights
		updateHeight( node );
		updateHeight( subRight );
		
		return subRight; //new root
	}
	
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
	
	public void insert ( E data ){
		if( data != null ){
			root = insert ( root, data );
			size++;
		}
		
	}
	
	private AVLNode<E> insert ( AVLNode<E> node, E data ){
		if ( node == null ){
			node = new AVLNode<E>( data, null, null );
		}
		else if ( data.compareTo(node.data) < 0 ){
			node.left = insert ( node.left, data );
		}
		else{
			node.right = insert ( node.right, data );
		}
		
		updateHeight( node );
		node = balance( node );
		return node;
		
	}
	
	public void remove ( E data ){
		if( data != null ){
			root = remove( data, root );
			size--;
		}
	}
	
	private AVLNode<E> remove( E data, AVLNode<E> node ){
		if( node == null ){
			;
		}
		if( data.compareTo( node.data ) < 0 ){
			node.left = remove( data, node.left );
			updateHeight( node );
			return balance( node );
		}
		else if( data.compareTo( node.data ) > 0 ){
			node.right = remove( data, node.right );
			updateHeight( node );
			return balance( node );
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
				E predValue = getPredecessor( node.left );
				node.data = predValue;
				node.left = remove( predValue, node.left );
				updateHeight( node );
				return balance( node );
			}
		}

	}

	private AVLNode<E> balance( AVLNode<E> node ){
		
		if( getBalance(node) == -2 ){
			AVLNode<E> t = tallerSub( node.left, node.right );
			if( getBalance(t) <= 0 ){
				node = leftRotate( node );
			}
			else if (getBalance(t)== 1){
				node = leftRightRotate( node );
			}
		}
		if( getBalance( node ) == 2){
			AVLNode<E> t = tallerSub( node.left, node.right );
			if( getBalance(t) >= 1 ){
				node = rightRotate( node );
			}
			else if( getBalance(t)== -1 ){
				node = rightLeftRotate( node );
			}
		}
		return node;
	}
	
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
	
	private void updateHeight( AVLNode<E> node ){
		if( node.left == null && node.right == null ){
			node.height = 0;
		}
		else if( node.left == null ){
			node.height = node.right.height + 1;
		}
		else if( node.right == null ){
			node.height = node.left.height + 1;
		}
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
	
	private E getPredecessor( AVLNode<E> node ){
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
	
	private boolean contains( E data, AVLNode<E> node){
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