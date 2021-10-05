package manager;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.spi.SessionImplementor;

import entity.Produto;
import net.sf.jasperreports.engine.JasperRunManager;
import persistence.HibernateUtil;
import persistence.ProdutoDao;

@ManagedBean(name = "mb")
@RequestScoped
public class ManagerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Produto produto;
	private List<Produto> produtos;

	public Produto getproduto() {
		return produto;
	}

	public void setproduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getprodutos() {
		try {
			this.produtos = new ProdutoDao().findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return produtos;
	}

	public void setprodutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public ManagerBean() {
		this.produto = new Produto();
	}
	
	public void reportProduto() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			InputStream arquivo = fc.getExternalContext().getResourceAsStream("/report1.jasper");
			SessionImplementor sim = (SessionImplementor) HibernateUtil.getSessionFactory().openSession();
			Connection con = (Connection) sim.connection();
			byte[] report = JasperRunManager.runReportToPdf(arquivo, null, con);
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.setHeader("context-Disposition", "inline;filename=relatorio.pdf");
			response.setContentLength(report.length);
			OutputStream out = response.getOutputStream();
			out.write(report);
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	public void cadastrar() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ProdutoDao().create(this.produto);
			this.produto = new Produto();
			fc.addMessage(null, new FacesMessage("Dados Gravados"));
		}catch(Exception ex) {
			ex.printStackTrace();
			fc.addMessage(null, new FacesMessage("Error" + ex.getMessage()));
		}
	}
	
	public void alterar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ProdutoDao().update(this.produto);
			this.produto = new Produto();
			fc.addMessage(null, new FacesMessage("Dados Alterados"));
		}catch(Exception ex) {
			ex.printStackTrace();
			fc.addMessage(null, new FacesMessage("Error" + ex.getMessage()));
		}
	}
	
	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ProdutoDao().delete(this.produto);
			this.produto = new Produto();
			fc.addMessage(null, new FacesMessage("Dados Excluídos"));
		}catch(Exception ex) {
			ex.printStackTrace();
			fc.addMessage(null, new FacesMessage("Error" + ex.getMessage()));
		}
	}

}
