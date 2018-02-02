#include<string>
#include<iostream>
using namespace std;

class students
{
private:
	int xh,grade;
	string name;
	static float sum;
	static int num;
public:
	students(int x,string n,int g){ xh=x; name=n; grade=g;  num++; }

	static float agv(students s)
	{
		float average;
		sum=sum+s.grade;
		average=sum/num;
		return average;
	}
	void show(students s)
	{
		float a;
		a=agv(s);
		cout<<xh<<" "<<name<<" "<<grade<<" "<<a<<" "<<num<<endl;
	}
};

int students::num=0;
float students::sum=0;


int main()
{
	string suu,ff,jj,ll;
	students s0(39,"suu",90);
	s0.show(s0);
	students s1(2,"ff",88);
	s1.show(s1);	
	students s2(3,"jj",92);
	s2.show(s2);
	students s3(4,"ll",85);
	s3.show(s3);
	return 0;
}