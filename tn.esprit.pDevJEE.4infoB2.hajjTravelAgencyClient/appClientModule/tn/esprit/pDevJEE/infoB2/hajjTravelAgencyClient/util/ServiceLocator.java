package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;


public class ServiceLocator {
	
		private Context context;
		private Map<String, Object> cache;

		private static ServiceLocator instance;

		private ServiceLocator() {
			try {
				context = new InitialContext();
				cache = new HashMap<String, Object>();
			} catch (Exception e) {
				throw new ServiceLocatorException(e);
			}

		}

		public static ServiceLocator getInstance() {
			if (instance == null)
				instance = new ServiceLocator();
			return instance;
		}

		public Object getRemoteInterface(String jndiName)
				throws ServiceLocatorException {
			Object remoteInterface = getRemoteObject(jndiName);
			return remoteInterface;
		}

		private synchronized Object getRemoteObject(String jndiName)
				throws ServiceLocatorException {
			Object remoteObject = cache.get(jndiName);
			try {
				if (remoteObject == null) {
					remoteObject = context.lookup(jndiName);
					cache.put(jndiName, remoteObject);
				}
			} catch (Exception e) {
				throw new ServiceLocatorException(e);
			}

			return remoteObject;
		}
	}


