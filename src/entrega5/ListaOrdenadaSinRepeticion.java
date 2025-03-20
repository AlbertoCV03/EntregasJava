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
		ListaOrdenada<Integer> prueba= ListaOrdenadaSinRepeticion.of(comparador);
		
		System.out.println("\nAñadiendo elementos");
		prueba.add(5);
		System.out.println(prueba.elements());
		prueba.add(2);
		System.out.println(prueba.elements());
		prueba.add(8);
		System.out.println(prueba.elements());
		prueba.add(1);
		System.out.println(prueba.elements());
		prueba.add(3);
		System.out.println(prueba.elements());
		prueba.add(5);
		System.out.println(prueba.elements());
		prueba.add(2);
		System.out.println(prueba.elements());
		
		System.out.println("\nTamaño de la lista");
		System.out.println(prueba.size());
		
		System.out.println("\nEliminando el primer elemento");
		System.out.println(prueba.remove());
		
		System.out.println("\nElementos después de eliminar");
		System.out.println(prueba.elements());
		
		System.out.println("\nAñadiendo elementos en lote");
		
		prueba.addAll(List.of(4,6,7,4));
		System.out.println(prueba.elements());
		
		System.out.println("\nEliminando todos los elementos");
		List<Integer> b= prueba.removeAll();
		System.out.println(b);
		
		System.out.println("\nEsta vacía?");
		System.out.println(prueba.isEmpty());

	}

	

}
