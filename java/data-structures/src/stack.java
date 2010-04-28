package datastructures;
public class stack <T>
{
	private int m_max_size;
	private int m_top;
	private list<T> m_data;
	public stack()
	{
		m_top = -1;
		m_max_size = 0;
		m_data = new list<T>();
	}
	public void set_max_size(int new_size)
	{
		if(m_max_size < new_size)//�u���b�̤jsize�j��ssize�ɤ~���ܧ�
			m_max_size = new_size;
		else
			return;
	}
	public stack(int max_size)//�ߨ�w�]�̤j�Ȫ��غc�l
	{
		m_top = -1;
		if(max_size <= 0)
			m_max_size = 0;
		else
			m_max_size = max_size - 1;
		m_data = new list<T>();
	}
	public boolean is_empty()//stack �O�_�O�Ū�
	{
		if(m_top == -1)
			return true;
		return false;
	}
	public boolean is_full()//stack�O�_�O����
	{
		if(m_top == m_max_size)
			return true;
		return false;
	}
	public void push(T data)//�s�W�@�ӭȶi��stack
	{
		if(is_full())
		{
			return ;
		}
		m_top++;
		m_data.push_back(data);
	}
	public T pop()//��stack���X�@�ӭ�
	{
		if(is_empty())
		{
			return null;
		}
		T tmp = m_data.back();
		m_data.pop_back();
		m_top--;
		return tmp;
	}
	public int size()//�ثestack���e�ƶq
	{
		return m_top+1;
	}
//	public T get(int index)
//	{
//		if(index < 0 || index >m_top)
//			return null;
//		return m_data.get(index);
//	}
}
