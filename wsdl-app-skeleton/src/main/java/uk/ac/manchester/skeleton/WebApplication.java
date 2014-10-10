package uk.ac.manchester.skeleton;

import static org.apache.commons.logging.LogFactory.getLog;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.cxf.annotations.WSDLDocumentation;
import org.springframework.beans.factory.annotation.Required;

import uk.ac.manchester.utils.CallTimeLogger.PerformanceLogged;

/**
 * This is the implementation of a skeleton of a web application. The
 * {@link WebService} annotation here, on a class, defines this as providing a
 * WSDL <i>service</i> and the <tt>endpointInterface</tt> attribute says that
 * that's the port type that the service implements.
 * <p>
 * WSDL is <i>all</i> about services and port types.
 * <p>
 * Note that we have a default no-argument constructor, and we follow the Java
 * Beans model for the setter for the property. That is how Spring works. You
 * don't have to supply getters for properties in Spring beans if you don't wish
 * to.
 */
@WebService(endpointInterface = "uk.ac.manchester.skeleton.MyPortType", serviceName = "SkeletonWebApplication", targetNamespace = Namespaces.NAMESPACE)
@WSDLDocumentation("The implementation of the skeleton web service.")
public class WebApplication implements MyPortType {
	private static final Log log = getLog("Webapp.Skeleton");

	/**
	 * A reference to another bean, which holds the values parsed from the
	 * baked-in properties file.
	 */
	private Configuration config;

	/** Setter for the "<tt>configuration</tt>" property. Not a webapp method! */
	@Required
	public void setConfiguration(Configuration config) {
		this.config = config;
	}

	/**
	 * This method is called after all properties have been set, but before this
	 * bean starts service. By the magic of the annotation and Spring's bean
	 * lifecycle management.
	 */
	@PostConstruct
	void finishSetup() {
		log.info("finished setting things up (version=" + config.getVersion()
				+ ")");
	}

	/**
	 * This method is just as the bean finishes service. Again, this is powered
	 * by Spring's bean lifecycle management
	 */
	@PreDestroy
	void startTeardown() {
		log.info("terminating web application (version=" + config.getVersion()
				+ ")");
	}

	///////////////////////////////////////////////////////
	// Web application methods start below!

	@Override
	@PerformanceLogged
	public String healthyOption() {
		return config.getFruit();
	}

	@Override
	@PerformanceLogged
	public String sayHello(String who) {
		return "Hello, " + who + "!";
	}

	@Override
	@PerformanceLogged
	public CompoundExample swapThem(CompoundExample in) {
		CompoundExample out = new CompoundExample();
		out.abc = in.def;
		out.def = in.abc;
		return out;
	}
}
