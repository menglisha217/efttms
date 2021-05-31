
package xupt.se.ttms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet 
{
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException
	{
		 String type = request.getParameter("type");
		
		 if(type.equalsIgnoreCase("add"))
	            add(request, response);
	     else if(type.equalsIgnoreCase("delete"))
	            delete(request, response);
	     else if(type.equalsIgnoreCase("update"))
	            update(request, response);
	     else if(type.equalsIgnoreCase("search"))
	            search(request, response);
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Schedule sched = null;
		int sched_id = 0;
		System.out.println("564");
		try
		{
			int studio_id = Integer.valueOf(request.getParameter("studioid"));
			int play_id = Integer.valueOf(request.getParameter("playid"));
			String time = request.getParameter("Time");
			double price = Double.parseDouble(request.getParameter("Price"));
			int status = Integer.valueOf(request.getParameter("Status"));
			
			sched = new Schedule(sched_id,studio_id, play_id, time, price, status);//还有几个参数);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			
			if (new ScheduleSrv().add(sched) == 1)
				out.write("数据添加成功");
			else
				out.write("数据添加失败，请重试");
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			int sched_id = Integer.valueOf(request.getParameter("sched_id"));
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
            out.write("" + new ScheduleSrv().delete(sched_id));
            out.close();
		}
		 catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
	}
	
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	Schedule sched = null;
    	int sched_id = 0;
    	try
    	{
    		sched_id = Integer.valueOf(request.getParameter("schedid"));
			int studio_id = Integer.valueOf(request.getParameter("studioid"));
			int play_id = Integer.valueOf(request.getParameter("playid"));
			String time = request.getParameter("Time");
			double price = Double.parseDouble(request.getParameter("Price"));
			int status = Integer.valueOf(request.getParameter("Status"));
			
			sched = new Schedule(sched_id,studio_id,play_id,time,price,status);
			response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new ScheduleSrv().modify(sched) == 1)
                out.write("数据修改成功");
            else
                out.write("数据修改失败，请重试");

            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }
    
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
		String schedid = request.getParameter("sched_id");//不知道对不对
    	List<Schedule> result = null;
    	
    	if(schedid != null && schedid.length()>0)
            result=new ScheduleSrv().Fetch(schedid);
        else
            result=new ScheduleSrv().FetchAll();
    	String jsonStr="";
    	try
    	{
    		JSONArray array = new JSONArray();
    		JSONObject json;
    		for(Schedule s :result)
    		{
    			json = new JSONObject();
    			json.put("sched_id",s.getSched_id());
    			json.put("studio_id",s.getStudio_id());
    			json.put("play_id",s.getPlay_id());
    			json.put("time",s.getTime());
    			json.put("price",s.getPrice());
    			json.put("status",s.getStatus());
    			array.put(json);
    		}
    		jsonStr = array.toString();
    	}
    	catch(JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.println(jsonStr);
            out.flush();
            out.close();
        }
    	System.out.print(jsonStr);
    }
}
