package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.Produto;

public class ProdutoDao {
	 
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PUjpa");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	EntityManager manager;
	TypedQuery<Produto> query;
	
	public void create(Produto c) throws Exception {
	manager = getEntityManager();
	manager.getTransaction().begin();
	manager.persist(c);
	manager.getTransaction().commit();
	}
	
	public void update(Produto c) throws Exception {
		manager = getEntityManager();
		manager.clear();
		manager.getTransaction().begin();
		manager.merge(c);
		manager.getTransaction().commit();
		}
	
	public void delete(Produto c) throws Exception {
		manager = getEntityManager();
		manager.clear();
		manager.getTransaction().begin();
		Produto resp = manager.find(Produto.class, c.getId());
		manager.remove(resp);
		manager.getTransaction().commit();
		}
	
	public List<Produto> findAll(){
		manager = getEntityManager();
		List<Produto> lista = manager.createQuery("select obj from Produto as obj ORDER BY dataValidade ASC",Produto.class).getResultList();
		return lista;
	}
	
	public Produto findByCode(Long cod) throws Exception{
		manager = getEntityManager();
		Produto produto= manager.find(Produto.class, cod);
		return produto;
	}
	
	public static void main(String[] args) {
		try {
			Produto c1 = new Produto(3l,null,null, null, null);

			
			ProdutoDao dao = new ProdutoDao();
			dao.delete(c1);
			
			System.out.println("Dados deletados...");
			dao.findAll().stream().forEach(x->System.out.println(x.getNome()+ "," +x.getdataValidade()));
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("error" + ex.getMessage());
		}
	}
}
