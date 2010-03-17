package datastructures;

public class list <T>
{
	private class node//�`�I���
	{
		private T m_data;//�x����Ƥ��e<T>
		private node m_prev;//�e�@�Ӹ`�I
		private node m_next;//�Ԥ@�Ӹ`�I
		private node()//�w�]�غc�l��l�ȫe�ᵲ�I���S����
		{
			m_prev = null;
			m_next = null;
		}
	}
	public class iterator//�M�X��
	{
		private node m_node;
//		private iterator(node new_node)
//		{
//			this.m_node = new_node;
//		}
		public iterator()
		{
			
		}
		public boolean has_next()//�O�_�����U�@�ӭ�
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
		public T next()//�V�U���@��
		{
			node tmp = m_node;
			m_node = m_node.m_next;
			return tmp.m_data;
		}
	}
	private node m_head;//�Y�`�I
	private node m_tail;//���`�I
	private node m_current;//�ثe�����I
	private node m_free_node;//���񪺵��I����
	private iterator m_iterator;//�M�X��
	
	public list()
	{
		m_iterator = new iterator();
		m_head = null;
		m_tail = null;
		m_current = null;
		m_free_node = null;
	}
	public list<T>.iterator iterator()//�I�s�M�X��
	{
		m_iterator.m_node = m_head;
		return m_iterator;
	}
	public node get_node()//���o���I
	{
		if(m_free_node == null)//�Y�������S���Ȫ���new�^��
		{
			return new node();
		}
		else//�Y���Ȫ��ܥ����������@�ӥX��
		{
			node tmp = m_free_node;
			m_free_node = m_free_node.m_next;
			m_free_node.m_prev = null;
			return tmp;
		}
	}
	public void push_back(T data)//�s�W�b�̫�
	{
		if(m_head == null && m_tail == null)//�Y���Ŧ����Y���۵�,�Y�e��null���ᬰnull
		{
			m_current = get_node();
			m_current.m_data = data;
			m_tail = m_current;
			m_head = m_current;
		}
		else //if(m_head == m_tail)//�s�W�b����,���ګᬰnull
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
	public void push_back()//�]�@�ӧ����S���Ȫ����I
	{
		push_back(null);
	}
	public list<T> begin()//�^��̪쵲�I
	{
		m_current = m_head;
		return this;
	}
	public T back()//���^�̫�@�Ӹ`�I����
	{
		return (T) m_current.m_data;
	}
	public void put_node(node earse)//����R�������I����������
	{
		earse.m_next = m_free_node;
		earse.m_prev = null;
		m_free_node = earse;
	}
	public void earse(node position)//�R�������m�����I
	{
		if(m_head == null && m_tail == null)//�Y���Ŧ���h�ƻ򳣤���
		{
			return ;
		}
		else if(position.m_prev == null && position.m_next == null)//�Y����u���@�ӭ�,�h��ߤ@�@�ӭȩ�����������Y��current�]��null
		{
			//last one;
			put_node(position);
			m_head = m_tail = m_current =null;
			position = null;
		}
		else if(position.m_prev == null)//�Y���Y�����I�n�R�h���Y���U�@�Ӹ`�I�P�Y���������p�R������Y����������
		{
			//mp_head;
			position.m_next.m_prev = null;
			m_head = position.m_next;
			m_current = m_head;
			put_node(position);
			position = m_current;
		}
		else if(position.m_next == null)//�Y���������I�n�R�h������W�@�Ӹ`�I�P�����������p�R����������������
		{
			//mp_tail;
			position.m_prev.m_next = null;
			m_tail = position.m_prev;
			m_current = m_tail;
			put_node(position);
			position = m_current;
		}
		else//��L�����I�N���L�`�I���e�@�ӻP��@�өM���I�����p�R�����`�I�������`�I��
		{
			//other position;
			position.m_prev.m_next = position.m_next;
			position.m_next.m_prev = position.m_prev;
			m_current = position.m_prev;
			put_node(position);
			position = m_current;
		}
	}
	public void pop_back()//�R���̫�@�Ӹ`�I
	{
		earse(m_tail);
	}
}
