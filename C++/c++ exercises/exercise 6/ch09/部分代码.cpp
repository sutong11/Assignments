//��������࣬���������"+"��"*"��ʵ�ֶԾ�����������Ӻ���ˡ�

#include<iostream>
using namespace std;

class Matrix
{
    private:
		int M, N;
		float ** Array;
		int Generate(void);

	public:
		Matrix(int m, int n);
		Matrix(Matrix &matrix);
		~Matrix();
		void GetSize(int &m, int &n);
		void Input(void);
		void Show(void);
		float GetElement(int i, int j);
		void SetElement(int i, int j, float data);
		//................................
		
};

int Matrix::Generate(void)
{
	Array = new float * [M];
	if (Array==NULL) return (0);

	for (int i=0; i<M; i++) {
		Array[i] = new float [N];
		if (Array[i]==NULL) return (0);
	}

	for (  i=0; i<M; i++)
		for (int j=0; j<N; j++) Array[i][j] = 0;
	
	return (1);
}

Matrix::Matrix(int m, int n)
{
	M = m;
	N = n;

	Generate();
}

Matrix::Matrix(Matrix &matrix)
{
	matrix.GetSize(M, N);

	if (!Generate()) {
		cout << "�����������캯��ʧ��\n";
		return ;
	}

	for (int i=0; i<M; i++)
		for (int j=0; j<N; j++) Array[i][j] = matrix.GetElement(i, j);
}

Matrix::~Matrix()
{
	delete []Array;
}

void Matrix::GetSize(int &m, int &n)
{
	m = M;
	n = N;
}

void Matrix::Input(void)
{
	cout << "�����ʼ��\n";
	for (int i=0; i<M; i++)
		for (int j=0; j<N; j++) {
			cout << "M[" << i << "][" << j << "]=";
			cin >> Array[i][j];
		}
}

void Matrix::Show(void)
{
	cout << "\n---------------------------------\n";
	for (int i=0; i<M; i++) {
		for (int j=0; j<N; j++)	cout << Array[i][j] << " ";
		cout << "\n";
	}
}

float Matrix::GetElement(int i, int j)
{
	return (Array[i][j]);
}

void Matrix::SetElement(int i, int j, float data)
{
	Array[i][j] = data;
}

