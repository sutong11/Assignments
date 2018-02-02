#include<iostream>
#define N 3
using namespace std;

class sample
{
private:
	int No;
	char name[5];
	int score;
public:
	sample(){ No=0; name[0]= 'a'; score=60; }
	void set()
	{
		int n,sco; char a;  
		cout<<"输入学号:"<<endl;
		cin>>n;
		No=n;
		cout<<"输入姓名:"<<endl;
		for(int i=0;i<5;i++)
		{cin>>a;name[i]=a;}
		cout<<"输入成绩:"<<endl;
		cin>>sco;
		score=sco;
	}
    void show()
	{
		for(int i=0;i<5;i++)
			cout<<name[i];
		cout<<No<<","<<score<<endl;
	}
	friend void sort(sample stud[]);
};

void sort(sample stud[])
{
	int max=0;
	for(int i=0;i<N;i++)
		if(stud[i].score>stud[i+1].score) {  stud[i+1]=stud[i]; max=stud[i+1].score; }
	    else max=stud[i+1].score;
	cout<<stud[N-1].No<<endl;
			
}


int main()
{
	sample stu[10];
	for(int i=0;i<N;i++)
		stu[i].set();
    for(i=0;i<N;i++)
		stu[i].show();
	sort(stu);
	return 0;
}