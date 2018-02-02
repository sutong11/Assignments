#include<iostream>
#include<string>
using namespace std;
class student{
public:
	student(long n,string s,int t)
	{
		number=n;
		name=s;
        m=t;
		num++;
		sum+=m;
	}
	student(student &s){
		number=s.number;
		name=s.name;
		m=s.m;
	}
	~student(){num--;}
	void show();
	static void agv();
private:
	static int sum;
	static int num;
	long number;
	string name;
	int m;
};
int student::sum=0;
int student::num=0;
void student::show()
{
	cout<<"输出学生信息："<<endl;
	cout<<number<<" "<<name<<" "<<m<<endl;
	cout<<num<<endl;
}
void student::agv()
{
	float agv;
	agv=sum/num;
	cout<<agv<<endl;
}
int main()
{
	student a(8042,"su",99);
		a.show();
     student b(8043,"lu",88);
     	b.show();
      student c(8044,"hu",77);
		c.show();
		student::agv();
		return 0;
}
