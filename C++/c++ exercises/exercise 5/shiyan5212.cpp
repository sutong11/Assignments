/*实验五（二）1（4）动态定义*/
#include<iostream>
using namespace std;

class figure
{
protected:
	double x,y;
public:
	virtual ~figure(){ cout<<"11111111111"<<endl;}
	void set_dim(double i,double j=0)
	{ x=i; y=j; }
	virtual void show_area()
	{ cout<<"No area computation defined for this class\n"; }
};

class triangle:public figure
{
	~triangle(){ cout<<"2222222222222"<<endl;}
	void show_area()
	{ cout<<"Triangle with height"<<x<<" and base "<<y; 
	  cout<<" has an area of "<<x*0.5*y<<endl;
	}
};

class square:public figure
{
	~square(){ cout<<"3333333333"<<endl;}
	void show_area()
	{ cout<<"Square with dimensions "<<x<<" and "<<y; 
	  cout<<" has an area of "<<x*y<<endl;
	}
};

class circle:public figure
{
	~circle(){ cout<<"4444444444"<<endl;}
	void show_area()
	{ cout<<"Circle with radius "<<x;; 
	  cout<<" has an area of "<<3.14159*x*x<<endl;
	}
};

void fun(figure *p)
{	delete p;  }

int main()
{
	figure *p;
//	triangle t;
//	square s;
//	circle c;
	p=new triangle();
	p->set_dim(10.0,5.0);
	p->show_area();
	fun(p);
	p=new square();
	p->set_dim(10.0,5.0);
	p->show_area();
	fun(p);
	p=new circle();
	p->set_dim(10.0);
	p->show_area();
	fun(p);

	return 0;
}