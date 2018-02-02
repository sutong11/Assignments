#include<iostream>
#include<string>
using namespace std;

class Date
{
private:
	int y,m,d;
public:
	Date(){ y=0; m=0; d=0; }
	Date(int year,int month,int day){ y=year; m=month; d=day; }
	void setdate(int year,int month,int day){ y=year; m=month; d=day; }
	void showdate(){ cout<<y<<"-"<<m<<"-"<<d<<endl<<endl; }
};

class people
{
public:
	people(){ m_no=0; m_name="  "; m_sex=" "; m_ident_no="  "; m_date; }
	people(long no,string name,string sex,string ident_no,Date date);
	people(const people & p);
	void Set(long no,string name,string sex,string ident_no,Date date);
	void Set(people & p);
	void show();
	bool operator== (people pe)
	{ 
		if(m_no==pe.m_no) 
			return true;
	    return false;  
	}
	people operator= (people p)
	{
		m_date=p.m_date;
		m_no=p.m_no;
		m_ident_no=p.m_ident_no;
		m_name=p.m_name;
		m_sex=p.m_sex;
		return people(m_no,m_name,m_sex,m_ident_no,m_date);
	}
protected:
	Date m_date;
	long m_no;
	string m_ident_no;
	string m_name;
	string m_sex;
};

people::people(long no,string name,string sex,string ident_no,Date date)
{ m_no=no; m_name=name; m_sex=sex; m_ident_no=ident_no; m_date=date; }

people::people(const people & p)
{ m_no=p.m_no; m_name=p.m_name; m_sex=p.m_sex; m_ident_no=p.m_ident_no; m_date=p.m_date; }


void people::Set(long no,string name,string sex,string ident_no,Date date)
{ m_no=no; m_name=name; m_sex=sex; m_ident_no=ident_no; m_date=date; }

void people::Set(people & p)
{  m_no=p.m_no; m_name=p.m_name; m_sex=p.m_sex; m_ident_no=p.m_ident_no; m_date=p.m_date; }

void people::show()
{ 
cout<<"no:"<<m_no<<"  name:"<<m_name<<"  sex:"<<m_sex<<"  ident_no:"<<m_ident_no<<endl;
m_date.showdate();
}

class student: virtual public people
{
protected:
	int classno;
public:
	student(people s,int classnoo):people(s),classno(classnoo){}

	void Set(people s,int classnoo)
	{ people::Set(s); classno=classnoo;}

	void Set(student s)
	{ people::Set(s); classno=s.classno; }

	void show()
	{ 
	  cout<<"编号:"<<m_no<<endl
	   <<"姓名:"<<m_name<<endl
	   <<"性别:"<<m_sex<<endl
	   <<"身份证号:"<<m_ident_no<<endl
	   <<"班号:"<<classno<<endl;
	  cout<<"日期:"; m_date.showdate();
	}
};

class teacher:virtual public people
{
protected:
	string principalship;
	string department;
public:
	teacher( people t,string prin,string depart):people(t)
	{
		principalship=prin;
		department=depart;
	}
	void Set(people p,string prin,string depart)
	{ people::Set(p); principalship=prin;	department=depart;}

	void Set(teacher t)
	{people::Set(t); principalship=t.principalship; department=t.department; }

	string getname(){ return m_name; }

	void show()
	{ 
	  cout<<"编号:"<<m_no<<endl
	   <<"姓名:"<<m_name<<endl
	   <<"性别:"<<m_sex<<endl
	   <<"身份证号:"<<m_ident_no<<endl
	   <<"职务:"<<principalship<<endl
	   <<"部门:"<<department<<endl;
	  cout<<"日期:"; m_date.showdate();
	}
};

class graduate:virtual public student
{
protected:
        string subject;
        teacher teac;
public:
     	graduate(student st,string sub,teacher t):people(st),student(st),subject(sub),teac(t){}

		void Set(student st,string sub,teacher t)
		{student::Set(st); subject=sub; teac.Set(t); }

		void Set(graduate ga)
		{ student::Set(ga); subject=ga.subject; teac.Set(ga.teac);}

	    void show()
		{ 
		  cout<<"编号:"<<graduate::m_no<<endl
	       <<"姓名:"<<graduate::m_name<<endl
	       <<"性别:"<<graduate::m_sex<<endl
	       <<"身份证号:"<<graduate::m_ident_no<<endl
	       <<"班号:"<<classno<<endl
	       <<"专业:"<<subject<<endl
	       <<"导师:"<<teac.getname()<<endl;
	      cout<<"日期:"; m_date.showdate();
		}
};

class TA:public teacher,public graduate
{
public:
	TA(teacher tt,graduate gra):teacher(tt),people(gra),student(gra),graduate(gra){}

	void Set(teacher tt,graduate ga)
	{teacher::Set(tt); graduate::Set(ga); }

	void show()
	{ cout<<"编号:"<<graduate::m_no<<endl
	   <<"姓名:"<<graduate::m_name<<endl
	   <<"性别:"<<graduate::m_sex<<endl
	   <<"身份证号:"<<graduate::m_ident_no<<endl
	   <<"班号:"<<classno<<endl
	   <<"专业:"<<subject<<endl
	   <<"导师:"<<teac.getname()<<endl
	   <<"导师职务:"<<principalship<<endl
	   <<"导师部门:"<<department<<endl;
	  cout<<"日期:"; m_date.showdate();
	}
};

int main()
{
	Date d1(2012,4,25),d2(2012,4,10);
	people peo1(1,"nate","m","210105",d1),peo2(2,"chuck","m","210106",d2);
	teacher te1(peo2,"teach","教务处");
	student stu1(peo1,1102);
	graduate gdt1(stu1,"math",te1);
	TA ta1(te1,gdt1);
	ta1.show();

	if(peo1==peo2)
		cout<<"Id equal"<<endl;
	else
		cout<<"Id not equal"<<endl;

	people pp;
	pp=peo1;
	pp.show();

	peo2.Set(3,"dan","f","210107",d1);
    te1.teacher::Set(peo2,"TEACH","教学");
	stu1.student::Set(peo1,1103);
	gdt1.graduate::Set(stu1,"math",te1);
	ta1.TA::Set(te1,gdt1);
	ta1.show();
	return 0;
}


