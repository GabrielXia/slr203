package com.store.catalog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet{
	
	public static String p1 = "param1";
	public static String p2 = "param2";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		PrintWriter w = resp.getWriter();
		String a = req.getParameter(p1);
		String b = req.getParameter(p2);
		int num1 = Integer.parseInt(a);
		int num2 = Integer.parseInt(b);
		int sum = num1 + num2;
		w.println(sum);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		PrintWriter w = resp.getWriter();
		String line = req.getReader().readLine();
		String[] parts = line.split("\\+");
		String a = parts[0];
		String b = parts[1];
		int num1 = Integer.parseInt(a);
		int num2 = Integer.parseInt(b);
		int sum = num1 + num2;
		w.println(sum);
	}
}
