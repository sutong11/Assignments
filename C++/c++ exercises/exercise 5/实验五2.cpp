/*实验五（二）2*/
#include<iostream>
#include<cmath>
using namespace std;
const double PI=3.14159;

class element
{
public:
	virtual void area()=0;
	virtual void show()=0;
};
class point:public element
{
protected:
	double x,y;
public:
	point() { x=0; y=0; }
	point(double xv,double yv) { x=xv; y=yv; }
	void area() { cout<<"0"<<endl; }
	void show() { cout<<"x="<<x<<' '<<"y="<<y<<endl; }
};

class circle:public point
{
protected:
	double radius;
public:
	circle() { x=0; y=0; radius=0; }
	circle(double xv,double yv,double vv):point(xv,yv){ radius=vv; }
	circle(circle &cir):point(cir){ radius=cir.radius; }
	void area(){ cout<<PI*radius*radius<<endl; }
	void show(){ cout<<"x="<<x<<' '<<"y="<<y<<" radius="<<radius<<endl; }
};

class cylinder:public circle
{
protected:
	double height;
public:
	cylinder() { x=0; y=0; radius=0; height=0; }
	cylinder(double xv,double yv,double vv,double zv):circle(xv,yv,vv){ height=zv; }
	cylinder(cylinder &cy):circle(cy){ height=cy.height; }
	void area(){ cout<<PI*radius*radius*height<<endl; }
	void show(){ cout<<"x="<<x<<' '<<"y="<<y<<" radius="<<radius<<" height="<<height<<endl; }
};

void fun(element *e)
{
	e->area();
	e->show();
}

int main()
{
	point pt(0,0);
	circle c11(100,100,10),c12(c11);
	cylinder cy1(100,100,10,10),cy2(cy1);

	fun(&pt);
	fun(&c11);
	fun(&cy1);

	return 0;
}