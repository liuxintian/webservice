package com.omt.webservice.test.unit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;

public class RestQuestionTest {
	
	public static final String URL = "https://query.omnimarkettide.com:15661/";
	
    
    @Test
    public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
          throws ClientProtocolException, IOException{
       // Given
       String name = RandomStringUtils.randomAlphabetic( 8 );
       HttpUriRequest request = new HttpGet(URL  + name );
       // When
       HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
       // Then
       assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }
    
    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
    		throws ClientProtocolException, IOException{
       // Given
       String jsonMimeType = "application/json";
       HttpUriRequest request = new HttpGet( URL + "asxdata/all" );
       // When
       HttpResponse response = HttpClientBuilder.create().build().execute( request );
       // Then
       String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
       assertEquals( jsonMimeType, mimeType );
    }
    
    public static String retrieveJsonFromResponse( final HttpResponse response )
    	      throws IOException{
    	   Preconditions.checkNotNull( response );
    	   
    	   return IOUtils.toString( response.getEntity().getContent() );
    }

    public static < T >T retrieveResourceFromResponse
    	      ( final HttpResponse response, final Class< T > clazz ) throws IOException{
    	   Preconditions.checkNotNull( response );
    	   Preconditions.checkNotNull( clazz );
    	   
    	   final String jsonFromResponse = retrieveJsonFromResponse( response );
    	   return convertJsonToResource( jsonFromResponse, clazz );
    }
    
    public static < T >String convertResourceToJson( final T resource )
    	      throws IOException{
    	   Preconditions.checkNotNull( resource );
    	  
    	  return new ObjectMapper().writeValueAsString( resource );
    	}

    public static < T >T convertJsonToResource
    	      ( final String json, final Class< T > clazzOfResource ) throws IOException{
    	  Preconditions.checkNotNull( json );
    	  Preconditions.checkNotNull( clazzOfResource );
    	  
    	  return new ObjectMapper().readValue( json, clazzOfResource );
    }    
}
