using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace test2_2
{
    public class Book
    {
        public string bookname, category;
        public int price;

        public Book(string a, string b, int c)
        {
            bookname = a;
            category = b;
            price = c;
        }
    }

    public class Sort
    {
        Book[] b;

        public Sort(Book[] bb)
        {
            b = bb;
        }

        public void priceorder()
        {
            Book k;
            for (int i = 0; i < b.Length - 1; i++)
            {
                for (int j = 0; j < b.Length - 1 - i; j++)
                {
                    if (b[j].price > b[j + 1].price)
                    {
                        k = b[j];
                        b[j] = b[j + 1];
                        b[j + 1] = k;
                    }
                }
            }
        }

        public void nameorder()
        {
            Book k;
            for (int i = 0; i < b.Length - 1; i++)
            {
                for (int j = 0; j < b.Length - 1 - i; j++)
                {
                    if (b[j].bookname.CompareTo(b[j + 1].bookname) > 0)
                    {
                        k = b[j];
                        b[j] = b[j + 1];
                        b[j + 1] = k;
                    }
                }
            }
        }

        public void output()
        {
            for (int i = 0; i < b.Length; i++)
            {
                Console.WriteLine(b[i].bookname + "          " + b[i].category + "          " + b[i].price);
            }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Book[] b = new Book[10];
            b[0] = new Book("c语言", "IT", 20);
            b[1] = new Book("cplus", "IT", 30);
            b[2] = new Book("java编程", "IT", 25);
            b[3] = new Book("net", "IT", 28);
            b[4] = new Book("数据结构", "IT", 35);
            b[5] = new Book("算法", "IT", 27);
            b[6] = new Book("软件工程", "IT", 46);
            b[7] = new Book("uml", "IT", 19);
            b[8] = new Book("操作系统", "IT", 33);
            b[9] = new Book("离散数学", "IT", 58);

            Sort s = new Sort(b);

            s.priceorder();
            Console.WriteLine("按价格排序:");
            s.output();
            Console.WriteLine();
            s.nameorder();
            Console.WriteLine("按书名排序:");
            s.output();

        }
    }
}
