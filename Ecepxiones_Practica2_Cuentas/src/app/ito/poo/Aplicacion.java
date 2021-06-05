package app.ito.poo;
import java.time.LocalDate;
import java.util.Scanner;
import clases.ito.poo.CuentaBancaria;
import clases.ito.poo.CuentasBancarias;
import ecepxiones.ito.poo.ExcepcionesNumCuenta;
import ecepxiones.ito.poo.ExcepcionDeposito;
import ecepxiones.ito.poo.ExcepcionRetiro;
import ecepxiones.ito.poo.ExcepcionSadoApertura;
import ecepxiones.ito.poo.ExcepcionRetiro_Invaido;
import ecepxiones.ito.poo.ExcepcionCuenta;
import ecepxiones.ito.poo.ExcepcionElimina0;
public class Aplicacion {

static CuentasBancarias c;
static Scanner io= new Scanner (System.in);

 static CuentaBancaria capturaCuenta(){
    CuentaBancaria n= new CuentaBancaria();
    try {
    System.out.print("Proporciona tu numero de cuenta:");
    n.setNumCuenta(io.nextLong());io.nextLine();
    }catch(ExcepcionesNumCuenta e){
    System.err.println(e.getMessage());
    }try {
    System.out.print("Proporciona saldo de la cuenta:");
    n.setSaldo(io.nextFloat());io.nextLine();
    }catch(ExcepcionSadoApertura e){
    System.err.println(e.getMessage());
    }
	System.out.print("Proporciona tu nombre del cliente:");
	n.setNomCliente(io.nextLine());
	System.out.print("Proporciona la fecha: aaaa-mm-dd");
	String fecha=io.nextLine();
	n.setFechaApertura(LocalDate.parse(fecha));
	return n;
}
 //=================================================================================
 static void agregarCuenta() throws ExcepcionCuenta{
	 CuentaBancaria nueva;
	 nueva=capturaCuenta();
	 if(c.addItem(nueva))
		 System.out.println("Exito:se agrego la cuenta sin problema");
     else
	     throw new ExcepcionCuenta("Error:la cuenta existe por lo que no se pudo agregar");
}
 //==========================================================================
 static CuentaBancaria alazar(String operacion){
	CuentaBancaria cuenta=null; int i=0;
	io.nextLine();
    for (;i <c.getSize();i++) {
      cuenta = c.getItem(i);
      System.out.println(c.getItem(i)+"\n Es la cuenta a "+operacion);
      if(io.nextLine().charAt(0)=='s')
    	 break;
      cuenta=null;
    }
	return cuenta;
 }
 //===========================================================================
  static void deposito(){
	  if(!c.isFree()) {
	  CuentaBancaria cuenta=alazar("Depositar");
	  if(cuenta!=null) { 
		  try {
		  System.out.println("Proporciona cantidad a depositar:");
		  cuenta.depositoD(io.nextFloat());
		  }catch(ExcepcionDeposito e){
		      System.err.println(e.getMessage());
		  }
	  }else 
		  System.out.println("Error");
}	  
	  System.out.println("No exisaten cuentas");
} 
//============================================================================
 static void retiro() throws ExcepcionRetiro_Invaido ,ExcepcionRetiro{
	   if(!c.isFree()) {
			  CuentaBancaria cuenta=alazar("Retira");
			  if(cuenta!=null) { 
				  try {
				  System.out.println("Proporciona cantidad a retirar:");
				  cuenta.retiroR(io.nextFloat());
				  }catch(ExcepcionRetiro e){
					    System.err.println(e.getMessage());}
			      catch(ExcepcionRetiro_Invaido e){
				    System.err.println(e.getMessage());}
			  }else 
				  System.out.println("Error"); 
	    }else
		  System.out.println("No exisaten cuentas");
}
 //==============================================================================
   static void elimina() throws ExcepcionElimina0{
	   if(!c.isFree()) {
			  CuentaBancaria cuenta=alazar("Elimina");
			  if(cuenta.getSaldo()==0) {
			  if(cuenta!=null) { 
				  c.delete(cuenta);
				  System.out.println("Se elimino con exito");
			  }	else 
				  System.out.println("Error");
			  }else
				  throw new ExcepcionElimina0("La cuenta con saldo distinto a cero no se puede eliminar.");
		}else	  
			  System.out.println("No exisaten cuentas");
}
   //============================================================= señalizador 
   static void listado(){
		if(!c.isFree()) {
			System.out.println("-----Lista de Cuentas----");
			for(int i=0;i<c.getSize();i++)
				System.out.println(c.getItem(i));
		}	
		else
			System.out.println("No existen cuentas ");
   }
   //==============================================================
  static float montoTota() {
	float monto=0;
	for(int i=0;i<c.getSize();i++) {
	  monto+=c.getItem(i).getSaldo();
    }
	return monto;
  }
  //================================================================
 static void monPromedio() {
	 float prome=montoTota()/c.getSize();
	System.out.println("El monto promedio de todas las cuentas es "+ prome);
 }
//===================================================================
 static void cuenMayoraMil() {
	if(!c.isFree()) {
		for(int i=0;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()>10000)
				System.out.println(c.getItem(i));
    }else
    	System.out.println("No existen cuentas por el momento ");
}
 //====================================================================
static float saldoMayor() {
	float mayor=c.getItem(0).getSaldo();
	for(int i=1;i<c.getSize();i++)
		if(c.getItem(i).getSaldo()>mayor)
			mayor=c.getItem(i).getSaldo();
	return mayor;
}
//======================================================================
static void cuentaMayor() {
	if(!c.isFree()) {
		float mayor=saldoMayor() ;
		for(int i=0;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()==mayor)
				System.out.println(c.getItem(i));
	}else
		System.out.println("No existen cuentas por el momento "); 	
}
//===========================================================================
static float saldoMenor() {
	float menor=c.getItem(0).getSaldo();
	for(int i=1;i<c.getSize();i++)
		if(c.getItem(i).getSaldo()<menor)
			menor=c.getItem(i).getSaldo();
	return menor;
}
//============================================================================
static void cuentaMenor() {
	if(!c.isFree()) {
		float menor=saldoMenor() ;
		for(int i=0;i<c.getSize();i++)
			if(c.getItem(i).getSaldo()==menor)
				System.out.println(c.getItem(i));
	}else
		System.out.println("No existen cuentas por el momento "); 	
}
//===========================================================================
  static void inicializa() {
	c=new CuentasBancarias();
}
  //=========================================================================
  static void menuConsultas() {
	  int opcion=0;
	  while(true) {
		  System.out.println(" 1.-Consulta monto total");  
		  System.out.println(" 2.-Consulta monto promedio entre todas las cuentas");  
		  System.out.println(" 3.-Consulta cuentas por encima de los $10000.00");  
		  System.out.println(" 4.-Consulta cuenta que tiene el máximo saldo");  
		  System.out.println(" 5.-Consulta  cuenta que tiene el minimo saldo");  
		  System.out.println(" 6.-salir");
		  opcion=io.nextInt();
		  switch(opcion){
		  	case 1:System.out.println(montoTota());break;
		  	case 2:monPromedio();break;
		  	case 3:cuenMayoraMil();break;
		  	case 4:cuentaMayor();break;
		  	case 5:cuentaMenor();break;
	     }
		  if(opcion==6) break;
      }
  }
//=======================================================================
   static void menuoperac() throws ExcepcionCuenta, ExcepcionElimina0, ExcepcionRetiro_Invaido, ExcepcionRetiro {
	  int opcio;
	  while(true) {	  
		  System.out.println(" 1.-Agregar una cuenta "); 
		  System.out.println(" 2.-Lista de cuentas agregadas ");  
		  System.out.println(" 3.-Realizar un deposito ");  
		  System.out.println(" 4.-Realizar un retiro");  
		  System.out.println(" 5.-Elimina una cuenta ");  
		  System.out.println(" 6.- Menu de consultas");  
		  System.out.println(" 7.-salir");
		  opcio=io.nextInt();
		  switch(opcio){
		  	case 1:agregarCuenta();break;
		  	case 2:listado();break;
		  	case 3:deposito();break;
		  	case 4:retiro();break;
		  	case 5:elimina();break;
		  	case 6:menuConsultas();break;
	     }
		  if(opcio==7) break;
      }
   }
 //=====================================================================
   static void run() throws ExcepcionCuenta, ExcepcionElimina0, ExcepcionRetiro_Invaido, ExcepcionRetiro {
	inicializa();
	menuoperac(); 
  }
}