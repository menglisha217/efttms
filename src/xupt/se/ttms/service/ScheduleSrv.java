package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;


public class ScheduleSrv{
private iScheduleDAO schedDAO=DAOFactory.creatScheduleDAO();
	
	public int add(Schedule sched){
		return schedDAO.insert(sched); 		
	}
	
	public int modify(Schedule sched){
		return schedDAO.update(sched); 		
	}
	
	public int delete(int ID){
		return schedDAO.delete(ID); 		
	}
	
	public List<Schedule> Fetch(String condt){
		return schedDAO.select(condt);		
	}
	
	public List<Schedule> FetchAll(){
		return schedDAO.select("");		
	}
}

