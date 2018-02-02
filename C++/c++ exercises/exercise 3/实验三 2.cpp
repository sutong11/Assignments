#include<iostream>
using namespace std;
#include<string>

class strclass
{
private:
	char *p;
	int length;
public:
	strclass()
	{
		p=new char[128];
		p="   ";
		length=128;
	}
	strclass(char t[],int len)
	{
		if(len<strlen(t))
			p=new char[strlen(t)];
		else p=new char[len];
		p=t;
		length=strlen(t);
	}
	char *getstring(){ return p; }
	int getlength(){ return length; }
};

int main()
{
	char *s="hello!";
	strclass str1(s,10);
	cout<<str1.getstring()<<str1.getlength()<<endl;
	strclass g;
	cout<<g.getstring()<<g.getlength()<<endl;
	return 0;
}