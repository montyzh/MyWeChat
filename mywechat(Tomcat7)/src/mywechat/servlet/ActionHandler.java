package mywechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cayenne.query.SelectQuery;

import com.google.gson.Gson;
import com.pr.cayenne.ext.CayenneUtil;
import com.pr.cayenne.ext.EntityUtils;

import mywechat.store.Users;
import mywechat.vo.auto.VO_Users;

public abstract class ActionHandler {

	public abstract Object execute(HttpServletRequest req) ;
}
