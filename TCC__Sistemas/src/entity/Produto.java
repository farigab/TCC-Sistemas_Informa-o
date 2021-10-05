package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(length=50)
	@Size(min=2, message="Minimo de letras para o produto")
	private String nome;
	@Column(length=80)
	private Date dataValidade;
	@Column(length=50)
	@Size(min=2, message="Minimo de letras para nome do cliente")
	private String nomeCliente;
	@Column
	private Integer quantidade;
	

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", dataValidade=" + dataValidade + "]";
	}

	public Produto() {
		super();
	}
	
	public Produto(Long id, String nome, Date dataValidade, String nomeCliente, Integer quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataValidade = dataValidade;
		this.nomeCliente = nomeCliente;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getdataValidade() {
		return dataValidade;
	}
	public void setdataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
