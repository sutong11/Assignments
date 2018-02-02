using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2
{
    public partial class WebForm2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            if (C5.Checked)
            {
                Session["b5"] = C5.Text;
                Session["b5_price"] = Label5.Text;
                Session["b5_number"] = TextBox1.Text;
            }
            if (C6.Checked)
            {
                Session["b6"] = C6.Text;
                Session["b6_price"] = Label6.Text;
                Session["b6_number"] = TextBox2.Text;
            }
            if (C7.Checked)
            {
                Session["b7"] = C7.Text;
                Session["b7_price"] = Label7.Text;
                Session["b7_number"] = TextBox3.Text;
            }
            if (C8.Checked)
            {
                Session["b8"] = C8.Text;
                Session["b8_price"] = Label8.Text;
                Session["b8_number"] = TextBox4.Text;
            }
        }

        protected void TextBox4_TextChanged(object sender, EventArgs e)
        {

        }
    }
}