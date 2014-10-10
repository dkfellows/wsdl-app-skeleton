/**
 * This is the declaration of how namespaces are mapped to XML prefixes. This
 * matters more when you have many namespaces in use, but since the defaults
 * are <i>awful</i>, you want to override them.
 * <p>
 * Yes, this file looks strange and has a strange name.
 */

@XmlSchema(namespace = NAMESPACE, elementFormDefault = QUALIFIED, attributeFormDefault = UNQUALIFIED, xmlns = {
		@XmlNs(namespaceURI = NAMESPACE, prefix = "xmpl"),
})
package uk.ac.manchester.skeleton;

import static javax.xml.bind.annotation.XmlNsForm.QUALIFIED;
import static javax.xml.bind.annotation.XmlNsForm.UNQUALIFIED;
import static uk.ac.manchester.skeleton.Namespaces.NAMESPACE;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;

