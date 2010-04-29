package DataStructures;

public class Iterator<T> 
{
	private Node<T> m_node;
	Iterator()
	{
		m_node = new Node<T>();
	}
	Iterator(Node<T> node)
	{
		m_node = node;
	}
	Boolean has_next()
	{
		if(m_node != null)
			return true;
		else
			return false;
	}
	Boolean has_prev()
	{
		if(m_node.get_prev() != null)
			return true;
		else
			return false;
	}
	public T next()
	{
		T tmp = null;
		tmp = m_node.get_data();
		m_node = m_node.get_next();
		return tmp;
	}
	public T prev()
	{
		T tmp = null;
		tmp = m_node.get_data();
		if(m_node.get_prev() != null)
		{
			m_node = m_node.get_prev();
		}
		return tmp;
	}
	void set_node(Node<T> node)
	{
		m_node = node;
	}
	Node<T> get_node()
	{
		return m_node;
	}
	public boolean equal(Iterator<T> ite)
	{
		if(ite.get_node() == m_node)
			return true;
		else
			return false;
	}
	Node<T> get_next()
	{
		return m_node.get_next();
	}
	Node<T> get_prev()
	{
		return m_node.get_prev();
	}
	public T back()
	{
		return m_node.get_data();
	}
	public void modify(T data)
	{
		m_node.set_data(data);
	}
}
