package org.pub.pt.data.ws.domain;

/**
 * This is an functional interface to enable the creation of not so verbose {@link WebResult}
 * objects. This is used to encapsulate the exception handling logic from the implementation
 * of the controllers 
 * @author balhau
 *
 * @param <T> Generic type 
 */
@FunctionalInterface
public interface Wrapper<T> {
	T wrap() throws Exception;
}
