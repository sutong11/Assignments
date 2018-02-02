using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2
{
    public partial class WebForm3 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Response.Write("您的购物车：" + "<br>");
            Response.Write("菜名" + "   " + "单价" + "   " + "数量" + "   " + "总价" + "<br>");
            for (int i = 0; i < Session.Count; i++)
            {
                string price = Session[i+1].ToString();
                string number = Session[i+2].ToString();
                double sum;
                if (price!=null)
                {
                   sum = double.Parse(price) * double.Parse(number);  
                }
                else
                {
                    sum = 0;
                }
                Response.Write(Session[i].ToString() + "   "+price+"   "+number+"   "+sum+"<br>");
                i=i+2;
            }
        }
    }
}