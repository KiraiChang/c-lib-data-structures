package DataStructures;

class Node<T>
{
	private T m_data;
	private Node<T> m_prev;
	private Node<T> m_next;
	Node()
	{
		m_prev = null;
		m_next = null;
	}
	Node<T> get_next()
	{
		return m_next;
	}
	Node<T> get_prev()
	{
		return m_prev;
	}
	T get_data()
	{
		return m_data;
	}
	void set_next(Node<T> node)
	{
		m_next = node;
	}
	void set_prev(Node<T> node)
	{
		m_prev = node;
	}
	void set_data(T data)
	{
		m_data = data;
	}
}
