#ifndef _SORT_H_
#define _SORT_H_

//template<class T>
//void swap(T &x, T &y)
//{
//	T temp;
//	temp = x;
//	x = y;
//	y = temp;
//}

template<class T>
void swap(T &x, T &y)
{
	x = x + y;
	y = x - y;
	x = x - y;
}


template<class T>
void select_sort(T &n)
{
	int min ;
	for(int i = 0; i < sizeof(n)/sizeof(n[0]) - 1; i++)
	{
		min = i;
		//��X�̤p��
		for(int j = i+1; j <= sizeof(n)/sizeof(n[0]) - 1; j++)
			if(n[j] < n[min])
				min = j;
		//��̤p�ȸ��i�ӥ洫
		if(min != i)
		{
			swap(n[i], n[min]);
		}
	}
}

template<class T>
void select_sort(T n[])
{
	int min ;
	for(int i = 0; i < sizeof(n) - 1; i++)
	{
		min = i;
		//��X�̤p��
		for(int j = i+1; j <= sizeof(n) - 1; j++)
			if(n[j] < n[min])
				min = j;
		//��̤p�ȸ��i�ӥ洫
		if(min != i)
		{
			swap(n[i], n[min]);
		}
	}
}


template<class T>
void bubble_sort(T &n)
{
	//���n-1��
	for(int i = sizeof(n)/sizeof(n[0]) - 1; i > 0; i--)
	{
		for(int j = 1; j <= i; j++)
			if(n[j-1] > n[j])
			{
				//�Yj-1�j��j�h��ƥ洫
				swap(n[j-1], n[j]);
			}
	}
}

template<class T>
void bubble_sort(T n[])
{
	//���n-1��
	for(int i = sizeof(n) - 1; i > 0; i--)
	{
		for(int j = 1; j <= i; j++)
			if(n[j-1] > n[j])
			{
				//�Yj-1�j��j�h��ƥ洫
				swap(n[j-1], n[j]);
			}
	}
}

template<class T>
void insertion_sort(T n[])
{
	for(int i = 1; i < sizeof(n); i++)
	{
		T target = n[i];
		int j = i;
		while((n[j-1] > target) && (j > 0))
		{
			n[j] = n[j-1];
			j--;
		}
		n[j] = target;
	}
}


#endif //_SORT_H_