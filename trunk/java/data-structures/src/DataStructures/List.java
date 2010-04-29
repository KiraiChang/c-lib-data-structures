package DataStructures;

public class List<T> 
{

	
	protected long m_size;//��ƭӼ�
	protected Node<T> m_head;//����Y
	protected Node<T> m_tail;//��Ƨ�
	protected Node<T> m_current;//�ثe���
	protected Node<T> m_free;//�������
	//protected Iterator<T> m_ite;
	
	public List()//�غc�l
	{
		m_size = 0;
		m_head = null;
		m_tail = null;
		m_current = null;
		m_free = null;
	}
	
	public long size()//���o��ƭӼ�
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
	
	public Iterator<T> begin()//�}�l
	{
		Iterator<T> tmp = new Iterator<T>(m_head);
		return tmp;
	}
	
	public Iterator<T> end()//����
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
	
	public Node<T> get_node(T data)//���o����
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
	public void insert(Iterator<T> ite, T data)//���J
	{
		m_size++;
		if(m_head == null && m_tail == null)//�Ŧ���
		{
			m_current = get_node(data);
			m_head = m_current;
			m_tail = m_current;
		}
		else if(ite.get_node() == null)//���J��
		{
			m_current = get_node(data);
			m_tail.set_next(m_current);
			m_current.set_prev(m_tail);
			m_tail = m_current;
		}
		else if(!ite.has_prev())//���J�Y
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
	public void push_front(T data)//�s�W�b�̫e
	{
		if(m_head == m_tail && m_tail !=null && m_head != null)//�Ȧ��@�Ӫ���
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
	public void push_back(T data)//�s�W�b�̫�
	{
		if(m_head == m_tail && m_tail !=null && m_head != null)//�Ȧ��@�Ӫ���
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
//		if(m_head == m_tail && m_head == null && m_tail == null)//�Ŧ���
//		{
//			m_current = get_node(data);
//			m_head = m_current;
//			m_tail = m_current;
//		}
//		else if(m_head == m_tail)//����u���@�Ӫ���
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
	public void put_node(Node<T> node)//��m�������
	{
		if(m_free == null )//m_free�Ū�
		{
			m_free = node;
			m_free.set_next(null);
			m_free.set_prev(null);
		}
		else
		{
			//��R�����`�I���m_free���夤
			node.set_next(m_free);
			m_free.set_prev(node);
			node.set_prev(null);
			m_free = node;
			node = null;
		}
	}

	public void earse(Iterator<T> ite)
	{
		m_size--;//��ƭӼƤ֤@
		//�����`�I���s
		if(m_head == null && m_tail == null)//�Ŧ���
		{
			
		}
		else if(m_head == m_tail && m_tail !=null && m_head != null)//�Ȧ��@�Ӫ���
		{
			put_node(m_current);
			m_current = null;
			m_tail= m_current;
			m_head = m_current;
		}
		else if(ite.get_node() == null)//�����I
		{
			m_current = m_tail;
			m_current.get_prev().set_next(m_current.get_next());
			m_tail = m_current.get_prev();
			put_node(m_current);
			m_current = m_tail;
		}
		else if(!ite.has_prev())//�Y�`�I
		{
			m_current = ite.get_node();
			m_current.get_next().set_prev(m_current.get_prev());
			ite.set_node(m_current.get_next());
			put_node(m_current);
			m_current = ite.get_node();
			m_head= m_current;
		}
		else//��L���p
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
