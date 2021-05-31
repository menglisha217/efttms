package xupt.se.ttms.idao;

import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.dao.StudioDAO;

public class DAOFactory
{
    private static iStudioDAO stuDao;

    public static synchronized iStudioDAO creatStudioDAO()
    {
        if(null == stuDao)
            stuDao=new StudioDAO();
        return stuDao;
    }
    
    private static iScheduleDAO schedDao;

    public static synchronized iScheduleDAO creatScheduleDAO()
    {
        if(null == schedDao)
            schedDao=new ScheduleDAO();
        return schedDao;
    }
    public static synchronized iScheduleDAO creatSaleDAO()
    {
        if(null == saleDao)
            saleDao=new SaleDAO();
        return saleDao;
    }
}
