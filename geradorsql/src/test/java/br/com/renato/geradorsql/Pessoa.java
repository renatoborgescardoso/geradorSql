package br.com.renato.geradorsql;

import java.time.LocalDateTime;

import br.com.geradorsql.anotacao.Coluna;
import br.com.geradorsql.anotacao.Tabela;

@Tabela(nome = "pessoa")
public class Pessoa {

	@Coluna(nome = "nome")
	private String nome;

	@Coluna(nome = "endereco")
	private String endereco;

	@Coluna
	private int idade;

	@Coluna(nome = "data_nascimento")
	private LocalDateTime dataNascimento;
	
	public Pessoa(String nome, String endereco, int idade, LocalDateTime dataNascimento) {
		this.nome = nome;
		this.endereco = endereco;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public int getIdade() {
		return idade;
	}

}
