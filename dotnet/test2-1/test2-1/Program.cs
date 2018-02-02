using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace test2_1
{
    class Program
    {
        static void Main(string[] args)
        {
            string a;
            int aa, i, j;
            Console.Write("请输入杨辉三角的行数:");
            a = Console.ReadLine();
            aa = int.Parse(a);
            int[][] arr = new int[aa][];

            for (i = 0; i < aa; i++)
            {
                arr[i] = new int[i + 1];
                for (j = 0; j <= i; j++)
                {
                    arr[i][j] = 1;
                }
            }

            for (i = 2; i < aa; i++)
            {
                for (j = 1; j < i; j++)
                {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }

            for (i = 0; i < aa; i++)
            {
                for (j = 0; j < 10-i; j++)
                {
                    Console.Write("  ");
                }
                for (j = 0; j <= i; j++)
                    Console.Write(arr[i][j] + "   ");
                Console.WriteLine();
            }
        }

     }
}
