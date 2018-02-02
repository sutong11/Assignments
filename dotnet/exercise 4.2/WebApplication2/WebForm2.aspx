<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm2.aspx.cs" Inherits="WebApplication2.WebForm2" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <style type="text/css">

        .auto-style1 {
            width: 295px;
        }
        .auto-style2 {
            width: 129px;
        }
        .auto-style3 {
            width: 295px;
            height: 77px;
        }
        .auto-style4 {
            width: 129px;
            height: 77px;
        }
        .auto-style5 {
            height: 77px;
        }
        .auto-style6 {
            width: 295px;
            height: 53px;
        }
        .auto-style7 {
            width: 129px;
            height: 53px;
        }
        .auto-style8 {
            height: 53px;
        }
        .auto-style9 {
            width: 126px;
        }
        .auto-style10 {
            width: 126px;
            height: 53px;
        }
        .auto-style11 {
            width: 126px;
            height: 77px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <table style="width: 100%; height: 503px;">
            <tr>
                <td class="auto-style1">
                    <asp:Label ID="Label1" runat="server" style="z-index: 1; left: 182px; top: 39px; position: absolute; width: 130px;" Text="水果选购："></asp:Label>
                </td>
                <td class="auto-style9">&nbsp;</td>
                <td class="auto-style2">&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style6"></td>
                <td class="auto-style10">
                    <asp:Label ID="Label2" runat="server" Text="名称："></asp:Label>
                </td>
                <td class="auto-style7">
                    <asp:Label ID="Label4" runat="server" Text="单价："></asp:Label>
                </td>
                <td class="auto-style8">
                    <asp:Label ID="Label3" runat="server" Text="数量："></asp:Label>
                </td>
            </tr>
            <tr>
                <td class="auto-style1">&nbsp;</td>
                <td class="auto-style9">
                    <asp:CheckBox ID="C5" runat="server" Text="苹果" />
                </td>
                <td class="auto-style2">
                    <asp:Label ID="Label5" runat="server" Text="10"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="TextBox1" runat="server">0</asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style3"></td>
                <td class="auto-style11">
                    <asp:CheckBox ID="C6" runat="server" Text="香蕉" />
                </td>
                <td class="auto-style4">
                    <asp:Label ID="Label6" runat="server" Text="8"></asp:Label>
                </td>
                <td class="auto-style5">
                    <asp:TextBox ID="TextBox2" runat="server">0</asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style3"></td>
                <td class="auto-style11">
                    <asp:CheckBox ID="C7" runat="server" Text="猕猴桃" />
                </td>
                <td class="auto-style4">
                    <asp:Label ID="Label7" runat="server" Text="15"></asp:Label>
                </td>
                <td class="auto-style5">
                    <asp:TextBox ID="TextBox3" runat="server">0</asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style1">&nbsp;</td>
                <td class="auto-style9">
                    <asp:CheckBox ID="C8" runat="server" Text="芒果" />
                </td>
                <td class="auto-style2">
                    <asp:Label ID="Label8" runat="server" Text="25"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="TextBox4" runat="server" OnTextChanged="TextBox4_TextChanged">0</asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style1">&nbsp;</td>
                <td class="auto-style9">
                    <asp:Button ID="Button1" runat="server" style="z-index: 1; left: 307px; top: 422px; position: absolute; width: 97px; height: 37px" Text="提交" OnClick="Button1_Click" />
                </td>
                <td class="auto-style2">
                    &nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style1">
                    <asp:HyperLink ID="HyperLink1" runat="server" NavigateUrl="~/WebForm1.aspx" style="z-index: 1; left: 203px; top: 477px; position: absolute; width: 119px">肉类选购</asp:HyperLink>
                </td>
                <td class="auto-style9">&nbsp;</td>
                <td class="auto-style2">&nbsp;</td>
                <td>
                    <asp:HyperLink ID="HyperLink2" runat="server"  NavigateUrl="~/WebForm3.aspx"  style="z-index: 1; left: 412px; top: 476px; position: absolute">查看购物车</asp:HyperLink>
                </td>
            </tr>
        </table>
    </form>
    <div>
    
    </div>
    </form>
</body>
</html>
