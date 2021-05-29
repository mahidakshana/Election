
import java.util.ArrayList;
//package col106.assignment3.Heap;

/*public class Heap<T extends Comparable, E extends Comparable> implements HeapInterface <T, E> {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	/*public static void main() {
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	} */
	/*
	 * end code
	 */
	
	// write your code here	  
	public class Heap<T extends Comparable, E extends Comparable>{
	
	Node root;
	ArrayList<Node> arr= new ArrayList<Node>();
	
	//create node from given data
	private class Node
	{
	   private T key;
	    private E value;
	  
	     Node(T key, E value)
	     {
	    	this.key=key;
	    	 this.value=value;
	    	
	     }
	}
	
	/* 
	 for node at index i
	 parent is at index (i-1)/2
	 left= 2*i+1
	 right=2*i+2
	 */
	private int parent(int pos) {return (pos-1)/2;}
	private int left(int pos) {return 2*pos+1;}
	private int right(int pos) {return 2*pos+2;}
	@SuppressWarnings("unchecked")
	private void perculate_up(int pos) {
		for(int i=pos; i>=0; i--)
		{
			Node val1= arr.get(i);
			Node val2= arr.get(parent(i));
			if(val1.value.compareTo(val2.value)>0) {
				arr.set(i,val2);
				arr.set(parent(i),val1);
				
				
			}
			//i=parent(i);
		}
		
		
	}
	
	
		@SuppressWarnings("unchecked")
		private void perculate_down(int pos) {
			
			boolean b=false;
			for(int i=pos; i<arr.size(); )
			{ 
				if(isLeaf(i)) break;
				
				
				Node val1= arr.get(i);
				
				  if(right(i)>arr.size()-1) {
					  if(val1.value.compareTo(arr.get(left(i)).value)<0) {
						  arr.set(i, arr.get(left(i))); arr.set(left(i), val1);}
					  break;
				  }
				
				Node val2;
				if((arr.get(left(i)).value).compareTo(arr.get(right(i)).value)>0) {val2=arr.get(left(i));  b=true;}
				else {val2=arr.get(right(i));  b=false;}
				if(val1.value.compareTo(val2.value)<0) {
					arr.set(i, val2);
					if(b){arr.set(left(i), val1);  i=left(i); }
					
					else {arr.set(right(i), val1);  i=right(i);}
				}
				else break;
				
				}
				
				}
		
		
		
		
		
		
		private boolean isLeaf(int pos)
		{
			if(pos+1>arr.size()/2) return true;
			else return false;
		}
		
	
	
	public void insert(T key, E value) {
		//write your code here
		Node n= new Node(key,value);
		if(root==null) {root=n;
		arr.add(0,root); }
		else
		{arr.add(n); 
		perculate_up(arr.size()-1);
			
		}
		
	}
	
	public E extractMax() {
		Node n=arr.get(0);
		delete(n.key);
		return n.value;
		}

	
	
	public void delete(T key) {
		//write your code here
		int n=0;
		
		Node temp=arr.get(arr.size()-1);
		arr.remove(arr.size()-1);
		for(int i=0; i<arr.size(); i++)
		{
			if(arr.get(i).key==key) {
				
				arr.set(i, temp);
				
				n=i;break;
			}
		}
		if(n==0) {perculate_down(0);}
		else if(arr.get(n).value.compareTo(arr.get(parent(n)).value)>0) perculate_up(n);
		else {perculate_down(n); }
		
	}

	public void increaseKey(T key, E value) {
		//write your code here
		int i=0;
		Node n = null;
		for( i=0; i<arr.size(); i++)
		{
			 n=arr.get(i);
			if(n.key==key) break;
			
		}
		E val=n.value;
		n.value=value;
		arr.set(i, n);
		if(val.compareTo(value)<0) { perculate_up(i);}
		else if(val.compareTo(value)>0) { perculate_down(i);}
		
		
	}

	public void printHeap() {
		//write your code here
		for(int i=0; i<arr.size(); i++)
		{
			System.out.println(arr.get(i).key+","+" "+arr.get(i).value);
		}
		
		
	}	

	
	
	   public static void main(String[] args)
	    {
Heap<Integer, Integer> maxHeap=new Heap<Integer,Integer>();
	    	
	    	
	    	maxHeap.insert(1,100); 
	        maxHeap.insert(2,10); 
	        maxHeap.insert(3,30); 
	        maxHeap.insert(4,50); 
	        maxHeap.insert(5,150); 
	        maxHeap.insert(6,1); 
	        maxHeap.insert(7,3); 
	       
	    	
	        maxHeap.printHeap();
	       //System.out.println(maxHeap.extractMax());
	        maxHeap.delete(1);
	        System.out.println();
	        maxHeap.printHeap();
	        maxHeap.insert(8, 500);
	        System.out.println();
	        maxHeap.printHeap();
	        System.out.println(maxHeap.extractMax());
	        //maxHeap.delete(8);
	        System.out.println();
	        maxHeap.printHeap();
	        maxHeap.increaseKey(7, 70);
	        System.out.println();
	        maxHeap.printHeap();  
//maxHeap.insert(1,100); 



maxHeap.printHeap();
	       
	    }
	
	
	
	
	
	
	
	
	
}
