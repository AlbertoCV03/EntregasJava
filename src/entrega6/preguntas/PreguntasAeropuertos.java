package entrega6.preguntas;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import us.lsi.aeropuerto.Aeropuerto;
import us.lsi.aeropuerto.EspacioAereo;
import us.lsi.aeropuerto.Vuelo;
import us.lsi.aeropuerto.VueloProgramado;

public class PreguntasAeropuertos {
	
	public static String ciudadAeropuertoMayorFacturacionImperativo(LocalDateTime a, LocalDateTime b) {
		
		if (!a.isBefore(b)) {
			LocalDateTime temp=a;
			a=b;
			b=temp;
		}
		
		if(!(ChronoUnit.DAYS.between(a.toLocalDate(), b.toLocalDate())>1)) {
			throw new IllegalArgumentException("Debe haber almenos 1 dia de diferencia entre ambas fechas");
		}
		
		Double contG= 0.;
		List<String> ls= new ArrayList<>();
		
		EspacioAereo espacio= EspacioAereo.of();
		for(Aeropuerto aeropuerto:espacio.aeropuertos().todos()) {
			Double contB=0.; 
			for(VueloProgramado vueloProgramado:espacio.vuelosProgramados().todos()) {
				for(Vuelo vuelo:espacio.vuelos().todas()) {
					if(vuelo.fecha().isAfter(a) 
							&& (vuelo.fecha().isBefore(b))
							&& vuelo.codigoVueloProgramado().equals(vueloProgramado.codigo())
							&&vueloProgramado.codigoOrigen().equals(aeropuerto.codigo())){
						
						contB+=vueloProgramado.precio();
						if(contB>contG) {
							contG=contB;
							ls.add(0,aeropuerto.ciudad());
						}
						
						
					}
				}
			}
			
		}
		
		String res= (ls.isEmpty()) ? "No existe ninguna ciudad que cumpla con las fechas proporcionadas" : ls.getFirst();
		
		return res;
	}
	
	public static String ciudadAeropuertoMayorFacturacionFuncional(LocalDateTime a, LocalDateTime b) {
	    
		LocalDateTime aFinal = a.isBefore(b) ? a : b;
		LocalDateTime bFinal = a.isBefore(b) ? b : a;

	    if (ChronoUnit.DAYS.between(a.toLocalDate(), b.toLocalDate()) <= 1) {
	        throw new IllegalArgumentException("Debe haber al menos 1 día de diferencia entre ambas fechas");
	    }

	    EspacioAereo espacio = EspacioAereo.of();

	    return espacio.aeropuertos().todos().stream()
	    	    .map(aeropuerto -> {
	    	        double facturacion = espacio.vuelosProgramados().todos().stream()
	    	            .filter(vp -> vp.codigoOrigen().equals(aeropuerto.codigo()))
	    	            .flatMap(vp -> espacio.vuelos().todas().stream()
	    	                .filter(v -> v.codigoVueloProgramado().equals(vp.codigo()))
	    	                .filter(v -> v.fecha().isAfter(aFinal) && v.fecha().isBefore(bFinal))
	    	                .map(v -> vp.precio())
	    	            )
	    	            .mapToDouble(Double::doubleValue)
	    	            .sum();

	    	        return Map.entry(aeropuerto.ciudad(), facturacion);
	    	    })
	    	    .filter(entry -> entry.getValue() > 0)
	    	    .max(Comparator.comparingDouble(Map.Entry::getValue))
	    	    .map(Map.Entry::getKey)
	    	    .orElse("No existe ninguna ciudad que cumpla con las fechas proporcionadas");
	}

	
	public static void main(String[] args) {
		LocalDateTime a= LocalDateTime.of(2000, 5, 12, 0, 0);
		LocalDateTime b= LocalDateTime.of(2024, 5, 12, 0, 0);
		
		System.out.println(ciudadAeropuertoMayorFacturacionImperativo(a,b));
		System.out.println(ciudadAeropuertoMayorFacturacionFuncional(a,b));
	}
}
