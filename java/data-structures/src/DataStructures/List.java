package DataStructures;

public class List<T> 
{

	
	protected long m_size;//資料個數
	protected Node<T> m_head;//資料頭
	protected Node<T> m_tail;//資料尾
	protected Node<T> m_current;//目前資料
	protected Node<T> m_free;//釋放串鍊
	//protected Iterator<T> m_ite;
	
	public List()//建構子
	{
		m_size = 0;
		m_head = null;
		m_tail = null;
		m_current = null;
		m_free = null;
	}
	
	public long size()//取得資料個數
	{
		return m_size;
	}
	
	public T back()
	{
		return m_current.get_data();
	}
	
	public void modify(T data)
	{
		m_current.set_data(data);
	}
	
	public Iterator<T> begin()//開始
	{
		Iterator<T> tmp = new Iterator<T>(m_head);
		return tmp;
	}
	
	public Iterator<T> end()//結尾
	{
		Iterator<T> tmp = new Iterator<T>(null);
		return tmp;
	}
	
//	public Iterator next()
//	{
//		if(m_current.get_next() != null)
//			m_current.set_next(m_current.get_next());
//		else
//			m_current = m_tail;
//		Iterator tmp = new Iterator(m_current);
//		return tmp;
//	}
	
	public Node<T> get_node(T data)//取得串鍊
	{
		Node<T> tmp;
		if(m_free == null)
		{
			tmp =  new Node<T>();
		}
		else
		{
			tmp = m_free;
			tmp.set_next(null);
			tmp.set_prev(null);
			m_free = m_free.get_next();
		}
		tmp.set_data(data);
		return tmp;
	}
	public void insert(Iterator<T> ite, T data)//插入
	{
		m_size++;
		if(m_head == null && m_tail == null)//空串鍊
		{
			m_current = get_node(data);
			m_head = m_current;
			m_tail = m_current;
		}
		else if(ite.get_node() == null)//插入尾
		{
			m_current = get_node(data);
			m_tail.set_next(m_current);
			m_current.set_prev(m_tail);
			m_tail = m_current;
		}
		else if(!ite.has_prev())//插入頭
		{
			m_current = get_node(data);
			ite.get_node().set_prev(m_current);
			m_current.set_next(ite.get_node());
			m_head = m_current;
		}
		else
		{
			m_current.set_data(data);
			ite.get_node().set_next(m_current);
			ite.get_next().set_prev(m_current);
			m_current.set_next(ite.get_next());
			m_current.set_prev(ite.get_prev());
		}
	}
	public void push_front(T data)//新增在最前
	{
		if(m_head == m_tail && m_tail !=null && m_head != null)//僅有一個物件
		{
			m_size++;
			m_current = get_node(data);
			m_head.set_prev(m_current);
			m_current.set_next(m_head);
			m_head = m_current;
		}
		else
			insert(begin(), data);
	}
	public void push_back(T data)//新增在最後
	{
		if(m_head == m_tail && m_tail !=null && m_head != null)//僅有一個物件
		{
			m_size++;
			m_current = get_node(data);
			m_tail.set_next(m_current);
			m_current.set_prev(m_tail);
			m_tail = m_current;
		}
		else
			insert(end(), data);
	}
//	public void push_back(T data)
//	{
//		if(m_head == m_tail && m_head == null && m_tail == null)//空串鍊
//		{
//			m_current = get_node(data);
//			m_head = m_current;
//			m_tail = m_current;
//		}
//		else if(m_head == m_tail)//串鍊只有一個物件
//		{
//			m_current = get_node(data);
//			m_head.set_next(m_current);
//			m_current.set_prev(m_head);
//			m_tail = m_current;
//		}
//		else
//		{
//			m_current = get_node(data);
//			m_tail.set_next(m_current);
//			m_current.set_prev(m_tail);
//			m_tail = m_current;
//		}
//	}
//	
//	public Iterator<T> iterator()
//	{
//		return m_ite;
//	}
	public void put_node(Node<T> node)//放置釋放串鍊
	{
		if(m_free == null )//m_free空的
		{
			m_free = node;
			m_free.set_next(null);
			m_free.set_prev(null);
		}
		else
		{
			//把刪除的節點放到m_free串鍊中
			node.set_next(m_free);
			m_free.set_prev(node);
			node.set_prev(null);
			m_free = node;
			node = null;
		}
	}

	public void earse(Iterator<T> ite)
	{
		m_size--;//資料個數少一
		//切除節點關連
		if(m_head == null && m_tail == null)//空串鍊
		{
			
		}
		else if(m_head == m_tail && m_tail !=null && m_head != null)//僅有一個物件
		{
			put_node(m_current);
			m_current = null;
			m_tail= m_current;
			m_head = m_current;
		}
		else if(ite.get_node() == null)//尾結點
		{
			m_current = m_tail;
			m_current.get_prev().set_next(m_current.get_next());
			m_tail = m_current.get_prev();
			put_node(m_current);
			m_current = m_tail;
		}
		else if(!ite.has_prev())//頭節點
		{
			m_current = ite.get_node();
			m_current.get_next().set_prev(m_current.get_prev());
			ite.set_node(m_current.get_next());
			put_node(m_current);
			m_current = ite.get_node();
			m_head= m_current;
		}
		else//其他狀況
		{
			m_current = ite.get_node();
			m_current.get_next().set_prev(m_current.get_prev());
			m_current.get_prev().set_next(m_current.get_next());
			ite.set_node(m_current.get_prev());
			put_node(m_current);
			m_current = ite.get_node();
		}
	}
	
	public void pop_front()
	{
		earse(begin());
	}
	
	public void pop_back()
	{
		earse(end());
	}
}
