package uk.ac.manchester.skeleton;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.apache.cxf.annotations.WSDLDocumentation;

/**
 * This is the declaration of the <i>interface</i> of the web service, it's Port
 * Type. The {@link WebService} annotation says just that. We keep the namespace
 * in a separate file (actually an interface) so that we can get it consistent
 * everywhere. The {@link WSDLDocumentation} does just that; provides
 * human-readable information to go into the WSDL document.
 */
@WebService(name = "skeletonWebAppService", targetNamespace = Namespaces.NAMESPACE)
@WSDLDocumentation("The SOAP service interface to the skeleton web application.")
public interface MyPortType {
	/**
	 * A very simple method that just returns a value. The {@link WebResult}
	 * annotation controls what the <i>tooled</i> name of the result. The
	 * {@link WebMethod} annotation controls what name the operation has in the
	 * WSDL file; if it is omitted, the method name is used.
	 * 
	 * @return A string (that we pull from a configuration file).
	 */
	@WebResult(name = "HealthyOption")
	@WSDLDocumentation("Return the healthy option that this service was configured with.")
	@WebMethod(operationName = "healthy-option")
	String healthyOption();

	/**
	 * A simple method that takes a simple argument and produces a simple result
	 * based on it. Note that taking multiple arguments is trivial; just add
	 * more arguments! The {@link WebParam} annotation controls what the
	 * <i>tooled</i> name of the argument is, and prevents an ugly default from
	 * being used. (You can also use <tt>@WebParam</tt> to make an argument be
	 * an OUT or INOUT parameter, but that's pretty confusing; it's better to
	 * return a compound document.)
	 * 
	 * @param who
	 *            The argument string, presumably a name.
	 * @return The response string, a sort of "hello world".
	 */
	@WebResult(name = "HelloResponse")
	@WSDLDocumentation("Say hello.")
	@WebMethod(operationName = "say-hello")
	String sayHello(
			@WebParam(name = "who") @XmlElement(required = true) String who);

	/**
	 * A more complex method that takes a compound argument and produces a
	 * compound result.
	 * 
	 * @param input
	 *            The input document, parsed into a Plain Old Java Object (POJO;
	 *            think "<tt>struct</tt>" if you know C or C++) by JAXB.
	 * @return The output document as a POJO, which JAXB will serialize.
	 */
	@WebResult(name = "SwapResponse")
	@WSDLDocumentation("Swap the values.")
	@WebMethod(operationName = "swap")
	CompoundExample swapThem(
			@WebParam(name = "in") @XmlElement(required = true) CompoundExample input);
}
