/*
package com.hq.CloudPlatform.CA.utils.ueditor;

import com.baidu.ueditor.ActionEnter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

*/
/**
 * @author Kevin
 *//*

public class UEditorController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html");
        String rootPath = request.getServletContext().getRealPath("/");

        PrintWriter out = response.getWriter();
        out.write(new ActionEnter(request, rootPath).exec());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
*/
