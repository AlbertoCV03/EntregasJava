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
    	if(this.isEmpty()) {
    		throw new IllegalArgumentException("La pila esta vacía");
    	}
    	return this.elementos.getFirst();
    }

	public static void main(String[] args) {
		System.out.println("----- Prueba de Pila -----");
		Pila<Double> testPila= Pila.of();
		
		System.out.println("\nAñadiendo elementos");
		testPila.add(1.1);
		System.out.println(testPila);
		testPila.add(2.2);
		System.out.println(testPila);
		testPila.add(3.3);
		System.out.println(testPila);
		
		System.out.println("\nTamaño de la pila");
		System.out.println(testPila.size());
		
		System.out.println("\nElemento en el tope");
		System.out.println(testPila.top());
		
		System.out.println("\nDesapilando elementos");
		System.out.println(testPila.remove());
		
		System.out.println("\nPila restante");
		System.out.println(testPila);
		
		System.out.println("\nDesapilando elementos");
		System.out.println(testPila.remove());
		
		System.out.println("\nPila restante");
		System.out.println(testPila.elements());
		
		System.out.println("\nDesapilando elementos");
		System.out.println(testPila.remove());
		
		System.out.println("\nPila restante");
		System.out.println(testPila.elements());
		
		
		System.out.println("\nEsta vacía?");
		System.out.println(testPila.isEmpty());
		
		try {
			testPila.top();
		}catch(Exception e) {
			System.out.println("\n" + e);
		}

	}

}
