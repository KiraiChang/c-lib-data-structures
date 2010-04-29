//import java.util.*;
import DataStructures.*;

public class _main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> i_list = new List<Integer>();
		i_list.push_back(10);
		i_list.push_back(20);
		i_list.push_back(30);
		i_list.push_back(40);
		
		List<Book> l_book = new List<Book>();
		l_book.push_back(new Book());
		l_book.back().set_name("String");
		l_book.push_back(new Book());
		l_book.back().set_name("String2");
		l_book.push_back(new Book());
		l_book.back().set_name("String3");
		l_book.push_back(new Book());
		l_book.back().set_name("String4");
		l_book.push_back(new Book());
		l_book.back().set_name("String5");
		
		
		
		Iterator<Book> it_book = l_book.begin();
		while(!it_book.equal(l_book.end()))
		{
			System.out.println(it_book.back().get_name());
			it_book.next();
		}
		
	}

}
