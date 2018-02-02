// triangle.h: interface for the triangle class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_TRIANGLE_H__448A2357_BC9E_441E_8492_7FA9937BD722__INCLUDED_)
#define AFX_TRIANGLE_H__448A2357_BC9E_441E_8492_7FA9937BD722__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "figure.h"

class triangle : public figure  
{
public:
	void show_area();
	triangle();
	virtual ~triangle();

};

#endif // !defined(AFX_TRIANGLE_H__448A2357_BC9E_441E_8492_7FA9937BD722__INCLUDED_)
