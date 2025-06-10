package model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Celular;
import model.Marca;
import model.ModelException;
import model.dao.DAOFactory;

public class MySQLCelularDAO implements CelularDAO {
	@Override
	public boolean save(Celular celular) throws ModelException {
		DBHandler db = new DBHandler();
		String sqlInsert = "INSERT INTO Celulares (modelo, cor, armazenamentoGB, preco, dataLancamento, idMarca) VALUES (?, ?, ?, ?, ?, ?);";
		db.prepareStatement(sqlInsert);
		db.setString(1, celular.getModelo());
		db.setString(2, celular.getCor());
		db.setInt(3, celular.getArmazenamentoGB());
		db.setString(4, String.valueOf(celular.getPreco()));
		if (celular.getDataLancamento() != null) {
			db.setDate(5, new java.sql.Date(celular.getDataLancamento().getTime()));
		} else {
			db.setNullDate(5);
		}
		db.setInt(6, celular.getMarca().getId());
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Celular celular) throws ModelException {
		DBHandler db = new DBHandler();
		String sqlUpdate = "UPDATE Celulares SET modelo=?, cor=?, armazenamentoGB=?, preco=?, dataLancamento=?, idMarca=? WHERE idCelular=?;";
		db.prepareStatement(sqlUpdate);
		db.setString(1, celular.getModelo());
		db.setString(2, celular.getCor());
		db.setInt(3, celular.getArmazenamentoGB());
		db.setString(4, String.valueOf(celular.getPreco()));
		if (celular.getDataLancamento() != null) {
			db.setDate(5, new java.sql.Date(celular.getDataLancamento().getTime()));
		} else {
			db.setNullDate(5);
		}
		db.setInt(6, celular.getMarca().getId());
		db.setInt(7, celular.getId());
		return db.executeUpdate() > 0;
	}

	@Override
    public boolean delete(Celular celular) throws ModelException {
        DBHandler db = new DBHandler();
        String sqlDelete = "DELETE FROM Celulares WHERE idCelular = ?;";

        try {
            db.prepareStatement(sqlDelete);
            db.setInt(1, celular.getId());
            boolean deleted = db.executeUpdate() > 0; 
            System.out.println("MySQLCelularDAO.delete: Execução da query DELETE retornou " + deleted); // Log de depuração
            return deleted; 
        } catch (ModelException e) {
            System.err.println("MySQLCelularDAO.delete: Erro ao deletar celular com ID " + celular.getId() + ": " + e.getMessage()); // Log de erro
            e.printStackTrace(); 
            throw e; 
        }
    }

	@Override
	public List<Celular> listAll() throws ModelException {
		DBHandler db = new DBHandler();
		List<Celular> celulares = new ArrayList<>();
		String sqlQuery = "SELECT c.idCelular, c.modelo, c.cor, c.armazenamentoGB, c.preco, c.dataLancamento, "
				+ "m.idMarca, m.nomeMarca FROM Celulares c JOIN Marcas m ON c.idMarca = m.idMarca;";
		db.createStatement();
		db.executeQuery(sqlQuery);
		while (db.next()) {
			Celular celular = new Celular();
			celular.setId(db.getInt("idCelular"));
			celular.setModelo(db.getString("modelo"));
			celular.setCor(db.getString("cor"));
			celular.setArmazenamentoGB(db.getInt("armazenamentoGB"));
			celular.setPreco(Double.parseDouble(db.getString("preco")));
			celular.setDataLancamento(db.getDate("dataLancamento"));
			Marca marca = new Marca(db.getInt("idMarca"), db.getString("nomeMarca"));
			celular.setMarca(marca);
			celulares.add(celular);
		}
		return celulares;
	}

	@Override
	public Celular findById(int id) throws ModelException {
		DBHandler db = new DBHandler();
		String sql = "SELECT c.idCelular, c.modelo, c.cor, c.armazenamentoGB, c.preco, c.dataLancamento, "
				+ "m.idMarca, m.nomeMarca FROM Celulares c JOIN Marcas m ON c.idMarca = m.idMarca WHERE c.idCelular = ?;";
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		Celular celular = null;
		if (db.next()) {
			celular = new Celular();
			celular.setId(db.getInt("idCelular"));
			celular.setModelo(db.getString("modelo"));
			celular.setCor(db.getString("cor"));
			celular.setArmazenamentoGB(db.getInt("armazenamentoGB"));
			celular.setPreco(Double.parseDouble(db.getString("preco")));
			celular.setDataLancamento(db.getDate("dataLancamento"));
			Marca marca = new Marca(db.getInt("idMarca"), db.getString("nomeMarca"));
			celular.setMarca(marca);
		}
		return celular;
	}

	@Override
	public List<Marca> listAllMarcas() throws ModelException {
		DBHandler db = new DBHandler();
		List<Marca> marcas = new ArrayList<>();
		String sqlQuery = "SELECT idMarca, nomeMarca FROM Marcas ORDER BY nomeMarca;";
		db.createStatement();
		db.executeQuery(sqlQuery);
		while (db.next()) {
			Marca marca = new Marca();
			marca.setId(db.getInt("idMarca"));
			marca.setNome(db.getString("nomeMarca"));
			marcas.add(marca);
		}
		return marcas;
	}

	@Override
	public Marca findMarcaById(int id) throws ModelException {
		DBHandler db = new DBHandler();
		String sql = "SELECT idMarca, nomeMarca FROM Marcas WHERE idMarca = ?;";
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		Marca marca = null;
		if (db.next()) {
			marca = new Marca();
			marca.setId(db.getInt("idMarca"));
			marca.setNome(db.getString("nomeMarca"));
		}
		return marca;
	}
}