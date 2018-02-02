#include<string>
#include<iostream>
using namespace std;

class Client
{
private:
	static string ServerName;
	static int ClientNum;
public:
	Client(string s="abc"){ ServerName=s; ClientNum++; }
	Client(Client &c){ ServerName=c.ServerName; ClientNum++; }
	~Client(){ ClientNum--; }
	static void ChangeServerName(string b)
	{ ServerName=b; }
	void show()
	{ cout<<ServerName<<endl<<"the number of clients is "<<ClientNum<<endl; }
};

int Client::ClientNum=0;
string Client::ServerName=" ";

int main()
{
	string c="kkkkk",a="aaaaa";
	Client G(c);
	Client::ChangeServerName(a);
	G.show();
	return 0;
}