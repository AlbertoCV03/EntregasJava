package Defensa5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import entrega5.ListaOrdenada;

public class ClientesPorAntiguedad<E> extends ListaOrdenada<Cliente> {

	public ClientesPorAntiguedad(Comparator<Cliente> comparator) {
		super(comparator);
	}
	

	public static ClientesPorAntiguedad<Cliente> of(Comparator comparator){
		return new ClientesPorAntiguedad<Cliente>(comparator);
		
	}
	
	public List<Cliente> topClientes(int n){
		
		List<Cliente> ls= this.elements();
		List<Cliente> res= new ArrayList<>();
		for(Integer i=0;i<n;i++) {
			res.add(ls.get(i));
		}
		
		return res;
		
	}
	
	
	public static void main(String[] args) {
		Cliente ana = Cliente.of("Ana", 5);
		Cliente juan = Cliente.of("Juan", 2);
		Cliente luis = Cliente.of("Luis", 7);
		
		Comparator<Cliente> comparador= Comparator.comparing(x->-x.antiguedad());
		
		ClientesPorAntiguedad<Cliente> a= ClientesPorAntiguedad.of(comparador);
		a.addAll(List.of(ana,juan,luis));
		System.out.println(a);
		System.out.println(a.topClientes(2));

	}
}
