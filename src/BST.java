/**
 * This class implements a binary search tree.
 * All of its methods are implemented recursively
 * @author Aaron Walker
 *
 */
public class BST<E extends Comparable <E> > implements BSTInterface<E> {
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
	
	private <T extends Comparable <T>> void recInOrderTraverse (BSTNode <T> root){
		if ( root != null ){
			recInOrderTraverse( root.left );
			System.out.println( root.data );
			recInOrderTraverse( root.right );
		}
		
	}
	
	private <T extends Comparable <T>> void recPostOrderTraverse (BSTNode <T> root){
		if ( root != null ){
			recPostOrderTraverse( root.left );
			recPostOrderTraverse( root.right );
			System.out.println( root.data );
		}
	}
	
	private <T extends Comparable<T>> void recPreOrderTraverse (BSTNode <T> root){
		if ( root != null ){
			System.out.println( root.data );
			recPreOrderTraverse( root.left );
			recPreOrderTraverse( root.right);	
		}
	}
	@Override
	public String toString(){
		recInOrderTraverse(root);
		System.out.println("\n");
		recPostOrderTraverse(root);
		System.out.println("\n");
		recPreOrderTraverse(root);
		return "";
	}
	
	private class BSTNode <T extends Comparable <T> >
					implements Comparable < BSTNode<T> >{
		
		private T data;
		private BSTNode <T> left;
		private BSTNode <T> right;
		private int height;
		
		public BSTNode ( T data ){
			this.data = data;
			this.left = null;
			this.right = null;
			this.height = 1;
		}
		
		private int max (int a, int b){
			return ( a > b ) ? a : b; 
		}
		
		public int compareTo ( BSTNode <T> other){
			return this.data.compareTo(other.data);
		}
		
		public int getHeight ( BSTNode<T> N){
			if ( N == null ){
				return 0;
			}
			return N.height;
		}
		
		private int getBalance ( BSTNode<T> N){
			if ( N == null ){
				return 0;
			}
			return getHeight(N.left) - getHeight(N.right);
		}
	}
}
