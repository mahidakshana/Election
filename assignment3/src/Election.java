

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;




	
	public class Election   {
		/* 
		 * Do not touch the code inside the upcoming block 
		 * If anything tempered your marks will be directly cut to zero
		*/
		
		/*
		 * end code
		 */
		
		//write your code here
	//candidate class to store details of each candidate
	class candidate implements Comparable<candidate>
	{
		String candID;String name;  String state;String district; String constituency;String party; String votes;
		
	candidate(String name,String candID,String state, String district, String constituency, String party, String votes)
	 {this.name=name;
	 this.state=state;
	 this.district=district;
	 this.constituency=constituency;
	 this.party=party;
	 this.votes=votes;
	 this.candID=candID;
	 }

	@Override
	public int compareTo(candidate o) {
		if(Integer.parseInt(votes)==Integer.parseInt(o.votes))
		return 0;
		
		else if(Integer.parseInt(votes)>Integer.parseInt(o.votes))
			return 1;
		else return -1;
	}  

}
	
	
	
	
	BST<String,candidate> b= new BST<String,candidate>();
	
	//insert each candidate in BST b
	 public void insert(String name, String candID, String state, String district, String constituency, String party, String votes){
			//write your code here 
		 candidate c1=new candidate(name,candID,state,district,constituency,party,votes);
		b.insert(candID, c1);
		
		}
	 
	 //update value of votes 
	 
		public void updateVote(String name, String candID, String votes){
			//write your code here
			BST.Node n=b.find(candID);
			candidate c=(candidate) n.value;
			candidate c1=new candidate(name,candID,c.state,c.district,c.constituency,c.party,votes);
			 
			b.delete(candID);
			b.insert(candID,c1);
		}
		
		
		
		//return top k candidate in given constituency
		public void topkInConstituency(String constituency, String k){
			ArrayList<candidate> arr= new ArrayList<candidate>();
			inorder(b.root,arr);
			
			int num= Integer.parseInt(k)-1;
			for(int i=arr.size()-1; i>=0; i--)
			{
				if(arr.get(i).constituency.equals(constituency)) {
					candidate c= (candidate) arr.get(i);
					System.out.println(c.name+","+" "+c.candID+","+" "+c.party);
				  num--;	
				}
				if(num<0) break;
			}
			
		}
		
		
		
		//return leading party in given state
		public void leadingPartyInState(String state){
			
			 HashMap<String, Integer> map = new HashMap<>();
			ArrayList<candidate> arr= new ArrayList<candidate>();
			ArrayList<String> arr1= new ArrayList<String>();
			int max=0;
			
			Queue<BST.Node> q = new LinkedList<BST.Node>();
	    	q.add(b.root);
	    	while(!q.isEmpty())
	    	{
	    		BST.Node temp=q.poll();
	    		candidate c=(candidate) temp.value;
	    		
	    		if(c.state.equals(state)) arr.add(c);
	    	  
	    	    if(temp.left!=null) q.add(temp.left);
	    	    if(temp.right!=null) q.add(temp.right);
	    	}
			
	    	
			
			for(int i=arr.size()-1; i>=0; i--)
			{       candidate c= (candidate) arr.get(i);
					if(map.containsKey(c.party)) {map.put(c.party, map.get(c.party)+Integer.parseInt(c.votes));}
					else {map.put(c.party,Integer.parseInt(c.votes) );}
				 
				
			}
			
			
			Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator(); 
			Iterator<Map.Entry<String, Integer>> itr1 = map.entrySet().iterator(); 
	          
	        while(itr.hasNext()) 
	        { 
	             Map.Entry<String, Integer> entry = itr.next(); 
	            if(entry.getValue()>max) max=entry.getValue();
	        } 
	       
	        
	        while(itr1.hasNext()) 
	        { 
	             Map.Entry<String, Integer> entry = itr1.next();
	             if(entry.getValue()==max) 
	             arr1.add(entry.getKey());
	             
	        } 
	        
	        
	       
	       sort(arr1,0,arr1.size()-1);
	        for(int i=0; i<arr1.size(); i++)
			{ 
				System.out.println(arr1.get(i));}
	        
			
		}
		
		
		
		
		
		
		//cancel votes in given constituency
		public void cancelVoteConstituency(String constituency){
			
				ArrayList<String> arr1= new ArrayList<String>();
				Queue<BST.Node> q = new LinkedList<BST.Node>();
		    	q.add(b.root);
		    	while(!q.isEmpty())
		    	{
		    		BST.Node temp=q.poll();
		    		candidate c=(candidate) temp.value;
		    		
		    		if(c.constituency.equals(constituency)) arr1.add(c.candID);
		    	  
		    	    if(temp.left!=null) q.add(temp.left);
		    	    if(temp.right!=null) q.add(temp.right);
		    	}
		    	 
			sort(arr1,0,arr1.size()-1);
			for(int i=0; i<arr1.size(); i++)
			{ 
			    
				b.delete(arr1.get(i));
			}
				
				
			
			
	}
		
		//return leading party overall
		public void leadingPartyOverall(){
			
			//write your code here
			 int max=0;
			 HashMap<String, Integer> map = new HashMap<>();
				ArrayList<candidate> arr= new ArrayList<candidate>();
				ArrayList<String> arr1= new ArrayList<String>();
				
				
				inorder(b.root,arr);
				for(int i=arr.size()-1; i>=0; i--)
				{
					    candidate c= (candidate) arr.get(i);
						if(map.containsKey(c.party)) {map.put(c.party, map.get(c.party)+Integer.parseInt(c.votes));}
						else {map.put(c.party,Integer.parseInt(c.votes) );}
					 
					
				}
				
				Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator(); 
				Iterator<Map.Entry<String, Integer>> itr1 = map.entrySet().iterator(); 
		          
		        while(itr.hasNext()) 
		        { 
		             Map.Entry<String, Integer> entry = itr.next(); 
		            if(entry.getValue()>max) max=entry.getValue();
		        } 
		       
		        while(itr1.hasNext()) 
		        { 
		             Map.Entry<String, Integer> entry = itr1.next();
		             if(entry.getValue()==max) 
		             arr1.add(entry.getKey());
		             
		        } 
		       
		        sort(arr1,0,arr1.size()-1);
		        for(int i=0; i<arr1.size(); i++)
				
				{
					System.out.println(arr1.get(i));}
		        
				
				
			
			
			
		}
		
		//voteshare in given state
		public void voteShareInState(String party,String state){
			
			    HashMap<String, Integer> map = new HashMap<>();
				ArrayList<candidate> arr= new ArrayList<candidate>();
				
				
				DecimalFormat format2dec = new DecimalFormat("0.0");
				
				int num=0;
				Queue<BST.Node> q = new LinkedList<BST.Node>();
		    	q.add(b.root);
		    	while(!q.isEmpty())
		    	{
		    		BST.Node temp=q.poll();
		    		candidate c=(candidate) temp.value;
		    		
		    		if(c.state.equals(state)) arr.add(c);
		    	  
		    	    if(temp.left!=null) q.add(temp.left);
		    	    if(temp.right!=null) q.add(temp.right);
		    	}
				
				for(int i=arr.size()-1; i>=0; i--)
				{
					
						num+=(Integer.parseInt(arr.get(i).votes));
						candidate c= (candidate) arr.get(i);
						if(map.containsKey(c.party)) {map.put(c.party, map.get(c.party)+Integer.parseInt(c.votes));}
						else {map.put(c.party,Integer.parseInt(c.votes) );}
					 
					
				}
				
				
				Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator(); 
				  while(itr.hasNext()) 
			        { 
			             Map.Entry<String, Integer> entry = itr.next(); 
			            if(entry.getKey().equals(party)) {
			            	float x=100*(float)entry.getValue()/(float)num;
			            	System.out.println(format2dec.format(x));
							
			            break;
			            	
			            }
			            
			        } 
				
				}
		
		
		
		//print election order
		public void printElectionLevelOrder() {
			//write your code here
			
			//write your code here
	    	Queue<BST.Node> q = new LinkedList<BST.Node>();
	    	q.add(b.root);
	    	while(!q.isEmpty())
	    	{
	    		BST.Node temp=q.poll();
	    		candidate c= (candidate) temp.value;
	    	    System.out.println(c.name+","+" "+temp.key+","+" "+c.state+","+" "+c.district+","+" "+c.constituency+","+" "+c.party+","+" "+c.votes);
	    	    if(temp.left!=null) q.add(temp.left);
	    	    if(temp.right!=null) q.add(temp.right);
	    	}
			
		}
		
		
		
		//inorder traversal
		private void inorder(BST.Node n, ArrayList<candidate> arr)
		{  
		 if (n==null) return;
		 inorder(n.left,arr);
		 arr.add((candidate) n.value);
		 inorder(n.right,arr);
		 
		}
		void inorder() {inorder(b.root, null);}
		
		
		
		public  void sort(ArrayList<String> arr1, int low, int high)
		{if(low<high)
		{
		int p= quick(arr1,low,high);
			sort(arr1,low,p-1);
			sort(arr1,p+1,high);
			
		}
			
		}
		
		public int quick(ArrayList<String> arr1, int low, int high)
		{
			String str =arr1.get(high);
			int i=low-1;
			for(int j=low; j<high; j++)
			{  String str1=arr1.get(j);
				if(str1.compareTo(str)<0)
				{
					i++;
					String tm= arr1.get(i);
					arr1.set(i, arr1.get(j));
					arr1.set(j, tm);
				}
			}
			String tem= arr1.get(i+1);
			arr1.set(i+1, arr1.get(high));
			arr1.set(high, tem);
			return i+1;
		}
		
		
		
		
		public static void main(String[] args)
		{
		Election e= new Election();
	
		e.insert("Cand1" , "1" , "S1" , "D1" , "C1" , "P1" , "1200");
		e.insert("Cand2" , "100" , "S1" , "D1" , "C1" , "P2" , "1000");
		e.insert("Cand3" , "101" , "S1" , "D1" , "C1" , "P3" , "1500");
		e.insert("Cand4" , "102" , "S1" , "D1" , "C1" , "P4" , "2000");
		e.insert("Cand5" , "105" , "S1" , "D1" , "C1" , "P5" , "500");
		e.insert("Cand6" , "2" , "S1" , "D2" , "C2" , "P2" , "4000");
		e.insert("Cand7" , "3" , "S2" , "D3" , "C3" , "P3" , "2100");
		e.insert("Cand8" , "4" , "S3" , "D4" , "C4" , "P4" , "800");
		e.insert("Cand9" , "5" , "S3" , "D5" , "C5" , "P6" , "1600");
		
		
		
		
		e.printElectionLevelOrder();
		System.out.println();
		
		
		e.updateVote("cand1", "1", "2500");
		e.updateVote("cand8", "4", "1400");
		e.printElectionLevelOrder();
		System.out.println();
		
		
		
		e.insert("Cand9" , "6" , "S1" , "D1" , "C6" , "P6" , "20");
		e.insert("Cand10" , "7" , "S1" , "D2" , "C3" , "P2" , "600");
		System.out.println();
		e.printElectionLevelOrder();
		e.topkInConstituency("C1", "3");
		
		e.leadingPartyInState("S1");
		e.leadingPartyOverall();
		e.voteShareInState("P1", "S1");
		
		e.cancelVoteConstituency("C1");
		e.printElectionLevelOrder(); 
		
		
		
		
		}

}

 
