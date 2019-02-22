package com.algaworks.socialbooks.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.algaworks.socialbooks.client.LivrosClient;
import com.algaworks.socialbooks.client.domain.Livro;

public class Aplicacao {
	private static LivrosClient cliente = new LivrosClient("http://localhost:8080", "breno", "s3mS3nh4");
	private static String localizacao;

	public static void main(String[] args) throws ParseException {
		listarLivros();
		salvarLivros();
		buscarLivros();
	}

	public static void listarLivros() {
		List<Livro> listaLivros = cliente.listar();

		for (Livro livro : listaLivros) {
			System.out.println("Livro: " + livro.getNome());
		}
	}

	public static void salvarLivros() throws ParseException {
		Livro livro = new Livro();

		livro.setNome("Git passo a passo");
		livro.setEditora("Algaworks");

		SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
		livro.setPublicacao(publicacao.parse("01/01/2016"));

		livro.setResumo("Livro sobre o GIT");

		localizacao = cliente.salvar(livro);

		System.out.println("URI do livro cadastrado: " + localizacao);
	}

	public static void buscarLivros() {
		Livro livroBuscado = cliente.buscar(localizacao);
		System.out.println("Livro buscado: " + livroBuscado.getNome());
	}
}
