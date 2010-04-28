import java.util.*;

public class _main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//LinkedList<Integer> ll = new LinkedList<Integer>();
		//Iterator i = ll.iterator();
		List<Integer> l1 = new List<Integer>();
//		l1.push_front(10);
//		l1.push_front(20);
//		l1.push_front(30);
//		l1.push_front(40);
		l1.push_back(10);
		l1.push_back(20);
		l1.push_back(30);
		l1.push_back(40);
		
		
		
		Iterator<Integer> it = l1.begin();
		//for(int i=0; i <= l1.size(); i++)
		while(!it.equal(l1.end()))
		{
			System.out.println(it.next());
		}
		
	}

}
