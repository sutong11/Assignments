<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm4.aspx.cs" Inherits="实验三.WebForm4" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <style type="text/css">
        .auto-style1
        {
            height: 42px;
        }
        .auto-style2
        {
            height: 42px;
            width: 302px;
        }
        .auto-style3
        {
            width: 302px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
      
        <table style="width: 100%; height: 342px;">
            <tr>
                <td class="auto-style2">
                    &nbsp;</td>
                <td class="auto-style1"></td>
                <td class="auto-style1"></td>
            </tr>
            <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label9" runat="server" ForeColor="Red" style="z-index: 1; left: 233px; top: 72px; position: absolute" Text="用户名："></asp:Label>
                </td>
                <td>
                    <asp:Label ID="Label1" runat="server" ForeColor="Red" Text=" "></asp:Label>
                    <%=Request.QueryString["用户名"] %>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label10" runat="server" ForeColor="Red" style="z-index: 1; left: 249px; top: 107px; position: absolute" Text="性别："></asp:Label>
                </td>
                <td>
                    <asp:Label ID="Label2" runat="server" ForeColor="Red" Text=" "></asp:Label>
                    <%=Request.QueryString["性别"] %>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label11" runat="server" ForeColor="Red" style="z-index: 1; left: 249px; top: 152px; position: absolute" Text="邮箱："></asp:Label>
                </td>
                <td>
                    <asp:Label ID="Label3" runat="server" ForeColor="Red" Text=" "></asp:Label>
                    <%=Request.QueryString["邮箱"] %>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label12" runat="server" ForeColor="Red" style="z-index: 1; left: 216px; top: 197px; position: absolute; bottom: 316px; width: 80px; height: 15px;" Text="出生日期："></asp:Label>
                </td>
                <td>
                    <asp:Label ID="Label4" runat="server" ForeColor="Red" Text=" "></asp:Label>
                    <%=Request.QueryString["出生日期"] %>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label13" runat="server" ForeColor="Red" style="z-index: 1; left: 215px; top: 234px; position: absolute" Text="身份证号："></asp:Label>
                </td>
                <td>
                    <asp:Label ID="Label5" runat="server" ForeColor="Red" Text=" "></asp:Label>
                    <%=Request.QueryString["身份证号"] %>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style2">
                    <asp:Label ID="Label14" runat="server" ForeColor="Red" style="z-index: 1; left: 245px; top: 280px; position: absolute; bottom: 104px; width: 59px; margin-bottom: 10px;" Text="专业："></asp:Label>
                </td>
                <td class="auto-style1">
                    <asp:Label ID="Label6" runat="server" ForeColor="Red" Text=" "></asp:Label>
                    <%=Request.QueryString["专业"] %>
                </td>
                <td class="auto-style1"></td>
            </tr>
            <tr>
                <td class="auto-style3">
                    <asp:Label ID="Label15" runat="server" ForeColor="Red" style="z-index: 1; left: 248px; top: 320px; position: absolute" Text="简介："></asp:Label>
                </td>
                <td>
                    <asp:Label ID="Label7" runat="server" ForeColor="Red" Text=" "></asp:Label>
                    <%=Request.QueryString["简介"] %>
                </td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </div> 
    </form>
</body>
</html>
