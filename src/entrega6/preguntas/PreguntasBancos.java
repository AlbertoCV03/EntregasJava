package entrega6.preguntas;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.bancos.Banco;
import us.lsi.bancos.Cuenta;
import us.lsi.bancos.Prestamo;
import us.lsi.ejemplos_b1_tipos.Persona;

public class PreguntasBancos {
	
	public static Map<String,Double> valorTotalPrestamosImperativo(Integer e, Double a, Double b, LocalDate f) {
		
		if (e<=18) {
			throw new IllegalArgumentException(String.format("La edad %d debe ser superior a 18 años", e));
		}
		
		if (a<=0 || b<=0) {
			throw new IllegalArgumentException(String.format("%.2f y %.2f deben ser positivos", a,b));
		}
		
		if (a > b) {
			throw new IllegalArgumentException(String.format("%.2f debe ser menor que %.2f", a,b));
		}
		
		Banco banco= Banco.of();
		Map<String,Double> clientePrestamo= new HashMap<>();
		
		for (Persona cliente:banco.personas().todos()) {
			
			for (Prestamo prestamo:banco.prestamos().todos()) {
				
				if (prestamo.dniCliente().equals(cliente.dni()) && cliente.edad().compareTo(e) == -1) {
					
					if (prestamo.cantidad() >= a && prestamo.cantidad() <= b) {
						
						//clientePrestamo.merge(cliente.nombreCompleto(), prestamo.cantidad(), Double::sum);
						if (!clientePrestamo.containsKey(cliente.nombreCompleto())) {
							clientePrestamo.put(cliente.nombreCompleto(), prestamo.cantidad());
						}else {
							
							clientePrestamo.put(cliente.nombreCompleto(), clientePrestamo.getOrDefault(cliente.nombreCompleto(), 0.0) + prestamo.cantidad());
							
						}
					}
					
					
					
					
				}
				
				
				
			}
			
		}
		
		
		return clientePrestamo;
	}
	
	public static Map<String,Double> valorTotalPrestamosFuncional(Integer e, Double a, Double b, LocalDate f) {
		Banco banco= Banco.of();
		
		Map<String, Double> res = banco.personas().todos().stream()
		        .flatMap(cliente ->
		            banco.prestamos().todos().stream()
		                .filter(prestamo -> prestamo.dniCliente().equals(cliente.dni()))
		                .filter(prestamo -> cliente.edad() < e)
		                .filter(prestamo -> prestamo.cantidad() >= a && prestamo.cantidad() <= b)
		                .map(prestamo -> Map.entry(cliente.nombreCompleto(), prestamo.cantidad()))
		        )
		        .collect(Collectors.groupingBy(
		            x->x.getKey(),
		            Collectors.summingDouble(x->x.getValue())
		        ));
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(valorTotalPrestamosImperativo(100,1.,100000.,LocalDate.of(1900, 5, 15)));
		
		System.out.println(valorTotalPrestamosFuncional(100,1.,100000.,LocalDate.of(1900, 5, 15)));
	}

}
