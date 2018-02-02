using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            if (C1.Checked)
            {
                Session["b1"] = C1.Text;
                Session["b1_price"]=Label5.Text;
                Session["b1_number"] = TextBox1.Text;
            }
            if (C2.Checked)
            {
                Session["b2"] = C2.Text;
                Session["b2_price"] = Label6.Text;
                Session["b2_number"] = TextBox2.Text;
            }
            if (C3.Checked) 
            {
                Session["b3"] = C3.Text;
                Session["b3_price"] = Label7.Text;
                Session["b3_number"] = TextBox3.Text;
            }
            if (C4.Checked) 
            {
                Session["b4"] = C4.Text;
                Session["b4_price"] = Label8.Text;
                Session["b4_number"] = TextBox4.Text;
            }
        }
    }
}