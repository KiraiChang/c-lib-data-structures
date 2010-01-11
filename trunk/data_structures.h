#ifndef _DATA_STRUCTURES_H_
#define _DATA_STRUCTURES_H_


//################################################################################
//###############################Double Link List#####################################
//################################################################################

template<class T>
class list
{
private:
	struct node;
	node *mp_head;
	node *mp_tail;
	node *mp_current;
	node *mp_free_list;
	unsigned m_length;
public:
	class iterator;
	list(void):mp_head(NULL),mp_current(NULL),mp_tail(NULL),mp_free_list(NULL),m_length(0)			{init();}
	~list(void)																																				{clear();}
	void init(void);
	void clear(void);
	unsigned size(void)const																														{return m_length;}
	iterator end(void)const																															{return mp_tail;}
	iterator begin(void)const																														{return mp_head;}
	T &back(void)const																																{return mp_current->m_data;}
	T const_back(void)const																														{return mp_current->m_data;}
	node *free_node(node *free_node)
	{
		node *tmp;
		tmp = free_node;
		free_node = free_node->mp_next;
		if(free_node)
			free_node->mp_prev = NULL;
		delete tmp;
		tmp = NULL;
		return free_node;
	}
	node *get_node()
	{
		if(mp_free_list == NULL)
		{
			node *tmp = new node;
			return tmp;
		}
		else
		{
			node *tmp = mp_free_list;
			mp_free_list = (*mp_free_list).mp_next;
			return tmp;
		}
	}
	void put_node(node *earse);
	void insert(iterator position,const T &data);
	void push_front(const T &data)	;
	void push_back(const T &data);
	iterator erase(iterator position)
	{
		if((*position.mp_node).mp_prev == NULL && (*position.mp_node).mp_next == NULL)
		{
			//last one;
			put_node(position.mp_node);
			--m_length;
			mp_head = mp_tail = mp_current =NULL;
			position.mp_node = NULL;
			return position;
		}
		if((*position.mp_node).mp_prev == NULL)
		{
			//mp_head;
			(*((*position.mp_node).mp_next)).mp_prev = NULL;
			mp_head = (*position.mp_node).mp_next;
			mp_current = mp_head;
			put_node(position.mp_node);
			--m_length;
			position.mp_node = mp_current;
			return position;
		}
		else if((*position.mp_node).mp_next == NULL)
		{
			//mp_tail;
			(*((*position.mp_node).mp_prev)).mp_next = NULL;
			mp_tail = (*position.mp_node).mp_prev;
			mp_current = mp_tail;
			put_node(position.mp_node);
			--m_length;
			position.mp_node = mp_current;
			return position;
		}
		else
		{
			//other position;
			(*((*position.mp_node).mp_prev)).mp_next = (*position.mp_node).mp_next;
			(*((*position.mp_node).mp_next)).mp_prev = (*position.mp_node).mp_prev;
			mp_current = (*position.mp_node).mp_prev;
			put_node(position.mp_node);
			--m_length;
			position.mp_node = mp_current;
			return position;
		}
	}
	void set_current(iterator current)																												{mp_current = current.mp_node;}
	void pop_front(void)																																	{erase(begin());}
	void pop_back(void)																																{erase(end());}
	void set_id(unsigned short year, unsigned short month, unsigned short day, unsigned short id);
	unsigned short get_year(void)const																											{return m_year;}
	unsigned short get_month(void)const																											{return m_month;}
	unsigned short get_day(void)const																												{return m_day;}
	unsigned short get_id(void)const																												{return m_id;}
};

template<class T>
struct list<T>::node
{
	T m_data;
	node *mp_next;
	node *mp_prev;
	node():mp_next(NULL),mp_prev(NULL)																							{}
};

template<class T>
class list<T>::iterator
{
protected:
	node *mp_node;
	iterator(node *newptr):mp_node(newptr)																							{}
public:
	iterator()																																			{}
	T &operator*()const																															{return (*mp_node).m_data;}
	iterator &operator++()																														{mp_node =(*mp_node).mp_next;return *this;}
	iterator &operator--()																														{mp_node =(*mp_node).mp_prev;return *this;}
	iterator operator++(int)																														{iterator temp = *this;++*this;return temp;}
	iterator operator--(int)																														{iterator temp = *this;--*this;return temp;}
	bool operator==(iterator tmp)																											{return tmp.mp_node == mp_node;}
	bool operator!=(iterator tmp)																											{return tmp.mp_node != mp_node;}
	friend class list<T>;
};

template<class T>
void list<T>::init(void)
{
}

template<class T>
void list<T>::clear(void)
{
	list<T>::iterator pi = begin();
	while(pi != NULL)
	{
		pi = erase(pi);
	}
	while(mp_free_list != NULL)
	{
		mp_free_list = free_node(mp_free_list);
	}
}

template<class T>
void list<T>::put_node(node *erase)
{
	erase->mp_next = mp_free_list;
	erase->mp_prev = NULL;
	mp_free_list = erase;
}


template<class T>
void list<T>::insert(iterator position,const T &data)
{
	node *tmp = get_node();
	tmp->m_data = data;
	(*tmp).mp_next = (*(position.mp_node)).mp_next;
	(*(position.mp_node)).mp_next = tmp;
	(*tmp).mp_prev = position.mp_node;
	(*position.mp_node).mp_prev = tmp;
	(*(*tmp).mp_prev).mp_next = tmp;
	mp_current = tmp;
	++m_length;
}

template<class T>
void list<T>:: push_front(const T &data)
{
	mp_current = get_node();
	(*mp_current).m_data = data;
	(*mp_current).mp_next = mp_head;
	(*mp_current).mp_prev = NULL;
	m_length++;
	if(mp_head == NULL)
	{
		mp_head = mp_current;
		mp_tail = mp_head;
	}
	else
	{
		(mp_head).prev = mp_current;
		mp_head = mp_current;
	}
}
	
template<class T>
void list<T>:: push_back(const T &data)
{
	mp_current = get_node();
	(*mp_current).m_data = data;
	(*mp_current).mp_next = NULL;
	(*mp_current).mp_prev = mp_tail;
	m_length++;
	if(mp_tail == NULL)
	{
		mp_tail = mp_current;
		mp_head = mp_tail;
	}
	else
	{
		(*mp_tail).mp_next = mp_current;
		mp_tail = mp_current;
	}
}

#endif //_DATA_STRUCTURES_H_