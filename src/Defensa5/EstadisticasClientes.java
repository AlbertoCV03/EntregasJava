package Defensa5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EstadisticasClientes {
	
	public static Map<Cliente, List<Compra>> agruparComprasPorClienteI(List<Compra> compras){
		
		Map<Cliente, List<Compra>> res= new HashMap<>();
		
		for(Compra e:compras) {
			if (!res.containsKey(e.cliente())) {
				res.put(e.cliente(), new ArrayList<>());
				res.get(e.cliente()).add(e);
			}else {
				res.get(e.cliente()).add(e);
			}
			
		}
		
		return res;
		
	}
	
	public static Map<Cliente, List<Compra>> agruparComprasPorClienteF(List<Compra> compras) {
			
			return compras.stream().collect(Collectors.groupingBy(Compra::cliente));		
		
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

		System.out.println(EstadisticasClientes.agruparComprasPorClienteF(List.of(c1,c2,c3,c4,c5,c6)));
		System.out.println(EstadisticasClientes.agruparComprasPorClienteI(List.of(c1,c2,c3,c4,c5,c6)));
	}

}
