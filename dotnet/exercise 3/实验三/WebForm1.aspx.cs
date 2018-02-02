using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace 实验三
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void BulletedList1_Click(object sender, BulletedListEventArgs e)
        {

        }

        protected void DropDownList1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        protected void TextBox3_TextChanged(object sender, EventArgs e)
        {

        }

        protected void Calendar1_SelectionChanged(object sender, EventArgs e)
        {

        }

        protected void CustomValidator1_ServerValidate(object source, ServerValidateEventArgs args)
        {
            string s = TextBox2.Text;
            int count = 0;
            foreach (char chr in s)
            {
                if (char.IsUpper(chr)) count++;
                else if (char.IsNumber(chr)) count++;
                else if (char.IsLower(chr)) count++;
            }
            if (count == 6) args.IsValid = true;
            else args.IsValid = false;
        }

        protected void CustomValidator2_ServerValidate(object source, ServerValidateEventArgs args)
        {
            if (TextBox5.Text.Length==18)
            {
                 if (Calendar1.SelectedDate.Year == int.Parse(TextBox5.Text.Substring(6, 4)) &&
                Calendar1.SelectedDate.Month == int.Parse(TextBox5.Text.Substring(10, 2)) &&
            Calendar1.SelectedDate.Day == int.Parse(TextBox5.Text.Substring(12, 2)))
            { args.IsValid = true; }
            else args.IsValid = false;
            }
            else args.IsValid = false;
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            if (Page.IsValid)
            {
                Response.Redirect("WebForm4.aspx?用户名=" + TextBox1.Text+ "&性别=" + RadioButtonList1.SelectedItem +
                    "&邮箱=" + TextBox4.Text + "&出生日期=" + Calendar1.SelectedDate + "&身份证号=" + TextBox5.Text +
                    "&专业=" + DropDownList1.SelectedItem + "&简介=" + TextBox6.Text);
            }
        }
    }
}