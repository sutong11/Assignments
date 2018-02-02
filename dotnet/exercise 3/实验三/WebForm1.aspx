<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="实验三.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <style type="text/css">
        .auto-style2
        {
            width: 357px;
        }
        .auto-style4
        {
            width: 194px;
        }
        .auto-style9
        {
            height: 45px;
            width: 357px;
        }
        .auto-style10
        {
            height: 45px;
            width: 194px;
        }
        .auto-style11
        {
            height: 45px;
        }
        .auto-style12
        {
            height: 49px;
            width: 357px;
        }
        .auto-style13
        {
            height: 49px;
            width: 194px;
        }
        .auto-style14
        {
            height: 49px;
        }
        .auto-style15
        {
            height: 50px;
            width: 357px;
        }
        .auto-style16
        {
            height: 50px;
            width: 194px;
        }
        .auto-style17
        {
            height: 50px;
        }
        .auto-style18
        {
            height: 48px;
            width: 357px;
        }
        .auto-style19
        {
            height: 48px;
            width: 194px;
        }
        .auto-style20
        {
            height: 48px;
        }
        .auto-style21
        {
            height: 41px;
            width: 357px;
        }
        .auto-style22
        {
            height: 41px;
            width: 194px;
        }
        .auto-style23
        {
            height: 41px;
        }
        .auto-style24
        {
            height: 47px;
            width: 357px;
        }
        .auto-style25
        {
            height: 47px;
            width: 194px;
        }
        .auto-style26
        {
            height: 47px;
        }
        .auto-style27
        {
            height: 51px;
            width: 357px;
        }
        .auto-style28
        {
            height: 51px;
            width: 194px;
        }
        .auto-style29
        {
            height: 51px;
        }
        .auto-style30
        {
            height: 55px;
            width: 357px;
        }
        .auto-style31
        {
            height: 55px;
            width: 194px;
        }
        .auto-style32
        {
            height: 55px;
        }
        .auto-style33
        {
            height: 53px;
            width: 357px;
        }
        .auto-style34
        {
            height: 53px;
            width: 194px;
        }
        .auto-style35
        {
            height: 53px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server" method="get" >
    <div>
    
    </div>
        <table style="width: 100%; z-index: 1; left: 10px; top: 50px; position: absolute; height: 532px;">
            <tr>
                <td class="auto-style9">
                    <asp:Label ID="Label1" runat="server" style="z-index: 1; left: 289px; top: 15px; position: absolute" Text="用户名:"></asp:Label>
                </td>
                <td class="auto-style10">
                    <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
                </td>
                <td class="auto-style11">
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" BorderColor="Red" ControlToValidate="TextBox1" ErrorMessage="用户名不能为空" ForeColor="Red">*请输入用户名</asp:RequiredFieldValidator>
                </td>
            </tr>
            <tr>
                <td class="auto-style12">
                    <asp:Label ID="Label2" runat="server" style="z-index: 1; left: 303px; top: 65px; position: absolute" Text="密码:"></asp:Label>
                </td>
                <td class="auto-style13">
                    <asp:TextBox ID="TextBox2" runat="server" TextMode="Password"></asp:TextBox>
                </td>
                <td class="auto-style14">
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" BorderColor="Red" ControlToValidate="TextBox2" ErrorMessage="密码不能为空" ForeColor="Red">*请输入密码</asp:RequiredFieldValidator>
                    <asp:CustomValidator ID="CustomValidator1" runat="server" BorderColor="Red" ControlToValidate="TextBox2" ErrorMessage="请输入6位密码" ForeColor="Red" OnServerValidate="CustomValidator1_ServerValidate">*请输入6位密码</asp:CustomValidator>
                </td>
            </tr>
            <tr>
                <td class="auto-style15">
                    <asp:Label ID="Label3" runat="server" style="z-index: 1; left: 270px; position: absolute; height: 16px; top: 120px; width: 75px" Text="确认密码:"></asp:Label>
                </td>
                <td class="auto-style16">
                    <asp:TextBox ID="TextBox3" runat="server" OnTextChanged="TextBox3_TextChanged" style="height: 19px" TextMode="Password"></asp:TextBox>
                </td>
                <td class="auto-style17">
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" BorderColor="Red" ControlToValidate="TextBox3" ErrorMessage="密码错误" ForeColor="Red">*两次输入密码不一致</asp:RequiredFieldValidator>
                    <asp:CompareValidator ID="CompareValidator1" runat="server" BorderColor="Red" ControlToCompare="TextBox2" ControlToValidate="TextBox3" ErrorMessage="两次密码不一致" ForeColor="Red">*两次密码不一致</asp:CompareValidator>
                </td>
            </tr>
            <tr>
                <td class="auto-style9">
                    <asp:Label ID="Label4" runat="server" style="z-index: 1; left: 302px; top: 164px; position: absolute; height: 19px; right: 433px; width: 43px" Text="性别:"></asp:Label>
                </td>
                <td class="auto-style10">
                    <asp:RadioButtonList ID="RadioButtonList1" runat="server" RepeatDirection="Horizontal" Width="151px">
                        <asp:ListItem Selected="True">男</asp:ListItem>
                        <asp:ListItem>女</asp:ListItem>
                    </asp:RadioButtonList>
                </td>
                <td class="auto-style11"></td>
            </tr>
            <tr>
                <td class="auto-style18">
                    <asp:Label ID="Label5" runat="server" style="z-index: 1; left: 303px; position: absolute; top: 217px; width: 42px" Text="邮箱:"></asp:Label>
                </td>
                <td class="auto-style19">
                    <asp:TextBox ID="TextBox4" runat="server" Height="16px"></asp:TextBox>
                </td>
                <td class="auto-style20">
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" BorderColor="Red" ControlToValidate="TextBox4" ErrorMessage="邮箱不能为空" ForeColor="Red">*请输入邮箱</asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" BorderColor="Red" ControlToValidate="TextBox4" ErrorMessage="邮箱格式不正确" ForeColor="Red" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*">*邮箱格式错误</asp:RegularExpressionValidator>
                </td>
            </tr>
            <tr>
                <td class="auto-style2">
                    <asp:Label ID="Label6" runat="server" style="z-index: 1; left: 271px; top: 260px; position: absolute" Text="出生日期:"></asp:Label>
                    <asp:ScriptManager ID="ScriptManager1" runat="server">
                    </asp:ScriptManager>
                </td>
                <td class="auto-style4">
                    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                        <ContentTemplate>
                            <asp:Calendar ID="Calendar1" runat="server" BackColor="White" BorderColor="#3366CC" BorderWidth="1px" CellPadding="1" DayNameFormat="Shortest" Font-Names="Verdana" Font-Size="8pt" ForeColor="#003399" Height="200px" Width="220px" SelectedDate="2013-12-05">
                                <DayHeaderStyle BackColor="#99CCCC" ForeColor="#336666" Height="1px" />
                                <NextPrevStyle Font-Size="8pt" ForeColor="#CCCCFF" />
                                <OtherMonthDayStyle ForeColor="#999999" />
                                <SelectedDayStyle BackColor="#009999" Font-Bold="True" ForeColor="#CCFF99" />
                                <SelectorStyle BackColor="#99CCCC" ForeColor="#336666" />
                                <TitleStyle BackColor="#003399" BorderColor="#3366CC" BorderWidth="1px" Font-Bold="True" Font-Size="10pt" ForeColor="#CCCCFF" Height="25px" />
                                <TodayDayStyle BackColor="#99CCCC" ForeColor="White" />
                                <WeekendDayStyle BackColor="#CCCCFF" />
                            </asp:Calendar>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style21">
                    <asp:Label ID="Label7" runat="server" style="z-index: 1; left: 272px; top: 467px; position: absolute" Text="身份证号:"></asp:Label>
                </td>
                <td class="auto-style22">
                    <asp:TextBox ID="TextBox5" runat="server"></asp:TextBox>
                </td>
                <td class="auto-style23">
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" BorderColor="Red" ControlToValidate="TextBox5" ErrorMessage="身份证号不能为空" ForeColor="Red">*请输入身份证号</asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="RegularExpressionValidator2" runat="server" BorderColor="Red" ControlToValidate="TextBox5" ErrorMessage="身份证号为18位" ForeColor="Red" ValidationExpression="\d{17}[\d|X]|\d{15}">*身份证号为18位</asp:RegularExpressionValidator>
                    <asp:CustomValidator ID="CustomValidator2" runat="server" BorderColor="Red" ControlToValidate="TextBox5" ErrorMessage="身份证号与日历不匹配" ForeColor="Red" OnServerValidate="CustomValidator2_ServerValidate">*请输入正确的身份证号</asp:CustomValidator>
                </td>
            </tr>
            <tr>
                <td class="auto-style9">
                    <asp:Label ID="Label8" runat="server" style="z-index: 1; left: 302px; top: 515px; position: absolute; height: 16px" Text="专业:"></asp:Label>
                </td>
                <td class="auto-style10">
                    <asp:DropDownList ID="DropDownList1" runat="server" Width="100px">
                        <asp:ListItem></asp:ListItem>
                        <asp:ListItem>软件工程</asp:ListItem>
                        <asp:ListItem>计算机</asp:ListItem>
                        <asp:ListItem>网络工程</asp:ListItem>
                        <asp:ListItem>电本</asp:ListItem>
                    </asp:DropDownList>
                </td>
                <td class="auto-style11">
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" BorderColor="Red" ControlToValidate="DropDownList1" ErrorMessage="专业不能为空" ForeColor="Red">*请选择专业</asp:RequiredFieldValidator>
                </td>
            </tr>
            <tr>
                <td class="auto-style24">
                    <asp:Label ID="Label9" runat="server" style="z-index: 1; left: 304px; top: 552px; position: absolute" Text="简介:"></asp:Label>
                </td>
                <td class="auto-style25">
                    <asp:TextBox ID="TextBox6" runat="server" Height="137px" Width="186px" TextMode="MultiLine"></asp:TextBox>
                </td>
                <td class="auto-style26"></td>
            </tr>
            <tr>
                <td class="auto-style27"></td>
                <td class="auto-style28"></td>
                <td class="auto-style29"></td>
            </tr>
            <tr>
                <td class="auto-style30"></td>
                <td class="auto-style31">
                    <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" style="z-index: 1; left: 391px; top: 739px; position: absolute; height: 33px; width: 121px" Text="确定" />
                </td>
                <td class="auto-style32"></td>
            </tr>
            <tr>
                <td class="auto-style33"></td>
                <td class="auto-style34">
                    &nbsp;</td>
                <td class="auto-style35"></td>
            </tr>
            <tr>
                <td class="auto-style27"></td>
                <td class="auto-style28"></td>
                <td class="auto-style29"></td>
            </tr>
        </table>
    </form>
</body>
</html>
