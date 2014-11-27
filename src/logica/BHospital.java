package logica;

public class BHospital {
	
	Hospital hosp_asignado;
	
	public Hospital getBase(){return hosp_asignado;}
	
	public void setBase(Hospital h){hosp_asignado=h;}
	
	public void privatizar(){hosp_asignado=null;}
	

}
