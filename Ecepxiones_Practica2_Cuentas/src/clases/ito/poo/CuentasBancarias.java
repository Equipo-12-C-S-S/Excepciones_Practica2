package clases.ito.poo;

import interfaces.ito.poo.Arreglo;

public class CuentasBancarias implements Arreglo<CuentaBancaria> {
	
	private CuentaBancaria cuentas[]=null;
	private  int ultimo=0;
	private final int INC=5;
	
	public CuentasBancarias() {
		super();
		this.cuentas = new CuentaBancaria[INC];
		this.ultimo = -1;//indica que esta vacio el arreglo
	}
	private void crecerArreglo() {
    CuentaBancaria temporal[]=new CuentaBancaria[this.cuentas.length+INC];
    for(int i=0;i<this.cuentas.length;i++)	
    cuentas=temporal;
    }
	
	@Override
	public boolean addItem(CuentaBancaria item) {
		// TODO Auto-generated method stub
		boolean bandera=false;
		if(!existeIte(item)) {
			if(isFull())
				crecerArreglo();
			int i=0;
			for(;i<=this.ultimo;i++)
				if(item.compareTo(cuentas[i])<0) {
					break;
				}
			for(int j=this.ultimo;j>=i;j--)
				cuentas[j+1]=cuentas[j];
			cuentas[i]=item;
			this.ultimo++;
			bandera=true;
			}
		return bandera;
	}


	@Override
	public boolean existeIte(CuentaBancaria item) {
		// TODO Auto-generated method stub
		boolean existe=false;
		for(int i=0;i<=this.ultimo;i++)
			if(item.compareTo(cuentas[i])==0) {
				existe=true;
				break;
			}
		return existe;
	}

	@Override
	public CuentaBancaria getItem(int pos) {
		// TODO Auto-generated method stub
		CuentaBancaria cb=null;
		if(!this.isFree()&& pos<=this.ultimo)
			cb=cuentas[pos];
		return cb;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.ultimo+1;
	}

	@Override
	public boolean delete(CuentaBancaria item) {
		// TODO Auto-generated method stub
		boolean delete=false;
		if(this.existeIte(item)) {
			int i=0;
			for(;i<=this.ultimo;i++)
				if(item.compareTo(cuentas[i])==0)
					break;
			for(;i<this.ultimo;i++)
				cuentas[i]=cuentas[i+1];
			this.ultimo--;
			delete=true;
		}
		return delete;
	}

	@Override
	public boolean isFree() {
		// TODO Auto-generated method stub
		return this.ultimo==-1;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return this.ultimo+1==this.cuentas.length;
	}
}
