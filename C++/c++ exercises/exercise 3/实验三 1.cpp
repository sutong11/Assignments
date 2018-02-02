#include<iostream>
using namespace std;
const int maxNum=20;

class Set
{
private:
	int size;
	int num;
	int *elems;
public:
	Set(int sz=maxNum);
	~Set();
	bool IsMember(int);
	bool AddElement(int);
	void RemoveElement(int);
	void copy(Set &);
	void show();
	void Intersect(Set &,Set &);
	bool Union(Set &,Set &);
};

inline Set::Set(int sz)
{
	num=0;
	size=sz;
	elems=new int[size];
}

inline Set::~Set() { delete []elems; }

bool Set::IsMember(int e)
{
	for(int i=0;i<num;i++)
		if(elems[i]==e)   return true;
	return false;
}

bool Set::AddElement(int elem)
{
	if(IsMember(elem))  return true;   //如果elem在里面返回ture
	if(num<size)
	{
		elems[num++]=elem;
		return true;
	}
	return false;
}

void Set::RemoveElement(int c)
{
	int index=-1;
	for(int i=0;i<num;i++)
		if(elems[i]==c)
		{
			index=i;
			break;
		}
	if(index!=-1)
	{
		for(int i=index;i<num-1;i++)
			elems[i]=elems[i+1];
		num--;
	}
}

void Set::copy(Set &s)
{
	if(size<s.size)
	{
		delete []elems;
		elems=new int[s.size];
		size=s.size;
	}
	for(int i=0;i<s.num;i++)
		elems[i]=s.elems[i];
	num=s.num;
}

void Set::show()
{
	cout<<"{";
	for(int i=0;i<num-1;i++)  cout<<elems[i]<<",";
	if(num>0)  cout<<elems[num-1];
	cout<<"}"<<endl;
}

void Set::Intersect(Set &s1,Set &s2)
{
	if(size<s1.size)
	{
		delete []elems;
		elems=new int[s1.size];
		size=s1.size;
	}
	num=0;
	for(int i=0;i<s1.num;i++)
		if(s2.IsMember(s1.elems[i]))  elems[num++]=s1.elems[i];
}

bool Set::Union(Set &s1,Set &s2)
{
	delete []elems;
	elems=new int[s1.size+s2.size];
	size=s1.size+s2.size;
	num=0;
	copy(s1);
	for(int i=0;i<s2.num;i++)
		if(!s1.IsMember(s2.elems[i]))   elems[num++]=s2.elems[i];
	return true;
}

int main()
{
	Set s1,s2,s3;
	int i;
	for(i=0;i<5;i++) s1.AddElement(2*i);
	for(i=0;i<5;i++) s2.AddElement(3*i);
	cout<<"s1=";s1.show();
	cout<<"s2=";s2.show();

	s2.RemoveElement(9);
	cout<<"s2-{9}=";
	s2.show();

	if(s1.IsMember(2)) cout<<"2 is in s1\n";
	else cout<<"2 is not in s1\n";

	s3.Intersect(s1,s2);
	cout<<"s1 intset s2=";
	s3.show();

	s3.Union(s1,s2);
	cout<<"s1 union s2=";
	s3.show();

	return 0;
}