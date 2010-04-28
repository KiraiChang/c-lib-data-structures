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
		if(m_max_size < new_size)//只有在最大size大於新size時才能變更
			m_max_size = new_size;
		else
			return;
	}
	public stack(int max_size)//立刻預設最大值的建構子
	{
		m_top = -1;
		if(max_size <= 0)
			m_max_size = 0;
		else
			m_max_size = max_size - 1;
		m_data = new list<T>();
	}
	public boolean is_empty()//stack 是否是空的
	{
		if(m_top == -1)
			return true;
		return false;
	}
	public boolean is_full()//stack是否是滿的
	{
		if(m_top == m_max_size)
			return true;
		return false;
	}
	public void push(T data)//新增一個值進到stack
	{
		if(is_full())
		{
			return ;
		}
		m_top++;
		m_data.push_back(data);
	}
	public T pop()//由stack提出一個值
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
	public int size()//目前stack內容數量
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
