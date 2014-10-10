package uk.ac.manchester.utils;

import static java.lang.String.format;
import static java.lang.System.nanoTime;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apache.commons.logging.LogFactory.getLog;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.commons.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;

/**
 * This class is responsible for timing all invocations of publicly-exposed
 * methods of the webapp. It's connected to the webapp through an AspectJ-style
 * pointcut that targets a custom annotation.
 * <p>
 * AspectJ is a <i>very</i> powerful mechanism for attaching functionality to
 * methods and classes; we are only using a little bit of it here.
 * 
 * @author Donal Fellows
 */
@Aspect
public class CallTimeLogger {
	private static final Log log = getLog("Webapp.Performance");

	private long threshold;

	/**
	 * Threshold beneath which monitored call times are not logged. In
	 * nanoseconds.
	 */
	@Value("40000")
	public void setThreshold(long threshold) {
		this.threshold = threshold;
	}

	/**
	 * The timer for this aspect. The wrapped invocation will be timed, and a
	 * log message written if the configured threshold is exceeded.
	 * 
	 * @param call
	 *            The call being wrapped.
	 * @return The result of the call.
	 * @throws Throwable
	 *             If anything goes wrong with the wrapped call.
	 * @see System#nanoTime()
	 */
	@Around("@annotation(uk.ac.manchester.utils.CallTimeLogger.PerformanceLogged)")
	public Object time(ProceedingJoinPoint call) throws Throwable {
		long fore = nanoTime();
		try {
			return call.proceed();
		} finally {
			long aft = nanoTime();
			long elapsed = aft - fore;
			if (elapsed > threshold)
				log.info(format("call to %s took %.3fms", call.toShortString(),
						elapsed / 1000000.0));
		}
	}

	/**
	 * Mark methods that should be counted by the invocation counter. This acts
	 * as a target for the {@link Around} advice used by the timer aspect;
	 * annotate any Spring bean method with this annotation and the aspect will
	 * work out how long it takes to execute and log that fact.
	 * 
	 * @author Donal Fellows
	 */
	@Retention(RUNTIME)
	@Documented
	@Target(METHOD)
	public static @interface PerformanceLogged {
	}
}
