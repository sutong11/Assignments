/*实验六3 课程链表*/
#include<iostream>
#include<string>
#include"9_6.h"
#include"9_6.cpp"
using namespace std;

class Date
{
private:
	int y,m,d;
public:
	Date(){ y=0; m=0; d=0; }
	Date(int year,int month,int day){ y=year; m=month; d=day; }
	void setdate(int year,int month,int day){ y=year; m=month; d=day; }
	void showdate(){ cout<<y<<"-"<<m<<"-"<<d<<endl<<endl; }
};

class course
{
protected:
	string coname;
	int score;
public:
	course(){ coname="  "; score=0; }
	course(string co,int sc){ coname=co; score=sc; }
	void Show()
	{
		cout<<"课程名称:"<<coname<<endl;
		cout<<"成绩:"<<score<<endl;
	}
};

class people
{
public:
	people(long no,string name,string sex,string ident_no,Date date);
	people(const people & p);
	void show();
protected:
	Date m_date;
	long m_no;
	string m_ident_no;
	string m_name;
	string m_sex;
};

people::people(long no,string name,string sex,string ident_no,Date date)
{ m_no=no; m_name=name; m_sex=sex; m_ident_no=ident_no; m_date=date; }

people::people(const people & p)
{ m_no=p.m_no; m_name=p.m_name; m_sex=p.m_sex; m_ident_no=p.m_ident_no; m_date=p.m_date; }

void people::show()
{ 
cout<<"no:"<<m_no<<"  name:"<<m_name<<"  sex:"<<m_sex<<"  ident_no:"<<m_ident_no<<endl;
m_date.showdate();
}

class student:public people
{
protected:
	int classno;
	LinkedList<course>courses;
public:
	student(people s,int classnoo,LinkedList<course> c):people(s){ classno=classnoo; courses=c; }
	student(student &s):people(s)
	{
		classno=s.classno;  courses=s.courses;
	}
	void show()
	{ 
	  cout<<"编号:"<<m_no<<endl
	   <<"姓名:"<<m_name<<endl
	   <<"性别:"<<m_sex<<endl
	   <<"身份证号:"<<m_ident_no<<endl
	   <<"班号:"<<classno<<endl;
	  cout<<"日期:"; m_date.showdate();
	}
};

int main()
{
	string a;
	int b;
	LinkedList<course> list;
	Date d(2012,5,10);
	people peo1(1,"nate","m","210105",d);
	student st1(peo1,1102,list);
	for(int i=0;i<3;i++)
	{
		cout<<"输入课程名称和成绩:"<<endl;
	    cin>>a>>b;
	    list.InsertFront(course(a,b));	
	}

	st1.show();
	list.Reset();
	while(!list.EndOfList())
	{
		list.Data().Show();
		list.Next();
	}
	cout<<endl;

	return 0;
}
