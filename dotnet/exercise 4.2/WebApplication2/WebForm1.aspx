<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="WebApplication2.WebForm1" %>

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
            width: 103px;
        }
        .auto-style3 {
            width: 295px;
            height: 77px;
        }
        .auto-style4 {
            width: 103px;
            height: 77px;
        }
        .auto-style5 {
            height: 77px;
        }
        .auto-style6 {
            width: 295px;
            height: 70px;
        }
        .auto-style7 {
            width: 103px;
            height: 70px;
        }
        .auto-style8 {
            height: 70px;
        }
        .auto-style9 {
            width: 119px;
        }
        .auto-style10 {
            width: 119px;
            height: 70px;
        }
        .auto-style11 {
            width: 119px;
            height: 77px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <table style="width: 100%; height: 503px;">
            <tr>
                <td class="auto-style1">
                    <asp:Label ID="Label1" runat="server" style="z-index: 1; left: 182px; top: 39px; position: absolute; width: 147px;" Text="肉类选购："></asp:Label>
                </td>
                <td class="auto-style9">&nbsp;</td>
                <td class="auto-style2">&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style1">&nbsp;</td>
                <td class="auto-style9">
                    <asp:Label ID="Label2" runat="server" Text="名称："></asp:Label>
                </td>
                <td class="auto-style2">
                    <asp:Label ID="Label4" runat="server" Text="单价"></asp:Label>
                </td>
                <td>
                    <asp:Label ID="Label3" runat="server" Text="数量:"></asp:Label>
                </td>
            </tr>
            <tr>
                <td class="auto-style1">&nbsp;</td>
                <td class="auto-style9">
                    <asp:CheckBox ID="C1" runat="server" Text="羊肉" />
                </td>
                <td class="auto-style2">
                    <asp:Label ID="Label5" runat="server" Text="40"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="TextBox1" runat="server">0</asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style6"></td>
                <td class="auto-style10">
                    <asp:CheckBox ID="C2" runat="server" Text="排骨" />
                </td>
                <td class="auto-style7">
                    <asp:Label ID="Label6" runat="server" Text="30"></asp:Label>
                </td>
                <td class="auto-style8">
                    <asp:TextBox ID="TextBox2" runat="server">0</asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style3"></td>
                <td class="auto-style11">
                    <asp:CheckBox ID="C3" runat="server" Text="鸡翅" />
                </td>
                <td class="auto-style4">
                    <asp:Label ID="Label7" runat="server" Text="20"></asp:Label>
                </td>
                <td class="auto-style5">
                    <asp:TextBox ID="TextBox3" runat="server">0</asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style1">&nbsp;</td>
                <td class="auto-style9">
                    <asp:CheckBox ID="C4" runat="server" Text="牛肉" />
                </td>
                <td class="auto-style2">
                    <asp:Label ID="Label8" runat="server" Text="36"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="TextBox4" runat="server">0</asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="auto-style1">&nbsp;</td>
                <td class="auto-style9">
                    <asp:Button ID="Button1" runat="server" style="z-index: 1; left: 312px; top: 416px; position: absolute; width: 97px; height: 37px" Text="提交" OnClick="Button1_Click" />
                </td>
                <td class="auto-style2">
                    &nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style1">
                    <asp:HyperLink ID="HyperLink1" runat="server" NavigateUrl="~/WebForm2.aspx" style="z-index: 1; left: 203px; top: 477px; position: absolute; width: 119px">水果选购</asp:HyperLink>
                </td>
                <td class="auto-style9">&nbsp;</td>
                <td class="auto-style2">&nbsp;</td>
                <td>
                    <asp:HyperLink ID="HyperLink2" runat="server"  NavigateUrl="~/WebForm3.aspx"  style="z-index: 1; left: 412px; top: 476px; position: absolute">查看购物车</asp:HyperLink>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>