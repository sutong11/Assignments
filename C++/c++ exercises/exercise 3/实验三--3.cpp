#include<iostream>
#include<string>
#define N 3
using namespace std;

class sample
{
private:
	int No;
	string str;
	int score;
public:
	sample()
	{
		No=0; 
		str="aaaa";
		score=60; 
	}

	sample(int m,string r,int re)  { No=m; str=r; score=re; }

	void set(int n,string s,int sc)
	{ 
	
		No=n;
		str=s;
		score=sc;
	}
    void show()
	{
		cout<<str<<","<<No<<","<<score<<endl;
	}
	friend void max(sample stud[]);
};

void max(sample stud[])
{
	sample b(0,"mnb",0);
	for(int i=0;i<N;i++)
		if(stud[i].score>b.score)  b=stud[i];
	cout<<b.No<<endl;			
}


int main()
{
	int n,sco; string t;
	sample stu[N];
	for(int i=0;i<N;i++)
	{ 
		cout<<"����ѧ��:"<<endl;
		cin>>n;
		cout<<"��������:"<<endl;
		cin>>t;
		cout<<"����ɼ�:"<<endl;
		cin>>sco;
		stu[i].set(n,t,sco);
	}
    for(i=0;i<N;i++)
		stu[i].show();
	max(stu);
	return 0;
}