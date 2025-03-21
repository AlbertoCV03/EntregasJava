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
		Cola<String> testCola= Cola.of();
		
		System.out.println("\nAñadiendo elementos");
		testCola.add("Primero");
		System.out.println(testCola);
		testCola.add("Segundo");
		System.out.println(testCola);
		testCola.add("Tercero");
		System.out.println(testCola);
		
		System.out.println("\nTamaño de la cola");
		System.out.println(testCola.size());
		
		System.out.println("\nDesencolando elementos");
		System.out.println(testCola.remove());
		
		System.out.println("\nCola restante");
		System.out.println(testCola);
		
		System.out.println("\nDesencolando elementos");
		System.out.println(testCola.remove());
		
		System.out.println("\nCola restante");
		System.out.println(testCola);
		
		System.out.println("\nDesencolando elementos");
		System.out.println(testCola.remove());
		
		System.out.println("\nCola restante");
		System.out.println(testCola);
		
		
		System.out.println("\nEsta vacía?");
		System.out.println(testCola.isEmpty());
		
		try {
			testCola.remove();
		}catch(Exception e) {
			System.out.println("\n" +e);
		}

	}

}
