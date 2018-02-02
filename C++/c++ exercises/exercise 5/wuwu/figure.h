// figure.h: interface for the figure class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_FIGURE_H__0DC780F4_D58B_487B_9873_9329E4250ABD__INCLUDED_)
#define AFX_FIGURE_H__0DC780F4_D58B_487B_9873_9329E4250ABD__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class figure  
{
protected:
	double x,y;
public:
	virtual void show_area();
	void set_dim(double i,double j=0);
	figure();
	virtual ~figure();

};

#endif // !defined(AFX_FIGURE_H__0DC780F4_D58B_487B_9873_9329E4250ABD__INCLUDED_)
