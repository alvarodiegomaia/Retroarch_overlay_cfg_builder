package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Game;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Scanner scArquivo = null;

		List<Game> listaGames = new ArrayList<>();

		System.out.print("Digite o nome do arquivo de entrada: ");
		String arquivo = sc.nextLine();
		String strCaminhoArquivo = "gameData\\" + arquivo + ".txt";

		File caminhoArquivo = new File(strCaminhoArquivo);
		System.out.println("Caminho do arquivo completo: " + caminhoArquivo.getPath());
		System.out.println("Caminho: " + caminhoArquivo.getParent());
		System.out.println("Nome do arquivo: " + caminhoArquivo.getName());

		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
			scArquivo = new Scanner(br);
			String strLeitura;

			while (scArquivo.hasNextLine()) {
				strLeitura = scArquivo.nextLine();
				int idx0 = strLeitura.indexOf("<");
				int idx1 = strLeitura.indexOf(">");

				if (idx0 != -1) {
					String parametro = strLeitura.substring(idx0 + 1, idx1);
					// System.out.println(parametro);

					if (stringCompare(parametro, "Game") == 0) {
						int sair = 0;
						String rom = null;
						String plataforma = null;
						String titulo = null;
						
						do {
							strLeitura = scArquivo.nextLine();
							int idGame0 = strLeitura.indexOf("<");
							int idGame1 = strLeitura.indexOf(">");
							if (idGame0 != -1) {
								parametro = strLeitura.substring(idGame0 + 1, idGame1);
							}

							// Condicional que obtem o nome da rom
							if (stringCompare(parametro, "ApplicationPath") == 0) {
								int idGameApplicationPath0 = strLeitura.indexOf(">");
								int idGameApplicationPath1 = strLeitura.indexOf("</ApplicationPath>");
								String caminhoDaRom = strLeitura.substring(idGameApplicationPath0 + 1,
										idGameApplicationPath1);
								String[] textoSeparado = caminhoDaRom.split("/");
								rom = textoSeparado[2].substring(0, textoSeparado[2].indexOf(".zip"));
							}

							// Condicional que obtem o nome da plataforma
							if (stringCompare(parametro, "Platform") == 0) {
								int idGamePlatform0 = strLeitura.indexOf(">");
								int idGamePlatform1 = strLeitura.indexOf("</Platform>");
								plataforma = strLeitura.substring(idGamePlatform0 + 1, idGamePlatform1);
							}
							
							// Condicional que obtem o titulo
							if (stringCompare(parametro, "Title") == 0) {
								int idGameTitle0 = strLeitura.indexOf(">");
								int idGameTitle1 = strLeitura.indexOf("</Title>");
								titulo = strLeitura.substring(idGameTitle0 + 1, idGameTitle1);
							}

							if (stringCompare(parametro, "/Game") == 0) {
								sair = 1;
							}


						} while (sair == 0);
						listaGames.add(new Game(titulo, rom, plataforma));

					}
				}

			}
		} catch (

		IOException e) {
			e.printStackTrace();
		}
		
		for (Game lista : listaGames) {
			System.out.println(lista);
		}

		sc.close();
	}

	public static int stringCompare(String str1, String str2) {

		int l1 = str1.length();
		int l2 = str2.length();
		int lmin = Math.min(l1, l2);

		for (int i = 0; i < lmin; i++) {
			int str1_ch = (int) str1.charAt(i);
			int str2_ch = (int) str2.charAt(i);

			if (str1_ch != str2_ch) {
				return str1_ch - str2_ch;
			}
		}

		// Edge case for strings like
		// String 1="Geeks" and String 2="Geeksforgeeks"
		if (l1 != l2) {
			return l1 - l2;
		}

		// If none of the above conditions is true,
		// it implies both the strings are equal
		else {
			return 0;
		}
	}

}
