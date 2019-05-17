package perf;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.UpdateRequest;

public class SolrTest {

	private static void solrClientTest() throws Exception {
		String urlString = "http://localhost:8983/solr/test2";
		SolrClient solr = new HttpSolrClient.Builder(urlString).build();
		
		solr.addBean(new MyBean(1, "test2", "test2"));
		solr.commit();
	}
	
	private static void solrCloudTest() throws Exception {
		String urlString = "http://localhost:8983/solr/test2";
		
		ConcurrentUpdateSolrClient server = new ConcurrentUpdateSolrClient.Builder("http://localhost:8983/solr/test2")
				.withQueueSize(20)
				.withThreadCount(10)
				.build();
//		
//		UpdateRequest updateRequest = new UpdateRequest();
//	    updateRequest.add(new );
//	    server.request(updateRequest);
	}
	
	public static void main(String[] args) throws Exception {
		
	}

}
