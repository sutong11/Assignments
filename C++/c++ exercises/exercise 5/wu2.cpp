/*实验五（一）1 友元函数 */
#include<iostream>
#include<cmath>
using std::cout;
using std::endl;

class Rectangle
{
public:
	Rectangle()
	{
	left=0;top=0;
	right=0;bottom=0;
	}
	Rectangle(double l,double t,double r,double b)//如果把初始值去掉 则主函数里Rectangle rect;变为Rectangle rect（0，0，0，0）;
	{
    	left=l;top=t;
    	right=r;bottom=b;
	}
	void Assign(double l,double t,double r,double b)
	{
	left=l;top=t;
	right=r;bottom=b;
	}
	Rectangle operator+= (Rectangle rr)
	{
		right=right+(rr.right-rr.left);
		bottom=bottom-(rr.top-rr.bottom);
		return Rectangle(left,top,right,bottom);
	}

	Rectangle operator-= (Rectangle rr)
	{
		right=left+fabs((right-left)-(rr.right-rr.left));
		bottom=top-fabs((top-bottom)-(rr.top-rr.bottom));
		return Rectangle(left,top,right,bottom);
	}

 	void Show()
	{
	cout<<"left-top point is("<<left<<","<<top<<")"<<'\n';
    cout<<"right-bottom point is("<<right<<","<<bottom<<")"<<'\n';
	}
	friend Rectangle operator+ (Rectangle r1,Rectangle r2);
	friend Rectangle operator- (Rectangle r1,Rectangle r2);
private:
	double left,top;
	double right,bottom;
};

	Rectangle operator+ (Rectangle r1,Rectangle r2)
	{
		double ri,bo;
		ri=r1.right+(r2.right-r2.left);
		bo=r1.bottom-(r2.top-r2.bottom);
		return Rectangle(r1.left,r1.top,ri,bo);
	}

	Rectangle operator- (Rectangle r1,Rectangle r2)
	{
		double ri,bo;
		ri=r1.left+fabs((r1.right-r1.left)-(r2.right-r2.left));
		bo=r1.top-fabs((r1.top-r1.bottom)-(r2.top-r2.bottom));
		return Rectangle(r1.left,r1.top,ri,bo);
	}

int main()
{
	Rectangle rect(0,0,0,0),rect2;
//	rect.Show();
	rect.Assign(100,400,300,200);
	rect.Show();
	Rectangle rect1(200,400,500,100);
	rect+= rect1;
	rect.Show();
//	rect-= rect1;
//	rect.Show();
	rect2=rect+rect1;
	rect.Show();
	rect2.Show();
//	rect2=rect-rect1;
//	rect2.Show();
	return 0;
}