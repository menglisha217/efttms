package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.util.DBUtil;

public class ScheduleDAO implements iScheduleDAO 
{
	 @SuppressWarnings("finally")
	 @Override
	 public int insert(Schedule sched)
	 {
		 int result = 0;
		 try
		 {
			 String sql="insert into schedule(sched_id,studio_id,play_id,sched_time,sched_ticket_price,sched_status)"
					 + "values("+ sched.getSched_id() +"," + sched.getStudio_id()
					 + "," + sched.getPlay_id() + ",'" + sched.getTime()+"',"+sched.getPrice()
					 +","+sched.getStatus() + ")";
			 DBUtil db = new DBUtil();
			 db.openConnection();
			 ResultSet rst = db.getInsertObjectIDs(sql);
			 if(rst != null && rst.first())
	            {
	                sched.setID(rst.getInt(1));
	            }
	            db.close(rst);
	            db.close();
	            result = 1;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 return result;
		 }
	 }
	 
	 @SuppressWarnings("finally")
	 @Override
	 public int update(Schedule sched)
	 {
		 int result=0;
		 try
		 {
			 String sql="update schedule set " 
					 + "studio_id ="+ sched.getStudio_id() + "," 
					 + "play_id=" + sched.getPlay_id() +"," 
					 + "sched_time='"+sched.getTime() + "'," 
					 + "sched_ticket_price=" + sched.getPrice() + "," 
					 + "sched_status="+sched.getStatus();
			 sql+=" "+"where sched_id = " + sched.getID();
			 
			 DBUtil db=new DBUtil();
	            db.openConnection();
	            result=db.execCommand(sql);
	            db.close();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            return result;
	        }
		 }
	 
	 @Override
	    public int delete(int sched_ID)
	    {
	        int result=0;
	        try
	        {
	            String sql="delete from  schedule where sched_id = " + sched_ID;
	            DBUtil db=new DBUtil();
	            db.openConnection();
	            result=db.execCommand(sql);
	            db.close();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        return result;
	    }
	 
	 @SuppressWarnings("finally")
	    public String selectschedleid(int condt)
	    {
	        DBUtil db=null;
	        String result="";
	        try
	        {
	            String sql="select sched_id from schedule  where sched_id= " + condt;
	            db=new DBUtil();
	            if(!db.openConnection())
	            {
	                System.out.print("fail to connect database");
	                return null;
	            }
	            ResultSet rst=db.execQuery(sql);
	            if(rst != null)
	            {
	                while(rst.next())
	                {
	                	result = rst.getString("studio_id");
	                }
	            }
	            db.close(rst);
	            db.close();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            return result;
	        }
	    }

	    @SuppressWarnings("finally")
	    @Override
	    public List<Schedule> select(String schedid)
	    {
	    	System.out.println("123");
	        DBUtil db=null;
	        List<Schedule> schedList=null;
	        schedList=new LinkedList<Schedule>();
	        try
	        {
	            String sql="select * from schedule where sched_id like '%" + schedid + "%'";
	            db=new DBUtil();
	            if(!db.openConnection())
	            {
	                System.out.print("fail to connect database");
	                return null;
	            }
	            ResultSet rst=db.execQuery(sql);
	            if(rst != null)
	            {
	                while(rst.next())
	                {
	                    Schedule sched=new Schedule();
	                    sched.setID(rst.getInt("sched_id"));
	                    sched.setStudio_id(rst.getInt("studio_id"));
	                    sched.setPlay_id(rst.getInt("play_id"));
	                    sched.setTime(rst.getString("sched_time"));
	                    sched.setPrice(rst.getDouble("sched_ticket_price"));
	                    sched.setStatus(rst.getInt("sched_status"));
	                    schedList.add(sched);
	                }
	            }
	            db.close(rst);
	            db.close();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            return schedList;
	        }
	    }
}

