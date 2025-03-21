package entrega5;

import java.util.Comparator;
import java.util.List;

public class ListaOrdenada<E> extends AgregadoLineal<E>{
	private Comparator<E> comparator;
	
	public ListaOrdenada(Comparator<E> comparator) {
		super();
		this.comparator= comparator;
	}
	public static <E> ListaOrdenada<E> of(Comparator<E> comparator){
		return new ListaOrdenada(comparator);
		
	}
	protected int indexOrder(E e) {
		
		Integer cont= 0;
		
		if (this.elementos.isEmpty()) {
			return 0;
		}else {
			for(E i : this.elementos) {
				Integer resC= this.comparator.compare(i, e);
				if(resC>=0) {
					break;
				}
				cont+=1;
			}
		}
		return cont;
	}
	
	@Override
	public void add(E e) {
		this.elementos.add(indexOrder(e), e);
	}

	public static void main(String[] args) {
		System.out.println("----- Prueba de ListaOrdenada -----");
		Comparator<Integer> comparador= Comparator.comparing(x->x);
		ListaOrdenada<Integer> testListaOrdenada= ListaOrdenada.of(comparador);
		
		System.out.println("\nAñadiendo elementos");
		testListaOrdenada.add(5);
		System.out.println(testListaOrdenada);
		testListaOrdenada.add(2);
		System.out.println(testListaOrdenada);
		testListaOrdenada.add(8);
		System.out.println(testListaOrdenada);
		testListaOrdenada.add(1);
		System.out.println(testListaOrdenada);
		testListaOrdenada.add(3);
		System.out.println(testListaOrdenada);
		
		System.out.println("\nTamaño de la lista");
		System.out.println(testListaOrdenada.size());
		
		System.out.println("\nEliminando el primer elemento");
		System.out.println(testListaOrdenada.remove());
		
		System.out.println("\nElementos después de eliminar");
		System.out.println(testListaOrdenada);
		
		System.out.println("\nAñadiendo elementos en lote");
		
		testListaOrdenada.addAll(List.of(4,5,6));
		System.out.println(testListaOrdenada);
		
		System.out.println("\nEliminando todos los elementos");
		List<Integer> b= testListaOrdenada.removeAll();
		System.out.println(b);
		
		System.out.println("\nEsta vacía?");
		System.out.println(testListaOrdenada.isEmpty());

	}

}
