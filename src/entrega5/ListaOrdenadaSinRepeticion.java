package entrega5;

import java.util.Comparator;
import java.util.List;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {
	
	private Comparator<E> comparator;

	public ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
		super(comparator);
		this.comparator = comparator;
	}
	public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator){
		return new ListaOrdenadaSinRepeticion(comparator);
		
	}
	
	@Override
    public void add(E e) {
		
		if(!elementos.contains(e)) {
			this.elementos.add(this.indexOrder(e), e);
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("----- Prueba de ListaOrdenadaSinRepeticion -----");
		Comparator<Integer> comparador= Comparator.comparing(x->x);
		ListaOrdenada<Integer> testListaOrdenadaSinRepeticion= ListaOrdenadaSinRepeticion.of(comparador);
		
		System.out.println("\nAñadiendo elementos");
		testListaOrdenadaSinRepeticion.add(5);
		System.out.println(testListaOrdenadaSinRepeticion);
		testListaOrdenadaSinRepeticion.add(2);
		System.out.println(testListaOrdenadaSinRepeticion);
		testListaOrdenadaSinRepeticion.add(8);
		System.out.println(testListaOrdenadaSinRepeticion);
		testListaOrdenadaSinRepeticion.add(1);
		System.out.println(testListaOrdenadaSinRepeticion);
		testListaOrdenadaSinRepeticion.add(3);
		System.out.println(testListaOrdenadaSinRepeticion);
		testListaOrdenadaSinRepeticion.add(5);
		System.out.println(testListaOrdenadaSinRepeticion);
		testListaOrdenadaSinRepeticion.add(2);
		System.out.println(testListaOrdenadaSinRepeticion);
		
		System.out.println("\nTamaño de la lista");
		System.out.println(testListaOrdenadaSinRepeticion);
		
		System.out.println("\nEliminando el primer elemento");
		System.out.println(testListaOrdenadaSinRepeticion.remove());
		
		System.out.println("\nElementos después de eliminar");
		System.out.println(testListaOrdenadaSinRepeticion);
		
		System.out.println("\nAñadiendo elementos en lote");
		
		testListaOrdenadaSinRepeticion.addAll(List.of(4,6,7,4));
		System.out.println(testListaOrdenadaSinRepeticion);
		
		System.out.println("\nEliminando todos los elementos");
		List<Integer> b= testListaOrdenadaSinRepeticion.removeAll();
		System.out.println(b);
		
		System.out.println("\nEsta vacía?");
		System.out.println(testListaOrdenadaSinRepeticion.isEmpty());

	}

	

}
