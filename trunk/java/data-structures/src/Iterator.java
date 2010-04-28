public class Iterator<T>
{
	private Node<T> m_node;
	public Iterator()
	{
		m_node = new Node<T>();
	}
	public Iterator(Node<T> node)
	{
		m_node = node;
	}
	public Boolean has_next()
	{
		if(m_node.get_next() != null)
			return true;
		else
			return false;
	}
	public Boolean has_prev()
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
		if(m_node.get_next() != null)
		{
			m_node = m_node.get_next();
		}
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
	public void set_node(Node<T> node)
	{
		m_node = node;
	}
	public Node<T> get_node()
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
	public Node<T> get_next()
	{
		return m_node.get_next();
	}
	public Node<T> get_prev()
	{
		return m_node.get_prev();
	}
}
