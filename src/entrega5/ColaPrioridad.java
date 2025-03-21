package entrega5;

import java.util.ArrayList;
import java.util.List;

import entrega5.PriorityElement;

public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {
	
	public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority(){
		return new ColaPrioridad();
	}
	
	
    public ColaPrioridad() {
		super();
	}


	@Override
    public void add(PriorityElement<E, P> element) {
		
		Integer cont= 0;
		for(PriorityElement<E, P> e :elementos) {
			if(e.priority().compareTo(element.priority()) >0) {
				break;
			}
				cont+=1;
			}
		this.elementos.add(cont, element);
		
	}
	
	public void add(E value, P priority) {
		this.add(new PriorityElement(value,priority));
	}
    
    public List<E> valuesAsList(){
    	List<E> listValues= new ArrayList<>();
    	for(PriorityElement<E, P> e : this.elementos) {
    		listValues.add(e.value());
    	}
    	return listValues;
    }
    
    public void decreasePriority(E value, P newPriority) {
    	
    	if(this.valuesAsList().contains(value)) {
    		this.elementos.remove(this.valuesAsList().indexOf(value));
    		this.add(value,newPriority);
    
    	}
    }
    
    public E removeValue() {
    	this.elementos.remove(0);
    	
    	return this.valuesAsList().remove(0);
    	
    	
    }
    
    public void addAllValues(List<E> values, P priority) {
    	
    	for(E e:values) {
    		this.add(e,priority);
    	}
    	
    }
    
    
	

	@Override
	public String toString() {
		return valuesAsList().toString();
	}


	public static void main(String[] args) {
		System.out.println("----- Prueba de Cola -----");
		ColaPrioridad<String,Integer> testColaPrioridad= ColaPrioridad.ofPriority();
		
		System.out.println("\nAñadiendo elementos con prioridad");
		testColaPrioridad.add(new PriorityElement("Crítico",1));
		System.out.println(testColaPrioridad);
		
		testColaPrioridad.add("Normal",3);
		System.out.println(testColaPrioridad);
		
		testColaPrioridad.add(new PriorityElement("Urgente",2));
		System.out.println(testColaPrioridad);
		
		testColaPrioridad.add("Bajo",4);
		System.out.println(testColaPrioridad);
		
		System.out.println("\nElementos con sus prioridades");
		System.out.println(testColaPrioridad.elements());
		
		System.out.println("\nTamaño cola");
		System.out.println(testColaPrioridad.size());
		
		System.out.println("\nCambiando la prioridad de 'Normal' de 3 a 1:");
		testColaPrioridad.decreasePriority("Normal", 1);
		System.out.println(testColaPrioridad);
		System.out.println(testColaPrioridad.elements());
		
		System.out.println("\nDesencolando elementos por prioridad");
		System.out.println(testColaPrioridad.remove());
		
		System.out.println("\nDesencolando elementos por prioridad");
		System.out.println(testColaPrioridad.remove());
		
		System.out.println("\nDesencolando elementos por prioridad");
		System.out.println(testColaPrioridad.remove());
		
		System.out.println("\nDesencolando elementos por prioridad");
		System.out.println(testColaPrioridad.remove());
		
		System.out.println("\nCola restante");
		System.out.println(testColaPrioridad);
		
		System.out.println("\n¿Está vacía?");
		System.out.println(testColaPrioridad.isEmpty());
		
		try {
			testColaPrioridad.remove();
		}catch(Exception e) {
			System.out.println("\n"+ e);
		}
		
		System.out.println("\nAñadiendo elementos en bloque");
		testColaPrioridad.addAllValues(List.of("Tarea A", "Tarea B", "Tarea C"),2);
		System.out.println(testColaPrioridad);
		
		System.out.println("\nAñadir Tarea Urgente con prioridad 1");
		testColaPrioridad.add("Tarea Urgente",1);
		System.out.println(testColaPrioridad);



	}

}
