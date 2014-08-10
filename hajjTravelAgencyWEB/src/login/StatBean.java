package login;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.PilgrimManLocal;


@ManagedBean
@ViewScoped

public class StatBean implements Serializable {

	
		  
	    /**
	 * 
	 */
	private static final long serialVersionUID = 414613420061158548L;
	public StatBean() {  
        createPieModel();  
    } 
	
	@EJB
	private PilgrimManLocal pml;
	
		private PieChartModel pieModel;
		private PieChartModel pieModel1;

		
		private List<Pilgrim> males;
		

		public void setPieModel(PieChartModel pieModel) {
			this.pieModel = pieModel;
		}

	
		@PostConstruct
		public void init(){
			males=pml.getPilgrimsByGender("Male");
			}
	  
	    public PieChartModel getPieModel() {  
	        return pieModel;  
	    }  
	  
	    private void createPieModel() {  
	        pieModel = new PieChartModel(); 
	        pieModel1 = new PieChartModel();  

	  
	        pieModel.set("Male", 7); 
	        pieModel.set("Female", 3); 

	        pieModel1.set("Tunis",5); 
	        pieModel1.set("Bizerte", 1);
	        pieModel1.set("Monastir", 2);


	         
	    }


		public PieChartModel getPieModel1() {
			return pieModel1;
		}


		public void setPieModel1(PieChartModel pieModel1) {
			this.pieModel1 = pieModel1;
		}



	}  
	  


