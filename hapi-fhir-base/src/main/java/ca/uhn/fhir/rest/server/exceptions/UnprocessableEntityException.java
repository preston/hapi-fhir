package ca.uhn.fhir.rest.server.exceptions;

/*
 * #%L
 * HAPI FHIR - Core Library
 * %%
 * Copyright (C) 2014 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import ca.uhn.fhir.model.dstu.resource.OperationOutcome;
import ca.uhn.fhir.rest.server.Constants;

/**
 * Represents an <b>HTTP 422 Unprocessable Entity</b> response, which means that a resource was rejected by the server because it "violated applicable FHIR profiles or server business rules".
 * 
 * <p>
 * This exception will generally contain an {@link OperationOutcome} instance which details the failure.
 * </p>
 * 
 * @see InvalidRequestException Which corresponds to an <b>HTTP 400 Bad Request</b> failure
 */
public class UnprocessableEntityException extends BaseServerResponseException {

	private static final String DEFAULT_MESSAGE = "Unprocessable Entity";
	private static final long serialVersionUID = 1L;
	public static final int STATUS_CODE = Constants.STATUS_HTTP_422_UNPROCESSABLE_ENTITY;

	/**
	 * Constructor
	 * 
	 * @param theMessage
	 *            The message to add to the status line
	 *  @param theOperationOutcome The OperationOutcome resource to return to the client
	 */
	public UnprocessableEntityException(String theMessage, OperationOutcome theOperationOutcome) {
		super(STATUS_CODE, theMessage, theOperationOutcome);
	}

	
	/**
	 * Constructor which accepts an {@link OperationOutcome} resource which will be supplied in the response
	 */
	public UnprocessableEntityException(OperationOutcome theOperationOutcome) {
		super(STATUS_CODE, DEFAULT_MESSAGE, theOperationOutcome == null ? new OperationOutcome() : theOperationOutcome);
	}

	/**
	 * Constructor which accepts a String describing the issue. This string will be translated into an {@link OperationOutcome} resource which will be supplied in the response.
	 */
	public UnprocessableEntityException(String theMessage) {
		super(STATUS_CODE, DEFAULT_MESSAGE, toOperationOutcome(theMessage));
	}

	/**
	 * Constructor which accepts an array of Strings describing the issue. This strings will be translated into an {@link OperationOutcome} resource which will be supplied in the response.
	 */
	public UnprocessableEntityException(String... theMessage) {
		super(STATUS_CODE, DEFAULT_MESSAGE, toOperationOutcome(theMessage));
	}

	private static OperationOutcome toOperationOutcome(String... theMessage) {
		OperationOutcome operationOutcome = new OperationOutcome();
		if (theMessage != null) {
			for (String next : theMessage) {
				operationOutcome.addIssue().setDetails(next);
			}
		}
		return operationOutcome;
	}

}
