#include<iostream>
using namespace std;

class circle
{
public:
	circle(double r=0.0)
	{
		radius=r;
		c=3.14*2*radius;
		s=3.14*radius*radius;
	}
	circle(circle& A);
	double cc() {return c;}
	double ss() { return s;}
private:
	double radius,c,s;
};

void main()
{
	double r;
	cout<<"输入半径:";
	cin>>r;
	circle CR(r);
	cout<<"周长="<<CR.cc()<<"面积="<<CR.ss();
}