using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            Random ran=new Random ();
            int a,n=ran.Next (100,999),i=0;
            while (i<10)
			{Console.WriteLine("请输入生成的数");
                a = int.Parse (Console.ReadLine());
              if (a< n)
               { Console.WriteLine("猜小了 ");}
              else if (a == n)
               {Console.Write("猜对了！");
                 break;
               }else
               {Console.WriteLine("猜大了");}
                i++;
                }                       
              if (i== 10)
                {Console.WriteLine("the number is {0}", n);} 
                Console.ReadLine();   
         }
    }

}
