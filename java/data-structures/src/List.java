public class List<T> 
{
	protected long m_size;
	protected Node<T> m_head;
	protected Node<T> m_tail;
	protected Node<T> m_current;
	protected Node<T> m_free;
	protected Iterator<T> m_ite;
	
	public List()
	{
		m_size = 0;
		m_head = null;
		m_tail = null;
		m_current = null;
		m_free = null;
	}
	
	public long size()
	{
		return m_size;
	}
	
	public T back()
	{
		return m_current.get_data();
	}
	
	public Iterator<T> begin()
	{
		Iterator<T> tmp = new Iterator<T>(m_head);
		return tmp;
	}
	
	public Iterator<T> end()
	{
		Iterator<T> tmp = new Iterator<T>(m_tail);
		return tmp;
	}
	
	public Iterator<T> next()
	{
		if(m_current.get_next() != null)
			m_current.set_next(m_current.get_next());
		else
			m_current = m_tail;
		Iterator<T> tmp = new Iterator<T>(m_current);
		return tmp;
	}
	
	public Node<T> get_node(T data)
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
	
	public void insert(Iterator<T> ite, T data)
	{
		m_size++;
		if(m_head == null && m_tail == null)//空串鍊
		{
			m_current = get_node(data);
			m_head = m_current;
			m_tail = m_current;
		}
		else if(!ite.has_prev())//插入頭
		{
			m_current = get_node(data);
			ite.get_node().set_prev(m_current);
			m_current.set_next(ite.get_node());
			m_head = m_current;
		}
		else if(!ite.has_next())//插入尾
		{
			m_current = get_node(data);
			ite.get_node().set_next(m_current);
			m_current.set_prev(ite.get_node());
			m_tail = m_current;
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
	public void push_front(T data)
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
	public void push_back(T data)
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
}
