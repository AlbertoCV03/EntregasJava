package entrega5;

import java.util.ArrayList;
import java.util.List;

public abstract class AgregadoLineal<E> {
	List<E> elementos= new ArrayList<>();
	
	public int size() {
		return elementos.size();
	}
    public boolean isEmpty() {
    	return elementos.isEmpty();
    }
    public List<E> elements(){
    	return elementos;
    }
    public abstract void add(E e);
    public void addAll(List<E> list) {
    	for(E e : list) {
    		this.add(e);
    	}
    }
    public E remove() {
    	return elementos.removeFirst();
    }
    public List<E> removeAll(){
    	List<E> eliminados= new ArrayList<>(elementos);
    	this.elementos= new ArrayList<>();
    	
    	return eliminados;
    }

}
