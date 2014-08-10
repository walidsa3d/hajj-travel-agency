package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.packageManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pakage;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class PackageMan
 */
@Stateless
public class PackageMan implements PackageManRemote, PackageManLocal {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;

    /**
     * Default constructor. 
     */
    public PackageMan() {
    }

	@Override
	public void addPackage(Pakage pack) {
		em.persist(pack);
		traceman.traceIt("ADD", pack);
	}

	@Override
	public void updatePackage(Pakage pack) {
		em.merge(pack);
		traceman.traceIt("UPDATE", pack);
	}

	@Override
	public void removePackage(Pakage pack) {
		em.remove(em.merge(pack));
		traceman.traceIt("DELETE", pack);
	}

	@Override
	public Pakage findPackageById(int idPakage) {
		return em.find(Pakage.class, idPakage);
	}

	@Override
	public List<Pakage> getAllPackages() {
		Query query=em.createQuery("Select p from Pakage p");
		return query.getResultList();
	}

}
