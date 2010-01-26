#ifndef _SORT_H_
#define _SORT_H_

template<class T>
void swap(T &x, T &y)
{
	T temp;
	temp = x;
	x = y;
	y = temp;
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

#endif //_SORT_H_