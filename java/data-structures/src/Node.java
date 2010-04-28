public class Node<T> 
{
	private T m_data;
	private Node<T> m_prev;
	private Node<T> m_next;
	public Node()
	{
		m_prev = null;
		m_next = null;
	}
	public Node<T> get_next()
	{
		return m_next;
	}
	public Node<T> get_prev()
	{
		return m_prev;
	}
	public T get_data()
	{
		return m_data;
	}
	public void set_next(Node<T> node)
	{
		m_next = node;
	}
	public void set_prev(Node<T> node)
	{
		m_prev = node;
	}
	public void set_data(T data)
	{
		m_data = data;
	}
}
