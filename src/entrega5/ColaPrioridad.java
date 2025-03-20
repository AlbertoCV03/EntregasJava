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
	

	public static void main(String[] args) {
		System.out.println("----- Prueba de Cola -----");
		ColaPrioridad<String,Integer> prueba= ColaPrioridad.ofPriority();
		
		System.out.println("\nAñadiendo elementos");
		prueba.add(new PriorityElement("Primero",1));
		System.out.println(prueba.elements());
		prueba.add(new PriorityElement("Segundo",2));
		System.out.println(prueba.elements());
		prueba.add(new PriorityElement("Tercero",3));
		System.out.println(prueba.elements());
		prueba.add(new PriorityElement("Zero",0));
		System.out.println(prueba.elements());
		prueba.add(new PriorityElement("Diez",10));
		System.out.println(prueba.elements());
		prueba.add(new PriorityElement("Siete",7));
		System.out.println(prueba.elements());
		prueba.add(new PriorityElement("Nueve",9));
		System.out.println(prueba.elements());
		
		prueba.add("Veinte",20);
		System.out.println(prueba.elements());
		prueba.add("Quince",15);
		System.out.println(prueba.elements());
		
		prueba.decreasePriority("Veinte",19);
		System.out.println(prueba.elements());
		
		prueba.removeValue();
		System.out.println(prueba.elements());
		
		prueba.decreasePriority("Veinte",18);
		System.out.println(prueba.elements());
		
		prueba.add(new PriorityElement("Cuarenta",18));
		System.out.println(prueba.elements());
		
		List<String> valoresBloque= List.of("Treinta","Tren","Triangulo");
		prueba.addAllValues(valoresBloque,13);
		System.out.println(prueba.elements());

	}

}
