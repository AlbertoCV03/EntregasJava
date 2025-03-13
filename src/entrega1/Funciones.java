package entrega1;

import java.util.function.Function;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Funciones {
	
	public static Integer producto(Integer n, Integer k) {
		assert n>k : String.format("n debe ser mayor que k y es: %d", n);
		Integer resFinal=1;
		for(Integer i=0; i<k+1;i++) {
			resFinal= resFinal* (n-i+1);
		}
		return resFinal;
	}
	
	public static Integer secuenciaGeometrica(Integer a1, Integer r,Integer k) {
		Integer res=1;
		
		for (Integer i=2; i<k+1;i++) {
			
			Integer an=(int) (a1*(Math.pow(r, (i-1))));
			res=an*a1;
			a1=res;
			
		}
		return res;
	}
	
	public static Integer factorial(Integer n) {
		assert n>0 : "No se puede hacer el factorial de numeros negativos o 0";
		Integer res=1;
		for(Integer i=2;i<n+1;i++) {
			res*=i;
		}
		return res;
	}
	
	public static Integer combinatorio(Integer n, Integer k) {
		assert n>=k : "n debe ser mayor o igual que k";
		return (factorial(n))/(factorial(k)*(factorial(n-k)));
	}
	
	public static double numeroS(Integer n, Integer k) {
		assert n>=k : "n debe ser mayor o igual que k";
		Double resSumatorio=0.;
		for(Integer i=0;i<k;i++ ) {
			resSumatorio =resSumatorio+( Math.pow(-1, i) * combinatorio(k+1,i+1)* Math.pow(k-i,n));
		}
		return (1./factorial(k))*resSumatorio;
		
	}
	
//	@FunctionalInterface
//	interface Funcion {
//	    double de(double a);
//	}
	
//	public static double newton(Funcion f, Funcion fd, double a, double e) {
//		double x2=0;
//		while(Math.abs(f.de(a))>e) {
//			x2=a-(f.de(a)/fd.de(a));
//			a=x2;
//		}
//		return x2;
//	}
	
	public static double newton(Function<Double,Double> f, Function<Double,Double> fd, double a, double e) {
		double x2=0;
		while(Math.abs(f.apply(a))>e) {
			x2=a-(f.apply(a)/fd.apply(a));
			a=x2;
		}
		return x2;
	

	}	
	
	//Tests
	
	public static void main(String[] args) {
//		System.out.println(producto(4,2));
//		System.out.println(secuenciaGeometrica(3,5,2));
//		System.out.println(factorial(5));
//		System.out.println(combinatorio(6,3));
//		System.out.println(numeroS(4,2));		
//		Function<Double,Double> f= (x)->2*x*x;
//		Function<Double,Double> fd=(x)->4*x;
//		
//		System.out.println(newton(f,fd,3.,0.001));
		
		System.out.println("___________________________________________");
        System.out.println("Test 1 \n");
        
        Integer n = 4, k = 2;
        
        System.out.println("El resultado de " + n + " y " + k + " es : " + producto(n, k));
        System.out.println("___________________________________________");

        System.out.println("Test 2 \n");
        
        Integer a1 = 3, r = 5;
        
        System.out.println("El producto de la secuencia geométrica con a1 = " + a1 + ", r = " + r + " y k = " + k + " es: " + secuenciaGeometrica(a1, r, k));
        System.out.println("___________________________________________");
        
        System.out.println("Test 3 \n");
        
        n = 4; k = 2;
        
        System.out.println("El número combinatorio de " + n + " y " + k + " es: " + combinatorio(n, k));
        System.out.println("___________________________________________");
        
        System.out.println("Test 4 \n");
        
        n = 4; k = 2;
        
        System.out.println("El número S(" + n + "," + k + ") es: " + numeroS(n, k));
        System.out.println("___________________________________________");
        
        System.out.println("Test Newton \n");
        
        Function<Double, Double> f = x -> 2 * Math.pow(x, 2);
        Function<Double, Double> fd = x -> 4 * x;
        double a = 3, e = 0.001;
        
        System.out.println("Resultado de la función 5 con a= " + a + " y e = " + e + ", f(x) = 2x^2 y fd(x) = 4x: " + newton(f, fd, a, e));
        System.out.println("___________________________________________");
		
	}
	
	
	
	
}
