#include<iostream>
using namespace std;

class circle
{
public:
	circle(double r)
	{radius=r;}
	double c();
	double s();
private:
	double radius;
};
double circle::c()
{
	return 3.14*2*radius;
}

double circle::s()
{ return 3.14*radius*radius;}

void main()
{
	double radius;
	cout<<"ÊäÈë°ë¾¶:";
	cin>>radius;
	circle A(radius);
	double cc=A.c();
	double ss=A.s();
	cout<<cc<<"  "<<ss;
}