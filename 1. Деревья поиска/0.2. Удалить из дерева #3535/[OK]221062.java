import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Scanner;


class Tree {
	private class Node {
	    public int key;
	    public Node left;
	    public Node right;
	 
	    public Node(int key) {
	        this.key = key;
	    }
	}
  public Node root;
  public void insert(int x) {
      root = doInsert(root, x);
  }
   
  private Node doInsert(Node node, int x) {
      if (node == null) {
          return new Node(x);
      }
      if (x < node.key) {
          node.left = doInsert(node.left, x);
      } else if (x > node.key) {
          node.right = doInsert(node.right, x);
      }
      return node;
  }
  
  public static Node DeleteRecursively(Node v, int x) {
	  if(v==null)
	  return null;
	  else{
		  if (x < v.key) {
			  v.left=DeleteRecursively(v.left, x);
			  return v;
		  }
		  else
			  if( x > v.key) {
				  v.right=DeleteRecursively(v.right, x);
				  return v;
			  }
			  if( v.left==null) {
				  return v.right;
			  }
			  else
				  if (v.right==null) {
					  return v.left;
				  }
			  else {
			  int min_key = FindMin(v.right).key;
			  v.key = min_key;
			  v.right=DeleteRecursively(v.right, min_key);
			  return v;
			  }
	  }
	 
  }
  public static Node FindMin(Node v) {
	  if (v.left!=null)
		  return FindMin(v.left);
	  else {
		  return v;
	  }
  }

public static void PreOrderTraversal(Node v, FileWriter fw) 
  { 
  if(v!=null) 
  { 
  try { 
  fw.write(Integer.toString(v.key) + "\r\n"); 
  } catch (IOException e) { 
  // TODO Auto-generated catch block 
  e.printStackTrace(); 
  } 
  PreOrderTraversal(v.left,fw); 
  PreOrderTraversal(v.right,fw); 
  } 
  }
}
public class TreeDel implements Runnable{

	 public static void main(String args[]){
	    	
		 new Thread(null, new TreeDel(), "", 64 * 1024 * 1024).start();
	    	
	    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File in = new File("input.txt");
		   //BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		   String strLine;
		   int k=0;
		   Tree mytree = new Tree();
		   try {
		        Scanner sc = new Scanner(in);
		        if (sc.hasNext()) {
		            k=sc.nextInt();
		        }
		        while (sc.hasNext()) {
		            mytree.insert(sc.nextInt());
		        }
		   } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		  
		   try(FileWriter fos=new FileWriter("output.txt"))
	        {
	           mytree.root= mytree.DeleteRecursively(mytree.root, k);
			   mytree.PreOrderTraversal(mytree.root, fos);
	        }
	        catch(IOException ex){
	        	System.out.println(ex.getMessage());
	        }
	}
}