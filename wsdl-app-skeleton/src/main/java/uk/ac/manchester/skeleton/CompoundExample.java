package uk.ac.manchester.skeleton;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A sample of how to do a complex type using JAXB. The {@link XmlType}
 * annotation on the class is the key. Check out the other annotations in the
 * {@link javax.xml.bind.annotation} package for other options (especially
 * {@link XmlAttribute}) and learn how to use their arguments.
 * <p>
 * Do not put complex methods inside JAXB-annotated classes (except possibly by
 * overriding {@link Object#toString()} and {@link Object#hashCode()} and
 * providing extra constructors). Keep them simple and make sure that there is a
 * no-argument constructor! Put the complexity in the service classes and the
 * beans that they use.
 */
@XmlType
public class CompoundExample {
	@XmlElement
	public String abc;
	@XmlElement
	public String def;
}
