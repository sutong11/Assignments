#include<string>
#include<iostream>
using namespace std;

class Date
{
private:
	int y,m,d;
public:
	Date(){ y=0; m=0; d=0; }

	Date(Date &p){ y=p.y; m=p.m; d=p.d; }

	~Date(){};

	void setdate(){int a,b,c; cin>>a>>b>>c; y=a; m=b; d=c; }

	void showdate(){ cout<<y<<"-"<<m<<"-"<<d; }
};

class Employee
{
private:
	int num; string u;char sex;	Date D; long id;

public:

	Employee(){num=0; u="kk"; sex='m'; D; id=0;}

	Employee(Employee &e):D(e.D)
	{ num=e.num; u=e.u; sex=e.sex;id=e.id; }

	~Employee(){}

	void Add();

	void show(Date e);


};

void Employee::show(Date e)
{
	cout<<num<<endl;
    cout<<u<<endl;
	cout<<sex<<endl;
	e.showdate();
	cout<<endl<<id<<endl;
}

void Employee::Add()
{
	int n; string t; char s; long b;
	cout<<"输入编号"<<endl;
	cin>>n;
	num=n;
	cout<<"输入姓名"<<endl;
	cin>>t;
	u=t;
	cout<<"输入性别"<<endl;
	cin>>s;
	sex=s;
	cout<<"输入身份证号"<<endl;
	cin>>b;
	id=b;
}

int main()
{
	Date D1;
	D1.setdate();
	Employee E1;
	E1.Add();
	E1.show(D1);
	return 0;
}
