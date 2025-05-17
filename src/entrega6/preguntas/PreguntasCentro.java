package entrega6.preguntas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import us.lsi.centro.Alumno;
import us.lsi.centro.Alumnos;
import us.lsi.centro.Asignacion;
import us.lsi.centro.Asignaciones;
import us.lsi.centro.Asignatura;
import us.lsi.centro.Asignaturas;
import us.lsi.centro.Centro;
import us.lsi.centro.Grupo;
import us.lsi.centro.Grupos;
import us.lsi.centro.Titulo;
import us.lsi.centro.Matricula;
import us.lsi.centro.Matriculas;
import us.lsi.centro.Profesor;
import us.lsi.centro.Profesores;

public class PreguntasCentro {
	
	public static Double promedioEdadProfesoresImperativo (String dni) {
		
		Matriculas matric = Matriculas.of();
		Asignaciones asig=Asignaciones.of();
		Profesores profes= Profesores.of();
		Double cont=0.;
		Integer resP=0;
		
		for (Matricula matricula:matric.todas()) {
			
			if (matricula.dni().equals(dni)) {
				
				for(Asignacion e:asig.todas()) {
					
					if(matricula.idg().equals(e.idg()) && matricula.ida().equals(e.ida())) {
						
						for (Profesor prof:profes.todos()) {
							
							if (prof.dni().equals(e.dni())) {
								
								//System.out.println(prof.apellidos()+" "+ prof.edad());
								
								resP+=prof.edad();
								cont+=1;
								
							}
						}
						
						
					}
					
				}
			}
			
		}
			
		
		return resP/cont;
	}
	
	public static Double promedioEdadProfesoresFuncional (String dni) {
		
		Matriculas matric = Matriculas.of();
		Asignaciones asig=Asignaciones.of();
		Profesores profes= Profesores.of();
		
		return matric.todas().stream().filter(x->x.dni().equals(dni))
				.flatMap(x->asig.todas().stream().filter(a->x.idg().equals(a.idg()) && x.ida().equals(a.ida()))
						.flatMap(b->profes.todos().stream().filter(p->p.dni().equals(b.dni()))))
				.mapToInt(x->x.edad()).average().orElse(0.0);
		
	}
	
	public static Grupo grupoMayorDiversidadEdadImperativo() {
		Grupos grps = Grupos.of();
		Matriculas matriculas= Matriculas.of();
		Alumnos alums= Alumnos.of();
		Integer edadMax=Integer.MIN_VALUE;
		Integer edadMin=Integer.MAX_VALUE;
		Integer intervaloMax= 0;
		List<Grupo> res= new ArrayList<>();
		
		for (Grupo g:grps.todos()) {
			List<Integer> edades= new ArrayList<>();
			for (Matricula mat:matriculas.todas()) {
				if(g.idg().equals(mat.idg()) && g.ida().equals(mat.ida())) {
					
					edades.add(alums.alumno(mat.dni()).edad());
					
				}
				
			}
			
			for(Integer edad:edades) {
				if(edad>edadMax) {
					edadMax=edad;
				}
				
				if(edad<edadMin) {
					edadMin=edad;
				}
				
				Integer intervalo=edadMax-edadMin;
				
				
				if (intervalo>intervaloMax) {
					intervaloMax=intervalo;
					res.add(0, g);
				}
				
				
			}
			
		}
		
		return res.get(0);
	}
	
	public static Grupo grupoMayorDiversidadEdadFuncional() {
	    Grupos grps = Grupos.of();
	    Matriculas matriculas = Matriculas.of();
	    Alumnos alums = Alumnos.of();

	    return grps.todos().stream()
	        .map(g -> {
	            List<Integer> edades = matriculas.todas().stream()
	                .filter(mat -> g.idg().equals(mat.idg()) && g.ida().equals(mat.ida()))
	                .map(mat -> alums.alumno(mat.dni()).edad())
	                .toList();

	            int edadMax = edades.stream().max(Integer::compare).orElse(Integer.MIN_VALUE);
	            int edadMin = edades.stream().min(Integer::compare).orElse(Integer.MAX_VALUE);
	            int intervalo = edadMax - edadMin;

	            return new AbstractMap.SimpleEntry<>(g, intervalo);
	        })
	        .max(Comparator.comparingInt(AbstractMap.SimpleEntry::getValue))
	        .map(AbstractMap.SimpleEntry::getKey)
	        .orElse(null);
	}

	
	
	public static Alumno alumnoMasMatriculasImperativo() {
		Matriculas matric = Matriculas.of();
		Alumnos alums= Alumnos.of();
		Integer cont=0;
		Integer maxCont=0;
		List<Alumno> alumMasMatriculas= new ArrayList<>();
		
		for(Alumno alum:alums.todos()) {
			cont=0;
			for(Matricula mat:matric.todas()) {
				if(alum.dni().equals(mat.dni())) {
					cont+=1;
					if(cont>maxCont) {
						maxCont=cont;
						alumMasMatriculas.add(0,alum);
					}
				}
			}
		}
		//System.out.println(maxCont);
		
		return alumMasMatriculas.get(0);
		
		
	}
	
	public static Alumno alumnoMasMatriculasFuncional() {
		Matriculas matric = Matriculas.of();
		Alumnos alums= Alumnos.of();
		
		Map<Object, Long> a= matric.todas().stream()
		.flatMap(x->alums.todos().stream().filter(y->x.dni().equals(y.dni())))
		.collect(Collectors.groupingBy(x->x,Collectors.counting()));
		
		Object claveMaxima = a.entrySet().stream()
			    .max(Map.Entry.comparingByValue())
			    .map(Map.Entry::getKey)
			    .orElse(null);
		
		return (Alumno) claveMaxima;
		
	}
	
	public static String rangosEdadPorAlumnoImperativo(String rangoEdad){
		
		if (rangoEdad.isBlank() || rangoEdad.isEmpty() || rangoEdad ==null) {
			
			throw new IllegalArgumentException("La cadena no puede estar vacía");
		}
		
		Set<String> rangosUnicos = new HashSet<>();
		
		if (!rangoEdad.matches("\\s*\\d+\\s*-\\s*\\d+(\\s*,\\s*\\d+\\s*-\\s*\\d+)*")) {
		    throw new IllegalArgumentException("Formato de rango incorrecto. Se espera: '__ - __ , __ - __, ...'");
		}
		
		for(String rango:rangoEdad.split(",")) {
			
//			if (!rango.matches("\\d+\\s*-\\s*\\d+")) {
//			    throw new IllegalArgumentException("Formato de rango incorrecto");
//			}
			
			if (!rangosUnicos.add(rango.trim())) {
				
				throw new IllegalArgumentException("No puede haber 2 o más rangos iguales");
			}
			
			String[] valor= rango.split("-");
			
			
			
			if(Integer.valueOf(valor[0].trim())> Integer.valueOf(valor[1].trim())) {
				throw new IllegalArgumentException("El primer elemento del rango debe ser menor o igual que el segundo");
				
			}
			
			
		}
		
		Alumnos alums= Alumnos.of();
		String res="Alumnos por rango de edades: ";
		
		
		for(String edadesLim:rangoEdad.split(",")) {
			res= String.join("\n\t",res,"[" +edadesLim+"]:");
			SortedMap<Integer, List<String>> nombreEdadOrdenados = new TreeMap<>();

			String[] edades=edadesLim.trim().split("-");
			Integer edadMin=Integer.valueOf(edades[0].trim());
			Integer edadMax=Integer.valueOf(edades[1].trim());
			
			for(Alumno alum:alums.todos()) {
				
				if(edadMin <= alum.edad() && alum.edad()<=edadMax ) {
					List<String> nomAp = nombreEdadOrdenados.get(alum.edad());
					//System.out.println(nomAp==nombreEdadOrdenados.get(alum.edad()));
			        if (nomAp == null) {
			            nomAp = new ArrayList<>();
			            nombreEdadOrdenados.put(alum.edad(), nomAp);
			        }
			        nomAp.add(alum.nombre() + " " + alum.apellidos());
					//res= String.join("\n\t\t",res,alum.nombre() + " "+ alum.apellidos() +"-> "+ alum.edad().toString());
				}
			}
			for(Integer edad:nombreEdadOrdenados.keySet()) {
				for(List<String> nombreApellidosLs:nombreEdadOrdenados.values()) {
					for(String nombreApellidos:nombreApellidosLs) {
						res=String.join("\n\t\t",res,nombreApellidos + "-> " +edad.toString());
					}
				}
			}
		}
		return res;
	}
	public static String rangosEdadPorAlumnoFuncional(String rangoEdad) {
	    if (rangoEdad == null || rangoEdad.isBlank()) {
	        throw new IllegalArgumentException("La cadena no puede estar vacía");
	    }

	    if (!rangoEdad.matches("\\s*\\d+\\s*-\\s*\\d+(\\s*,\\s*\\d+\\s*-\\s*\\d+)*")) {
	        throw new IllegalArgumentException("Formato de rango incorrecto. Se espera: '__ - __ , __ - __, ...'");
	    }

	    Set<String> rangosUnicos = Arrays.stream(rangoEdad.split(","))
	        .map(String::trim)
	        .peek(rango -> {
	            if (!rango.matches("\\d+\\s*-\\s*\\d+")) {
	                throw new IllegalArgumentException("Formato de rango incorrecto");
	            }

	            String[] limites = rango.split("-");
	            int min = Integer.parseInt(limites[0].trim());
	            int max = Integer.parseInt(limites[1].trim());
	            if (min > max) {
	                throw new IllegalArgumentException("El primer elemento del rango debe ser menor o igual que el segundo");
	            }
	        })
	        .collect(Collectors.toCollection(() -> new HashSet<>()));

	    if (rangosUnicos.size() != rangoEdad.split(",").length) {
	        throw new IllegalArgumentException("No puede haber 2 o más rangos iguales");
	    }

	    Alumnos alums = Alumnos.of();

	    return Arrays.stream(rangoEdad.split(","))
	        .map(String::trim)
	        .map(rango -> {
	            String[] limites = rango.split("-");
	            int edadMin = Integer.parseInt(limites[0].trim());
	            int edadMax = Integer.parseInt(limites[1].trim());

	            SortedMap<Integer, List<String>> agrupados = alums.todos().stream()
	                .filter(a -> a.edad() >= edadMin && a.edad() <= edadMax)
	                .collect(Collectors.groupingBy(
	                    Alumno::edad,
	                    TreeMap::new,
	                    Collectors.mapping(a -> a.nombre() + " " + a.apellidos(), Collectors.toList())
	                ));

	            String alumnosPorEdad = agrupados.entrySet().stream()
	                .flatMap(entry -> entry.getValue().stream()
	                    .map(nombre -> "\t\t" + nombre + "-> " + entry.getKey()))
	                .collect(Collectors.joining("\n"));

	            return "\t[" + rango + "]:" + (alumnosPorEdad.isEmpty() ? "" : "\n" + alumnosPorEdad);
	        })
	        .collect(Collectors.joining("\n", "Alumnos por rango de edades: \n", ""));
	}
	
	public static String nombreProfesorMasGruposImperativo(Integer min, Integer max) {
		if(!(min<=max)) {
			throw new IllegalArgumentException(String.format("La edad minima %d debe ser menor que la máxima %d", min,max));
		}
		
		Grupos grps = Grupos.of();
		Profesores profs= Profesores.of();
		Asignaciones asigs= Asignaciones.of();
		Integer cont= 0;
		Integer contMax=0;
		List<Profesor> masProf=new ArrayList<>();
		
		for (Profesor prof:profs.todos()) {
			cont=0;
			//System.out.println(prof.nombreCompleto() + " "+ prof.edad());
			if (min<= prof.edad() && prof.edad()<=max) {
				
				for(Asignacion asig:asigs.todas()) {
					if(asig.dni().equals(prof.dni())) {
						cont+=1;
						if(cont>contMax) {
							
							contMax=cont;
							masProf.add(0,prof);
						}
						
//						for(Grupo grp:grps.todos()) {
//							
//							if(grp.ida().equals(asig.ida()) && grp.idg().equals(asig.idg())) {
//								cont+=1;
//								if(cont>contMax) {
//									
//									contMax=cont;
//									masProf.add(0,prof);
//								}
//								
//								
//							}
//						}
					}
					
				}
				
			}
			
		}
		
		String res= "No hay profesores que cumplan el criterio de edad.";
		if(! masProf.isEmpty()) {
			res=masProf.get(0).nombreCompleto();
			
		}
		
		return res;
	}
	
	public static String nombreProfesorMasGruposFuncional(Integer min, Integer max) {
	    if (min > max) {
	        throw new IllegalArgumentException(String.format("La edad mínima %d debe ser menor que la máxima %d", min, max));
	    }

	    Grupos grps = Grupos.of();
	    Profesores profs = Profesores.of();
	    Asignaciones asigs = Asignaciones.of();

	    return profs.todos().stream()
	        .filter(prof -> prof.edad() >= min && prof.edad() <= max)
	        .map(prof -> Map.entry(
	            prof,
	            (int) asigs.todas().stream()
	                .filter(asig -> asig.dni().equals(prof.dni()))
	                .count()
	        ))
	        .filter(entry -> entry.getValue() > 0)
	        .max(Comparator.comparingInt(Map.Entry::getValue))
	        .map(entry -> entry.getKey().nombreCompleto())
	        .orElse("No hay profesores que cumplan el criterio de edad.");
	}

	
	public static List<String> nombresAlumnosMayorNotaImperativo(Integer n, LocalDate a){
		
		if(n==null || a==null) {
			throw new IllegalArgumentException("n y a no pueden ser nulos");
		}
		
		if(!(1<=n && n<=10)) {
			throw new IllegalArgumentException("n debe estar entre 1 y 10");
		}
		
		Alumnos alums= Alumnos.of();
		List<Alumno> alumnosNotaOrdenados= new ArrayList<>(); 
		for(Alumno alum:alums.todos()) {
			if(alum.fechaDeNacimiento().getYear() > a.getYear()) {  //if(alum.fechaDeNacimiento().toLocalDate().compareTo(a)>=1)
				alumnosNotaOrdenados.add(alum);
			}
			
		}
		Collections.sort(alumnosNotaOrdenados, Comparator.comparing(x->-x.nota()));;
		//System.out.println(alumnosNotaOrdenados);
		List<String> nombresRes=new ArrayList<>();
		for(Integer i=0; i<n;i++) {
			if(alumnosNotaOrdenados.isEmpty()) {
				break;
			}
			nombresRes.add(alumnosNotaOrdenados.get(i).nombreCompleto() +" nota: "+ alumnosNotaOrdenados.get(i).nota());
		}
		return nombresRes;
	}
	
	public static List<String> nombresAlumnosMayorNotaFuncional(Integer n, LocalDate a){
		
		if(n==null || a==null) {
			throw new IllegalArgumentException("n y a no pueden ser nulos");
		}
		
		if(!(1<=n && n<=10)) {
			throw new IllegalArgumentException("n debe estar entre 1 y 10");
		}
		
		Alumnos alums= Alumnos.of();
		return alums.todos().stream().filter(alumno->alumno.fechaDeNacimiento().getYear()>a.getYear())
				.sorted(Comparator.comparing(x->-x.nota()))
				.map(x->x.nombreCompleto() +" nota: "+ x.nota()).toList().subList(0, n);
		
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(promedioEdadProfesoresImperativo("72842943B"));
		System.out.println(promedioEdadProfesoresFuncional("72842943B"));
		
		System.out.println(grupoMayorDiversidadEdadImperativo());
		System.out.println(grupoMayorDiversidadEdadFuncional());
		
		System.out.println(alumnoMasMatriculasImperativo());
		System.out.println(alumnoMasMatriculasFuncional());
		
		
		System.out.println(rangosEdadPorAlumnoImperativo("23-24"));
		System.out.println(rangosEdadPorAlumnoFuncional("23-24"));
		//System.out.println(rangosEdadPorAlumnoImperativo(""));
		//System.out.println(rangosEdadPorAlumnoImperativo("23+24;23+24"));
		//System.out.println(rangosEdadPorAlumnoImperativo("23-24,23-24"));
		
		System.out.println(nombreProfesorMasGruposImperativo(27,28));
		System.out.println(nombreProfesorMasGruposFuncional(27,28));
		
		
		System.out.println(nombresAlumnosMayorNotaImperativo(10,LocalDateTime.of(2000, 4, 16, 0, 0).toLocalDate()));
		System.out.println(nombresAlumnosMayorNotaFuncional(10,LocalDateTime.of(2000, 4, 16, 0, 0).toLocalDate()));
	}

}
