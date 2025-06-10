package controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Celular;
import model.Marca;
import model.ModelException;
import model.dao.CelularDAO;
import model.dao.DAOFactory;

@WebServlet(urlPatterns = { "/celulares", "/celular/form", "/celular/insert", "/celular/delete", "/celular/update" })
public class CelularesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CelularDAO celularDAO;

	public CelularesController() {
		super();
		this.celularDAO = DAOFactory.createDAO(CelularDAO.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		String contextPath = req.getContextPath(); 

		switch (action) {
		case "/crud-manager/celular/form":
		case "/crud-manager-celulares/celular/form": 
			prepareMarcas(req);
			req.setAttribute("action", "insert");
			ControllerUtil.forward(req, resp, "/WEB-INF/views/celulares/form-celular.jsp");
			break;
		case "/crud-manager/celular/update":
		case "/crud-manager-celulares/celular/update": 
			String idStr = req.getParameter("celularId");
			int idCelular = Integer.parseInt(idStr);

			Celular celular = null;
			try {
				celular = celularDAO.findById(idCelular);
			} catch (ModelException e) {
				e.printStackTrace();
				ControllerUtil.errorMessage(req, "Erro ao carregar celular para edição.");
			}

			prepareMarcas(req);
			req.setAttribute("action", "update");
			req.setAttribute("celular", celular);
			ControllerUtil.forward(req, resp, "/WEB-INF/views/celulares/form-celular.jsp");
			break;
		default:
			listCelulares(req);
			ControllerUtil.transferSessionMessagesToRequest(req);
			ControllerUtil.forward(req, resp, "/WEB-INF/views/celulares/celulares.jsp");
		}
	}

	private void listCelulares(HttpServletRequest req) {
		List<Celular> celulares = null;
		try {
			celulares = celularDAO.listAll();
		} catch (ModelException e) {
			e.printStackTrace();
		}

		if (celulares != null)
			req.setAttribute("celulares", celulares);
	}

	private void prepareMarcas(HttpServletRequest req) {
		try {
			List<Marca> marcas = celularDAO.listAllMarcas();
			req.setAttribute("marcas", marcas);
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, "Erro ao carregar lista de marcas.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		String contextPath = req.getContextPath(); 

		switch (action) {
		case "/crud-manager/celular/insert":
		case "/crud-manager-celulares/celular/insert":
			insertCelular(req, resp);
			break;
		case "/crud-manager/celular/delete":
		case "/crud-manager-celulares/celular/delete":
			deleteCelular(req, resp);
			break;
		case "/crud-manager/celular/update":
		case "/crud-manager-celulares/celular/update":
			updateCelular(req, resp);
			break;
		default:
			System.out.println("URL inválida " + action);
		}

		ControllerUtil.redirect(resp, req.getContextPath() + "/celulares");
	}

	private void updateCelular(HttpServletRequest req, HttpServletResponse resp) {
		String celularIdStr = req.getParameter("celularId");
		String modelo = req.getParameter("modelo");
		String cor = req.getParameter("cor");
		String armazenamentoGBStr = req.getParameter("armazenamentoGB");
		String precoStr = req.getParameter("preco");
		String dataLancamentoStr = req.getParameter("dataLancamento");
		String idMarcaStr = req.getParameter("marca");

		Celular celular = new Celular(Integer.parseInt(celularIdStr));
		celular.setModelo(modelo);
		celular.setCor(cor);
		celular.setArmazenamentoGB(Integer.parseInt(armazenamentoGBStr));
		celular.setPreco(Double.parseDouble(precoStr));

		Date dataLancamento = ControllerUtil.formatDate(dataLancamentoStr);
		celular.setDataLancamento(dataLancamento);

		try {
			Marca marca = celularDAO.findMarcaById(Integer.parseInt(idMarcaStr));
			celular.setMarca(marca);
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, "Marca inválida selecionada.");
			return;
		}

		try {
			if (celularDAO.update(celular)) {
				ControllerUtil.sucessMessage(req, "Celular '" + celular.getModelo() + "' atualizado com sucesso.");
			} else {
				ControllerUtil.errorMessage(req, "Celular '" + celular.getModelo() + "' não pode ser atualizado.");
			}
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}

	private void deleteCelular(HttpServletRequest req, HttpServletResponse resp) {
		String celularIdParameter = req.getParameter("id");

		int celularId = Integer.parseInt(celularIdParameter);

		try {
			Celular celular = celularDAO.findById(celularId);

			if (celular == null)
				throw new ModelException("Celular não encontrado para deleção.");

			if (celularDAO.delete(celular)) {
				ControllerUtil.sucessMessage(req, "Celular '" + celular.getModelo() + "' deletado com sucesso.");
			} else {
				ControllerUtil.errorMessage(req, "Celular '" + celular.getModelo() + "' não pode ser deletado.");
			}
		} catch (ModelException e) {
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				ControllerUtil.errorMessage(req, "Não foi possível deletar o celular devido a dados relacionados.");
			} else {
				ControllerUtil.errorMessage(req, e.getMessage());
			}
			e.printStackTrace();
		}
	}

	private void insertCelular(HttpServletRequest req, HttpServletResponse resp) {
		String modelo = req.getParameter("modelo");
		String cor = req.getParameter("cor");
		int armazenamentoGB = Integer.parseInt(req.getParameter("armazenamentoGB"));
		double preco = Double.parseDouble(req.getParameter("preco"));
		String dataLancamentoStr = req.getParameter("dataLancamento");
		String idMarcaStr = req.getParameter("marca");

		Celular celular = new Celular();
		celular.setModelo(modelo);
		celular.setCor(cor);
		celular.setArmazenamentoGB(armazenamentoGB);
		celular.setPreco(preco);

		Date dataLancamento = ControllerUtil.formatDate(dataLancamentoStr);
		celular.setDataLancamento(dataLancamento);

		try {
			Marca marca = celularDAO.findMarcaById(Integer.parseInt(idMarcaStr));
			celular.setMarca(marca);
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, "Marca inválida selecionada.");
			return;
		}

		try {
			if (celularDAO.save(celular)) {
				ControllerUtil.sucessMessage(req, "Celular '" + celular.getModelo() + "' salvo com sucesso.");
			} else {
				ControllerUtil.errorMessage(req, "Celular '" + celular.getModelo() + "' não pode ser salvo.");
			}
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
}