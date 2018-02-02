// circle.h: interface for the circle class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_CIRCLE_H__8B58D6E6_B623_4FFD_AC20_127A6576CA34__INCLUDED_)
#define AFX_CIRCLE_H__8B58D6E6_B623_4FFD_AC20_127A6576CA34__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "figure.h"

class circle : public figure  
{
public:
	void show_area();
	circle();
	virtual ~circle();

};

#endif // !defined(AFX_CIRCLE_H__8B58D6E6_B623_4FFD_AC20_127A6576CA34__INCLUDED_)
