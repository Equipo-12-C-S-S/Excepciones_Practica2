/*******************************************************************************
 * 2021, All rights reserved.
 *******************************************************************************/
package clases.ito.poo;

import java.time.LocalDate;

import javax.swing.JOptionPane;
import ecepxiones.ito.poo.ExcepcionesNumCuenta;
import ecepxiones.ito.poo.ExcepcionSadoApertura;
import ecepxiones.ito.poo.ExcepcionDeposito;
import ecepxiones.ito.poo.ExcepcionRetiro;
import ecepxiones.ito.poo.ExcepcionRetiro_Invaido;
/**
 * Description of CuentaBancaria.
 * 
 * @authors Guadalupe y Emmanuel
 */
public class CuentaBancaria implements Comparable<CuentaBancaria> {
	
	private long numCuenta = 0L;
	private String nomCliente = "";
	private float saldo = 0F;
	private java.time.LocalDate fechaApertura = null;
	private java.time.LocalDate fechaActualizacicon = null;
//===================================================================================
	private void verificaCuenta(long numCuenta)throws ExcepcionesNumCuenta {
		if( numCuenta<9999)
			throw new ExcepcionesNumCuenta("el número de cuenta debe ser un valor superior a 9999");	
	}
//===================================================================================
	private void verificaSaldo(float saldo)throws  ExcepcionSadoApertura{
		if(saldo>5000)
        	throw new ExcepcionSadoApertura("el saldo mínimo para apertura será de $5000.");
	}
//========================================================================================
	public CuentaBancaria(long numCuenta, String nomCliente, float saldo, LocalDate fechaApertura,
			LocalDate fechaActualizacicon) throws ExcepcionesNumCuenta ,ExcepcionSadoApertura{
		super();
		verificaCuenta(numCuenta);
		verificaSaldo(saldo);
		this.numCuenta = numCuenta;
		this.nomCliente = nomCliente;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;
		this.fechaActualizacicon = fechaActualizacicon;
	}
//=======================================================================================
	public CuentaBancaria() {
		// TODO Auto-generated constructor stub
	}
	//===================
	public boolean retiroR(float cantidad) throws ExcepcionRetiro,ExcepcionRetiro_Invaido{
		boolean retiro = false;
		if (cantidad>=100 && cantidad<=6000 && cantidad%100==0) {
			if (this.saldo >= cantidad) {
				this.saldo -= cantidad;
				retiro = true;
				this.fechaActualizacicon = LocalDate.now();
			}else if(this.saldo<=cantidad) {
		    	throw new ExcepcionRetiro_Invaido("La cuenta tiene fondos insuficientes");}
		}else 
				throw new ExcepcionRetiro("cantidad invalida: debe ser entre $100 y  $6000");
		
		return retiro;
	}
//==========================================================================================
	public boolean depositoD(float cantidad) throws ExcepcionDeposito {
		boolean deposito= false;
		if (cantidad>=500 && cantidad<=25000) {
			this.saldo += cantidad;
			deposito= true;
			this.fechaActualizacicon = LocalDate.now();
		}else
			throw new ExcepcionDeposito("La cantidad a depositar debe ser entre $500 y $25000");
		return deposito;
	}
//===========================================================================================
	public long getNumCuenta() {
		return this.numCuenta;
	}
	
	public void setNumCuenta(long numCuenta) throws ExcepcionesNumCuenta {
		verificaCuenta(numCuenta);
	    this.numCuenta = numCuenta;
	}

	public String getNomCliente() {
		return this.nomCliente;
	}
	
	public void setNomCliente(String newNomCliente) {
	    this.nomCliente = newNomCliente;
	}

	public float getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(float saldo) throws ExcepcionSadoApertura {
		verificaSaldo(saldo);
	    this.saldo = saldo;
	}

	public java.time.LocalDate getFechaApertura() {
		return this.fechaApertura;
	}
	
	public void setFechaApertura(java.time.LocalDate newFechaApertura) {
	    this.fechaApertura = newFechaApertura;
	}

	public java.time.LocalDate getFechaActualizacicon() {
		return this.fechaActualizacicon;
	}
	
	public void setFechaActualizacicon(java.time.LocalDate newFechaActualizacicon) {
	    this.fechaActualizacicon = newFechaActualizacicon;
	}
//======================================================================
	public String toString() {
		return "CuentaBancaria [numCuenta=" + numCuenta + ", nomCliente=" + nomCliente + ", saldo=" + saldo
				+ ", fechaApertura=" + fechaApertura + ", fechaActualizacicon=" + fechaActualizacicon + "]";
	}
//==========================================================================
	public int compareTo(CuentaBancaria o) {
		// TODO Auto-generated method stub
		int compare=0;
		if(this.numCuenta<o.getNumCuenta())
			compare=-1;
		else if(this.numCuenta>o.getNumCuenta())
			compare=1;
		return compare;
	}

}
