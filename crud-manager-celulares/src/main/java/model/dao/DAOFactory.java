package model.dao;

import java.util.HashMap;
import java.util.Map;
import model.dao.CelularDAO;
import model.dao.CompanyDAO;
import model.dao.MySQLCelularDAO;
import model.dao.MySQLCompanyDAO;
import model.dao.MySQLPostDAO;
import model.dao.MySQLUserDAO;
import model.dao.PostDAO;
import model.dao.UserDAO;

public class DAOFactory {
	private static Map<Class<?>, Object> listDAOsInterfaces = new HashMap<Class<?>, Object>();
	static {
		listDAOsInterfaces.put(PostDAO.class, new MySQLPostDAO());
		listDAOsInterfaces.put(UserDAO.class, new MySQLUserDAO());
		listDAOsInterfaces.put(CompanyDAO.class, new MySQLCompanyDAO());
		listDAOsInterfaces.put(CelularDAO.class, new MySQLCelularDAO());
	}

	@SuppressWarnings("unchecked")
	public static <DAOInterface> DAOInterface createDAO(Class<?> entity) {
		return (DAOInterface) listDAOsInterfaces.get(entity);
	}
}