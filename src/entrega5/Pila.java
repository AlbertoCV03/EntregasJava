package entrega5;

public class Pila<E> extends AgregadoLineal<E> {
	
	public Pila() {
		super();
	}
	
	public static <E> Pila<E> of(){
		return new Pila();
	}
	
	@Override
    public void add(E e) {
		this.elementos.add(0,e);
	}
	
    public E top() {
    	return this.elementos.getFirst();
    }

	public static void main(String[] args) {
		System.out.println("----- Prueba de Pila -----");
		Pila<String> prueba= Pila.of();
		
		System.out.println("\nAñadiendo elementos");
		prueba.add("Primero");
		System.out.println(prueba.elements());
		prueba.add("Segundo");
		System.out.println(prueba.elements());
		prueba.add("Tercero");
		System.out.println(prueba.elements());
		
		System.out.println("\nTamaño de la lista");
		System.out.println(prueba.size());
		
		System.out.println("\nElemento en el tope");
		System.out.println(prueba.top());
		
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
