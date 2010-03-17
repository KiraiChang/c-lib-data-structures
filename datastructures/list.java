package datastructures;

public class list <T>
{
	private class node//節點資料
	{
		private T m_data;//泛型資料內容<T>
		private node m_prev;//前一個節點
		private node m_next;//候一個節點
		private node()//預設建構子初始值前後結點都沒有值
		{
			m_prev = null;
			m_next = null;
		}
	}
	public class iterator//尋訪器
	{
		private node m_node;
//		private iterator(node new_node)
//		{
//			this.m_node = new_node;
//		}
		public iterator()
		{
			
		}
		public boolean has_next()//是否有往下一個值
		{
			if(m_node != null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		public T next()//向下移一個
		{
			node tmp = m_node;
			m_node = m_node.m_next;
			return tmp.m_data;
		}
	}
	private node m_head;//頭節點
	private node m_tail;//尾節點
	private node m_current;//目前的結點
	private node m_free_node;//釋放的結點串鍊
	private iterator m_iterator;//尋訪器
	
	public list()
	{
		m_iterator = new iterator();
		m_head = null;
		m_tail = null;
		m_current = null;
		m_free_node = null;
	}
	public list<T>.iterator iterator()//呼叫尋訪器
	{
		m_iterator.m_node = m_head;
		return m_iterator;
	}
	public node get_node()//取得結點
	{
		if(m_free_node == null)//若釋放串鍊沒有值的話new回傳
		{
			return new node();
		}
		else//若有值的話由釋放串鍊取一個出來
		{
			node tmp = m_free_node;
			m_free_node = m_free_node.m_next;
			m_free_node.m_prev = null;
			return tmp;
		}
	}
	public void push_back(T data)//新增在最後
	{
		if(m_head == null && m_tail == null)//若為空串鍊頭尾相等,頭前為null尾後為null
		{
			m_current = get_node();
			m_current.m_data = data;
			m_tail = m_current;
			m_head = m_current;
		}
		else //if(m_head == m_tail)//新增在尾巴,尾巴後為null
		{
			m_current =  get_node();
			m_current.m_data = data;
			m_current.m_prev = m_tail;
			m_tail.m_next = m_current;
			m_tail = m_current;
		}
//		else
//		{
//			m_current =  get_node();
//			m_current.m_data = data;
//			m_current.m_left = m_tail;
//			m_tail.m_right = m_current;
//			//m_current.m_right = m_head;
//			//m_head.m_left = m_current;
//			m_tail = m_current;
//		}
	}
	public void push_back()//設一個完全沒有值的結點
	{
		push_back(null);
	}
	public list<T> begin()//回到最初結點
	{
		m_current = m_head;
		return this;
	}
	public T back()//取回最後一個節點的值
	{
		return (T) m_current.m_data;
	}
	public void put_node(node earse)//把欲刪除的結點放到釋放串鍊
	{
		earse.m_next = m_free_node;
		earse.m_prev = null;
		m_free_node = earse;
	}
	public void earse(node position)//刪除任何位置的結點
	{
		if(m_head == null && m_tail == null)//若為空串鍊則甚麼都不做
		{
			return ;
		}
		else if(position.m_prev == null && position.m_next == null)//若串鍊只有一個值,則把唯一一個值放到釋放串鍊把頭尾current設為null
		{
			//last one;
			put_node(position);
			m_head = m_tail = m_current =null;
			position = null;
		}
		else if(position.m_prev == null)//若為頭的結點要刪則把頭的下一個節點與頭之間的關聯刪除後把頭放到釋放串鍊
		{
			//mp_head;
			position.m_next.m_prev = null;
			m_head = position.m_next;
			m_current = m_head;
			put_node(position);
			position = m_current;
		}
		else if(position.m_next == null)//若為尾的結點要刪則把尾的上一個節點與尾之間的關聯刪除後把尾放到釋放串鍊
		{
			//mp_tail;
			position.m_prev.m_next = null;
			m_tail = position.m_prev;
			m_current = m_tail;
			put_node(position);
			position = m_current;
		}
		else//其他的結點就把其他節點的前一個與後一個和結點的關聯刪除後把節點放到釋放節點中
		{
			//other position;
			position.m_prev.m_next = position.m_next;
			position.m_next.m_prev = position.m_prev;
			m_current = position.m_prev;
			put_node(position);
			position = m_current;
		}
	}
	public void pop_back()//刪除最後一個節點
	{
		earse(m_tail);
	}
}
