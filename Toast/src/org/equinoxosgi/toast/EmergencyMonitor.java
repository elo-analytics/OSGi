package org.equinoxosgi.toast;

public class EmergencyMonitor implements IAirbagListener {
	private Airbag airbag;
	private Gps gps;
	
	/*business logic - satisfaz a interface do listener e implementa o comportamento
	 * real de emergencia. Todos os outros metodos sao desenhados para dar suporte
	 * a esse codigo*/
	public void deployed(){
		System.out.println("Emergency occurred at lat=" +
	gps.getLatitude()
		+ " lon=" + gps.getLongitude() + " heading=" +
	gps.getHeading()
		+ " speed=" +gps.getSpeed());
	}
	
	/* Esses metodos set poderiam ter sido colocados no construtor,
	 * mas a separacao de instanciacao e inicializacao e' bastante util
	 * 
	 * e' tambem uma boa pratica criar metodos de startup e shutdown simetricos
	 * i.e., qualquer comportamento feito por um deve ser desfeito pelo outro
	 * na ordem reversa*/
	public void setAirbag (Airbag value){
		airbag = value;
	}
	
	public void setGps (Gps value){
		gps = value;
	}
	
	/*Da mesma maneira, separar o startup e o shutdown desacopla o lifecycle 
	 * da instanciacao da classe do monitor - tambem util para a modularizacao*/
	public void shutdown() {
		airbag.removeListener(this);
	}
	
	public void startup() {
		airbag.addListener(this);
	}
}

