package model.dao;

import java.util.List;
import model.Celular;
import model.Marca;
import model.ModelException;

public interface CelularDAO {
	boolean save(Celular celular) throws ModelException;

	boolean update(Celular celular) throws ModelException;

	boolean delete(Celular celular) throws ModelException;

	List<Celular> listAll() throws ModelException;

	Celular findById(int id) throws ModelException;

	List<Marca> listAllMarcas() throws ModelException;

	Marca findMarcaById(int id) throws ModelException;
}