#include<iostream>
using namespace std;
#include "figure.h"
#include "triangle.h"
#include "square.h"
#include "circle.h"
int main()
{
	figure *p;
	triangle t;
	square s;
	circle c;
	p=new triangle();
	p->set_dim(100.0,50.0);
	p->show_area();
	delete p;
	p=new square();
	p->set_dim(100.0,50.0);
	p->show_area();
	delete p;
	p=new circle();
	p->set_dim(100.0,50.0);
	p->show_area();
	delete p;

	return 0;
}