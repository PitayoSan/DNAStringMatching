import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DNAStringMatching {

	private File enzimasRestriccion, busqueda;
	private String adn;
	private ArrayList<String[][]> tablasKMP;
	private ArrayList<int[]> tablasKMPIndex;
	private ArrayList<String[]> enzimas;
	
	public ArrayList<String[]> getEnzima(){
		return this.enzimas;
	}
	
	//Recibe los dos archivos
	public void ingresaTexto() {
		//Pedir path de archivo con enzimas de restricción
		String possiblePath = "C:\\Users\\Hector\\Documents\\Algoritmos\\ProyectoAlgoritmos\\EnzimasRestriccion.txt";
//		do {
//			possiblePath = JOptionPane.showInputDialog("Ingresa el path del archivo que contenga las enzimas de restricción a buscar");
//			if(possiblePath == null) {
//				System.exit(0);
//			}
//		} while(possiblePath.equals(""));
		this.enzimasRestriccion = new File(possiblePath);

		//Pedir path de archivo con ADN donde se va a buscar
		possiblePath = "C:\\Users\\Hector\\Documents\\Algoritmos\\ProyectoAlgoritmos\\chlorella_vulgaris.dat";
//		do {
//			possiblePath = JOptionPane.showInputDialog("Ingresa el path del archivo que contenga el genoma donde se realizará la "
//					+ "busqueda");
//			if(possiblePath == null) {
//				System.exit(0);
//			}
//		} while(possiblePath.equals(""));
		this.busqueda = new File(possiblePath);
	}
	
	//Procesar archivo de busqueda
	public void procesarArchivo() {
		System.out.println("Path ingresado: " + this.busqueda.getPath());
		System.out.println("Eliminando espacios y caracteres basura");
		this.adn = convertFile(this.busqueda);
		System.out.println("...");
		System.out.println("Texto con formato correcto");
		System.out.println();
		System.out.println();
	}
	
	
	//La entrada es el número de enzima a buscar
	public void busquedaGeneral(int seleccion) {
		this.buscarEnzima(this.adn, seleccion);
	}
	
	//Para buscar una sola enzima
	private void buscarEnzima(String adn, int seleccion) {
		System.out.println("--------------------Comenzando busqueda--------------------");
		System.out.println("Buscando para " + this.enzimas.get(seleccion)[0]);
		System.out.println("...");
		int coincidencias = this.fuerzaBruta(adn, this.enzimas.get(seleccion)[1], seleccion);
		System.out.println("Se encontraron " + coincidencias + " coindidencias de los caracteres " + this.enzimas.get(seleccion)[1]);
		System.out.println();
		System.out.println("Busqueda finalizada");
		System.out.println();
		System.out.println();
	}
	
	//Para buscar 1 o más enzimas
	private void buscarEnzimas(String adn, int seleccion) {
		System.out.println("Comenzando busqueda");
		System.out.println();
		for(int nEnzimas = 0; nEnzimas < this.enzimas.size(); nEnzimas++) {
			String[] enzimaActual = this.enzimas.get(nEnzimas);
			System.out.println("Buscando para " + enzimaActual[0]);
			System.out.println("...");
			int coincidencias = this.fuerzaBruta(adn, enzimaActual[1], seleccion);
			System.out.println("Se encontraron " + coincidencias + " coindidencias de los caracteres " + enzimaActual[1]);
			System.out.println();
		}
		System.out.println("Busqueda finalizada");
	}
	
	//Alamecna las enzimas de restriccion
	private ArrayList<String[]> almacenarEnzimas(File archivo) {
		ArrayList<String[]> enzimas = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(archivo.toPath())) {
			String line = null;
		    while ((line = reader.readLine()) != null) {
				String[] enzima = new String[2];
				int indice = 0;
				String nombreEnzima = "";
				while(line.charAt(indice) != ' ') {
					nombreEnzima += line.charAt(indice);
					indice++;
				}
				enzima[0] = nombreEnzima;
				String dataEnzima = "";
				indice++;
				while(indice < line.length()) {
					dataEnzima += line.charAt(indice);
					indice++;
				}
				enzima[1] = dataEnzima.toLowerCase();
				enzimas.add(enzima);
		    }
		} catch (IOException x) {
			//throw new IOException("El archivo " + archivo.getPath() + " no se pudo leer");
		}
	    return enzimas;
	}
	
	private int fuerzaBruta(String strTexto, String strPatron, int enzima) {
		int busquedaActual = 0;
		int coincidencias = 0;
		char[] texto = strTexto.toCharArray();
		char[] patron = strPatron.toCharArray();
		int endPos = texto.length - patron.length+1;
		OUTER:
		for(int pos = 0; pos < endPos; pos++) {
			for(int i = 0; i < patron.length; i++) {
				if(texto[pos + i] != patron[busquedaActual]) {
					pos += (busquedaActual == 0) ? busquedaActual : busquedaActual - 1;
					busquedaActual = this.tablasKMPIndex.get(enzima)[busquedaActual];
					continue OUTER;
				}
				busquedaActual++;
			}
			System.out.println("Coincidencia en la posición: " + pos);
			coincidencias++;
			pos += busquedaActual - 1;
			busquedaActual = 0;
		}
		return coincidencias;
	}
	
	//Procesar archivo de busqueda
	private String convertFile(File archivo) {
		BufferedReader lector;
		StringBuilder builder = new StringBuilder();
		String ultimo = null;
		try {
			lector = new BufferedReader(new FileReader(archivo));
			String linea = lector.readLine();
			while (linea != null) {
				int blanks = 0;
				char[] line = linea.toCharArray();
				for (int i = 10; i < line.length; i++) {
					if (Character.isWhitespace(line[i])) {
						blanks++;
					}
				}
				char[] newLine = new char[line.length - (10 + blanks)];
				int cont = 0;
				for (int i = 10; i < line.length; i++) {
					if (!Character.isWhitespace(line[i])) {
						newLine[cont] = line[i];
						cont++;
					}
					
				}
				builder.append(new String(newLine));
				linea = lector.readLine();
				if (linea.length() == 0) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ultimo = builder.toString();
		return ultimo;
	}
	
	//Crea las tablas pertinentes y almacena la información de las enzimas
	public void crearTablas() {
		this.tablasKMP = new ArrayList<>();
		this.tablasKMPIndex = new ArrayList<>();

		//Almacenar las enzimas en un arreglo con nombre y secuencia
		this.enzimas = this.almacenarEnzimas(this.enzimasRestriccion);
		
		//Crea la tabla KMP para la enzima
		this.hacerMultiplesTablas();
	}
	
	private void hacerUnicaTabla(int seleccion) {
		String[] enzimaActual = this.enzimas.get(seleccion);
		System.out.println("Creando tabla KMP para enzima " + enzimaActual[0]);
		System.out.println("Cadena: " + enzimaActual[1]);
		System.out.println();
		this.llenarTablas(enzimaActual[1]);
		this.imprimirKMP(this.tablasKMP.get(seleccion), seleccion);
		System.out.println("Tabla creada");
		System.out.println();
		System.out.println();
		
	}
	
	private void hacerMultiplesTablas() {
		for(String[] enz: this.enzimas) {
			System.out.println("enzima: "+enz[0]);
		}
		for(int largo = 0; largo < this.enzimas.size(); largo++) {
			String[] enzimaActual = this.enzimas.get(largo);
			System.out.println("Creando tabla KMP para enzima " + enzimaActual[0]);
			System.out.println("Cadena: " + enzimaActual[1]);
			System.out.println();
			this.llenarTablas(enzimaActual[1]);
			this.imprimirKMP(this.tablasKMP.get(largo), largo);
			System.out.println("Tabla creada");
			System.out.println();
			System.out.println();
		}
	}
	
	private void llenarTablas(String patron) {
		String[][] tablaKMP = new String[3][patron.length()+1];
		int[] kmpTableIndex = new int[patron.length()+1];
		for(int charAt = 0; charAt < patron.length()+1; charAt++) {
			tablaKMP[0][charAt] = patron.substring(0, charAt);
			kmpTableIndex[charAt] = this.coincidenCaracteres(tablaKMP[0][charAt]);
			tablaKMP[1][charAt] = patron.substring(0, kmpTableIndex[charAt]);
		}
		this.tablasKMP.add(tablaKMP);
		this.tablasKMPIndex.add(kmpTableIndex);
	}
	
	//Analiza si en un patron coinciden los caracteres
	private int coincidenCaracteres(String patron) {
		int coincidencias = 0;
		int largo = patron.length();
		int prefijoPosible = largo-1;
		int index = largo-2;
		while(index >= 0) {
				if(coincidencias > 0 && patron.charAt(index) != patron.charAt(prefijoPosible)) {
					index += coincidencias-1;
					coincidencias = 0;
					prefijoPosible = largo-1;
				}
				char h1 = patron.charAt(index);
				char h2 = patron.charAt(prefijoPosible);
				if(h1 == h2) {
					coincidencias++;
					prefijoPosible--;
				}
				index--;
		}
		return coincidencias;
	}
	
	private void imprimirKMP(String[][] tablaKMP, int nTablasIndex) {
		System.out.println("Imprimiendo tabla: ");
		System.out.println();
		for(int i = 0; i < tablaKMP.length; i++) {
			System.out.print(i + "	");
			System.out.print(tablaKMP[0][i] + "		");
			System.out.print(tablaKMP[1][i] + "		");
			System.out.print(this.tablasKMPIndex.get(nTablasIndex)[i]);
			System.out.println();
		}
		System.out.println();
	}
	
}
