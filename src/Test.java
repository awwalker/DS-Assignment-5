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
		nums.insert(25);
		nums.insert(10);
		nums.insert(29);
		nums.insert(7);
		nums.insert(15);
		nums.insert(10);
		nums.insert(17);
		nums.insert(25);
		nums.insert(35);
		nums.insert(30);
		nums.insert(40);
		System.out.println(nums.toString());
	}
}
