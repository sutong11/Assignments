#include<cstdlib>
#include<fstream>
#include<iostream>
using namespace std;

const int n=20;

class datout
{
private:
	int a[n],b[n];
	fstream dat,out;
public:
	datout()
	{
		dat.open("file.dat",ios::out|ios::in|ios::binary);
		if(!dat)
		{
			dat.clear(0);
			dat.open("file.dat",ios::out|ios::binary);
			dat.close();
			dat.open("file.dat",ios::out|ios::in|ios::binary);
			if(!dat) { cout<<"cannot open file\n"; }
		}
		out.open("file.out",ios::out);
		if(!out)
		{ cout<<"cannot open file\n"; }
		for(int i=0;i<n;i++)
		{
			a[i]=rand();
			dat.write((char*)&a[i],sizeof(int));
		}
		
	}
	
	~datout() { out.close(); dat.close(); }
	void read()
	{
		dat.seekg(0);
		for(int i=0;i<n;i++)
		{ dat.read((char*)&b[i],sizeof(int)); }
	}
	void show()
	{
		for(int i=0;i<n;i++)
		{
			cout<<b[i]<<'\t';
			if(i%5==4)  cout<<'\n';
		}
		cout<<endl;
	}
	void take()
	{
		for(int i=0;i<n;i++)
		{
			out<<b[i]<<'\t';
			if(i%5==4)
				out<<'\n';
		}
		out<<endl;
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

};

int main()
{
	int c[n];
	datout d;
	d.read();
	d.sort(c,n);
	d.show();
	d.take();

	return 1;
}

