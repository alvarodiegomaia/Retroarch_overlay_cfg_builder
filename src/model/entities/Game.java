package model.entities;

public class Game {
	
	private String titulo;
	private String rom;
	private String plataforma;
	
	public Game() {
		
	}

	public Game(String titulo, String rom, String plataforma) {
		super();
		this.titulo = titulo;
		this.rom = rom;
		this.plataforma = plataforma;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRom() {
		return rom;
	}

	public void setRom(String rom) {
		this.rom = rom;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	@Override
	public String toString() {
		return "Game [titulo=" + titulo + ", rom=" + rom + ", plataforma=" + plataforma + "]";
	}

	
	
	

}
