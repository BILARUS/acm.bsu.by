#include <iostream>
#include <fstream>
#include <math.h>
using namespace std;

int main()
{
	int K, N;//�-���������� ���� N-������ �����
	fstream input ("input.txt", ios::in);
	fstream output ("output.txt", ios::out);
	input >> K;
	input >> N;
	N++;
	int **Mass= new int*[N];	//������� ������ ��� �������� ���� ��� ����� ������ ��� ������ ����� � ������� ���������� ������ � ������
	for(int i=0; i<N; i++)      //�����. � ���� ����� ��� ���������� ��������� �������� ��������� ������� ������
		Mass[i]=new int[N];

	for(int i=0; i<N;i++)      //������������� ������ 
		for(int j=0; j<N;j++)
			Mass[i][j]=0;

	for(int i=1;i<N;i++)    //��������� ���������� ��� ��� 0 ������
	{
		Mass[i][0]=1;
	}
	
	int X=1;

	for(int i=3;i<N;i++,X++) //��������� ����� ��� �������� ������� ��� ����� ������ 1
	{
		Mass[i][1]=X;
	}

	for(int i=2;i<N;i++)      //��������� ��� �������
		for(int j=5;j<N;j++)
	{
		Mass[j][i]=Mass[j-2][i-1]+Mass[j-1][i];
	}

	int *MassRez=new int[N];
	for(int i=1; i<N;i++)
	{
		MassRez[i]=0;
	}

	for(int i=1; i<N;i++)
			for(int k=i,j=0;k<N;k++,j++)
	{
		MassRez[i]+=Mass[k][j];
		
	}
	int pw=1;
	int REZULT=0;
	for(int i=1;i<N;i++)
	{
		for(int j=0;j<i;j++)
		{
			pw*=K;
		}
		REZULT+=pw*MassRez[i];
		pw=1;
	}
	output<<REZULT;
	
	return 0;
}
