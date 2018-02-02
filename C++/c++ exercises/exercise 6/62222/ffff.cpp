/*สตั้ม๙1*/
#include<iostream>
#include "9_6.h"
#include "9_6.cpp"
using namespace std;

int main()
{
	LinkedList<int> A;
	LinkedList<int> B;
	
	int i,e;
	for(i=0;i<5;i++)
	{
		cin>>e;
		A.InsertFront(e);
	}
	for(i=0;i<5;i++)
	{
		cin>>e;
		B.InsertFront(e);
	}
	B.Reset();

	while(!B.EndOfList())
	{
		A.InsertRear(B.Data());
		B.Next();
	}
	A.Reset();
	while(!A.EndOfList())
	{
		cout<<A.Data()<<" ";
		A.Next();
	}
	cout<<endl;

	return 0;
}