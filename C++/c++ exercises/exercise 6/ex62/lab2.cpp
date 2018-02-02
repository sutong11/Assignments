/*สตั้ม๙2*/
#include <iostream>
#include "9_8.h"
using namespace std;

int main()
{
	Stack<int> s;
	int i,e,t;

	for(i=0;i<5;i++)
	{
		cin>>e;
		s.Push(e);
	}

	while(!s.StackEmpty())
	{ t=s.Pop(); cout<<t<<"  "; }

	cout<<endl;

	return 0;
}