package org.equinoxosgi.toast.dev.airbag;

/* Criar um listener permite que o airbag seja independente
 * dos objetos que ele est� interessado */
public interface IAirbagListener {
	public void deployed();
}
