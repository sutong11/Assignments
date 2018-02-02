#include<iostream>
#include<string>
using namespace std;

class teacher
{
protected:
	string name1; char n1; int age1; string ename; long tp;
public:
	teacher(){ name1="aaa"; n1='f'; age1=30; ename="dk"; tp=12345678; }
	teacher(string na, char m, int ag, string ena, long p) { name1=na; n1=m; age1=ag; ename=ena; tp=p; }
	void show()
	{
	cout<<"name:"<<name1<<" sex:"<<n1<<" age:"<<age1<<" ename:"<<ename<<" phone:"<<tp<<endl; }
};

class leader
{
protected:
	string name2; char n2; int age2; string job; long tph;
public:
	leader(){ name2="aa"; n2='m'; age2=35; job="dk"; tph=87654321; }
	leader(string na, char m, int ag, string j, long p) { name2=na; n2=m; age2=ag; job=j; tph=p; }
	void show()
	{ cout<<"name:"<<name2<<" "<<"sex:"<<n2<<" "<<"age:"<<age2<<" "<<"job:"<<job<<" "<<"phone:"<<tph<<endl; }
};

class both:public teacher,public leader
{
protected: int salary;
public:
	both(string na, char m, int ag, string j, string ena, long p, int sal):
	  teacher(na,m,ag,ena,p),leader(na,m,ag,j,p)
	  { salary=sal; }
	 void show()
	 { cout<<"name:"<<name1<<endl<<"sex:"<<n1<<endl<<"age:"<<age1<<endl<<"job:"<<job<<endl<<"ename:"<<ename<<endl<<"phone:"<<tph<<endl<<"salary:"<<salary<<endl; }
};

int main()
{
	both bo1("suu",'f',28,"teach","½ÌÊ¦",67854323,4000);
	bo1.show();
	bo1.leader::show();

	return 0;
}


