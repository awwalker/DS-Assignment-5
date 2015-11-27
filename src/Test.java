/**
 * This class is designed to facilitate 
 * testing of the methods implemented in
 * the other classes of this project
 * @author Aaron Walker
 *
 */
public class Test extends BST<Integer> {
	public static void main ( String[] args ){
		BST<Integer> nums = new BST<Integer>();
		
		Integer[] toAdd = {1,2,3, 4, 5,6, 7,8};
		for(int num : toAdd){
			nums.insert(num);
		}
		
		System.out.println(nums.toString());
		
		System.out.println("\n\n");
		
		nums.remove(4);
		System.out.println(nums.toString());
	}
}
