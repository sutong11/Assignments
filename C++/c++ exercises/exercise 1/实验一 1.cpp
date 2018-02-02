#include<iostream>
#include<cmath>
using namespace std;

class Rectangle
{
public:
	Rectangle(double l,double t,double r,double b); //如果把初始值去掉 则主函数里Rectangle rect;变为Rectangle rect（0，0，0，0）;
	~Rectangle(){};
	void Assign(double l,double t,double r,double b);
	void Show();
	double Area();
	double Perimeter();
private:
	double left,top;
	double right,bottom;
};
Rectangle::Rectangle(double l,double t,double r,double b)
{
	left=l;top=t;
	right=r;bottom=b;
}

void Rectangle::Assign(double l,double t,double r,double b)  //不能去掉ASSIGN函数，它用来修改数据，和构造函数这是赋初始值
{
	left=l;top=t;
	right=r;bottom=b;
}

void Rectangle::Show()
{
	cout<<"left-top point is("<<left<<","<<top<<")"<<'\n';
    cout<<"right-bottom point is("<<right<<","<<bottom<<")"<<'\n';
}

double Rectangle::Area()
{
	return fabs((right-left)*(bottom-top));
}

double Rectangle::Perimeter()
{
	return 2*(fabs(right-left)+fabs(bottom-top));
}

int main()
{
	Rectangle rect(0,0,0,0);
	rect.Show();
	rect.Assign(100,200,300,400);
	rect.Show();

	Rectangle rect1(0,0,200,200);
	rect1.Show();
	cout<<"面积"<<rect.Area()<<'\t'<<"周长"<<rect.Perimeter()<<endl;
	cout<<"面积"<<rect1.Area()<<'\t'<<"周长"<<rect1.Perimeter()<<endl;
	return 0;
}