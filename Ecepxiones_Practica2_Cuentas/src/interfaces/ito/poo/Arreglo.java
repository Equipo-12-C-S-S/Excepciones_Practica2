package interfaces.ito.poo;

public interface Arreglo<T> {
	
	public boolean addItem(T item);//Agreaga una nueva cuenta 
	public boolean existeIte(T item); //muestra si la cuenta exite 
	public T getItem(int pos); 
	public int getSize(); // retorna cantidad de elementos
	public boolean delete(T item); //borra una cuanta 
	public boolean isFree(); // si esta vacio  
	public boolean isFull();
}
