package Defensa5;

import java.util.ArrayList;
import java.util.List;

import entrega5.Cola;

public class ColaComprasPendientes<E> extends Cola<Compra> {
	
	public ColaComprasPendientes() {
		super();
	}

	public static ColaComprasPendientes<Compra> of() {
		return new ColaComprasPendientes<Compra>();
	}
	
	public Compra buscarCompraPorDescripcion(String desc) {
		
		List<Compra> ls=this.elements();
		
		for(Compra e:ls) {
			if(e.descripcion()==desc) {
				return e;
			}
		}
		return null;
	}
	
	public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto){
		return this.elements().stream().filter(x->x.cliente().equals(cliente)).filter(x->x.descripcion()==producto).toList();
		
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
		Compra c6 = Compra.of(luis, "Poster gigante", 85.0);
		
		ColaComprasPendientes<Compra> a= ColaComprasPendientes.of();
		a.addAll(List.of(c1,c2,c3,c4,c5,c6));
		System.out.println(a);
		System.out.println(a.buscarCompraPorDescripcion("Agenda personalizada"));
		System.out.println(a.buscarCompraPorDescripcion("Agendapersonalizada"));
		System.out.println(a.filtrarPorClienteYProducto(luis, "Poster gigante"));

	}

}
