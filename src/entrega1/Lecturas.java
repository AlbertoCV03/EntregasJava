package entrega1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Lecturas {
	
//	public static List<String> lineasDeFichero(String file) {
//		Charset charSet = Charset.defaultCharset();
//		return lineasDeFichero(file, charSet.toString());
//	}
//	
//	
//	public static List<String> lineasDeFichero(String file, String charSet) {
//		List<String> lineas = null;
//		try {		
//			lineas = Files.readAllLines(Paths.get(file), Charset.forName(charSet));
//		} catch (IOException e) {
//			System.out.println(e.toString());
//		}
//		return lineas;
//	}
	
	public static List<String> lineasDeFichero(String file){
		List<String> lectura= new ArrayList<>();
		
		try (BufferedReader br= new BufferedReader(new FileReader(file))){
			
			String linea;
			while((linea=br.readLine()) != null) {
				lectura.add(linea);
			}
			
		}catch(IOException e){
			e.printStackTrace();
			
		}
		return lectura;
	}
	
	public static Integer numeroPalabrasFichero(String file,String sep,String cad) {
		List<String> lns= lineasDeFichero(file);
		Integer n=0;
		for(String linea:lns) {
			for(String palabra:linea.split(sep)) {
				if(palabra.toUpperCase().contentEquals(cad.toUpperCase())) {
				n+=1;
				}
			}
		}
		return n;
	}
	public static List<String> buscarLineasFichero(String file,String cad) {
		List<String> lns= lineasDeFichero(file);
		List<String> lineasConPalabra=new ArrayList<>();
		for(String linea:lns) {
			for(String palabra:linea.split(" ")) {
				if(palabra.toUpperCase().contentEquals(cad.toUpperCase())) {
				lineasConPalabra.add(linea.strip());
				}
			}
		}
		return lineasConPalabra;
	}
	
	public static List<String> palabrasUnicas(String file) {
		List<String> lns= lineasDeFichero(file);
		Set<String> palabrasRecorridas=new HashSet<>();
		for(String linea:lns) {
			for(String palabra:linea.split(" ")) {
				if(!palabrasRecorridas.contains(palabra)) {
					palabrasRecorridas.add(palabra);
				}
			}
			
		}
		List<String> listaUnica= new ArrayList<>(palabrasRecorridas);
		return listaUnica;
	}
	
	public static Double longitudPromedioLineas(String file) {
		List<String> lns= lineasDeFichero(file);
		Integer n1=0;
		Integer n2=0;
		Integer p=0;
		Double mediaLineas=0.;
		Double res=0.;
		for(String linea:lns) {
			n1+=1;
			p=0;
			res+=n2;
			n2=0;
			
			for(String palabra:linea.split(",")) {
				n2= n2+1;
				p+=palabra.strip().length();
				

				
			}
			
		}
		res+=n2;
		return res/n1;
	}
	
	//Tests
	
	
	
	public static int testNumeroPalabrasFichero(String fichero, String sep, String cad) {
        return Lecturas.numeroPalabrasFichero(fichero, sep, cad);
    }

    public static List<String> testBuscarLineasFichero(String fichero, String cad) {
        return Lecturas.buscarLineasFichero(fichero, cad);
    }

    public static List<String> testPalabrasUnicas(String fichero) {
        return Lecturas.palabrasUnicas(fichero);
    }

    public static Double testLongitudPromedioLineas(String filePath) {
        return Lecturas.longitudPromedioLineas(filePath);
    }
	
	public static void main(String[] args) {
//		System.out.println(numeroPalabrasFichero("resources/lin_quijote.txt"," ","quijote"));
//		System.out.println(buscarLineasFichero("resources/lin_quijote.txt","quijote"));
//		System.out.println(palabrasUnicas("resources/archivo_palabras.txt"));
//		System.out.println(longitudPromedioLineas("resources/palabras_random.csv"));
		
		System.out.println("___________________________________________");
        System.out.println("Test 6 \n");
        String fichero = "resources/lin_quijote.txt";
        String sep = " ";
        String cad = "quijote";
        System.out.println("El número de veces que aparece la palabra " + cad + " en el fichero resources/lin_quijote.txt es: " + testNumeroPalabrasFichero(fichero, sep, cad));
        System.out.println("___________________________________________");
        
        System.out.println("Test 7 \n");
        System.out.println("Las líneas en las que aparece la palabra " + cad + " son: " + testBuscarLineasFichero(fichero, cad));
        System.out.println("___________________________________________");
        
        System.out.println("Test 8 \n");
        fichero = "resources/archivo_palabras.txt";
        System.out.println("Las palabras únicas en el fichero resources/archivo_palabras.txt son: " + testPalabrasUnicas(fichero));
        System.out.println("___________________________________________");
        
        System.out.println("Test 9 \n");
        String filePath = "resources/palabras_random.csv";
        System.out.println("La longitud promedio de las líneas del fichero resources/palabras_random.csv es: " + testLongitudPromedioLineas(filePath));
        System.out.println("___________________________________________");
	}

}
