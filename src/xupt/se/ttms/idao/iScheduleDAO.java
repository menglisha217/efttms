package xupt.se.ttms.idao;

import java.util.List;
import xupt.se.ttms.model.Schedule;

public interface iScheduleDAO {
	public int insert(Schedule sched);
	
	public int update(Schedule sched);
	
	public int delete(int ID);
	
	public List<Schedule> select(String schedid);
}
