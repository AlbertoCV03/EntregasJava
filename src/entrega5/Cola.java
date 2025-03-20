package entrega5;

import java.util.Comparator;
import java.util.List;

public class Cola<E> extends AgregadoLineal<E> {

	public Cola() {
		super();
	}
	
	public static <E> Cola<E> of(){
		return new Cola();
	}
    @Override
    public void add(E e) {
    	Integer index = this.size();
    	this.elementos.add(index,e);
    }
    
    public static void main(String[] args) {
		System.out.println("----- Prueba de Cola -----");
		Cola<String> prueba= Cola.of();
		
		System.out.println("\nAñadiendo elementos");
		prueba.add("Primero");
		System.out.println(prueba.elements());
		prueba.add("Segundo");
		System.out.println(prueba.elements());
		prueba.add("Tercero");
		System.out.println(prueba.elements());
		
		System.out.println("\nTamaño de la lista");
		System.out.println(prueba.size());
		
		System.out.println("\nDesencolando elementos");
		System.out.println(prueba.remove());
		
		System.out.println("\nCola restante");
		System.out.println(prueba.elements());
		
		System.out.println("\nDesencolando elementos");
		System.out.println(prueba.remove());
		
		System.out.println("\nCola restante");
		System.out.println(prueba.elements());
		
		System.out.println("\nDesencolando elementos");
		System.out.println(prueba.remove());
		
		System.out.println("\nCola restante");
		System.out.println(prueba.elements());
		
		
		System.out.println("\nEsta vacía?");
		System.out.println(prueba.isEmpty());

	}

}
