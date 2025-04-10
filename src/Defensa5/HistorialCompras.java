package Defensa5;

import java.util.List;

import entrega5.Pila;

public class HistorialCompras<E> extends Pila<Compra> {
	

	public HistorialCompras() {
		super();
	}
	
	public static HistorialCompras<Compra> of() {
		return new HistorialCompras<Compra>();
	}
	
	public double totalGastadoPor(Cliente cliente) {
		
		return this.elements().stream().filter(x->x.cliente().equals(cliente)).mapToDouble(x->x.importe()).sum();
	}
	
	public List<Compra> comprasMayoresA(double cantidad){
		return this.elements().stream().filter(x->x.importe()>cantidad).toList();
	}
	
	public static void main(String[] args) {
		Cliente ana = Cliente.of("Ana", 5);
		Cliente juan = Cliente.of("Juan", 2);
		Cliente luis = Cliente.of("Luis", 7);
		
		Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
		Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
		Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
		Compra c4 = Compra.of(luis, "Poster gigante", 80.0);
		Compra c5 = Compra.of(luis, "Poster pequeño", 40.0);
		
		HistorialCompras<Compra> a= HistorialCompras.of();
		a.addAll(List.of(c1,c2,c3,c4,c5));
		System.out.println(a);
		System.out.println(a.totalGastadoPor(luis));
		System.out.println(a.comprasMayoresA(50));
		
		

	}

}
