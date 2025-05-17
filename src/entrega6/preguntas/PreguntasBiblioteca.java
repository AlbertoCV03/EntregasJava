package entrega6.preguntas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.biblioteca.Biblioteca;
import us.lsi.biblioteca.Libro;
import us.lsi.biblioteca.Libros;
import us.lsi.biblioteca.Prestamo;

public class PreguntasBiblioteca {
	
	public static Map<Libro,Integer> masVecesPrestadoImperativo(){
		
		Biblioteca biblioteca= Biblioteca.of("C:/Users/Alberto CV/git/EntregasJava");
		Integer contGlobal=0;
		List<Libro> ls= new ArrayList<>();
		
		for (Libro libro:biblioteca.libros().todos()){
			Integer contBucle=0;
			for (Prestamo prestamo:biblioteca.prestamos().todos()){
				if(libro.isbn().equals(prestamo.isbn())) {
					contBucle+=1;
					if(contBucle >contGlobal) {
						contGlobal= contBucle;
						ls.add(0,libro);
						
					}
					
				}
				
			}
			
		}
		
		Map<Libro,Integer> res= new HashMap<>();
		res.put(ls.getFirst(),contGlobal);
		
		return res;
		
	}
	
	public static Map.Entry<Libro,Long> masVecesPrestadoFuncional(){
		
		Biblioteca biblioteca= Biblioteca.of("C:/Users/Alberto CV/git/EntregasJava");
		
		
		return biblioteca.prestamos().todos().stream()
				.flatMap(prestamos->biblioteca.libros().todos().stream().filter(libros->prestamos.isbn().equals(libros.isbn())))
				.collect(Collectors.groupingBy(x->x,Collectors.counting()))
				.entrySet().stream().max(Map.Entry.comparingByValue())
		        .orElse(null);
	}
	
	public static Map <String, Set<String>> librosPorAutorImperativo(Libros libros, List<String> nombres){
		
		Map <String, Set<String>> res= new HashMap<>();
		
		if(nombres == null) {
			Set<String> nombresNoRepetidos= new HashSet<>();
			for (Libro libro:libros.todos()) {
				nombresNoRepetidos.add(libro.autor());
			}
			
			nombres= new ArrayList<>(nombresNoRepetidos);
		}
		for (Libro libro: libros.todos()) {
			Set<String> ls= new HashSet<>();
			for(String nombre:nombres) {
				if(libro.autor().toLowerCase().strip().equals(nombre.toLowerCase().strip())) {
					
					if(!res.containsKey(nombre)) {
						ls.add(libro.titulo());
						res.put(nombre, ls);
					}else {
						res.get(nombre).add(libro.titulo());
					}
					
				}
			}
			
		}
		
		return res;
	}
	
	public static Map <String, Set<String>> librosPorAutorFuncional(Libros libros, List<String> nombres){
		
		Set<Libro> todosLosLibros = libros.todos();

	    List<String> nombresFinales = (nombres == null)
	        ? todosLosLibros.stream()
	              .map(libro -> libro.autor())
	              .distinct()
	              .collect(Collectors.toList())
	        : nombres;

	    return nombresFinales.stream()
	        .collect(Collectors.toMap(
	            nombre -> nombre,
	            nombre -> todosLosLibros.stream()
	                .filter(libro -> libro.autor().strip().equalsIgnoreCase(nombre.strip()))
	                .map(Libro::titulo)
	                .collect(Collectors.toSet())
	        ));
		
	}
	
	public static void main(String[] args) {
//		System.out.println(masVecesPrestadoImperativo());
//		System.out.println(masVecesPrestadoFuncional());
		
		Libros libros= Libros.of();
		List<String> nombres= new ArrayList<>();
		nombres.add("Xiomara Brunilda Menéndez Cabeza");
		nombres.add("Mariano Uriarte Mínguez");
		
		System.out.println(librosPorAutorImperativo(libros,nombres));
		System.out.println(librosPorAutorFuncional(libros,nombres));
		
		System.out.println(librosPorAutorImperativo(libros,null));
		System.out.println(librosPorAutorFuncional(libros,null));
	}

}
