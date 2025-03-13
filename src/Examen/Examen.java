package Examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Examen {

	public static Integer productoImpares(Integer n) {
		assert n>0 : String.format("n debe ser mayor que 0 y es: %d", n);
		Integer producto = 1;
        Integer contador = 0;
        Integer numero = 1;

        while (contador < n) {
            producto *= numero;
            numero += 2;
            contador++;
        }

        return producto;
    }
	
	public static Double sumaGeometricaAlternada(Double a1, Double r, Integer k ) {
		assert k>0 : String.format("k debe ser mayor que 0 y es: %d", k);
		assert a1 > 0 : String.format("a1 debe ser positivo");
		assert r > 0 : String.format("r debe ser positivo");
	
		Double res= a1; // el termino a1 se devuelve si se pide el primer termino de la secuencia (cosa que no tiene mucho sentido)
		
		for(Integer i= 2; i<k+1;i++) { //iniciamos el bucle en a2, ya que a1 esta definido
			
			res += Math.pow(-1,i)* a1* Math.pow(r, i-1);
			}

	        return res;
	}
	public static Integer factorialSinMultiplosDeTres(Integer n) {
		assert n>0 : "No se puede hacer el factorial de numeros negativos o 0";
		Integer res=1;
		for(Integer i=2;i<n+1;i++) {
			if (i%3 !=0) {
				res*=i;
			}
			
		}
		return res;
	}
	
	public static Double combinatorioSinMultiplosDeTres(Integer n, Integer k) {
		assert n>=k : "n debe ser mayor o igual que k";
		assert n>0 && k>0 : "No se puede hacer el factorial de numeros negativos o 0";

		return ((double) factorialSinMultiplosDeTres(n))/(factorialSinMultiplosDeTres(k)*(factorialSinMultiplosDeTres(n-k)));
	}
	
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
	
	public static List<String> filtrarLineasConsecutivas(String file, List<String> palabrasClave) {
        List<String> lineas = lineasDeFichero(file);
        List<String> resultado = new ArrayList<>();

        for (String linea : lineas) {
            String[] palabras = linea.split(" ");
            for (int i = 0; i < palabras.length - 1; i++) {
                if (palabrasClave.contains(palabras[i]) && palabrasClave.contains(palabras[i + 1])) {
                    resultado.add(linea);
                    break;
                }
            }
        }
        return resultado;
    }

	public static void main(String[] args) {
		
		//No me dio tiempo a implementar todas las pruebas *Parece ser que el assert no funciona
		try {
	        System.out.println(productoImpares(4));
	        System.out.println(productoImpares(-4));
	        
	    } catch (Exception e) {
	        System.err.println("Error en productoImpares: " + e.getMessage());
	    }

	    try {
	        System.out.println(sumaGeometricaAlternada(2.0, 3.0, 2));
	        System.out.println(sumaGeometricaAlternada(2.0, 3.0, -2));
	        System.out.println(sumaGeometricaAlternada(-4., 3.0, 2));
	        System.out.println(sumaGeometricaAlternada(2.0, -3.0, 2));
	    } catch (Exception e) {
	        System.err.println("Error en sumaGeometricaAlternada: " + e.getMessage());
	    }

	    try {
	        System.out.println(factorialSinMultiplosDeTres(6));
	    } catch (Exception e) {
	        System.err.println("Error en factorialSinMultiplosDeTres: " + e.getMessage());
	    }

	    try {
	        System.out.println(combinatorioSinMultiplosDeTres(7, 3));
	    } catch (Exception e) {
	        System.err.println("Error en combinatorioSinMultiplosDeTres: " + e.getMessage());
	    }

	    try {
	        System.out.println(filtrarLineasConsecutivas("resources/lin_quijote.txt", List.of("de")));
	    } catch (Exception e) {
	        System.err.println("Error en filtrarLineasConsecutivas con 'de': " + e.getMessage());
	    }

	    try {
	        System.out.println(filtrarLineasConsecutivas("resources/lin_quijote.txt", List.of("la")));
	    } catch (Exception e) {
	        System.err.println("Error en filtrarLineasConsecutivas con 'la': " + e.getMessage());
	    }
	}
	
	
}
