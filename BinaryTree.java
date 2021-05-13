package ;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class BinaryTree {

	private static class TreeNode< AnyType > implements Comparable<TreeNode>
	{
		public TreeNode left;
		public TreeNode right;
		public double distance;
		public AnyType element;
		
		public TreeNode (AnyType o, double d)
		{
			this (o, null, null, d);
		}
		
		public TreeNode (AnyType o, TreeNode l, TreeNode r, double d)
		{
			element = o;
			left = l;
			right = r;
			distance = d;
		}
		
		public String toString()
		{
			return "" + element;
		}
		
		@Override
		public int compareTo(TreeNode t) {
			if (this.distance > t.distance){
				return 1;
			}else if (this.distance < t.distance){
				return -1;
			}else{
				return 0;
			}
		}
	
	}
	
	private TreeNode root;
	
	public BinaryTree( TreeNode root )
	{
		this.root = root;
	}
	
	public TreeNode findClosest (Object target){
		BinaryHeap<TreeNode> q = new BinaryHeap();
		q.insert(root);
		while (!q.isEmpty()){
			TreeNode t = (TreeNode) q.deleteMin();
			if (t!=null && !t.element.equals(target)){
				if (t.left!= null){
					t.left.distance += t.distance;
					q.insert(t.left);
				}
				if (t.right!= null){
					t.right.distance += t.distance;
					q.insert(t.right);
				}
			}else{ 
				return t;
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		String res = ""; 
		try { res = new String(Files.readAllBytes(Paths.get(args[0]))); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		Stack<TreeNode> stack = new Stack<>();
		String [] result = res.split(" ");
		int c = 0;
		while (c < result.length){
			String t = result[c];
			if (t.equals(")")){
				TreeNode left_child = (TreeNode) stack.pop();
				TreeNode right_child = (TreeNode) stack.pop();
				TreeNode parent = (TreeNode) stack.pop();
				parent.right = left_child;
				parent.left = right_child;
				stack.push(parent);
				c += 1;
			}else{
				if (t.equals("(")){
					c += 1;
				}else{
					TreeNode n = new TreeNode(t,Double.valueOf(result[c+1]));
					stack.push(n);
					c += 2;
				}
			}
		}
		BinaryTree bt = new BinaryTree(stack.pop());
		System.out.print("Found "+"\"*\""+ " at distance " + bt.findClosest("*").distance);
	}
}
