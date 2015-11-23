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
	
	public void insert ( E item ){
		root = recInsert ( root, item);
	}
	
	private BSTNode<E> recInsert ( BSTNode<E> node, E newData ){
		if ( node == null ){
			BSTNode<E> newNode = new BSTNode<E>( newData );
			return newNode;
		}
		if ( newData.compareTo(node.data) < 0 ){
			node.left = recInsert ( node.left, newData );
		}
		else{
			node.right = recInsert ( node.right, newData );
		}
		node.height = node.max( node.getHeight( node.left ), node.getHeight( node.right ) ) + 1;
		
		int balance = node.getBalance( node );
		
		if( balance > 1 && newData.compareTo( node.left.data ) < 0 ){
			return node.rightRotate( node );
		}
		if( balance < -1 && newData.compareTo( node.right.data ) > 0 ){
			return node.leftRotate( node );
		}
		if( balance > 1 && newData.compareTo( node.left.data ) > 0 ){
			node.left = node.leftRotate( node.left );
			return node.rightRotate( node );
		}
		if( balance < -1 && newData.compareTo( node.right.data ) < 0 ){
			node.right = node.rightRotate( node.right );
			return node.leftRotate( node );
		}
		
		return node;
		
		
	}
	

	public void remove ( E item ){
		
	}
	
	public boolean contains ( E item ){
		boolean truth = true;
		return truth;
	}
	
	private int recSize (BSTNode<E> root){
		if(root == null){
			return 0;
		}
		else{
			return recSize(root.left) + recSize(root.right) + 1;
		}
	}
	
	public int size(){
		return recSize(root);
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
		
		public int max (int a, int b){
			return ( a > b ) ? a : b; 
		}
		
		public int compareTo ( BSTNode <E> other){
			return this.data.compareTo(other.data);
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
		
		private BSTNode<E> rightRotate( BSTNode<E> node){
			//temporary for rotation
			BSTNode<E> subLeft = node.left;
			BSTNode<E> subLeftRight = subLeft.right;
			//perform rotation
			subLeft.right = node;
			node.left = subLeftRight;
			//update heights
			node.height = max( getHeight( node.left ), getHeight( node.right ) ) + 1;
			subLeft.height = max( getHeight( subLeft.left ), getHeight( subLeft.right ) ) + 1;
			
			return subLeft; //new root
		}
		
		private BSTNode<E> leftRotate( BSTNode<E> node){
			BSTNode<E> subRight = node.right;
			BSTNode<E> subRightLeft = subRight.left;
			
			subRight.left = node;
			node.right = subRightLeft;
			
			node.height = max( getHeight( node.left ), getHeight( node.right ) ) + 1;
			subRight.height = max( getHeight( subRight.left ), getHeight( subRight.right ) ) + 1;
			
			return subRight;
		}
	}
	
}
