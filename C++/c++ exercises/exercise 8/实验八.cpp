#include<cstdlib>
#include<fstream>
#include<iostream>
using namespace std;

const int n=20;
void sort(int [],int);

int main()
{
	fstream dat,out;
	int i,a[n],b[n];

	dat.open("file.dat",ios::out|ios::in|ios::binary);
	if(!dat)
	{
		dat.clear(0);
		dat.open("file.dat",ios::out|ios::binary);
		dat.close();
		dat.open("file.dat",ios::out|ios::in|ios::binary);
		if(!dat) { cout<<"cannot open file\n"; return -1; }
	}

	for(i=0;i<n;i++)
	{
		a[i]=rand();
		dat.write((char*)&a[i],sizeof(int));
	}

	dat.seekg(0);
	for(i=0;i<n;i++)
	{ dat.read((char*)&b[i],sizeof(int)); }

	sort(b,n);

	for(i=0;i<n;i++)
	{
		cout<<b[i]<<'\t';
		if(i%5==4)  cout<<'\n';
	}
	cout<<endl;
	out.open("file.out",ios::out);
	if(!out)
	{
		cout<<"cannot open file\n"; return -1;
	}
	for(i=0;i<n;i++)
	{
		out<<b[i]<<'\t';
		if(i%5==4)
			out<<'\n';
	}
	out<<endl;
	out.close();
	dat.close();
	return 0;
}

void sort(int x[],int m)
{
	int i,j,k,t;
	for(i=0;i<m-1;i++)
	{
		k=i;
		for(j=i+1;j<m;j++)
			if(x[j]<x[k])
				k=j;
		if(k!=i)
		{ t=x[i]; x[i]=x[k]; x[k]=t; }
	}
}



