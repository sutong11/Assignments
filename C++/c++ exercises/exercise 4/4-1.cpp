#include<iostream>
#include<cmath>
using namespace std;
const double PI=3.14159;

class point
{
protected:
	double x,y;
public:
	point() { x=0; y=0; }
	point(double xv,double yv) { x=xv; y=yv; }
	point (point &p){ x=p.x; y=p.y; }     //ɾ��Ҳ�������� ϵͳ�Զ�����Ĭ�Ͽ������캯��
	double area() { return 0; }
	void show() { cout<<"x="<<x<<' '<<"y="<<y<<endl; }
};

class circle:public point
{
protected:
	double radius;
public:
	circle() { x=0; y=0; radius=0; }
	circle(double xv,double yv,double vv):point(xv,yv){ radius=vv; }
	circle(point p1,double vv):point(p1){ radius=vv; }        //�����Ϊ�����Ĺ��캯��
	circle(circle &cir):point(cir){ radius=cir.radius; }       //ɾ��Ҳ�������� ϵͳ�Զ�����Ĭ�Ͽ������캯��  
	double area(){ return PI*radius*radius; }
	void show(){ cout<<"x="<<x<<' '<<"y="<<y<<" radius="<<radius<<endl; }
};

class cylinder:public circle
{
protected:
	double height;
public:
	cylinder() { x=0; y=0; radius=0; height=0; }
	cylinder(double xv,double yv,double vv,double zv):circle(xv,yv,vv){ height=zv; }
	cylinder(cylinder &cy):circle(cy){ height=cy.height; }     //ɾ��Ҳ�������� ϵͳ�Զ�����Ĭ�Ͽ������캯��
	double area()
	{  return (2*PI*radius*radius+2*PI*radius*height); }
	void show()
	{  cout<<"x="<<x<<' '<<"y="<<y<<" radius="<<radius<<" height="<<height<<endl; }
};


int main()
{
	point pt(2,2);
	circle c11(100,100,10),c12(pt,1);
	cylinder cy1(100,100,10,10),cy2(cy1);

	cout<<"�����:"<<pt.area()<<endl;
	pt.show();

	cout<<"c11Բ���:"<<c11.area()<<endl;
	c11.show();
	cout<<"c12Բ���:"<<c12.area()<<endl;
	c12.show();

	cout<<"cy1�����:"<<cy1.area()<<endl;
	cy1.show();
	cout<<"cy2�����:"<<cy2.area()<<endl;
	cy2.show();

	return 0;
}