package Defensa5;


public record Compra(Cliente cliente, String descripcion, double importe) {
	
	public static Compra of(Cliente cliente, String descripcion, double importe) {
		return new Compra(cliente,descripcion,importe);
	}

	public Cliente cliente() {
		return cliente;
	}

	public String descripcion() {
		return descripcion;
	}

	public double importe() {
		return importe;
	}

	@Override
	public String toString() {
		return "Compra [Nombre de cliente=" + cliente.nombre() + ", descripcion=" + descripcion + ", importe=" + importe + "]";
	}
	



}
