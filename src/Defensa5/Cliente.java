package Defensa5;

public record Cliente(String nombre, Integer antiguedad) implements Comparable<Cliente>{
	
	public static Cliente of(String nombre, Integer antiguedad) {
		return new Cliente(nombre,antiguedad);
	}

	public String nombre() {
		return nombre;
	}

	public Integer antiguedad() {
		return antiguedad;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", antiguedad=" + antiguedad + "]";
	}

	
	@Override
	public int compareTo(Cliente other) {
		Integer r = this.antiguedad().compareTo(other.antiguedad());
		return r;
	}
	

}
