// square.h: interface for the square class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_SQUARE_H__E487489A_7980_40A3_A60D_531982CB50D5__INCLUDED_)
#define AFX_SQUARE_H__E487489A_7980_40A3_A60D_531982CB50D5__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "figure.h"

class square : public figure  
{
public:
	void show_area();
	square();
	virtual ~square();

};

#endif // !defined(AFX_SQUARE_H__E487489A_7980_40A3_A60D_531982CB50D5__INCLUDED_)
